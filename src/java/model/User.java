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
    private String phone;
    private String password;
    private int roleId;
    private boolean gender;
    private Date createdDate;
    private int status;
    private String imageURL;
    private String address;

    public User() {
    }

    public User(String email, Date dob, String firstName, String lastName, String phoneNumber, String password, int role_id, boolean gender, int status, String image_url) {
        this.email = email;
        this.dob = dob;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phoneNumber;
        this.password = getEncodingPassword(password);
        this.roleId = role_id;
        this.gender = gender;
        setCreatedDate();
        this.status = status;
        this.imageURL = image_url;
    }

    public User(int id, String email, String phoneNumber, String password, int role_id) {
        this.email = email;
        this.phone = phoneNumber;
        this.password = getEncodingPassword(password);
        this.roleId = role_id;
        this.status = 0;
//        this.imageURL = imageURL;
    }

    //constructor dung de insert 1 account vao co so du lieu 
    public User(String email, String phoneNumber, String password, int role_id) {
        this.email = email;
        this.phone = phoneNumber;
        this.password = getEncodingPassword(password);
        setCreatedDate();
        setRoleId(role_id);
        this.imageURL = "";
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
        return phone;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phone = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String image_url) {
        this.imageURL = image_url;
    }
    // </editor-fold>

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEncodingPassword(String password) {
        return PasswordEncoding.getEncodingPassword(password);
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", email=" + email + ", dob=" + dob + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phone + ", password=" + password + ", role=" + roleId + ", gender=" + gender + ", createdDate=" + createdDate + ", status=" + status + '}' + ", image_url=" + imageURL;
    }

}
