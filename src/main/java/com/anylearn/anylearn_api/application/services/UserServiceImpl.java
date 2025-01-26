package com.anylearn.anylearn_api.application.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anylearn.anylearn_api.domain.user.entity.User;
import com.anylearn.anylearn_api.domain.user.repositories.UserRepository;
import com.anylearn.anylearn_api.domain.user.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> userByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }
}
