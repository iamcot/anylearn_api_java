package com.anylearn.anylearn_api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anylearn.anylearn_api.entity.User;
import com.anylearn.anylearn_api.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> userByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }
}
