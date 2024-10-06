/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.SettingDAO;
import dal.SettingTypeDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.Setting;
import model.SettingType;
import util.*;

/**
 *
 * @author 84941
 */
public class SettingDashboardController extends HttpServlet {

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
        SettingDAO settingDAO = new SettingDAO();
        SettingTypeDAO settingTypeDAO = new SettingTypeDAO();
        String service = request.getParameter("service");
        if (service == null) {
            service = "viewAllSetting";
        }
        if (service.equals("viewAllSetting")) {
            String fillterSubmit = request.getParameter("fillterSubmit");
            Vector<Setting> settingVector = null;
            Vector<SettingType> settingTypeVector = null;
            String sql = "select * from setting ";
            String checked = "sortById";
            if (fillterSubmit != null) {
                String searchByValue = request.getParameter("searchByValue");
                String fillterByType = request.getParameter("fillterByType");
                String fillterByStatus = request.getParameter("fillterByStatus");
                String sortBy = request.getParameter("sortBy");
                sql += " where value like '%" + searchByValue.toLowerCase() + "%' ";
                if (!fillterByType.equals("all")) {
                    sql += " and setting_type_id= " + fillterByType;
                }
                if (!fillterByStatus.equals("all")) {
                    sql += " and status =" + fillterByStatus;
                }
                if (sortBy != null) {
                    switch (sortBy) {
                        case "sortById": {
                            sql += " order by id asc";
                            break;
                        }
                        case "sortBySettingType": {
                            sql += " order by setting_type_id asc";
                            checked = "sortBySettingType";
                            break;
                        }
                        case "sortByValue": {
                            sql += " order by value asc ";
                            checked = "sortByValue";
                            break;
                        }
                        case "sortByCreatedDate": {
                            sql += " order by created_date desc";
                            checked = "sortByCreatedDate";
                            break;
                        }
                        case "sortByUpdatedDate": {
                            sql += " order by updated_date desc ";
                            checked = "sortByUpdatedDate";
                            break;
                        }
                        case "sortByStatus": {
                            sql += " order by status asc ";
                            checked = "sortByStatus";
                            break;
                        }
                    }
                }
            }
            //paging
            int nrpp = 10;
            settingVector = settingDAO.getSettings(sql);
            int totalPage = (settingVector.size() + nrpp - 1) / nrpp;
            String indexRaw = request.getParameter("index");
            int index = 1;
            if (indexRaw != null) {
                index = Integer.parseInt(indexRaw);
            }
            sql += " limit " + (index - 1) * nrpp + "," + nrpp;
            settingVector = settingDAO.getSettings(sql);
            settingTypeVector = settingTypeDAO.getSettingTypes("select*from settingtype");
            //set data for views
            request.setAttribute("data", settingVector);
            request.setAttribute("settingType", settingTypeVector);
            request.setAttribute("checked", checked);
            request.setAttribute("totalPage", totalPage);
            // select view
            RequestDispatcher dispath = request.getRequestDispatcher("jsp/ViewSettingList.jsp");
            //run
            dispath.forward(request, response);

        }
        if (service.equals("addNewSetting")) {
            String value = request.getParameter("addNewSettingValue");
            String description = request.getParameter("addNewSettingDescription");
            int settingTypeId = Integer.parseInt(request.getParameter("addNewSettingSettingType"));
            Setting setting = new Setting(0, settingTypeId, value, 1, description, GetTodayDate.getTodayDate(), GetTodayDate.getTodayDate());
            settingDAO.insertSetting(setting);
            String message = "Add new setting successfuly";
            //set data for views
            request.setAttribute("message", message);
            // select view
            RequestDispatcher dispath = request.getRequestDispatcher("SettingDashboardController?service=viewAllSetting");
            //run
            dispath.forward(request, response);

        }
        if (service.equals("viewSettingDetails")) {
            String idRaw = request.getParameter("id");
            Setting setting = settingDAO.getSettingById(Integer.parseInt(idRaw));
            Vector<SettingType> settingTypeVector = settingTypeDAO.getSettingTypes("SELECT * FROM learnik.settingtype");
            //set data for views
            request.setAttribute("setting", setting);
            request.setAttribute("settingType", settingTypeVector);
            // select view
            RequestDispatcher dispath = request.getRequestDispatcher("jsp/ViewSettingDetails.jsp");
            //run
            dispath.forward(request, response);
        }
        if (service.equals("updateSetting")) {
            int id = Integer.parseInt(request.getParameter("settingId"));
            int settingTypeId = Integer.parseInt(request.getParameter("settingTypeId"));
            String value = request.getParameter("settingValue");
            int status = Integer.parseInt(request.getParameter("settingStatus"));
            String description = request.getParameter("settingDescription");
            Setting setting = new Setting(id, settingTypeId, value, status, description, settingDAO.getSettingById(id).getCreatedDate(), GetTodayDate.getTodayDate());
            int result = settingDAO.updateSetting(setting);
            if (result >0) {
                String message = "Update setting successfuly";
                //set data for views
                request.setAttribute("message", message);
                // select view
                RequestDispatcher dispath = request.getRequestDispatcher("SettingDashboardController?service=viewAllSetting");
                //run
                dispath.forward(request, response);
            }
            else{
                String message = "Update setting failed";
                //set data for views
                request.setAttribute("message", message);
                // select view
                RequestDispatcher dispath = request.getRequestDispatcher("SettingDashboardController?service=viewAllSetting");
                //run
                dispath.forward(request, response);
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
