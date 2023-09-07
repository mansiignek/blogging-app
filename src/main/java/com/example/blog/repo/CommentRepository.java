package com.example.blog.repo;

import com.example.blog.model.Blog;
import com.example.blog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByBlogIdId(long blogId);
}
