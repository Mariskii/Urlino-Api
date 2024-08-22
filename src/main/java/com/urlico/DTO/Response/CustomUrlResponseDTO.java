package com.urlico.DTO.Response;

public record CustomUrlResponseDTO(
        String id,
        String longUrl,
        String customBody,
        String customUrl
) {
}
