package com.hust.manga.services;

import com.hust.manga.core.db.CRUDService;
import com.hust.manga.model.Manga;
import org.springframework.stereotype.Component;

@Component
public class MangaServices extends CRUDService<Manga, Integer> {
}
