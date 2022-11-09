/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ebooks.model;

/**
 *
 * @author ASUS
 */
public class HoaDonThucUong {
    private String maHoaDon;
    private String maNguoiDung;
    private String maThucUong;
    private int soLuong;

    public HoaDonThucUong() {
    }

    public HoaDonThucUong(String maHoaDon, String maNguoiDung, String maThucUong, int soLuong) {
        this.maHoaDon = maHoaDon;
        this.maNguoiDung = maNguoiDung;
        this.maThucUong = maThucUong;
        this.soLuong = soLuong;
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

    public String getMaThucUong() {
        return maThucUong;
    }

    public void setMaThucUong(String maThucUong) {
        this.maThucUong = maThucUong;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    
}
