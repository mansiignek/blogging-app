package com.example.blog.model;

import javax.persistence.*;
@Entity
@Table(name = "Otp")
public class Otp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Otp_id")
    private long id;
    @Column(name="otp")
    private String otp;


    @Column(name="otpGenerationAttempts")
    private int otpGenerationAttempts;

    @ManyToOne
    @JoinColumn(name="User_id")
    private User userId;

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getOtpGenerationAttempts() {
        return otpGenerationAttempts;
    }

    public void setOtpGenerationAttempts(int otpGenerationAttempts) {
        this.otpGenerationAttempts = otpGenerationAttempts;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
