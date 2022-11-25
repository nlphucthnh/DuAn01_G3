
package com.ebooks.model;


public class HoaDonChiTiet {
    private String maHoaDon;
    private String maThucUong;
    private String maHoaDonChiTiet;
    private int soLuong;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(String maHoaDon, String maThucUong, String maHoaDonChiTiet, int soLuong) {
        this.maHoaDon = maHoaDon;
        this.maThucUong = maThucUong;
        this.maHoaDonChiTiet = maHoaDonChiTiet;
        this.soLuong = soLuong;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaThucUong() {
        return maThucUong;
    }

    public void setMaThucUong(String maThucUong) {
        this.maThucUong = maThucUong;
    }

    public String getMaHoaDonChiTiet() {
        return maHoaDonChiTiet;
    }

    public void setMaHoaDonChiTiet(String maHoaDonChiTiet) {
        this.maHoaDonChiTiet = maHoaDonChiTiet;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    
    
}
