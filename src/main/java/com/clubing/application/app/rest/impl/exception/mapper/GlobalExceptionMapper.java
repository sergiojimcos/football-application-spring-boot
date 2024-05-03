package com.clubing.application.app.rest.impl.exception.mapper;

import com.clubing.application.app.rest.impl.exception.BadRequestException;
import com.clubing.application.app.rest.impl.exception.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergio Jiménez del Coso
 */

@RestControllerAdvice
public class GlobalExceptionMapper {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleValidationException(ConstraintViolationException constraintViolationException) {

        List<ConstraintViolation<?>> constraintViolations = new ArrayList<>(constraintViolationException.getConstraintViolations()) ;

        int lastIndex = constraintViolations.size() - 1;

        return ResponseEntity.badRequest().body(new ExceptionResponse(HttpStatus.BAD_REQUEST,
                constraintViolations.get(lastIndex).getMessage(), LocalDateTime.now()));
    }
}
