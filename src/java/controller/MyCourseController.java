/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CourseDAO;
import dal.PersonalCourseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import model.Course;
import model.PersonalCourse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "MyCourse", urlPatterns = {"/myCourse"})
public class MyCourseController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String view = request.getParameter("view");
        PersonalCourseDAO personalCourseDAO = new PersonalCourseDAO();
        CourseDAO courseDAO = new CourseDAO();
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        Vector<PersonalCourse> listOfPersonalCourse = new Vector();
        Map<Course, Integer> total = new HashMap<>();
        try {
            if (action != null && "list".equals(action)) {
                //total
                Vector<PersonalCourse> listOfCourse = personalCourseDAO.getPersonalCourses("");
                if (!listOfCourse.isEmpty()) {
                    for (PersonalCourse personalCourse : listOfCourse) {
                        Course course = courseDAO.getCourseById(personalCourse.getCourseId());
                        total.put(course, personalCourse.getProgress());
                    }
                    request.setAttribute("course", total);
                } else {
                    throw new Exception("No data");
                }
            }

            if (view != null) {
                //in progress 
                if (view.equals("in_progress")) {
                    listOfPersonalCourse = personalCourseDAO.getPersonalCourses("where progress < 100 and status = 1;");
                    total.clear();
                    if (!listOfPersonalCourse.isEmpty()) {
                        for (PersonalCourse personalCourse : listOfPersonalCourse) {
                            Course course = courseDAO.getCourseById(personalCourse.getCourseId());
                            total.put(course, personalCourse.getProgress());
                        }
                        request.setAttribute("in_progress", total);
                    } else {
                        throw new Exception("No data");
                    }
                }
                
                //completed
                if (view.equals("completed")) {
                    listOfPersonalCourse = personalCourseDAO.getPersonalCourses("where progress = 100 and status = 1;");
                    Vector<Course> listOfCompletedCourse = new Vector();
                    if (!listOfPersonalCourse.isEmpty()) {
                        for (PersonalCourse personalCourse : listOfPersonalCourse) {
                            Course course = courseDAO.getCourseById(personalCourse.getCourseId());
                            listOfCompletedCourse.add(course);
                        }
                        request.setAttribute("completed", listOfCompletedCourse);
                    } else {
                        throw new Exception("No data");
                    }
                }
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        } finally {
            request.getRequestDispatcher("my_courses.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CourseDAO courseDAO = new CourseDAO();
        PersonalCourseDAO personalCourseDAO = new PersonalCourseDAO();

        //get result of searching
        String result = request.getParameter("result");
        PrintWriter out = response.getWriter();
        if (result != null) {
            Vector<PersonalCourse> listOfPersonalCourse = personalCourseDAO.getPersonalCourses("where progress < 100 and status = 1;");
            Map<Integer, Course> map = new HashMap<>();
            if (!personalCourseDAO.getCourseBySearching(result).isEmpty()) {
                for (PersonalCourse personalCourse : personalCourseDAO.getCourseBySearching(result)) {
                    Course course = courseDAO.getCourseById(personalCourse.getCourseId());
                    map.put(personalCourse.getProgress(), course);
                }
                request.setAttribute("in_progress", map);
            } else {
                request.setAttribute("error", "No data");
            }
        }

        //redirect
        request.getRequestDispatcher("my_courses.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
