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
    private int authorId;
    private String image_url;
    private String backlink_url;
    private int status;

    public Slider() {
    }

    public Slider(int authorId, String image_url, String backlink_url, int status) {
        this.authorId = authorId;
        this.image_url = image_url;
        this.backlink_url = backlink_url;
        this.status = status;
    }

    // <editor-fold defaultstate="collapsed" desc="Getter and Setter methods. Click on the + sign on the left to edit the code.">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getBacklink_url() {
        return backlink_url;
    }

    public void setBacklink_url(String backlink_url) {
        this.backlink_url = backlink_url;
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
        return "Slider{" + "id=" + id + ", authorId=" + authorId + ", image_url=" + image_url + ", backlink_url=" + backlink_url + ", status=" + status + '}';
    }

}
