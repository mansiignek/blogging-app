package com.example.blog.repo;

import com.example.blog.model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<Otp,Long> {

    Otp findByUserIdId(long id);
}
