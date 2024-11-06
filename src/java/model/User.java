/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author AnhVuNAV
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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
    private int gender;
    private String firstPhone;
    private String secondPhone;
    private String secondaryEmail;
    private String imageUrl;
    private String preferContact;
    private String address;
    private String imageURL;

    //Add attribute role from setting
    private Setting role = new Setting();

    public User() {
    }

    public User(int id, String primaryEmail, String password, int roleId, Date createdDate, int status, String firstName, String lastName, Date dob, int gender, String firstPhone, String secondPhone, String secondaryEmail, String imageURL, String preferContact) {
        this.id = id;
        this.primaryEmail = primaryEmail;
        this.password = password;
        this.roleId = roleId;
        this.createdDate = createdDate;
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.firstPhone = firstPhone;
        this.secondPhone = secondPhone;
        this.secondaryEmail = secondaryEmail;
        this.imageURL = imageURL;
        this.preferContact = preferContact;
    }

    public User(int id, String primaryEmail, String password, int roleId, Date createdDate, int status, String firstName, String lastName, Date dob, int gender, String firstPhone, String secondPhone, String secondaryEmail, String imageUrl, String preferContact, String address) {
        this.id = id;
        this.primaryEmail = primaryEmail;
        this.password = password;
        this.roleId = roleId;
        this.createdDate = createdDate;
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.firstPhone = firstPhone;
        this.secondPhone = secondPhone;
        this.secondaryEmail = secondaryEmail;
        this.imageUrl = imageUrl;
        this.preferContact = preferContact;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getPassword() {
        return password;
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPreferContact() {
        return preferContact;
    }

    public void setPreferContact(String preferContact) {
        this.preferContact = preferContact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
