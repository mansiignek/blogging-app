package com.example.blog.repo;

import com.example.blog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findAllByUserIdId(long userId);

    List<Blog> findAllByCategoryId(long categoryId);

    List<Blog> findAllByCategoryIdCategoryType(String type);
}
