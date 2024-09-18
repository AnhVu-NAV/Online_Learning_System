/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dal.AccountDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author ADMIN
 */
public class ForgotPasswordController extends HttpServlet{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String email = request.getParameter("email"); //email người nhận
            AccountDAO adao = new AccountDAO();
//            String fullName = adao.checkEmail(email);

            if (fullName.equals("Nothing")) {
                request.setAttribute("message", "No exit account with this email. Please check again!");
                request.getRequestDispatcher("forgetPass.jsp").forward(request, response);
            }
//            String newPass = adao.updatePass(email);

            //SMTP
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            // Tài khoản Gmail gửi
//            String username = "giaptdhe186094@fpt.edu.vn";
//            String password = "voqfdcwtvasoucxx";
            String username = "tuannahe182193@fpt.edu.vn";
            String password = "bjcg ajlw kqjq rnzn";

            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            try {
                String content = "<html><body>"
                        + "<p style='font-size: 20px; font-weight: bold;'>     Traveland     </p>"
                        + "<p style='font-size: 12px; font-weight: bold;'>      Reset Password      </p>"
//                        + "<p style='font-size: 10px;'>Hi <span style='font-weight: bold;'>" + fullName + "</span>,</p>"
//                        + "<p>This is your new password for your Traveland account: <span style='font-weight: bold;'>" + newPass + "</span></p>"
                        + "<p>Do not share for anyone else.</p>"
                        + "<p>Thank you!</p>"
                        + "</body></html>";

                //MimeMessage
                Message message = new MimeMessage(session);
                message.addHeader("Content-type", "text/HTML; charset=UTF-8");
                message.setFrom(new InternetAddress(username)); //Email người gửi
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false)); //Email người nhận
                message.setSubject(MimeUtility.encodeText("Password Change Request", "UTF-8", "B"));
                message.setSentDate(new Date());
                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                mimeBodyPart.setContent(content, "text/html; charset=UTF-8");

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(mimeBodyPart);
                message.setContent(multipart); //chứa nội dung của email

                Transport.send(message);
                request.setAttribute("message", "Your new password has been sent to your email. Please check and log in again.");
                request.getRequestDispatcher("sign_in.jsp").forward(request, response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
