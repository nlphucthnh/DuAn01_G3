/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ebooks.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class DateHelper {
    public static final SimpleDateFormat DATE_FORMATER = new SimpleDateFormat("dd/MM/yyyy");
    
    //lấy giờ hiện tại
    public static Date now() {
        return new Date();   //new Date lấy giờ hiện tại
    }
    
    //chuuyển String sang Date
    /*
    @param date truyền vào date kiểu String
    @param pattern truyền vào kiểu
    return trả về date kiểu Date
     */
    public static Date toDate(String date, String... pattern) {
        try {
            if (pattern.length > 0) {
                DATE_FORMATER.applyPattern(pattern[0]);
            }
            if (date == null) {
                return DateHelper.now();
            }
            return DATE_FORMATER.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    //chuyển Date sang String
    /*
    @param date chuyền vào date kiểu date
    @param pattern định dạng date
    return date kiểu String đã định theo dạng pattern
     */
    public static String toString(Date date, String... pattern) {
        if (pattern.length > 0) {
            DATE_FORMATER.applyPattern(pattern[0]);
        }
        if (date == null) {
            date = DateHelper.now();
        }
        return DATE_FORMATER.format(date);
    }
    
    //thêm 1 số ngày vào mốc thời gian
    /*
    @param date kiểu Date
    @param days số ngày thêm, kiểu int
    return date kiểu Date đã thêm số ngày
     */
    public static Date addDays(Date date, int days) {
        //date.setTime(date.getTime()+days*24*60*60*1000);
        //setTime gán cho biến date 1 mốc thời gian được chuyển từ milisecon (long)
        //getTime chuyển mốc thời gian của biến date thành milisecon (long)
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }
    
    
}
