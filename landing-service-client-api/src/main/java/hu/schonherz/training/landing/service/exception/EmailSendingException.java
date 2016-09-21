package hu.schonherz.training.landing.service.exception;

public class EmailSendingException extends Exception {

    public EmailSendingException(String message, Throwable cause) {
        super(message, cause);
    }
}
