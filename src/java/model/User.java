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
    private boolean gender;
    private String firstPhone;
    private String secondPhone;
    private String secondaryEmail;
    private String imageURL;
    private String preferContact;
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

    //Add attribute role from setting
    private Setting role = new Setting();

    public User() {
    }

    public User(int id, String primaryEmail, String password, int roleId, Date createdDate, int status, String firstName, String lastName, Date dob, boolean gender, String firstPhone, String secondPhone, String secondaryEmail, String imageURL, String preferContact) {
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
    private int id;
    private String email;
    private String firstName; // first_name
    private String lastName; // last_name
    private String password;
    private Date dob; // Ngày sinh
    private int roleId; // ID vai trò (Foreign key từ Setting)
    private Date createdDate; // Ngày tạo tài khoản
    private int status; // Trạng thái tài khoản (Active, Inactive, etc.)
    private String phone; // Số điện thoại
    private boolean gender; // Giới tính (true: Nam, false: Nữ)
    private String address; // Địa chỉ
    private String imageURL; // URL ảnh đại diện

    // Getters và Setters cho tất cả các thuộc tính

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
    public String getEmail() {
        return email;
        }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword() {
        return password;

        this.password = password;
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
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
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
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getPreferContact() {
        return preferContact;
    }

    public void setPreferContact(String preferContact) {
        this.preferContact = preferContact;
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
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageURL() {
        return imageURL;
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
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
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
    
    
    public void setImage_url(String image_url) {
        this.image_url = image_url;
        }
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
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
    
    
}
