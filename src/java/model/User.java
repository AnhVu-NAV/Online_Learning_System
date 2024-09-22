/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author admin
 */
public class User {
    int CustomerID;
    String FirstName;
    String LastName;
    String Email;
    String Password;
    Date DoB;
    Date Created_Date;
    String PhoneNumber;
    int RoleID;
    int Status;
    int Gender;
    String Address;
    String image_url;

    public User(int CustomerID, String FirstName, String LastName, String Email, String Password, Date DoB, Date Created_Date, String PhoneNumber, int RoleID, int Status, int Gender, String Address, String image_url) {
        this.CustomerID = CustomerID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Email = Email;
        this.Password = Password;
        this.DoB = DoB;
        this.Created_Date = Created_Date;
        this.PhoneNumber = PhoneNumber;
        this.RoleID = RoleID;
        this.Status = Status;
        this.Gender = Gender;
        this.Address = Address;
        this.image_url = image_url;
    }

    public User() {
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public Date getDoB() {
        return DoB;
    }

    public Date getCreated_Date() {
        return Created_Date;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public int getRoleID() {
        return RoleID;
    }

    public int getStatus() {
        return Status;
    }

    public int getGender() {
        return Gender;
    }

    public String getAddress() {
        return Address;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setDoB(Date DoB) {
        this.DoB = DoB;
    }

    public void setCreated_Date(Date Created_Date) {
        this.Created_Date = Created_Date;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public void setRoleID(int RoleID) {
        this.RoleID = RoleID;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public void setGender(int Gender) {
        this.Gender = Gender;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
    
    

    
    
    
}
