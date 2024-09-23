/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author mocun
 */
public class Blogs {

    private int blogId;
    private int authorId;
    private String title;
    private String content;
    private String thumbnailUrl;
    private String briefInfo;
    private String category;
    private Date createdAt;
    private Date updatedAt;

    // Constructor
    public Blogs(int blogId, int authorId, String title, String content, String thumbnailUrl, String briefInfo, String category, Date createdAt, Date updatedAt) {
        this.blogId = blogId;
        this.authorId = authorId;
        this.title = title;
        this.content = content;
        this.thumbnailUrl = thumbnailUrl;
        this.briefInfo = briefInfo;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Blogs() {

    }

    // Getters and Setters
    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Getter và Setter cho thumbnailUrl
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getBriefInfo() {
        return briefInfo;
    }

    public void setBriefInfo(String briefInfo) {
        this.briefInfo = briefInfo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // toString method
    @Override
    public String toString() {
        return "Blogs{"
                + "blogId=" + blogId
                + ", authorId=" + authorId
                + ", title='" + title + '\''
                + ", content='" + content + '\''
                + ", thumbnailUrl='" + thumbnailUrl + '\''
                + ", briefInfo='" + briefInfo + '\''
                + ", category='" + category + '\''
                + ", createdAt=" + createdAt
                + ", updatedAt=" + updatedAt
                + '}';
    }
}
