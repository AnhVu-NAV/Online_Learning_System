/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author AnhVuNAV
 */
public class PricePackage {
    private int id;
    private String title;
    private int price;
    private int salePrice;
    private int courseId;
    private Date saleStartDate;
    private Date saleEndDate;

    public PricePackage() {
    }

    public PricePackage(int id, String title, int price, int salePrice) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.salePrice = salePrice;
    }
    
    public PricePackage(int id, int courseId, String title, int price, int salePrice, Date saleStartDate, Date saleEndDate) {
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.price = price;
        this.salePrice = salePrice;
        this.saleStartDate = saleStartDate;
        this.saleEndDate = saleEndDate;
    }
   


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Date getSaleStartDate() {
        return saleStartDate;
    }

    public void setSaleStartDate(Date saleStartDate) {
        this.saleStartDate = saleStartDate;
    }

    public Date getSaleEndDate() {
        return saleEndDate;
    }

    public void setSaleEndDate(Date saleEndDate) {
        this.saleEndDate = saleEndDate;
    }
    
}
