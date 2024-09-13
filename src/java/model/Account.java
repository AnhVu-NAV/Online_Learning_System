/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author ADMIN
 */
public class Account {
    //demo chuc nang, chua phai ban thiet ke cuoi cung 
    private String email;
    private String name;
    private LocalDate dob;
    private String address;
    private String phoneNumber;
    private String password;

    public Account() {
    }

    public Account(String email, String name, LocalDate dob, String address, String phoneNumber, String password) {
        this.email = email;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" + "email=" + email + ", name=" + name + ", dob=" + dob + ", address=" + address + ", phoneNumber=" + phoneNumber + ", password=" + password + '}';
    }

}
