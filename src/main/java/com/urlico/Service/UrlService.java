package com.urlico.Service;

import com.urlico.DTO.Request.CustomUrlDTO;
import com.urlico.DTO.Response.CustomUrlResponseDTO;
import com.urlico.DTO.Response.ShortURLDTO;

public interface UrlService {
    ShortURLDTO shortUrl(String url);
    String redirectToLongUrl(String shortURL);
    CustomUrlResponseDTO buildCustomUrl(CustomUrlDTO customUrlDTO);
}
