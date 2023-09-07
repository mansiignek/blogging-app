package com.example.blog.serviceImpl;

import com.example.blog.dto.CustomException;
import com.example.blog.model.Blog;
import com.example.blog.model.Category;
import com.example.blog.model.Comment;
import com.example.blog.model.User;
import com.example.blog.repo.BlogRepository;
import com.example.blog.repo.CommentRepository;
import com.example.blog.repo.UserRepository;
import com.example.blog.services.CommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServicesImpl implements CommentServices {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BlogRepository blogRepository;
    @Override
    public Comment addComment(Comment comment) {
        Optional<Blog> blog = blogRepository.findById(comment.getBlogId().getId());
        Optional<User> user = userRepository.findById(comment.getUserId().getId());

        if (blog.isPresent()) {
            comment.setBlogId(blog.get());
        }
        if (user.isPresent()) {
            comment.setUserId(user.get());

        }

        return commentRepository.save(comment);
    }



    @Override
    public List<Comment> getCommentByBlogId(Long blogId) {
        return commentRepository.findAllByBlogIdId(blogId);
    }
    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
    @Override
    public void validId(long id) throws CustomException {
        if (!commentRepository.existsById(id)) {
            throw new CustomException("Invalid comment Id was provided");
        }
    }
}
