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
import util.ImportFileAndNormalizingData;

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
        int id;

        try {
            data = questionDAO.getAllInformationOfQuestions();

            // filter and list
            if (action != null) {
                switch (action) {
                    case "course":
                        data = questionDAO.getAllQuestionsByQuery("order by course.title");
                        break;
                    case "lesson":
                        data = questionDAO.getAllQuestionsByQuery("order by lesson.title");
                        break;
                    case "chapter":
                        data = questionDAO.getAllQuestionsByQuery("order by chapter.title");
                        break;
                    case "status":
                        data = questionDAO.getAllQuestionsByQuery("order by chapter.status");
                        break;
                    case "show":
                        id = Integer.parseInt(request.getParameter("id"));
                        questionDAO.updateStatus(1, id);
                        break;
                    case "hide":
                        id = Integer.parseInt(request.getParameter("id"));
                        questionDAO.updateStatus(0, id);
                        break;
                }
            }

            // search
            if (result != null) {
                data = questionDAO.searchForQuestion(result);
            }
            request.setAttribute("data", data);

        } catch (NumberFormatException e) {
            request.setAttribute("error", e.getMessage());
        } finally {
            request.getRequestDispatcher("question_list.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        QuestionDAO questionDAO = new QuestionDAO();
        PrintWriter out = response.getWriter();

        String content = request.getParameter("content");
        String answer = request.getParameter("answer");
        String level_raw = request.getParameter("level");
        String typeOfQuestion = request.getParameter("type");
        String explaination = request.getParameter("explaination");
        String id_raw = request.getParameter("id");

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
                List<String[]> data = ImportFileAndNormalizingData.readXlsx(file.getPath());
                int count = 1;
                for (String[] array : data) {
                    ++count;
                    Question question = new Question();
                    String validContent = ImportFileAndNormalizingData.getContent(array[1], count);
                    int validQuestionType = ImportFileAndNormalizingData.getTypeOfQuestion(array[2], count);
                    int levelId = ImportFileAndNormalizingData.getLevelOfQuestion(array[3], count);

                    question.setContent(validContent);
                    question.setQuestionTypeId(validQuestionType);
                    question.setLevelId(levelId);
                    String courseTitle = array[6];
                    String quiz = array[7];
                    String lesson = array[8];
                    String chapter = array[9];

                    int quizId = getQuizId(courseTitle, quiz, lesson, chapter);
                    if (quizId == -1) {
                        throw new Exception("Title of Course, Quiz, Lesson or Chapter is wrong at the line " + count);
                    }
                    question.setQuizId(quizId);
                    questionDAO.insert(question);
                }
            }

            // Edit
//            if (content != null && answer != null && level_raw != null && typeOfQuestion != null && id_raw != null && explaination != null) {
//                int type = Integer.parseInt(typeOfQuestion);
//                int level = Integer.parseInt(level_raw);
//                int id = Integer.parseInt(id_raw);
//                Question question = new Question();
//                question.setContent(content);
//                question.setLevelId(level);
//                question.setQuestionTypeId(type);
//
//                OptionDAO optionDAO = new OptionDAO();
//                int optionId = optionDAO.getIdByQuestionId(id);
//                Option option = optionDAO.getOptionById(optionId);
//                option.setExplanation(explaination);
//            }

        } catch (Exception e) {
//            request.setAttribute("error", e.getMessage());
            out.println(e.getMessage());
//        } finally {
//            request.getRequestDispatcher("question_list.jsp").forward(request, response);
        }
    }

    private int getQuizId(String courseTitle, String quiz, String lesson, String chapter) {
        String query;
        int quizId = -1;

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
        quizId = quizDAO.getIdByRequest(query);

        return quizId;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
