package com.urlico.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
@Builder
public class UrlModel {
    @Id
    private String id;
    private String longURL;
    @Indexed(unique = true)
    private String shortURL;
    private String shortURLKey;

    private String userId;
}
