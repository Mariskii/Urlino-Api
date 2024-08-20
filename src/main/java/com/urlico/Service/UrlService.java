package com.urlico.Service;

import com.urlico.DTO.Request.CustomUrlDTO;
import com.urlico.DTO.Response.CustomUrlResponseDTO;
import com.urlico.DTO.Response.PageResponseDTO;
import com.urlico.DTO.Response.ShortURLDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UrlService {
    ShortURLDTO shortUrl(String url);
    String redirectToLongUrl(String shortURL);
    CustomUrlResponseDTO buildCustomUrl(CustomUrlDTO customUrlDTO);
    PageResponseDTO<CustomUrlResponseDTO> getUserUrl(Pageable pageable, String userId);
}
