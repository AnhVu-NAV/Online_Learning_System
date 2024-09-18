/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author mocun
 */
public class Blog extends Blogs {

    private int commentsCount;
    private String authorName;

    // Constructor không tham số
    public Blog() {
        super();  // Gọi constructor của lớp cơ sở Blogs
    }

    // Constructor có tham số
    public Blog(int blogId, int authorId, String title, String content, String thumbnailUrl,
            String category, String briefInfo, Timestamp createdAt, Timestamp updatedAt,
            int commentsCount, String authorName) {
        super(blogId, authorId, title, content, thumbnailUrl, category, briefInfo, createdAt, updatedAt);
        this.commentsCount = commentsCount;
        this.authorName = authorName;
    }

    // Getters và setters
    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
