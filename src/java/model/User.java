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
    private String primaryEmail;
    private String password;
    private int status;
    private Date createdDate;
    private Date updatedDate;

    public User() {
    }

    public User(int id, String primaryEmail, String password, int status) {
        this.id = id;
        this.primaryEmail = primaryEmail;
        this.password = getEncodingPassword(password);
        this.status = status;
    }

    //use for create account
    public User(String primaryEmail, String password, int status) {
        this.primaryEmail = primaryEmail;
        this.password = getEncodingPassword(password);
        this.status = status;
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

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate() {
        this.createdDate = GetCurrentDate.getCurrentDate();
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
    // </editor-fold>

    public String getEncodingPassword(String password) {
        return PasswordEncoding.getEncodingPassword(password);
    }

    @Override
    public String toString() {
        return "NewUser{" + "id=" + id + ", primaryEmail=" + primaryEmail + ", password=" + password + ", status=" + status + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + '}';
    }
}
