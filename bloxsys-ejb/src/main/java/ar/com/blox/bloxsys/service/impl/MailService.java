package ar.com.blox.bloxsys.service.impl;


import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class MailService {

    private static final Logger LOG = Logger.getLogger(MailService.class.getName());
    private static final long serialVersionUID = 3L;

    /**
     * Realiza el envío de e-mail.
     *
     * @param e_mail_to
     * @param subject
     * @param text
     */
    public void sendMail(String e_mail_to, String subject, String text) {
        try {
            InitialContext ctx = new InitialContext();
            Session session = (Session) ctx.lookup("java:/jboss/mail/gmail");
            Message msg = new MimeMessage(session);
            msg.setSubject(subject);

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(e_mail_to));
            msg.setReplyTo(InternetAddress.parse("bloxsys.utn@gmail.com"));
            msg.setContent(text, "text/html; charset=utf-8");
            Transport.send(msg);
        } catch (NamingException | MessagingException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
}
