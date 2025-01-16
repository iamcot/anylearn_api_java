package com.anylearn.anylearn_api.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anylearn.anylearn_api.entity.User;
import com.anylearn.anylearn_api.repository.UserRepository;


@Controller
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
	public String health(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping("/profile/{phone}")
	public @ResponseBody Optional<User> profile(@PathVariable String phone) {
        Optional<User> result = userRepository.findByPhone(phone);
        return result;
	}

    @GetMapping("/search")
    public @ResponseBody Page<User> search(@RequestParam String role, @RequestParam int page) {
        Pageable pageable = Pageable.ofSize(20).withPage(page);
        return userRepository.findByRole(role, pageable);
    }

    @GetMapping("/test")
    public @ResponseBody String test() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return now.format(timeFormat);
    }
}
