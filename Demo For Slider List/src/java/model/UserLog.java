/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class UserLog {

    private int id;
    private Date createdDate;
    private Account account;
    private Setting setting;

    public UserLog() {
    }

    public UserLog(int id, Date createdDate, Account account, Setting setting) {
        this.id = id;
        this.createdDate = createdDate;
        this.account = account;
        this.setting = setting;
    }

    // <editor-fold defaultstate="collapsed" desc="Getter and Setter methods. Click on the + sign on the left to edit the code.">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }
    // </editor-fold>    

    @Override
    public String toString() {
        return "UserLog{" + "id=" + id + ", createdDate=" + createdDate + '}';
    }

}
