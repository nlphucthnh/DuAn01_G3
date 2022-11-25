/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ebooks.dao;

import com.ebooks.helper.JdbcHelper;
import com.ebooks.model.HoaDonThueSach;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ThueSachDAO {
    private HoaDonThueSach readFromResultSet(ResultSet rs) throws SQLException{
	HoaDonThueSach model=new HoaDonThueSach();
        model.setMaThueSach(rs.getString("MaThueSach"));
        model.setMaNguoiDung(rs.getString("MaNguoiDung"));
        model.setTenDangNhap(rs.getString("TenDangNhap"));
        model.setThoiGian(rs.getInt("ThoiGian"));
        model.setNgayThue(rs.getDate("NgayThue"));
        model.setTienThue(rs.getDouble("TienThue"));
        return model;
    }
    
    private List<HoaDonThueSach> select(String sql, Object...args){
        List<HoaDonThueSach> list=new ArrayList<>();
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
    public void insert(HoaDonThueSach entity) {
        String sql="INSERT INTO ThueSach (MaThueSach, MaNguoiDung, TenDangNhap, ThoiGian, NgayThue, TienThue) VALUES (?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                entity.getMaThueSach(),
                entity.getMaNguoiDung(),
                entity.getTenDangNhap(),
                entity.getThoiGian(),
                entity.getNgayThue(),
                entity.getTienThue());
    }
    /**
     * Cập nhật thực thể vào CSDL
     * @param entity là thực thể chứa thông tin bản ghi cần cập nhật
     */
    public void update(HoaDonThueSach entity) {
        String sql="UPDATE ThueSach SET MaNguoiDung=?, TenDangNhap=?, ThoiGian=?, NgayThue=?, TienThue=? WHERE MaThueSach=?";
        JdbcHelper.executeUpdate(sql,
                entity.getMaNguoiDung(),
                entity.getTenDangNhap(),
                entity.getThoiGian(),
                entity.getNgayThue(),
                entity.getTienThue(),
                entity.getMaThueSach());
    }
    
    /**
     * Xóa bản ghi khỏi CSDL
     * @param id là mã của bản ghi cần xóa
     */
    public void delete(String id) {
        String sql="DELETE FROM ThueSach WHERE MaThueSach=?";
        JdbcHelper.executeUpdate(sql, id);
    }
    
    /**
     * Truy vấn tất cả các các thực thể
     * @return list danh sách các thực thể
     */
    public List<HoaDonThueSach> selectAll() {
        String sql="SELECT * FROM ThueSach";
        return select(sql);
    }
    
    /**
     * Truy vấn thực thể theo mã id
     * @param id là mã của bản ghi được truy vấn
     * @return thực thể chứa thông tin của bản ghi
     */
    public HoaDonThueSach findById(String id) {
        String sql="SELECT * FROM ThueSach WHERE MaThueSach=?";
        List<HoaDonThueSach> list=select(sql,id);
        return list.size()>0?list.get(0):null;
    }
}
