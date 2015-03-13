/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.sglj.util.mail;

import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Enumeration;
import java.io.IOException;
import java.io.PrintStream;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import sun.net.smtp.SmtpClient;
import tcc.sglj.util.SimpleAuth;
import tcc.sglj.util.SystemMessage;

/**
 *
 * @author jhonatan
 */
public class Email {

    private Properties properties;

    public Email(ResourceBundle resource) {
        this.loadProperties(resource);
    }

    public void send(EmailMessage message) throws Exception {

        try {

            String user = getProperties().getProperty("mail.auth.user");
            String password = getProperties().getProperty("mail.auth.password");

            SimpleAuth auth = new SimpleAuth(user, password);
            getProperties().put("mail.user", auth);

            Session mailSession = Session.getDefaultInstance(getProperties(), auth);
            mailSession.setDebug(true);

            Message email = new MimeMessage(mailSession);
            email.setRecipients(Message.RecipientType.TO, InternetAddress.parse(message.getTo()));

            if (!message.getCc().trim().equals("")) {
                email.setRecipients(Message.RecipientType.CC, InternetAddress.parse(message.getCc()));
            }
            if (!message.getBcc().trim().equals("")) {
                email.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(message.getBcc()));
            }

            email.setFrom(new InternetAddress(message.getFrom()));
            email.setSubject(message.getSubject());
            email.setContent(message.getText(), "text/plain");

            Transport tr = mailSession.getTransport("smtp");
            tr.connect(getProperties().getProperty("mail.smtp.host"), user, password);
            email.saveChanges();
            tr.sendMessage(email, email.getAllRecipients());
            tr.close();
        } catch (Exception e) {
            throw new EmailException(SystemMessage.get("MSG009") + " " + e.getMessage());
        }

    }

    /**
     * @return the properties
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * @param properties the properties to set
     */
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    private void loadProperties(ResourceBundle resource) {
        Properties prop = new Properties();

        Enumeration<String> keys = resource.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            prop.put(key, resource.getString(key));
        }

        this.properties = prop;
    }
}
