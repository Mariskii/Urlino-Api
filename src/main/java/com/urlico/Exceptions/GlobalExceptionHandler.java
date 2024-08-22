package com.urlico.Exceptions;

import com.urlico.Exceptions.Errors.UrlIdDoesntExistException;
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
    public ResponseEntity<ErrorMessage> invalidUrl(UrlNotValidException exception)
    {
        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),exception.getMessage());

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(UrlIdDoesntExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> idNotFound(UrlIdDoesntExistException exception)
    {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(),exception.getMessage());

        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

}
