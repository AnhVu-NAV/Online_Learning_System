/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author AnhVuNAV
 */
public class Course {
    private int id;
    private String title;
    private int expertId;
    private String description;
    private String thumbnailUrl;
    private int numberOfLesson;
    private int price;
    private int salePrice;
    private Date createdDate;
    private Date updatedDate;
    private int categoryId; 
    private float totalDuration;
    
    public Course() {
    }

    public Course(int id, String title, int expertId, String description, String thumbnailUrl, int numberOfLesson, int price, int salePrice, Date createdDate, Date updatedDate, int categoryId, float totalDuration) {
        this.id = id;
        this.title = title;
        this.expertId = expertId;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.numberOfLesson = numberOfLesson;
        this.price = price;
        this.salePrice = salePrice;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.categoryId = categoryId;
        this.totalDuration = totalDuration;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public float getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(float totalDuration) {
        this.totalDuration = totalDuration;
    }

    

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

    public int getExpertId() {
        return expertId;
    }

    public void setExpertId(int expertId) {
        this.expertId = expertId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
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


    
}
