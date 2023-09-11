package com.example.blog.controller;

import com.example.blog.auth.CustomUserDetailsService;
import com.example.blog.auth.JwtUtil;
import com.example.blog.dto.CustomException;
import com.example.blog.dto.Request;
import com.example.blog.dto.Response;
import com.example.blog.model.User;
import com.example.blog.repo.UserRepository;
import com.example.blog.services.OtpServices;
import com.example.blog.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
public class AuthController {
    @Autowired
    private CustomUserDetailsService userDetailsService;


    @Autowired
    private UserServices userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private OtpServices otpServices;
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody Request request) {
        try {
            // Validate the OTP token
            otpServices.validToken(request.getEmail(), request.getOtp());

            // Authenticate the user
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getOtp()));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage()+": Wrong Otp");
        }

        // If authentication is successful, generate a JWT token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);

        // Return the JWT token in the response
        User user = userRepository.findByEmail(request.getEmail());
        long userId=user.getId();
        return ResponseEntity.ok(new Response(token,userId));
    }



        @PostMapping("/generateOtp")
        public ResponseEntity<String> generateOtp(@RequestBody Request request) throws CustomException {
            try {

                String otp = otpServices.generateOtp(request.getEmail());
                System.out.println(otp);
                return new ResponseEntity<>(otp, HttpStatus.OK);

            }catch (CustomException e){
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
}

