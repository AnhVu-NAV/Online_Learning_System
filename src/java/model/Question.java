/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Question {

    private int id;
    private int quizId;
    private int levelId;
    private int status;
    private String content;
    private int questionTypeId;
    private String hint;

    // Constructors
    public Question() {
    }

    public Question(int id, int quizId, int levelId, int status, String content, int questionTypeId, String hint) {
        this.id = id;
        this.quizId = quizId;
        this.levelId = levelId;
        this.status = status;
        this.content = content;
        this.questionTypeId = questionTypeId;
        this.hint = hint;
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

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(int questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", quizId=" + quizId + ", levelId=" + levelId + ", status=" + status + ", content=" + content + ", questionTypeId=" + questionTypeId + ", hint=" + hint + '}';
    }

}
