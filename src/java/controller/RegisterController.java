package controller;

import dal.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import model.User;

@WebServlet(urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\d{10,11}$");
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-ZÀ-ỹ\\s]+$");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String primaryEmail = request.getParameter("primaryEmail");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dobStr = request.getParameter("dob");
        String genderStr = request.getParameter("gender");
        String firstPhone = request.getParameter("firstPhone");
        String address = request.getParameter("address");

        UserDAO userDao = new UserDAO();

        // Validate email format
        if (!EMAIL_PATTERN.matcher(primaryEmail).matches()) {
            request.setAttribute("errorMessage", "Invalid email format.");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
            return;
        }

        // Check for existing email
        if (userDao.isEmailExists(primaryEmail)) {
            request.setAttribute("duplicateEmail", "Email already exists.");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
            return;
        }

        // Validate phone number format
        if (!PHONE_PATTERN.matcher(firstPhone).matches()) {
            request.setAttribute("errorMessage", "Phone number must be 10 or 11 digits.");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
            return;
        }

        // Check for existing phone number
        if (userDao.isPhoneExists(firstPhone)) {
            request.setAttribute("duplicatePhone", "Phone number already exists.");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
            return;
        }

        // Validate first name and last name for special characters
        if (!NAME_PATTERN.matcher(firstName).matches() || !NAME_PATTERN.matcher(lastName).matches()) {
            request.setAttribute("errorMessage", "Names cannot contain special characters or numbers.");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
            return;
        }

        // Validate date format for dob
        Date dob = null;
        try {
            java.util.Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(dobStr);
            dob = new Date(parsedDate.getTime());
        } catch (ParseException e) {
            request.setAttribute("errorMessage", "Invalid date format for DOB. Use yyyy-MM-dd.");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
            return;
        }

        // Parse gender to integer
        int gender = Integer.parseInt(genderStr);

        // Current date for createdDate
        Date createdDate = new Date(System.currentTimeMillis());

        // Create new User object
        User newUser = new User(primaryEmail, password, createdDate, firstName, lastName, dob, gender, firstPhone, address);

        // Insert user and handle result
        if (userDao.createNewUser(newUser) > 0) {
            request.setAttribute("registerSuccess", "Registration successful");
        } else {
            request.setAttribute("registerError", "Registration failed. Please try again.");
        }

        request.getRequestDispatcher("Register.jsp").forward(request, response);
    }
}
