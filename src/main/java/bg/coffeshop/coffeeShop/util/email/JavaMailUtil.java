package bg.coffeshop.coffeeShop.util.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class JavaMailUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JavaMailUtil.class);

    public static void sendMail(String recipient) throws MessagingException {
        Properties properties = new Properties();

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); //TLS
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        String myAccountEmail = "coffeshopPlovdiv@gmail.com";
        String myPassword = "springSecurity1";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, myPassword);
            }
        });

        Message message = prepareMessage(session, myAccountEmail, recipient);
        if (message == null) {
            message = prepareMessage(session, myAccountEmail, recipient);
        }
        Transport.send(message);
        LOGGER.info("The email was sent successfully!");
    }


    private static Message prepareMessage(Session session, String myAccountEmail, String recipient) {
        try {
            MessageEmailService messageEmail = new MessageEmail();
            messageEmail.readFromFile();

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("New order notification - coffee shop");
            message.setText(messageEmail.getMessage());
            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
