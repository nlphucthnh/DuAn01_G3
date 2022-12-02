/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ebooks.dao;

import com.ebooks.helper.JdbcHelper;
import com.ebooks.model.AudioSach;
import com.ebooks.model.HoaDonThucUong;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class HoaDonThucUongDAO {
    private HoaDonThucUong readFromResultSet(ResultSet rs) throws SQLException{
	HoaDonThucUong model=new HoaDonThucUong();
        model.setMaHoaDon(rs.getString("MaHoaDon"));
        model.setMaNguoiDung(rs.getString("MaNguoiDung"));
        model.setNgayMua(rs.getDate("NgayMua"));
        model.setTrangThai(rs.getBoolean("trangThai"));
        return model;
    }
    
    private List<HoaDonThucUong> select(String sql, Object...args){
        List<HoaDonThucUong> list=new ArrayList<>();
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
    public void insert(HoaDonThucUong entity) {
        String sql="INSERT INTO HoaDonThucUong (MaHoaDon, MaNGuoiDung, NgayMua, trangThai) VALUES (?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                entity.getMaHoaDon(),
                entity.getMaNguoiDung(),
                entity.getNgayMua(),
                entity.isTrangThai());
    }
    
    /**
     * Cập nhật thực thể vào CSDL
     * @param entity là thực thể chứa thông tin bản ghi cần cập nhật
     */
    public void update(HoaDonThucUong entity) {
        String sql="UPDATE HoaDonThucUong SET MaNguoiDung=?, NgayMua=?, trangThai=? WHERE MaHoaDon=?";
        JdbcHelper.executeUpdate(sql,
                entity.getMaNguoiDung(),
                entity.getNgayMua(),
                entity.isTrangThai(),
                entity.getMaHoaDon());
    }
    
    /**
     * Xóa bản ghi khỏi CSDL
     * @param id là mã của bản ghi cần xóa
     */
    public void delete(String id) {
        String sql="DELETE FROM HoaDonThucUong WHERE MaHoaDon=?";
        JdbcHelper.executeUpdate(sql, id);
    }
    
    /**
     * Truy vấn tất cả các các thực thể
     * @return list danh sách các thực thể
     */
    public List<HoaDonThucUong> selectAll() {
        String sql="SELECT * FROM HoaDonThucUong";
        return select(sql);
    }
    
    /**
     * Truy vấn thực thể theo mã id
     * @param id là mã của bản ghi được truy vấn
     * @return thực thể chứa thông tin của bản ghi
     */
    public HoaDonThucUong findById(String id) {
        String sql="SELECT * FROM HoaDonThucUong WHERE MaHoaDon=?";
        List<HoaDonThucUong> list=select(sql,id);
        return list.size()>0?list.get(0):null;
    }   
}
