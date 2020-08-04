package com.hust.manga.services;

import com.google.gson.*;
import com.hust.manga.model.Category;
import com.hust.manga.model.Chapter;
import com.hust.manga.model.ChapterDetail;
import com.hust.manga.model.Manga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Consumer;

@Component
public class CloneFromApiService {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CategoryService categoryService;
    @Autowired
    MangaServices mangaServices;
    @Autowired
    ChapterService chapterService;
    @Autowired
    ChapterDetailService chapterDetailService;

    public boolean cloneCategory() {
        String url = "http://45.33.47.79:6978/category";
        String result = restTemplate.getForObject(url, String.class);
        assert result != null;
        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
        JsonArray dataJsonObject = jsonObject.getAsJsonArray("data");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ArrayList<Category> categories = gson.fromJson(dataJsonObject, CategoryList.class);
        return categoryService.createBulk("manga", categories);

    }

    public boolean cloneAllMangas() {
        List<Category> categories = categoryService.getAll("manga");
        for (Category item : categories) {
            cloneMangaByCategory(item.getOriginId());
        }
        System.out.println("Clone all manga done !");
        return true;
    }

    public void cloneMangaByCategory(String categoryId) {
        int pageCurrent = 0;
        boolean isFinished;
        do {
            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromHttpUrl("http://45.33.47.79:6978/manga/category")
                    .queryParam("categoryId", categoryId)
                    .queryParam("pageIndex", pageCurrent)
                    .queryParam("pageSize", 100)
                    .queryParam("sort", "-hits");
            String results = restTemplate.getForObject(builder.toUriString(), String.class);
            assert results != null;
            JsonObject jsonObject = new JsonParser().parse(results).getAsJsonObject();
            JsonArray dataJsonObject = jsonObject.getAsJsonArray("data");
            isFinished = dataJsonObject.size() == 0;
            if (!isFinished) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                ArrayList<Manga> mangas = gson.fromJson(dataJsonObject, MangaList.class);
                mangaServices.createBulk("manga", mangas);
                pageCurrent++;
            }
        } while (!isFinished);
        System.out.println(categoryId);
    }

    public boolean cloneAllChapter() {
        int SIZE_THREAD = 10;
        int pageCurrent = 1;
        boolean isFinished;
        do {
            List<Manga> mangas = mangaServices.getPage("manga", 10000, pageCurrent).list;
            isFinished = mangas.isEmpty();
            if (!isFinished) {
                pageCurrent++;
                ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
                int size = mangas.size();
                System.out.printf("\nManga : %d", size);
                int pageSize = (size % SIZE_THREAD == 0) ? size / SIZE_THREAD : size / SIZE_THREAD + 1;
                for (int i = 0; i < SIZE_THREAD; i++) {
                    int from = i * pageSize;
                    int to;
                    if (i == (SIZE_THREAD - 1)) {
                        to = size;
                    } else {
                        to = from + pageSize;
                    }
                    List<Manga> subChapterList = mangas.subList(from, to);
                    RequestHandle<Manga> requestHandler = new RequestHandle<>(subChapterList, from, to, manga -> cloneChapterByManga(manga.getOriginId()));
                    executor.execute(requestHandler);
                }

                executor.shutdown();
                while (!executor.isTerminated()) {
                    // Chờ xử lý hết các request còn chờ trong Queue ...
                }
            }
        } while (!isFinished);
        System.out.println("Clone chapter done!");
        return true;
    }

    public void cloneChapterByManga(String mangaId) {
        boolean isFinished;
        String results = restTemplate.getForObject("http://45.33.47.79:6978/chapter/manga/" + mangaId, String.class);
        JsonObject jsonObject = new JsonParser().parse(results).getAsJsonObject();
        JsonArray dataJsonObject = jsonObject.getAsJsonArray("data");
        isFinished = dataJsonObject.size() == 0;
        if (!isFinished) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            ArrayList<Chapter> chapters = gson.fromJson(dataJsonObject, ChapterList.class);
            chapterService.createBulk("manga", chapters);
        }
        System.out.println("clone done manga = " + mangaId);
    }

    public boolean cloneAllChapterDetail() {
        int SIZE_THREAD = 10;
        long startTime = System.currentTimeMillis();
        int pageCurrent = 1;
        boolean isFinished;
        do {
            List<Chapter> chapters = chapterService.getPage("manga", 10000, pageCurrent).list;
            isFinished = chapters.isEmpty();
            if (!isFinished) {
                pageCurrent++;
                ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
                int size = chapters.size();
                System.out.printf("\nChapter : %d", size);
                int pageSize = (size % SIZE_THREAD == 0) ? size / SIZE_THREAD : size / SIZE_THREAD + 1;
                for (int i = 0; i < SIZE_THREAD; i++) {
                    int from = i * pageSize;
                    int to;
                    if (i == (SIZE_THREAD - 1)) {
                        to = size;
                    } else {
                        to = from + pageSize;
                    }
                    List<Chapter> subChapterList = chapters.subList(from, to);
                    RequestHandle<Chapter> requestHandler = new RequestHandle<>(subChapterList, from, to, chapter -> cloneChapterDetailByChapter(chapter.getOriginId()));
                    executor.execute(requestHandler);
                }

                executor.shutdown();
                while (!executor.isTerminated()) {
                    // Chờ xử lý hết các request còn chờ trong Queue ...
                }
            }
            System.out.printf("Clone chapter details page %d done!", pageCurrent-1);

        } while (!isFinished);
        System.out.println("Clone chapter details all done!");
        System.out.println(System.currentTimeMillis() - startTime);
        return true;
    }

    public void cloneChapterDetailByChapter(String chapterId) {
        String url = "http://45.33.47.79:6978/chapter/imagesOfChapter/" + chapterId;
        String results = restTemplate.getForObject(url, String.class);
        assert results != null;
        JsonObject jsonObject = new JsonParser().parse(results).getAsJsonObject();
        JsonArray dataJsonObject = jsonObject.getAsJsonArray("data");
        if (dataJsonObject.size() > 0) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            ArrayList<ChapterDetail> chapterDetails = gson.fromJson(dataJsonObject, ChapterDetailList.class);
            chapterDetailService.createBulk("manga", chapterDetails);
        }
        System.out.println(chapterId);
    }


    static public class CategoryList extends ArrayList<Category> {
    }

    static public class MangaList extends ArrayList<Manga> {
    }

    static public class ChapterList extends ArrayList<Chapter> {
    }

    static public class ChapterDetailList extends ArrayList<ChapterDetail> {
    }

    private static class RequestHandle<T> implements Runnable {
        int from, to;
        List<T> list;
        Consumer<? super T> action;

        public RequestHandle(List<T> list, int from, int to, Consumer<? super T> action) {
            this.list = list;
            this.from = from;
            this.to = to;
            this.action = action;

        }

        @Override
        public void run() {
            System.out.printf("\nStart %s synchronization progress from %d to %d%n", "Chapter Detail", from, to);
            System.out.println("\nChapter syncing....");
            list.forEach(action);
            System.out.printf("\r%s completed: from %d to %d", "Chapter Detail", from, to);
        }
    }

}


