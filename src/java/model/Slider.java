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
    private String imageURL;
    private String backlinkURL;
    private int status;

    public Slider() {
    }

    public Slider(int authorId, String imageURL, String backlinkURL, int status) {
        this.authorId = authorId;
        this.imageURL = imageURL;
        this.backlinkURL = backlinkURL;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String image_url) {
        this.imageURL = image_url;
    }

    public String getBacklinkURL() {
        return backlinkURL;
    }

    public void setBacklinkURL(String backlinkURL) {
        this.backlinkURL = backlinkURL;
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
        return "Slider{" + "id=" + id + ", authorId=" + authorId + ", image_url=" + imageURL + ", backlink_url=" + backlinkURL + ", status=" + status + '}';
    }

}
