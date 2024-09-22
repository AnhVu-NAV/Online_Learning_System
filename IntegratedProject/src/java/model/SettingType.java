/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Vector;

/**
 *
 * @author ADMIN
 */
public class SettingType {

    private int id;
    private String name;
    private Vector<Setting> settings;

    public SettingType() {
    }

    public SettingType(int id, String name, Vector<Setting> settings) {
        this.id = id;
        this.name = name;
        this.settings = settings;
    }

    // <editor-fold defaultstate="collapsed" desc="Getter and Setter methods. Click on the + sign on the left to edit the code.">
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

    public Vector<Setting> getSettings() {
        return settings;
    }

    public void setSettings(Vector<Setting> settings) {
        this.settings = settings;
    }

    // </editor-fold> 
    @Override
    public String toString() {
        return "SettingType{" + "id=" + id + ", name=" + name + '}';
    }

}
