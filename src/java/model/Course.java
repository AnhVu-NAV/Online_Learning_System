/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

<<<<<<< HEAD
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
=======
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

    

>>>>>>> fbf38e88c7423732c03f084d6c211a2ea03af5a2
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

<<<<<<< HEAD
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
=======
    public int getExpertId() {
        return expertId;
    }

    public void setExpertId(int expertId) {
        this.expertId = expertId;
>>>>>>> fbf38e88c7423732c03f084d6c211a2ea03af5a2
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

<<<<<<< HEAD
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

=======
>>>>>>> fbf38e88c7423732c03f084d6c211a2ea03af5a2
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

<<<<<<< HEAD
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

=======
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


    
>>>>>>> fbf38e88c7423732c03f084d6c211a2ea03af5a2
}
