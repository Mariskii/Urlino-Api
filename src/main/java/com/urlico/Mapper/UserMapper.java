package com.urlico.Mapper;

import com.urlico.DTO.Response.UserResponseDTO;
import com.urlico.Models.User;

public class UserMapper {
    public static UserResponseDTO buildUserResponseDTO(User user) {
        return  UserResponseDTO.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .avatarURL(user.getAvatarURL())
                .build();
    }
}
