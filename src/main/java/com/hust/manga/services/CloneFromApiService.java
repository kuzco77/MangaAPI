package com.hust.manga.services;

import com.google.gson.*;
import com.hust.manga.model.Category;
import com.hust.manga.model.Manga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Component
public class CloneFromApiService {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    CategoryService categoryService;

    @Autowired
    MangaServices mangaServices;

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
        return true;
    }

    public void cloneMangaByCategory(String categoryId) {
        int pageCurrent = 0;
        boolean isFinished = false;
        do {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://45.33.47.79:6978/manga/category")
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
        } while (isFinished);
    }

    public boolean cloneChapterByManga(String mangaId) {
        return mangaId != null;
    }

    public boolean cloneChapterDetailByChapter(String chapterId) {
        return chapterId != null;
    }

    static public class CategoryList extends ArrayList<Category> {
    }

    static public class MangaList extends ArrayList<Manga> {
    }

}


