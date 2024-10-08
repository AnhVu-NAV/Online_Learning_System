/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Course {
    private int id;
    private String title;
    private String subtitle;
    private int expertId;
    private int totalDuration;
    private int categoryId;
    private String description;
    private String thumbnailUrl;
    private int status;
    private Date updatedDate;
    private Date createdDate;
    private int numberOfLearners;

    // Constructors
    public Course() {}

    public Course(int id, String title, String subtitle, int expertId, int totalDuration, int categoryId, String description, String thumbnailUrl, int status, Date updatedDate, Date createdDate, int numberOfLearners) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.expertId = expertId;
        this.totalDuration = totalDuration;
        this.categoryId = categoryId;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.status = status;
        this.updatedDate = updatedDate;
        this.createdDate = createdDate;
        this.numberOfLearners = numberOfLearners;
    }

   
}
