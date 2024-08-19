package com.urlico.DTO.Response;

import lombok.Builder;

@Builder
public record UserResponseDTO(
        String id,
        String userName,
        String avatarURL
) {
}
