package br.com.juhmaran.pet_flow_cloud.exceptions.handlers;

import br.com.juhmaran.pet_flow_cloud.exceptions.dto.ExceptionResponse;
import br.com.juhmaran.pet_flow_cloud.exceptions.runtimes.EmailSendingException;
import br.com.juhmaran.pet_flow_cloud.exceptions.runtimes.ResourceNotFoundException;
import br.com.juhmaran.pet_flow_cloud.exceptions.runtimes.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
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
    public ResponseEntity<ExceptionResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        log.error("### handleValidationErrors - Erro de validação: {}", errors);

        var response = ExceptionResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Erro de validação.")
                .errors(errors)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleGeneralExceptions(Exception ex) {
        List<String> errors = List.of(ex.getMessage());

        log.error("### handleGeneralExceptions - Erro interno do servidor: {}", ex.getMessage());

        var response = ExceptionResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message("Erro interno do servidor.")
                .errors(errors)
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler({
            ResourceNotFoundException.class
    })
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(ResourceNotFoundException ex) {
        log.error("### handleNotFoundException - Recurso não encontrado: {}", ex.getMessage());

        ExceptionResponse response = ExceptionResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .errors(List.of(ex.getMessage()))
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            ServiceException.class,
            EmailSendingException.class
    })
    public final ResponseEntity<ExceptionResponse> handleServiceException(RuntimeException ex) {
        log.error("Erro de serviço: {}", ex.getMessage());

        ExceptionResponse response = ExceptionResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(ex.getMessage())
                .errors(List.of(ex.getMessage()))
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
