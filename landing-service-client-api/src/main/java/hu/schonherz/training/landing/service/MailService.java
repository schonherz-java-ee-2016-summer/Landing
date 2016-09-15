package hu.schonherz.training.landing.service;

public interface MailService {

    void sendMail(String mailFrom, String mailTo, String subject, String mailText);

}
