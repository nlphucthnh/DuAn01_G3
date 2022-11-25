/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ebooks.dao;

import com.ebooks.helper.JdbcHelper;
import com.ebooks.model.QuanTriVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class QuanTriVienDAO {
    private QuanTriVien readFromResultSet(ResultSet rs) throws SQLException{
	QuanTriVien model=new QuanTriVien();
        model.setMaQuanTriVien(rs.getString("MaQuanTriVien"));
        model.setTenDangNhap(rs.getString("TenDangNhap"));        
        return model;
    }
    
    private List<QuanTriVien> select(String sql, Object...args){
        List<QuanTriVien> list=new ArrayList<>();
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
    public void insert(QuanTriVien entity) {
        String sql="INSERT INTO QuanTriVien (MaQuanTriVien, TenDangNhap) VALUES (?, ?)";
        JdbcHelper.executeUpdate(sql,
                entity.getMaQuanTriVien(),
                entity.getTenDangNhap());
    }
    /**
     * Cập nhật thực thể vào CSDL
     * @param entity là thực thể chứa thông tin bản ghi cần cập nhật
     */
    public void update(QuanTriVien entity) {
        String sql="UPDATE QuanTriVien SET TenDangNhap=? WHERE tenDangNhap=?";
        JdbcHelper.executeUpdate(sql,
                entity.getTenDangNhap(),
                entity.getMaQuanTriVien());
    }
    
    /**
     * Xóa bản ghi khỏi CSDL
     * @param id là mã của bản ghi cần xóa
     */
    public void delete(String id) {
        String sql="DELETE FROM QuanTriVien WHERE tenDangNhap=?";
        JdbcHelper.executeUpdate(sql, id);
    }
    
    /**
     * Truy vấn tất cả các các thực thể
     * @return list danh sách các thực thể
     */
    public List<QuanTriVien> selectAll() {
        String sql="SELECT * FROM QuanTriVien";
        return select(sql);
    }
    
    /**
     * Truy vấn thực thể theo mã id
     * @param id là mã của bản ghi được truy vấn
     * @return thực thể chứa thông tin của bản ghi
     */
    public QuanTriVien findById(String id) {
        String sql="SELECT * FROM QuanTriVien WHERE tenDangNhap=?";
        List<QuanTriVien> list=select(sql,id);
        return list.size()>0?list.get(0):null;
    }
    
    
}
