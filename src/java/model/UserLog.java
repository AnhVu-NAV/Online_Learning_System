/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
<<<<<<< HEAD
 * @author ADMIN
 */
public class UserLog {

    private int id;
    private Date createdDate;
    private Account account;
    private Setting setting;
=======
 * @author 84941
 */
public class UserLog {
    private int id;
    private int account_id;
    private Date created_date;
    private int type_id;
>>>>>>> fbf38e88c7423732c03f084d6c211a2ea03af5a2

    public UserLog() {
    }

<<<<<<< HEAD
    public UserLog(int id, Date createdDate, Account account, Setting setting) {
        this.id = id;
        this.createdDate = createdDate;
        this.account = account;
        this.setting = setting;
    }

    // <editor-fold defaultstate="collapsed" desc="Getter and Setter methods. Click on the + sign on the left to edit the code.">
=======
    public UserLog(int id, int account_id, Date created_date, int type_id) {
        this.id = id;
        this.account_id = account_id;
        this.created_date = created_date;
        this.type_id = type_id;
    }

>>>>>>> fbf38e88c7423732c03f084d6c211a2ea03af5a2
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

<<<<<<< HEAD
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

=======
    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }
    
    
>>>>>>> fbf38e88c7423732c03f084d6c211a2ea03af5a2
}
