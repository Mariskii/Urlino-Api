package com.urlico.Implementation;

import com.urlico.DTO.Request.CustomUrlDTO;
import com.urlico.DTO.Response.CustomUrlResponseDTO;
import com.urlico.DTO.Response.PageResponseDTO;
import com.urlico.DTO.Response.ShortURLDTO;
import com.urlico.Mapper.PageMapper;
import com.urlico.Mapper.UrlMapper;
import com.urlico.Models.UrlModel;
import com.urlico.Repository.UrlRepository;
import com.urlico.Service.UrlService;
import com.urlico.Utils.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        String shortUrl = UrlUtils.generateRandomShortUrl(longUrl,urlRepository);

        UrlModel url = UrlModel.builder()
                .longURL(longUrl)
                .shortURL(shortUrl)
                .shortURLKey(shortUrl)
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

        String shortUrlKey = UrlUtils.generateRandomShortUrl(customUrlDTO.longUrl(),urlRepository);

        UrlModel urlModel = UrlModel.builder()
                .shortURL(shortUrlKey)
                .shortURLKey(shortUrlKey)
                .longURL(customUrlDTO.longUrl())
                .userId(customUrlDTO.userId())
                .build();

        if(!customUrlDTO.customBody().isBlank()) {
            String shortUrl = customUrlDTO.customBody()+"-"+shortUrlKey;
            urlModel.setShortURL(shortUrl);
        }

        return UrlMapper.buildCustomUrlResponeDTO(urlRepository.save(urlModel));
    }

    @Override
    public PageResponseDTO<CustomUrlResponseDTO> getUserUrl(Pageable pageable, String userId) {
        return PageMapper.createPageResponse(
                urlRepository.findAllUrlModelByUserId(userId,pageable).map(UrlMapper::buildCustomUrlResponeDTO)
        );
    }

    @Override
    public PageResponseDTO<CustomUrlResponseDTO> getUserUrlByShortUrl(Pageable pageable, String shortUrl, String userId) {
        return PageMapper.createPageResponse(
                urlRepository.findAllByShortURLContainingAndUserId(shortUrl, userId,pageable).map(UrlMapper::buildCustomUrlResponeDTO)
        );
    }

    @Override
    public void deleteUrlById(String id) {
        urlRepository.deleteById(id);
    }
}
