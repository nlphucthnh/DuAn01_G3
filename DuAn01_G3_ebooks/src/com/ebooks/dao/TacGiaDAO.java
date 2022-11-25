/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ebooks.dao;

import com.ebooks.helper.JdbcHelper;
import com.ebooks.model.TacGia;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class TacGiaDAO {
    private TacGia readFromResultSet(ResultSet rs) throws SQLException{
	TacGia model=new TacGia();
        model.setMaTacGia(rs.getString("MaTacGia"));
        model.setHoTen(rs.getString("HoTen"));
        model.setGioiTinh(rs.getBoolean("GioiTinh"));
        model.setNgaySinh(rs.getDate("NgaySinh"));
        model.setMoTa(rs.getString("MoTa"));
        model.setHinh(rs.getString("Hinh"));
        model.setMaQuanTriVien(rs.getString("MaQuanTriVien"));
        return model;
    }
    
    private List<TacGia> select(String sql, Object...args){
        List<TacGia> list=new ArrayList<>();
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
    public void insert(TacGia entity) {
        String sql="INSERT INTO TacGia ( HoTen, GioiTinh, NgaySinh, MoTa, Hinh, MaQuanTriVien) VALUES (?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                entity.getHoTen(),
                entity.isGioiTinh(),
                entity.getNgaySinh(),
                entity.getMoTa(),
                entity.getHinh(),
                entity.getMaQuanTriVien());
    }
    /**
     * Cập nhật thực thể vào CSDL
     * @param entity là thực thể chứa thông tin bản ghi cần cập nhật
     */
    public void update(TacGia entity) {
        String sql="UPDATE TacGia SET HoTen=?, GioiTinh=?, NgaySinh=?, MoTa=?, Hinh=?, MaQuanTriVien=? WHERE MaTacGia=?";
        JdbcHelper.executeUpdate(sql,
                entity.getHoTen(),
                entity.isGioiTinh(),
                entity.getNgaySinh(),
                entity.getMoTa(),
                entity.getHinh(),
                entity.getMaQuanTriVien(),
                entity.getMaTacGia());
    }
    
    /**
     * Xóa bản ghi khỏi CSDL
     * @param id là mã của bản ghi cần xóa
     */
    public void delete(String id) {
        String sql="DELETE FROM TacGia WHERE MaTacGia=?";
        JdbcHelper.executeUpdate(sql, id);
    }
    
    /**
     * Truy vấn tất cả các các thực thể
     * @return list danh sách các thực thể
     */
    public List<TacGia> selectAll() {
        String sql="SELECT * FROM TacGia";
        return select(sql);
    }
    
    /**
     * Truy vấn thực thể theo mã id
     * @param id là mã của bản ghi được truy vấn
     * @return thực thể chứa thông tin của bản ghi
     */
    public TacGia findById(String id) {
        String sql="SELECT * FROM TacGia WHERE MaTacGia=?";
        List<TacGia> list=select(sql,id);
        return list.size()>0?list.get(0):null;
    }
    
    public TacGia findByName(String name) {
        String sql="SELECT * FROM TacGia WHERE hoTen LIKE ?";
        List<TacGia> list=select(sql,"%"+name+"%");
        return list.size()>0?list.get(0):null;
    }
}
