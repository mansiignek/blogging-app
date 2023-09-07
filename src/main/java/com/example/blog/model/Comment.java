package com.example.blog.model;

import javax.persistence.*;

@Entity
@Table(name = "Comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Comment_id")
    private long id;

    @ManyToOne
    @JoinColumn(name="User_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name="Blog_id")
    private Blog blogId;

    @Column(name="comment")
    private String comments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Blog getBlogId() {
        return blogId;
    }

    public void setBlogId(Blog blogId) {
        this.blogId = blogId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
