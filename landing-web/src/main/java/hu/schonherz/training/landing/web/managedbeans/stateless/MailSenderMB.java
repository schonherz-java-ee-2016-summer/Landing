package hu.schonherz.training.landing.web.managedbeans.stateless;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@ManagedBean(name = "mailSender")
@Stateless
public class MailSenderMB {

    @Resource(mappedName="java:jboss/mail/Default")
    private Session mailSessionSeznam;

    public void sendMail(String mailFrom, String mailTo, String subject, String mailText) throws MessagingException {
        MimeMessage message = new MimeMessage( mailSessionSeznam );
        message.setFrom( new InternetAddress( mailFrom ) );
        message.addRecipient( Message.RecipientType.TO, new InternetAddress( mailTo ) );
        message.setContent(mailText, "text/html; charset=utf-8");
        message.setSubject( subject );
        Transport.send( message );
    }
}