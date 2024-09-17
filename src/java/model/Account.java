/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.Date;

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
    private LocalDate createdDate;
    private int status;

    public Account() {
    }

    public Account(int id, String email, Date dob, String firstName, String lastName, String phoneNumber, String password, int role_id, boolean gender, LocalDate createdDate, int status) {
        this.id = id;
        this.email = email;
        this.dob = dob;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role_id = role_id;
        this.gender = gender;
        this.createdDate = createdDate;
        this.status = status;
    }
    
    public Account(String email, String phoneNumber, String password) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        setCreatedDateForNewAccount();
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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public void setCreatedDateForNewAccount() {
        LocalDate date = LocalDate.now();
        this.createdDate = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int isActivated) {
        this.status = isActivated;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", email=" + email + ", dob=" + dob + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", password=" + password + ", role=" + role_id + ", gender=" + gender + ", createdDate=" + createdDate + ", status=" + status + '}';
    }

}
