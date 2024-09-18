/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.util.Vector;
import util.PasswordEncoding;

/**
 *
 * @author ADMIN
 */
public class Account {

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
    private Vector<UserLog> userLog;
    private Vector<Blog> blog;
    private Vector<Slider> slider;
    private Setting setting;

    public Account() {
    }
    
    public Account(String email, Date dob, String firstName, String lastName, String phoneNumber, String password, int role_id, boolean gender, int status, String image_url) {
        this.email = email;
        this.dob = dob;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = getEncodingPassword(password);  
        this.role_id = role_id;
        this.gender = gender;
        this.createdDate = setCreatedDateForNewAccount();
        this.status = status;
        this.image_url = image_url;
    }
    
    //constructor dung de insert 1 account vao co so du lieu 
    public Account(String email, String phoneNumber, String password, int role_id) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = getEncodingPassword(password);  
        this.createdDate = setCreatedDateForNewAccount(); 
        this.role_id = role_id;
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

    public void setRoleId(int role) {
        this.role_id = role;
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

    public Date setCreatedDateForNewAccount() {
        Date date = new Date(System.currentTimeMillis());
        return date;
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

    public Vector<UserLog> getUserLog() {
        return userLog;
    }

    public void setUserLog(Vector<UserLog> userLog) {
        this.userLog = userLog;
    }

    public Vector<Blog> getBlog() {
        return blog;
    }

    public void setBlog(Vector<Blog> blog) {
        this.blog = blog;
    }

    public Vector<Slider> getSlider() {
        return slider;
    }

    public void setSlider(Vector<Slider> slider) {
        this.slider = slider;
    }

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
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
