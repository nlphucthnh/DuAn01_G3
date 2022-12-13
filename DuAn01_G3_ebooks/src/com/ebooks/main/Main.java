/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ebooks.main;

import com.ebooks.Compoment.MyButton;
import com.ebooks.Compoment.Table;

import com.ebooks.dao.AudioSachDAO;
import com.ebooks.helper.ShareHelper;
import com.ebooks.model.AudioSach;
import com.ebooks.audio.FileTypeFilter;
import com.ebooks.dao.BangGiaThueDAO;
import com.ebooks.dao.HoaDonChiTietDAO;
import jaco.mp3.player.MP3Player;

import com.ebooks.dao.HoaDonThucUongDAO;
import com.ebooks.dao.HoaDonThueSachDAO;
import com.ebooks.dao.HoaDonTongHopDAO;
import com.ebooks.dao.LoaiSSDAO;
import com.ebooks.dao.NguoiDungDAO;
import com.ebooks.dao.QuanTriVienDAO;
import com.ebooks.dao.SachDAO;
import com.ebooks.dao.TacGiaDAO;
import com.ebooks.dao.TaiKhoanDAO;
import com.ebooks.dao.TheLoaiDAO;
import com.ebooks.dao.ThucUongDAO;
import com.ebooks.helper.DateHelper;
import com.ebooks.helper.DialogHelper;
import com.ebooks.helper.ShareHelper;
import com.ebooks.helper.UtilityHelper;
import com.ebooks.model.HoaDonChiTiet;
import com.ebooks.model.HoaDonThucUong;
import com.ebooks.model.HoaDonThueSach;
import com.ebooks.model.HoaDonTongHop;
import com.ebooks.model.LoaiSS;
import com.ebooks.model.NguoiDung;
import com.ebooks.model.QuanTriVien;
import com.ebooks.model.Sach;
import com.ebooks.model.TacGia;
import com.ebooks.model.TaiKhoan;
import com.ebooks.model.TheLoai;
import com.ebooks.model.ThucUong;
import com.ebooks.main.TableRentCostDialog;
import com.ebooks.model.BangGiaThue;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

/**
 *
 * @author Admin
 */
public class Main extends javax.swing.JFrame {

    boolean KiemThu = false;
    static String MaND;
    static String TenDangNhap;
    static int timeAuido;
    static boolean playPase = false;
    static int indexSeletor = -1;
    static int seconds = 0;
    static int minute = 0;
    static int I = -1;
    //TimeRentalPeriod
    int tic = -1;
    int sec = 0;
    int min = 0;
    int hour = 0;
    //TimeRentalPeriod
    public static DefaultTableModel tblTable;
    String Url = "..\\DuAn01_G3_ebooks\\src\\com\\Content\\imgEbooks\\";
    ImageIcon MainA = new ImageIcon("..\\DuAn01_G3_ebooks\\src\\com\\ebooks\\Icon\\apps (2).png");
    ImageIcon MainB = new ImageIcon("..\\DuAn01_G3_ebooks\\src\\com\\ebooks\\Icon\\apps (1).png");
    ImageIcon ManageB = new ImageIcon("..\\DuAn01_G3_ebooks\\src\\com\\ebooks\\Icon\\business-time 1.png");
    ImageIcon ManageA = new ImageIcon("..\\DuAn01_G3_ebooks\\src\\com\\ebooks\\Icon\\business-time (4).png");
    ImageIcon StatisticalA = new ImageIcon("..\\DuAn01_G3_ebooks\\src\\com\\ebooks\\Icon\\chart-area (4).png");
    ImageIcon StatisticalB = new ImageIcon("..\\DuAn01_G3_ebooks\\src\\com\\ebooks\\Icon\\chart-area (2).png");
    ImageIcon ReadA = new ImageIcon("..\\DuAn01_G3_ebooks\\src\\com\\ebooks\\Icon\\book-alt (4).png");
    ImageIcon ReadB = new ImageIcon("..\\DuAn01_G3_ebooks\\src\\com\\ebooks\\Icon\\book-alt (3).png");
    ImageIcon ListenA = new ImageIcon("..\\DuAn01_G3_ebooks\\src\\com\\ebooks\\Icon\\headphones (4).png");
    ImageIcon ListenB = new ImageIcon("..\\DuAn01_G3_ebooks\\src\\com\\ebooks\\Icon\\headphones (3).png");
    static boolean congTac = false;
    static boolean TimeDl = false;
    DefaultTableModel model;
    AudioSachDAO DAOAU = new AudioSachDAO();
    List<AudioSach> listAudio = new ArrayList();
    int row = 0;

    // Define MP3Player Class From JACO MP3Player Lib
    MP3Player player;
    // Define File For Song
    File songFile;
    // Define Current Directory Like If We Use JFileChooser then where it statys.
    String currentDirectory = "home.user"; // I am using home.user this will call file chooser in user documents.
    // Here I define currentPath of the running app class
    String currentPath;
    // This String Will Be For Image Name or Path
    String imagePath;
    // Now Check If Repeat Button is Enabled or not
    boolean repeat = false;
    boolean muteOnOff = false;
    // Here I am making a boolean for windowCollapsed
    boolean windowCollapsed = false;

    public static List<NguoiDung> listND;
    public static List<TaiKhoan> listTK;

    //model DAO
    TaiKhoanDAO DaoTK = new TaiKhoanDAO();
    NguoiDungDAO DaoND = new NguoiDungDAO();
    ThucUongDAO DaoTU = new ThucUongDAO();
    HoaDonThucUongDAO DaoHDTU = new HoaDonThucUongDAO();
    HoaDonThueSachDAO DaoHDTS = new HoaDonThueSachDAO();
    HoaDonChiTietDAO DaoHDCT = new HoaDonChiTietDAO();
    SachDAO DAOS = new SachDAO();
    TacGiaDAO DAOTG = new TacGiaDAO();
    QuanTriVienDAO DAOQTV = new QuanTriVienDAO();
    TheLoaiDAO DAOTL = new TheLoaiDAO();
    LoaiSSDAO DAOLSS = new LoaiSSDAO();
    HoaDonTongHopDAO DAOHDTH = new HoaDonTongHopDAO();
    BangGiaThueDAO DAOBGT = new BangGiaThueDAO();
    //model 
    NguoiDung nguoiDung = new NguoiDung();
    ThucUong thucUong = new ThucUong();
    HoaDonThucUong hoaDonThucUong = new HoaDonThucUong();
    Sach sach = new Sach();
    TacGia tacGia = new TacGia();
    TheLoai theLoai = new TheLoai();
    BangGiaThue bangGiaThue = new BangGiaThue();
    //List
//    List<NguoiDung> listND = new ArrayList<>();

    List<ThucUong> listTU = new ArrayList<>();
    List<HoaDonThucUong> listHD = new ArrayList<>();
    List<Sach> listS = new ArrayList<>();
    List<TheLoai> listTL = new ArrayList<>();
    List<TacGia> listTG = new ArrayList<>();
    List<BangGiaThue> listGT = new ArrayList<>();
    TimeNotifyDialog timeDialog;

    public Main() {
        initComponents();
        init();
        setBackground(new Color(0, 0, 0, 0));
        movedpnlMenu();
        Date();
        fillTableDanhSachCacSach();
        AppStatus.mainApp = this;
        timeDialog = new TimeNotifyDialog(this, congTac);
        initMoving(this, pnlMainProjebt);
        RentalPeriod();
    }

    public void init() {
        //Set icon, show from
        setIconImage(ShareHelper.APP_ICON);
        new StartUpDiaLog(this, true).setVisible(true);
        new LogInDiaLog(this, true).setVisible(true);
    }

    //-------------------------------------------FILL TABLE------------------------------------------//
    //method fillTable Sach
    public void fillTableSach() {
        DefaultTableModel model;
        model = (DefaultTableModel) tblSach.getModel();
        tblSach.setSelectionBackground(new Color(87, 190, 110));
        model.setRowCount(0);
        try {
            listS = DAOS.selectAll();
            for (Sach sach : listS) {
                Object[] row = {sach.getMaSach(), sach.getTenSach(), DAOTG.findById(sach.getMaTacGia()).getHoTen(), sach.getNgayDang(), sach.getMoTa()};
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    public void fillTableDanhSachCacSach() {
        DefaultTableModel model;
        model = (DefaultTableModel) tblDanhSachCacSach.getModel();
        tblDanhSachCacSach.setSelectionBackground(new Color(87, 190, 110));
        model.setRowCount(0);
        try {
            listS = DAOS.selectName(txtSearch.getText());
            for (Sach sach : listS) {
                Object[] row = {sach.getMaSach(), sach.getTenSach(), DAOTG.findById(sach.getMaTacGia()).getHoTen(), sach.getNgayDang(), sach.getMoTa()};
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    public void fillTableDTTU() {
        DefaultTableModel model = (DefaultTableModel) tblDoanhThuThucUong.getModel();
        model.setRowCount(0);
        List<Object[]> list = DAOHDTH.getDoanhThuThucUong();
        for (Object[] row : list) {
            model.addRow(row);
        }

    }

    public void fillTableDTTS() {
        DefaultTableModel model = (DefaultTableModel) tblDoanhThuThueSach.getModel();
        model.setRowCount(0);
        List<Object[]> listTKTS = DAOHDTH.getDoanhThuThueSach();
        for (Object[] row : listTKTS) {
            model.addRow(row);
        }

    }

    // method fillTableNguoiDung
    public void fillTableNguoiDung() {
        DefaultTableModel model;
        model = (DefaultTableModel) tblNguoiDung.getModel();
        model.setRowCount(0);
        try {
            listND = DaoND.selectAll();
            for (NguoiDung nd : listND) {
                Object[] row = {nd.getMaNguoiDung(), nd.getHoTen(), nd.getSoDienThoai(), nd.isGioiTinh() ? "Nam" : "Nữ"};
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    List<Object[]> listTKTU;

    public void fillTableTKTU(int nam) {
        DefaultTableModel model = (DefaultTableModel) tblTKDTThucUong.getModel();
        model.setRowCount(0);
        /*List<Object[]>*/ listTKTU = DAOHDTH.getTKDoanhThuThucUong(nam);
        for (Object[] row : listTKTU) {
            model.addRow(row);
        }

    }

    List<Object[]> listTKTS;

    public void fillTableTKTS(int nam) {
        DefaultTableModel model = (DefaultTableModel) tblTKDTThueSach.getModel();
        model.setRowCount(0);
        listTKTS = DAOHDTH.getTKDoanhThuThueSach(nam);
        for (Object[] row : listTKTS) {
            model.addRow(row);
        }

    }

    //Fill DU LIEU LEN BANG TAC GIA
    public void fillTableTacGia() {
        DefaultTableModel model;
        model = (DefaultTableModel) tblTacGia.getModel();
        tblTacGia.setSelectionBackground(new Color(87, 190, 110));
        model.setRowCount(0);
        try {
            listTG = DAOTG.selectAll();
            for (TacGia tacGia : listTG) {
                Object[] row = {tacGia.getMaTacGia(), tacGia.getHoTen(), tacGia.getNgaySinh(), tacGia.isGioiTinh() ? "Nam" : "Nữ"};
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    //FILL DU LIEU LEN BANG LOAI SACH
    public void fillTableTheLoai() {
        DefaultTableModel model;
        model = (DefaultTableModel) tblTheLoai.getModel();
        tblTheLoai.setSelectionBackground(new Color(87, 190, 110));
        model.setRowCount(0);
        try {
            listTL = DAOTL.selectAll();
            for (TheLoai theLoai : listTL) {
                Object[] row = {theLoai.getMaTheLoai(), theLoai.getTenTheLoai(), theLoai.getMoTaTheLoai()};
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    // method fillTableAudio
    public void fillTableAudio(Table tbl) {
        model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        tbl.setSelectionBackground(new Color(87, 190, 110));
        listAudio = DAOAU.selectAll();
        try {
            for (AudioSach au : listAudio) {
                model.addRow(new Object[]{au.getMaAudio(), au.getTenAudio(), au.getNgayPhatHanh(), au.getNguoiThu()});
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi fill bảng Audio");
        }
    }

    //method Otion Sach
    public void OtionTableSach(int index) {
        DefaultTableModel model;
        model = (DefaultTableModel) tblSach.getModel();
        model.setRowCount(0);
        listTL = DAOTL.selectAll();
        try {
            listS = DAOS.selectByTheLoai(listTL.get(index).getMaTheLoai());
            for (Sach sach : listS) {
                Object[] row = {sach.getMaSach(), sach.getTenSach(), DAOTG.findById(sach.getMaTacGia()).getHoTen(), sach.getNgayDang(), sach.getMoTa()};
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    //method fillTableThứcUống
    public void fillTableThucUong() {
        DefaultTableModel model;
        model = new DefaultTableModel();
        model.setRowCount(0);
        //set COLUMN
        model.addColumn("Mã Thức Uống");
        model.addColumn("Tên Thức Uống");
        model.addColumn("Giá Tiền");

        listTU = DaoTU.selectAll();
        //tạo hàng và do du lieu
        for (ThucUong tu : listTU) {
            Object[] row = {tu.getMaThucUong(), tu.getTenThucUong(), tu.getGia()};
            model.addRow(row);
            tblThucUong.setModel(model);
        }
    }

    public void fillTableHoaDon() {
        DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        List<Object[]> list = DAOHDTH.getHoaDonTongHop();
        for (Object[] row : list) {
            model.addRow(row);
        }
    }

    //method fillTableTaiKhoan
    public void fillTableTaiKhoan(List<TaiKhoan> list) {
        tblTable = (DefaultTableModel) tblTaiKhoan.getModel();
        tblTable.setRowCount(0);
        try {
            for (TaiKhoan tk : list) {
                Object[] row = {
                    tk.getTenDangNhap(),
                    tk.getMatKhau(),
                    tk.getMaNguoiDung(),
                    tk.isTrangThai() == true ? "Quản trị viên" : "Người dùng"};
                tblTable.addRow(row);
            }
        } catch (Exception e) {
            System.out.println(e);
            DialogHelper.alert(null, "Lỗi truy vấn dữ liệu!");
        }
    }

    //--------------------------------------------FillTable--------------------------------------//
    //----------------------------------------Methor other-------------------------------------//
    public void setModelAudio() {
        //Show MP3 to play music audio
        model = new DefaultTableModel();
        model.addColumn("Mã Audio");
        model.addColumn("Tên Audio");
        model.addColumn("Ngày Phát Hành");
        model.addColumn("Người Thu");
        // model.addColumn("Đường Dẫn");
        tblAudio.setModel(model);
    }

    public ImageIcon ShowImg(String nameImg) {
        ImageIcon imgIcon = new ImageIcon(Url + nameImg);
        Image image = imgIcon.getImage();
        Image newimg = image.getScaledInstance(160, 160, java.awt.Image.SCALE_SMOOTH);
        imgIcon = new ImageIcon(newimg);
        return imgIcon;
    }

    // I am going to create a custom MP3Player Method
    private MP3Player mp3Player() {
        MP3Player mp3Player = new MP3Player();
        return mp3Player;
    }
// Let's Set Volume Down Method It's not necessary to remeber this code.

    private void RentalPeriod() {
        TaiKhoan tk = ShareHelper.USER;
        int Hours = tk.getThoiLuong().getHours();
        int Minute = tk.getThoiLuong().getMinutes();
        int Seconds = tk.getThoiLuong().getSeconds();
        System.out.println(tk.getThoiLuong().getTime());
        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sec += 1;
                if (sec == 60) {
                    min += 1;
                    sec = 0;
                }
                if (min == 60) {
                    hour += 1;
                    min = 0;
                }
                if (Hours == hour) {
                    if (Minute == min) {
                        if (Seconds == sec) {
                            tk.setThoiLuong(Time.valueOf("00:00:00"));
                            DaoTK.update(tk);
                            DialogHelper.alert(null, "Đã Hết Thời Gian Thuê");
                            System.exit(0);

                        }
                    }
                }
            }
        }).start();
    }

    private void runtimeFirst() {
        int timeDislay = timeAuido / 125;
        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if (!playPase) {
                            break;
                        }
                        I++;
                        seconds++;
                        if (seconds == 60) {
                            minute++;
                            seconds = 0;
                        }
                        sliderAudio.setValue(I);
                        sliderAudio.setMaximum(timeDislay);
                        if (seconds < 10) {
                            lblTimeFirst.setText(minute + ":0" + seconds);
                        } else {
                            lblTimeFirst.setText(minute + ":" + seconds);
                        }

                        if (I == timeDislay) {
                            break;
                        }

                        Thread.sleep(1000);   //thread tạm dừng hoạt động trong 20 ms
                    } catch (InterruptedException ex) {
                        break;
                    }
                }
            }
        };
        t.start();

    }

    private void displayTimeAudio() {

        int timeDislay = timeAuido / 125;
        int timeEven = timeDislay / 60;
        int timeSurplus = timeDislay % 60;
        if (timeSurplus < 10) {
            lblTimeLast.setText(timeEven + ":0" + timeSurplus);
        } else {
            lblTimeLast.setText(timeEven + ":" + timeSurplus);
        }

    }

    private void volumeDownControl(Double valueToPlusMinus) {
        // Get Mixer Information From AudioSystem
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        // Now use a for loop to list all mixers
        for (Mixer.Info mixerInfo : mixers) {
            // Get Mixer
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            // Now Get Target Line
            Line.Info[] lineInfos = mixer.getTargetLineInfo();
            // Here again use for loop to list lines
            for (Line.Info lineInfo : lineInfos) {
                // Make a null line
                Line line = null;
                // Make a boolean as opened
                boolean opened = true;
                // Now use try exception for opening a line
                try {
                    line = mixer.getLine(lineInfo);
                    opened = line.isOpen() || line instanceof Clip;
                    // Now Check If Line Is not Opened
                    if (!opened) {
                        // Open Line
                        line.open();
                    }
                    // Make a float control
                    FloatControl volControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                    // Get Current Volume Now
                    float currentVolume = volControl.getValue();
                    // Make a temp double variable and store valuePlusMinus
                    Double volumeToCut = valueToPlusMinus;
                    // Make a float and calculate the addition or subtraction in volume
                    float changedCalc = (float) ((float) currentVolume - (double) volumeToCut);
                    // Now Set This Changed Value Into Volume Line.
                    volControl.setValue(changedCalc);

                } catch (LineUnavailableException lineException) {
                } catch (IllegalArgumentException illException) {
                } finally {
                    // Close Line If it opened
                    if (line != null && !opened) {
                        line.close();
                    }
                }
            }
        }
    }

    // Let's Set Volume Up Method It's not necessary to remeber this code.
    private void volumeUpControl(Double valueToPlusMinus) {
        // Get Mixer Information From AudioSystem
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        // Now use a for loop to list all mixers
        for (Mixer.Info mixerInfo : mixers) {
            // Get Mixer
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            // Now Get Target Line
            Line.Info[] lineInfos = mixer.getTargetLineInfo();
            // Here again use for loop to list lines
            for (Line.Info lineInfo : lineInfos) {
                // Make a null line
                Line line = null;
                // Make a boolean as opened
                boolean opened = true;
                // Now use try exception for opening a line
                try {
                    line = mixer.getLine(lineInfo);
                    opened = line.isOpen() || line instanceof Clip;
                    // Now Check If Line Is not Opened
                    if (!opened) {
                        // Open Line
                        line.open();
                    }
                    // Make a float control
                    FloatControl volControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                    // Get Current Volume Now
                    float currentVolume = volControl.getValue();
                    // Make a temp double variable and store valuePlusMinus
                    Double volumeToCut = valueToPlusMinus;
                    // Make a float and calculate the addition or subtraction in volume
                    float changedCalc = (float) ((float) currentVolume + (double) volumeToCut);
                    // Now Set This Changed Value Into Volume Line.
                    volControl.setValue(changedCalc);

                } catch (LineUnavailableException lineException) {
                } catch (IllegalArgumentException illException) {
                } finally {
                    // Close Line If it opened
                    if (line != null && !opened) {
                        line.close();
                    }
                }
            }
        }
    }

    // Let's Set Volume Method It's not necessary to remeber this code.
    private void volumeControl(Double valueToPlusMinus) {
        // Get Mixer Information From AudioSystem
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        // Now use a for loop to list all mixers
        for (Mixer.Info mixerInfo : mixers) {
            // Get Mixer
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            // Now Get Target Line
            Line.Info[] lineInfos = mixer.getTargetLineInfo();
            // Here again use for loop to list lines
            for (Line.Info lineInfo : lineInfos) {
                // Make a null line
                Line line = null;
                // Make a boolean as opened
                boolean opened = true;
                // Now use try exception for opening a line
                try {
                    line = mixer.getLine(lineInfo);
                    opened = line.isOpen() || line instanceof Clip;
                    // Now Check If Line Is not Opened
                    if (!opened) {
                        // Open Line
                        line.open();
                    }
                    // Make a float control
                    FloatControl volControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                    // Get Current Volume Now
                    float currentVolume = volControl.getValue();
                    // Make a temp double variable and store valuePlusMinus
                    Double volumeToCut = valueToPlusMinus;
                    // Make a float and calculate the addition or subtraction in volume
                    float changedCalc = (float) ((double) volumeToCut);
                    // Now Set This Changed Value Into Volume Line.
                    volControl.setValue(changedCalc);

                } catch (LineUnavailableException lineException) {
                } catch (IllegalArgumentException illException) {
                } finally {
                    // Close Line If it opened
                    if (line != null && !opened) {
                        line.close();
                    }
                }
            }
        }
    }

    public void movedpnlMenu() {
        if (ShareHelper.isManager()) {
            pnlManage.setVisible(true);
            pnlStatistical.setVisible(true);
            pnlRead.setLocation(15, 260);
            pnlListen.setLocation(15, 340);
            ImageIcon icon = new ImageIcon("..\\DuAn01_G3_ebooks\\src\\com\\ebooks\\Image\\AvataQTV.png");
            imageAvatar1.setIcon(icon);
        } else {
            pnlManage.setVisible(false);
            pnlStatistical.setVisible(false);
            pnlRead.setLocation(new Point(15, 100));
            pnlListen.setLocation(new Point(15, 180));
            ImageIcon icon = new ImageIcon("..\\DuAn01_G3_ebooks\\src\\com\\ebooks\\Image\\AvataND.png");
            imageAvatar1.setIcon(icon);
        }
    }

    public void Date() {
        new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date now = new Date();
                SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss a");
                SimpleDateFormat formats = new SimpleDateFormat("dd/MM/yyyy");
                String Time = format.format(now);
                String Day = formats.format(now);
                lblTime.setText(Time);
                lblDay.setText(Day);
            }
        }).start();

    }

    public void SortSach() {
        try {
            Comparator<Sach> comp = new Comparator<Sach>() {

                @Override
                public int compare(Sach o1, Sach o2) {
                    return o1.getTenSach().compareTo(o2.getTenSach());
                }
            };
            Collections.sort(listS, comp);
            DefaultTableModel model;
            model = (DefaultTableModel) tblSach.getModel();
            tblSach.setSelectionBackground(new Color(87, 190, 110));
            model.setRowCount(0);//tăng dần
            for (Sach sach : listS) {
                Object[] row = {sach.getMaSach(), sach.getTenSach(), DAOTG.findById(sach.getMaTacGia()).getHoTen(), sach.getNgayDang(), sach.getMoTa()};
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void SortAudio() {
        try {
            Comparator<AudioSach> comp = new Comparator<AudioSach>() {

                @Override
                public int compare(AudioSach o1, AudioSach o2) {
                    return o1.getTenAudio().compareTo(o2.getTenAudio());
                }
            };
            Collections.sort(listAudio, comp);            //tăng dần
            model = (DefaultTableModel) tblAudioQL.getModel();
            model.setRowCount(0);
            tblAudioQL.setSelectionBackground(new Color(87, 190, 110));
            for (AudioSach au : listAudio) {
                model.addRow(new Object[]{au.getMaAudio(), au.getTenAudio(), au.getNgayPhatHanh(), au.getNguoiThu()});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void SetColorPanel(JPanel panel, boolean bl) {
        if (bl) {
            panel.setBackground(new Color(145, 227, 168));
        } else {
            panel.setBackground(new Color(205, 239, 215));

        }
    }

    public List fillDuplicate(List<Integer> list) {
        int sizeList = list.size();
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j))) {
                    list.remove(j);
                }
            }
        }

        return list;
    }

    public void SetColorIconBtn(MyButton btn, boolean bl, ImageIcon iconA, ImageIcon iconB) {
        if (bl) {
            btn.setIcon(iconA);
            btn.setBackground(new Color(145, 227, 168));
            btn.setBoderColor(new Color(145, 227, 168));
        } else {
            btn.setIcon(iconB);
            btn.setBackground(new Color(205, 239, 215));
            btn.setBoderColor(new Color(205, 239, 215));
        }
    }
    private int x;
    private int y;

    public void initMoving(JFrame frame, JPanel panel) {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                x = me.getX();
                y = me.getY();
            }

        });
        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                frame.setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
            }
        });
    }

    public void DisplayReadPanel() {
        SetColorPanel(pnlMain, false);
        SetColorPanel(pnlManage, false);
        SetColorPanel(pnlStatistical, false);
        SetColorPanel(pnlRead, true);
        SetColorPanel(pnlListen, false);
        //SetColorBtn
        SetColorIconBtn(btnMain, false, MainA, MainB);
        SetColorIconBtn(btnManage, false, ManageA, ManageB);
        SetColorIconBtn(btnStatistical, false, StatisticalA, StatisticalB);
        SetColorIconBtn(btnRead, true, ReadA, ReadB);
        SetColorIconBtn(btnListen, false, ListenA, ListenB);
        //SetFrameHienThi
        pnlFrameMain.show(false);
        pnlFrameManage.show(false);
        pnlFrameStatistical.show(false);
        pnlFrameRead.show(true);
        pnlFrameListen.show(false);
    }

    //----------------------------------------Methor other-------------------------------------//
    //---------------------------------------DELETE METHOD------------------------------------//
    //method deleteNguoiDung
    private void deleteNguoiDung() {
        int index = tblNguoiDung.getSelectedRow();
        if (DialogHelper.confirm(this, "Bạn thực sự muốn xóa người dùng này?")) {
            String MaND = tblNguoiDung.getValueAt(index, 0).toString();
            try {
                TaiKhoan tk = DaoTK.findByIdND(MaND);
                if (tk != null) {
                    boolean kq = DialogHelper.confirm(this, "Người Dùng Đã Có Tài khoản bạn muốn xóa ?");
                    if (kq) {
                        DaoTK.delete(tk.getTenDangNhap());
                    }
                }
                DaoND.delete(MaND);
                fillTableNguoiDung();
                DialogHelper.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Xóa thất bại!");
            }
        }
    }

    //method deleteTaiKhoan
    private void deleteTaiKhoan() {
        int index = tblTaiKhoan.getSelectedRow();
        if (DialogHelper.confirm(this, "Bạn thực sự muốn xóa người dùng này?")) {
            String TenDangNhap = tblTaiKhoan.getValueAt(index, 0).toString();

            try {
                QuanTriVien qtv = DAOQTV.findById(TenDangNhap);
                if (qtv != null) {
                    boolean kq = DialogHelper.confirm(this, "Bạn Có muốn Xóa Quản Trị Viên Này");
                    if (kq) {
                        DAOQTV.delete(qtv.getTenDangNhap());
                    }
                }
                DaoTK.delete(TenDangNhap);
                LoadTaiKhoan();
                this.fillTableTaiKhoan(listTK);
                DialogHelper.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Xóa thất bại!");
            }
        }
    }

    //method deleteTacGia
    private void deleteTacGia() {
        int index = tblTacGia.getSelectedRow();
        if (index != -1) {
            if (DialogHelper.confirm(this, "Bạn thực sự muốn xóa người dùng này?")) {
                String MaTG = tblTacGia.getValueAt(index, 0).toString();
                try {
                    DAOTG.delete(MaTG);
                    fillTableTacGia();
                    DialogHelper.alert(this, "Xóa thành công!");
                } catch (Exception e) {
                    DialogHelper.alert(this, "Xóa thất bại!");

                }
            }
        }else {
            DialogHelper.alert(this,"Hãy chọn tác giả");
        }

    }

    private void deleteTheLoai() {
        int index = tblTheLoai.getSelectedRow();
        if (DialogHelper.confirm(this, "Bạn thực sự muốn xóa người dùng này?")) {
            String MaTL = tblTheLoai.getValueAt(index, 0).toString();
            try {
                DAOTL.delete(MaTL);
                fillTableTheLoai();
                DialogHelper.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Xóa thất bại!");

            }
        }
    }

    private void DeleteHoaDon() {
        int indexTab = tabDoanhThu.getSelectedIndex();
        if (indexTab == 0) {
            DialogHelper.alert(this, "Hãy Xóa Hóa Đơn Thức Uống Hoặc Thuê Sách");
        } else if (indexTab == 1) {
            try {
                int index = tblDoanhThuThucUong.getSelectedRow();
                if (index != -1) {
                    DaoHDCT.deleteByHD(String.valueOf(tblDoanhThuThucUong.getValueAt(index, 2)));
                    DaoHDTU.delete(String.valueOf(tblDoanhThuThucUong.getValueAt(index, 2)));
                    fillTableDTTU();
                }

            } catch (Exception e) {
                DialogHelper.alert(this, "lỗi Xóa hóa đơn Thức Uống");
            }

        } else if (indexTab == 2) {
            try {
                int index = tblDoanhThuThueSach.getSelectedRow();
                if (index != -1) {
                    DaoHDTS.delete(String.valueOf(tblDoanhThuThueSach.getValueAt(index, 3)));
                    fillTableDTTS();
                }
            } catch (Exception e) {
                DialogHelper.alert(this, "lỗi Xóa hóa đơn Thức Uống");
            }
        }

    }

    //---------------------------------------DELETE METHOD------------------------------------//
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMainProjebt = new com.ebooks.Compoment.PanelBorder();
        lblLogo = new javax.swing.JLabel();
        pnlMenu = new com.ebooks.Compoment.PanelRadius();
        pnlMain = new com.ebooks.Compoment.PanelRadius();
        btnMain = new com.ebooks.Compoment.MyButton();
        pnlManage = new com.ebooks.Compoment.PanelRadius();
        btnManage = new com.ebooks.Compoment.MyButton();
        pnlStatistical = new com.ebooks.Compoment.PanelRadius();
        btnStatistical = new com.ebooks.Compoment.MyButton();
        pnlRead = new com.ebooks.Compoment.PanelRadius();
        btnRead = new com.ebooks.Compoment.MyButton();
        pnlListen = new com.ebooks.Compoment.PanelRadius();
        btnListen = new com.ebooks.Compoment.MyButton();
        pnlBossMain = new javax.swing.JPanel();
        pnlFrameMain = new com.ebooks.Compoment.PanelRadius();
        pnlMenuBooks = new com.ebooks.Compoment.PanelRadius();
        pnl1tuanLamViec = new com.ebooks.Compoment.PanelRadius();
        imageBoder1 = new com.ebooks.Compoment.ImageBoder();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        panelRadius6 = new com.ebooks.Compoment.PanelRadius();
        imageBoder2 = new com.ebooks.Compoment.ImageBoder();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        panelRadius7 = new com.ebooks.Compoment.PanelRadius();
        imageBoder3 = new com.ebooks.Compoment.ImageBoder();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        panelRadius8 = new com.ebooks.Compoment.PanelRadius();
        imageBoder4 = new com.ebooks.Compoment.ImageBoder();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDanhSachCacSach = new com.ebooks.Compoment.Table();
        panelRadius4 = new com.ebooks.Compoment.PanelRadius();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        pnlFrameManage = new com.ebooks.Compoment.PanelRadius();
        jLabel17 = new javax.swing.JLabel();
        tabQuanLy = new com.ebooks.Compoment.MaterialTabbed();
        panelRadius9 = new com.ebooks.Compoment.PanelRadius();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTheLoai = new com.ebooks.Compoment.Table();
        btnLastLoaiSach = new com.ebooks.Compoment.MyButton();
        btnFirstLoaiSach = new com.ebooks.Compoment.MyButton();
        btnPrevLoaiSach = new com.ebooks.Compoment.MyButton();
        btnNextLoaiSach = new com.ebooks.Compoment.MyButton();
        btnThemLoaiSach = new com.ebooks.Compoment.MyButton();
        btnSuaLoaiSach = new com.ebooks.Compoment.MyButton();
        btnXoaLoaiSach = new com.ebooks.Compoment.MyButton();
        panelRadius10 = new com.ebooks.Compoment.PanelRadius();
        btnFirstSach = new com.ebooks.Compoment.MyButton();
        btnNextSach = new com.ebooks.Compoment.MyButton();
        btnPrevSach = new com.ebooks.Compoment.MyButton();
        btnLastSach = new com.ebooks.Compoment.MyButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSach = new com.ebooks.Compoment.Table();
        panelRadius17 = new com.ebooks.Compoment.PanelRadius();
        jLabel18 = new javax.swing.JLabel();
        cboTheLoai = new javax.swing.JComboBox<>();
        btnSapXepSach = new com.ebooks.Compoment.MyButton();
        btnThemSach = new com.ebooks.Compoment.MyButton();
        btnCapNhatSach = new com.ebooks.Compoment.MyButton();
        btnXoaSach = new com.ebooks.Compoment.MyButton();
        panelRadius11 = new com.ebooks.Compoment.PanelRadius();
        panelRadius31 = new com.ebooks.Compoment.PanelRadius();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblAudioQL = new com.ebooks.Compoment.Table();
        btnSapXepTheoTen = new com.ebooks.Compoment.MyButton();
        btnThemAudio = new com.ebooks.Compoment.MyButton();
        btnSuaAudio = new com.ebooks.Compoment.MyButton();
        btnXoaAudio = new com.ebooks.Compoment.MyButton();
        btnFirstAudio = new com.ebooks.Compoment.MyButton();
        btnPrevAudio = new com.ebooks.Compoment.MyButton();
        btnNextAudio = new com.ebooks.Compoment.MyButton();
        btnLastAudio = new com.ebooks.Compoment.MyButton();
        panelRadius12 = new com.ebooks.Compoment.PanelRadius();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblTacGia = new com.ebooks.Compoment.Table();
        btnFirstTacGia = new com.ebooks.Compoment.MyButton();
        btnPrevTacGia = new com.ebooks.Compoment.MyButton();
        btnNextTacGia = new com.ebooks.Compoment.MyButton();
        btnLastTacGia = new com.ebooks.Compoment.MyButton();
        btnThemTacGia = new com.ebooks.Compoment.MyButton();
        btnSuaThongTin = new com.ebooks.Compoment.MyButton();
        btnXoaTacGia = new com.ebooks.Compoment.MyButton();
        panelRadius13 = new com.ebooks.Compoment.PanelRadius();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblTaiKhoan = new com.ebooks.Compoment.Table();
        btnLastTaiKhoan = new com.ebooks.Compoment.MyButton();
        btnNextTaiKhoan = new com.ebooks.Compoment.MyButton();
        btnPrevTaiKhoan = new com.ebooks.Compoment.MyButton();
        btnFirstTaiKhoan = new com.ebooks.Compoment.MyButton();
        btnSuaTaiKhoan = new com.ebooks.Compoment.MyButton();
        btnXoaTaiKhoan = new com.ebooks.Compoment.MyButton();
        btnThemTaiKhoan = new com.ebooks.Compoment.MyButton();
        panelRadius14 = new com.ebooks.Compoment.PanelRadius();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblNguoiDung = new com.ebooks.Compoment.Table();
        btnLastNguoiDung = new com.ebooks.Compoment.MyButton();
        btnNextNguoiDung = new com.ebooks.Compoment.MyButton();
        btnPrevNguoiDung = new com.ebooks.Compoment.MyButton();
        btnFirstNguoiDung = new com.ebooks.Compoment.MyButton();
        btnXoaNguoiDung = new com.ebooks.Compoment.MyButton();
        btnSuaNguoiDung = new com.ebooks.Compoment.MyButton();
        btnThemNguoiDung = new com.ebooks.Compoment.MyButton();
        pnlThucUong = new com.ebooks.Compoment.PanelRadius();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblThucUong = new com.ebooks.Compoment.Table();
        btnFirstThucUong = new com.ebooks.Compoment.MyButton();
        btnPreThucUong = new com.ebooks.Compoment.MyButton();
        btnNextThucUong = new com.ebooks.Compoment.MyButton();
        btnLastThucUong = new com.ebooks.Compoment.MyButton();
        btnThucUong = new com.ebooks.Compoment.MyButton();
        btnSuaThongTinThucUong = new com.ebooks.Compoment.MyButton();
        btnXoaThucUong = new com.ebooks.Compoment.MyButton();
        pnlHoaDon = new com.ebooks.Compoment.PanelRadius();
        btnThemHoaDon = new com.ebooks.Compoment.MyButton();
        btnSuaThongTinHoaDon = new com.ebooks.Compoment.MyButton();
        btnXoaThongTinHoaDon = new com.ebooks.Compoment.MyButton();
        tabDoanhThu = new com.ebooks.Compoment.MaterialTabbed();
        panelRadius5 = new com.ebooks.Compoment.PanelRadius();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblHoaDon = new com.ebooks.Compoment.Table();
        panelRadius15 = new com.ebooks.Compoment.PanelRadius();
        jScrollPane16 = new javax.swing.JScrollPane();
        tblDoanhThuThucUong = new com.ebooks.Compoment.Table();
        panelRadius16 = new com.ebooks.Compoment.PanelRadius();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblDoanhThuThueSach = new com.ebooks.Compoment.Table();
        panelRadius1 = new com.ebooks.Compoment.PanelRadius();
        jScrollPane15 = new javax.swing.JScrollPane();
        tblBangGia = new com.ebooks.Compoment.Table();
        btnPreBangGia = new com.ebooks.Compoment.MyButton();
        btnFirstBangGia = new com.ebooks.Compoment.MyButton();
        btnNextBangGia = new com.ebooks.Compoment.MyButton();
        btnLastBangGia = new com.ebooks.Compoment.MyButton();
        btnThemBangGia = new com.ebooks.Compoment.MyButton();
        btnSuaBangGia = new com.ebooks.Compoment.MyButton();
        btnXoaBangGia = new com.ebooks.Compoment.MyButton();
        pnlFrameStatistical = new com.ebooks.Compoment.PanelRadius();
        jLabel20 = new javax.swing.JLabel();
        tabThongKe = new com.ebooks.Compoment.MaterialTabbed();
        panelRadius18 = new com.ebooks.Compoment.PanelRadius();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblTKDTThucUong = new com.ebooks.Compoment.Table();
        panelRadius27 = new com.ebooks.Compoment.PanelRadius();
        jLabel25 = new javax.swing.JLabel();
        cboNamTU = new javax.swing.JComboBox<>();
        bntFistDTTU = new com.ebooks.Compoment.MyButton();
        btnPreDTTU = new com.ebooks.Compoment.MyButton();
        btnNextDTTU = new com.ebooks.Compoment.MyButton();
        btnLastDTTU = new com.ebooks.Compoment.MyButton();
        panelRadius20 = new com.ebooks.Compoment.PanelRadius();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblTKDTThueSach = new com.ebooks.Compoment.Table();
        panelRadius25 = new com.ebooks.Compoment.PanelRadius();
        jLabel22 = new javax.swing.JLabel();
        cboNamTS = new javax.swing.JComboBox<>();
        btnFirstDTTS = new com.ebooks.Compoment.MyButton();
        btnPreDTTS = new com.ebooks.Compoment.MyButton();
        btnNextDTTS = new com.ebooks.Compoment.MyButton();
        btnLastDTTS = new com.ebooks.Compoment.MyButton();
        pnlFrameRead = new com.ebooks.Compoment.PanelRadius();
        materialTabbed3 = new com.ebooks.Compoment.MaterialTabbed();
        pnlReader = new com.ebooks.Compoment.PanelRadius();
        scrollpanePDF = new javax.swing.JScrollPane();
        pnlListSachDoc = new com.ebooks.Compoment.PanelRadius();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblListSachDoc = new com.ebooks.Compoment.Table();
        jLabel19 = new javax.swing.JLabel();
        pnlFrameListen = new com.ebooks.Compoment.PanelRadius();
        panelRadius30 = new com.ebooks.Compoment.PanelRadius();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblAudio = new com.ebooks.Compoment.Table();
        jLabel27 = new javax.swing.JLabel();
        lblTenNguoiThu = new javax.swing.JLabel();
        btnPlay = new com.ebooks.Compoment.MyButton();
        lblTimeLast = new javax.swing.JLabel();
        lblTimeFirst = new javax.swing.JLabel();
        sliderAudio = new com.ebooks.Compoment.Slider();
        lblTenAudio = new javax.swing.JLabel();
        lblVolumeRepeat = new javax.swing.JLabel();
        lblVolumeDown = new javax.swing.JLabel();
        lblVolumeUp = new javax.swing.JLabel();
        lblVolumeFull = new javax.swing.JLabel();
        lblAnhAudio = new javax.swing.JLabel();
        panelRadius2 = new com.ebooks.Compoment.PanelRadius();
        jLabel1 = new javax.swing.JLabel();
        txtSearch = new com.ebooks.Compoment.SearchText();
        imageAvatar1 = new com.ebooks.Compoment.ImageAvatar();
        pnlSetting = new com.ebooks.Compoment.PanelRadius();
        btnSetting = new com.ebooks.Compoment.MyButton();
        pnlOff = new com.ebooks.Compoment.PanelRadius();
        lblOff = new javax.swing.JLabel();
        lblDay = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        btnBell = new com.ebooks.Compoment.MyButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hệ Thống eBooks");
        setUndecorated(true);

        pnlMainProjebt.setBackground(new java.awt.Color(251, 251, 251));
        pnlMainProjebt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlMainProjebtMousePressed(evt);
            }
        });
        pnlMainProjebt.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Image/nerds-removebg-preview.png"))); // NOI18N
        pnlMainProjebt.add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        pnlMenu.setBackground(new java.awt.Color(205, 239, 215));
        pnlMenu.setRadius(20);
        pnlMenu.setLayout(null);

        pnlMain.setBackground(new java.awt.Color(145, 227, 168));
        pnlMain.setRadius(15);
        pnlMain.setLayout(new java.awt.GridBagLayout());

        btnMain.setBackground(new java.awt.Color(145, 227, 168));
        btnMain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/apps (2).png"))); // NOI18N
        btnMain.setBoderColor(new java.awt.Color(145, 227, 168));
        btnMain.setFocusable(false);
        btnMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainActionPerformed(evt);
            }
        });
        pnlMain.add(btnMain, new java.awt.GridBagConstraints());

        pnlMenu.add(pnlMain);
        pnlMain.setBounds(15, 20, 60, 60);

        pnlManage.setBackground(new java.awt.Color(205, 239, 215));
        pnlManage.setRadius(15);
        pnlManage.setLayout(new java.awt.GridBagLayout());

        btnManage.setBackground(new java.awt.Color(205, 239, 215));
        btnManage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/business-time 1.png"))); // NOI18N
        btnManage.setBoderColor(new java.awt.Color(205, 239, 215));
        btnManage.setColorOver(new java.awt.Color(205, 239, 215));
        btnManage.setFocusable(false);
        btnManage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageActionPerformed(evt);
            }
        });
        pnlManage.add(btnManage, new java.awt.GridBagConstraints());

        pnlMenu.add(pnlManage);
        pnlManage.setBounds(15, 100, 60, 60);

        pnlStatistical.setBackground(new java.awt.Color(205, 239, 215));
        pnlStatistical.setRadius(15);
        pnlStatistical.setLayout(new java.awt.GridBagLayout());

        btnStatistical.setBackground(new java.awt.Color(205, 239, 215));
        btnStatistical.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/chart-area (2).png"))); // NOI18N
        btnStatistical.setBoderColor(new java.awt.Color(205, 239, 215));
        btnStatistical.setFocusable(false);
        btnStatistical.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatisticalActionPerformed(evt);
            }
        });
        pnlStatistical.add(btnStatistical, new java.awt.GridBagConstraints());

        pnlMenu.add(pnlStatistical);
        pnlStatistical.setBounds(15, 180, 60, 60);

        pnlRead.setBackground(new java.awt.Color(205, 239, 215));
        pnlRead.setRadius(15);
        pnlRead.setLayout(new java.awt.GridBagLayout());

        btnRead.setBackground(new java.awt.Color(205, 239, 215));
        btnRead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/book-alt (3).png"))); // NOI18N
        btnRead.setBoderColor(new java.awt.Color(205, 239, 215));
        btnRead.setFocusable(false);
        btnRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReadActionPerformed(evt);
            }
        });
        pnlRead.add(btnRead, new java.awt.GridBagConstraints());

        pnlMenu.add(pnlRead);
        pnlRead.setBounds(15, 260, 60, 60);

        pnlListen.setBackground(new java.awt.Color(205, 239, 215));
        pnlListen.setRadius(15);
        pnlListen.setLayout(new java.awt.GridBagLayout());

        btnListen.setBackground(new java.awt.Color(205, 239, 215));
        btnListen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/headphones (3).png"))); // NOI18N
        btnListen.setBoderColor(new java.awt.Color(205, 239, 215));
        btnListen.setFocusable(false);
        btnListen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListenActionPerformed(evt);
            }
        });
        pnlListen.add(btnListen, new java.awt.GridBagConstraints());

        pnlMenu.add(pnlListen);
        pnlListen.setBounds(15, 340, 60, 60);

        pnlMainProjebt.add(pnlMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 90, 450));

        pnlBossMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlBossMain.setLayout(new java.awt.CardLayout());

        pnlFrameMain.setBackground(new java.awt.Color(251, 251, 251));
        pnlFrameMain.setRadius(15);
        pnlFrameMain.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlMenuBooks.setBackground(new java.awt.Color(205, 239, 215));
        pnlMenuBooks.setRadius(15);
        pnlMenuBooks.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnl1tuanLamViec.setBackground(new java.awt.Color(255, 255, 255));
        pnl1tuanLamViec.setRadius(20);
        pnl1tuanLamViec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnl1tuanLamViecMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnl1tuanLamViecMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnl1tuanLamViecMouseExited(evt);
            }
        });
        pnl1tuanLamViec.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imageBoder1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        imageBoder1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/imgEbooks/1tuanLamViec4Gio.png"))); // NOI18N
        pnl1tuanLamViec.add(imageBoder1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 50, 70));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("1 Tuần Làm Việc 4 giờ");
        pnl1tuanLamViec.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 120, -1));

        jLabel6.setText("Timothy Ferriss");
        pnl1tuanLamViec.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 45, -1, -1));

        jLabel7.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        jLabel7.setText("310/310");
        pnl1tuanLamViec.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, -1, -1));

        pnlMenuBooks.add(pnl1tuanLamViec, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 290, 90));

        panelRadius6.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius6.setRadius(20);
        panelRadius6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelRadius6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelRadius6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelRadius6MouseExited(evt);
            }
        });
        panelRadius6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imageBoder2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        imageBoder2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/imgEbooks/BanCoTheDamPhanBatCuDieuGi.jpg"))); // NOI18N
        panelRadius6.add(imageBoder2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 50, 70));

        jLabel10.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        jLabel10.setText("187/187");
        panelRadius6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Bạn Có Thể Đàm Phán");
        panelRadius6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 120, -1));

        jLabel8.setText("Hebr Cohen");
        panelRadius6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, -1));

        pnlMenuBooks.add(panelRadius6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 290, 90));

        panelRadius7.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius7.setRadius(20);
        panelRadius7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelRadius7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelRadius7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelRadius7MouseExited(evt);
            }
        });
        panelRadius7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imageBoder3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        imageBoder3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/imgEbooks/BucXucKhongLamTaVoCan.jpg"))); // NOI18N
        panelRadius7.add(imageBoder3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 50, 70));

        jLabel13.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        jLabel13.setText("127/127");
        panelRadius7.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Bức Xúc Không Làm Bạn");
        panelRadius7.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 120, -1));

        jLabel11.setText("Hoàng Giang Đặng");
        panelRadius7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, -1));

        pnlMenuBooks.add(panelRadius7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 290, 90));

        panelRadius8.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius8.setRadius(20);
        panelRadius8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelRadius8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelRadius8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelRadius8MouseExited(evt);
            }
        });
        panelRadius8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imageBoder4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/imgEbooks/GiaDiepMang.png"))); // NOI18N
        panelRadius8.add(imageBoder4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 50, 80));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("Gián Điệp Mạng");
        panelRadius8.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 120, -1));

        jLabel14.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        jLabel14.setText("516/516");
        panelRadius8.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, -1, -1));

        jLabel16.setText("Clifford Stoll");
        panelRadius8.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, -1));

        pnlMenuBooks.add(panelRadius8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 290, 90));

        jLabel4.setFont(new java.awt.Font("Open Sans", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(17, 49, 30));
        jLabel4.setText("Hạng Sách Tốt");
        pnlMenuBooks.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        pnlFrameMain.add(pnlMenuBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 10, 330, 520));

        jLabel2.setFont(new java.awt.Font("Open Sans", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(17, 49, 30));
        jLabel2.setText("Danh Sách Các Sách");
        pnlFrameMain.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, -1, -1));

        tblDanhSachCacSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Sách ", "Tên Sách", "Tên Tác Giả", "Tên Tác Giả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhSachCacSach.setRadius(10);
        tblDanhSachCacSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblDanhSachCacSachMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblDanhSachCacSach);

        pnlFrameMain.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 700, 260));

        panelRadius4.setBackground(new java.awt.Color(205, 239, 215));
        panelRadius4.setRadius(20);
        panelRadius4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Image/13.png"))); // NOI18N
        panelRadius4.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, 260, 140));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Image/181-removebg-preview.png"))); // NOI18N
        panelRadius4.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, -1, -1));

        jLabel34.setFont(new java.awt.Font("Inter SemiBold", 0, 36)); // NOI18N
        jLabel34.setText("NERDS");
        panelRadius4.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jLabel35.setFont(new java.awt.Font("Inter Medium", 0, 18)); // NOI18N
        jLabel35.setText("Học tập là ánh mắt của tri thức ");
        panelRadius4.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        pnlFrameMain.add(panelRadius4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 700, 180));

        pnlBossMain.add(pnlFrameMain, "card2");

        pnlFrameManage.setBackground(new java.awt.Color(205, 239, 215));
        pnlFrameManage.setRadius(15);
        pnlFrameManage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Open Sans", 1, 28)); // NOI18N
        jLabel17.setText("Quản Lý");
        pnlFrameManage.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        tabQuanLy.setClorSroll(new java.awt.Color(87, 190, 110));
        tabQuanLy.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tabQuanLy.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabQuanLyStateChanged(evt);
            }
        });

        panelRadius9.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblTheLoai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã Loại Sách", "Tên Loại Sách", "Mô Tả"
            }
        ));
        tblTheLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblTheLoaiMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblTheLoai);

        panelRadius9.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 840, 350));

        btnLastLoaiSach.setBackground(new java.awt.Color(145, 227, 168));
        btnLastLoaiSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-right.png"))); // NOI18N
        btnLastLoaiSach.setBoderColor(new java.awt.Color(145, 227, 168));
        btnLastLoaiSach.setRadius(10);
        btnLastLoaiSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastLoaiSachActionPerformed(evt);
            }
        });
        panelRadius9.add(btnLastLoaiSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 380, 60, 40));

        btnFirstLoaiSach.setBackground(new java.awt.Color(145, 227, 168));
        btnFirstLoaiSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-left.png"))); // NOI18N
        btnFirstLoaiSach.setBoderColor(new java.awt.Color(145, 227, 168));
        btnFirstLoaiSach.setRadius(10);
        btnFirstLoaiSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstLoaiSachActionPerformed(evt);
            }
        });
        panelRadius9.add(btnFirstLoaiSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 60, 40));

        btnPrevLoaiSach.setBackground(new java.awt.Color(145, 227, 168));
        btnPrevLoaiSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-left.png"))); // NOI18N
        btnPrevLoaiSach.setBoderColor(new java.awt.Color(145, 227, 168));
        btnPrevLoaiSach.setRadius(10);
        btnPrevLoaiSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevLoaiSachActionPerformed(evt);
            }
        });
        panelRadius9.add(btnPrevLoaiSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 60, 40));

        btnNextLoaiSach.setBackground(new java.awt.Color(145, 227, 168));
        btnNextLoaiSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-right.png"))); // NOI18N
        btnNextLoaiSach.setBoderColor(new java.awt.Color(145, 227, 168));
        btnNextLoaiSach.setRadius(10);
        btnNextLoaiSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextLoaiSachActionPerformed(evt);
            }
        });
        panelRadius9.add(btnNextLoaiSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 60, 40));

        btnThemLoaiSach.setBackground(new java.awt.Color(87, 190, 110));
        btnThemLoaiSach.setForeground(new java.awt.Color(255, 255, 255));
        btnThemLoaiSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/add-document.png"))); // NOI18N
        btnThemLoaiSach.setText("Thêm Loại Sách");
        btnThemLoaiSach.setBoderColor(new java.awt.Color(87, 190, 110));
        btnThemLoaiSach.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnThemLoaiSach.setRadius(10);
        btnThemLoaiSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLoaiSachActionPerformed(evt);
            }
        });
        panelRadius9.add(btnThemLoaiSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 20, 150, 40));

        btnSuaLoaiSach.setBackground(new java.awt.Color(87, 190, 110));
        btnSuaLoaiSach.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaLoaiSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/refresh (1).png"))); // NOI18N
        btnSuaLoaiSach.setText("Sửa Thông Tin");
        btnSuaLoaiSach.setBoderColor(new java.awt.Color(87, 190, 110));
        btnSuaLoaiSach.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnSuaLoaiSach.setRadius(10);
        btnSuaLoaiSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaLoaiSachActionPerformed(evt);
            }
        });
        panelRadius9.add(btnSuaLoaiSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 80, 150, 40));

        btnXoaLoaiSach.setBackground(new java.awt.Color(253, 127, 127));
        btnXoaLoaiSach.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaLoaiSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/delete-document.png"))); // NOI18N
        btnXoaLoaiSach.setText("Xóa Loại Sách");
        btnXoaLoaiSach.setBoderColor(new java.awt.Color(253, 127, 127));
        btnXoaLoaiSach.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnXoaLoaiSach.setRadius(10);
        btnXoaLoaiSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaLoaiSachActionPerformed(evt);
            }
        });
        panelRadius9.add(btnXoaLoaiSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 140, 150, 40));

        tabQuanLy.addTab("Loại Sách", panelRadius9);

        panelRadius10.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnFirstSach.setBackground(new java.awt.Color(145, 227, 168));
        btnFirstSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-left.png"))); // NOI18N
        btnFirstSach.setBoderColor(new java.awt.Color(145, 227, 168));
        btnFirstSach.setRadius(10);
        btnFirstSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstSachActionPerformed(evt);
            }
        });
        panelRadius10.add(btnFirstSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 60, 40));

        btnNextSach.setBackground(new java.awt.Color(145, 227, 168));
        btnNextSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-left.png"))); // NOI18N
        btnNextSach.setBoderColor(new java.awt.Color(145, 227, 168));
        btnNextSach.setRadius(10);
        btnNextSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextSachActionPerformed(evt);
            }
        });
        panelRadius10.add(btnNextSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 60, 40));

        btnPrevSach.setBackground(new java.awt.Color(145, 227, 168));
        btnPrevSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-right.png"))); // NOI18N
        btnPrevSach.setBoderColor(new java.awt.Color(145, 227, 168));
        btnPrevSach.setRadius(10);
        btnPrevSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevSachActionPerformed(evt);
            }
        });
        panelRadius10.add(btnPrevSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 60, 40));

        btnLastSach.setBackground(new java.awt.Color(145, 227, 168));
        btnLastSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-right.png"))); // NOI18N
        btnLastSach.setBoderColor(new java.awt.Color(145, 227, 168));
        btnLastSach.setRadius(10);
        btnLastSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastSachActionPerformed(evt);
            }
        });
        panelRadius10.add(btnLastSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 380, 60, 40));

        tblSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Sách", "Tên Sách", "Tên Tác Giả", "Ngày Đăng"
            }
        ));
        tblSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblSachMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tblSach);

        panelRadius10.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 860, 300));

        panelRadius17.setBackground(new java.awt.Color(231, 249, 234));
        panelRadius17.setRadius(15);

        javax.swing.GroupLayout panelRadius17Layout = new javax.swing.GroupLayout(panelRadius17);
        panelRadius17.setLayout(panelRadius17Layout);
        panelRadius17Layout.setHorizontalGroup(
            panelRadius17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        panelRadius17Layout.setVerticalGroup(
            panelRadius17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        panelRadius10.add(panelRadius17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 390, 40));

        jLabel18.setFont(new java.awt.Font("Inter SemiBold", 0, 14)); // NOI18N
        jLabel18.setText("Lọc Theo Thể Loại");
        panelRadius10.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, -1, -1));

        cboTheLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cboTheLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTheLoaiActionPerformed(evt);
            }
        });
        panelRadius10.add(cboTheLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 160, 40));

        btnSapXepSach.setBackground(new java.awt.Color(87, 190, 110));
        btnSapXepSach.setForeground(new java.awt.Color(255, 255, 255));
        btnSapXepSach.setText("Sắp Xếp Theo Tên");
        btnSapXepSach.setBoderColor(new java.awt.Color(87, 190, 110));
        btnSapXepSach.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnSapXepSach.setRadius(10);
        btnSapXepSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSapXepSachActionPerformed(evt);
            }
        });
        panelRadius10.add(btnSapXepSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 140, 40));

        btnThemSach.setBackground(new java.awt.Color(87, 190, 110));
        btnThemSach.setForeground(new java.awt.Color(255, 255, 255));
        btnThemSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/add-document.png"))); // NOI18N
        btnThemSach.setText("Thêm Sách");
        btnThemSach.setBoderColor(new java.awt.Color(87, 190, 110));
        btnThemSach.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnThemSach.setRadius(10);
        btnThemSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSachActionPerformed(evt);
            }
        });
        panelRadius10.add(btnThemSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 60, 150, 40));

        btnCapNhatSach.setBackground(new java.awt.Color(87, 190, 110));
        btnCapNhatSach.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhatSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/refresh (1).png"))); // NOI18N
        btnCapNhatSach.setText("Sửa Thông Tin");
        btnCapNhatSach.setBoderColor(new java.awt.Color(87, 190, 110));
        btnCapNhatSach.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnCapNhatSach.setRadius(10);
        btnCapNhatSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatSachActionPerformed(evt);
            }
        });
        panelRadius10.add(btnCapNhatSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 120, 150, 40));

        btnXoaSach.setBackground(new java.awt.Color(253, 127, 127));
        btnXoaSach.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/delete-document.png"))); // NOI18N
        btnXoaSach.setText("Xóa Sách");
        btnXoaSach.setBoderColor(new java.awt.Color(253, 127, 127));
        btnXoaSach.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnXoaSach.setRadius(10);
        btnXoaSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSachActionPerformed(evt);
            }
        });
        panelRadius10.add(btnXoaSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 180, 150, 40));

        tabQuanLy.addTab("Sách", panelRadius10);

        panelRadius11.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRadius31.setBackground(new java.awt.Color(231, 249, 234));
        panelRadius31.setRadius(15);

        javax.swing.GroupLayout panelRadius31Layout = new javax.swing.GroupLayout(panelRadius31);
        panelRadius31.setLayout(panelRadius31Layout);
        panelRadius31Layout.setHorizontalGroup(
            panelRadius31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        panelRadius31Layout.setVerticalGroup(
            panelRadius31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        panelRadius11.add(panelRadius31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 390, 40));

        tblAudioQL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Audio", "Tên Audio", "Ngày Đăng", "Người Thu"
            }
        ));
        tblAudioQL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblAudioQLMousePressed(evt);
            }
        });
        jScrollPane14.setViewportView(tblAudioQL);

        panelRadius11.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 860, 300));

        btnSapXepTheoTen.setBackground(new java.awt.Color(87, 190, 110));
        btnSapXepTheoTen.setForeground(new java.awt.Color(255, 255, 255));
        btnSapXepTheoTen.setText("Sắp Xếp Theo Tên");
        btnSapXepTheoTen.setBoderColor(new java.awt.Color(87, 190, 110));
        btnSapXepTheoTen.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnSapXepTheoTen.setRadius(10);
        btnSapXepTheoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSapXepTheoTenActionPerformed(evt);
            }
        });
        panelRadius11.add(btnSapXepTheoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 140, 40));

        btnThemAudio.setBackground(new java.awt.Color(87, 190, 110));
        btnThemAudio.setForeground(new java.awt.Color(255, 255, 255));
        btnThemAudio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/music-file.png"))); // NOI18N
        btnThemAudio.setText("Thêm Audio");
        btnThemAudio.setBoderColor(new java.awt.Color(87, 190, 110));
        btnThemAudio.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnThemAudio.setRadius(10);
        btnThemAudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemAudioActionPerformed(evt);
            }
        });
        panelRadius11.add(btnThemAudio, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 60, 150, 40));

        btnSuaAudio.setBackground(new java.awt.Color(87, 190, 110));
        btnSuaAudio.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaAudio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/refresh (1).png"))); // NOI18N
        btnSuaAudio.setText("Sửa Thông Tin");
        btnSuaAudio.setBoderColor(new java.awt.Color(87, 190, 110));
        btnSuaAudio.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnSuaAudio.setRadius(10);
        btnSuaAudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaAudioActionPerformed(evt);
            }
        });
        panelRadius11.add(btnSuaAudio, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 120, 150, 40));

        btnXoaAudio.setBackground(new java.awt.Color(253, 127, 127));
        btnXoaAudio.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaAudio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/delete-document.png"))); // NOI18N
        btnXoaAudio.setText("Xóa Audio");
        btnXoaAudio.setBoderColor(new java.awt.Color(253, 127, 127));
        btnXoaAudio.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnXoaAudio.setRadius(10);
        btnXoaAudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaAudioActionPerformed(evt);
            }
        });
        panelRadius11.add(btnXoaAudio, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 180, 150, 40));

        btnFirstAudio.setBackground(new java.awt.Color(145, 227, 168));
        btnFirstAudio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-left.png"))); // NOI18N
        btnFirstAudio.setBoderColor(new java.awt.Color(145, 227, 168));
        btnFirstAudio.setRadius(10);
        btnFirstAudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstAudioActionPerformed(evt);
            }
        });
        panelRadius11.add(btnFirstAudio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 60, 40));

        btnPrevAudio.setBackground(new java.awt.Color(145, 227, 168));
        btnPrevAudio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-left.png"))); // NOI18N
        btnPrevAudio.setBoderColor(new java.awt.Color(145, 227, 168));
        btnPrevAudio.setRadius(10);
        btnPrevAudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevAudioActionPerformed(evt);
            }
        });
        panelRadius11.add(btnPrevAudio, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 60, 40));

        btnNextAudio.setBackground(new java.awt.Color(145, 227, 168));
        btnNextAudio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-right.png"))); // NOI18N
        btnNextAudio.setBoderColor(new java.awt.Color(145, 227, 168));
        btnNextAudio.setRadius(10);
        btnNextAudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextAudioActionPerformed(evt);
            }
        });
        panelRadius11.add(btnNextAudio, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 60, 40));

        btnLastAudio.setBackground(new java.awt.Color(145, 227, 168));
        btnLastAudio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-right.png"))); // NOI18N
        btnLastAudio.setBoderColor(new java.awt.Color(145, 227, 168));
        btnLastAudio.setRadius(10);
        btnLastAudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastAudioActionPerformed(evt);
            }
        });
        panelRadius11.add(btnLastAudio, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 380, 60, 40));

        tabQuanLy.addTab("Audio", panelRadius11);

        panelRadius12.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblTacGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                " Mã Tác Giả", "Họ Và Tên", "Năm Sinh", "Giới Tính"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTacGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblTacGiaMousePressed(evt);
            }
        });
        jScrollPane6.setViewportView(tblTacGia);

        panelRadius12.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 840, 350));

        btnFirstTacGia.setBackground(new java.awt.Color(145, 227, 168));
        btnFirstTacGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-left.png"))); // NOI18N
        btnFirstTacGia.setBoderColor(new java.awt.Color(145, 227, 168));
        btnFirstTacGia.setRadius(10);
        btnFirstTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstTacGiaActionPerformed(evt);
            }
        });
        panelRadius12.add(btnFirstTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 60, 40));

        btnPrevTacGia.setBackground(new java.awt.Color(145, 227, 168));
        btnPrevTacGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-left.png"))); // NOI18N
        btnPrevTacGia.setBoderColor(new java.awt.Color(145, 227, 168));
        btnPrevTacGia.setRadius(10);
        btnPrevTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevTacGiaActionPerformed(evt);
            }
        });
        panelRadius12.add(btnPrevTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 60, 40));

        btnNextTacGia.setBackground(new java.awt.Color(145, 227, 168));
        btnNextTacGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-right.png"))); // NOI18N
        btnNextTacGia.setBoderColor(new java.awt.Color(145, 227, 168));
        btnNextTacGia.setRadius(10);
        btnNextTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextTacGiaActionPerformed(evt);
            }
        });
        panelRadius12.add(btnNextTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 60, 40));

        btnLastTacGia.setBackground(new java.awt.Color(145, 227, 168));
        btnLastTacGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-right.png"))); // NOI18N
        btnLastTacGia.setBoderColor(new java.awt.Color(145, 227, 168));
        btnLastTacGia.setRadius(10);
        btnLastTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastTacGiaActionPerformed(evt);
            }
        });
        panelRadius12.add(btnLastTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 380, 60, 40));

        btnThemTacGia.setBackground(new java.awt.Color(87, 190, 110));
        btnThemTacGia.setForeground(new java.awt.Color(255, 255, 255));
        btnThemTacGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/user-add.png"))); // NOI18N
        btnThemTacGia.setText("Thêm Tác Giả");
        btnThemTacGia.setBoderColor(new java.awt.Color(87, 190, 110));
        btnThemTacGia.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnThemTacGia.setRadius(10);
        btnThemTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemTacGiaActionPerformed(evt);
            }
        });
        panelRadius12.add(btnThemTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 20, 150, 40));

        btnSuaThongTin.setBackground(new java.awt.Color(87, 190, 110));
        btnSuaThongTin.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaThongTin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/refresh (1).png"))); // NOI18N
        btnSuaThongTin.setText("Sửa Thông Tin");
        btnSuaThongTin.setBoderColor(new java.awt.Color(87, 190, 110));
        btnSuaThongTin.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnSuaThongTin.setRadius(10);
        btnSuaThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaThongTinActionPerformed(evt);
            }
        });
        panelRadius12.add(btnSuaThongTin, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 80, 150, 40));

        btnXoaTacGia.setBackground(new java.awt.Color(253, 127, 127));
        btnXoaTacGia.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaTacGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/delete-user.png"))); // NOI18N
        btnXoaTacGia.setText("Xóa Tác Giả");
        btnXoaTacGia.setBoderColor(new java.awt.Color(253, 127, 127));
        btnXoaTacGia.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnXoaTacGia.setRadius(10);
        btnXoaTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTacGiaActionPerformed(evt);
            }
        });
        panelRadius12.add(btnXoaTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 140, 150, 40));

        tabQuanLy.addTab("Tác Giả", panelRadius12);

        panelRadius13.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblTaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên Đăng Nhập", "Mật Khẩu", "Mã Người Dùng", "Vai Trò"
            }
        ));
        tblTaiKhoan.setSelectionBackground(new java.awt.Color(87, 190, 110));
        tblTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblTaiKhoanMousePressed(evt);
            }
        });
        jScrollPane7.setViewportView(tblTaiKhoan);

        panelRadius13.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 840, 350));

        btnLastTaiKhoan.setBackground(new java.awt.Color(145, 227, 168));
        btnLastTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-right.png"))); // NOI18N
        btnLastTaiKhoan.setBoderColor(new java.awt.Color(145, 227, 168));
        btnLastTaiKhoan.setRadius(10);
        btnLastTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastTaiKhoanActionPerformed(evt);
            }
        });
        panelRadius13.add(btnLastTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 380, 60, 40));

        btnNextTaiKhoan.setBackground(new java.awt.Color(145, 227, 168));
        btnNextTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-right.png"))); // NOI18N
        btnNextTaiKhoan.setBoderColor(new java.awt.Color(145, 227, 168));
        btnNextTaiKhoan.setRadius(10);
        btnNextTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextTaiKhoanActionPerformed(evt);
            }
        });
        panelRadius13.add(btnNextTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 60, 40));

        btnPrevTaiKhoan.setBackground(new java.awt.Color(145, 227, 168));
        btnPrevTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-left.png"))); // NOI18N
        btnPrevTaiKhoan.setBoderColor(new java.awt.Color(145, 227, 168));
        btnPrevTaiKhoan.setRadius(10);
        btnPrevTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevTaiKhoanActionPerformed(evt);
            }
        });
        panelRadius13.add(btnPrevTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 60, 40));

        btnFirstTaiKhoan.setBackground(new java.awt.Color(145, 227, 168));
        btnFirstTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-left.png"))); // NOI18N
        btnFirstTaiKhoan.setBoderColor(new java.awt.Color(145, 227, 168));
        btnFirstTaiKhoan.setRadius(10);
        btnFirstTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstTaiKhoanActionPerformed(evt);
            }
        });
        panelRadius13.add(btnFirstTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 60, 40));

        btnSuaTaiKhoan.setBackground(new java.awt.Color(87, 190, 110));
        btnSuaTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/refresh (1).png"))); // NOI18N
        btnSuaTaiKhoan.setText("Sửa Thông Tin");
        btnSuaTaiKhoan.setBoderColor(new java.awt.Color(87, 190, 110));
        btnSuaTaiKhoan.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnSuaTaiKhoan.setRadius(10);
        btnSuaTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaTaiKhoanActionPerformed(evt);
            }
        });
        panelRadius13.add(btnSuaTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 80, 150, 40));

        btnXoaTaiKhoan.setBackground(new java.awt.Color(253, 127, 127));
        btnXoaTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/delete-user.png"))); // NOI18N
        btnXoaTaiKhoan.setText("Xóa Tài Khoản");
        btnXoaTaiKhoan.setBoderColor(new java.awt.Color(253, 127, 127));
        btnXoaTaiKhoan.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnXoaTaiKhoan.setRadius(10);
        btnXoaTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTaiKhoanActionPerformed(evt);
            }
        });
        panelRadius13.add(btnXoaTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 140, 150, 40));

        btnThemTaiKhoan.setBackground(new java.awt.Color(87, 190, 110));
        btnThemTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        btnThemTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/user-add.png"))); // NOI18N
        btnThemTaiKhoan.setText("Thêm Tài Khoản");
        btnThemTaiKhoan.setBoderColor(new java.awt.Color(87, 190, 110));
        btnThemTaiKhoan.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnThemTaiKhoan.setRadius(10);
        btnThemTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemTaiKhoanActionPerformed(evt);
            }
        });
        panelRadius13.add(btnThemTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 20, 150, 40));

        tabQuanLy.addTab("Tài Khoản", panelRadius13);

        panelRadius14.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblNguoiDung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Người Dùng", "Tên Người Dùng", "Số Điện Thoại", "Giới Tính"
            }
        ));
        tblNguoiDung.setSelectionBackground(new java.awt.Color(87, 190, 110));
        tblNguoiDung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblNguoiDungMousePressed(evt);
            }
        });
        jScrollPane8.setViewportView(tblNguoiDung);

        panelRadius14.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 840, 350));

        btnLastNguoiDung.setBackground(new java.awt.Color(145, 227, 168));
        btnLastNguoiDung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-right.png"))); // NOI18N
        btnLastNguoiDung.setBoderColor(new java.awt.Color(145, 227, 168));
        btnLastNguoiDung.setRadius(10);
        btnLastNguoiDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastNguoiDungActionPerformed(evt);
            }
        });
        panelRadius14.add(btnLastNguoiDung, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 380, 60, 40));

        btnNextNguoiDung.setBackground(new java.awt.Color(145, 227, 168));
        btnNextNguoiDung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-right.png"))); // NOI18N
        btnNextNguoiDung.setBoderColor(new java.awt.Color(145, 227, 168));
        btnNextNguoiDung.setRadius(10);
        btnNextNguoiDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextNguoiDungActionPerformed(evt);
            }
        });
        panelRadius14.add(btnNextNguoiDung, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 60, 40));

        btnPrevNguoiDung.setBackground(new java.awt.Color(145, 227, 168));
        btnPrevNguoiDung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-left.png"))); // NOI18N
        btnPrevNguoiDung.setBoderColor(new java.awt.Color(145, 227, 168));
        btnPrevNguoiDung.setRadius(10);
        btnPrevNguoiDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevNguoiDungActionPerformed(evt);
            }
        });
        panelRadius14.add(btnPrevNguoiDung, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 60, 40));

        btnFirstNguoiDung.setBackground(new java.awt.Color(145, 227, 168));
        btnFirstNguoiDung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-left.png"))); // NOI18N
        btnFirstNguoiDung.setBoderColor(new java.awt.Color(145, 227, 168));
        btnFirstNguoiDung.setRadius(10);
        btnFirstNguoiDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstNguoiDungActionPerformed(evt);
            }
        });
        panelRadius14.add(btnFirstNguoiDung, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 60, 40));

        btnXoaNguoiDung.setBackground(new java.awt.Color(253, 127, 127));
        btnXoaNguoiDung.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaNguoiDung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/delete-user.png"))); // NOI18N
        btnXoaNguoiDung.setText("Người Dùng");
        btnXoaNguoiDung.setBoderColor(new java.awt.Color(253, 127, 127));
        btnXoaNguoiDung.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnXoaNguoiDung.setRadius(10);
        btnXoaNguoiDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNguoiDungActionPerformed(evt);
            }
        });
        panelRadius14.add(btnXoaNguoiDung, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 140, 150, 40));

        btnSuaNguoiDung.setBackground(new java.awt.Color(87, 190, 110));
        btnSuaNguoiDung.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaNguoiDung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/refresh (1).png"))); // NOI18N
        btnSuaNguoiDung.setText("Sửa Thông Tin");
        btnSuaNguoiDung.setBoderColor(new java.awt.Color(87, 190, 110));
        btnSuaNguoiDung.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnSuaNguoiDung.setRadius(10);
        btnSuaNguoiDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNguoiDungActionPerformed(evt);
            }
        });
        panelRadius14.add(btnSuaNguoiDung, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 80, 150, 40));

        btnThemNguoiDung.setBackground(new java.awt.Color(87, 190, 110));
        btnThemNguoiDung.setForeground(new java.awt.Color(255, 255, 255));
        btnThemNguoiDung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/user-add.png"))); // NOI18N
        btnThemNguoiDung.setText("Người Dùng");
        btnThemNguoiDung.setBoderColor(new java.awt.Color(87, 190, 110));
        btnThemNguoiDung.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnThemNguoiDung.setRadius(10);
        btnThemNguoiDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNguoiDungActionPerformed(evt);
            }
        });
        panelRadius14.add(btnThemNguoiDung, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 20, 150, 40));

        tabQuanLy.addTab("Người Dùng", panelRadius14);

        pnlThucUong.setBackground(new java.awt.Color(255, 255, 255));
        pnlThucUong.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblThucUong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã Thức Uống ", "Tên Thức Uống", "Giá Tiền"
            }
        ));
        tblThucUong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblThucUongMousePressed(evt);
            }
        });
        jScrollPane9.setViewportView(tblThucUong);

        pnlThucUong.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 840, 350));

        btnFirstThucUong.setBackground(new java.awt.Color(145, 227, 168));
        btnFirstThucUong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-left.png"))); // NOI18N
        btnFirstThucUong.setBoderColor(new java.awt.Color(145, 227, 168));
        btnFirstThucUong.setRadius(10);
        btnFirstThucUong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstThucUongActionPerformed(evt);
            }
        });
        pnlThucUong.add(btnFirstThucUong, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 60, 40));

        btnPreThucUong.setBackground(new java.awt.Color(145, 227, 168));
        btnPreThucUong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-left.png"))); // NOI18N
        btnPreThucUong.setBoderColor(new java.awt.Color(145, 227, 168));
        btnPreThucUong.setRadius(10);
        btnPreThucUong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreThucUongActionPerformed(evt);
            }
        });
        pnlThucUong.add(btnPreThucUong, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 60, 40));

        btnNextThucUong.setBackground(new java.awt.Color(145, 227, 168));
        btnNextThucUong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-right.png"))); // NOI18N
        btnNextThucUong.setBoderColor(new java.awt.Color(145, 227, 168));
        btnNextThucUong.setRadius(10);
        btnNextThucUong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextThucUongActionPerformed(evt);
            }
        });
        pnlThucUong.add(btnNextThucUong, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 60, 40));

        btnLastThucUong.setBackground(new java.awt.Color(145, 227, 168));
        btnLastThucUong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-right.png"))); // NOI18N
        btnLastThucUong.setBoderColor(new java.awt.Color(145, 227, 168));
        btnLastThucUong.setRadius(10);
        btnLastThucUong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastThucUongActionPerformed(evt);
            }
        });
        pnlThucUong.add(btnLastThucUong, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 380, 60, 40));

        btnThucUong.setBackground(new java.awt.Color(87, 190, 110));
        btnThucUong.setForeground(new java.awt.Color(255, 255, 255));
        btnThucUong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/drink-alt.png"))); // NOI18N
        btnThucUong.setText("Thức Uống");
        btnThucUong.setBoderColor(new java.awt.Color(87, 190, 110));
        btnThucUong.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnThucUong.setRadius(10);
        btnThucUong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThucUongActionPerformed(evt);
            }
        });
        pnlThucUong.add(btnThucUong, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 20, 150, 40));

        btnSuaThongTinThucUong.setBackground(new java.awt.Color(87, 190, 110));
        btnSuaThongTinThucUong.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaThongTinThucUong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/refresh (1).png"))); // NOI18N
        btnSuaThongTinThucUong.setText("Sửa Thông Tin");
        btnSuaThongTinThucUong.setBoderColor(new java.awt.Color(87, 190, 110));
        btnSuaThongTinThucUong.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnSuaThongTinThucUong.setRadius(10);
        btnSuaThongTinThucUong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaThongTinThucUongActionPerformed(evt);
            }
        });
        pnlThucUong.add(btnSuaThongTinThucUong, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 80, 150, 40));

        btnXoaThucUong.setBackground(new java.awt.Color(253, 127, 127));
        btnXoaThucUong.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaThucUong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/circle-cross.png"))); // NOI18N
        btnXoaThucUong.setText("Thức Uống");
        btnXoaThucUong.setBoderColor(new java.awt.Color(253, 127, 127));
        btnXoaThucUong.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnXoaThucUong.setRadius(10);
        btnXoaThucUong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaThucUongActionPerformed(evt);
            }
        });
        pnlThucUong.add(btnXoaThucUong, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 140, 150, 40));

        tabQuanLy.addTab("Thực Đơn", pnlThucUong);

        pnlHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        pnlHoaDon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnThemHoaDon.setBackground(new java.awt.Color(87, 190, 110));
        btnThemHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnThemHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/receipt (1).png"))); // NOI18N
        btnThemHoaDon.setText("Thêm Hóa Đơn");
        btnThemHoaDon.setBoderColor(new java.awt.Color(87, 190, 110));
        btnThemHoaDon.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnThemHoaDon.setRadius(10);
        btnThemHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHoaDonActionPerformed(evt);
            }
        });
        pnlHoaDon.add(btnThemHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 50, 150, 40));

        btnSuaThongTinHoaDon.setBackground(new java.awt.Color(87, 190, 110));
        btnSuaThongTinHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaThongTinHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/refresh (1).png"))); // NOI18N
        btnSuaThongTinHoaDon.setText("Sửa Thông Tin");
        btnSuaThongTinHoaDon.setBoderColor(new java.awt.Color(87, 190, 110));
        btnSuaThongTinHoaDon.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnSuaThongTinHoaDon.setRadius(10);
        btnSuaThongTinHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaThongTinHoaDonActionPerformed(evt);
            }
        });
        pnlHoaDon.add(btnSuaThongTinHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 110, 150, 40));

        btnXoaThongTinHoaDon.setBackground(new java.awt.Color(253, 127, 127));
        btnXoaThongTinHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaThongTinHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/circle-cross.png"))); // NOI18N
        btnXoaThongTinHoaDon.setText("Xóa Hóa Đơn");
        btnXoaThongTinHoaDon.setBoderColor(new java.awt.Color(253, 127, 127));
        btnXoaThongTinHoaDon.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnXoaThongTinHoaDon.setRadius(10);
        btnXoaThongTinHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaThongTinHoaDonActionPerformed(evt);
            }
        });
        pnlHoaDon.add(btnXoaThongTinHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 170, 150, 40));

        tabDoanhThu.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabDoanhThuStateChanged(evt);
            }
        });

        panelRadius5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Người Dùng", "Họ Tên", "Tên Đăng Nhập", "HD Thức Uống", "Tổng Thức Uống", "HD Thuê Sách", "Tổng Thuê Sách", "Tổng Giá"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblHoaDonMousePressed(evt);
            }
        });
        jScrollPane10.setViewportView(tblHoaDon);

        panelRadius5.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 810, 340));

        tabDoanhThu.addTab("Doanh Thu Tổng Hợp", panelRadius5);

        panelRadius15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblDoanhThuThucUong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Người Dùng", "Họ Tên", "Mã HD Thức Uống", "DT Thức Uống", "Ngày Mua"
            }
        ));
        tblDoanhThuThucUong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblDoanhThuThucUongMousePressed(evt);
            }
        });
        jScrollPane16.setViewportView(tblDoanhThuThucUong);

        panelRadius15.add(jScrollPane16, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 10, 810, 340));

        tabDoanhThu.addTab("Doanh Thu Thức Uống", panelRadius15);

        panelRadius16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblDoanhThuThueSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Người Dùng", "Họ Tên", "Tên Đăng Nhập", "Mã HD Thuê Sách", "DT Thuê Sách", "Ngày Thuê"
            }
        ));
        tblDoanhThuThueSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblDoanhThuThueSachMousePressed(evt);
            }
        });
        jScrollPane12.setViewportView(tblDoanhThuThueSach);

        panelRadius16.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 10, 810, 340));

        tabDoanhThu.addTab("Doanh Thu Thuê Sách", panelRadius16);

        pnlHoaDon.add(tabDoanhThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 840, 420));

        tabQuanLy.addTab("Hóa Đơn", pnlHoaDon);

        panelRadius1.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblBangGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Bảng Giá ", "Thời Lượng", "Đơn Giá Thuê", "Mô Tả"
            }
        ));
        tblBangGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblBangGiaMousePressed(evt);
            }
        });
        jScrollPane15.setViewportView(tblBangGia);

        panelRadius1.add(jScrollPane15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 840, 350));

        btnPreBangGia.setBackground(new java.awt.Color(145, 227, 168));
        btnPreBangGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-left.png"))); // NOI18N
        btnPreBangGia.setBoderColor(new java.awt.Color(145, 227, 168));
        btnPreBangGia.setRadius(10);
        btnPreBangGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreBangGiaActionPerformed(evt);
            }
        });
        panelRadius1.add(btnPreBangGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 60, 40));

        btnFirstBangGia.setBackground(new java.awt.Color(145, 227, 168));
        btnFirstBangGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-left.png"))); // NOI18N
        btnFirstBangGia.setBoderColor(new java.awt.Color(145, 227, 168));
        btnFirstBangGia.setRadius(10);
        btnFirstBangGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstBangGiaActionPerformed(evt);
            }
        });
        panelRadius1.add(btnFirstBangGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 60, 40));

        btnNextBangGia.setBackground(new java.awt.Color(145, 227, 168));
        btnNextBangGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-right.png"))); // NOI18N
        btnNextBangGia.setBoderColor(new java.awt.Color(145, 227, 168));
        btnNextBangGia.setRadius(10);
        btnNextBangGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextBangGiaActionPerformed(evt);
            }
        });
        panelRadius1.add(btnNextBangGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 60, 40));

        btnLastBangGia.setBackground(new java.awt.Color(145, 227, 168));
        btnLastBangGia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-right.png"))); // NOI18N
        btnLastBangGia.setBoderColor(new java.awt.Color(145, 227, 168));
        btnLastBangGia.setRadius(10);
        btnLastBangGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastBangGiaActionPerformed(evt);
            }
        });
        panelRadius1.add(btnLastBangGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 380, 60, 40));

        btnThemBangGia.setBackground(new java.awt.Color(87, 190, 110));
        btnThemBangGia.setForeground(new java.awt.Color(255, 255, 255));
        btnThemBangGia.setText("Thêm Bảng Giá");
        btnThemBangGia.setBoderColor(new java.awt.Color(87, 190, 110));
        btnThemBangGia.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnThemBangGia.setRadius(10);
        btnThemBangGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemBangGiaActionPerformed(evt);
            }
        });
        panelRadius1.add(btnThemBangGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 20, 150, 40));

        btnSuaBangGia.setBackground(new java.awt.Color(87, 190, 110));
        btnSuaBangGia.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaBangGia.setText("Sửa Thông Tin");
        btnSuaBangGia.setBoderColor(new java.awt.Color(87, 190, 110));
        btnSuaBangGia.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnSuaBangGia.setRadius(10);
        btnSuaBangGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaBangGiaActionPerformed(evt);
            }
        });
        panelRadius1.add(btnSuaBangGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 80, 150, 40));

        btnXoaBangGia.setBackground(new java.awt.Color(253, 127, 127));
        btnXoaBangGia.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaBangGia.setText("Xóa Bảng Giá");
        btnXoaBangGia.setBoderColor(new java.awt.Color(253, 127, 127));
        btnXoaBangGia.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnXoaBangGia.setRadius(10);
        btnXoaBangGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaBangGiaActionPerformed(evt);
            }
        });
        panelRadius1.add(btnXoaBangGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 140, 150, 40));

        tabQuanLy.addTab("Bảng Giá Thuê", panelRadius1);

        pnlFrameManage.add(tabQuanLy, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1060, 480));

        pnlBossMain.add(pnlFrameManage, "card6");

        pnlFrameStatistical.setBackground(new java.awt.Color(205, 239, 215));
        pnlFrameStatistical.setRadius(15);
        pnlFrameStatistical.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Open Sans", 1, 28)); // NOI18N
        jLabel20.setText("Thống Kê");
        pnlFrameStatistical.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        tabThongKe.setClorSroll(new java.awt.Color(87, 190, 110));
        tabThongKe.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        panelRadius18.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblTKDTThucUong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Hóa Đơn", "Mã Người Dùng", "Họ Và Tên", "Tổng Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblTKDTThucUong);

        panelRadius18.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 930, 360));

        panelRadius27.setBackground(new java.awt.Color(231, 249, 234));
        panelRadius27.setRadius(10);
        panelRadius27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setText("Theo Năm");
        panelRadius27.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 17, -1, -1));

        cboNamTU.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1977", "1957", "2022" }));
        cboNamTU.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboNamTUItemStateChanged(evt);
            }
        });
        panelRadius27.add(cboNamTU, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 160, 30));

        panelRadius18.add(panelRadius27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 250, 50));

        bntFistDTTU.setBackground(new java.awt.Color(145, 227, 168));
        bntFistDTTU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-up.png"))); // NOI18N
        bntFistDTTU.setBoderColor(new java.awt.Color(145, 227, 168));
        bntFistDTTU.setRadius(10);
        bntFistDTTU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntFistDTTUActionPerformed(evt);
            }
        });
        panelRadius18.add(bntFistDTTU, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 70, 60, 40));

        btnPreDTTU.setBackground(new java.awt.Color(145, 227, 168));
        btnPreDTTU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-up.png"))); // NOI18N
        btnPreDTTU.setBoderColor(new java.awt.Color(145, 227, 168));
        btnPreDTTU.setRadius(10);
        btnPreDTTU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreDTTUActionPerformed(evt);
            }
        });
        panelRadius18.add(btnPreDTTU, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 130, 60, 40));

        btnNextDTTU.setBackground(new java.awt.Color(145, 227, 168));
        btnNextDTTU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-down.png"))); // NOI18N
        btnNextDTTU.setBoderColor(new java.awt.Color(145, 227, 168));
        btnNextDTTU.setRadius(10);
        btnNextDTTU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextDTTUActionPerformed(evt);
            }
        });
        panelRadius18.add(btnNextDTTU, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 190, 60, 40));

        btnLastDTTU.setBackground(new java.awt.Color(145, 227, 168));
        btnLastDTTU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-down.png"))); // NOI18N
        btnLastDTTU.setBoderColor(new java.awt.Color(145, 227, 168));
        btnLastDTTU.setRadius(10);
        btnLastDTTU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastDTTUActionPerformed(evt);
            }
        });
        panelRadius18.add(btnLastDTTU, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 250, 60, 40));

        tabThongKe.addTab("Doanh Thu Thức Uống", panelRadius18);

        panelRadius20.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblTKDTThueSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Thuê Sách", "Mã Người Dùng", "Họ Và Tên", "Tổng Tiền"
            }
        ));
        jScrollPane11.setViewportView(tblTKDTThueSach);

        panelRadius20.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 930, 360));

        panelRadius25.setBackground(new java.awt.Color(231, 249, 234));
        panelRadius25.setRadius(10);
        panelRadius25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setText("Theo Năm");
        panelRadius25.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 17, -1, -1));

        cboNamTS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "2022" }));
        cboNamTS.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboNamTSItemStateChanged(evt);
            }
        });
        panelRadius25.add(cboNamTS, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 160, 30));

        panelRadius20.add(panelRadius25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 250, 50));

        btnFirstDTTS.setBackground(new java.awt.Color(145, 227, 168));
        btnFirstDTTS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-up.png"))); // NOI18N
        btnFirstDTTS.setBoderColor(new java.awt.Color(145, 227, 168));
        btnFirstDTTS.setRadius(10);
        btnFirstDTTS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstDTTSActionPerformed(evt);
            }
        });
        panelRadius20.add(btnFirstDTTS, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 70, 60, 40));

        btnPreDTTS.setBackground(new java.awt.Color(145, 227, 168));
        btnPreDTTS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-up.png"))); // NOI18N
        btnPreDTTS.setBoderColor(new java.awt.Color(145, 227, 168));
        btnPreDTTS.setRadius(10);
        btnPreDTTS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreDTTSActionPerformed(evt);
            }
        });
        panelRadius20.add(btnPreDTTS, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 130, 60, 40));

        btnNextDTTS.setBackground(new java.awt.Color(145, 227, 168));
        btnNextDTTS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-down.png"))); // NOI18N
        btnNextDTTS.setBoderColor(new java.awt.Color(145, 227, 168));
        btnNextDTTS.setRadius(10);
        btnNextDTTS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextDTTSActionPerformed(evt);
            }
        });
        panelRadius20.add(btnNextDTTS, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 190, 60, 40));

        btnLastDTTS.setBackground(new java.awt.Color(145, 227, 168));
        btnLastDTTS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-down.png"))); // NOI18N
        btnLastDTTS.setBoderColor(new java.awt.Color(145, 227, 168));
        btnLastDTTS.setRadius(10);
        btnLastDTTS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastDTTSActionPerformed(evt);
            }
        });
        panelRadius20.add(btnLastDTTS, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 250, 60, 40));

        tabThongKe.addTab("Doanh Thu Thuê Sách", panelRadius20);

        pnlFrameStatistical.add(tabThongKe, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1060, 480));

        pnlBossMain.add(pnlFrameStatistical, "card5");

        pnlFrameRead.setBackground(new java.awt.Color(205, 239, 215));
        pnlFrameRead.setRadius(15);
        pnlFrameRead.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        materialTabbed3.setTabPlacement(javax.swing.JTabbedPane.RIGHT);
        materialTabbed3.setClorSroll(new java.awt.Color(87, 190, 110));
        materialTabbed3.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N

        pnlReader.setBackground(new java.awt.Color(255, 255, 255));
        pnlReader.setRadius(15);

        javax.swing.GroupLayout pnlReaderLayout = new javax.swing.GroupLayout(pnlReader);
        pnlReader.setLayout(pnlReaderLayout);
        pnlReaderLayout.setHorizontalGroup(
            pnlReaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlReaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollpanePDF, javax.swing.GroupLayout.DEFAULT_SIZE, 952, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlReaderLayout.setVerticalGroup(
            pnlReaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlReaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollpanePDF, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                .addContainerGap())
        );

        materialTabbed3.addTab("Đọc Sách", pnlReader);

        pnlListSachDoc.setBackground(new java.awt.Color(255, 255, 255));
        pnlListSachDoc.setRadius(15);
        pnlListSachDoc.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblListSachDoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Sách", "Tên Sách", "Họ Tên Tác Giả ", "Ngày Đăng", "Mô Tả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblListSachDoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblListSachDocMousePressed(evt);
            }
        });
        jScrollPane5.setViewportView(tblListSachDoc);

        pnlListSachDoc.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 940, 440));

        jLabel19.setFont(new java.awt.Font("Inter SemiBold", 0, 24)); // NOI18N
        jLabel19.setText("Danh Sách Sách Đọc");
        pnlListSachDoc.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        materialTabbed3.addTab("Danh Sách", pnlListSachDoc);

        pnlFrameRead.add(materialTabbed3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 1050, 528));

        pnlBossMain.add(pnlFrameRead, "card4");

        pnlFrameListen.setBackground(new java.awt.Color(251, 251, 251));
        pnlFrameListen.setRadius(15);
        pnlFrameListen.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRadius30.setBackground(new java.awt.Color(205, 239, 215));
        panelRadius30.setRadius(15);
        panelRadius30.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblAudio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Audio", "Tên Audio", "Ngày Phát Hành", "Người Thu", "Mô Tả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAudio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAudioMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblAudioMousePressed(evt);
            }
        });
        jScrollPane13.setViewportView(tblAudio);

        panelRadius30.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 680, 420));

        jLabel27.setFont(new java.awt.Font("Inter SemiBold", 0, 24)); // NOI18N
        jLabel27.setText("Danh Sách Audio Books");
        panelRadius30.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        pnlFrameListen.add(panelRadius30, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 700, 500));

        lblTenNguoiThu.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblTenNguoiThu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenNguoiThu.setText("Steven Levy");
        pnlFrameListen.add(lblTenNguoiThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 300, 30));

        btnPlay.setBackground(new java.awt.Color(251, 251, 251));
        btnPlay.setBorder(null);
        btnPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/play-button-arrowhead.png"))); // NOI18N
        btnPlay.setBoderColor(new java.awt.Color(251, 251, 251));
        btnPlay.setColorClick(new java.awt.Color(255, 255, 255));
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });
        pnlFrameListen.add(btnPlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 470, 40, 40));

        lblTimeLast.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTimeLast.setText("5:00");
        lblTimeLast.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                lblTimeLastAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        pnlFrameListen.add(lblTimeLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 420, 100, -1));

        lblTimeFirst.setText("00:00");
        pnlFrameListen.add(lblTimeFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, -1, -1));

        sliderAudio.setColorSlider(new java.awt.Color(87, 190, 110));
        sliderAudio.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderAudioStateChanged(evt);
            }
        });
        pnlFrameListen.add(sliderAudio, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 220, -1));

        lblTenAudio.setFont(new java.awt.Font("Inter SemiBold", 0, 24)); // NOI18N
        lblTenAudio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenAudio.setText("Hacker Lược Sử");
        pnlFrameListen.add(lblTenAudio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 280, 70));

        lblVolumeRepeat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/repeat.png"))); // NOI18N
        lblVolumeRepeat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVolumeRepeatMouseClicked(evt);
            }
        });
        pnlFrameListen.add(lblVolumeRepeat, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 470, -1, 40));

        lblVolumeDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/volume-down.png"))); // NOI18N
        lblVolumeDown.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVolumeDownMouseClicked(evt);
            }
        });
        pnlFrameListen.add(lblVolumeDown, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, -1, 40));

        lblVolumeUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/volume.png"))); // NOI18N
        lblVolumeUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVolumeUpMouseClicked(evt);
            }
        });
        pnlFrameListen.add(lblVolumeUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 470, -1, 40));

        lblVolumeFull.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/speaker-filled-audio-tool.png"))); // NOI18N
        lblVolumeFull.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVolumeFullMouseClicked(evt);
            }
        });
        pnlFrameListen.add(lblVolumeFull, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 470, -1, 40));

        lblAnhAudio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/imgEbooks/5PhuongThuc.png"))); // NOI18N
        pnlFrameListen.add(lblAnhAudio, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 200, 310));

        pnlBossMain.add(pnlFrameListen, "card3");

        pnlMainProjebt.add(pnlBossMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 1060, 540));

        panelRadius2.setBackground(new java.awt.Color(205, 239, 215));
        panelRadius2.setRadius(20);
        panelRadius2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/search (1).png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        panelRadius2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 50, 40));

        txtSearch.setBackground(new java.awt.Color(205, 239, 215));
        txtSearch.setBorder(null);
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });
        panelRadius2.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 410, 40));

        pnlMainProjebt.add(panelRadius2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 480, 60));

        imageAvatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Image/41b92ec3eab97e4c24b3f6e8fe75ddec.png"))); // NOI18N
        imageAvatar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imageAvatar1MouseClicked(evt);
            }
        });
        pnlMainProjebt.add(imageAvatar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 25, 70, 70));

        pnlSetting.setBackground(new java.awt.Color(205, 239, 215));
        pnlSetting.setRadius(10);
        pnlSetting.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlSettingMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlSettingMouseExited(evt);
            }
        });
        pnlSetting.setLayout(new java.awt.GridBagLayout());

        btnSetting.setBackground(new java.awt.Color(205, 239, 215));
        btnSetting.setBorder(null);
        btnSetting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/settings (1).png"))); // NOI18N
        btnSetting.setBoderColor(new java.awt.Color(205, 239, 215));
        btnSetting.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSettingMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSettingMouseExited(evt);
            }
        });
        btnSetting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettingActionPerformed(evt);
            }
        });
        pnlSetting.add(btnSetting, new java.awt.GridBagConstraints());

        pnlMainProjebt.add(pnlSetting, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 40, 40, 40));

        pnlOff.setBackground(new java.awt.Color(205, 239, 215));
        pnlOff.setRadius(10);
        pnlOff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlOffMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlOffMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlOffMouseExited(evt);
            }
        });
        pnlOff.setLayout(new java.awt.GridBagLayout());

        lblOff.setBackground(new java.awt.Color(205, 239, 215));
        lblOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/power.png"))); // NOI18N
        lblOff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblOffMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblOffMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblOffMouseExited(evt);
            }
        });
        pnlOff.add(lblOff, new java.awt.GridBagConstraints());

        pnlMainProjebt.add(pnlOff, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 40, 40, 40));

        lblDay.setFont(new java.awt.Font("Inter Medium", 0, 18)); // NOI18N
        lblDay.setText("17/11/2022");
        pnlMainProjebt.add(lblDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 660, -1, -1));

        lblTime.setFont(new java.awt.Font("Inter Medium", 0, 18)); // NOI18N
        lblTime.setText("22 : 30 CH");
        pnlMainProjebt.add(lblTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 50, -1, -1));

        btnBell.setBackground(new java.awt.Color(205, 239, 215));
        btnBell.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/bell (1).png"))); // NOI18N
        btnBell.setBoderColor(new java.awt.Color(205, 239, 215));
        btnBell.setRadius(10);
        btnBell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBellMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBellMouseExited(evt);
            }
        });
        btnBell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBellActionPerformed(evt);
            }
        });
        pnlMainProjebt.add(btnBell, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 40, 40, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMainProjebt, javax.swing.GroupLayout.PREFERRED_SIZE, 1230, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMainProjebt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 683, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainActionPerformed
        //SetColorPanel
        SetColorPanel(pnlMain, true);
        SetColorPanel(pnlManage, false);
        SetColorPanel(pnlStatistical, false);
        SetColorPanel(pnlRead, false);
        SetColorPanel(pnlListen, false);
        //SetColorBtn
        SetColorIconBtn(btnMain, true, MainA, MainB);
        SetColorIconBtn(btnManage, false, ManageA, ManageB);
        SetColorIconBtn(btnStatistical, false, StatisticalA, StatisticalB);
        SetColorIconBtn(btnRead, false, ReadA, ReadB);
        SetColorIconBtn(btnListen, false, ListenA, ListenB);
        //SetFrameHienThi
        pnlFrameMain.show(true);
        pnlFrameManage.show(false);
        pnlFrameStatistical.show(false);
        pnlFrameRead.show(false);
        pnlFrameListen.show(false);


    }//GEN-LAST:event_btnMainActionPerformed

    private void btnManageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageActionPerformed
        SetColorPanel(pnlMain, false);
        SetColorPanel(pnlManage, true);
        SetColorPanel(pnlStatistical, false);
        SetColorPanel(pnlRead, false);
        SetColorPanel(pnlListen, false);
        //SetColorBtn
        SetColorIconBtn(btnMain, false, MainA, MainB);
        SetColorIconBtn(btnManage, true, ManageA, ManageB);
        SetColorIconBtn(btnStatistical, false, StatisticalA, StatisticalB);
        SetColorIconBtn(btnRead, false, ReadA, ReadB);
        SetColorIconBtn(btnListen, false, ListenA, ListenB);
        //SetFrameHienThi
        pnlFrameMain.show(false);
        pnlFrameManage.show(true);
        pnlFrameStatistical.show(false);
        pnlFrameRead.show(false);
        pnlFrameListen.show(false);
    }//GEN-LAST:event_btnManageActionPerformed

    private void btnStatisticalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatisticalActionPerformed
        fillComBoBoxNamTU();
        SetColorPanel(pnlMain, false);
        SetColorPanel(pnlManage, false);
        SetColorPanel(pnlStatistical, true);
        SetColorPanel(pnlRead, false);
        SetColorPanel(pnlListen, false);
        //SetColorBtn
        SetColorIconBtn(btnMain, false, MainA, MainB);
        SetColorIconBtn(btnManage, false, ManageA, ManageB);
        SetColorIconBtn(btnStatistical, true, StatisticalA, StatisticalB);
        SetColorIconBtn(btnRead, false, ReadA, ReadB);
        SetColorIconBtn(btnListen, false, ListenA, ListenB);
        //SetFrameHienThi
        pnlFrameMain.show(false);
        pnlFrameManage.show(false);
        pnlFrameStatistical.show(true);
        pnlFrameRead.show(false);
        pnlFrameListen.show(false);
    }//GEN-LAST:event_btnStatisticalActionPerformed

    private void btnListenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListenActionPerformed
        setModelAudio();
        fillTableAudio(tblAudio);
        SetColorPanel(pnlMain, false);
        SetColorPanel(pnlManage, false);
        SetColorPanel(pnlStatistical, false);
        SetColorPanel(pnlRead, false);
        SetColorPanel(pnlListen, true);
        //SetColorBtn
        SetColorIconBtn(btnMain, false, MainA, MainB);
        SetColorIconBtn(btnManage, false, ManageA, ManageB);
        SetColorIconBtn(btnStatistical, false, StatisticalA, StatisticalB);
        SetColorIconBtn(btnRead, false, ReadA, ReadB);
        SetColorIconBtn(btnListen, true, ListenA, ListenB);
        //SetFrameHienThi
        pnlFrameMain.show(false);
        pnlFrameManage.show(false);
        pnlFrameStatistical.show(false);
        pnlFrameRead.show(false);
        pnlFrameListen.show(true);
    }//GEN-LAST:event_btnListenActionPerformed

    private void btnReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReadActionPerformed
        fillTableListSachDoc();
        SetColorPanel(pnlMain, false);
        SetColorPanel(pnlManage, false);
        SetColorPanel(pnlStatistical, false);
        SetColorPanel(pnlRead, true);
        SetColorPanel(pnlListen, false);
        //SetColorBtn
        SetColorIconBtn(btnMain, false, MainA, MainB);
        SetColorIconBtn(btnManage, false, ManageA, ManageB);
        SetColorIconBtn(btnStatistical, false, StatisticalA, StatisticalB);
        SetColorIconBtn(btnRead, true, ReadA, ReadB);
        SetColorIconBtn(btnListen, false, ListenA, ListenB);
        //SetFrameHienThi
        pnlFrameMain.show(false);
        pnlFrameManage.show(false);
        pnlFrameStatistical.show(false);
        pnlFrameRead.show(true);
        pnlFrameListen.show(false);
    }//GEN-LAST:event_btnReadActionPerformed

    private void lblOffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOffMouseClicked
        TaiKhoan tk = ShareHelper.USER;
        int newHour = tk.getThoiLuong().getHours() - hour;
        int newMin = tk.getThoiLuong().getMinutes() - min;
        int newSec = tk.getThoiLuong().getSeconds() - sec;
        tk.setThoiLuong(Time.valueOf(newHour + ":" + newMin + ":" + newSec));
        DaoTK.update(tk);
        System.exit(0);
    }//GEN-LAST:event_lblOffMouseClicked

    private void panelBorder1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder1MouseClicked

    }//GEN-LAST:event_panelBorder1MouseClicked

    private void btnSettingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSettingMouseEntered
        pnlSetting.setBackground(new Color(130, 219, 150));
        btnSetting.setBackground(new Color(130, 219, 150));
    }//GEN-LAST:event_btnSettingMouseEntered

    private void btnSettingMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSettingMouseExited
        pnlSetting.setBackground(new Color(205, 239, 215));
        btnSetting.setBackground(new Color(205, 239, 215));
        btnSetting.setBoderColor(new Color(0, 0, 0, 0));

    }//GEN-LAST:event_btnSettingMouseExited

    private void pnlSettingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlSettingMouseEntered
        pnlSetting.setBackground(new Color(130, 219, 150));
        btnSetting.setBackground(new Color(130, 219, 150));
        btnSetting.setBoderColor(new Color(0, 0, 0, 0));
    }//GEN-LAST:event_pnlSettingMouseEntered

    private void pnlSettingMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlSettingMouseExited
        pnlSetting.setBackground(new Color(205, 239, 215));
        btnSetting.setBackground(new Color(205, 239, 215));
    }//GEN-LAST:event_pnlSettingMouseExited

    private void lblOffMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOffMouseEntered
        lblOff.setBackground(new Color(0, 0, 0, 0));
        pnlOff.setBackground(new Color(253, 127, 127));
    }//GEN-LAST:event_lblOffMouseEntered

    private void lblOffMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOffMouseExited
        pnlOff.setBackground(new Color(205, 239, 215));
    }//GEN-LAST:event_lblOffMouseExited

    private void pnlOffMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlOffMouseEntered

        lblOff.setBackground(new Color(0, 0, 0, 0));
        pnlOff.setBackground(new Color(253, 127, 127));
    }//GEN-LAST:event_pnlOffMouseEntered

    private void pnlOffMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlOffMouseExited
        pnlOff.setBackground(new Color(205, 239, 215));
    }//GEN-LAST:event_pnlOffMouseExited

    private void btnSettingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettingActionPerformed
        OpenSetting();
        if (SettingDiaLog.TacMain) {
            this.setVisible(false);
            new LogInDiaLog(this, true).setVisible(true);
            this.setVisible(true);
        }
    }//GEN-LAST:event_btnSettingActionPerformed

    private void imageAvatar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageAvatar1MouseClicked

    }//GEN-LAST:event_imageAvatar1MouseClicked
    int i = 0;
    private void btnThemNguoiDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNguoiDungActionPerformed
        MaND = null;
        new PersonDiaLog(this, true).setVisible(true);
        fillTableNguoiDung();
    }//GEN-LAST:event_btnThemNguoiDungActionPerformed

    private void btnThemLoaiSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLoaiSachActionPerformed
        new TypeBooksDiaLog(this, true).setVisible(true);
        fillTableTheLoai();

    }//GEN-LAST:event_btnThemLoaiSachActionPerformed


    private void tblNguoiDungMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNguoiDungMousePressed
        if (evt.getClickCount() == 2) {
            int indexRow = tblNguoiDung.getSelectedRow();
            String MaNguoiDung = (String) tblNguoiDung.getValueAt(indexRow, 0);
            nguoiDung = DaoND.findById(MaNguoiDung);
            new PersonDiaLog(this, true, nguoiDung).setVisible(true);
        }
    }//GEN-LAST:event_tblNguoiDungMousePressed

    private void tblThucUongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThucUongMousePressed
        if (evt.getClickCount() == 2) {
            int indexRow = tblThucUong.getSelectedRow();
            String maThucUong = (String) tblThucUong.getValueAt(indexRow, 0);
            thucUong = DaoTU.findById(maThucUong);
            new DrinksDiaLog(this, true, thucUong).setVisible(true);
            this.fillTableThucUong();
        }
    }//GEN-LAST:event_tblThucUongMousePressed

    private void btnThucUongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThucUongActionPerformed
        // TODO add your handling code here:
        new DrinksDiaLog(this, true).setVisible(true);
        this.fillTableThucUong();
    }//GEN-LAST:event_btnThucUongActionPerformed

    private void btnSuaThongTinThucUongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaThongTinThucUongActionPerformed
        int index = tblThucUong.getSelectedRow();
        if (index < 0) {
            DialogHelper.alert(this, "Chưa chọn thức uống cần chỉnh sửa!");
        } else {
            String maThucUong = (String) tblThucUong.getValueAt(index, 0);
            thucUong = DaoTU.findById(maThucUong);
            new DrinksDiaLog(this, true, thucUong).setVisible(true);
            this.fillTableThucUong();
        }

    }//GEN-LAST:event_btnSuaThongTinThucUongActionPerformed

    private void btnXoaThucUongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaThucUongActionPerformed
        int index = tblThucUong.getSelectedRow();
        if (index < 0) {
            DialogHelper.alert(this, "Chưa chọn thức uống cần xóa!");
        } else if (DialogHelper.confirm(this, "Bạn thật sự muốn xóa thức uống này?")) {
            DaoTU.delete(tblThucUong.getValueAt(index, 0).toString());
            this.fillTableThucUong();
        }
    }//GEN-LAST:event_btnXoaThucUongActionPerformed

    private void btnFirstThucUongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstThucUongActionPerformed
        int index = tblThucUong.getSelectedRow();
        UtilityHelper.first(index, tblThucUong);
    }//GEN-LAST:event_btnFirstThucUongActionPerformed

    private void btnPreThucUongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreThucUongActionPerformed
        int index = tblThucUong.getSelectedRow();
        UtilityHelper.previous(index, tblThucUong, listTU);
    }//GEN-LAST:event_btnPreThucUongActionPerformed

    private void btnNextThucUongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextThucUongActionPerformed
        int index = tblThucUong.getSelectedRow();
        UtilityHelper.next(index, tblThucUong, listTU);
    }//GEN-LAST:event_btnNextThucUongActionPerformed

    private void btnLastThucUongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastThucUongActionPerformed
        int index = tblThucUong.getSelectedRow();
        UtilityHelper.last(index, tblThucUong, listTU);
    }//GEN-LAST:event_btnLastThucUongActionPerformed

    private void btnXoaNguoiDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNguoiDungActionPerformed
        int index = tblNguoiDung.getSelectedRow();
        if (index != -1) {
            deleteNguoiDung();
            fillTableNguoiDung();
        } else {
            DialogHelper.alert(this, "Hãy Chọn Người Dùng");
        }


    }//GEN-LAST:event_btnXoaNguoiDungActionPerformed

    private void btnThemTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemTaiKhoanActionPerformed
        TenDangNhap = null;
        new AccountDiaLog(this, true).setVisible(true);

        LoadTaiKhoan();
        fillTableTaiKhoan(listTK);
    }//GEN-LAST:event_btnThemTaiKhoanActionPerformed

    private void btnXoaTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTaiKhoanActionPerformed
        int index = tblTaiKhoan.getSelectedRow();

        if (index != -1) {
            deleteTaiKhoan();
            LoadTaiKhoan();
            fillTableTaiKhoan(listTK);
        }

    }//GEN-LAST:event_btnXoaTaiKhoanActionPerformed

    private void btnSuaNguoiDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNguoiDungActionPerformed
        int index = tblNguoiDung.getSelectedRow();
        if (index != -1) {
            MaND = tblNguoiDung.getValueAt(index, 0).toString();
            NguoiDung nd = DaoND.findById(MaND);
            new PersonDiaLog(this, true, nd).setVisible(true);
            fillTableNguoiDung();
        } else {
            DialogHelper.alert(this, "Hãy Chọn Người Dùng");
        }


    }//GEN-LAST:event_btnSuaNguoiDungActionPerformed

    private void lblVolumeDownMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVolumeDownMouseClicked
        volumeDownControl(0.1);
    }//GEN-LAST:event_lblVolumeDownMouseClicked

    private void lblVolumeUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVolumeUpMouseClicked
        volumeUpControl(0.1);
    }//GEN-LAST:event_lblVolumeUpMouseClicked

    private void lblVolumeFullMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVolumeFullMouseClicked
        try {
            if (muteOnOff) {
                muteOnOff = false;
                volumeControl(1.0);
                String image = "..\\DuAn01_G3_ebooks\\src\\com\\ebooks\\Icon\\speaker-filled-audio-tool.png";
                lblVolumeFull.setIcon(new ImageIcon(image));
            } else {
                muteOnOff = true;
                volumeControl(0.0);
                String image = "..\\DuAn01_G3_ebooks\\src\\com\\ebooks\\Icon\\mute.png";
                lblVolumeFull.setIcon(new ImageIcon(image));
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_lblVolumeFullMouseClicked

    private void lblVolumeRepeatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVolumeRepeatMouseClicked

        try {
            if (repeat == false) {
                repeat = true;
                player.setRepeat(repeat);
                String image = "..\\DuAn01_G3_ebooks\\src\\com\\ebooks\\Icon\\repeat-once.png";
                lblVolumeRepeat.setIcon(new ImageIcon(image));
            } else if (repeat == true) {
                repeat = false;
                player.setRepeat(repeat);
                String image = "..\\DuAn01_G3_ebooks\\src\\com\\ebooks\\Icon\\repeat.png";
                lblVolumeRepeat.setIcon(new ImageIcon(image));
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_lblVolumeRepeatMouseClicked

    private void lblTimeLastAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_lblTimeLastAncestorAdded

    }//GEN-LAST:event_lblTimeLastAncestorAdded

    private void tblAudioQLMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAudioQLMousePressed
        if (evt.getClickCount() == 2) {
            int index = tblAudioQL.getSelectedRow();
            String maAudio = tblAudioQL.getValueAt(index, 0).toString();
            AudioSach audio = DAOAU.findById(maAudio);
            new AudiosDiaLog(this, true, audio).setVisible(true);
            fillTableAudio(tblAudioQL);
            fillTableAudio(tblAudio);
        }
    }//GEN-LAST:event_tblAudioQLMousePressed

    private void btnSapXepTheoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSapXepTheoTenActionPerformed
        SortAudio();
    }//GEN-LAST:event_btnSapXepTheoTenActionPerformed

    private void btnThemAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemAudioActionPerformed
        new AudiosDiaLog(this, true).setVisible(true);
        fillTableAudio(tblAudioQL);
        fillTableAudio(tblAudio);
    }//GEN-LAST:event_btnThemAudioActionPerformed

    private void btnSuaAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaAudioActionPerformed

        int index = tblAudioQL.getSelectedRow();
        if (index != -1) {
            String maAudio = tblAudioQL.getValueAt(index, 0).toString();
            AudioSach audio = DAOAU.findById(maAudio);
            new AudiosDiaLog(this, true, audio).setVisible(true);
            fillTableAudio(tblAudioQL);
            fillTableAudio(tblAudio);
        } else {
            DialogHelper.alert(this, "Bạn Hãy Chọn Audio");
        }

    }//GEN-LAST:event_btnSuaAudioActionPerformed

    private void btnXoaAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaAudioActionPerformed
        int index = tblAudioQL.getSelectedRow();

        if (index < 0) {
            DialogHelper.alert(this, "Chưa chọn Audio cần xóa!");
        } else if (DialogHelper.confirm(this, "Bạn thật sự muốn xóa Audio này?")) {
            try {
                String MaAuido = tblAudioQL.getValueAt(index, 0).toString();
                DAOAU.delete(MaAuido);
                this.fillTableAudio(tblAudioQL);
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_btnXoaAudioActionPerformed

    private void btnFirstAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstAudioActionPerformed
        int index = tblAudioQL.getSelectedRow();
        UtilityHelper.first(index, tblAudioQL);
    }//GEN-LAST:event_btnFirstAudioActionPerformed

    private void btnPrevAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevAudioActionPerformed
        int index = tblAudioQL.getSelectedRow();
        UtilityHelper.previous(index, tblAudioQL, listAudio);
    }//GEN-LAST:event_btnPrevAudioActionPerformed

    private void btnNextAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextAudioActionPerformed

        int index = tblAudioQL.getSelectedRow();
        UtilityHelper.next(index, tblAudioQL, listAudio);
    }//GEN-LAST:event_btnNextAudioActionPerformed

    private void btnLastAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastAudioActionPerformed
        int index = tblAudioQL.getSelectedRow();
        UtilityHelper.last(index, tblAudioQL, listAudio);
    }//GEN-LAST:event_btnLastAudioActionPerformed

    private void btnFirstSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstSachActionPerformed
        int index = tblSach.getSelectedRow();
        UtilityHelper.first(index, tblSach);
    }//GEN-LAST:event_btnFirstSachActionPerformed

    private void btnNextSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextSachActionPerformed
        int index = tblSach.getSelectedRow();
        UtilityHelper.previous(index, tblSach, listS);
    }//GEN-LAST:event_btnNextSachActionPerformed

    private void btnPrevSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevSachActionPerformed
        int index = tblSach.getSelectedRow();
        UtilityHelper.next(index, tblSach, listS);
    }//GEN-LAST:event_btnPrevSachActionPerformed

    private void btnLastSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastSachActionPerformed
        int index = tblSach.getSelectedRow();
        UtilityHelper.last(index, tblSach, listS);
    }//GEN-LAST:event_btnLastSachActionPerformed

    private void tblSachMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSachMousePressed
        if (evt.getClickCount() == 2) {
            int index = tblSach.getSelectedRow();
            String maSach = (String) tblSach.getValueAt(index, 0);
            Sach sach = DAOS.findById(maSach);
            new BooksDiaLog(this, true, sach).setVisible(true);
            fillTableSach();
        }
    }//GEN-LAST:event_tblSachMousePressed

    private void cboTheLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTheLoaiActionPerformed
        int index = cboTheLoai.getSelectedIndex();
        if (index != -1) {
            OtionTableSach(index);
        }
    }//GEN-LAST:event_cboTheLoaiActionPerformed

    private void btnSapXepSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSapXepSachActionPerformed
        SortSach();
    }//GEN-LAST:event_btnSapXepSachActionPerformed

    private void btnThemSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSachActionPerformed
        new BooksDiaLog(this, true).setVisible(true);
        fillTableSach();
    }//GEN-LAST:event_btnThemSachActionPerformed

    private void btnCapNhatSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatSachActionPerformed
        int index = tblSach.getSelectedRow();
        if (index != -1) {
            String maSach = (String) tblSach.getValueAt(index, 0);
            Sach sach = DAOS.findById(maSach);
            new BooksDiaLog(this, true, sach).setVisible(true);
            fillTableSach();
        } else {
            DialogHelper.alert(this, "Hãy Chọn Sách");
        }
    }//GEN-LAST:event_btnCapNhatSachActionPerformed

    private void btnXoaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSachActionPerformed
        int index = tblSach.getSelectedRow();
        if (index == -1) {
            DialogHelper.alert(this, "Chưa chọn Sách cần xóa!");
        } else if (DialogHelper.confirm(this, "Bạn thật sự muốn xóa Sách này?")) {
            String MaSach = tblSach.getValueAt(index, 0).toString();
            try {
                LoaiSS lss = DAOLSS.findByBook(MaSach);
                if (lss != null) {
                    DAOLSS.delete(lss.getMaLoaiSS());
                }
                DAOS.delete(MaSach);
                this.fillTableSach();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_btnXoaSachActionPerformed

    private void tblTheLoaiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTheLoaiMousePressed
        if (evt.getClickCount() == 2) {
            int indexRow = tblTheLoai.getSelectedRow();
            String MaTheLoai = (String) tblTheLoai.getValueAt(indexRow, 0);
            theLoai = DAOTL.findById(MaTheLoai);
            new TypeBooksDiaLog(this, true, theLoai).setVisible(true);
            fillTableTheLoai();
        }
    }//GEN-LAST:event_tblTheLoaiMousePressed

    private void btnSuaLoaiSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaLoaiSachActionPerformed
        int index = tblTheLoai.getSelectedRow();
        if (index != -1) {
            String maTL = (String) tblTheLoai.getValueAt(index, 0);
            TheLoai tLoai = DAOTL.findById(maTL);
            new TypeBooksDiaLog(this, true, tLoai).setVisible(true);
            fillTableTheLoai();
        } else {
            DialogHelper.alert(this, "Hãy Chọn The Loai");
        }
    }//GEN-LAST:event_btnSuaLoaiSachActionPerformed

    private void btnXoaLoaiSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaLoaiSachActionPerformed
        int index = tblTheLoai.getSelectedRow();
        if (index != -1) {
            deleteTheLoai();
            fillTableTheLoai();
        } else {
            DialogHelper.alert(this, "Hãy Chọn The Loai");
        }

    }//GEN-LAST:event_btnXoaLoaiSachActionPerformed

    private void btnFirstLoaiSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstLoaiSachActionPerformed
        int index = tblTheLoai.getSelectedRow();
        UtilityHelper.first(index, tblTheLoai);
    }//GEN-LAST:event_btnFirstLoaiSachActionPerformed

    private void btnPrevLoaiSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevLoaiSachActionPerformed
        int index = tblTheLoai.getSelectedRow();
        UtilityHelper.previous(index, tblTheLoai, listTL);
    }//GEN-LAST:event_btnPrevLoaiSachActionPerformed

    private void btnNextLoaiSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextLoaiSachActionPerformed
        int index = tblTheLoai.getSelectedRow();
        UtilityHelper.next(index, tblTheLoai, listTL);
    }//GEN-LAST:event_btnNextLoaiSachActionPerformed

    private void btnLastLoaiSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastLoaiSachActionPerformed
        int index = tblTheLoai.getSelectedRow();
        UtilityHelper.last(index, tblTheLoai, listTL);
    }//GEN-LAST:event_btnLastLoaiSachActionPerformed

    private void btnThemTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemTacGiaActionPerformed
        new AuthorDiaLog(this, true).setVisible(true);
        fillTableTacGia();
    }//GEN-LAST:event_btnThemTacGiaActionPerformed

    private void btnSuaThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaThongTinActionPerformed
        int index = tblTacGia.getSelectedRow();
        if (index != -1) {
            String maTG = (String) tblTacGia.getValueAt(index, 0);
            TacGia tgia = DAOTG.findById(maTG);
            new AuthorDiaLog(this, true, tgia).setVisible(true);
            fillTableTacGia();
        } else {
            DialogHelper.alert(this, "Hãy Chọn Tac Gia");
        }
    }//GEN-LAST:event_btnSuaThongTinActionPerformed

    private void btnXoaTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTacGiaActionPerformed
        deleteTacGia();
        fillTableTacGia();
    }//GEN-LAST:event_btnXoaTacGiaActionPerformed

    private void tblTacGiaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTacGiaMousePressed
        if (evt.getClickCount() == 2) {
            int indexRow = tblTacGia.getSelectedRow();
            String MaTacGia = (String) tblTacGia.getValueAt(indexRow, 0);
            tacGia = DAOTG.findById(MaTacGia);
            new AuthorDiaLog(this, true, tacGia).setVisible(true);
            fillTableTacGia();
        }
    }//GEN-LAST:event_tblTacGiaMousePressed

    private void btnFirstTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstTacGiaActionPerformed
        int index = tblTacGia.getSelectedRow();
        UtilityHelper.first(index, tblTacGia);
    }//GEN-LAST:event_btnFirstTacGiaActionPerformed

    private void btnPrevTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevTacGiaActionPerformed
        int index = tblTacGia.getSelectedRow();
        UtilityHelper.previous(index, tblTacGia, listTG);
    }//GEN-LAST:event_btnPrevTacGiaActionPerformed

    private void btnNextTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextTacGiaActionPerformed
        int index = tblTacGia.getSelectedRow();
        UtilityHelper.next(index, tblTacGia, listTG);
    }//GEN-LAST:event_btnNextTacGiaActionPerformed

    private void btnLastTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastTacGiaActionPerformed
        int index = tblTacGia.getSelectedRow();
        UtilityHelper.last(index, tblTacGia, listTG);
    }//GEN-LAST:event_btnLastTacGiaActionPerformed

    private void tblTaiKhoanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTaiKhoanMousePressed
        if (evt.getClickCount() == 2) {
            int index = tblTaiKhoan.getSelectedRow();
            TenDangNhap = tblTaiKhoan.getValueAt(index, 0).toString();

            new AccountDiaLog(this, true).setVisible(true);
            LoadTaiKhoan();
            fillTableTaiKhoan(listTK);
        }
    }//GEN-LAST:event_tblTaiKhoanMousePressed

    private void btnSuaTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaTaiKhoanActionPerformed
        int index = tblTaiKhoan.getSelectedRow();
        if (index != -1) {
            TenDangNhap = tblTaiKhoan.getValueAt(index, 0).toString();
            new AccountDiaLog(this, true).setVisible(true);
            LoadTaiKhoan();
            fillTableTaiKhoan(listTK);
        } else {
            DialogHelper.alert(this, "Hãy Chọn Tài Khoản");
        }
    }//GEN-LAST:event_btnSuaTaiKhoanActionPerformed

    private void btnFirstTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstTaiKhoanActionPerformed
        int index = tblTaiKhoan.getSelectedRow();
        UtilityHelper.first(index, tblTaiKhoan);
    }//GEN-LAST:event_btnFirstTaiKhoanActionPerformed

    private void btnPrevTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevTaiKhoanActionPerformed
        int index = tblTaiKhoan.getSelectedRow();
        UtilityHelper.previous(index, tblTaiKhoan, listTK);
    }//GEN-LAST:event_btnPrevTaiKhoanActionPerformed

    private void btnNextTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextTaiKhoanActionPerformed
        int index = tblTaiKhoan.getSelectedRow();
        UtilityHelper.next(index, tblTaiKhoan, listTK);
    }//GEN-LAST:event_btnNextTaiKhoanActionPerformed

    private void btnLastTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastTaiKhoanActionPerformed
        int index = tblTaiKhoan.getSelectedRow();
        UtilityHelper.last(index, tblTaiKhoan, listTK);
    }//GEN-LAST:event_btnLastTaiKhoanActionPerformed

    private void btnFirstNguoiDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstNguoiDungActionPerformed
        int index = tblNguoiDung.getSelectedRow();
        UtilityHelper.first(index, tblNguoiDung);
    }//GEN-LAST:event_btnFirstNguoiDungActionPerformed

    private void btnPrevNguoiDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevNguoiDungActionPerformed
        int index = tblNguoiDung.getSelectedRow();
        UtilityHelper.previous(index, tblNguoiDung, listND);
    }//GEN-LAST:event_btnPrevNguoiDungActionPerformed

    private void btnNextNguoiDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextNguoiDungActionPerformed
        int index = tblNguoiDung.getSelectedRow();
        UtilityHelper.next(index, tblNguoiDung, listND);
    }//GEN-LAST:event_btnNextNguoiDungActionPerformed

    private void btnLastNguoiDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastNguoiDungActionPerformed
        int index = tblNguoiDung.getSelectedRow();
        UtilityHelper.last(index, tblNguoiDung, listND);
    }//GEN-LAST:event_btnLastNguoiDungActionPerformed

    private void btnThemHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHoaDonActionPerformed

        new ReceiptDiaLog(this, true).setVisible(true);
        fillTableHoaDon();
        fillTableDTTS();
        fillTableDTTU();
    }//GEN-LAST:event_btnThemHoaDonActionPerformed

    private void btnSuaThongTinHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaThongTinHoaDonActionPerformed
        int indexTab = tabDoanhThu.getSelectedIndex();
        if (indexTab == 0) {
            int index = tblHoaDon.getSelectedRow();
            if (index != -1) {
                try {
                    HoaDonTongHop hdth = new HoaDonTongHop();
                    String maNguoiDung = (String) tblHoaDon.getValueAt(index, 0);
                    String HoTen = (String) tblHoaDon.getValueAt(index, 1);
                    String tenDangNhap = (String) tblHoaDon.getValueAt(index, 2);
                    String maHDThucUong = String.valueOf(tblHoaDon.getValueAt(index, 3));
                    float tongTienThucUong = (Float) tblHoaDon.getValueAt(index, 4);
                    String maHDThueSach = String.valueOf(tblHoaDon.getValueAt(index, 5));
                    float tongTienThueSach = (Float) tblHoaDon.getValueAt(index, 6);
                    float TongTien = (Float) tblHoaDon.getValueAt(index, 7);
                    hdth.setMaNguoiDung(maNguoiDung);
                    hdth.setHoTen(HoTen);
                    hdth.setTenDangNhap(tenDangNhap);
                    hdth.setMaHoaDonThucUong(maHDThucUong);
                    hdth.setTongTienThucUong(tongTienThucUong);
                    hdth.setMaHoaDonThueSach(maHDThueSach);
                    hdth.setTongTienThueSach(tongTienThueSach);
                    hdth.setTongTien(TongTien);
                    new ReceiptDiaLog(this, true, hdth).setVisible(true);
                } catch (Exception e) {
                }
            } else {
                DialogHelper.alert(this, "Hãy Chọn Hóa Đơn");
            }
        } else if (indexTab == 1) {
            int index = tblDoanhThuThucUong.getSelectedRow();
            if (index != -1) {
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                HoaDonThucUong hdtu = new HoaDonThucUong();
                hdtu.setMaHoaDon(String.valueOf(tblDoanhThuThucUong.getValueAt(index, 2)));
                hdtu.setMaNguoiDung(String.valueOf(tblDoanhThuThucUong.getValueAt(index, 0)));
                Float tongTienThucUong = (Float) tblDoanhThuThucUong.getValueAt(index, 3);
                new ReceiptDiaLog(this, congTac, hdtu, tongTienThucUong).setVisible(true);
                fillTableDTTU();
            } else {
                DialogHelper.alert(this, "Hãy Chọn Hóa Đơn");
            }
        } else if (indexTab == 2) {
            int index = tblDoanhThuThueSach.getSelectedRow();
            if (index != -1) {
                HoaDonThueSach hdts = new HoaDonThueSach();
                hdts.setMaThueSach(String.valueOf(tblDoanhThuThueSach.getValueAt(index, 3)));
                hdts.setMaNguoiDung(String.valueOf(tblDoanhThuThueSach.getValueAt(index, 0)));
                hdts.setTenDangNhap(String.valueOf(tblDoanhThuThueSach.getValueAt(index, 2)));
                hdts.setNgayThue(DateHelper.toDate(String.valueOf(tblDoanhThuThueSach.getValueAt(index, 5))));
                new ReceiptDiaLog(this, congTac, hdts).setVisible(true);
                fillTableDTTS();
            } else {
                DialogHelper.alert(this, "Hãy Chọn Hóa Đơn");
            }

        }


    }//GEN-LAST:event_btnSuaThongTinHoaDonActionPerformed

    private void btnXoaThongTinHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaThongTinHoaDonActionPerformed
        DeleteHoaDon();
    }//GEN-LAST:event_btnXoaThongTinHoaDonActionPerformed

    private void tblHoaDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMousePressed
        if (evt.getClickCount() == 2) {
//            try {
            int index = tblHoaDon.getSelectedRow();
            HoaDonTongHop hdth = new HoaDonTongHop();
            String maNguoiDung = (String) tblHoaDon.getValueAt(index, 0);
            String HoTen = (String) tblHoaDon.getValueAt(index, 1);
            String tenDangNhap = (String) tblHoaDon.getValueAt(index, 2);
            String maHDThucUong = String.valueOf(tblHoaDon.getValueAt(index, 3));
            float tongTienThucUong = (Float) tblHoaDon.getValueAt(index, 4);
            String maHDThueSach = String.valueOf(tblHoaDon.getValueAt(index, 5));
            float tongTienThueSach = (Float) tblHoaDon.getValueAt(index, 6);
            float TongTien = (Float) tblHoaDon.getValueAt(index, 7);
            hdth.setMaNguoiDung(maNguoiDung);
            hdth.setHoTen(HoTen);
            hdth.setTenDangNhap(tenDangNhap);
            hdth.setMaHoaDonThucUong(maHDThucUong);
            hdth.setTongTienThucUong(tongTienThucUong);
            hdth.setMaHoaDonThueSach(maHDThueSach);
            hdth.setTongTienThueSach(tongTienThueSach);
            hdth.setTongTien(TongTien);
            new ReceiptDiaLog(this, true, hdth).setVisible(true);
//            } catch (Exception e) { 
//            }

        }
    }//GEN-LAST:event_tblHoaDonMousePressed

    private void tblDoanhThuThucUongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDoanhThuThucUongMousePressed
        if (evt.getClickCount() == 2) {
            int index = tblDoanhThuThucUong.getSelectedRow();
            HoaDonChiTiet hdct = new HoaDonChiTiet();
            HoaDonThucUong hdtu = new HoaDonThucUong();
            hdtu.setMaHoaDon(String.valueOf(tblDoanhThuThucUong.getValueAt(index, 2)));
            hdtu.setMaNguoiDung(String.valueOf(tblDoanhThuThucUong.getValueAt(index, 0)));
            Float tongTienThucUong = (Float) tblDoanhThuThucUong.getValueAt(index, 3);
            new ReceiptDiaLog(this, true, hdtu, tongTienThucUong).setVisible(true);
            fillTableDTTU();
        }
    }//GEN-LAST:event_tblDoanhThuThucUongMousePressed

    private void tblDoanhThuThueSachMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDoanhThuThueSachMousePressed
        if (evt.getClickCount() == 2) {
            int index = tblDoanhThuThueSach.getSelectedRow();
            HoaDonThueSach hdts = new HoaDonThueSach();
            hdts.setMaThueSach(String.valueOf(tblDoanhThuThueSach.getValueAt(index, 3)));
            hdts.setMaNguoiDung(String.valueOf(tblDoanhThuThueSach.getValueAt(index, 0)));
            hdts.setTenDangNhap(String.valueOf(tblDoanhThuThueSach.getValueAt(index, 2)));
            hdts.setNgayThue(DateHelper.toDate(String.valueOf(tblDoanhThuThueSach.getValueAt(index, 5))));
            new ReceiptDiaLog(this, true, hdts).setVisible(true);
            fillTableDTTS();
        }
    }//GEN-LAST:event_tblDoanhThuThueSachMousePressed

    private void btnLastDTTSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastDTTSActionPerformed

        int index = listTKTS.size() - 1;
        UtilityHelper.last(index, tblTKDTThueSach, listTKTS);
    }//GEN-LAST:event_btnLastDTTSActionPerformed

    private void btnLastDTTUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastDTTUActionPerformed

        int index = listTKTU.size() - 1;
        UtilityHelper.last(index, tblTKDTThucUong, listTKTU);
    }//GEN-LAST:event_btnLastDTTUActionPerformed

    private void cboNamTSItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboNamTSItemStateChanged
        int index = cboNamTS.getSelectedIndex();
        if (index != -1) {
            int nam = Integer.parseInt(String.valueOf(cboNamTS.getSelectedItem()));
            fillTableTKTS(nam);
        }
    }//GEN-LAST:event_cboNamTSItemStateChanged

    private void cboNamTUItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboNamTUItemStateChanged
        int index = cboNamTU.getSelectedIndex();
        if (index != -1) {
            int nam = Integer.parseInt(String.valueOf(cboNamTU.getSelectedItem()));
            fillTableTKTU(nam);
        }
    }//GEN-LAST:event_cboNamTUItemStateChanged

    private void btnFirstDTTSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstDTTSActionPerformed
        int index = 0;
        UtilityHelper.first(index, tblTKDTThueSach);

    }//GEN-LAST:event_btnFirstDTTSActionPerformed

    private void btnPreDTTSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreDTTSActionPerformed

        int index = tblTKDTThueSach.getSelectedRow();
        if (index < 0) {
            index = listTKTS.size() - 1;
        }
        UtilityHelper.previous(index, tblTKDTThueSach, listTKTS);


    }//GEN-LAST:event_btnPreDTTSActionPerformed

    private void bntFistDTTUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntFistDTTUActionPerformed
        int index = 0;
        UtilityHelper.first(index, tblTKDTThucUong);

    }//GEN-LAST:event_bntFistDTTUActionPerformed

    private void btnPreDTTUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreDTTUActionPerformed

        int index = tblTKDTThucUong.getSelectedRow();
        UtilityHelper.previous(index, tblTKDTThucUong, listTKTU);

    }//GEN-LAST:event_btnPreDTTUActionPerformed

    private void btnNextDTTUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextDTTUActionPerformed
        int index = tblTKDTThucUong.getSelectedRow();
        UtilityHelper.next(index, tblTKDTThucUong, listTKTU);


    }//GEN-LAST:event_btnNextDTTUActionPerformed

    private void btnNextDTTSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextDTTSActionPerformed

        int index = tblTKDTThueSach.getSelectedRow();
        if (index > listTKTS.size() - 1) {
            index = 0;
        }
        UtilityHelper.next(index, tblTKDTThueSach, listTKTS);

    }//GEN-LAST:event_btnNextDTTSActionPerformed

    private void tblListSachDocMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListSachDocMousePressed
        try {
            int index = tblListSachDoc.getSelectedRow();
            if (index != -1) {
                Sach s = listS.get(index);
                System.out.println(s.getDuongDan());
                openpdf("..\\DuAn01_G3_ebooks\\src\\com\\ebooks\\pdf\\" + s.getDuongDan());
                materialTabbed3.setSelectedIndex(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_tblListSachDocMousePressed

    private void tblDanhSachCacSachMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachCacSachMousePressed
        if (evt.getClickCount() == 2) {
            int index = tblDanhSachCacSach.getSelectedRow();
            listS = DAOS.selectAll();
            if (index != -1) {
                try {
                    Sach sach = listS.get(index);
                    DisplayReadPanel();
                    openpdf("..\\DuAn01_G3_ebooks\\src\\com\\ebooks\\pdf\\" + sach.getDuongDan());
                    materialTabbed3.setSelectedIndex(0);
                } catch (Exception e) {
                }

            }
        }
    }//GEN-LAST:event_tblDanhSachCacSachMousePressed

    private void tblAudioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAudioMousePressed
        if (player != null) {
            playPase = false;
            runtimeFirst();
            player.pause();
        }
        if (evt.getClickCount() == 2) {
            int index = tblAudio.getSelectedRow();
            if (indexSeletor != index) {
                if (player != null) {
                    timeAuido = 0;
                    player.pause();
                    indexSeletor = index;
                }
            }
            Sach s = listS.get(index);
            AudioSach au = listAudio.get(index);
            lblTenAudio.setText(au.getTenAudio());
            lblTenNguoiThu.setText(au.getNguoiThu());
            lblAnhAudio.setIcon(new ImageIcon("..\\DuAn01_G3_ebooks\\src\\com\\Content\\imgEbooks\\" + DAOS.findById(au.getMaSach()).getHinh()));
            songFile = new File("..\\DuAn01_G3_ebooks\\src\\com\\ebooks\\audio\\" + au.getDuongDan());
            player = mp3Player();
            player.addToPlayList(songFile);
            player.skipForward();
            btnPlay.setIcon(new ImageIcon("..\\DuAn01_G3_ebooks\\src\\com\\ebooks\\Icon\\pause-button.png"));
            player.play();
            currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
            imagePath = "\\Icon";
            FileInputStream fileInputStream = null;
            long duration = 0;
            try {
                fileInputStream = new FileInputStream(songFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                duration = Objects.requireNonNull(fileInputStream).getChannel().size() / 128;
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(duration);
            if (timeAuido != duration) {
                I = 0;
                seconds = 0;
                minute = 0;
                timeAuido = (int) duration;
            }
            playPase = true;
            displayTimeAudio();
            runtimeFirst();
            congTac = false;

        }
    }//GEN-LAST:event_tblAudioMousePressed

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        if (player == null) {
            DialogHelper.alert(this, "Bạn chưa chọn sách nói");
            return;
        }

        try {

            if (congTac) {
                btnPlay.setIcon(new ImageIcon("..\\DuAn01_G3_ebooks\\src\\com\\ebooks\\Icon\\pause-button.png"));
                congTac = false;
                playPase = true;
                runtimeFirst();
                player.play();
            } else {
                btnPlay.setIcon(new ImageIcon("..\\DuAn01_G3_ebooks\\src\\com\\ebooks\\Icon\\play-button-arrowhead.png"));
                congTac = true;
                playPase = false;
                runtimeFirst();
                player.pause();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnPlayActionPerformed

    private void sliderAudioStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderAudioStateChanged

    }//GEN-LAST:event_sliderAudioStateChanged

    private void tblAudioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAudioMouseClicked

    }//GEN-LAST:event_tblAudioMouseClicked

    private void pnlOffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlOffMouseClicked
        System.exit(0);
    }//GEN-LAST:event_pnlOffMouseClicked

    private void tabQuanLyStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabQuanLyStateChanged
        int index = tabQuanLy.getSelectedIndex();
        if (index == -1) {
            return;
        } else if (index == 0) {
            fillTableTheLoai();
        } else if (index == 1) {
            fillComBoBoxTheLoai(cboTheLoai);
        } else if (index == 2) {
            fillTableAudio(tblAudioQL);
        } else if (index == 3) {
            fillTableTacGia();
        } else if (index == 4) {
            LoadTaiKhoan();
            fillTableTaiKhoan(listTK);
        } else if (index == 5) {
            fillTableNguoiDung();
        } else if (index == 6) {
            fillTableThucUong();
        } else if (index == 7){
            fillTableHoaDon();
        } else {
            fillTableBangGiaThue();
        }


    }//GEN-LAST:event_tabQuanLyStateChanged

    private void tabDoanhThuStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabDoanhThuStateChanged
        int index = tabDoanhThu.getSelectedIndex();
        if (index == -1) {
            return;
        } else if (index == 0) {
            fillTableHoaDon();
        } else if (index == 1) {
            fillTableDTTU();
        } else {
            fillTableDTTS();
        }
    }//GEN-LAST:event_tabDoanhThuStateChanged

    private void pnlMainProjebtMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMainProjebtMousePressed
//        initMoving(this, pnlMainProjebt);
    }//GEN-LAST:event_pnlMainProjebtMousePressed

    private void btnBellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBellActionPerformed
        if (!TimeDl) {
            timeDialog.setVisible(true);
            TimeDl = false;
        }
    }//GEN-LAST:event_btnBellActionPerformed

    private void btnBellMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBellMouseEntered
        btnBell.setBackground(new Color(130, 219, 150));
    }//GEN-LAST:event_btnBellMouseEntered

    private void btnBellMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBellMouseExited
        btnBell.setBackground(new Color(205, 239, 215));
    }//GEN-LAST:event_btnBellMouseExited

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            fillTableDanhSachCacSach();
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        fillTableDanhSachCacSach();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void pnl1tuanLamViecMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl1tuanLamViecMouseEntered
        pnl1tuanLamViec.setBackground(new Color(87, 190, 110));
    }//GEN-LAST:event_pnl1tuanLamViecMouseEntered

    private void pnl1tuanLamViecMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl1tuanLamViecMouseExited
        pnl1tuanLamViec.setBackground(Color.white);
    }//GEN-LAST:event_pnl1tuanLamViecMouseExited

    private void panelRadius6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRadius6MouseEntered
        panelRadius6.setBackground(new Color(87, 190, 110));
    }//GEN-LAST:event_panelRadius6MouseEntered

    private void panelRadius6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRadius6MouseExited
        panelRadius6.setBackground(Color.white);
    }//GEN-LAST:event_panelRadius6MouseExited

    private void panelRadius7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRadius7MouseEntered
        panelRadius7.setBackground(new Color(87, 190, 110));
    }//GEN-LAST:event_panelRadius7MouseEntered

    private void panelRadius7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRadius7MouseExited
        panelRadius7.setBackground(Color.WHITE);
    }//GEN-LAST:event_panelRadius7MouseExited

    private void panelRadius8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRadius8MouseEntered
        panelRadius8.setBackground(new Color(87, 190, 110));
    }//GEN-LAST:event_panelRadius8MouseEntered

    private void panelRadius8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRadius8MouseExited
        panelRadius8.setBackground(Color.WHITE);
    }//GEN-LAST:event_panelRadius8MouseExited

    private void pnl1tuanLamViecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnl1tuanLamViecMouseClicked
        try {
            Sach sach = DAOS.findById("SH001");
            DisplayReadPanel();
            openpdf("..\\DuAn01_G3_ebooks\\src\\com\\ebooks\\pdf\\" + sach.getDuongDan());
            materialTabbed3.setSelectedIndex(0);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_pnl1tuanLamViecMouseClicked

    private void panelRadius6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRadius6MouseClicked
        try {
            Sach sach = DAOS.findById("CTPL001");
            DisplayReadPanel();
            openpdf("..\\DuAn01_G3_ebooks\\src\\com\\ebooks\\pdf\\" + sach.getDuongDan());
            materialTabbed3.setSelectedIndex(0);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_panelRadius6MouseClicked

    private void panelRadius7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRadius7MouseClicked
        try {
            Sach sach = DAOS.findById("TLH001");
            DisplayReadPanel();
            openpdf("..\\DuAn01_G3_ebooks\\src\\com\\ebooks\\pdf\\" + sach.getDuongDan());
            materialTabbed3.setSelectedIndex(0);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_panelRadius7MouseClicked

    private void panelRadius8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRadius8MouseClicked
        try {
            Sach sach = DAOS.findById("KHCN0011");
            DisplayReadPanel();
            openpdf("..\\DuAn01_G3_ebooks\\src\\com\\ebooks\\pdf\\" + sach.getDuongDan());
            materialTabbed3.setSelectedIndex(0);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_panelRadius8MouseClicked

    private void tblBangGiaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangGiaMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblBangGiaMousePressed

    private void btnFirstBangGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstBangGiaActionPerformed
        // TODO add your handling code here:
        int index = tblBangGia.getSelectedRow();
        UtilityHelper.first(index, tblBangGia);
    }//GEN-LAST:event_btnFirstBangGiaActionPerformed

    private void btnPreBangGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreBangGiaActionPerformed
        // TODO add your handling code here:
        int index = tblBangGia.getSelectedRow();
        UtilityHelper.previous(index, tblBangGia, listGT);
    }//GEN-LAST:event_btnPreBangGiaActionPerformed

    private void btnNextBangGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextBangGiaActionPerformed
        // TODO add your handling code here:
        int index = tblBangGia.getSelectedRow();
        UtilityHelper.next(index, tblBangGia, listGT);
    }//GEN-LAST:event_btnNextBangGiaActionPerformed

    private void btnLastBangGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastBangGiaActionPerformed
        // TODO add your handling code here:
        int index = tblBangGia.getSelectedRow();
        UtilityHelper.last(index, tblBangGia, listGT);
    }//GEN-LAST:event_btnLastBangGiaActionPerformed

    private void btnThemBangGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemBangGiaActionPerformed
        // TODO add your handling code here:
        new TableRentCostDialog(this, true).setVisible(true);
        fillTableBangGiaThue();
    }//GEN-LAST:event_btnThemBangGiaActionPerformed

    private void btnSuaBangGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaBangGiaActionPerformed
        // TODO add your handling code here:
        int index = tblBangGia.getSelectedRow();
        if (index < 0) {
            DialogHelper.alert(this, "Chưa chọn bảng giá cần chỉnh sửa!");
        } else {
            String maGiaThue = (String) tblBangGia.getValueAt(index, 0);
            bangGiaThue = DAOBGT.findById(maGiaThue);
            new TableRentCostDialog(this, true, bangGiaThue).setVisible(true);
            fillTableBangGiaThue();
        }
        
    }//GEN-LAST:event_btnSuaBangGiaActionPerformed

    private void btnXoaBangGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaBangGiaActionPerformed
        // TODO add your handling code here:
        
        int index = tblBangGia.getSelectedRow();
        if (index < 0) {
            DialogHelper.alert(this, "Chưa chọn bảng giá cần xóa!");
        } else if (DialogHelper.confirm(this, "Bạn thật sự muốn xóa bảng giá này?")) {
            DAOBGT.delete(tblBangGia.getValueAt(index, 0).toString());
            this.fillTableBangGiaThue();
        }
    }//GEN-LAST:event_btnXoaBangGiaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ebooks.Compoment.MyButton bntFistDTTU;
    private com.ebooks.Compoment.MyButton btnBell;
    private com.ebooks.Compoment.MyButton btnCapNhatSach;
    private com.ebooks.Compoment.MyButton btnFirstAudio;
    private com.ebooks.Compoment.MyButton btnFirstBangGia;
    private com.ebooks.Compoment.MyButton btnFirstDTTS;
    private com.ebooks.Compoment.MyButton btnFirstLoaiSach;
    private com.ebooks.Compoment.MyButton btnFirstNguoiDung;
    private com.ebooks.Compoment.MyButton btnFirstSach;
    private com.ebooks.Compoment.MyButton btnFirstTacGia;
    private com.ebooks.Compoment.MyButton btnFirstTaiKhoan;
    private com.ebooks.Compoment.MyButton btnFirstThucUong;
    private com.ebooks.Compoment.MyButton btnLastAudio;
    private com.ebooks.Compoment.MyButton btnLastBangGia;
    private com.ebooks.Compoment.MyButton btnLastDTTS;
    private com.ebooks.Compoment.MyButton btnLastDTTU;
    private com.ebooks.Compoment.MyButton btnLastLoaiSach;
    private com.ebooks.Compoment.MyButton btnLastNguoiDung;
    private com.ebooks.Compoment.MyButton btnLastSach;
    private com.ebooks.Compoment.MyButton btnLastTacGia;
    private com.ebooks.Compoment.MyButton btnLastTaiKhoan;
    private com.ebooks.Compoment.MyButton btnLastThucUong;
    private com.ebooks.Compoment.MyButton btnListen;
    private com.ebooks.Compoment.MyButton btnMain;
    private com.ebooks.Compoment.MyButton btnManage;
    private com.ebooks.Compoment.MyButton btnNextAudio;
    private com.ebooks.Compoment.MyButton btnNextBangGia;
    private com.ebooks.Compoment.MyButton btnNextDTTS;
    private com.ebooks.Compoment.MyButton btnNextDTTU;
    private com.ebooks.Compoment.MyButton btnNextLoaiSach;
    private com.ebooks.Compoment.MyButton btnNextNguoiDung;
    private com.ebooks.Compoment.MyButton btnNextSach;
    private com.ebooks.Compoment.MyButton btnNextTacGia;
    private com.ebooks.Compoment.MyButton btnNextTaiKhoan;
    private com.ebooks.Compoment.MyButton btnNextThucUong;
    private com.ebooks.Compoment.MyButton btnPlay;
    private com.ebooks.Compoment.MyButton btnPreBangGia;
    private com.ebooks.Compoment.MyButton btnPreDTTS;
    private com.ebooks.Compoment.MyButton btnPreDTTU;
    private com.ebooks.Compoment.MyButton btnPreThucUong;
    private com.ebooks.Compoment.MyButton btnPrevAudio;
    private com.ebooks.Compoment.MyButton btnPrevLoaiSach;
    private com.ebooks.Compoment.MyButton btnPrevNguoiDung;
    private com.ebooks.Compoment.MyButton btnPrevSach;
    private com.ebooks.Compoment.MyButton btnPrevTacGia;
    private com.ebooks.Compoment.MyButton btnPrevTaiKhoan;
    private com.ebooks.Compoment.MyButton btnRead;
    private com.ebooks.Compoment.MyButton btnSapXepSach;
    private com.ebooks.Compoment.MyButton btnSapXepTheoTen;
    private com.ebooks.Compoment.MyButton btnSetting;
    private com.ebooks.Compoment.MyButton btnStatistical;
    private com.ebooks.Compoment.MyButton btnSuaAudio;
    private com.ebooks.Compoment.MyButton btnSuaBangGia;
    private com.ebooks.Compoment.MyButton btnSuaLoaiSach;
    private com.ebooks.Compoment.MyButton btnSuaNguoiDung;
    private com.ebooks.Compoment.MyButton btnSuaTaiKhoan;
    private com.ebooks.Compoment.MyButton btnSuaThongTin;
    private com.ebooks.Compoment.MyButton btnSuaThongTinHoaDon;
    private com.ebooks.Compoment.MyButton btnSuaThongTinThucUong;
    private com.ebooks.Compoment.MyButton btnThemAudio;
    private com.ebooks.Compoment.MyButton btnThemBangGia;
    private com.ebooks.Compoment.MyButton btnThemHoaDon;
    private com.ebooks.Compoment.MyButton btnThemLoaiSach;
    private com.ebooks.Compoment.MyButton btnThemNguoiDung;
    private com.ebooks.Compoment.MyButton btnThemSach;
    private com.ebooks.Compoment.MyButton btnThemTacGia;
    private com.ebooks.Compoment.MyButton btnThemTaiKhoan;
    private com.ebooks.Compoment.MyButton btnThucUong;
    private com.ebooks.Compoment.MyButton btnXoaAudio;
    private com.ebooks.Compoment.MyButton btnXoaBangGia;
    private com.ebooks.Compoment.MyButton btnXoaLoaiSach;
    private com.ebooks.Compoment.MyButton btnXoaNguoiDung;
    private com.ebooks.Compoment.MyButton btnXoaSach;
    private com.ebooks.Compoment.MyButton btnXoaTacGia;
    private com.ebooks.Compoment.MyButton btnXoaTaiKhoan;
    private com.ebooks.Compoment.MyButton btnXoaThongTinHoaDon;
    private com.ebooks.Compoment.MyButton btnXoaThucUong;
    private javax.swing.JComboBox<String> cboNamTS;
    private javax.swing.JComboBox<String> cboNamTU;
    private javax.swing.JComboBox<String> cboTheLoai;
    private com.ebooks.Compoment.ImageAvatar imageAvatar1;
    private com.ebooks.Compoment.ImageBoder imageBoder1;
    private com.ebooks.Compoment.ImageBoder imageBoder2;
    private com.ebooks.Compoment.ImageBoder imageBoder3;
    private com.ebooks.Compoment.ImageBoder imageBoder4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblAnhAudio;
    private javax.swing.JLabel lblDay;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblOff;
    private javax.swing.JLabel lblTenAudio;
    private javax.swing.JLabel lblTenNguoiThu;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTimeFirst;
    private javax.swing.JLabel lblTimeLast;
    private javax.swing.JLabel lblVolumeDown;
    private javax.swing.JLabel lblVolumeFull;
    private javax.swing.JLabel lblVolumeRepeat;
    private javax.swing.JLabel lblVolumeUp;
    private com.ebooks.Compoment.MaterialTabbed materialTabbed3;
    private com.ebooks.Compoment.PanelRadius panelRadius1;
    private com.ebooks.Compoment.PanelRadius panelRadius10;
    private com.ebooks.Compoment.PanelRadius panelRadius11;
    private com.ebooks.Compoment.PanelRadius panelRadius12;
    private com.ebooks.Compoment.PanelRadius panelRadius13;
    private com.ebooks.Compoment.PanelRadius panelRadius14;
    private com.ebooks.Compoment.PanelRadius panelRadius15;
    private com.ebooks.Compoment.PanelRadius panelRadius16;
    private com.ebooks.Compoment.PanelRadius panelRadius17;
    private com.ebooks.Compoment.PanelRadius panelRadius18;
    private com.ebooks.Compoment.PanelRadius panelRadius2;
    private com.ebooks.Compoment.PanelRadius panelRadius20;
    private com.ebooks.Compoment.PanelRadius panelRadius25;
    private com.ebooks.Compoment.PanelRadius panelRadius27;
    private com.ebooks.Compoment.PanelRadius panelRadius30;
    private com.ebooks.Compoment.PanelRadius panelRadius31;
    private com.ebooks.Compoment.PanelRadius panelRadius4;
    private com.ebooks.Compoment.PanelRadius panelRadius5;
    private com.ebooks.Compoment.PanelRadius panelRadius6;
    private com.ebooks.Compoment.PanelRadius panelRadius7;
    private com.ebooks.Compoment.PanelRadius panelRadius8;
    private com.ebooks.Compoment.PanelRadius panelRadius9;
    private com.ebooks.Compoment.PanelRadius pnl1tuanLamViec;
    private javax.swing.JPanel pnlBossMain;
    private com.ebooks.Compoment.PanelRadius pnlFrameListen;
    private com.ebooks.Compoment.PanelRadius pnlFrameMain;
    private com.ebooks.Compoment.PanelRadius pnlFrameManage;
    private com.ebooks.Compoment.PanelRadius pnlFrameRead;
    private com.ebooks.Compoment.PanelRadius pnlFrameStatistical;
    private com.ebooks.Compoment.PanelRadius pnlHoaDon;
    private com.ebooks.Compoment.PanelRadius pnlListSachDoc;
    private com.ebooks.Compoment.PanelRadius pnlListen;
    private com.ebooks.Compoment.PanelRadius pnlMain;
    private com.ebooks.Compoment.PanelBorder pnlMainProjebt;
    private com.ebooks.Compoment.PanelRadius pnlManage;
    private com.ebooks.Compoment.PanelRadius pnlMenu;
    private com.ebooks.Compoment.PanelRadius pnlMenuBooks;
    private com.ebooks.Compoment.PanelRadius pnlOff;
    private com.ebooks.Compoment.PanelRadius pnlRead;
    private com.ebooks.Compoment.PanelRadius pnlReader;
    private com.ebooks.Compoment.PanelRadius pnlSetting;
    private com.ebooks.Compoment.PanelRadius pnlStatistical;
    private com.ebooks.Compoment.PanelRadius pnlThucUong;
    private javax.swing.JScrollPane scrollpanePDF;
    private com.ebooks.Compoment.Slider sliderAudio;
    private com.ebooks.Compoment.MaterialTabbed tabDoanhThu;
    private com.ebooks.Compoment.MaterialTabbed tabQuanLy;
    private com.ebooks.Compoment.MaterialTabbed tabThongKe;
    private com.ebooks.Compoment.Table tblAudio;
    private com.ebooks.Compoment.Table tblAudioQL;
    private com.ebooks.Compoment.Table tblBangGia;
    private com.ebooks.Compoment.Table tblDanhSachCacSach;
    private com.ebooks.Compoment.Table tblDoanhThuThucUong;
    private com.ebooks.Compoment.Table tblDoanhThuThueSach;
    private com.ebooks.Compoment.Table tblHoaDon;
    private com.ebooks.Compoment.Table tblListSachDoc;
    private com.ebooks.Compoment.Table tblNguoiDung;
    private com.ebooks.Compoment.Table tblSach;
    private com.ebooks.Compoment.Table tblTKDTThucUong;
    private com.ebooks.Compoment.Table tblTKDTThueSach;
    private com.ebooks.Compoment.Table tblTacGia;
    private com.ebooks.Compoment.Table tblTaiKhoan;
    private com.ebooks.Compoment.Table tblTheLoai;
    private com.ebooks.Compoment.Table tblThucUong;
    private com.ebooks.Compoment.SearchText txtSearch;
    // End of variables declaration//GEN-END:variables

    public void OpenSetting() {
        new SettingDiaLog(this, true).setVisible(true);

    }

    public void OpenPerson() {
        new AccountDiaLog(this, true).setVisible(true);

    }

    public void fillComBoBoxTheLoai(JComboBox cbo) {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbo.getModel();
        model.removeAllElements();
        List<TheLoai> list = DAOTL.selectAll();
        for (TheLoai tl : list) {
            model.addElement(tl.getTenTheLoai());
        }
    }

    public void fillComBoBoxNamTU() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNamTU.getModel();
        model.removeAllElements();
        List<HoaDonThucUong> list = DaoHDTU.selectAll();
        List<Integer> listNam = new ArrayList();
        for (HoaDonThucUong hdtu : list) {
            SimpleDateFormat formats = new SimpleDateFormat("dd/MM/yyyy");
            String ngay = formats.format(hdtu.getNgayMua());
            String NamMua = ngay.substring(ngay.lastIndexOf("/") + 1);
            listNam.add(Integer.parseInt(NamMua));
        }
        List<Integer> listX = fillDuplicate(listNam);
        List<Integer> listY = fillDuplicate(listX);
        List<Integer> listZ = fillDuplicate(listY);
        for (int i = 0; i < listZ.size(); i++) {
            model.addElement(listZ.get(i));
        }
    }

    public void fillComBoBoxNamTS() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNamTS.getModel();
        model.removeAllElements();
        List<HoaDonThueSach> list = DaoHDTS.selectAll();
        List<Integer> listNam = new ArrayList();
        for (HoaDonThueSach hdts : list) {
            SimpleDateFormat formats = new SimpleDateFormat("dd/MM/yyyy");
            String ngay = formats.format(hdts.getNgayThue());
            String NamThue = ngay.substring(ngay.lastIndexOf("/") + 1);
            listNam.add(Integer.parseInt(NamThue));
        }
        List<Integer> listX = fillDuplicate(listNam);
        for (int i = 0; i < listX.size(); i++) {
            model.addElement(listX.get(i));
        }

    }

    public void LoadNguoiDung() {

        listND = DaoND.selectAll();
    }

    public void LoadTaiKhoan() {
        listTK = DaoTK.selectAll();
    }

    // HÀM DELETE TONG QUAT //
    //Read File FDF
    public void openpdf(String file) {

        try {
            SwingController control = new SwingController();
            SwingViewBuilder factry = new SwingViewBuilder(control);
            JPanel veiwerCompntpnl = factry.buildViewerPanel();
            ComponentKeyBinding.install(control, veiwerCompntpnl);
            control.getDocumentViewController().setAnnotationCallback(
                    new org.icepdf.ri.common.MyAnnotationCallback(
                            control.getDocumentViewController()));
            control.openDocument(file);
            scrollpanePDF.setViewportView(veiwerCompntpnl);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Cannot Load Pdf");
        }
        System.out.println(file);
    }

    public void fillTableListSachDoc() {
        DefaultTableModel model;
        model = (DefaultTableModel) tblListSachDoc.getModel();
        tblListSachDoc.setSelectionBackground(new Color(87, 190, 110));

        model.setRowCount(0);
        try {
            listS = DAOS.selectAll();
            for (Sach sach : listS) {
                Object[] row = {sach.getMaSach(), sach.getTenSach(), DAOTG.findById(sach.getMaTacGia()).getHoTen(), sach.getNgayDang(), sach.getMoTa()};
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }
    public void fillTableBangGiaThue() {
        DefaultTableModel model;
        model = new DefaultTableModel();
        model.setRowCount(0);
        //set COLUMN
        model.addColumn("Mã Giá Thuê");
        model.addColumn("Thời Lượng");
        model.addColumn("Đơn Giá");

        listGT = DAOBGT.selectAll();
        //tạo hàng và do du lieu
        for (BangGiaThue tu : listGT) {
            Object[] row = {tu.getMaGiaThue(), tu.getThoiLuong(), tu.getDonGiaThue()};
            model.addRow(row);
            tblBangGia.setModel(model);
        }
    }
}
