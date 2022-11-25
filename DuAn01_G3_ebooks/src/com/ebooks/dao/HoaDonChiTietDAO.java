package com.ebooks.dao;

import com.ebooks.helper.JdbcHelper;
import com.ebooks.model.HoaDonChiTiet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HoaDonChiTietDAO {

    public class HoaDonThucUongDAO {

        private HoaDonChiTiet readFromResultSet(ResultSet rs) throws SQLException {
            HoaDonChiTiet model = new HoaDonChiTiet();
            model.setMaHoaDon(rs.getString("MaHoaDon"));
            model.setSoLuong(rs.getInt("soLuong"));
            model.setMaThucUong(rs.getString("maThucUong"));
            model.setMaHoaDonChiTiet(rs.getString("maHoaDonChiTiet"));
            return model;
        }

        private List<HoaDonChiTiet> select(String sql, Object... args) {
            List<HoaDonChiTiet> list = new ArrayList<>();
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
        public void insert(HoaDonChiTiet entity) {
            String sql = "INSERT INTO HoaDonChiTiet (MaHoaDon, soLuong, maThucUong, maHoaDonChiTiet) VALUES (?, ?, ?, ?)";
            JdbcHelper.executeUpdate(sql,
                    entity.getMaHoaDon(),
                    entity.getSoLuong(),
                    entity.getMaThucUong(),
                    entity.getMaHoaDonChiTiet());
        }

        /**
         * Cập nhật thực thể vào CSDL
         *
         * @param entity là thực thể chứa thông tin bản ghi cần cập nhật
         */
        public void update(HoaDonChiTiet entity) {
            String sql = "UPDATE HoaDonChiTiet SET maHoaDon =?, soLuong=?,maThucUong=? WHERE maHoaDonChiTiet=?";
            JdbcHelper.executeUpdate(sql,
                    entity.getMaHoaDon(),
                    entity.getSoLuong(),
                    entity.getMaThucUong(),
                    entity.getMaHoaDonChiTiet()
            );
        }

        /**
         * Xóa bản ghi khỏi CSDL
         *
         * @param id là mã của bản ghi cần xóa
         */
        public void delete(String id) {
            String sql = "DELETE FROM HoaDonChiTiet WHERE maHoaDonChiTiet=?";
            JdbcHelper.executeUpdate(sql, id);
        }

        /**
         * Truy vấn tất cả các các thực thể
         *
         * @return list danh sách các thực thể
         */
        public List<HoaDonChiTiet> selectAll() {
            String sql = "SELECT * FROM HoaDonChiTiet";
            return select(sql);
        }

        /**
         * Truy vấn thực thể theo mã id
         *
         * @param id là mã của bản ghi được truy vấn
         * @return thực thể chứa thông tin của bản ghi
         */
        public HoaDonChiTiet findById(String id) {
            String sql = "SELECT * FROM HoaDonChiTiet WHERE maHoaDonChiTiet=?";
            List<HoaDonChiTiet> list = select(sql, id);
            return list.size() > 0 ? list.get(0) : null;
        }
    }
}
