package com.example.blog.services;

import com.example.blog.dto.CustomException;
import com.example.blog.model.Blog;
import com.example.blog.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlogServices {
    public Blog createBlog(Blog blog);
    public void validBlogData(Blog blog) throws CustomException;
    public List<Blog> getBlog();
    public List<Blog> getBlogByUserId(Long userId);
    public List<Blog> getBlogByCategoryId(String Category);
    public Blog updateBlog(Long id, Blog updateBlog) throws CustomException;
    public void deleteBlog(Long id);
    public void validId(long id) throws CustomException;
    public void validCategoryId(String categoryType) throws CustomException;
    public void validUserId(long userId) throws CustomException;
    public List<Category> categoryList();

    public List<List<Blog>> getBlogofFriends(long userId);

}
