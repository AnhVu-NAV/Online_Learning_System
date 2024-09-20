/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Lesson {

    private int id;
    private Course course;
    private String title;
    private int status;

    public Lesson() {
    }

    public Lesson(int id, Course course, String title, int status) {
        this.id = id;
        this.course = course;
        this.title = title;
        this.status = status;
    }

    // <editor-fold defaultstate="collapsed" desc="Getter and Setter methods. Click on the + sign on the left to edit the code.">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    // </editor-fold>

    @Override
    public String toString() {
        return "Lesson{" + "id=" + id + ", course=" + course + ", title=" + title + ", status=" + status + '}';
    }

}
