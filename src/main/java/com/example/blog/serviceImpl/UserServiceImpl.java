package com.example.blog.serviceImpl;

import com.example.blog.dto.CustomException;
import com.example.blog.dto.FriendRequest;
import com.example.blog.model.Otp;
import com.example.blog.repo.OtpRepository;
import com.example.blog.repo.UserRepository;
import com.example.blog.services.UserServices;
import com.example.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

@Service
    public class UserServiceImpl implements UserServices {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpRepository otpRepository;
//    @Autowired
//    BCryptPasswordEncoder passwordEncoder;
//    private Map<String, Instant> otpRequestTimes = new HashMap<>();

    @Override
    public User createUser(User user) {
        String email=user.getEmail();
        if(userRepository.existsByEmail(email)) {
            user.setName(user.getName());
            user.setBirthday(user.getBirthday());
            user.setCountOfBlogs(0);
            user.setProfileStatus("complete");
            User savedUser = userRepository.save(user);
            return savedUser;
        }else{
            user.setEmail(user.getEmail());
            user.setName(user.getName());
            user.setBirthday(user.getBirthday());
            user.setCountOfBlogs(0);
            user.setProfileStatus("complete");
            User savedUser = userRepository.save(user);

            Otp addUseId = new Otp();
            addUseId.setUserId(savedUser);
            addUseId.setOtpGenerationAttempts(0);
            otpRepository.save(addUseId);
            return savedUser;
        }





    }


    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(Long id, User updatedUser) throws CustomException {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setEmail(updatedUser.getEmail());
            return userRepository.save(user);
        }
        throw new CustomException("user not found");
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

//    public String generateOtp(String email) throws CustomException {
//        User user = userRepository.findByEmail(email);
//
//        if (user != null && user.getOtpGenerationAttempts() <= 3) {
//            String otp = generateRandomOtp();
//            Instant otpRequestedTime = Instant.now();
//            otpRequestTimes.put(email, otpRequestedTime);
//            String hashedOtp = passwordEncoder.encode(otp);
//            user.setOtp(hashedOtp);
//            user.setOtpGenerationAttempts(user.getOtpGenerationAttempts() + 1);
//            userRepository.save(user);
//            return otp;
//        } else {
//            throw new CustomException("Email is not registered");
//        }
//    }

//    public void validToken(String email) throws CustomException {
//        Instant otpRequestedTime = otpRequestTimes.get(email);
//        if (otpRequestedTime != null) {
//            long timeDifferenceSeconds = Duration.between(otpRequestedTime, Instant.now()).getSeconds();
//            if (timeDifferenceSeconds > 300) {
//                throw new CustomException("Token is expired");
//            }
//        } else {
//            throw new CustomException("No OTP request found for this email");
//        }
//    }

//    private String generateRandomOtp() {
//        Random random = new Random();
//        int randomNumber = random.nextInt(9000) + 1000;
//        return String.valueOf(randomNumber);
//    }

    public void validId(long id) throws CustomException {
        if (!userRepository.existsById(id)) {
            throw new CustomException("Invalid Id was provided");
        }
    }

    public void validemail(String email) throws CustomException {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getProfileStatus()=="complete") {
            throw new CustomException("This emailId already present");
        }
    }

    public void validemailFotToken(String email) throws CustomException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new CustomException("This emailId not present");
        }
    }

    @Override
    public List<User> getUsersWithBirthdayToday() {
        LocalDate today = LocalDate.now();
        System.out.println(today);
        return userRepository.findByBirthday(today);
    }
//    @Override
//    public User sendFriendRequest(long userId, long requestedUserId) {
//        User user = userRepository.findById(requestedUserId).orElse(null);
//
//        if (user != null) {
//            List<Long> list = user.getFriendRequestList();
//            list.add(userId);
//            user.setFriendRequestList(list);
//            return userRepository.save(user);
//        }
//
//        return null;
//    }

//    @Override
//    public User acceptOrRejectFriendRequest(long userId, FriendRequest friendRequest) {
//        User frienduser = userRepository.findById(friendRequest.getUserId()).orElse(null);
//        User user = userRepository.findById(userId).orElse(null);
//
//        if (user != null && frienduser != null) {
//            if ("APPROVED".equals(friendRequest.getStatus())) {
//                List<Long> list = user.getFriendList();
//                list.add(friendRequest.getUserId());
//                user.setFriendList(list);
//
//                List<Long> requestfriendsList = frienduser.getFriendList();
//                requestfriendsList.add(userId);
//                frienduser.setFriendList(requestfriendsList);
//
//                userRepository.saveAll(Arrays.asList(user, frienduser)); // Save both users
//
//            } else {
//                List<Long> list = user.getFriendRequestList();
//                Iterator<Long> iterator = list.iterator();
//                while (iterator.hasNext()) {
//                    Long id = iterator.next();
//                    if (id.equals(friendRequest.getUserId())) {
//                        iterator.remove();
//                        user.setFriendRequestList(list);
//                        userRepository.save(user);
//                        break;
//                    }
//                }
//            }
//        }
//
//        return user;
//    }

}









