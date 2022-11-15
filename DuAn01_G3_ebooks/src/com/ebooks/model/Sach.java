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
public class Sach {
    private String maSach;
    private String tenSach;
    private String maTacGia;
    private String moTa;
    private double gia;
    private Date ngayDang;
    private String hinh;
    private String maQuanTriVien;

    public Sach() {
    }

    public Sach(String maSach, String tenSach, String maTacGia, String moTa, double gia, Date ngayDang, String hinh, String maQuanTriVien) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.maTacGia = maTacGia;
        this.moTa = moTa;
        this.gia = gia;
        this.ngayDang = ngayDang;
        this.hinh = hinh;
        this.maQuanTriVien = maQuanTriVien;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getMaTacGia() {
        return maTacGia;
    }

    public void setMaTacGia(String maTacGia) {
        this.maTacGia = maTacGia;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public Date getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(Date ngayDang) {
        this.ngayDang = ngayDang;
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