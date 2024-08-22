package com.urlico.Mapper;

import com.urlico.DTO.Response.CustomUrlResponseDTO;
import com.urlico.Models.UrlModel;

public class UrlMapper {
    public static CustomUrlResponseDTO buildCustomUrlResponeDTO(UrlModel urlModel) {
        return new CustomUrlResponseDTO(urlModel.getId(),urlModel.getLongURL(), urlModel.getCustomBody(),urlModel.getShortURL());
    }
}
