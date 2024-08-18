package com.urlico.Service;

import com.urlico.DTO.Response.ShortURLDTO;

public interface UrlService {
    ShortURLDTO shortUrl(String url);
    String redirectToLongUrl(String shortURL);
}
