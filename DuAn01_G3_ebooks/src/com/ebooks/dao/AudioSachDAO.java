/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ebooks.dao;

import com.ebooks.helper.JdbcHelper;
import com.ebooks.model.AudioSach;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class AudioSachDAO {
    private AudioSach readFromResultSet(ResultSet rs) throws SQLException{
	AudioSach model=new AudioSach();
        model.setMaAudio(rs.getString("MaAudio"));
        model.setTenAudio(rs.getString("TenAudio"));
        model.setMaSach(rs.getString("MaSach"));
        model.setNgayPhatHanh(rs.getDate("NgayPhatHanh"));
        model.setNguoiThu(rs.getString("NguoiThu"));
        model.setMoTa(rs.getString("MoTa"));
        model.setDuongDan(rs.getString("DuongDan"));
        model.setHinhAnh(rs.getString("HinhAnh"));
        model.setMaQuanTriVien(rs.getString("MaQuanTriVien"));
        return model;
    }
    
    private List<AudioSach> select(String sql, Object...args){
        List<AudioSach> list=new ArrayList<>();
        try {
            ResultSet rs=null;
            try{
                rs=JdbcHelper.executeQuery(sql, args);
                while(rs.next()){
                    list.add(readFromResultSet(rs));
                }
            }finally{
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
        return list;
    }
    
    /**
     * Thêm mới thực thể vào CSDL
     * @param entity là thực thể chứa thông tin bản ghi mới
     */
    public void insert(AudioSach entity) {
        String sql="INSERT INTO AudioSach ( TenAudio, MaSach, NgayPhatHanh, NguoiThu, MoTa, DuongDan, HinhAnh, MaQuanTriVien) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                entity.getTenAudio(),
                entity.getMaSach(),
                entity.getNgayPhatHanh(),
                entity.getNguoiThu(),
                entity.getMoTa(),
                entity.getDuongDan(),
                entity.getHinhAnh(),
                entity.getMaQuanTriVien());
    }
    /**
     * Cập nhật thực thể vào CSDL
     * @param entity là thực thể chứa thông tin bản ghi cần cập nhật
     */
    public void update(AudioSach entity) {
        String sql="UPDATE AudioSach SET TenAudio=?, MaSach=?, NgayPhatHanh=?, NguoiThu=?, MoTa=?, DuongDan=?, HinhAnh=?, MaQuanTriVien=? WHERE MaAudio=?";
        JdbcHelper.executeUpdate(sql,
                entity.getTenAudio(),
                entity.getMaSach(),
                entity.getNgayPhatHanh(),
                entity.getNguoiThu(),
                entity.getMoTa(),
                entity.getDuongDan(),
                entity.getHinhAnh(),
                entity.getMaQuanTriVien(),
                entity.getMaAudio());
    }
    
    /**
     * Xóa bản ghi khỏi CSDL
     * @param id là mã của bản ghi cần xóa
     */
    public void delete(String id) {
        String sql="DELETE FROM AudioSach WHERE MaAudio=?";
        JdbcHelper.executeUpdate(sql, id);
    }
    
    /**
     * Truy vấn tất cả các các thực thể
     * @return list danh sách các thực thể
     */
    public List<AudioSach> selectAll() {
        String sql="SELECT * FROM AudioSach";
        return select(sql);
    }
    
    /**
     * Truy vấn thực thể theo mã id
     * @param id là mã của bản ghi được truy vấn
     * @return thực thể chứa thông tin của bản ghi
     */
    public AudioSach findById(String id) {
        String sql="SELECT * FROM AudioSach WHERE MaAudio=?";
        List<AudioSach> list=select(sql,id);
        return list.size()>0?list.get(0):null;
    }   
}
