/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.util.List;
import lombok.ToString;

/**
 *
 * @author AnhVuNAV
 */
@ToString
public class Course {

    private int id;
    private String title;
    private String subtitle; // Thêm thuộc tính subtitle
    private int expertId;
    private String description;
    private List<String> thumbnailUrl; // New field for storing multiple thumbnail URLs
    private int numberOfLearner;
    private int status;
    private int numberOfLesson;
    private int price;
    private int salePrice;
    private Date createdDate;
    private Date updatedDate;
    private int categoryId;
    private float totalDuration;
    private String categoryName;
    private String ownerName;
    private List<String> taglines; // Thêm thuộc tính để lưu danh sách tagline
    private List<String> thumbnailDescriptions;

    public Course() {
    }

    public Course(int id, String title, String subtitle, int expertId, String description, List<String> thumbnailUrl, int numberOfLearner, int status, int price, int salePrice, Date createdDate, Date updatedDate, int categoryId, float totalDuration, String categoryName, String ownerName, List<String> taglines) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.expertId = expertId;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.numberOfLearner = numberOfLearner;
        this.status = status;
        this.price = price;
        this.salePrice = salePrice;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.categoryId = categoryId;
        this.totalDuration = totalDuration;
        this.categoryName = categoryName;
        this.ownerName = ownerName;
        this.taglines = taglines;
    }

    public Course(int id, String title, String subtitle, int expertId, int categoryId, int duration, String description) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.expertId = expertId;
        this.description = description;
        this.categoryId = categoryId;
    }

    public Course(String title, String subtitle, int expertId, int categoryId, int duration, String description) {
        this.title = title;
        this.subtitle = subtitle;
        this.expertId = expertId;
        this.description = description;
        this.categoryId = categoryId;
    }

    public Course(int aInt, String string, String string0, int aInt0, int aInt1, String description, int aInt2) {
        this.id = aInt;
        this.title = string;
        this.subtitle = string0;
        this.expertId = aInt1;
        this.description = description;
        this.status = aInt2;
        this.categoryId = aInt0;
    }

    public Course(String title, String subtitle, int categoryId, int ownerId, String description, int status) {
        this.title = title;
        this.subtitle = subtitle;
        this.expertId = ownerId;
        this.description = description;
        this.status = status;
        this.categoryId = categoryId;
    }

    public Course(int id, String title, String subtitle, int expertId, int totalDuration, int categoryId, String description, int status, java.util.Date updatedDate, java.util.Date createdDate, int numberOfLearner) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.expertId = expertId;
        this.description = description;
        this.numberOfLearner = numberOfLearner;
        this.status = status;
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

    public List<String> getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(List<String> thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public List<String> getThumbnailDescriptions() {
        return thumbnailDescriptions;
    }

    public void setThumbnailDescriptions(List<String> thumbnailDescriptions) {
        this.thumbnailDescriptions = thumbnailDescriptions;
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

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public List<String> getThumbnailUrls() {
        return thumbnailUrl;
    }

    public void setThumbnailUrls(List<String> thumbnailUrls) {
        this.thumbnailUrl = thumbnailUrls;
    }

}
