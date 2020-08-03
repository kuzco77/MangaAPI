package com.hust.manga.controllers;

import com.hust.manga.core.AbstractController;
import com.hust.manga.services.CloneFromApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("clone")
public class CloneDBController extends AbstractController {
    @Autowired
    CloneFromApiService cloneFromApiService;

    @PostMapping
    @RequestMapping("category")
    public boolean cloneCategory() {
        return cloneFromApiService.cloneCategory();
    }

    @PostMapping
    @RequestMapping("manga")
    public boolean cloneAllManga() {
        return cloneFromApiService.cloneAllMangas();
    }
}
