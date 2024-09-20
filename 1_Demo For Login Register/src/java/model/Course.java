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
public class Course {

    private int id;
    private String title;
    private Account account;
    private double total_duration;
    private int status;
    private String description;
    private Setting setting;
    private Date createdDate;
    private Date updatedDate;
    private String thumbnailUrl;
    private int numberOfLesson;

    public Course() {
    }

    public Course(int id, String title, Account account, double total_duration, int status, String description, Setting setting, Date createdDate, Date updatedDate, String thumbnailUrl, int numberOfLesson) {
        this.id = id;
        this.title = title;
        this.account = account;
        this.total_duration = total_duration;
        this.status = status;
        this.description = description;
        this.setting = setting;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.thumbnailUrl = thumbnailUrl;
        this.numberOfLesson = numberOfLesson;
    }

    // <editor-fold defaultstate="collapsed" desc="Getter and Setter methods. Click on the + sign on the left to edit the code.">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public double getTotal_duration() {
        return total_duration;
    }

    public void setTotal_duration(double total_duration) {
        this.total_duration = total_duration;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getNumberOfLesson() {
        return numberOfLesson;
    }

    public void setNumberOfLesson(int numberOfLesson) {
        this.numberOfLesson = numberOfLesson;
    }

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

    // </editor-fold>
    
    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", title=" + title + ", account=" + account + ", createdDate=" + createdDate + ", thumbnailUrl=" + thumbnailUrl + ", numberOfLesson=" + numberOfLesson + '}';
    }

}
