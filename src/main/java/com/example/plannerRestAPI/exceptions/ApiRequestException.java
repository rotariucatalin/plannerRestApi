package com.example.plannerRestAPI.exceptions;

public class ApiRequestException extends Throwable {

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
