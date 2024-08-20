package com.urlico.DTO.Response;

import java.util.List;

public record PageResponseDTO<T>(
        List<T> content,
        long totalElements,
        long totalPages
) {
}
