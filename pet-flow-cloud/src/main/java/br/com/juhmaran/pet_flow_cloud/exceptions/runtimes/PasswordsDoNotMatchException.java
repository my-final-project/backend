package br.com.juhmaran.pet_flow_cloud.exceptions.runtimes;

public class PasswordsDoNotMatchException extends RuntimeException {

    public PasswordsDoNotMatchException() {

    }

    public PasswordsDoNotMatchException(String message) {
        super(message);
    }

    public PasswordsDoNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

}
