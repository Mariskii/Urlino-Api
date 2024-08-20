package com.urlico.Mapper;

import com.urlico.DTO.Response.PageResponseDTO;
import org.springframework.data.domain.Page;

public class PageMapper {
    public static <T> PageResponseDTO<T> createPageResponse(Page<T> page) {
        return new PageResponseDTO<>(page.getContent(), page.getTotalElements(), page.getTotalPages());
    }
}
