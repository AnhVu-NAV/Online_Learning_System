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
 * @author AnhVuNAV
 */
public class Account {

    private int id;
    private String email;
    private Date dob;
    private String first_name;
    private String last_name;
    private String phone;
    private String password;
    private Setting role_id;
    private boolean gender;
    private Date createdDate;
    private int status;
    private String image_url;
    private Vector<UserLog> userLog;
    private Vector<Blog> blog;
    private Vector<Slider> slider;
    private Setting setting;
    private String address;

    public Account() {
    }

    public Account(String email, Date dob, String firstName, String lastName, String phoneNumber, String password, Setting role_id, boolean gender, int status, String image_url) {
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

    public Account(int id, String email, String phoneNumber, String password, Setting role_id) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = getEncodingPassword(password);
        this.role_id = role_id;
        this.status = status;
        this.image_url = image_url;
    }

    //constructor dung de insert 1 account vao co so du lieu 
    public Account(String email, String phoneNumber, String password, Setting role_id) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = getEncodingPassword(password);
        setCreatedDate();
        setRoleId(role_id);
        this.image_url = "";
        this.status = 0;
    }

    // <editor-fold defaultstate="collapsed" desc="Getter and Setter methods. Click on the + sign on the left to edit the code.">
    public Account(int id, String email, String first_name, String last_name, String password, Date dob, int role_id, Date created_date, int status, String phone, boolean gender, String address, String image_url) {
        this.id = id;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.dob = dob;
        this.role_id = role_id;
        this.created_date = created_date;
        this.status = status;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
        this.image_url = image_url;
    }

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

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Setting getRole_id() {
        return role_id;
    }

    public void setRole_id(Setting role_id) {
        this.role_id = role_id;
    }

    public boolean isGender() {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    
}
