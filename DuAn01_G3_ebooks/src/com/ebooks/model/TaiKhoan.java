/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ebooks.model;

/**
 *
 * @author Admin
 */
public class TaiKhoan {

    private String tenDangNhap;
    private String matKhau;
    private String maNguoiDung;
    private boolean TrangThai;
    private String HoTen;

    public TaiKhoan() {
    }

    public TaiKhoan(String tenDangNhap, String matKhau, String maNguoiDung, String HoTen) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.maNguoiDung = maNguoiDung;
        this.HoTen = HoTen;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

}
