package com.urlico.DTO.Response;

public record UpdateUrlDTO(
        String id,
        String longUrl,
        String shortURL
) {
}
