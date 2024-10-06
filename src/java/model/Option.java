/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Option {

    private int id;
    private int questionId;
    private boolean isTrue;
    private String explanation;

    public Option() {
    }

    public Option(int id, int questionId, boolean isTrue, String explanation) {
        this.id = id;
        this.questionId = questionId;
        this.isTrue = isTrue;
        this.explanation = explanation;
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

    public boolean isIsTrue() {
        return isTrue;
    }

    public void setIsTrue(boolean isTrue) {
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
        return "Option{" + "id=" + id + ", questionId=" + questionId + ", isTrue=" + isTrue + ", explanation=" + explanation + '}';
    }

}
