package com.anylearn.anylearn_api.domain.user.services;

import java.util.Optional;

import com.anylearn.anylearn_api.domain.user.entity.User;

public interface UserService {

    Optional<User> userByPhone(String phone);

}
