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
    private String thumbnailUrl;
    private String category;
    private String briefInfo;

    public Blog(int blogId, int authorId, String title, String content, Timestamp createdAt, Timestamp updatedAt) {
        super(blogId, authorId, title, content, createdAt, updatedAt);
    }

}
