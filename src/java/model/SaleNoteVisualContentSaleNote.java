/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 84941
 */
public class SaleNoteVisualContentSaleNote {

    private int saleNoteId;
    private int saleNoteVisualContentId;
    private int status;

    public SaleNoteVisualContentSaleNote(int saleNoteId, int saleNoteVisualContentId, int status) {
        this.saleNoteId = saleNoteId;
        this.saleNoteVisualContentId = saleNoteVisualContentId;
        this.status = status;
    }

    public int getSaleNoteId() {
        return saleNoteId;
    }

    public void setSaleNoteId(int saleNoteId) {
        this.saleNoteId = saleNoteId;
    }

    public int getSaleNoteVisualContentId() {
        return saleNoteVisualContentId;
    }

    public void setSaleNoteVisualContentId(int saleNoteVisualContentId) {
        this.saleNoteVisualContentId = saleNoteVisualContentId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
