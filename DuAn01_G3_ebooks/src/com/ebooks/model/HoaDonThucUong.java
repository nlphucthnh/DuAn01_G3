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
    private int soLuong;
    private Date ngayMua;
    

    public HoaDonThucUong() {
    }

    public HoaDonThucUong(String maHoaDon, String maNguoiDung, int soLuong, Date ngayMua) {
        this.maHoaDon = maHoaDon;
        this.maNguoiDung = maNguoiDung;
        this.soLuong = soLuong;
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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Date getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(Date ngayMua) {
        this.ngayMua = ngayMua;
    }

  
  
    
}
