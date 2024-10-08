package com.urlico.Repository;

import com.urlico.Models.UrlModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<UrlModel, String> {
    UrlModel findByShortURL(String url);
    boolean existsUrlModelByShortURLKey(String shortURL);
    Page<UrlModel> findAllUrlModelByUserId(String userId, Pageable pageable);
    Page<UrlModel> findAllByShortURLContainingAndUserId(String shortUrl, String userId, Pageable pageable);
}
