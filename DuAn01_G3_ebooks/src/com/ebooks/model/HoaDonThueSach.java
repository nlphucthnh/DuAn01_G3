
package com.ebooks.model;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class HoaDonThueSach {
    private String maThueSach;
    private String maNguoiDung;
    private String tenDangNhap;
    private Time thoiGian;
    private Date ngayThue;
    private String maGiaThue;

    public HoaDonThueSach() {
        
    }

    public HoaDonThueSach(String maThueSach, String maNguoiDung, String tenDangNhap, Time thoiGian, Date ngayThue, String maGiaThue) {
        this.maThueSach = maThueSach;
        this.maNguoiDung = maNguoiDung;
        this.tenDangNhap = tenDangNhap;
        this.thoiGian = thoiGian;
        this.ngayThue = ngayThue;
        this.maGiaThue = maGiaThue;
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

    public Time getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Time thoiGian) {
        this.thoiGian = thoiGian;
    }

    public Date getNgayThue() {
        return ngayThue;
    }

    public void setNgayThue(Date ngayThue) {
        this.ngayThue = ngayThue;
    }

    public String getMaGiaThue() {
        return maGiaThue;
    }

    public void setMaGiaThue(String maGiaThue) {
        this.maGiaThue = maGiaThue;
    }

    
    
}
