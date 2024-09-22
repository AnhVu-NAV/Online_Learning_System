/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.SettingDAO;
import dal.SliderDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.Account;
import model.Slider;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SliderController", urlPatterns = {"/slider"})
public class SliderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SliderDAO sliderDao = new SliderDAO();
        try {
            if (request.getParameter("id") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("slider", sliderDao.getSliderById(id));
                request.getRequestDispatcher("slider.jsp").forward(request, response);
            }
            Vector<Slider> list = sliderDao.getAllSlider();
            if (list != null) {
                request.setAttribute("list", list);
            } else {
                throw new Exception("Sliders are empty");
            }
        } catch (Exception ex) {
            request.setAttribute("error", ex.getMessage());
        }
        request.getRequestDispatcher("slider.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String crud = request.getParameter("crud");
        String imageUrl = request.getParameter("imageUrl");
        String backlinkUrl = request.getParameter("backlinkUrl");
        String status_raw = request.getParameter("status");
        SliderDAO sliderDao = new SliderDAO();
        HttpSession sesson = request.getSession();
        try {
            if (crud != null) {
                switch (crud) {
                    case "Show":
                        request.setAttribute("showAllSlider", sliderDao.getAllSlider());
                        request.getRequestDispatcher("slider_list.jsp").forward(request, response);
                        break;
                    case "Add":
                        request.setAttribute("add", "add");
                        request.getRequestDispatcher("slider_list.jsp").forward(request, response);
                        break;
                    case "Hide":
                        break;
                    case "Edit":
                        request.setAttribute("edit", "edit");
                        request.getRequestDispatcher("slider_list.jsp").forward(request, response);
                        break;
                }
            }
//            AccountDAO adao = new AccountDAO();
//            Account account = adao.getAccountById(2); 
            Account account = (Account) sesson.getAttribute("account"); 
            Slider slider;
            int status = 0;
            if (imageUrl != null && backlinkUrl != null && status_raw != null && request.getParameter("id") == null) {
                status = Integer.parseInt(status_raw);
                slider = new Slider(account, imageUrl, backlinkUrl, status);
                sliderDao.insertSlider(slider);
            } else if (imageUrl != null && backlinkUrl != null && status_raw != null && request.getParameter("id") != null) {
                slider = new Slider(account, imageUrl, backlinkUrl, status);
                int id = Integer.parseInt(request.getParameter("id"));
                sliderDao.updateSliderById(id, slider);
            } else {
                throw new Exception("Must enter all the fields");
            }
            request.getRequestDispatcher("slider_list.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("slider_list.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
