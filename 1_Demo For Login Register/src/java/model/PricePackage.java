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

    private int id;
    private Course course;
    private String title;
    private int price;
    private int salePrice;
    private int duration;

    public PricePackage() {
    }

    public PricePackage(int id, Course course, String title, int price, int salePrice, int duration) {
        this.id = id;
        this.course = course;
        this.title = title;
        this.price = price;
        this.salePrice = salePrice;
        this.duration = duration;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Getter and Setter methods. Click on the + sign on the left to edit the code.">

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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
        return "PricePackage{" + "id=" + id + ", title=" + title + ", price=" + price + ", salePrice=" + salePrice + ", duration=" + duration + '}';
    }
    
}
