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
public class SettingController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
        DataConvert dataConvert = new DataConvert();
        String service = request.getParameter("service");
        if (service == null) {
            service = "viewAllSetting";
        }
        if(service.equals("viewAllSetting")){
            String fillterSubmit = request.getParameter("fillterSubmit");
            Vector<Setting> settingVector = null;
            Vector<SettingType> settingTypeVector = null;
            String sql = "select * from setting ";
            String checked = "sort_by_id";
            if(fillterSubmit!=null){
                String search_by_value = request.getParameter("search_by_value");
                String fillter_by_type = request.getParameter("fillter_by_type");
                String fillter_by_status = request.getParameter("fillter_by_status");
                String sort_by = request.getParameter("sort_by");
                sql += " where value like '%" + search_by_value.toLowerCase() + "%' ";
                if (!fillter_by_type.equals("all")) {
                    sql += " and setting_type_id= " + fillter_by_type;
                }
                if (!fillter_by_status.equals("all")) {
                    sql += " and status =" + fillter_by_status;
                }
                if (sort_by != null) {
                    switch (sort_by) {
                        case "sort_by_id": {
                            sql += " order by id asc";
                            break;
                        }
                        case "sort_by_setting_type": {
                            sql += " order by setting_type_id asc";
                            checked = "sort_by_setting_type";
                            break;
                        }
                        case "sort_by_value": {
                            sql += " order by value asc ";
                            checked = "sort_by_value";
                            break;
                        }
                        case "sort_by_created_date": {
                            sql += " order by created_date asc";
                            checked = "sort_by_created_date";
                            break;
                        }
                        case "sort_by_updated_date": {
                            sql += " order by updated_date asc ";
                            checked = "sort_by_updated_date";
                            break;
                        }
                        case "sort_by_status": {
                            sql += " order by status asc ";
                            checked = "sort_by_status";
                            break;
                        }
                    }
                }
            }
            //paging
            int nrpp=10;
            settingVector = settingDAO.getSettings(sql);
            int totalPage=(settingVector.size()+nrpp-1)/nrpp;
            String index_raw=request.getParameter("index");
            int index=1;
            if(index_raw!=null){
                index=Integer.parseInt(index_raw);
            }
            sql+=" limit "+(index-1)*nrpp+","+nrpp;
            settingVector = settingDAO.getSettings(sql);
            settingTypeVector = settingTypeDAO.getSettingTypes("select*from settingtype");
            //set data for views
            request.setAttribute("data", settingVector);
            request.setAttribute("setting_type", settingTypeVector);
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
            int setting_type_id = Integer.parseInt(request.getParameter("addNewSettingSettingType"));
            Setting setting = new Setting(0, setting_type_id, value, description, 1, GetTodayDate.getTodayDate(), GetTodayDate.getTodayDate());
            settingDAO.insertSetting(setting);             
            String message = "Add new setting successfuly";
            //set data for views
            request.setAttribute("message", message);
            // select view
            RequestDispatcher dispath = request.getRequestDispatcher("SettingController?service=viewAllSetting");
            //run
            dispath.forward(request, response);

        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
