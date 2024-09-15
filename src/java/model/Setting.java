/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author 84941
 */
public class Setting {
    private int id;
    private int setting_type_id;
    private String value;
    private String description;
    private int status;
    private Date created_date;
    private Date updated_date;

    public Setting() {
    }

    public Setting(int id, int setting_type_id, String value, String description, int status, Date created_date, Date updated_date) {
        this.id = id;
        this.setting_type_id = setting_type_id;
        this.value = value;
        this.description = description;
        this.status = status;
        this.created_date = created_date;
        this.updated_date = updated_date;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSetting_type_id() {
        return setting_type_id;
    }

    public void setSetting_type_id(int setting_type_id) {
        this.setting_type_id = setting_type_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(Date updated_date) {
        this.updated_date = updated_date;
    }
    
    
    
    
    
    
}
