/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class Customer {
    int CustomerID;
    String FullName;
    String Email;
    String Password;
    String Username;
    String PhoneNumber;
    int RoleID;
    String avatar_url;

    public Customer() {
    }

    public Customer(int CustomerID, String FullName, String Email, String Password, String Username, String PhoneNumber, int RoleID, String avatar_url) {
        this.CustomerID = CustomerID;
        this.FullName = FullName;
        this.Email = Email;
        this.Password = Password;
        this.Username = Username;
        this.PhoneNumber = PhoneNumber;
        this.RoleID = RoleID;
        this.avatar_url = avatar_url;
    }

    public int getUserID() {
        return CustomerID;
    }

    public void setUserID(int UserID) {
        this.CustomerID = UserID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int RoleID) {
        this.RoleID = RoleID;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
    
    
}
