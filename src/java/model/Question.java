/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Question {

    private int id;
    private int quizId;
    private String content;
    private int levelId; //muc do kho de cua cau hoi
    private int questionTypeId; //1-Essay, 2-Multiple Choices
    private int status;

    public Question() {
    }

    public Question(int id, int quizId, String content, int levelId, int questionTypeId, int status) {
        this.id = id;
        this.quizId = quizId;
        this.content = content;
        this.levelId = levelId;
        this.questionTypeId = questionTypeId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public int getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(int questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", quizId=" + quizId + ", content=" + content + ", levelId=" + levelId + ", questionTypeId=" + questionTypeId + '}';
    }

}
