/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ebooks.model;

/**
 *
 * @author Admin
 */
public class NguoiDung {

    private String maNguoiDung;
    private String hoTen;
    private boolean gioiTinh;
    private String soDienThoai;
    private String email;
    private String hinh;

    public NguoiDung() {
    }

    public NguoiDung(String maNguoiDung, String hoTen) {
        this.maNguoiDung = maNguoiDung;
        this.hoTen = hoTen;
    }
    
    

    public NguoiDung(String maNguoiDung, String hoTen, boolean gioiTinh, String soDienThoai, String email, String hinh) {
        this.maNguoiDung = maNguoiDung;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.hinh = hinh;
    }

    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

}
