package com.anylearn.anylearn_api.infra.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anylearn.anylearn_api.domain.user.entity.User;
import com.anylearn.anylearn_api.domain.user.services.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

	@GetMapping("/profile")
	public ResponseEntity<?> profile(@AuthenticationPrincipal UserDetails userDetails) {
        String phone = userDetails.getUsername();

        Optional<User> user = userService.userByPhone(phone);

        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user.get());
    }

}
