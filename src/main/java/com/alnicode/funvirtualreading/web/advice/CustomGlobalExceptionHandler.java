package com.alnicode.funvirtualreading.web.advice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.alnicode.funvirtualreading.util.AppConstants.DATE_TIME_FORMAT;

@RestControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> body = new LinkedHashMap<>();

        body.put("timestamp", this.timestamp());

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> body.put(error.getField(), error.getDefaultMessage()));

        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        Map<Object, Object> body = new LinkedHashMap<>();

        body.put("timestamp", this.timestamp());

        ex.getConstraintViolations().forEach(error -> {
            body.put(error.getPropertyPath(), error.getMessage());
            body.put("request", error.getInvalidValue());
        });

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    private String timestamp() {
        return DateTimeFormatter.ofPattern(DATE_TIME_FORMAT).format(LocalDateTime.now());
    }

}
