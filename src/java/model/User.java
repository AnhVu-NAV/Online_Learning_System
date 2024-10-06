/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import util.GetCurrentDate;
import util.PasswordEncoding;

/**
 *
 * @author ADMIN
 */
public class User {

    private int id;
    private String primaryEmail;
    private String password;
    private int roleId;
    private Date createdDate;
    private int status;
    private String firstName;
    private String lastName;
    private Date dob;
    private boolean gender;
    private String firstPhone;
    private String secondPhone;
    private String secondaryEmail;
    private String imageURL;
    private String preferContact;

    public User() {
    }

    public User(int id, String primaryEmail, String password, int status) {
        this.id = id;
        this.primaryEmail = primaryEmail;
        this.password = getEncodingPassword(password);
        this.status = status;
    }

    //use for create account
    public User(String primaryEmail, String password, int status, String firstPhone) {
        this.primaryEmail = primaryEmail;
        this.password = getEncodingPassword(password);
        this.status = status;
        this.firstPhone = firstPhone;
        setCreatedDate();
    }

    // <editor-fold defaultstate="collapsed" desc="Getter and Setter methods. Click on the + sign on the left to edit the code.">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryKey) {
        this.primaryEmail = primaryKey;
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

    public String getFirstPhone() {
        return firstPhone;
    }

    public void setFirstPhone(String firstPhone) {
        this.firstPhone = firstPhone;
    }

    public String getSecondPhone() {
        return secondPhone;
    }

    public void setSecondPhone(String secondPhone) {
        this.secondPhone = secondPhone;
    }

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getPreferContact() {
        return preferContact;
    }

    public void setPreferContact(String preferContact) {
        this.preferContact = preferContact;
    }    // </editor-fold>

    public String getEncodingPassword(String password) {
        return PasswordEncoding.getEncodingPassword(password);
    }

    public void setCreatedDate() {
        this.createdDate = GetCurrentDate.getCurrentDate();
    }

    @Override
    public String toString() {
        return "User1{" + "id=" + id + ", primaryEmail=" + primaryEmail + ", password=" + password + ", roleId=" + roleId + ", createdDate=" + createdDate + ", status=" + status + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + ", gender=" + gender + ", firstPhone=" + firstPhone + ", secondPhone=" + secondPhone + ", secondaryEmail=" + secondaryEmail + ", imageURL=" + imageURL + ", preferContact=" + preferContact + '}';
    }

}
