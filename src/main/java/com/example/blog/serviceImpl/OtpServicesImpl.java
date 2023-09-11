package com.example.blog.serviceImpl;

import com.example.blog.dto.CustomException;
import com.example.blog.model.Otp;
import com.example.blog.model.User;
import com.example.blog.repo.OtpRepository;
import com.example.blog.repo.UserRepository;
import com.example.blog.services.OtpServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
@Service
public class OtpServicesImpl implements OtpServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpRepository otpRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    private Map<String, Instant> otpRequestTimes = new HashMap<>();
@Override
    public String generateOtp(String email) throws CustomException {
        User user = userRepository.findByEmail(email);

            if(user ==  null) {
                String otp2 = generateRandomOtp();
                Instant otpRequestedTime = Instant.now();
                otpRequestTimes.put(email, otpRequestedTime);
                String hashedOtp = passwordEncoder.encode(otp2);
                User user1=new User();
                user1.setCountOfBlogs(0);
                user1.setBirthday(null);
                user1.setEmail(email);
                user1.setName(null);
                user1.setProfileStatus("pending");
                userRepository.save(user1);
                User user2 = userRepository.findByEmail(email);
                Otp otp1=new Otp();
                otp1.setOtpGenerationAttempts(0);
                otp1.setUserId(null);
                otp1.setOtp(hashedOtp);
                otp1.setUserId(user2);
                otpRepository.save(otp1);
                return otp2;
            }else {
                Otp userDetail = otpRepository.findByUserIdId(user.getId());
                if (userDetail.getOtpGenerationAttempts() <= 3) {
                    String otp = generateRandomOtp();
                    Instant otpRequestedTime = Instant.now();
                    otpRequestTimes.put(email, otpRequestedTime);
                    String hashedOtp = passwordEncoder.encode(otp);
                    userDetail.setOtp(hashedOtp);
                    userDetail.setOtpGenerationAttempts(userDetail.getOtpGenerationAttempts() + 1);
                    otpRepository.save(userDetail);
                    return otp;
                }
                else{
                    throw new CustomException("you attempt are over now!");
                }
            }

        }


    private String generateRandomOtp() {
        Random random = new Random();
        int randomNumber = random.nextInt(9000) + 1000;
        return String.valueOf(randomNumber);
    }
@Override
    public void validToken(String email,String otpFromUser) throws CustomException {
        Instant otpRequestedTime = otpRequestTimes.get(email);
//    User user = userRepository.findByEmail(email);
//
//    if (user != null) {
//        long id = user.getId();
//        Otp otp = otpRepository.findByUserIdId(id);
//
//        if (otp != null) {
//            String otpFromDb = otp.getOtp();
//            String hashedOtpFromUser = passwordEncoder.encode(otpFromUser);
//            if (!hashedOtpFromUser.equals(otpFromDb)) {
//                throw new CustomException("Wrong OTP");
//            }
//        } else {
//            throw new CustomException("No OTP found for the user");
//        }
//    } else {
//        throw new CustomException("User not found");
//    }

        if (otpRequestedTime != null) {
            long timeDifferenceSeconds = Duration.between(otpRequestedTime, Instant.now()).getSeconds();
            if (timeDifferenceSeconds > 300) {
                throw new CustomException("Token is expired");
            }
        } else {
            throw new CustomException("Wrong Otp");
        }
    }

}
