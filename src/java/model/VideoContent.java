/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class VideoContent {
    private int lessonId;
    private String videoId;
    private String listId;
    private int index_vid;
    private String videoSummary;
    private String description;

    // Constructors
    public VideoContent() {}

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public int getIndex_vid() {
        return index_vid;
    }

    public void setIndex_vid(int index_vid) {
        this.index_vid = index_vid;
    }

    public String getVideoSummary() {
        return videoSummary;
    }

    public void setVideoSummary(String videoSummary) {
        this.videoSummary = videoSummary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}

