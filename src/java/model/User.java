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
    private int status;
    private int roleId;
    private Date createdDate;
    private String firstName;
    private String lastName;
    private Date dob;
    private boolean gender;
    private String firstPhone;
    private String secondPhone;
    private String secondaryEmail;
    private String imageURL;
    private String preferContact;
    private String address;

    //Add attribute role from setting
//    private Setting role = new Setting();
    public User(String primaryEmail, String password, int status, int roleId, Date createdDate,  String firstName, String lastName, 
            Date dob, boolean gender,
            String firstPhone, String secondPhone, String secondaryEmail, String imageURL, String preferContact, String address) {
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
        this.address = address;
    }

}
