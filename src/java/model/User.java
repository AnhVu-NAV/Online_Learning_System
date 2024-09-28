/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.util.Vector;
import util.GetCurrentDate;
import util.PasswordEncoding;

/**
 *
 * @author ADMIN
 */
public class User {

    private int id;
    private String email;
    private Date dob;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private int role_id;
    private boolean gender;
    private Date createdDate;
    private int status;
    private String image_url;

    public User() {
    }

    public User(String email, Date dob, String firstName, String lastName, String phoneNumber, String password, int role_id, boolean gender, int status, String image_url) {
        this.email = email;
        this.dob = dob;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = getEncodingPassword(password);
        this.role_id = role_id;
        this.gender = gender;
        setCreatedDate();
        this.status = status;
        this.image_url = image_url;
    }

    public User(int id, String email, String phoneNumber, String password, int role_id) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = getEncodingPassword(password);
        this.role_id = role_id;
        this.status = 0;
//        this.image_url = image_url;
    }

    //constructor dung de insert 1 account vao co so du lieu 
    public User(String email, String phoneNumber, String password, int role_id) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = getEncodingPassword(password);
        setCreatedDate();
        setRoleId(role_id);
        this.image_url = "";
        this.status = 0;
    }

    // <editor-fold defaultstate="collapsed" desc="Getter and Setter methods. Click on the + sign on the left to edit the code.">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return role_id;
    }

    public void setRoleId(int roleId) {
        this.role_id = roleId;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setCreatedDate() {
        this.createdDate = GetCurrentDate.getCurrentDate();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int isActivated) {
        this.status = isActivated;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
    // </editor-fold>

    public String getEncodingPassword(String password) {
        return PasswordEncoding.getEncodingPassword(password);
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", email=" + email + ", dob=" + dob + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", password=" + password + ", role=" + role_id + ", gender=" + gender + ", createdDate=" + createdDate + ", status=" + status + '}' + ", image_url=" + image_url;
    }

}
