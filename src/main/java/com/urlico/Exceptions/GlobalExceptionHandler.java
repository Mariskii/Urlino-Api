package com.urlico.Exceptions;

import com.urlico.Exceptions.Errors.UrlNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UrlNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> IdNotFound(UrlNotValidException exception)
    {
        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),exception.getMessage());

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

}
