/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebooks.helper;

import java.awt.Image;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;
import com.ebooks.helper.ShareHelper;
import com.ebooks.model.TaiKhoan;

/**
 *
 * @author Jason
 */
public class ShareHelper {
 /**
 * Ảnh biểu tượng của ứng dụng, xuất hiện trên mọi cửa sổ
 */
 public static final Image APP_ICON;
 public static final ImageIcon APP_ICON_1;
 static{
     // Tải biểu tượng ứng dụng 
     //CÁCH TẢI ẢNH TỪ TRONG PROJECT
     //icon là thư mục con của src
     String file = "/com/poly/icon/fpt.png";      //icon là thư mục con của src
     APP_ICON = new ImageIcon(ShareHelper.class.getResource(file)).getImage();
     APP_ICON_1 = new ImageIcon(ShareHelper.class.getResource(file));
 }

 /**
 * Sao chép file logo chuyên đề vào thư mục logos (tạo nếu chưa có thư mục logos)
 * @param file là đối tượng file ảnh
 * @return chép được hay không
 */
 public static boolean saveLogo(File file){
     File dir = new File("logos");  //khai báo thư mục logos ngang hàng với src
     // Tạo thư mục nếu chưa tồn tại
     if(!dir.exists()){
        dir.mkdirs();
     }
     File newFile = new File(dir, file.getName());
     try {
         // Copy vào thư mục logos (đè nếu đã tồn tại)
         Path source = Paths.get(file.getAbsolutePath());
         Path destination = Paths.get(newFile.getAbsolutePath());
         Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
         return true;
     }
     catch (Exception ex) {
        return false;
     }
 }
 /**
 * Đọc hình ảnh logo chuyên đề trong thư mục logos theo tenFile
 * @param fileName là tên file logo
 * @return ImageIcon ảnh đọc được
 */
 public static ImageIcon readLogo(String fileName){
    File path = new File("logos", fileName);
    return new ImageIcon(new ImageIcon(path.getAbsolutePath()).getImage().getScaledInstance(180, 180, Image.SCALE_DEFAULT));
 }

 /**
 * Đối tượng này chứa thông tin người sử dụng sau khi đăng nhnập
 */
 public static TaiKhoan USER = new TaiKhoan();
 /**
 * Xóa thông tin của người sử dụng khi có yêu cầu đăng xuất
 */ 
 public static void clear(){
     ShareHelper.USER = null;
 }
 /**
 * Kiểm tra xem đăng nhập hay chưa
 * @return đăng nhập hay chưa
 */
 public static boolean isLogin() {
    return ShareHelper.USER != null;
 }
 
 
 public static boolean isManager(){
    return ShareHelper.isLogin() && USER.isVaiTro();
 }

 public static void logoff() {
    ShareHelper.USER = null;
 }
 /**
 * Kiểm tra xem đăng nhập hay chưa
 * @return đăng nhập hay chưa
 */
 public static boolean authenticated() {
    return ShareHelper.USER != null;
 }
 
 
}

