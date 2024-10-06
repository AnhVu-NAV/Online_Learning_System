/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

/**
 *
 * @author ADMIN
 */
public class QuestionDAO extends DBContext {

    // <editor-fold defaultstate="collapsed" desc="Get Methods">
    public int getIdByRequest(String request) {
        int id = 0;
        String sql = "select question.id from Question question " + request;
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = pre.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getInt("id");
                }
            }
        } catch (Exception e) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return id;
    }

    public Map<Question, List<String>> getAllInformationOfQuestions() {
        Map<Question, List<String>> map = new HashMap<>();
        String sql = "select question.*, `option`.isTrue, `option`.explaination, course.title as course_title, chapter.title as chapter_title, lesson.title as lesson_title, quiz.title as quiz_title from Question question\n"
                + "inner join Quiz quiz on question.quiz_id = quiz.lesson_id\n"
                + "inner join Lesson lesson on lesson.id = quiz.lesson_id\n"
                + "inner join Chapter chapter on chapter.id = lesson.chapter_id\n"
                + "inner join Course course on course.id = chapter.course_id\n"
                + "inner join `Option` `option` on `option`.question_id = question.id";
        try (PreparedStatement pre = connection.prepareStatement(sql); ResultSet resultSet = pre.executeQuery()) {
            while (resultSet.next()) {

                // question
                Question question = new Question();
                question.setId(resultSet.getInt("id"));
                question.setQuizId(resultSet.getInt("quiz_id"));
                question.setLevelId(resultSet.getInt("level_id"));
                question.setContent(resultSet.getString("content"));
                question.setQuestionTypeId(resultSet.getInt("question_type_id"));
                question.setStatus(resultSet.getInt("status"));

                // list
                List<String> listOfTitle = new ArrayList<>();
                listOfTitle.add(resultSet.getString("isTrue"));
                listOfTitle.add(resultSet.getString("explaination"));
                listOfTitle.add(resultSet.getString("course_title"));
                listOfTitle.add(resultSet.getString("chapter_title"));
                listOfTitle.add(resultSet.getString("lesson_title"));
                listOfTitle.add(resultSet.getString("quiz_title"));

                map.put(question, listOfTitle);
            }
        } catch (Exception e) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return map;
    }// </editor-fold>

    public void insert(Question question) {
        String sql = "INSERT INTO Question (quiz_id, level_id, content, question_type_id)\n"
                + "VALUES (?,?,?,?)";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, question.getQuizId());
            pre.setInt(2, question.getLevelId());
            pre.setString(3, question.getContent());
            pre.setInt(4, question.getQuestionTypeId());
            pre.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public Map<Question, List<String>> searchForQuestion(String input) {
        Map<Question, List<String>> map = new HashMap<>();
        String sql = "select question.*, `option`.isTrue, `option`.explaination, course.title as course_title, chapter.title as chapter_title, lesson.title as lesson_title, quiz.title as quiz_title from Question question\n"
                + "inner join Quiz quiz on question.quiz_id = quiz.lesson_id\n"
                + "inner join Lesson lesson on lesson.id = quiz.lesson_id\n"
                + "inner join Chapter chapter on chapter.id = lesson.chapter_id\n"
                + "inner join Course course on course.id = chapter.course_id\n"
                + "inner join `Option` `option` on `option`.question_id = question.id\n"
                + "where question.content like ? or question.id = ? or question.level_id = ?";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            int levelOfQuestion = 0;
            switch (input.toLowerCase()) {
                case "Easy" -> levelOfQuestion = 0;
                case "Normal" -> levelOfQuestion = 1;
                case "Difficulty" -> levelOfQuestion = 2;
                default -> {
                }
            }
            String string = "%" + input + "%";
            pre.setString(1, string);
            pre.setString(2, input);
            pre.setInt(3, levelOfQuestion);
            try (ResultSet resultSet = pre.executeQuery()) {
                while (resultSet.next()) {

                    // question
                    Question question = new Question();
                    question.setId(resultSet.getInt("id"));
                    question.setQuizId(resultSet.getInt("quiz_id"));
                    question.setLevelId(resultSet.getInt("level_id"));
                    question.setContent(resultSet.getString("content"));
                    question.setQuestionTypeId(resultSet.getInt("question_type_id"));
                    question.setStatus(resultSet.getInt("status"));

                    // list
                    List<String> listOfTitle = new ArrayList<>();
                    listOfTitle.add(resultSet.getString("isTrue"));
                    listOfTitle.add(resultSet.getString("explaination"));
                    listOfTitle.add(resultSet.getString("course_title"));
                    listOfTitle.add(resultSet.getString("chapter_title"));
                    listOfTitle.add(resultSet.getString("lesson_title"));
                    listOfTitle.add(resultSet.getString("quiz_title"));

                    map.put(question, listOfTitle);
                }
            }
        } catch (Exception e) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return map;
    }

    public Map<Question, List<String>> getAllQuestionsByQuery(String query) {
        Map<Question, List<String>> map = new HashMap<>();
        String sql = "select question.*, `option`.isTrue, `option`.explaination, course.title as course_title, chapter.title as chapter_title, lesson.title as lesson_title, quiz.title as quiz_title from Question question\n"
                + "inner join Quiz quiz on question.quiz_id = quiz.lesson_id\n"
                + "inner join Lesson lesson on lesson.id = quiz.lesson_id\n"
                + "inner join Chapter chapter on chapter.id = lesson.chapter_id\n"
                + "inner join Course course on course.id = chapter.course_id\n"
                + "inner join `Option` `option` on `option`.question_id = question.id\n" + query;
        try (PreparedStatement pre = connection.prepareStatement(sql); ResultSet resultSet = pre.executeQuery()) {
            while (resultSet.next()) {

                // question
                Question question = new Question();
                question.setId(resultSet.getInt("id"));
                question.setQuizId(resultSet.getInt("quiz_id"));
                question.setLevelId(resultSet.getInt("level_id"));
                question.setContent(resultSet.getString("content"));
                question.setQuestionTypeId(resultSet.getInt("question_type_id"));
                question.setStatus(resultSet.getInt("status"));

                // list
                List<String> listOfTitle = new ArrayList<>();
                listOfTitle.add(resultSet.getString("isTrue"));
                listOfTitle.add(resultSet.getString("explaination"));
                listOfTitle.add(resultSet.getString("course_title"));
                listOfTitle.add(resultSet.getString("chapter_title"));
                listOfTitle.add(resultSet.getString("lesson_title"));
                listOfTitle.add(resultSet.getString("quiz_title"));
                map.put(question, listOfTitle);
            }
        } catch (Exception e) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return map;
    }

    public void updateStatus(int status, int id) {
        String sql = "update Question\n"
                + "set status = ? \n"
                + "where id = ?";
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, status);
            pre.setInt(2, id);
            pre.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void main(String[] args) {
        QuestionDAO questionDAO = new QuestionDAO();
        Map<Question, List<String>> data = new HashMap<>();
        data = questionDAO.getAllInformationOfQuestions();
        for (Question q : data.keySet()) {
            System.out.println(q.toString());
        }
        System.out.println("values");
        for (List<String> l : data.values()) {
            for (String s : l) {
                System.out.println(s);
            }
        }
    }
}
