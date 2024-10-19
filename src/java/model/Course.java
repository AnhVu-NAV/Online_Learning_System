/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Course {
    private int id;
    private String title;
    private String subtitle; // Thêm thuộc tính subtitle
    private int expertId;
    private int totalDuration;
    private int categoryId;
    private String description;
    private String thumbnailUrl;
    private int status;
    private Date updatedDate;
    private Date createdDate;
    private int numberOfLearners;
    private List<String> taglines; // Thêm thuộc tính để lưu danh sách tagline

    // Constructors
    public Course() {}

    public Course(int id, String title, String subtitle, int expertId, int totalDuration, int categoryId, String description, String thumbnailUrl, int status, Date updatedDate, Date createdDate, int numberOfLearners) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.expertId = expertId;
        this.totalDuration = totalDuration;
        this.categoryId = categoryId;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.status = status;
        this.updatedDate = updatedDate;
    
    
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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSalePrice() {
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
        this.numberOfLearners = numberOfLearners;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public float getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(float totalDuration) {
        this.totalDuration = totalDuration;
    }

    public List<String> getTaglines() {
        return taglines;
    }

    public void setTaglines(List<String> taglines) {
        this.taglines = taglines;
    }
    
    

}
