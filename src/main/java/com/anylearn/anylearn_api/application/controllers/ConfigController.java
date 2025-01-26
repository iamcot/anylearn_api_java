package com.anylearn.anylearn_api.application.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anylearn.anylearn_api.application.dto.BaseResponseDto;
import com.anylearn.anylearn_api.application.dto.config.ConfigHomeDto;
import com.anylearn.anylearn_api.application.usecases.ConfigUsecase;
import com.anylearn.anylearn_api.domain.user.entity.User;
import com.anylearn.anylearn_api.domain.user.services.UserService;

@RestController
public class ConfigController {

    @Autowired
    private ConfigUsecase configUsecase;

    @Autowired
    private UserService userService;

    @GetMapping("/config/home")
    public ResponseEntity<?> homeConfig(@AuthenticationPrincipal UserDetails userDetails) {
        Optional<User> user = Optional.empty();
        if (userDetails != null && userDetails.getUsername().length() > 0) {
            user = userService.userByPhone(userDetails.getUsername());
        }
        ConfigHomeDto homeConfig = configUsecase.configHome(user);

        BaseResponseDto<ConfigHomeDto> resp = BaseResponseDto.<ConfigHomeDto>builder().data(homeConfig).build();
        return ResponseEntity.ok(resp);
    }
}
