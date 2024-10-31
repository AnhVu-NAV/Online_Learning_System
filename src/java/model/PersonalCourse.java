/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author 84941
 */
public class PersonalCourse {

    private int id;
    private int customerId;
    private int courseId;
    private Date enrollDate;
    private Date expireDate;
    private int progress;
    private int status;
    private int pricePackageId;
    private int saleNoteId;
    private int price;

    public PersonalCourse() {
    }

    public PersonalCourse(int id, int customerId, int courseId, Date enrollDate, Date expireDate, int progress, int status, int pricePackageId, int saleNoteId, int price) {
        this.id = id;
        this.customerId = customerId;
        this.courseId = courseId;
        this.enrollDate = enrollDate;
        this.expireDate = expireDate;
        this.progress = progress;
        this.status = status;
        this.pricePackageId = pricePackageId;
        this.saleNoteId = saleNoteId;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPricePackageId() {
        return pricePackageId;
    }

    public void setPricePackageId(int pricePackageId) {
        this.pricePackageId = pricePackageId;
    }

    public int getSaleNoteId() {
        return saleNoteId;
    }

    public void setSaleNoteId(int saleNoteId) {
        this.saleNoteId = saleNoteId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    

}
