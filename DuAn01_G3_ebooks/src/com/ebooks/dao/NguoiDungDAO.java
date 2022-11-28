/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ebooks.dao;

import com.ebooks.helper.DialogHelper;
import com.ebooks.helper.JdbcHelper;
import com.ebooks.model.NguoiDung;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class NguoiDungDAO {

    private NguoiDung readFromResultSet(ResultSet rs) throws SQLException {
        NguoiDung model = new NguoiDung();
        model.setMaNguoiDung(rs.getString("MaNguoiDung"));
        model.setHoTen(rs.getString("HoTen"));
        model.setGioiTinh(rs.getBoolean("GioiTinh"));
        model.setSoDienThoai(rs.getString("SoDienThoai"));
        model.setEmail(rs.getString("Email"));
        model.setHinh(rs.getString("Hinh"));
        return model;
    }

    private List<NguoiDung> select(String sql, Object... args) {
        List<NguoiDung> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    list.add(readFromResultSet(rs));
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException();
        }
        return list;
    }

    /**
     * Thêm mới thực thể vào CSDL
     *
     * @param entity là thực thể chứa thông tin bản ghi mới
     */
    public void insert(NguoiDung entity) {
        String sql = "INSERT INTO NguoiDung (MaNguoiDung, HoTen, GioiTinh, SoDienThoai, Email, Hinh) VALUES (?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                entity.getMaNguoiDung(),
                entity.getHoTen(),
                entity.isGioiTinh(),
                entity.getSoDienThoai(),
                entity.getEmail(),
                entity.getHinh());
    }

    /**
     * Cập nhật thực thể vào CSDL
     *
     * @param entity là thực thể chứa thông tin bản ghi cần cập nhật
     */
    public void update(NguoiDung entity) {
        String sql = "UPDATE dbo.NguoiDung SET hoTen = ?, gioiTinh = ?, soDienThoai = ?, email = ?, hinh = ? WHERE maNguoiDung = ?";
        JdbcHelper.executeUpdate(sql,
                entity.getHoTen(),
                entity.isGioiTinh(),
                entity.getSoDienThoai(),
                entity.getEmail(),
                entity.getHinh(),
                entity.getMaNguoiDung());
        DialogHelper.alert(null, "Cập nhật thành công!");

    }

    /**
     * Xóa bản ghi khỏi CSDL
     *
     * @param id là mã của bản ghi cần xóa
     */
    public void delete(String id) {
        String sql = "DELETE FROM NguoiDung WHERE MaNguoiDung=?";
        JdbcHelper.executeUpdate(sql, id);
    }

    /**
     * Truy vấn tất cả các các thực thể
     *
     * @return list danh sách các thực thể
     */
    public List<NguoiDung> selectAll() {
        String sql = "SELECT * FROM NguoiDung";
        return select(sql);
    }

    /**
     * Truy vấn thực thể theo mã id
     *
     * @param id là mã của bản ghi được truy vấn
     * @return thực thể chứa thông tin của bản ghi
     */
    public NguoiDung findById(String id) {
        String sql = "SELECT * FROM NguoiDung WHERE MaNguoiDung=?";
        List<NguoiDung> list = select(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }
}
