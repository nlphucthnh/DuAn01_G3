
package com.ebooks.dao;

import com.ebooks.helper.JdbcHelper;
import com.ebooks.model.BangGiaThue;
import com.ebooks.model.HoaDonThueSach;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BangGiaThueDAO {
    private BangGiaThue readFromResultSet(ResultSet rs) throws SQLException{
	BangGiaThue model = new BangGiaThue();
        model.setMaGiaThue(rs.getString("maGiaThue"));
        model.setThoiLuong(rs.getTime("thoiLuong"));
        model.setDonGiaThue(rs.getDouble("donGiaThue"));
        model.setMoTa(rs.getString("maGiaThue"));
        return model;
    }
    
    private List<BangGiaThue> select(String sql, Object...args){
        List<BangGiaThue> list=new ArrayList<>();
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
    public void insert(BangGiaThue entity) {
        String sql="INSERT INTO BangGiaThue(maGiaThue,donGiaThue,thoiLuong,moTa) VALUES (?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,
                entity.getMaGiaThue(),
                entity.getDonGiaThue(),
                entity.getThoiLuong(),
                entity.getMoTa());
    }
    /**
     * Cập nhật thực thể vào CSDL
     * @param entity là thực thể chứa thông tin bản ghi cần cập nhật
     */
    public void update(BangGiaThue entity) {
        String sql="UPDATE BangGiaThue SET donGiaThue=?, thoiLuong=?, moTa=? WHERE maGiaThue=?";
        JdbcHelper.executeUpdate(sql,
                entity.getDonGiaThue(),
                entity.getThoiLuong(),
                entity.getMoTa(),
                entity.getMaGiaThue());
    }
    
    /**
     * Xóa bản ghi khỏi CSDL
     * @param id là mã của bản ghi cần xóa
     */
    public void delete(String id) {
        String sql="DELETE FROM BangGiaThue WHERE maGiaThue=?";
        JdbcHelper.executeUpdate(sql, id);
    }
    
    /**
     * Truy vấn tất cả các các thực thể
     * @return list danh sách các thực thể
     */
    public List<BangGiaThue> selectAll() {
        String sql="SELECT * FROM BangGiaThue";
        return select(sql);
    }
    
    /**
     * Truy vấn thực thể theo mã id
     * @param id là mã của bản ghi được truy vấn
     * @return thực thể chứa thông tin của bản ghi
     */
    public BangGiaThue findById(String id) {
        String sql="SELECT * FROM BangGiaThue WHERE maGiaThue=?";
        List<BangGiaThue> list=select(sql,id);
        return list.size()>0?list.get(0):null;
    }
}
