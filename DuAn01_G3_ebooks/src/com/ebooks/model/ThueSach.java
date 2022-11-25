/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ebooks.model;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class ThueSach {
    private String maThueSach;
    private String maNguoiDung;
    private String tenDangNhap;
    private int thoiGian;
    private Date ngayThue;
    private double tienThue;

    public ThueSach() {
    }

    public ThueSach(String maThueSach, String maNguoiDung, String tenDangNhap, int thoiGian, Date ngayThue, double tienThue) {
        this.maThueSach = maThueSach;
        this.maNguoiDung = maNguoiDung;
        this.tenDangNhap = tenDangNhap;
        this.thoiGian = thoiGian;
        this.ngayThue = ngayThue;
        this.tienThue = tienThue;
    }

    public String getMaThueSach() {
        return maThueSach;
    }

    public void setMaThueSach(String maThueSach) {
        this.maThueSach = maThueSach;
    }

    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public int getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(int thoiGian) {
        this.thoiGian = thoiGian;
    }

    public Date getNgayThue() {
        return ngayThue;
    }

    public void setNgayThue(Date ngayThue) {
        this.ngayThue = ngayThue;
    }

    public double getTienThue() {
        return tienThue;
    }

    public void setTienThue(double tienThue) {
        this.tienThue = tienThue;
    }
    
}
