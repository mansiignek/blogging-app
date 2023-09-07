package com.example.blog.services;


import com.example.blog.dto.CustomException;
import com.example.blog.dto.FriendRequest;
import com.example.blog.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserServices {
    public User createUser(User user) throws CustomException;
    public User getUserById(Long id);
    public User updateUser(Long id, User updatedUser) throws CustomException;
    public void deleteUser(Long id) throws CustomException;

    public void validId(long id) throws CustomException;
    public void validemail(String email) throws CustomException;
    public void validemailFotToken(String email) throws CustomException;
    public List<User> getUsersWithBirthdayToday();

//    public User sendFriendRequest(long userId, long requestedUserId);
//    public User acceptOrRejectFriendRequest(long userId, FriendRequest friendRequest);

}
