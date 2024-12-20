/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java_mail;

import javax.mail.*;
import java.util.*;
import javax.mail.internet.*;

/**
 *
 * @author ADMIN
 */
public class EmailService implements IJavaMail {

    @Override
    public boolean send(String toEmail, String subject, String messageContent) {
        // Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", EmailProperty.HOST_NAME);
        props.put("mail.smtp.socketFactory.port", EmailProperty.SSL_PORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", EmailProperty.SSL_PORT);

        // get Session
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EmailProperty.APP_EMAIL, EmailProperty.APP_PASSWORD);
            }
        });

        // compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(messageContent);

            // send message
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        IJavaMail emailService = new EmailService();
        String toEmail = "@gmail.com";
        String subject = "Test my module"; 
        String messageContent = "Can you read it? I'm very happy if you can see it";
        boolean result = emailService.send(toEmail, subject, messageContent);

        if (result) {
            System.out.println("Email sent successfully!");
        } else {
            System.out.println("Failed to send email.");
        }
    }

}
