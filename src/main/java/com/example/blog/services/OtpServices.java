package com.example.blog.services;

import com.example.blog.dto.CustomException;
import org.springframework.stereotype.Service;

@Service
public interface OtpServices {
    public String generateOtp(String email) throws CustomException;
    public void validToken(String email,String otpFromUser) throws CustomException;
}
