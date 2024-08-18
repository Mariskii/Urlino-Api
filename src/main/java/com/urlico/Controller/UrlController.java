package com.urlico.Controller;

import com.urlico.DTO.Response.ShortURLDTO;
import com.urlico.Implementation.UrlServiceImpl;
import com.urlico.Models.UrlModel;
import com.urlico.Service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class UrlController {
    @Autowired
    private UrlServiceImpl urlService;

    @PostMapping("shorten")
    public ResponseEntity<ShortURLDTO> getShortURL(@RequestParam("url") String url) {
        return ResponseEntity.ok(urlService.shortUrl(url));
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirectUrl(@PathVariable String shortUrl) {
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(urlService.redirectToLongUrl(shortUrl)))
                .build();
    }
}
