package br.com.juhmaran.pet_flow_cloud.exceptions.handler;

import br.com.juhmaran.pet_flow_cloud.exceptions.dto.ErrorResponse;
import br.com.juhmaran.pet_flow_cloud.exceptions.runtimes.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        log.error("Erro de validação: {}", errors);

        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Erro de validação.")
                .errors(errors)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleGeneralExceptions(Exception ex) {
        List<String> errors = List.of(ex.getMessage());

        log.error("Erro interno do servidor: {}", ex.getMessage());

        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message("Erro interno do servidor.")
                .errors(errors)
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler({
            DataIntegrityViolationException.class,
            BadRequestException.class
    })
    public ResponseEntity<ErrorResponse> handleCustomExceptions(RuntimeException ex) {
        log.error("Erro de requisição: {}", ex.getMessage());

        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .errors(List.of(ex.getMessage()))
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            UnauthorizedException.class
    })
    public final ResponseEntity<ErrorResponse> handleAuthenticatedException(RuntimeException ex) {
        log.error("Erro de autenticação: {}", ex.getMessage());

        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .message(ex.getMessage())
                .errors(List.of(ex.getMessage()))
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({
            ForbiddenException.class
    })
    public final ResponseEntity<ErrorResponse> handlePermissionDeniedException(RuntimeException ex) {
        log.error("Erro de permissão: {}", ex.getMessage());

        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .error(HttpStatus.FORBIDDEN.getReasonPhrase())
                .message(ex.getMessage())
                .errors(List.of(ex.getMessage()))
                .build();

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({
            ResourceNotFoundException.class
    })
    public final ResponseEntity<ErrorResponse> handleNotFoundException(RuntimeException ex) {
        log.error("Recurso não encontrado: {}", ex.getMessage());

        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .errors(List.of(ex.getMessage()))
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            ConflictException.class
    })
    public final ResponseEntity<ErrorResponse> handleAlreadyExistsException(RuntimeException ex) {
        log.error("Conflito de dados: {}", ex.getMessage());

        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.CONFLICT.value())
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .message(ex.getMessage())
                .errors(List.of(ex.getMessage()))
                .build();

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({
            ServiceException.class,
            InternalServerException.class
    })
    public final ResponseEntity<ErrorResponse> handleServiceException(RuntimeException ex) {
        log.error("Erro de serviço: {}", ex.getMessage());

        ErrorResponse response = ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(ex.getMessage())
                .errors(List.of(ex.getMessage()))
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
