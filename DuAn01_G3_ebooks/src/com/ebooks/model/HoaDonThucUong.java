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
public class HoaDonThucUong {
    private String maHoaDon;
    private String maNguoiDung;
    private boolean trangThai;
    private Date ngayMua;
    

    public HoaDonThucUong() {
    }

    public HoaDonThucUong(String maHoaDon, String maNguoiDung, boolean trangThai, Date ngayMua) {
        this.maHoaDon = maHoaDon;
        this.maNguoiDung = maNguoiDung;
        this.trangThai = trangThai;
        this.ngayMua = ngayMua;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(Date ngayMua) {
        this.ngayMua = ngayMua;
    }

    
  
  
    
}
