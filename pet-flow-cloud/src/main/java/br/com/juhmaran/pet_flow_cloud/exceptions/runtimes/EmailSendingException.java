package br.com.juhmaran.pet_flow_cloud.exceptions.runtimes;

public class EmailSendException extends RuntimeException {

    public EmailSendException(String message) {
        super(message);
    }

    public EmailSendException() {
        super("Erro ao enviar email para redefinição de senha.");
    }

}
