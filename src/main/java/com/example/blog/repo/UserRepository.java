package com.example.blog.repo;


import com.example.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    List<User> findByBirthday(LocalDate birthday);

    boolean existsByEmail(String email);
}

