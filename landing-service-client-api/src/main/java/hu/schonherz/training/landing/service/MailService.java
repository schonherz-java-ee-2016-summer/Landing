package hu.schonherz.training.landing.service;

import hu.schonherz.training.landing.service.exception.EmailSendingException;

public interface MailService {

    void sendMail(String mailFrom, String mailTo, String subject, String mailText) throws EmailSendingException;

}
