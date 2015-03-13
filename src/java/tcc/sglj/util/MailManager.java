/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.sglj.util;

/**
 *
 * @author jhonatan
 */

import tcc.sglj.util.mail.EmailMessage;
import java.io.IOException; 
import java.io.PrintStream; 
import java.util.*; 
import javax.mail.*; 
import javax.mail.internet.*; 
import javax.activation.*; 
import sun.net.smtp.SmtpClient; 


public class MailManager {

    private final String usuario = "jhonatan.luiz.santos@gmail.com";
    private final String senha = "$Sled!z*10#";
    private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final String SMTP_PORT = "465";
    private static final String emailMsgTxt = "Teste";
    private static final String emailSubjectTxt = "Testando o email! do SGLJ não responder";
    private static final String emailFromAddress = "jhonatan.luiz.santos@gmail.com";
    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    public static void main(String args[]) {
        tcc.sglj.util.MailManager m = new tcc.sglj.util.MailManager();
        try {
            m.send("jhonatan.luiz.santos@gmail.com,leandrolima2985@gmail.com", "", "", emailFromAddress, "Esse e um email de teste", "SGLJ HEHEHEHE TESTE EMAIL!");
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public MailManager() {
    }

    public void send(EmailMessage msg) throws Exception {
        try {
            Properties mailProps = new Properties();
            mailProps.put("mail.smtp.host", SMTP_HOST_NAME);

            SimpleAuth auth = null;
            auth = new SimpleAuth(usuario, senha);

            mailProps.put("mail.smtp.auth", "true");
            mailProps.put("mail.user", usuario);
            mailProps.put("mail.from", msg.getFrom());
            mailProps.put("mail.to", msg.getTo());

            mailProps.put("mail.smtp.host", SMTP_HOST_NAME);
            mailProps.put("mail.smtp.auth", "true");
            mailProps.put("mail.debug", "true");

            mailProps.put("mail.smtp.port", SMTP_PORT);
            mailProps.put("mail.smtp.socketFactory.port", SMTP_PORT);
            mailProps.put("mail.smtp.socketFactory.class", SSL_FACTORY);
            mailProps.put("mail.smtp.socketFactory.fallback", "false");


            Session mailSession = Session.getDefaultInstance(mailProps, auth);

            mailSession.setDebug(true);

            Message email = new MimeMessage(mailSession);
            email.setRecipients(Message.RecipientType.TO, InternetAddress.parse(msg.getTo()));
            
            if (!msg.getCc().trim().equals("")) {
                email.setRecipients(Message.RecipientType.CC, InternetAddress.parse(msg.getCc()));
            }
            if (!msg.getBcc().trim().equals("")) {
                email.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(msg.getBcc()));
            }
            
            email.setFrom(new InternetAddress(msg.getFrom()));
            email.setSubject(msg.getSubject());
            email.setContent(msg.getText(), "text/plain");

            Transport tr = mailSession.getTransport("smtp");
            tr.connect(SMTP_HOST_NAME, usuario, senha);
            email.saveChanges(); // don't forget this 
            tr.sendMessage(email, email.getAllRecipients());
            tr.close();

        } catch (Exception e) {
            System.out.println(" [E] ERROR: " + e);
            e.printStackTrace(System.out);
            throw e;
        }
        System.out.println(" [#] Email successfully sent");
        return;
    }

    public void send(String to, String cc, String bcc, String from, String subject, String text) throws Exception {
//        EmailMessage msg = new EmailMessage(to, cc, bcc, from, subject, text);
//        this.send(msg);
//        return;
    }
}
