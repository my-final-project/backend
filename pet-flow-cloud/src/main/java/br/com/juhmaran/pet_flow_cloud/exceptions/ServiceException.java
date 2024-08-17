package br.com.juhmaran.pet_flow_cloud.exceptions;

public class ServiceException extends RuntimeException {

    public ServiceException() {

    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
