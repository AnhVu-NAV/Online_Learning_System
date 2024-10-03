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
    private String email;
    private String first_name;
    private String last_name;
    private String password;
    private Date dob;
    private int role_id;
    private Date created_date;
    private int status;
    private String phone;
    private int gender;
    private String address;
    private String image_url;
    //Add attribute role from setting
    private Setting role = new Setting();

    public User(String email, String first_name, String last_name, String password,
            Date dob, int role_id, Date created_date, int status, String phone, int gender, String address, String image_url) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.dob = dob;
        this.role_id = role_id;
        this.created_date = created_date;
        this.status = status;
        this.phone = phone;
        this.gender = this.gender;
        this.address = address;
        this.image_url = image_url;
    }

    public User(int id, String email, String first_name, String last_name, String password,
            Date dob, int role_id, Date created_date, int status, String phone, int gender, String address, String image_url) {
        this.id = id;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.dob = dob;
        this.role_id = role_id;
        this.created_date = created_date;
        this.status = status;
        this.phone = phone;
        this.gender = this.gender;
        this.address = address;

}
