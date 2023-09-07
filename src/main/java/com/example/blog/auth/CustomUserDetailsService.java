package com.example.blog.auth;

import com.example.blog.model.Otp;
import com.example.blog.model.User;
import com.example.blog.repo.OtpRepository;
import com.example.blog.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    OtpRepository otpRepository;
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        Otp userDetail=otpRepository.findByUserIdId(user.getId());
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                userDetail.getOtp(),
                new ArrayList<>()
        );
    }
}


