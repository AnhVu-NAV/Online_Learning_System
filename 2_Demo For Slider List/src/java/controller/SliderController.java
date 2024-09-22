/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.SliderDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
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
        SliderDAO sliderDao = new SliderDAO();
        try {
            if (crud.equals("Show")) {
                request.setAttribute("showAllSlider", sliderDao.getAllSlider()); 
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage()); 
        } finally {
            request.getRequestDispatcher("edit_sliders.jsp").forward(request, response); 
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
