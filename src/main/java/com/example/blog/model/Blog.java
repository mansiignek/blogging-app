package com.example.blog.model;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "Blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Blog_id")
    private long id;

    @Column(name="Blog_desc")
    private String blogDescription;

    @ManyToOne
    @JoinColumn(name="User_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name="Category_id")
    private Category categoryId;
    @Column(name="Blog_name")
    private String blogName;

//    public String getDraftblog() {
//        return draftblog;
//    }
//
//    public void setDraftblog(String draftblog) {
//        this.draftblog = draftblog;
//    }

//    @Column(name="draftblog")
//    private String draftblog;

    public String getBlogDescription() {
        return blogDescription;
    }

    public User getUserId() {
        return userId;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setBlogDescription(String blogDescription) {
        this.blogDescription = blogDescription;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }




}
