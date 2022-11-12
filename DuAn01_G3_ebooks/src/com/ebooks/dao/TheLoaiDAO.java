/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ebooks.dao;

import com.ebooks.helper.JdbcHelper;
import com.ebooks.model.TheLoai;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class TheLoaiDAO {
    private TheLoai readFromResultSet(ResultSet rs) throws SQLException{
	TheLoai model=new TheLoai();
        model.setMaTheLoai(rs.getString("MaTheLoai"));
        model.setTenTheLoai(rs.getString("TenTheLoai"));
        model.setMoTaTheLoai(rs.getString("MoTaTheLoai"));
        model.setMaQuanTriVien(rs.getString("MaQuanTriVien"));
        return model;
    }
    
    private List<TheLoai> select(String sql, Object...args){
        List<TheLoai> list=new ArrayList<>();
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
    public void insert(TheLoai entity) {
        String sql="INSERT INTO TheLoai (MaTheLoai, TenTheLoai, MoTaTheLoai, MaQuanTriVien) VALUES (?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                entity.getMaTheLoai(),
                entity.getTenTheLoai(),
                entity.getMoTaTheLoai(),
                entity.getMaQuanTriVien());
    }
    /**
     * Cập nhật thực thể vào CSDL
     * @param entity là thực thể chứa thông tin bản ghi cần cập nhật
     */
    public void update(TheLoai entity) {
        String sql="UPDATE TheLoai SET TenTheLoai=?, MoTaTheLoai=?, MaQuanTriVien=? WHERE MaTheLoai=?";
        JdbcHelper.executeUpdate(sql,
                entity.getTenTheLoai(),
                entity.getMoTaTheLoai(),
                entity.getMaQuanTriVien(),
                entity.getMaTheLoai());
    }
    
    /**
     * Xóa bản ghi khỏi CSDL
     * @param id là mã của bản ghi cần xóa
     */
    public void delete(String id) {
        String sql="DELETE FROM TheLoai WHERE MaTheLoai=?";
        JdbcHelper.executeUpdate(sql, id);
    }
    
    /**
     * Truy vấn tất cả các các thực thể
     * @return list danh sách các thực thể
     */
    public List<TheLoai> selectAll() {
        String sql="SELECT * FROM TheLoai";
        return select(sql);
    }
    
    /**
     * Truy vấn thực thể theo mã id
     * @param id là mã của bản ghi được truy vấn
     * @return thực thể chứa thông tin của bản ghi
     */
    public TheLoai findById(String id) {
        String sql="SELECT * FROM TheLoai WHERE MaTheLoai=?";
        List<TheLoai> list=select(sql,id);
        return list.size()>0?list.get(0):null;
    }
}
