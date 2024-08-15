package br.com.juhmaran.pet_flow_cloud.exceptions.runtimes;

public class EmailSendingException extends RuntimeException {

    public EmailSendingException(String message) {
        super(message);
    }

    public EmailSendingException() {
        super("Erro ao enviar email para redefinição de senha.");
    }

}
