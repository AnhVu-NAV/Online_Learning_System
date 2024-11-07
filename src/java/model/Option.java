/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Option {
   private int id;
    private int questionId;
    private int status;
    private int isTrue; // 1 for true, 0 for false in the database
    private String explanation;
    private String content;

    // Constructors
    public Option() {}

    public Option(int id, int questionId, int status, int isTrue, String explanation, String content) {
        this.id = id;
        this.questionId = questionId;
        this.status = status;
        this.isTrue = isTrue;
        this.explanation = explanation;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(int isTrue) {
        this.isTrue = isTrue;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    @Override
    public String toString() {
        return "Option{" + "id=" + id + ", questionId=" + questionId + ", status=" + status + ", isTrue=" + isTrue + ", explanation=" + explanation + '}';
    }
    
    
    
}
