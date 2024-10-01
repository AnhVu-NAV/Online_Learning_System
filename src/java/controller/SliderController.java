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
import jakarta.servlet.http.Cookie;
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
        SliderDAO sliderDAO = new SliderDAO();
        String choice = request.getParameter("filter");
        String crud = request.getParameter("crud");
        int page = 0;
        int numberContentPerPage = 10;
        int numberPages = 0;
        int start = 0;
        int end = 0;
        Slider slider = null;
        Cookie cookie = null;
        Cookie[] cookies = request.getCookies();
        try {
            Vector<Slider> list = null;
            for (Cookie c : cookies) {
                if (c.getName().equals("filter")) {
                    cookie = c;
                    break;
                }
            }

            //filter
            if (choice != null) {
                if (cookie == null) {
                    cookie = new Cookie("filter", choice);
                } else {
                    cookie.setValue(choice);
                }
                response.addCookie(cookie);

                switch (choice) {
                    case "id":
                        list = sliderDAO.getSliderByOrder("order by id;");
                        break;
                    case "account":
                        list = sliderDAO.getSliderByOrder("order by author_id;");
                        break;
                    case "status":
                        list = sliderDAO.getSliderByOrder("order by status;");
                        break;
                }
            } else if (choice != null && cookie != null) {
                choice = cookie.getValue();
                switch (choice) {
                    case "id":
                        list = sliderDAO.getSliderByOrder("order by id;");
                        break;
                    case "account":
                        list = sliderDAO.getSliderByOrder("order by author_id;");
                        break;
                    case "status":
                        list = sliderDAO.getSliderByOrder("order by status;");
                        break;
                    default:
                        list = sliderDAO.getAllSlider();
                        break;
                }
            } else if (choice == null) {
                list = sliderDAO.getAllSlider();
                if (cookie != null) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }

            //paging
            numberPages = (list.size() % numberContentPerPage == 0) ? list.size() / numberContentPerPage : (list.size() / numberContentPerPage + 1);
            int size = list.size();
            String pageIndex = request.getParameter("pageIndex");
            if (pageIndex != null) {
                page = Integer.parseInt(pageIndex);
            } else {
                page = 1;
            }
            start = (page - 1) * numberContentPerPage;
            end = Math.min(page * numberContentPerPage, size);

            //add, edit
            if (crud != null) {
                if (crud.equals("Add")) {
                    request.setAttribute("add", "add");
                    request.getRequestDispatcher("slider.jsp").forward(request, response);
                }
            }

            //view by slider?
            Vector<Slider> data = getList(list, start, end);
            request.setAttribute("page", page);
            request.setAttribute("numberPages", numberPages);
            request.setAttribute("showAllSlider", data);

            //view, show, hide
            if (request.getParameter("id") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                slider = sliderDAO.getSliderById(id);
                String action = request.getParameter("action");
                if (action != null) {
                    switch (action) {
                        case "view":
                            request.setAttribute("slider", slider);
                            request.getRequestDispatcher("slider_detail.jsp").forward(request, response);
                            break;
                        case "show":
                            slider.setStatus(1);
                            break;
                        case "hide":
                            slider.setStatus(0);
                            break;
                    }
                    sliderDAO.updateSliderById(id, slider);
                }
            }
        } catch (Exception ex) {
            request.setAttribute("error", ex.getMessage());
        } finally {
            request.getRequestDispatcher("slider.jsp").forward(request, response);
        }
    }

    private Vector<Slider> getList(List<Slider> list, int start, int end) {
        Vector<Slider> l = new Vector();
        for (int i = start; i < end; i++) {
            l.add(list.get(i));
        }
        return l;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String imageUrl = request.getParameter("imageUrl");
        String backlinkUrl = request.getParameter("backlinkUrl");
        String statusRaw = request.getParameter("status");
        String result = request.getParameter("result");
        SliderDAO sliderDao = new SliderDAO();
        HttpSession sesson = request.getSession();
        PrintWriter out = response.getWriter();
        int page = 0;
        int numberContentPerPage = 10;
        int numberPages = 0;
        int start = 0;
        int end = 0;
        Vector<Slider> list = new Vector();
        try {

            if (result != null) {
                if (sliderDao.getSliderBySearching(result) == null) {
                    throw new Exception("No result");
                } else {
                    list = sliderDao.getSliderBySearching(result);

                    //paging
                    numberPages = (list.size() % numberContentPerPage == 0) ? list.size() / numberContentPerPage : (list.size() / numberContentPerPage + 1);
                    int size = list.size();
                    String pageIndex = request.getParameter("pageIndex");
                    if (pageIndex != null) {
                        page = Integer.parseInt(pageIndex);
                    } else {
                        page = 1;
                    }
                    start = (page - 1) * numberContentPerPage;
                    end = Math.min(page * numberContentPerPage, size);
                    Vector<Slider> data = getList(list, start, end);
                    request.setAttribute("showAllSlider", data);
                    request.setAttribute("page", page);
                    request.setAttribute("numberPages", numberPages);
                }
            }

            Slider slider;
            int status = 0;
            User user = (User) sesson.getAttribute("account");

            //xu ly add slider 
            if (imageUrl != null && backlinkUrl != null) {
                status = Integer.parseInt(statusRaw);
                slider = new Slider(1, imageUrl, backlinkUrl, status);
                sliderDao.insertSlider(slider);
//                request.getRequestDispatcher("slider?filter=id").forward(request, response);
            } else {
                request.setAttribute("add_error", "Must enter all the fields");
                request.setAttribute("add", "add");
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        } finally {
            request.getRequestDispatcher("slider.jsp").forward(request, response);
        }
    }

}
