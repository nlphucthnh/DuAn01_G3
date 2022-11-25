
package com.ebooks.model;

import java.sql.Time;


public class BangGiaThue {
    private String maGiaThue;
    private Time ThoiLuong;
    private double DonGiaThue;
    private String MoTa;

    public BangGiaThue() {
    }

    public BangGiaThue(String maGiaThue, Time ThoiLuong, double DonGiaThue, String MoTa) {
        this.maGiaThue = maGiaThue;
        this.ThoiLuong = ThoiLuong;
        this.DonGiaThue = DonGiaThue;
        this.MoTa = MoTa;
    }

    public String getMaGiaThue() {
        return maGiaThue;
    }

    public void setMaGiaThue(String maGiaThue) {
        this.maGiaThue = maGiaThue;
    }

    public Time getThoiLuong() {
        return ThoiLuong;
    }

    public void setThoiLuong(Time ThoiLuong) {
        this.ThoiLuong = ThoiLuong;
    }

    public double getDonGiaThue() {
        return DonGiaThue;
    }

    public void setDonGiaThue(double DonGiaThue) {
        this.DonGiaThue = DonGiaThue;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }
    
    
    
}
