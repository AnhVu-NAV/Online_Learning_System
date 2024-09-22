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
import model.User;

/**
 *
 * @author mocun
 */
public class AuthenrizationFilter implements Filter {

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
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");
            if (user != null) {
                if (user.getRole_id() == 0) {
                    // Admin user
                    filterChain.doFilter(servletRequest, servletResponse);
                } else if (user.getRole_id() == 1) {
                    // Customer user
                    response.sendRedirect(request.getContextPath() + "/login?action=login&message='Not permission'&alert=danger");
                } else if (user.getRole_id() == 2) {
                    // Default user
                    response.sendRedirect(request.getContextPath() + "/login?action=login&message='Not permission'&alert=danger");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/login?action=login&message='Not login'&alert=danger");
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
