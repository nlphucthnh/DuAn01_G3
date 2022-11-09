/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ebooks.model;

/**
 *
 * @author ASUS
 */
public class ThucUong {
    private String maThucUong;
    private String tenThucUong;
    private double gia;

    public ThucUong() {
    }

    public ThucUong(String maThucUong, String tenThucUong, double gia) {
        this.maThucUong = maThucUong;
        this.tenThucUong = tenThucUong;
        this.gia = gia;
    }

    public String getMaThucUong() {
        return maThucUong;
    }

    public void setMaThucUong(String maThucUong) {
        this.maThucUong = maThucUong;
    }

    public String getTenThucUong() {
        return tenThucUong;
    }

    public void setTenThucUong(String tenThucUong) {
        this.tenThucUong = tenThucUong;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }
    
}
