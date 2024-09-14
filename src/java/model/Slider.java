/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Slider {

    private int id;
    private byte[] image_data;
    private int author_id;
    private int post_id;

    public Slider() {
    }

    public Slider(int id, byte[] image_data, int author_id, int post_id) {
        this.id = id;
        this.image_data = image_data;
        this.author_id = author_id;
        this.post_id = post_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage_data() {
        return image_data;
    }

    public void setImage_data(byte[] image_data) {
        this.image_data = image_data;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    @Override
    public String toString() {
        return "Slider{" + "id=" + id + ", image_data=" + ", author_id=" + author_id + ", post_id=" + post_id + '}';
    }

}
