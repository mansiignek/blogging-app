package com.example.blog.controller;

import com.example.blog.dto.CustomException;
import com.example.blog.model.Blog;
import com.example.blog.model.User;
import com.example.blog.services.BlogServices;
import com.example.blog.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {
    @Autowired
    private UserServices userService;
    @Autowired
    private BlogServices blogServices;
    @PostMapping("/addBlog")
    public ResponseEntity<String> createBlog(@RequestBody Blog blog) throws CustomException {
        System.out.println();
        try {
            blogServices.validBlogData(blog);
            blogServices.createBlog(blog);
            return new ResponseEntity<>("blog data successfully added", HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/allBlog")
    public ResponseEntity<?> getBlog() {
        List<Blog> blog=blogServices.getBlog();
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    @GetMapping("/blog-by-user-id/{id}")
    public ResponseEntity<?> getBlogByUserById(@PathVariable Long id) {
        try {
            blogServices.validUserId(id);
            List<Blog> blog=blogServices.getBlogByUserId(id);
            return new ResponseEntity<>(blog, HttpStatus.OK);
        }catch (CustomException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/blogByCategoryId")
    public ResponseEntity<?> getBlogByCategoryId(@RequestParam String categoryType) {
        try {
            blogServices.validCategoryId(categoryType);
          List<Blog> blog = blogServices.getBlogByCategoryId(categoryType);
            return new ResponseEntity<>(blog, HttpStatus.OK);
        }catch (CustomException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/updateBlog/{id}")
    public ResponseEntity<String> updateBlog(@PathVariable Long id, @RequestBody Blog blog) throws CustomException {
        try {
            blogServices.validId(id);
            blogServices.validBlogData(blog);
            blogServices.updateBlog(id,blog);
            return new ResponseEntity<>("data successfully updated", HttpStatus.OK);
        }catch (CustomException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }}

    @DeleteMapping("/deleteBlog/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable Long id) throws CustomException {
        try {
            blogServices.validId(id);
            blogServices.deleteBlog(id);
            return new ResponseEntity<>("blog successfully deleted!!", HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
//    @GetMapping("/friendsBlog/{id}")
//    public ResponseEntity<?> friendsBlog(@PathVariable Long userId) throws CustomException {
//        try {
//            blogServices.validUserId(userId);
//            List<List<Blog>> friendBlog = blogServices.getBlogofFriends(userId);
//            return new ResponseEntity<>(friendBlog, HttpStatus.OK);
//        }catch (CustomException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
}
