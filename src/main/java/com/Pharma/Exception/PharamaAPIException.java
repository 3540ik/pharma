package com.Pharma.Exception;


import org.springframework.http.HttpStatus;

public class PharamaAPIException extends RuntimeException {
    private HttpStatus status;
    private  String message;

    public PharamaAPIException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
