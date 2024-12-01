package com.book.hive.hub.application.configuration.api;

import com.book.hive.hub.application.exceptions.NotFoundException;
import com.book.hive.hub.presentation.dto.response.common.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // InternalServerError (500)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponseDto> handleGeneralException(Exception ex) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                "Internal server error",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // BadRequestException (400)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDto> handleBadRequest(IllegalArgumentException ex) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                "Invalid request",
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Unauthorized (401)
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponseDto> handleUnauthorizedException(AuthenticationException ex) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                "Unauthorized",
                HttpStatus.UNAUTHORIZED.value(),
                ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    // Forbidden (403)
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorResponseDto> handleForbiddenException(AccessDeniedException ex) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                "Forbidden",
                HttpStatus.FORBIDDEN.value(),
                ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    // NotFound (404)
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseDto> notFoundException(NotFoundException ex) {
        ErrorResponseDto errorResponse = new ErrorResponseDto(
                "Resource not found",
                HttpStatus.NOT_FOUND.value(),
                "The requested resource could not be found.");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
