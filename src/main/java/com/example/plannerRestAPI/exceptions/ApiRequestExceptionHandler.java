package com.example.plannerRestAPI.exceptions;

import com.example.plannerRestAPI.models.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ApiRequestExceptionHandler{

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<?> handleApiRequestException(ApiRequestException apiRequestException) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiException exception = new ApiException(apiRequestException.getMessage(), badRequest);

        return new ResponseEntity<>(exception, badRequest);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleMissMatchException(MethodArgumentTypeMismatchException ex) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        String name = ex.getName();
        String type = ex.getRequiredType().getSimpleName();
        Object value = ex.getValue();
        String message = String.format("'%s' should be a valid '%s' and '%s' isn't", name, type, value);

        ApiException exception = new ApiException(message, badRequest);

        return new ResponseEntity<>(exception, badRequest);
    }
}
