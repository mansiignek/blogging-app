package com.example.blog.controller;

import com.example.blog.dto.CustomException;
import com.example.blog.model.Blog;
import com.example.blog.model.Comment;
import com.example.blog.model.User;
import com.example.blog.services.BlogServices;
import com.example.blog.services.CommentServices;
import com.example.blog.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private UserServices userService;
    @Autowired
    private BlogServices blogServices;
    @Autowired
    private CommentServices commentServices;
    @PostMapping("/addComment")
    public ResponseEntity<String> createComment(@RequestBody Comment comment) throws CustomException {
        try {
            blogServices.validId(comment.getBlogId().getId());
           userService.validId(comment.getUserId().getId());
           commentServices.addComment(comment);
            return new ResponseEntity<>("comment successfully added", HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/commentByBlogs/{id}")
    public ResponseEntity<?> getCommentByBlogId(@PathVariable Long id) throws CustomException{
        try {
            blogServices.validId(id);
            List<Comment> commentList=commentServices.getCommentByBlogId(id);
            return new ResponseEntity<>(commentList, HttpStatus.OK);
        }catch (CustomException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteComment/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) throws CustomException {
        try {
            commentServices.validId(id);
            commentServices.deleteComment(id);
            return new ResponseEntity<>("comment successfully deleted!!", HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
