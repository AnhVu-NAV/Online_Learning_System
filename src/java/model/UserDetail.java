/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class UserDetail {

    private int userId;
    private String firstName;
    private String lastName;
    private Date dob;
    private boolean gender; //true for male, false for female 
    private String phone;
    private String imageURL;
    private String secondaryEmail;
    private String preferContact;

    public UserDetail() {
    }

    public UserDetail(int userId, String firstName, String lastName, boolean gender, String phone, String imageURL, String secondaryEmail, String preferContact) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.phone = phone;
        this.imageURL = imageURL;
        this.secondaryEmail = secondaryEmail;
        this.preferContact = preferContact;
    }

    public UserDetail(int userId, String phone) {
        this.userId = userId;
        this.phone = phone;
    }

    // <editor-fold defaultstate="collapsed" desc="Getter and Setter methods. Click on the + sign on the left to edit the code.">
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    public String getPreferContact() {
        return preferContact;
    }

    public void setPreferContact(String preferContact) {
        this.preferContact = preferContact;
    }
    // </editor-fold>

    @Override
    public String toString() {
        return "UserDetail{" + "userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + ", gender=" + gender + ", phone=" + phone + ", imageURL=" + imageURL + ", secondaryEmail=" + secondaryEmail + ", preferContact=" + preferContact + '}';
    }

}
