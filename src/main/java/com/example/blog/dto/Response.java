package com.example.blog.dto;

public class Response {
    String token;

    long userId;

    public long getUserId() {
        return userId;
    }

    public Response(String token, long userId) {
        this.token = token;
        this.userId = userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Response(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
