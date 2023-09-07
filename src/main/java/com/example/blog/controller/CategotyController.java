package com.example.blog.controller;

import com.example.blog.model.Blog;
import com.example.blog.model.Category;
import com.example.blog.services.BlogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class CategotyController {
    @Autowired
    private BlogServices blogServices;
    @GetMapping("/category")
    public ResponseEntity<?> getBlogCategory() {
        List<Category> categoryList=blogServices.categoryList();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }
}
