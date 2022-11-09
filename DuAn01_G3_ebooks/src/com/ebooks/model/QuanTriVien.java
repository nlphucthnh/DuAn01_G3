/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ebooks.model;

/**
 *
 * @author ASUS
 */
public class QuanTriVien {
    private String maQuanTriVien;
    private String tenDangNhap;
    private String maNguoiDung;

    public QuanTriVien() {
    }

    public QuanTriVien(String maQuanTriVien, String tenDangNhap, String maNguoiDung) {
        this.maQuanTriVien = maQuanTriVien;
        this.tenDangNhap = tenDangNhap;
        this.maNguoiDung = maNguoiDung;
    }

    public String getMaQuanTriVien() {
        return maQuanTriVien;
    }

    public void setMaQuanTriVien(String maQuanTriVien) {
        this.maQuanTriVien = maQuanTriVien;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }
    
}
