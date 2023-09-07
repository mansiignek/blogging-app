package com.example.blog.serviceImpl;

import com.example.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class BirthdayNotificationScheduler {
    @Autowired
    private UserServiceImpl userService;

    @Scheduled(cron = "0 39 16 * * *")
    public void sendBirthdayNotifications() {
        List<User> usersWithBirthday = userService.getUsersWithBirthdayToday();

        for (User user : usersWithBirthday) {
            sendNotification(user);
        }
    }

    private void sendNotification(User user) {
        System.out.println("Sending birthday notification to: " + user.getName());
    }
}
