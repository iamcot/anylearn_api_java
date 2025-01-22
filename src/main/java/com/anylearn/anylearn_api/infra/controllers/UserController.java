package com.anylearn.anylearn_api.infra.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public String profile() {
       return "profile";
	}

}
