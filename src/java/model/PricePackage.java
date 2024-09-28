/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class PricePackage {

    private int pricePackageId;
    private int courseId;
    private String title;
    private int price;
    private int salePrice;
    private int duration;

    public PricePackage() {
    }

    public PricePackage(int pricePackageId, int courseId, String title, int price, int salePrice, int duration) {
        this.pricePackageId = pricePackageId;
        this.courseId = courseId;
        this.title = title;
        this.price = price;
        this.salePrice = salePrice;
        this.duration = duration;
    }

    // <editor-fold defaultstate="collapsed" desc="Getter and Setter methods. Click on the + sign on the left to edit the code.">
    public int getPricePackageId() {
        return pricePackageId;
    }

    public void setPricePackageId(int pricePackageId) {
        this.pricePackageId = pricePackageId;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    // </editor-fold>    
    @Override
    public String toString() {
        return "PricePackage{" + "pricePackageId=" + pricePackageId + ", courseId=" + courseId + ", title=" + title + ", price=" + price + ", salePrice=" + salePrice + ", duration=" + duration + '}';
    }

}
