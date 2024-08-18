package br.com.juhmaran.pet_flow_cloud.exceptions.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Setter
public class ErrorResponse {

    private int status;
    private String error;
    private String message;
    private Map<String, String> errors;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.error = HttpStatus.valueOf(status).getReasonPhrase();
        this.message = message;
    }

    public ErrorResponse(int status, String message, Map<String, String> errors) {
        this.status = status;
        this.error = HttpStatus.valueOf(status).getReasonPhrase();
        this.message = message;
        this.errors = errors;
    }

}
