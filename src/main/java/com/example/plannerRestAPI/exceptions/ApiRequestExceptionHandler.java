package com.example.plannerRestAPI.exceptions;

import com.example.plannerRestAPI.models.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiRequestExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<?> handleApiRequestException(ApiRequestException apiRequestException) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiException exception = new ApiException(apiRequestException.getMessage(), badRequest);

        return new ResponseEntity<>(exception, badRequest);

    }

}
