package hu.schonherz.training.landing.service.impl;

import hu.schonherz.training.landing.service.MailService;
import hu.schonherz.training.landing.service.exception.EmailSendingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class MailServiceImpl implements MailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

    @Resource(mappedName = "java:jboss/mail/Default")
    private Session mailSession;

    @Override
    public void sendMail(String mailFrom, String mailTo, String subject, String mailText) throws EmailSendingException {
        try {
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(mailFrom));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
            message.setContent(mailText, "text/html; charset=utf-8");
            message.setSubject(subject);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new EmailSendingException("Messaging error", e);
        }
    }
}
