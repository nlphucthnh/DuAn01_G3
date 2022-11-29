/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ebooks.model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class AudioSach {
    private String maAudio;
    private String tenAudio;
    private String maSach;
    private Date ngayPhatHanh;
    private String nguoiThu;
    private String moTa;
    private String duongDan;
    private String hinhAnh;
    private String maQuanTriVien;

    public AudioSach() {
    }

    public AudioSach(String maAudio, String tenAudio, String maSach, Date ngayPhatHanh, String nguoiThu, String moTa, String duongDan, String hinhAnh, String maQuanTriVien) {
        this.maAudio = maAudio;
        this.tenAudio = tenAudio;
        this.maSach = maSach;
        this.ngayPhatHanh = ngayPhatHanh;
        this.nguoiThu = nguoiThu;
        this.moTa = moTa;
        this.duongDan = duongDan;
        this.hinhAnh = hinhAnh;
        this.maQuanTriVien = maQuanTriVien;
    }

    public String getMaAudio() {
        return maAudio;
    }

    public void setMaAudio(String maAudio) {
        this.maAudio = maAudio;
    }

    public String getTenAudio() {
        return tenAudio;
    }

    public void setTenAudio(String tenAudio) {
        this.tenAudio = tenAudio;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public Date getNgayPhatHanh() {
        return ngayPhatHanh;
    }

    public void setNgayPhatHanh(Date ngayPhatHanh) {
        this.ngayPhatHanh = ngayPhatHanh;
    }

    public String getNguoiThu() {
        return nguoiThu;
    }

    public void setNguoiThu(String nguoiThu) {
        this.nguoiThu = nguoiThu;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getDuongDan() {
        return duongDan;
    }

    public void setDuongDan(String duongDan) {
        this.duongDan = duongDan;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getMaQuanTriVien() {
        return maQuanTriVien;
    }

    public void setMaQuanTriVien(String maQuanTriVien) {
        this.maQuanTriVien = maQuanTriVien;
    }

}
