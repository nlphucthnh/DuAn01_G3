/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ebooks.model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class TacGia {
    private String maTacGia;
    private String hoTen;
    private boolean gioiTinh;
    private Date ngaySinh;
    private String moTa;
    private String hinh;
    private String maQuanTriVien;

    public TacGia() {
    }

    public TacGia(String hoTen, boolean gioiTinh, Date ngaySinh, String moTa, String hinh, String maQuanTriVien) {
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.moTa = moTa;
        this.hinh = hinh;
        this.maQuanTriVien = maQuanTriVien;
    }
    
    

    public TacGia(String maTacGia, String hoTen, boolean gioiTinh, Date ngaySinh, String moTa, String hinh, String maQuanTriVien) {
        this.maTacGia = maTacGia;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.moTa = moTa;
        this.hinh = hinh;
        this.maQuanTriVien = maQuanTriVien;
    }

    public String getMaTacGia() {
        return maTacGia;
    }

    public void setMaTacGia(String maTacGia) {
        this.maTacGia = maTacGia;
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

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getMaQuanTriVien() {
        return maQuanTriVien;
    }

    public void setMaQuanTriVien(String maQuanTriVien) {
        this.maQuanTriVien = maQuanTriVien;
    }
    
}
