package com.clubing.application.app.rest.impl.exception.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author Sergio Jiménez del Coso
 */

public class ExceptionResponse {

    private HttpStatus _httpStatus;

    private String _message;

    private LocalDateTime _timestamp;

    public ExceptionResponse(HttpStatus _httpStatus, String _message, LocalDateTime _timestamp) {
        this._httpStatus = _httpStatus;
        this._message = _message;
        this._timestamp = _timestamp;
    }

    public HttpStatus getStatus() {
        return _httpStatus;
    }

    public LocalDateTime getLocalDateTime() {
        return _timestamp;
    }

    public String getMessage() {
        return _message;
    }

    public void setStatus(HttpStatus httpStatus) {
        this._httpStatus = httpStatus;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this._timestamp = localDateTime;
    }

    public void setMessage(String message) {
        this._message = message;
    }
}
