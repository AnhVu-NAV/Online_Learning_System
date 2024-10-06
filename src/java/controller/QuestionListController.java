/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;
import model.*;
import util.ImportFile;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "QuestionListController", urlPatterns = {"/QuestionList"})
@MultipartConfig
public class QuestionListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        QuestionDAO questionDAO = new QuestionDAO();
        PrintWriter out = response.getWriter();
        Map<Question, List<String>> data = new HashMap<>();
        String action = request.getParameter("action");
        String result = request.getParameter("result");

        String content = request.getParameter("content");
        String answer = request.getParameter("answer");
        String level_raw = request.getParameter("level");
        String typeOfQuestion = request.getParameter("type");
        String explaination = request.getParameter("explaination");
        String id_raw = request.getParameter("id"); 

//        try {

            // filter and list
//            if (action != null) {
//                switch (action) {
//                    case "list":
//                        data = questionDAO.getAllInformationOfQuestions();
//                        break;
//                    case "course":
//                        data = questionDAO.getAllQuestionsByQuery("order by course.title");
//                        break;
//                    case "lesson":
//                        data = questionDAO.getAllQuestionsByQuery("order by lesson.title");
//                        break;
//                    case "chapter":
//                        data = questionDAO.getAllQuestionsByQuery("order by chapter.title");
//                        break;
//                    case "status":
//                        data = questionDAO.getAllQuestionsByQuery("order by chapter.status");
//                        break;
//                }
//            }

            // search
//            if (result != null) {
//                data = questionDAO.searchForQuestion(result);
//            }
//            request.setAttribute("data", data);
            out.println(id_raw);
            out.println(content);
            out.println(answer);
            out.println(level_raw);
            out.println(typeOfQuestion);
            out.println(explaination);

            // Edit
//            if (content != null && answer != null && level_raw != null && typeOfQuestion != null) {
//                int type = Integer.parseInt(typeOfQuestion);
//                int level = Integer.parseInt(level_raw);
//                Question question = new Question();
//                question.setContent(content);
//                question.setLevelId(level);
//                question.setQuestionTypeId(type);
//            }
//        } catch (Exception e) {
//            request.setAttribute("error", e.getMessage());
//        } finally {
//            request.getRequestDispatcher("data2.jsp").forward(request, response);
//        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        QuestionDAO questionDAO = new QuestionDAO();
        PrintWriter out = response.getWriter();

        try {
            // upload file
            Part filePart = request.getPart("fileUpload");
            if (filePart != null) {
                String fileName = filePart.getSubmittedFileName();

                // save file into "uploads" folder
                File uploads = new File(getServletContext().getRealPath("/") + "uploads");
                if (!uploads.exists()) {
                    uploads.mkdir();
                }
                File file = new File(uploads, fileName);
                try (InputStream input = filePart.getInputStream()) {
                    //copy to build/web/uploads folder
                    Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                List<String[]> data = ImportFile.readXlsx(file.getPath());
                for (String[] array : data) {
                    Question question = new Question();
                    question.setContent(array[1]);
                    question.setQuestionTypeId(Integer.parseInt(array[2]));
                    question.setLevelId(Integer.parseInt(array[3]));
                    String courseTitle = array[6];
                    String quiz = array[7];
                    String lesson = array[8];
                    String chapter = array[9];

                    int quizId = getQuizId(courseTitle, quiz, lesson, chapter);
                    question.setQuizId(quizId);
                    questionDAO.insert(question);
                }
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        } finally {
            request.getRequestDispatcher("data2.jsp").forward(request, response);
        }
    }

    private int getQuizId(String courseTitle, String quiz, String lesson, String chapter) {
        String query;
        // get Chapter's id by Chapter's name and Course's title
        ChapterDAO chapterDAO = new ChapterDAO();
        query = "inner join Course course on chapter.course_id = course.id where chapter.title = "
                + chapter
                + " and course.title = '" + courseTitle + "'";
        int chapterId = chapterDAO.getIdByRequest(query);

        //get Lesson's id by Chapter's id and lesson'title
        LessonDAO lessonDAO = new LessonDAO();
        query = "inner join Chapter chapter on lesson.chapter_id = chapter.id where chapter.id = "
                + chapterId
                + " and lesson.title = '" + lesson + "'";
        int lessonId = lessonDAO.getIdByRequest(query);

        //get Quiz's id by Lesson's id and Quiz's title
        QuizDAO quizDAO = new QuizDAO();
        query = "inner join Lesson lesson on quiz.lesson_id = lesson.id where quiz.title = "
                + quiz
                + " and lesson.id = " + lessonId;
        int quizId = quizDAO.getIdByRequest(query);

        return quizId;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
