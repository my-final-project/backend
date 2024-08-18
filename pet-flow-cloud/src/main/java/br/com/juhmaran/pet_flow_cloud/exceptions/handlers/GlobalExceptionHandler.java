package br.com.juhmaran.pet_flow_cloud.exceptions.handlers;

import br.com.juhmaran.pet_flow_cloud.exceptions.BadRequestException;
import br.com.juhmaran.pet_flow_cloud.exceptions.ConflictException;
import br.com.juhmaran.pet_flow_cloud.exceptions.ResourceNotFoundException;
import br.com.juhmaran.pet_flow_cloud.exceptions.RoleCreationException;
import br.com.juhmaran.pet_flow_cloud.exceptions.dto.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    public MessageSource messageSource;

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex, Locale locale) {
        log.error("### BadRequestException occurred: {}", ex.getMessage(), ex);
        String errorMessage = messageSource.getMessage("error.bad_request", null, locale);
        var errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), errorMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflictException(ConflictException ex, Locale locale) {
        log.error("### ConflictException occurred: {}", ex.getMessage(), ex);
        String errorMessage = messageSource.getMessage("error.conflict", null, locale);
        var errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), errorMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, Locale locale) {
        log.error("### ResourceNotFoundException occurred: {}", ex.getMessage(), ex);
        String errorMessage = messageSource.getMessage("error.not_found", null, locale);
        var errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), errorMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            @NotNull MethodArgumentNotValidException ex, Locale locale) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = messageSource
                    .getMessage(Objects.requireNonNull(error.getDefaultMessage()), null, locale);
            errors.put(fieldName, errorMessage);
        });
        log.error("### MethodArgumentNotValidException occurred: {}", errors, ex);
        var errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                messageSource.getMessage("error.validation", null, locale), errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(
            @NotNull ConstraintViolationException ex, Locale locale) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach((violation) -> {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = messageSource.getMessage(violation.getMessage(), null, locale);
            errors.put(fieldName, errorMessage);
        });
        log.error("### ConstraintViolationException occurred: {}", errors, ex);
        var errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                messageSource.getMessage("error.validation", null, locale), errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RoleCreationException.class)
    public ResponseEntity<ErrorResponse> handleRoleCreationException(RoleCreationException ex, Locale locale) {
        log.error("### RoleCreationException occurred: {}", ex.getMessage(), ex);
        String errorMessage = messageSource.getMessage("error.role_creation", null, locale);
        var errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, Locale locale) {
        log.error("### GlobalException occurred: {}", ex.getMessage(), ex);
        String errorMessage = messageSource.getMessage("error.internal", null, locale);
        var errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}