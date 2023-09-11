package com.example.blog.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "User")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="User_id")
    private long id;

    @Column(name="email")
    private String email;

//    @Column(name="otp")
//    private String otp;
//
//    @Column(name="otpGenerationAttempts")
//    private int otpGenerationAttempts;

    @Column(name="name")
    private String name;

    @Column(name="birthday")
    private LocalDate birthday;
    @Column(name="profileStatus")
    private String profileStatus;

//    @Column(name="friendList")
//    @ElementCollection
//    private List<Long> friendList;

    @Column(name="countOfBlogs")
    private Integer countOfBlogs;

//    @Column(name="friendRequestList")
//    @ElementCollection
//    private List<Long> friendRequestList;



    public int getCountOfBlogs() {
        return countOfBlogs;
    }

    public void setCountOfBlogs(int countOfBlogs) {
        this.countOfBlogs = countOfBlogs;
    }


    public String getProfileStatus() {
        return profileStatus;
    }

    public void setProfileStatus(String profileStatus) {
        this.profileStatus = profileStatus;
    }

//    public List<Long> getFriendList() {
//        return friendList;
//    }
//
//    public void setFriendList(List<Long> friendList) {
//        this.friendList = friendList;
//    }



    public void setCountOfBlogs(Integer countOfBlogs) {
        this.countOfBlogs = countOfBlogs;
    }








    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }





    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

//    public List<Long> getFriendRequestList() {
//        return friendRequestList;
//    }
//
//    public void setFriendRequestList(List<Long> friendRequestList) {
//        this.friendRequestList = friendRequestList;
//    }
}
