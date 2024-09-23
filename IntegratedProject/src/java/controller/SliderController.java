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
import java.io.PrintWriter;
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
        String choice = request.getParameter("choice");
        Slider slider;
        PrintWriter out = response.getWriter();
        try {
            int nrpp = 10;
            int totalPage = (sliderDao.getAllSlider().size() + nrpp - 1) / nrpp;
            String index_raw = request.getParameter("index");
            int index = 1;
            if (index_raw != null) {
                index = Integer.parseInt(index_raw);
            }
            String string = "select * from Slider limit " + (index - 1) * nrpp + "," + nrpp;
            request.setAttribute("sliderType", sliderDao.getAllSlider());
            request.setAttribute("totalPage", totalPage);
            if (request.getParameter("action") != null) {
                if (request.getParameter("action").equals("next")) {
                    request.setAttribute("showAllSlider", sliderDao.getSliders(string));
                    request.getRequestDispatcher("slider?action=list").forward(request, response);
                }
            }
            if (choice != null) {
                switch (choice) {
                    case "byID":
                        request.setAttribute("showAllSlider", sliderDao.getSliderByIdOrder());
                        request.getRequestDispatcher("slider_list.jsp").forward(request, response);
                        break;
                    case "byAccount":
                        request.setAttribute("showAllSlider", sliderDao.getSliderByIdOrder());
                        request.getRequestDispatcher("slider?action=list").forward(request, response);
                        break;
                    case "byStatus":
                        request.setAttribute("showAllSlider", sliderDao.getSliderByStatusOrder());
                        request.getRequestDispatcher("slider?action=list").forward(request, response);
                        break;
                }
            }
//            String string = filterPage(request, response, choice);
            if ("list".equals(request.getParameter("action"))) { //goi ra bang slider?action=list
                out.println("oke");
                Vector<Slider> list = sliderDao.getAllSlider();
                request.setAttribute("showAllSlider", sliderDao.getSliders(string));
//                request.setAttribute("showAllSlider", sliderDao.getAllSlider());
                request.getRequestDispatcher("slider_list.jsp").forward(request, response);
            }
            if (request.getParameter("id") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                if (request.getParameter("action") != null && request.getParameter("action").equals("view")) {
                    request.setAttribute("slider", sliderDao.getSliderById(id));
                    request.getRequestDispatcher("slider.jsp").forward(request, response);
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
            request.getRequestDispatcher("slider_list.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String crud = request.getParameter("crud");
        String imageUrl = request.getParameter("imageUrl");
        String backlinkUrl = request.getParameter("backlinkUrl");
        String status_raw = request.getParameter("status");
        String result = request.getParameter("result");
        SliderDAO sliderDao = new SliderDAO();
        HttpSession sesson = request.getSession();
        try {
            if (crud != null) {
                switch (crud) {
                    case "Add":
                        request.setAttribute("add", "add");
                        request.getRequestDispatcher("slider_list.jsp").forward(request, response);
                        break;
                    case "Edit":
                        request.setAttribute("edit", "edit");
                        request.getRequestDispatcher("slider_list.jsp").forward(request, response);
                        break;
                }
            }
            if (result != null) {
                if (sliderDao.getSlderByRequest(result) == null) {
                    throw new Exception("No result");
                } else {
                    request.setAttribute("showAllSlider", sliderDao.getSlderByRequest(result));
                    request.getRequestDispatcher("slider?action=list").forward(request, response);
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

//    private String filterPage(HttpServletRequest request, HttpServletResponse response, String choice) throws Exception {
//        SliderDAO sliderDao = new SliderDAO();
//        int nrpp = 10;
//        int totalPage = (sliderDao.getAllSlider().size() + nrpp - 1) / nrpp;
//        String index_raw = request.getParameter("index");
//        int index = 1;
//        if (index_raw != null) {
//            index = Integer.parseInt(index_raw);
//        }
//        String string = "select * from Slider limit " + (index - 1) * nrpp + "," + nrpp;
//        Vector<Slider> list = sliderDao.getAllSlider();
//        if (choice != null) {
//            switch (choice) {
//                case "byID":
////                    request.setAttribute("sliderType", sliderDao.getSliderByIdOrder());
////                request.getRequestDispatcher("slider?action=list").forward(request, response);
//                    list = sliderDao.getSliderByIdOrder();
//                    break;
//                case "byAccount":
////                    request.setAttribute("sliderType", sliderDao.getSliderByAccountOrder());
////                request.getRequestDispatcher("slider?action=list").forward(request, response);
//                    list = sliderDao.getSliderByAccountOrder();
//                    break;
//                case "byStatus":
////                    request.setAttribute("sliderType", sliderDao.getSliderByStatusOrder());
////                request.getRequestDispatcher("slider?action=list").forward(request, response);
//                    list = sliderDao.getSliderByStatusOrder();
//                    break;
//            }
//        }
////        request.setAttribute("sliderType", sliderDao.getAllSlider());
//        request.setAttribute("sliderType", list);
//        request.setAttribute("totalPage", totalPage);
//        request.getRequestDispatcher("slider?action=list").forward(request, response);
//        if (request.getParameter("action") != null) {
//            if (request.getParameter("action").equals("next")) {
//                request.setAttribute("showAllSlider", sliderDao.getSliders(string));
//                request.getRequestDispatcher("slider?action=list").forward(request, response);
//            }
//        }
//        return string;
//    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
