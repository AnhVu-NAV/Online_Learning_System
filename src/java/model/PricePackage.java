/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author AnhVuNAV
 */
public class PricePackage {
    private int price;
    private int salePrice;

    public PricePackage() {
    }

    public PricePackage(int price, int salePrice) {
        this.price = price;
        this.salePrice = salePrice;
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
    
    
}
