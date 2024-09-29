/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.SliderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import model.Slider;
import model.User;

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
        String choice = request.getParameter("filter");
        String crud = request.getParameter("crud");
        String action = request.getParameter("action");
        Slider slider;
        PrintWriter out = response.getWriter();
        try {
            //add, edit
            if (crud != null) {
                if (crud.equals("Add")) {
                    request.setAttribute("add", "add");
                    request.getRequestDispatcher("slider.jsp").forward(request, response);
                }
            }

            //filter
            if (choice != null) {
                switch (choice) {
                    case "id":
                        request.setAttribute("showAllSlider", sliderDao.getSliderByOrder("order by id;"));
                        request.getRequestDispatcher("slider.jsp").forward(request, response);
                        break;
                    case "account":
                        request.setAttribute("showAllSlider", sliderDao.getSliderByOrder("order by author_id;"));
                        request.getRequestDispatcher("slider.jsp").forward(request, response);
                        break;
                    case "status":
                        request.setAttribute("showAllSlider", sliderDao.getSliderByOrder("order by status;"));
                        request.getRequestDispatcher("slider.jsp").forward(request, response);
                        break;
                }
            }

            //view, show, hide
            if (action != null) {
                //goi ra bang slider?action=list
                if (action.equals("list")) {
                    Vector<Slider> list = sliderDao.getAllSlider();
                    request.setAttribute("showAllSlider", list);
                    request.getRequestDispatcher("slider.jsp").forward(request, response);
                }
            }

            if (request.getParameter("id") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                if (request.getParameter("action") != null && request.getParameter("action").equals("view")) {
                    request.setAttribute("slider", sliderDao.getSliderById(id));
                    request.getRequestDispatcher("slider_detail.jsp").forward(request, response);
                } else if (request.getParameter("action") != null && request.getParameter("action").equals("show")) {
                    slider = sliderDao.getSliderById(id);
                    slider.setStatus(1);
                    sliderDao.updateSliderById(id, slider);
                    request.getRequestDispatcher("slider?action=list").forward(request, response);
                } else if (request.getParameter("action") != null && request.getParameter("action").equals("hide")) {
                    slider = sliderDao.getSliderById(id);
                    slider.setStatus(0);
                    sliderDao.updateSliderById(id, slider);
                    request.getRequestDispatcher("slider?action=list").forward(request, response);
                }
            }
        } catch (Exception ex) {
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("slider.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String imageUrl = request.getParameter("imageUrl");
        String backlinkUrl = request.getParameter("backlinkUrl");
        String status_raw = request.getParameter("status");
        SliderDAO sliderDao = new SliderDAO();
        HttpSession sesson = request.getSession();
        PrintWriter out = response.getWriter();
        try {
            Slider slider;
            int status = 0;
            User user = (User) sesson.getAttribute("account");

            //xu ly add slider 
            if (!imageUrl.isEmpty() && !backlinkUrl.isEmpty()) {
                status = Integer.parseInt(status_raw);
                slider = new Slider(user.getId(), imageUrl, backlinkUrl, status);
                sliderDao.insertSlider(slider);
                request.getRequestDispatcher("slider?action=list").forward(request, response);
            } else {
                request.setAttribute("add_error", "Must enter all the fields");
                request.setAttribute("add", "add");
                request.getRequestDispatcher("slider.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("slider.jsp").forward(request, response);
        }
    }

}
