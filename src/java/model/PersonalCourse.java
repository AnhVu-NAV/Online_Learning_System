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

    private int id;
    private int customerId;
    private int courseId;
    private int status;
    private Date enrollDate;
    private Date expireDate;
    private int progress;
    private int pricePackageId;

    public PersonalCourse() {
    }

    public PersonalCourse(int id, int customerId, int courseId, int status, Date enrollDate, Date expireDate, int progress, int pricePackageId) {
        this.id = id;
        this.customerId = customerId;
        this.courseId = courseId;
        this.status = status;
        this.enrollDate = enrollDate;
        this.expireDate = expireDate;
        this.progress = progress;
        this.pricePackageId = pricePackageId;
    }

    // <editor-fold defaultstate="collapsed" desc="Getter and Setter methods. Click on the + sign on the left to edit the code.">
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPricePackageId() {
        return pricePackageId;
    }

    public void setPricePackageId(int pricePackageId) {
        this.pricePackageId = pricePackageId;
    }// </editor-fold>    

    @Override
    public String toString() {
        return "PersonalCourse{" + "id=" + id + ", customerId=" + customerId + ", courseId=" + courseId + ", status=" + status + ", enrollDate=" + enrollDate + ", expireDate=" + expireDate + ", progress=" + progress + ", pricePackageId=" + pricePackageId + '}';
    }

}
