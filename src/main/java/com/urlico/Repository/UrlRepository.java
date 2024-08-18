package com.urlico.Repository;

import com.urlico.Models.UrlModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<UrlModel, String> {
    UrlModel findByShortURL(String url);
    boolean existsUrlModelByShortURL(String shortURL);
}
