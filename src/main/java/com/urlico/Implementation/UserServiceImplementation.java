package com.urlico.Implementation;

import com.urlico.DTO.Response.UserResponseDTO;
import com.urlico.Mapper.UserMapper;
import com.urlico.Models.User;
import com.urlico.Repository.UserRepository;
import com.urlico.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserResponseDTO registerUser(OAuth2User user) {
        User newUser = User.builder()
                .userName(user.getAttribute("login"))
                .avatarURL(user.getAttribute("avatar_url"))
                //.providedId(user.getAttribute())
                .build();

        return UserMapper.buildUserResponseDTO(userRepository.save(newUser));
    }

    @Override
    public UserResponseDTO loginUser(OAuth2User user) {
        return null;
    }
}
