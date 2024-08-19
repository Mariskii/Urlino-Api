package com.urlico.Service;

import com.urlico.DTO.Response.UserResponseDTO;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface UserService {
    UserResponseDTO registerUser(OAuth2User user);
    UserResponseDTO loginUser(OAuth2User user);
}
