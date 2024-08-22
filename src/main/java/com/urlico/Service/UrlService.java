package com.urlico.Service;

import com.urlico.DTO.Request.CustomUrlDTO;
import com.urlico.DTO.Response.CustomUrlResponseDTO;
import com.urlico.DTO.Response.PageResponseDTO;
import com.urlico.DTO.Response.ShortURLDTO;
import com.urlico.DTO.Response.UpdateUrlDTO;
import com.urlico.Exceptions.Errors.UrlIdDoesntExistException;
import com.urlico.Exceptions.Errors.UrlNotValidException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UrlService {
    ShortURLDTO shortUrl(String url) throws UrlNotValidException;
    String redirectToLongUrl(String shortURL);
    CustomUrlResponseDTO buildCustomUrl(CustomUrlDTO customUrlDTO) throws UrlNotValidException;
    CustomUrlResponseDTO updateCustomUrl(UpdateUrlDTO updateUrlDTO) throws UrlNotValidException, UrlIdDoesntExistException;
    PageResponseDTO<CustomUrlResponseDTO> getUserUrl(Pageable pageable, String userId);
    PageResponseDTO<CustomUrlResponseDTO> getUserUrlByShortUrl(Pageable pageable, String shortUrl, String userId);
    void deleteUrlById(String id);
}
