package com.example.blog.services;

import com.example.blog.dto.CustomException;
import com.example.blog.model.Comment;

import java.util.List;

public interface CommentServices {
    public Comment addComment(Comment comment);
    public List<Comment> getCommentByBlogId(Long blogId);
    public void deleteComment(Long id);
    public void validId(long id) throws CustomException;
}
