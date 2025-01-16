package com.anylearn.anylearn_api.service;

import java.util.Optional;

import com.anylearn.anylearn_api.entity.User;

public interface UserService {
    Optional<User> userByPhone(String phone);
}
