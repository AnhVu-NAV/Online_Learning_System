/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class PersonalCourse {

    private Account customer_id;
    private Course course_id;
    private Date enrollDate;
    private Date expireDate;
    private int progress;
    private int status;

    public PersonalCourse() {
    }

    public PersonalCourse(int progress, int status) {
        this.progress = progress;
        this.status = status;
    }

    // <editor-fold defaultstate="collapsed" desc="Getter and Setter methods. Click on the + sign on the left to edit the code.">
    public Account getAccount() {
        return customer_id;
    }

    public void setAccount(Account account) {
        this.customer_id = account;
    }

    public Course getCourse() {
        return course_id;
    }

    public void setCourse(Course course) {
        this.course_id = course;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public Account getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Account customer_id) {
        this.customer_id = customer_id;
    }

    public Course getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Course course_id) {
        this.course_id = course_id;
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
        return "PersonalCourse{" + "enrollDate=" + enrollDate + ", expireDate=" + expireDate + ", progress=" + progress + '}';
    }

}
