package com.urlico.Controller;

import com.urlico.DTO.Response.UserResponseDTO;
import com.urlico.Implementation.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("auth")
public class UserController {

    @Autowired
    UserServiceImplementation userService;

    @GetMapping("user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User user) {
        return user.getAttributes();
    }

    @PostMapping("register")
    public UserResponseDTO register(@AuthenticationPrincipal OAuth2User user) {
        System.out.println("REGISTRO "+user.getName());
        return userService.registerUser(user);
    }
}
