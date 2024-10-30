/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author AnhVuNAV
 */
public class Tagline {

    private int id;
    private String name;
     private boolean checked; // Add this property

    public Tagline() {
    }

    public Tagline(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter and Setter for 'checked'
    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
