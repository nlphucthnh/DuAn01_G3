/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ebooks.model;

/**
 *
 * @author Thinh
 */
public class HoaDonTongHop {

    private String maNguoiDung;
    private String maHoaDonThucUong;
    private String maHoaDonThueSach;
    private String HoTen;
    private String tenDangNhap;
    private Float tongTienThueSach;
    private Float tongTienThucUong;
    private Float tongTien;

    public HoaDonTongHop() {
    }

    public HoaDonTongHop(String maNguoiDung, String maHoaDonThucUong, String maHoaDonThueSach, String HoTen, String tenDangNhap, Float tongTienThueSach, Float tongTienThucUong, Float tongTien) {
        this.maNguoiDung = maNguoiDung;
        this.maHoaDonThucUong = maHoaDonThucUong;
        this.maHoaDonThueSach = maHoaDonThueSach;
        this.HoTen = HoTen;
        this.tenDangNhap = tenDangNhap;
        this.tongTienThueSach = tongTienThueSach;
        this.tongTienThucUong = tongTienThucUong;
        this.tongTien = tongTien;
    }

    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getMaHoaDonThucUong() {
        return maHoaDonThucUong;
    }

    public void setMaHoaDonThucUong(String maHoaDonThucUong) {
        this.maHoaDonThucUong = maHoaDonThucUong;
    }

    public String getMaHoaDonThueSach() {
        return maHoaDonThueSach;
    }

    public void setMaHoaDonThueSach(String maHoaDonThueSach) {
        this.maHoaDonThueSach = maHoaDonThueSach;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public Float getTongTienThueSach() {
        return tongTienThueSach;
    }

    public void setTongTienThueSach(Float tongTienThueSach) {
        this.tongTienThueSach = tongTienThueSach;
    }

    public Float getTongTienThucUong() {
        return tongTienThucUong;
    }

    public void setTongTienThucUong(Float tongTienThucUong) {
        this.tongTienThucUong = tongTienThucUong;
    }

    public Float getTongTien() {
        return tongTien;
    }

    public void setTongTien(Float tongTien) {
        this.tongTien = tongTien;
    }

}
