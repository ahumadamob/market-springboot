package com.ahumadamob.market.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ahumadamob.market.dto.ResponseDTO;
import com.ahumadamob.market.util.BuildResponse;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseDTO<Object>> handleConstraintViolationException(ConstraintViolationException ex) {
        return BuildResponse.handleConstraintException(ex);
    }
}

