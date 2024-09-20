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
public class Blog {
    private int id;
    private Date createdDate;
    private Date updatedDate;
    private Setting category_id;
    private String thumbnailUrl;
    private String title;
    private String briefInfo;
    private Account author_id;
    private String blogDetail;
    private int status;

    public Blog() {
    }

    public Blog(int id, Date createdDate, Date updatedDate, Setting category_id, String thumbnailUrl, String title, String briefInfo, Account account, String blogDetail, int status) {
        this.id = id;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.category_id = category_id;
        this.thumbnailUrl = thumbnailUrl;
        this.title = title;
        this.briefInfo = briefInfo;
        this.author_id = account;
        this.blogDetail = blogDetail;
        this.status = status;
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

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Setting getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Setting category_id) {
        this.category_id = category_id;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBriefInfo() {
        return briefInfo;
    }

    public void setBriefInfo(String briefInfo) {
        this.briefInfo = briefInfo;
    }

    public Account getAccount() {
        return author_id;
    }

    public void setAccount(Account account) {
        this.author_id = account;
    }

    public String getBlogDetail() {
        return blogDetail;
    }

    public void setBlogDetail(String blogDetail) {
        this.blogDetail = blogDetail;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    // </editor-fold>    

    @Override
    public String toString() {
        return "Blog{" + "createdDate=" + createdDate + ", thumbnailUrl=" + thumbnailUrl + ", title=" + title + ", briefInfo=" + briefInfo + ", blogDetail=" + blogDetail + ", status=" + status + '}';
    }
    
}
