/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.*;

/**
 *
 * @author ADMIN
 */
public class Setting {

    private int id;
    private SettingType settingTypeId;
    private String value;
    private int status;
    private String description;
    private Date createdDate;
    private Date updatedDate;
    private Vector<Account> accounts;
    private Vector<Course> courses;
    private Vector<Blog> blogs;
    private Vector<UserLog> userLogs;

    public Setting() {
    }

    public Setting(int id, SettingType settingTypeId, String value, int status, String description, Date createdDate, Date updatedDate, Vector<Account> accounts, Vector<Course> courses, Vector<Blog> blogs, Vector<UserLog> userLogs) {
        this.id = id;
        this.settingTypeId = settingTypeId;
        this.value = value;
        this.status = status;
        this.description = description;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.accounts = accounts;
        this.courses = courses;
        this.blogs = blogs;
        this.userLogs = userLogs;
    }

    public Setting(SettingType settingTypeId, String value, int status) {
        this.settingTypeId = settingTypeId;
        this.value = value;
        this.status = status;
        this.createdDate = setCreatedDateForNewSetting();
        this.updatedDate = setCreatedDateForNewSetting();
    }

    // <editor-fold defaultstate="collapsed" desc="Getter and Setter methods. Click on the + sign on the left to edit the code.">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SettingType getSettingTypeId() {
        return settingTypeId;
    }

    public void setSettingTypeId(SettingType settingTypeId) {
        this.settingTypeId = settingTypeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public Vector<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Vector<Account> accounts) {
        this.accounts = accounts;
    }

    public Vector<Course> getCourses() {
        return courses;
    }

    public void setCourses(Vector<Course> courses) {
        this.courses = courses;
    }

    public Vector<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(Vector<Blog> blogs) {
        this.blogs = blogs;
    }

    public Vector<UserLog> getUserLogs() {
        return userLogs;
    }

    public void setUserLogs(Vector<UserLog> userLogs) {
        this.userLogs = userLogs;
    }
    // </editor-fold>    

    public Date setCreatedDateForNewSetting() {
        Date date = new Date(System.currentTimeMillis());
        return date;
    }

    @Override
    public String toString() {
        return "Setting{" + "id=" + id + "value=" + value + ", status=" + status + ", description=" + description + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + '}';
    }

}
