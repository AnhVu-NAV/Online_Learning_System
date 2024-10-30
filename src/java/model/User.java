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
public class User {
    private int id;
    private String email;
    private String firstName; // first_name
    private String lastName; // last_name
    private String password;
    private Date dob; // Ngày sinh
    private int roleId; // ID vai trò (Foreign key từ Setting)
    private Date createdDate; // Ngày tạo tài khoản
    private int status; // Trạng thái tài khoản (Active, Inactive, etc.)
    private String phone; // Số điện thoại
    private boolean gender; // Giới tính (true: Nam, false: Nữ)
    private String address; // Địa chỉ
    private String imageURL; // URL ảnh đại diện

    public User(int id, String email, String firstName, String lastName, String password, Date dob, int roleId, Date createdDate, int status, String phone, boolean gender, String address, String imageURL) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.dob = dob;
        this.roleId = roleId;
        this.createdDate = createdDate;
        this.status = status;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
        this.imageURL = imageURL;
    }

    public User() {
    }
    
    

    
    public User(int id, String firstName, String lastName, String string1, String string2) {
        this.id = id;
        this.email = string1;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageURL = string2;
    }

    // Getters và Setters cho tất cả các thuộc tính

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    
}
