/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ebooks.dao;

import com.ebooks.helper.JdbcHelper;
import com.ebooks.model.ThucUong;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ThucUongDAO {
    private ThucUong readFromResultSet(ResultSet rs) throws SQLException{
	ThucUong model=new ThucUong();
        model.setMaThucUong(rs.getString("MaThucUong"));
        model.setTenThucUong(rs.getString("TenThucUong"));
        model.setGia(rs.getDouble("Gia"));
        return model;
    }
    
    private List<ThucUong> select(String sql, Object...args){
        List<ThucUong> list=new ArrayList<>();
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
    public void insert(ThucUong entity) {
        String sql="INSERT INTO ThucUong (MaThucUong, TenThucUong, Gia) VALUES (?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                entity.getMaThucUong(),
                entity.getTenThucUong(),
                entity.getGia());
    }
    /**
     * Cập nhật thực thể vào CSDL
     * @param entity là thực thể chứa thông tin bản ghi cần cập nhật
     */
    public void update(ThucUong entity) {
        String sql="UPDATE ThucUong SET TenThucUong=?, Gia=? WHERE MaThucUong=?";
        JdbcHelper.executeUpdate(sql,
                entity.getTenThucUong(),
                entity.getGia(),
                entity.getMaThucUong());
    }
    
    /**
     * Xóa bản ghi khỏi CSDL
     * @param id là mã của bản ghi cần xóa
     */
    public void delete(String id) {
        String sql="DELETE FROM ThucUong WHERE MaThucUong=?";
        JdbcHelper.executeUpdate(sql, id);
    }
    
    /**
     * Truy vấn tất cả các các thực thể
     * @return list danh sách các thực thể
     */
    public List<ThucUong> selectAll() {
        String sql="SELECT * FROM ThucUong";
        return select(sql);
    }
    
    /**
     * Truy vấn thực thể theo mã id
     * @param id là mã của bản ghi được truy vấn
     * @return thực thể chứa thông tin của bản ghi
     */
    public ThucUong findById(String id) {
        String sql="SELECT * FROM ThucUong WHERE MaThucUong=?";
        List<ThucUong> list=select(sql,id);
        return list.size()>0?list.get(0):null;
    }
}
