/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ebooks.dao;

import com.ebooks.helper.JdbcHelper;
import com.ebooks.model.HoaDonChiTiet;
import com.ebooks.model.HoaDonThucUong;
import com.ebooks.model.HoaDonThueSach;
import com.ebooks.model.HoaDonTongHop;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class HoaDonTongHopDAO {
    
    HoaDonChiTietDAO DAOCT = new HoaDonChiTietDAO();
    HoaDonThucUongDAO DAOHDTU = new HoaDonThucUongDAO();
    HoaDonThueSachDAO DAOTS = new HoaDonThueSachDAO();
    List<HoaDonTongHop> listHDTH = new ArrayList<>();
    List<HoaDonChiTiet> listHDCT = new ArrayList<>();
    List<HoaDonThucUong> lisHDTU = new ArrayList<>();
    List<HoaDonThueSach> listHSTS = new ArrayList<>();
    
    
     
    
    
    
    
    public List<Object[]> getHoaDonTongHop() {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_HoaDonTongHop}";
                rs = JdbcHelper.executeQuery(sql);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString("maNguoiDung"),
                        rs.getString("hoTen"),
                        rs.getString("tenDangNhap"),
                        rs.getInt("maHoaDon"),
                        rs.getFloat("TongThucUong"),
                        rs.getInt("maThueSach"),
                        rs.getFloat("TongThueSach"),
                        rs.getFloat("TongHoaDon")
                    };
                    list.add(model);

                }
            } finally {
                rs.getStatement().getConnection().close();

            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        return list;

    }
    
     public List<Object[]> getDoanhThuThucUong() {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_DoanhThuThucUong}";
                rs = JdbcHelper.executeQuery(sql);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString("maNguoiDung"),
                        rs.getString("hoTen"),
                        rs.getInt("maHoaDon"),
                        rs.getFloat("TongThucUong"),
                        rs.getDate("NgayMua")
                    };
                    list.add(model);

                }
            } finally {
                rs.getStatement().getConnection().close();

            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        return list;

    }
     
      public List<Object[]> getDoanhThuThueSach() {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_DoanhThuThueSach}";
                rs = JdbcHelper.executeQuery(sql);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString("maNguoiDung"),
                        rs.getString("hoTen"),
                        rs.getString("tenDangNhap"),
                        rs.getInt("maThueSach"),
                        rs.getFloat("TongThueSach"),
                        rs.getString("ngayThue")
                    };
                    list.add(model);

                }
            } finally {
                rs.getStatement().getConnection().close();

            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        }
        return list;

    }
      
     public List<Object[]> getTKDoanhThuThucUong(int nam) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_ThongKeDoanhThuThucUong (?)}";
                rs = JdbcHelper.executeQuery(sql, nam);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString("maHoaDon"),
                        rs.getString("maNguoiDung"),
                        rs.getString("hoTen"),
                        rs.getDouble("DoanhThu"),
                        rs.getDate("NgayMua"),
                        
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }  
     
       public List<Object[]> getTKDoanhThuThueSach(int nam) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_ThongKeDoanhThuThueSach (?)}";
                rs = JdbcHelper.executeQuery(sql, nam);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString("maThueSach"),
                        rs.getString("maNguoiDung"),
                        rs.getString("hoTen"),
                        rs.getDouble("DoanhThu"),
                        rs.getDate("ngayThue"),
                        
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
     
    
    
}
