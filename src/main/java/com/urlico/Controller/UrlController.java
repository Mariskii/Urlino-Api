package com.urlico.Controller;

import com.urlico.DTO.Request.CustomUrlDTO;
import com.urlico.DTO.Response.CustomUrlResponseDTO;
import com.urlico.DTO.Response.PageResponseDTO;
import com.urlico.DTO.Response.ShortURLDTO;
import com.urlico.DTO.Response.UserResponseDTO;
import com.urlico.Exceptions.Errors.UrlNotValidException;
import com.urlico.Implementation.UrlServiceImpl;
import com.urlico.Models.UrlModel;
import com.urlico.Service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("api")
public class UrlController {
    @Autowired
    private UrlServiceImpl urlService;

    @PostMapping("shorten")
    public ResponseEntity<ShortURLDTO> getShortURL(@RequestParam("url") String url) throws UrlNotValidException {
        return ResponseEntity.ok(urlService.shortUrl(url));
    }

    @GetMapping("{shortUrl}")
    public ResponseEntity<Void> redirectUrl(@PathVariable String shortUrl) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(urlService.redirectToLongUrl(shortUrl)))
                .build();
    }

    @PostMapping("custom-url")
    public ResponseEntity<CustomUrlResponseDTO> getCustomUrl(@RequestBody CustomUrlDTO customUrl) throws UrlNotValidException {
        return ResponseEntity.ok(urlService.buildCustomUrl(customUrl));
    }

    @DeleteMapping("delete-by-id")
    public ResponseEntity<?> deleteCustomUrl(@RequestParam String id) {
        urlService.deleteUrlById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("user-urls")
    public ResponseEntity<PageResponseDTO<CustomUrlResponseDTO>> getUserUrls(@AuthenticationPrincipal OAuth2User user, Pageable pageable) {
        return ResponseEntity.ok(urlService.getUserUrl(pageable,user.getName()));
    }

    @GetMapping("user-urls-by-short-url")
    public ResponseEntity<PageResponseDTO<CustomUrlResponseDTO>> getUserUrlsByShortUrl(@AuthenticationPrincipal OAuth2User user, @RequestParam String shortUrl, Pageable pageable) {
        return ResponseEntity.ok(urlService.getUserUrlByShortUrl(pageable, shortUrl, user.getName()));
    }
}
