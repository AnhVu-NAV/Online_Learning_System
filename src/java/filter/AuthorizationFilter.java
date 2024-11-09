/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import model.User;

/**
 *
 * @author mocun
 */
public class AuthorizationFilter implements Filter {

    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();

        if (url.contains("/admin")) {
//            if (url.startsWith(request.getContextPath() + "/admin")) {
            HttpSession session = request.getSession(false);
            User user = (session != null) ? (User) session.getAttribute("user") : null;
            if (user != null) {
                if (user.getRoleId() == 1) {
                    // Admin user
                    filterChain.doFilter(servletRequest, servletResponse);
                } else if (user.getRoleId() == 6) {
                    // Customer user
                    request.setAttribute("message", "Not Permission");
                    request.setAttribute("alert", "danger");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);

                } else if (user.getRoleId() == 2) {
                    // Default user
                    request.setAttribute("message", "Not Permission");
                    request.setAttribute("alert", "danger");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);

                }
            } else {
                request.setAttribute("message", "Not Permission");
                request.setAttribute("alert", "danger");
                request.getRequestDispatcher("/login.jsp").forward(request, response);

            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
