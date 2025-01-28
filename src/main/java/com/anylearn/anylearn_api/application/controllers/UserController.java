package com.anylearn.anylearn_api.application.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anylearn.anylearn_api.application.dto.BaseResponseDto;
import com.anylearn.anylearn_api.domain.notification.services.EmailService;
import com.anylearn.anylearn_api.domain.user.entity.User;
import com.anylearn.anylearn_api.domain.user.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/profile")
    public ResponseEntity<?> profile(@AuthenticationPrincipal UserDetails userDetails) {
        String phone = userDetails.getUsername();

        Optional<User> user = userService.userByPhone(phone);

        if (!user.isPresent()) {// in case loggined user get blocked
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(BaseResponseDto.builder()
                            .status(HttpStatus.NOT_FOUND)
                            .message("User not found").build());
        }

        return ResponseEntity.ok(BaseResponseDto.<User>builder().data(user.get()).build());
    }

    @GetMapping("/notification")
    public ResponseEntity<?> notification(@AuthenticationPrincipal UserDetails userDetails) {
        String phone = userDetails.getUsername();
        userService.userByPhone(phone).ifPresent(user -> {
            if (user.getEmail() != null) {
                emailService.send(user.getEmail(), "Notification", "Test");
            }
        });
        
        return ResponseEntity.ok(BaseResponseDto.builder().message("Notification sent").build());
    }

}
