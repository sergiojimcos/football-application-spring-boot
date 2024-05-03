package com.clubing.application.app.rest.impl.exception.handler;

import com.clubing.application.app.rest.impl.exception.response.ExceptionResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergio Jiménez del Coso
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleValidationException(ConstraintViolationException constraintViolationException) {

        List<ConstraintViolation<?>> constraintViolations = new ArrayList<>(constraintViolationException.getConstraintViolations());

        int lastIndex = constraintViolations.size() - 1;

        return ResponseEntity.badRequest().body(new ExceptionResponse(HttpStatus.BAD_REQUEST,
                constraintViolations.get(lastIndex).getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityException(DataIntegrityViolationException dataIntegrityViolationException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(HttpStatus.BAD_REQUEST,
                dataIntegrityViolationException.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handlePermissionException(AccessDeniedException accessDeniedException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionResponse(HttpStatus.UNAUTHORIZED,
                accessDeniedException.getMessage(), LocalDateTime.now()));
    }
}
