package com.account.system.controller;

import com.account.system.dto.response.ErrorResponse;
import com.account.system.exceptions.DuplicateEmailException;
import com.account.system.exceptions.InvalidCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandlerForUsers {

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentials(InvalidCredentialsException icx) {
        ErrorResponse err = new ErrorResponse(icx.getMessage(), Instant.now());

        return new ResponseEntity<>(err, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateEmail(DuplicateEmailException dex) {
        ErrorResponse err = new ErrorResponse(dex.getMessage(), Instant.now());
        return new ResponseEntity<>(err, HttpStatus.CONFLICT);
    }

}
