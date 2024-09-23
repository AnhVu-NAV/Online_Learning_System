/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 84941
 */
public class Paging {
    private int size;//so luong ban ghi
    private int nrpp;// so luong ban ghi tren 1 trang
    private int index;// trang hien tai (bat dau tu 0)
    private int totalPage;//so luong trang
    private int begin;//index cua ban ghi dau tien tren trang do
    private int end;// index cua ban ghi cuoi cung tren trang do
    private int pageStart;//trang bat dau (bat dau tu 0)
    private int pageEnd;// trang ket thuc

    public void calc() {
        this.totalPage = (this.size + this.nrpp - 1) / this.nrpp;
        this.index = this.index < 0 ? 0 : this.index;
        this.index = this.index >= this.totalPage ? this.totalPage - 1 : this.index;
        this.begin = this.index * this.nrpp;
        this.end = this.begin + this.nrpp;
        this.end = this.end > this.size ? this.size : this.end;
        this.pageStart = this.index - 2;
        this.pageStart = this.pageStart < 0 ? 0 : this.pageStart;
        this.pageEnd = this.index + 2;
        this.pageEnd = this.pageEnd >= this.totalPage ? this.totalPage - 1 : this.pageEnd;

    }

    public Paging() {
    }

    public Paging(int size, int nrpp, int index) {
        this.size = size;
        this.nrpp = nrpp;
        this.index = index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNrpp() {
        return nrpp;
    }

    public void setNrpp(int nrpp) {
        this.nrpp = nrpp;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
