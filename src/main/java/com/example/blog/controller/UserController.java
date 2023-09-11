package com.example.blog.controller;

import com.example.blog.auth.JwtUtil;
import com.example.blog.dto.CustomException;
import com.example.blog.dto.FriendRequest;
import com.example.blog.model.User;
import com.example.blog.services.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServices userService;
    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private  UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/addUsers")
    public ResponseEntity<String> createUser(@RequestBody User user) throws CustomException{
        try {
          //  userService.validemail(user.getEmail());
            userService.createUser(user);
            return new ResponseEntity<>("data successfully added", HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            userService.validId(id);
            User user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
    }catch (CustomException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user) throws CustomException {
        try {
            userService.validId(id);
           // userService.validemail(user.getEmail());
         userService.updateUser(id, user);
         return new ResponseEntity<>("data successfully updated", HttpStatus.OK);
    }catch (CustomException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }}

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) throws CustomException {
        try {
            userService.validId(id);
            userService.deleteUser(id);
            return new ResponseEntity<>("data successfully deleted!!", HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @PutMapping("/friendRequest/{id}")
//    public ResponseEntity<String> sendFriendRequest(@PathVariable Long id, @RequestBody long friendUserId) throws CustomException {
//        try {
//            userService.validId(id);
//            userService.validId(friendUserId);
//            userService.sendFriendRequest(id,friendUserId);
//            return new ResponseEntity<>("friend request sent!!", HttpStatus.OK);
//        }catch (CustomException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }}
//
//    @PutMapping("/changeStatus/{id}")
//    public ResponseEntity<String> approveOrRejectFriendRequest(@PathVariable Long id, @RequestBody FriendRequest friendRequest)throws CustomException {
//        try {
//            userService.validId(id);
//            userService.acceptOrRejectFriendRequest(id,friendRequest);
//            return new ResponseEntity<>("updated your friend list!!", HttpStatus.OK);
//        }catch (CustomException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//
//        }

    }








