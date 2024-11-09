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

        // Bỏ qua kiểm tra nếu là trang /home
        if (url.contains("/home") || url.contains("/login")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user != null) {
            switch (user.getRoleId()) {
                case 3: // Admin
                    if (url.contains("/UserDashboardController") || url.contains("/subjectLesson")) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        handleUnauthorizedAccess(request, response);
                    }
                    break;
                case 4: // Customer
                case 5: // Marketer
                    if (url.contains("/marketer")) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        handleUnauthorizedAccess(request, response);
                    }
                    break;
                case 6: // Expert
                    if (url.contains("/expert")) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        handleUnauthorizedAccess(request, response);
                    }
                    break;
                case 7: // Sale
                    if (url.contains("/home")) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        handleUnauthorizedAccess(request, response);
                    }
                    break;
                default:
                    // Người dùng có roleId không xác định
                    handleUnauthorizedAccess(request, response);
                    break;
            }
        } else {
            // Nếu chưa đăng nhập, chuyển hướng tới trang đăng nhập với thông báo
            handleUnauthorizedAccess(request, response);

//            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private void handleUnauthorizedAccess(HttpServletRequest request, HttpServletResponse response) throws IOException {
    HttpSession session = request.getSession();
    session.setAttribute("message", "Bạn không có quyền truy cập trang này");
    session.setAttribute("alert", "danger");
    response.sendRedirect(request.getContextPath() + "/home?action=login");
}


    @Override
    public void destroy() {
        Filter.super.destroy(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
