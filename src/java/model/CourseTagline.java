/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 84941
 */
public class CourseTagline {

    private int taglineId;
    private int courseId;

    public CourseTagline() {
    }

    public CourseTagline(int taglineId, int courseId) {
        this.taglineId = taglineId;
        this.courseId = courseId;
    }

    public int getTaglineId() {
        return taglineId;
    }

    public void setTaglineId(int taglineId) {
        this.taglineId = taglineId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

}
