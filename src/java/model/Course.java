/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author AnhVuNAV
 */
public class Course {

    private int id;
    private String title;
    private String subtitle; // Thêm thuộc tính subtitle
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
    private List<String> taglines; // Thêm thuộc tính để lưu danh sách tagline
    private int status;
    private int numberOfLearner;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNumberOfLearner() {
        return numberOfLearner;
    }

    public void setNumberOfLearner(int numberOfLearner) {
        this.numberOfLearner = numberOfLearner;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Course{");
        sb.append("id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", subtitle=").append(subtitle);
        sb.append(", expertId=").append(expertId);
        sb.append(", description=").append(description);
        sb.append(", thumbnailUrl=").append(thumbnailUrl);
        sb.append(", numberOfLesson=").append(numberOfLesson);
        sb.append(", price=").append(price);
        sb.append(", salePrice=").append(salePrice);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", totalDuration=").append(totalDuration);
        sb.append(", taglines=").append(taglines);
        sb.append(", status=").append(status);
        sb.append(", numberOfLearner=").append(numberOfLearner);
        sb.append('}');
        return sb.toString();
    }

}
