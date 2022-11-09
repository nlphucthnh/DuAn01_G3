/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ebooks.model;

/**
 *
 * @author Admin
 */
public class LoaiSS {
    private String maLoaiSS;
    private String maSach;
    private String maTheLoai;

    public LoaiSS() {
    }

    public LoaiSS(String maLoaiSS, String maSach, String maTheLoai) {
        this.maLoaiSS = maLoaiSS;
        this.maSach = maSach;
        this.maTheLoai = maTheLoai;
    }

    public String getMaLoaiSS() {
        return maLoaiSS;
    }

    public void setMaLoaiSS(String maLoaiSS) {
        this.maLoaiSS = maLoaiSS;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }
    
    
}
