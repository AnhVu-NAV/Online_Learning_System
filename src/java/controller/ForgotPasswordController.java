package controller;

import dal.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author admin
 */
public class ForgotPasswordController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String email = request.getParameter("email"); //email người nhận
            UserDAO cus = new UserDAO();
            String fullName = cus.checkEmail(email);
            HttpSession sessions = request.getSession();

            if (fullName.equals("Nothing")) {
                sessions.setAttribute("message", "No existing account with this email. Please check again!");
                response.sendRedirect("ForgotPassword.jsp");
                return;

            }
            String newPass = cus.updatePass(email);

            //SMTP
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            // Tài khoản Gmail gửi
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
                        + "<p style='font-size: 20px; font-weight: bold;'>     Learnik     </p>"
                        + "<p style='font-size: 12px; font-weight: bold;'>      Reset Password      </p>"
                        + "<p style='font-size: 10px;'>Hi <span style='font-weight: bold;'>" + fullName + "</span>,</p>"
                        + "<p>This is your new password for your Learnik account: <span style='font-weight: bold;'>" + newPass + "</span></p>"
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
                sessions.setAttribute("message", "Your new password has been sent to your email. Please check and log in again.");
                response.sendRedirect("ForgotPassword.jsp");
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
