/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Vector;

/**
 *
 * @author ADMIN
 */
public class Question {

    private Quiz quizId;
    private Vector<Option> options;
    private Setting levelId;
    private Vector<PersonalQuestion> personalQuestions;
    private String content;
    private int id;

    public Question() {
    }

}
