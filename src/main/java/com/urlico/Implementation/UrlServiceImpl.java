package com.urlico.Implementation;

import com.urlico.DTO.Request.CustomUrlDTO;
import com.urlico.DTO.Response.CustomUrlResponseDTO;
import com.urlico.DTO.Response.ShortURLDTO;
import com.urlico.Mapper.UrlMapper;
import com.urlico.Models.UrlModel;
import com.urlico.Repository.UrlRepository;
import com.urlico.Service.UrlService;
import com.urlico.Utils.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    UrlRepository urlRepository;

    @Override
    public ShortURLDTO shortUrl(String longUrl) {
        UrlModel url = UrlModel.builder()
                .longURL(longUrl)
                .shortURL(UrlUtils.generateRandomShortUrl(longUrl,urlRepository))
                .build();

        UrlModel urlModel = urlRepository.save(url);
        return new ShortURLDTO(urlModel.getShortURL());
    }

    @Override
    public String redirectToLongUrl(String shortURL) {
        UrlModel url = urlRepository.findByShortURL(shortURL);
        return url.getLongURL();
    }

    @Override
    public CustomUrlResponseDTO buildCustomUrl(CustomUrlDTO customUrlDTO) {

        String shortUrl = UrlUtils.generateRandomShortUrl(customUrlDTO.longUrl(),urlRepository);

        shortUrl = customUrlDTO.customBody()+"/"+shortUrl;

        UrlModel urlModel = UrlModel.builder()
                .shortURL(shortUrl)
                .longURL(customUrlDTO.longUrl())
                .userId(customUrlDTO.userId())
                .build();

        return UrlMapper.buildCustomUrlResponeDTO(urlRepository.save(urlModel));
    }
}
