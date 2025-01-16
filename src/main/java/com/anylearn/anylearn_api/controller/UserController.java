package com.anylearn.anylearn_api.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.anylearn.anylearn_api.entity.User;
import com.anylearn.anylearn_api.service.UserService;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/")
	public String health(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping("/profile/{phone}")
	public @ResponseBody Optional<User> profile(@PathVariable String phone) {
        Optional<User> result = userService.userByPhone(phone);
        logger.debug(result.toString());
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND) ;
        }
        return result;
	}

    @GetMapping("/search")
    public @ResponseBody Page<User> search(@RequestParam String role, @RequestParam int page) {
        // Pageable pageable = Pageable.ofSize(20).withPage(page);
        // return userRepository.findByRole(role, pageable);
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/test")
    public @ResponseBody String test() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return now.format(timeFormat);
    }
}
