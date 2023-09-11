package com.example.blog.serviceImpl;

import com.example.blog.dto.CustomException;
import com.example.blog.model.Blog;
import com.example.blog.model.Category;
import com.example.blog.model.User;
import com.example.blog.repo.BlogRepository;
import com.example.blog.repo.CategoryRepository;
import com.example.blog.repo.UserRepository;
import com.example.blog.services.BlogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class BlogServiceImpl implements BlogServices {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Blog createBlog(Blog blog) {
        Optional<Category> category = categoryRepository.findById(blog.getCategoryId().getId());
        Optional<User> user = userRepository.findById(blog.getUserId().getId());

        if (category.isPresent()) {
            blog.setCategoryId(category.get());
        }
        if (user.isPresent()) {
            blog.setUserId(user.get());

                user.get().setCountOfBlogs(user.get().getCountOfBlogs() + 1);

        }

        return blogRepository.save(blog);
    }

    @Override
    public List<Blog> getBlogByUserId(Long userId) {
        return blogRepository.findAllByUserIdId(userId);
    }

    @Override
    public List<Blog> getBlogByCategoryId(String Category) {
        return blogRepository.findAllByCategoryIdCategoryType(Category);
    }

    @Override
    public List<Blog> getBlog() {
        return blogRepository.findAll();
    }

    @Override
    public Blog updateBlog(Long id, Blog updateBlog) throws CustomException {
        Blog blog = blogRepository.findById(id).orElse(null);
        if (blog != null) {
            blog.setBlogDescription(blog.getBlogDescription());
            blog.setBlogName(blog.getBlogName());
            Optional<Category> category = categoryRepository.findById(blog.getCategoryId().getId());
            Optional<User> user = userRepository.findById(blog.getUserId().getId());
            if (category.isPresent()) {
                blog.setCategoryId(blog.getCategoryId());
            }
            if (user.isPresent()) {
                blog.setUserId(blog.getUserId());
            }
            return blogRepository.save(blog);
        }
        throw new CustomException("blog not found");
    }

    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public void validBlogData(Blog blog) throws CustomException {
        if (!userRepository.existsById(blog.getUserId().getId())) {
            throw new CustomException("User not acceptable");
        }
        if (!categoryRepository.existsById(blog.getCategoryId().getId())) {
            throw new CustomException("this category not prefer");
        }
        if (blog.getBlogDescription().trim().equals("") || blog.getBlogDescription() == null) {
            throw new CustomException("Blog Description is compulsory");
        }

    }

    @Override
    public void validId(long id) throws CustomException {
        if (!blogRepository.existsById(id)) {
            throw new CustomException("Invalid Blog Id was provided");
        }
    }

    @Override
    public void validUserId(long userId) throws CustomException {
        if (!userRepository.existsById(userId)) {
            throw new CustomException("User not found");
        }
    }

    @Override
    public void validCategoryId(String categoryId) throws CustomException {
        if (!categoryRepository.existsByCategoryType(categoryId)) {
            throw new CustomException("this category not found");
        }
    }

    @Override
    public List<Category> categoryList() {
        return categoryRepository.findAll();
    }
//    @Override
//    public List<List<Blog>> getBlogofFriends(long userId) {
//        User user = userRepository.findById(userId).orElse(null);
//        List<Long> friendList = user.getFriendList();
//        List<List<Blog>> friendsBlogList = new ArrayList<>();
//        for (Long frienndId : friendList) {
//            List<Blog> blog = blogRepository.findAllByUserIdId(frienndId);
//            friendsBlogList.add(blog);
//        }
//        return friendsBlogList;
//    }
}
