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
//    private String imageURL;

    //Add attribute role from setting
//    private Setting role = new Setting();

    
//    public User(int id, String primaryEmail, String password, int roleId, Date createdDate, int status, String firstName, String lastName, Date dob, int gender, String firstPhone, String secondPhone, String secondaryEmail, String imageURL, String preferContact) {
//        this.id = id;
//        this.primaryEmail = primaryEmail;
//        this.password = password;
//        this.roleId = roleId;
//        this.createdDate = createdDate;
//        this.status = status;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.dob = dob;
//        this.gender = gender;
//        this.firstPhone = firstPhone;
//        this.secondPhone = secondPhone;
//        this.secondaryEmail = secondaryEmail;
//        this.imageURL = imageURL;
//        this.preferContact = preferContact;
//    }

//    public User(int id, String primaryEmail, String password, int roleId, Date createdDate, int status, String firstName, String lastName, Date dob, int gender, String firstPhone, String secondPhone, String secondaryEmail, String imageUrl, String preferContact, String address) {
//        this.id = id;
//        this.primaryEmail = primaryEmail;
//        this.password = password;
//        this.roleId = roleId;
//        this.createdDate = createdDate;
//        this.status = status;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.dob = dob;
//        this.gender = gender;
//        this.firstPhone = firstPhone;
//        this.secondPhone = secondPhone;
//        this.secondaryEmail = secondaryEmail;
//        this.imageUrl = imageUrl;
//        this.preferContact = preferContact;
//        this.address = address;
//    }

    public User(int id, String primaryEmail, String password, int roleId, Date createdDate, int status, String firstName, String lastName, Date dob, int gender, String firstPhone, String secondPhone, String secondaryEmail, String imageUrL, String preferContact) {
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
//        this.imageUrl = imageUrl;
        this.preferContact = preferContact;
//        this.address = address;
    }
    
    public String getImageURL(){
        return imageUrl;
    }
    
    public String getImageUrl(){
        return imageUrl;
    }

//    public boolean isGender() {
//        if (gender == 2) {
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    public int Gender(boolean gender) {
//        if (gender == false) {
//            return 2;
//        } else {
//            return 1;
//        }
//    }

public User(String primaryEmail, String password, Date createdDate, 
        String firstName, String lastName, Date dob, int gender, String firstPhone, String address) {
        this.primaryEmail = primaryEmail;
        this.password = password;
        this.roleId = 4; // default role
        this.createdDate = createdDate;
        this.status = 1; // active status
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.firstPhone = firstPhone;
        this.address = address;
    }

}
