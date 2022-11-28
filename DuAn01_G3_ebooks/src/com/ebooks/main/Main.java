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
import jaco.mp3.player.MP3Player;

import com.ebooks.dao.HoaDonThucUongDAO;
import com.ebooks.dao.LoaiSSDAO;
import com.ebooks.dao.NguoiDungDAO;
import com.ebooks.dao.SachDAO;
import com.ebooks.dao.TacGiaDAO;
import com.ebooks.dao.TaiKhoanDAO;
import com.ebooks.dao.TheLoaiDAO;
import com.ebooks.dao.ThucUongDAO;
import com.ebooks.helper.DialogHelper;
import com.ebooks.helper.ShareHelper;
import com.ebooks.helper.UtilityHelper;
import com.ebooks.model.HoaDonThucUong;
import com.ebooks.model.LoaiSS;
import com.ebooks.model.NguoiDung;
import com.ebooks.model.Sach;
import com.ebooks.model.TaiKhoan;
import com.ebooks.model.TheLoai;
import com.ebooks.model.ThucUong;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class Main extends javax.swing.JFrame {

    static String MaND;
    static String TenDangNhap;
    public static DefaultTableModel tblTable;

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
    boolean congTac = true;

    DefaultTableModel model;
    AudioSachDAO daoAudio = new AudioSachDAO();
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
    // Here I am making a boolean for windowCollapsed
    boolean windowCollapsed = false;

    public static List<NguoiDung> listND;
    public static List<TaiKhoan> listTK;

    //model DAO
    TaiKhoanDAO DaoTK = new TaiKhoanDAO();
    NguoiDungDAO DaoND = new NguoiDungDAO();
    ThucUongDAO DaoTU = new ThucUongDAO();
    HoaDonThucUongDAO DaoHD = new HoaDonThucUongDAO();
    SachDAO DAOS = new SachDAO();
    TacGiaDAO DAOTG = new TacGiaDAO();
    TheLoaiDAO DAOTL = new TheLoaiDAO();
    LoaiSSDAO DAOLSS = new LoaiSSDAO();
    //model 
    NguoiDung nguoiDung = new NguoiDung();
    ThucUong thucUong = new ThucUong();
    HoaDonThucUong hoaDonThucUong = new HoaDonThucUong();
    Sach sach = new Sach();
    //List
//    List<NguoiDung> listND = new ArrayList<>();
    List<ThucUong> listTU = new ArrayList<>();
    List<HoaDonThucUong> listHD = new ArrayList<>();
    List<Sach> listS = new ArrayList<>();
    private List<TheLoai> listTL = new ArrayList<>();
    int index = -1;

    public Main() {
        initComponents();
        init();
        movedpnlMenu();
        setBackground(new Color(0, 0, 0, 0));
        Date();
        AppStatus.mainApp = this;
        initMoving(this, pnlMainProjebt);
        setModelAudio();
        fillTableAudio(tblAudio);
        fillTableAudio(tblAudioQL);
        fillComBoBoxTheLoai(cboTheLoaiSach);
        fillTableSach();
        fillTableNguoiDung();
        fillTableThucUong();
    }

    public void init() {
        //Set icon, show from
        setIconImage(ShareHelper.APP_ICON);
        new StartUpDiaLog(this, true).setVisible(true);
        new LogInDiaLog(this, true).setVisible(true);
        LoadTaiKhoan();
        fillTableTaiKhoan(listTK);

        LoadNguoiDung();
        fillTableNguoiDung(listND);
    }

    //AudioSach
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

    public void fillTableAudio(Table tbl) {
        model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        tbl.setSelectionBackground(new Color(87, 190, 110));
        try {
            listAudio = daoAudio.selectAll();
            for (AudioSach au : listAudio) {
                model.addRow(new Object[]{au.getMaAudio(), au.getTenAudio(), au.getNgayPhatHanh(), au.getNguoiThu()});
            }
        } catch (Exception e) {

        }
    }
    // I am going to create a custom MP3Player Method

    private MP3Player mp3Player() {
        MP3Player mp3Player = new MP3Player();
        return mp3Player;
    }

    public void movedpnlMenu() {
        if (ShareHelper.isManager()) {
            pnlManage.setVisible(true);
            pnlStatistical.setVisible(true);
            pnlRead.setLocation(15, 260);
            pnlListen.setLocation(15, 340);

        } else {
            pnlManage.setVisible(false);
            pnlStatistical.setVisible(false);
            pnlRead.setLocation(new Point(15, 100));
            pnlListen.setLocation(new Point(15, 180));

        }
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

    public void SetColorPanel(JPanel panel, boolean bl) {
        if (bl) {
            panel.setBackground(new Color(145, 227, 168));
        } else {
            panel.setBackground(new Color(205, 239, 215));

        }
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
        panelRadius3 = new com.ebooks.Compoment.PanelRadius();
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
        table2 = new com.ebooks.Compoment.Table();
        panelRadius4 = new com.ebooks.Compoment.PanelRadius();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        pnlFrameManage = new com.ebooks.Compoment.PanelRadius();
        jLabel17 = new javax.swing.JLabel();
        materialTabbed1 = new com.ebooks.Compoment.MaterialTabbed();
        panelRadius9 = new com.ebooks.Compoment.PanelRadius();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLoaiSach = new com.ebooks.Compoment.Table();
        myButton3 = new com.ebooks.Compoment.MyButton();
        myButton4 = new com.ebooks.Compoment.MyButton();
        myButton5 = new com.ebooks.Compoment.MyButton();
        myButton6 = new com.ebooks.Compoment.MyButton();
        myButton8 = new com.ebooks.Compoment.MyButton();
        myButton9 = new com.ebooks.Compoment.MyButton();
        myButton10 = new com.ebooks.Compoment.MyButton();
        panelRadius10 = new com.ebooks.Compoment.PanelRadius();
        panelRadius17 = new com.ebooks.Compoment.PanelRadius();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSach = new com.ebooks.Compoment.Table();
        myButton18 = new com.ebooks.Compoment.MyButton();
        cboTheLoaiSach = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        btnThemSach = new com.ebooks.Compoment.MyButton();
        btnCapNhatSach = new com.ebooks.Compoment.MyButton();
        btnXoaSach = new com.ebooks.Compoment.MyButton();
        btnFirstSach = new com.ebooks.Compoment.MyButton();
        btnNextSach = new com.ebooks.Compoment.MyButton();
        btnPrevSach = new com.ebooks.Compoment.MyButton();
        btnLastSach = new com.ebooks.Compoment.MyButton();
        panelRadius11 = new com.ebooks.Compoment.PanelRadius();
        panelRadius31 = new com.ebooks.Compoment.PanelRadius();
        jScrollPane14 = new javax.swing.JScrollPane();
        tblAudioQL = new com.ebooks.Compoment.Table();
        myButton78 = new com.ebooks.Compoment.MyButton();
        btnThemAudio = new com.ebooks.Compoment.MyButton();
        btnSuaAudio = new com.ebooks.Compoment.MyButton();
        btnXoaAudio = new com.ebooks.Compoment.MyButton();
        btnFirstAudio = new com.ebooks.Compoment.MyButton();
        btnPrevAudio = new com.ebooks.Compoment.MyButton();
        btnNextAudio = new com.ebooks.Compoment.MyButton();
        btnLastAudio = new com.ebooks.Compoment.MyButton();
        panelRadius12 = new com.ebooks.Compoment.PanelRadius();
        jScrollPane6 = new javax.swing.JScrollPane();
        table6 = new com.ebooks.Compoment.Table();
        myButton86 = new com.ebooks.Compoment.MyButton();
        myButton87 = new com.ebooks.Compoment.MyButton();
        myButton88 = new com.ebooks.Compoment.MyButton();
        myButton89 = new com.ebooks.Compoment.MyButton();
        myButton90 = new com.ebooks.Compoment.MyButton();
        myButton91 = new com.ebooks.Compoment.MyButton();
        myButton92 = new com.ebooks.Compoment.MyButton();
        panelRadius13 = new com.ebooks.Compoment.PanelRadius();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblTaiKhoan = new com.ebooks.Compoment.Table();
        btnSuaTaiKhoan = new com.ebooks.Compoment.MyButton();
        btnXoaTaiKhoan = new com.ebooks.Compoment.MyButton();
        btnThemTaiKhoan = new com.ebooks.Compoment.MyButton();
        myButton93 = new com.ebooks.Compoment.MyButton();
        myButton94 = new com.ebooks.Compoment.MyButton();
        myButton95 = new com.ebooks.Compoment.MyButton();
        myButton96 = new com.ebooks.Compoment.MyButton();
        panelRadius14 = new com.ebooks.Compoment.PanelRadius();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblNguoiDung = new com.ebooks.Compoment.Table();
        btnXoaNguoiDung = new com.ebooks.Compoment.MyButton();
        btnSuaNguoiDung = new com.ebooks.Compoment.MyButton();
        btnThemNguoiDung = new com.ebooks.Compoment.MyButton();
        myButton104 = new com.ebooks.Compoment.MyButton();
        myButton103 = new com.ebooks.Compoment.MyButton();
        myButton102 = new com.ebooks.Compoment.MyButton();
        myButton101 = new com.ebooks.Compoment.MyButton();
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
        jScrollPane10 = new javax.swing.JScrollPane();
        tblHoaDon = new com.ebooks.Compoment.Table();
        btnLastHoaDon = new com.ebooks.Compoment.MyButton();
        btnNextHoaDon = new com.ebooks.Compoment.MyButton();
        btnPreHoaDon = new com.ebooks.Compoment.MyButton();
        btnFirstHoaDon = new com.ebooks.Compoment.MyButton();
        btnThemHoaDon = new com.ebooks.Compoment.MyButton();
        btnSuaThongTinHoaDon = new com.ebooks.Compoment.MyButton();
        btnXoaThongTinHoaDon = new com.ebooks.Compoment.MyButton();
        pnlFrameStatistical = new com.ebooks.Compoment.PanelRadius();
        jLabel20 = new javax.swing.JLabel();
        materialTabbed2 = new com.ebooks.Compoment.MaterialTabbed();
        panelRadius18 = new com.ebooks.Compoment.PanelRadius();
        jScrollPane4 = new javax.swing.JScrollPane();
        table4 = new com.ebooks.Compoment.Table();
        panelRadius27 = new com.ebooks.Compoment.PanelRadius();
        jLabel25 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<>();
        myButton126 = new com.ebooks.Compoment.MyButton();
        myButton127 = new com.ebooks.Compoment.MyButton();
        myButton128 = new com.ebooks.Compoment.MyButton();
        myButton129 = new com.ebooks.Compoment.MyButton();
        panelRadius20 = new com.ebooks.Compoment.PanelRadius();
        jScrollPane11 = new javax.swing.JScrollPane();
        table11 = new com.ebooks.Compoment.Table();
        panelRadius25 = new com.ebooks.Compoment.PanelRadius();
        jLabel22 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        myButton122 = new com.ebooks.Compoment.MyButton();
        myButton123 = new com.ebooks.Compoment.MyButton();
        myButton124 = new com.ebooks.Compoment.MyButton();
        myButton125 = new com.ebooks.Compoment.MyButton();
        panelRadius21 = new com.ebooks.Compoment.PanelRadius();
        panelRadius24 = new com.ebooks.Compoment.PanelRadius();
        jLabel3 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jScrollPane12 = new javax.swing.JScrollPane();
        table12 = new com.ebooks.Compoment.Table();
        myButton71 = new com.ebooks.Compoment.MyButton();
        myButton72 = new com.ebooks.Compoment.MyButton();
        myButton73 = new com.ebooks.Compoment.MyButton();
        myButton64 = new com.ebooks.Compoment.MyButton();
        panelRadius28 = new com.ebooks.Compoment.PanelRadius();
        jLabel23 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        panelRadius29 = new com.ebooks.Compoment.PanelRadius();
        jLabel24 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        panelRadius26 = new com.ebooks.Compoment.PanelRadius();
        jLabel21 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        pnlFrameRead = new com.ebooks.Compoment.PanelRadius();
        materialTabbed3 = new com.ebooks.Compoment.MaterialTabbed();
        panelRadius22 = new com.ebooks.Compoment.PanelRadius();
        panelRadius23 = new com.ebooks.Compoment.PanelRadius();
        jScrollPane5 = new javax.swing.JScrollPane();
        table5 = new com.ebooks.Compoment.Table();
        jLabel19 = new javax.swing.JLabel();
        pnlFrameListen = new com.ebooks.Compoment.PanelRadius();
        imageBoder5 = new com.ebooks.Compoment.ImageBoder();
        panelRadius30 = new com.ebooks.Compoment.PanelRadius();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblAudio = new com.ebooks.Compoment.Table();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        myButton7 = new com.ebooks.Compoment.MyButton();
        btnPlay = new com.ebooks.Compoment.MyButton();
        myButton14 = new com.ebooks.Compoment.MyButton();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        slider1 = new com.ebooks.Compoment.Slider();
        lblTenAudio = new javax.swing.JLabel();
        panelRadius2 = new com.ebooks.Compoment.PanelRadius();
        jLabel1 = new javax.swing.JLabel();
        searchText1 = new com.ebooks.Compoment.SearchText();
        imageAvatar1 = new com.ebooks.Compoment.ImageAvatar();
        pnlSetting = new com.ebooks.Compoment.PanelRadius();
        btnSetting = new com.ebooks.Compoment.MyButton();
        pnlBell = new com.ebooks.Compoment.PanelRadius();
        btnBell = new com.ebooks.Compoment.MyButton();
        pnlOff = new com.ebooks.Compoment.PanelRadius();
        lblOff = new javax.swing.JLabel();
        lblDay = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hệ Thống eBooks");
        setUndecorated(true);

        pnlMainProjebt.setBackground(new java.awt.Color(251, 251, 251));
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
        pnlMenuBooks.setRadius(25);
        pnlMenuBooks.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRadius3.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius3.setRadius(20);
        panelRadius3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imageBoder1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        imageBoder1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/imgEbooks/5PhuongThuc.png"))); // NOI18N
        panelRadius3.add(imageBoder1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 50, 70));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("5 Phương Thức Ghi Nhân Nổ Lục Của Nhân Viên");
        panelRadius3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 120, -1));

        jLabel6.setText("Gary D. Chapman");
        panelRadius3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 45, -1, -1));

        jLabel7.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        jLabel7.setText("248/248");
        panelRadius3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, -1, -1));

        pnlMenuBooks.add(panelRadius3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 290, 90));

        panelRadius6.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius6.setRadius(20);
        panelRadius6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imageBoder2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        imageBoder2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/imgEbooks/AnItDeKhoe.png"))); // NOI18N
        panelRadius6.add(imageBoder2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 50, 70));

        jLabel10.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        jLabel10.setText("200/200");
        panelRadius6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Ăn Ít Để Khỏe");
        panelRadius6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 120, -1));

        jLabel8.setText("Yoshinori Nagumo");
        panelRadius6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, -1));

        pnlMenuBooks.add(panelRadius6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 290, 90));

        panelRadius7.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius7.setRadius(20);
        panelRadius7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imageBoder3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/imgEbooks/BanChiSongCoMotLan.png"))); // NOI18N
        panelRadius7.add(imageBoder3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 50, 80));

        jLabel13.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        jLabel13.setText("128/128");
        panelRadius7.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Bạn Chỉ Sống Có Một Lần");
        panelRadius7.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 120, -1));

        jLabel11.setText("Nhã Nam ");
        panelRadius7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, -1, -1));

        pnlMenuBooks.add(panelRadius7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 290, 90));

        panelRadius8.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius8.setRadius(20);
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

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table2.setRadius(10);
        jScrollPane2.setViewportView(table2);

        pnlFrameMain.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 700, 260));

        panelRadius4.setBackground(new java.awt.Color(205, 239, 215));
        panelRadius4.setRadius(20);
        panelRadius4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Image/13.png"))); // NOI18N
        panelRadius4.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, 260, 140));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Image/181-removebg-preview.png"))); // NOI18N
        panelRadius4.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, -1, -1));

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

        materialTabbed1.setClorSroll(new java.awt.Color(87, 190, 110));
        materialTabbed1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        panelRadius9.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblLoaiSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblLoaiSach);

        panelRadius9.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 840, 350));

        myButton3.setBackground(new java.awt.Color(145, 227, 168));
        myButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-right.png"))); // NOI18N
        myButton3.setBoderColor(new java.awt.Color(145, 227, 168));
        myButton3.setRadius(10);
        panelRadius9.add(myButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 380, 60, 40));

        myButton4.setBackground(new java.awt.Color(145, 227, 168));
        myButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-left.png"))); // NOI18N
        myButton4.setBoderColor(new java.awt.Color(145, 227, 168));
        myButton4.setRadius(10);
        panelRadius9.add(myButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 60, 40));

        myButton5.setBackground(new java.awt.Color(145, 227, 168));
        myButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-left.png"))); // NOI18N
        myButton5.setBoderColor(new java.awt.Color(145, 227, 168));
        myButton5.setRadius(10);
        panelRadius9.add(myButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 60, 40));

        myButton6.setBackground(new java.awt.Color(145, 227, 168));
        myButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-right.png"))); // NOI18N
        myButton6.setBoderColor(new java.awt.Color(145, 227, 168));
        myButton6.setRadius(10);
        panelRadius9.add(myButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 60, 40));

        myButton8.setBackground(new java.awt.Color(87, 190, 110));
        myButton8.setForeground(new java.awt.Color(255, 255, 255));
        myButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/add-document.png"))); // NOI18N
        myButton8.setText("Thêm Loại Sách");
        myButton8.setBoderColor(new java.awt.Color(87, 190, 110));
        myButton8.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        myButton8.setRadius(10);
        myButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton8ActionPerformed(evt);
            }
        });
        panelRadius9.add(myButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 20, 150, 40));

        myButton9.setBackground(new java.awt.Color(87, 190, 110));
        myButton9.setForeground(new java.awt.Color(255, 255, 255));
        myButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/refresh (1).png"))); // NOI18N
        myButton9.setText("Sửa Thông Tin");
        myButton9.setBoderColor(new java.awt.Color(87, 190, 110));
        myButton9.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        myButton9.setRadius(10);
        panelRadius9.add(myButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 80, 150, 40));

        myButton10.setBackground(new java.awt.Color(253, 127, 127));
        myButton10.setForeground(new java.awt.Color(255, 255, 255));
        myButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/delete-document.png"))); // NOI18N
        myButton10.setText("Xóa Loại Sách");
        myButton10.setBoderColor(new java.awt.Color(253, 127, 127));
        myButton10.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        myButton10.setRadius(10);
        panelRadius9.add(myButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 140, 150, 40));

        materialTabbed1.addTab("Loại Sách", panelRadius9);

        panelRadius10.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        myButton18.setBackground(new java.awt.Color(87, 190, 110));
        myButton18.setForeground(new java.awt.Color(255, 255, 255));
        myButton18.setText("Sắp Xếp Theo Tên");
        myButton18.setBoderColor(new java.awt.Color(87, 190, 110));
        myButton18.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        myButton18.setRadius(10);
        myButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton18ActionPerformed(evt);
            }
        });
        panelRadius10.add(myButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 140, 40));

        cboTheLoaiSach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cboTheLoaiSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTheLoaiSachActionPerformed(evt);
            }
        });
        panelRadius10.add(cboTheLoaiSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 160, 40));

        jLabel18.setFont(new java.awt.Font("Inter SemiBold", 0, 14)); // NOI18N
        jLabel18.setText("Lọc Theo Thể Loại");
        panelRadius10.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, -1, -1));

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

        materialTabbed1.addTab("Sách", panelRadius10);

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

        myButton78.setBackground(new java.awt.Color(87, 190, 110));
        myButton78.setForeground(new java.awt.Color(255, 255, 255));
        myButton78.setText("Sắp Xếp Theo Tên");
        myButton78.setBoderColor(new java.awt.Color(87, 190, 110));
        myButton78.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        myButton78.setRadius(10);
        myButton78.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton78ActionPerformed(evt);
            }
        });
        panelRadius11.add(myButton78, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, 140, 40));

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

        materialTabbed1.addTab("Audio", panelRadius11);

        panelRadius12.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(table6);

        panelRadius12.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 840, 350));

        myButton86.setBackground(new java.awt.Color(145, 227, 168));
        myButton86.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-left.png"))); // NOI18N
        myButton86.setBoderColor(new java.awt.Color(145, 227, 168));
        myButton86.setRadius(10);
        panelRadius12.add(myButton86, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 60, 40));

        myButton87.setBackground(new java.awt.Color(145, 227, 168));
        myButton87.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-left.png"))); // NOI18N
        myButton87.setBoderColor(new java.awt.Color(145, 227, 168));
        myButton87.setRadius(10);
        panelRadius12.add(myButton87, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 60, 40));

        myButton88.setBackground(new java.awt.Color(145, 227, 168));
        myButton88.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-right.png"))); // NOI18N
        myButton88.setBoderColor(new java.awt.Color(145, 227, 168));
        myButton88.setRadius(10);
        panelRadius12.add(myButton88, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 60, 40));

        myButton89.setBackground(new java.awt.Color(145, 227, 168));
        myButton89.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-right.png"))); // NOI18N
        myButton89.setBoderColor(new java.awt.Color(145, 227, 168));
        myButton89.setRadius(10);
        panelRadius12.add(myButton89, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 380, 60, 40));

        myButton90.setBackground(new java.awt.Color(87, 190, 110));
        myButton90.setForeground(new java.awt.Color(255, 255, 255));
        myButton90.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/user-add.png"))); // NOI18N
        myButton90.setText("Thêm Tác Giả");
        myButton90.setBoderColor(new java.awt.Color(87, 190, 110));
        myButton90.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        myButton90.setRadius(10);
        panelRadius12.add(myButton90, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 20, 150, 40));

        myButton91.setBackground(new java.awt.Color(87, 190, 110));
        myButton91.setForeground(new java.awt.Color(255, 255, 255));
        myButton91.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/refresh (1).png"))); // NOI18N
        myButton91.setText("Sửa Thông Tin");
        myButton91.setBoderColor(new java.awt.Color(87, 190, 110));
        myButton91.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        myButton91.setRadius(10);
        panelRadius12.add(myButton91, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 80, 150, 40));

        myButton92.setBackground(new java.awt.Color(253, 127, 127));
        myButton92.setForeground(new java.awt.Color(255, 255, 255));
        myButton92.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/delete-user.png"))); // NOI18N
        myButton92.setText("Xóa Tác Giả");
        myButton92.setBoderColor(new java.awt.Color(253, 127, 127));
        myButton92.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        myButton92.setRadius(10);
        panelRadius12.add(myButton92, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 140, 150, 40));

        materialTabbed1.addTab("Tác Giả", panelRadius12);

        panelRadius13.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblTaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên đăng nhập", "Mật khẩu", "Mã người dùng", "Chức vụ", "Thời lượng đọc sách"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTaiKhoanMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblTaiKhoan);

        panelRadius13.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 840, 350));

        btnSuaTaiKhoan.setBackground(new java.awt.Color(87, 190, 110));
        btnSuaTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/refresh (1).png"))); // NOI18N
        btnSuaTaiKhoan.setText("Sửa Thông Tin");
        btnSuaTaiKhoan.setBoderColor(new java.awt.Color(87, 190, 110));
        btnSuaTaiKhoan.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnSuaTaiKhoan.setRadius(10);
        btnSuaTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaTaiKhoanMouseClicked(evt);
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

        myButton93.setBackground(new java.awt.Color(145, 227, 168));
        myButton93.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-right.png"))); // NOI18N
        myButton93.setBoderColor(new java.awt.Color(145, 227, 168));
        myButton93.setRadius(10);
        myButton93.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton93ActionPerformed(evt);
            }
        });
        panelRadius13.add(myButton93, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 380, 60, 40));

        myButton94.setBackground(new java.awt.Color(145, 227, 168));
        myButton94.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-right.png"))); // NOI18N
        myButton94.setBoderColor(new java.awt.Color(145, 227, 168));
        myButton94.setRadius(10);
        myButton94.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton94ActionPerformed(evt);
            }
        });
        panelRadius13.add(myButton94, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 60, 40));

        myButton95.setBackground(new java.awt.Color(145, 227, 168));
        myButton95.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-left.png"))); // NOI18N
        myButton95.setBoderColor(new java.awt.Color(145, 227, 168));
        myButton95.setRadius(10);
        myButton95.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton95ActionPerformed(evt);
            }
        });
        panelRadius13.add(myButton95, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 60, 40));

        myButton96.setBackground(new java.awt.Color(145, 227, 168));
        myButton96.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-left.png"))); // NOI18N
        myButton96.setBoderColor(new java.awt.Color(145, 227, 168));
        myButton96.setRadius(10);
        myButton96.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton96ActionPerformed(evt);
            }
        });
        panelRadius13.add(myButton96, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 60, 40));

        materialTabbed1.addTab("Tài Khoản", panelRadius13);

        panelRadius14.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblNguoiDung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã người dùng", "Họ tên", "Số điện thoại", "Giới tính", "Email", "Hình"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNguoiDung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblNguoiDungMousePressed(evt);
            }
        });
        jScrollPane8.setViewportView(tblNguoiDung);

        panelRadius14.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 840, 350));

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

        myButton104.setBackground(new java.awt.Color(145, 227, 168));
        myButton104.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-left.png"))); // NOI18N
        myButton104.setBoderColor(new java.awt.Color(145, 227, 168));
        myButton104.setRadius(10);
        myButton104.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton104ActionPerformed(evt);
            }
        });
        panelRadius14.add(myButton104, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 60, 40));

        myButton103.setBackground(new java.awt.Color(145, 227, 168));
        myButton103.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-left.png"))); // NOI18N
        myButton103.setBoderColor(new java.awt.Color(145, 227, 168));
        myButton103.setRadius(10);
        myButton103.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton103ActionPerformed(evt);
            }
        });
        panelRadius14.add(myButton103, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 60, 40));

        myButton102.setBackground(new java.awt.Color(145, 227, 168));
        myButton102.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-right.png"))); // NOI18N
        myButton102.setBoderColor(new java.awt.Color(145, 227, 168));
        myButton102.setRadius(10);
        myButton102.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton102ActionPerformed(evt);
            }
        });
        panelRadius14.add(myButton102, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 60, 40));

        myButton101.setBackground(new java.awt.Color(145, 227, 168));
        myButton101.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-right.png"))); // NOI18N
        myButton101.setBoderColor(new java.awt.Color(145, 227, 168));
        myButton101.setRadius(10);
        myButton101.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton101ActionPerformed(evt);
            }
        });
        panelRadius14.add(myButton101, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 380, 60, 40));

        materialTabbed1.addTab("Người Dùng", panelRadius14);

        pnlThucUong.setBackground(new java.awt.Color(255, 255, 255));
        pnlThucUong.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblThucUong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
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

        materialTabbed1.addTab("Thực Đơn", pnlThucUong);

        pnlHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        pnlHoaDon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane10.setViewportView(tblHoaDon);

        pnlHoaDon.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 840, 350));

        btnLastHoaDon.setBackground(new java.awt.Color(145, 227, 168));
        btnLastHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-right.png"))); // NOI18N
        btnLastHoaDon.setBoderColor(new java.awt.Color(145, 227, 168));
        btnLastHoaDon.setRadius(10);
        pnlHoaDon.add(btnLastHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 380, 60, 40));

        btnNextHoaDon.setBackground(new java.awt.Color(145, 227, 168));
        btnNextHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-right.png"))); // NOI18N
        btnNextHoaDon.setBoderColor(new java.awt.Color(145, 227, 168));
        btnNextHoaDon.setRadius(10);
        pnlHoaDon.add(btnNextHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 60, 40));

        btnPreHoaDon.setBackground(new java.awt.Color(145, 227, 168));
        btnPreHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-left.png"))); // NOI18N
        btnPreHoaDon.setBoderColor(new java.awt.Color(145, 227, 168));
        btnPreHoaDon.setRadius(10);
        pnlHoaDon.add(btnPreHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 60, 40));

        btnFirstHoaDon.setBackground(new java.awt.Color(145, 227, 168));
        btnFirstHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-left.png"))); // NOI18N
        btnFirstHoaDon.setBoderColor(new java.awt.Color(145, 227, 168));
        btnFirstHoaDon.setRadius(10);
        pnlHoaDon.add(btnFirstHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 60, 40));

        btnThemHoaDon.setBackground(new java.awt.Color(87, 190, 110));
        btnThemHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnThemHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/receipt (1).png"))); // NOI18N
        btnThemHoaDon.setText("Thêm Hóa Đơn");
        btnThemHoaDon.setBoderColor(new java.awt.Color(87, 190, 110));
        btnThemHoaDon.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnThemHoaDon.setRadius(10);
        pnlHoaDon.add(btnThemHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 20, 150, 40));

        btnSuaThongTinHoaDon.setBackground(new java.awt.Color(87, 190, 110));
        btnSuaThongTinHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaThongTinHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/refresh (1).png"))); // NOI18N
        btnSuaThongTinHoaDon.setText("Sửa Thông Tin");
        btnSuaThongTinHoaDon.setBoderColor(new java.awt.Color(87, 190, 110));
        btnSuaThongTinHoaDon.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnSuaThongTinHoaDon.setRadius(10);
        pnlHoaDon.add(btnSuaThongTinHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 80, 150, 40));

        btnXoaThongTinHoaDon.setBackground(new java.awt.Color(253, 127, 127));
        btnXoaThongTinHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaThongTinHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/circle-cross.png"))); // NOI18N
        btnXoaThongTinHoaDon.setText("Xóa Hóa Đơn");
        btnXoaThongTinHoaDon.setBoderColor(new java.awt.Color(253, 127, 127));
        btnXoaThongTinHoaDon.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N
        btnXoaThongTinHoaDon.setRadius(10);
        pnlHoaDon.add(btnXoaThongTinHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 140, 150, 40));

        materialTabbed1.addTab("Hóa Đơn", pnlHoaDon);

        pnlFrameManage.add(materialTabbed1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1060, 480));

        pnlBossMain.add(pnlFrameManage, "card6");

        pnlFrameStatistical.setBackground(new java.awt.Color(205, 239, 215));
        pnlFrameStatistical.setRadius(15);
        pnlFrameStatistical.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Open Sans", 1, 28)); // NOI18N
        jLabel20.setText("Thống Kê");
        pnlFrameStatistical.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        materialTabbed2.setClorSroll(new java.awt.Color(87, 190, 110));
        materialTabbed2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        panelRadius18.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(table4);

        panelRadius18.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 930, 360));

        panelRadius27.setBackground(new java.awt.Color(231, 249, 234));
        panelRadius27.setRadius(10);
        panelRadius27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setText("Theo Năm");
        panelRadius27.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 17, -1, -1));

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        panelRadius27.add(jComboBox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 160, 30));

        panelRadius18.add(panelRadius27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 250, 50));

        myButton126.setBackground(new java.awt.Color(145, 227, 168));
        myButton126.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-up.png"))); // NOI18N
        myButton126.setBoderColor(new java.awt.Color(145, 227, 168));
        myButton126.setRadius(10);
        panelRadius18.add(myButton126, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 70, 60, 40));

        myButton127.setBackground(new java.awt.Color(145, 227, 168));
        myButton127.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-up.png"))); // NOI18N
        myButton127.setBoderColor(new java.awt.Color(145, 227, 168));
        myButton127.setRadius(10);
        panelRadius18.add(myButton127, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 130, 60, 40));

        myButton128.setBackground(new java.awt.Color(145, 227, 168));
        myButton128.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-down.png"))); // NOI18N
        myButton128.setBoderColor(new java.awt.Color(145, 227, 168));
        myButton128.setRadius(10);
        panelRadius18.add(myButton128, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 190, 60, 40));

        myButton129.setBackground(new java.awt.Color(145, 227, 168));
        myButton129.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-down.png"))); // NOI18N
        myButton129.setBoderColor(new java.awt.Color(145, 227, 168));
        myButton129.setRadius(10);
        myButton129.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton129ActionPerformed(evt);
            }
        });
        panelRadius18.add(myButton129, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 250, 60, 40));

        materialTabbed2.addTab("Doanh Số", panelRadius18);

        panelRadius20.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table11.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane11.setViewportView(table11);

        panelRadius20.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 930, 360));

        panelRadius25.setBackground(new java.awt.Color(231, 249, 234));
        panelRadius25.setRadius(10);
        panelRadius25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setText("Theo Năm");
        panelRadius25.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 17, -1, -1));

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        panelRadius25.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 160, 30));

        panelRadius20.add(panelRadius25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 250, 50));

        myButton122.setBackground(new java.awt.Color(145, 227, 168));
        myButton122.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-up.png"))); // NOI18N
        myButton122.setBoderColor(new java.awt.Color(145, 227, 168));
        myButton122.setRadius(10);
        panelRadius20.add(myButton122, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 70, 60, 40));

        myButton123.setBackground(new java.awt.Color(145, 227, 168));
        myButton123.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-up.png"))); // NOI18N
        myButton123.setBoderColor(new java.awt.Color(145, 227, 168));
        myButton123.setRadius(10);
        panelRadius20.add(myButton123, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 130, 60, 40));

        myButton124.setBackground(new java.awt.Color(145, 227, 168));
        myButton124.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-down.png"))); // NOI18N
        myButton124.setBoderColor(new java.awt.Color(145, 227, 168));
        myButton124.setRadius(10);
        panelRadius20.add(myButton124, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 190, 60, 40));

        myButton125.setBackground(new java.awt.Color(145, 227, 168));
        myButton125.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-down.png"))); // NOI18N
        myButton125.setBoderColor(new java.awt.Color(145, 227, 168));
        myButton125.setRadius(10);
        myButton125.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton125ActionPerformed(evt);
            }
        });
        panelRadius20.add(myButton125, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 250, 60, 40));

        materialTabbed2.addTab("Số Tài Khoản", panelRadius20);

        panelRadius21.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRadius24.setBackground(new java.awt.Color(231, 249, 234));
        panelRadius24.setRadius(10);
        panelRadius24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Theo Năm");
        panelRadius24.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 17, -1, -1));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        panelRadius24.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 160, 30));

        panelRadius21.add(panelRadius24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 250, 50));

        table12.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane12.setViewportView(table12);

        panelRadius21.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 930, 360));

        myButton71.setBackground(new java.awt.Color(145, 227, 168));
        myButton71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-up.png"))); // NOI18N
        myButton71.setBoderColor(new java.awt.Color(145, 227, 168));
        myButton71.setRadius(10);
        panelRadius21.add(myButton71, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 70, 60, 40));

        myButton72.setBackground(new java.awt.Color(145, 227, 168));
        myButton72.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-up.png"))); // NOI18N
        myButton72.setBoderColor(new java.awt.Color(145, 227, 168));
        myButton72.setRadius(10);
        panelRadius21.add(myButton72, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 130, 60, 40));

        myButton73.setBackground(new java.awt.Color(145, 227, 168));
        myButton73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/caret-down.png"))); // NOI18N
        myButton73.setBoderColor(new java.awt.Color(145, 227, 168));
        myButton73.setRadius(10);
        panelRadius21.add(myButton73, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 190, 60, 40));

        myButton64.setBackground(new java.awt.Color(145, 227, 168));
        myButton64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/angle-double-small-down.png"))); // NOI18N
        myButton64.setBoderColor(new java.awt.Color(145, 227, 168));
        myButton64.setRadius(10);
        myButton64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton64ActionPerformed(evt);
            }
        });
        panelRadius21.add(myButton64, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 250, 60, 40));

        panelRadius28.setBackground(new java.awt.Color(231, 249, 234));
        panelRadius28.setRadius(10);
        panelRadius28.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setText("Sắp Xếp ");
        panelRadius28.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 17, -1, -1));

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        panelRadius28.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 170, 30));

        panelRadius29.setBackground(new java.awt.Color(231, 249, 234));
        panelRadius29.setRadius(10);
        panelRadius29.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setText("Phương Thức ");
        panelRadius29.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 17, -1, -1));

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        panelRadius29.add(jComboBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 140, 30));

        panelRadius28.add(panelRadius29, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 250, 50));

        panelRadius21.add(panelRadius28, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 250, 50));

        panelRadius26.setBackground(new java.awt.Color(231, 249, 234));
        panelRadius26.setRadius(10);
        panelRadius26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setText("Phương Thức ");
        panelRadius26.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 17, -1, -1));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        panelRadius26.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 140, 30));

        panelRadius21.add(panelRadius26, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 250, 50));

        materialTabbed2.addTab("Lượt Trải Nghiệm", panelRadius21);

        pnlFrameStatistical.add(materialTabbed2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1060, 480));

        pnlBossMain.add(pnlFrameStatistical, "card5");

        pnlFrameRead.setBackground(new java.awt.Color(205, 239, 215));
        pnlFrameRead.setRadius(15);
        pnlFrameRead.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        materialTabbed3.setTabPlacement(javax.swing.JTabbedPane.RIGHT);
        materialTabbed3.setClorSroll(new java.awt.Color(87, 190, 110));
        materialTabbed3.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N

        panelRadius22.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius22.setRadius(15);

        javax.swing.GroupLayout panelRadius22Layout = new javax.swing.GroupLayout(panelRadius22);
        panelRadius22.setLayout(panelRadius22Layout);
        panelRadius22Layout.setHorizontalGroup(
            panelRadius22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
        );
        panelRadius22Layout.setVerticalGroup(
            panelRadius22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 523, Short.MAX_VALUE)
        );

        materialTabbed3.addTab("Đọc Sách", panelRadius22);

        panelRadius23.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius23.setRadius(15);
        panelRadius23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(table5);

        panelRadius23.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 940, 440));

        jLabel19.setFont(new java.awt.Font("Inter SemiBold", 0, 24)); // NOI18N
        jLabel19.setText("Danh Sách Sách Đọc");
        panelRadius23.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        materialTabbed3.addTab("Danh Sách", panelRadius23);

        pnlFrameRead.add(materialTabbed3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 1050, 528));

        pnlBossMain.add(pnlFrameRead, "card4");

        pnlFrameListen.setBackground(new java.awt.Color(255, 255, 255));
        pnlFrameListen.setRadius(15);
        pnlFrameListen.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        imageBoder5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Image/stock-photo-28962631.jpg"))); // NOI18N
        imageBoder5.setRadius(20);
        pnlFrameListen.add(imageBoder5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 300, 300));

        panelRadius30.setBackground(new java.awt.Color(205, 239, 215));
        panelRadius30.setRadius(15);
        panelRadius30.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblAudio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAudio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAudioMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(tblAudio);

        panelRadius30.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 680, 420));

        jLabel27.setFont(new java.awt.Font("Inter SemiBold", 0, 24)); // NOI18N
        jLabel27.setText("Danh Sách Audio Books");
        panelRadius30.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        pnlFrameListen.add(panelRadius30, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 700, 500));

        jLabel29.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Steven Levy");
        pnlFrameListen.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1, 390, 300, -1));

        myButton7.setBackground(new java.awt.Color(254, 254, 254));
        myButton7.setBorder(null);
        myButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/rewind-button.png"))); // NOI18N
        myButton7.setBoderColor(new java.awt.Color(255, 255, 255));
        myButton7.setColorClick(new java.awt.Color(255, 255, 255));
        pnlFrameListen.add(myButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, 40, 40));

        btnPlay.setBackground(new java.awt.Color(254, 254, 254));
        btnPlay.setBorder(null);
        btnPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/play-button-arrowhead.png"))); // NOI18N
        btnPlay.setBoderColor(new java.awt.Color(255, 255, 255));
        btnPlay.setColorClick(new java.awt.Color(255, 255, 255));
        btnPlay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPlayMouseClicked(evt);
            }
        });
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });
        pnlFrameListen.add(btnPlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 470, 40, 40));

        myButton14.setBackground(new java.awt.Color(254, 254, 254));
        myButton14.setBorder(null);
        myButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/fast-forward-double-black-arrows-multimedia-symbol.png"))); // NOI18N
        myButton14.setBoderColor(new java.awt.Color(255, 255, 255));
        myButton14.setColorClick(new java.awt.Color(255, 255, 255));
        pnlFrameListen.add(myButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 470, 40, 40));

        jLabel30.setText("5:00");
        pnlFrameListen.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 420, -1, -1));

        jLabel31.setText("00:00");
        pnlFrameListen.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, -1, -1));

        slider1.setColorSlider(new java.awt.Color(87, 190, 110));
        pnlFrameListen.add(slider1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 220, -1));

        lblTenAudio.setFont(new java.awt.Font("Inter SemiBold", 0, 24)); // NOI18N
        lblTenAudio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenAudio.setText("Hacker Lược Sử");
        pnlFrameListen.add(lblTenAudio, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 350, 300, -1));

        pnlBossMain.add(pnlFrameListen, "card3");

        pnlMainProjebt.add(pnlBossMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 1060, 540));

        panelRadius2.setBackground(new java.awt.Color(205, 239, 215));
        panelRadius2.setRadius(20);
        panelRadius2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/search (1).png"))); // NOI18N
        panelRadius2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 50, 40));

        searchText1.setBackground(new java.awt.Color(205, 239, 215));
        searchText1.setBorder(null);
        searchText1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchText1ActionPerformed(evt);
            }
        });
        panelRadius2.add(searchText1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 410, 40));

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

        pnlBell.setBackground(new java.awt.Color(205, 239, 215));
        pnlBell.setRadius(10);
        pnlBell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlBellMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlBellMouseExited(evt);
            }
        });
        pnlBell.setLayout(new java.awt.GridBagLayout());

        btnBell.setBackground(new java.awt.Color(205, 239, 215));
        btnBell.setBorder(null);
        btnBell.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/bell (1).png"))); // NOI18N
        btnBell.setBoderColor(new java.awt.Color(205, 239, 215));
        btnBell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBellMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBellMouseExited(evt);
            }
        });
        pnlBell.add(btnBell, new java.awt.GridBagConstraints());

        pnlMainProjebt.add(pnlBell, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 40, 40, 40));

        pnlOff.setBackground(new java.awt.Color(205, 239, 215));
        pnlOff.setRadius(10);
        pnlOff.addMouseListener(new java.awt.event.MouseAdapter() {
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

    private void searchText1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchText1ActionPerformed

    }//GEN-LAST:event_searchText1ActionPerformed

    private void cboTheLoaiSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTheLoaiSachActionPerformed
        int index = cboTheLoaiSach.getSelectedIndex();
        if (index != -1) {
            OtionTableSach(index);
        }
    }//GEN-LAST:event_cboTheLoaiSachActionPerformed

    private void myButton64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton64ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_myButton64ActionPerformed

    private void myButton125ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton125ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_myButton125ActionPerformed

    private void myButton129ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton129ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_myButton129ActionPerformed

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed

    }//GEN-LAST:event_btnPlayActionPerformed

    private void lblOffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOffMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblOffMouseClicked

    private void panelBorder1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBorder1MouseClicked

    }//GEN-LAST:event_panelBorder1MouseClicked

    private void pnlBellMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlBellMouseEntered
        pnlBell.setBackground(new Color(130, 219, 150));
        btnBell.setBackground(new Color(130, 219, 150));
        btnBell.setBoderColor(new Color(0, 0, 0, 0));
    }//GEN-LAST:event_pnlBellMouseEntered

    private void pnlBellMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlBellMouseExited
        pnlBell.setBackground(new Color(205, 239, 215));
        btnBell.setBackground(new Color(205, 239, 215));
    }//GEN-LAST:event_pnlBellMouseExited

    private void btnBellMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBellMouseEntered
        pnlBell.setBackground(new Color(130, 219, 150));
        btnBell.setBackground(new Color(130, 219, 150));
        btnBell.setBoderColor(new Color(0, 0, 0, 0));
    }//GEN-LAST:event_btnBellMouseEntered

    private void btnBellMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBellMouseExited
        pnlBell.setBackground(new Color(205, 239, 215));
        btnBell.setBackground(new Color(205, 239, 215));
    }//GEN-LAST:event_btnBellMouseExited

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
    }//GEN-LAST:event_btnSettingActionPerformed

    private void imageAvatar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageAvatar1MouseClicked
        OpenPerson();
    }//GEN-LAST:event_imageAvatar1MouseClicked
    int i = 0;
    private void tblAudioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAudioMouseClicked
        // TODO add your handling code here:

        int index = tblAudio.getSelectedRow();

        AudioSach au = listAudio.get(index);
        lblTenAudio.setText(au.getTenAudio());
        songFile = new File("E:\\UDPM_DuAn1\\Github\\DuAn01_G3\\DuAn01_G3_ebooks" + au.getDuongDan());
        player = mp3Player();
        player.addToPlayList(songFile);
        player.skipForward();
        btnPlay.setIcon(new ImageIcon("..\\DuAn01_G3_ebooks\\src\\com\\ebooks\\Icon\\play-button-arrowhead.png"));


    }//GEN-LAST:event_tblAudioMouseClicked

    private void btnPlayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPlayMouseClicked
        if (congTac == true) {
            btnPlay.setIcon(new ImageIcon("..\\DuAn01_G3_ebooks\\src\\com\\ebooks\\Icon\\pause-button.png"));
            congTac = false;
            player.play();
        } else {
            btnPlay.setIcon(new ImageIcon("..\\DuAn01_G3_ebooks\\src\\com\\ebooks\\Icon\\play-button-arrowhead.png"));
            congTac = true;
            player.pause();
        }
    }//GEN-LAST:event_btnPlayMouseClicked

    private void btnThemNguoiDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNguoiDungActionPerformed
        MaND = null;
        new PersonDiaLog(this, true).setVisible(true);
    }//GEN-LAST:event_btnThemNguoiDungActionPerformed

    private void myButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_myButton8ActionPerformed


    private void tblNguoiDungMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNguoiDungMousePressed
        if (evt.getClickCount() == 2) {
            int indexRow = tblNguoiDung.getSelectedRow();
            String MaNguoiDung = (String) tblNguoiDung.getValueAt(indexRow, 0);
            nguoiDung = DaoND.findById(MaNguoiDung);
            new PersonDiaLog(this, congTac, nguoiDung).setVisible(true);
        }


    }//GEN-LAST:event_tblNguoiDungMousePressed

    private void tblThucUongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThucUongMousePressed
        if (evt.getClickCount() == 2) {
            int indexRow = tblThucUong.getSelectedRow();
            String maThucUong = (String) tblThucUong.getValueAt(indexRow, 0);
            thucUong = DaoTU.findById(maThucUong);
            new DrinksDiaLog(this, congTac, thucUong).setVisible(true);
            this.fillTableThucUong();
        }
    }//GEN-LAST:event_tblThucUongMousePressed

    private void btnThucUongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThucUongActionPerformed
        // TODO add your handling code here:
        new DrinksDiaLog(this, congTac).setVisible(true);
        this.fillTableThucUong();
    }//GEN-LAST:event_btnThucUongActionPerformed

    private void btnSuaThongTinThucUongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaThongTinThucUongActionPerformed
        index = tblThucUong.getSelectedRow();
        if (index < 0) {
            DialogHelper.alert(this, "Chưa chọn thức uống cần chỉnh sửa!");
        } else {
            String maThucUong = (String) tblThucUong.getValueAt(index, 0);
            thucUong = DaoTU.findById(maThucUong);
            new DrinksDiaLog(this, congTac, thucUong).setVisible(true);
            this.fillTableThucUong();
        }

    }//GEN-LAST:event_btnSuaThongTinThucUongActionPerformed

    private void btnXoaThucUongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaThucUongActionPerformed
        index = tblThucUong.getSelectedRow();
        if (index < 0) {
            DialogHelper.alert(this, "Chưa chọn thức uống cần xóa!");
        } else if (DialogHelper.confirm(this, "Bạn thật sự muốn xóa thức uống này?")) {
            DaoTU.delete(tblThucUong.getValueAt(index, 0).toString());
            this.fillTableThucUong();
        }
    }//GEN-LAST:event_btnXoaThucUongActionPerformed

    private void btnFirstThucUongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstThucUongActionPerformed
        index = tblThucUong.getSelectedRow();
        UtilityHelper.first(index, tblThucUong);
    }//GEN-LAST:event_btnFirstThucUongActionPerformed

    private void btnPreThucUongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreThucUongActionPerformed
        index = tblThucUong.getSelectedRow();
        UtilityHelper.previous(index, tblThucUong, listTU);
    }//GEN-LAST:event_btnPreThucUongActionPerformed

    private void btnNextThucUongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextThucUongActionPerformed
        index = tblThucUong.getSelectedRow();
        UtilityHelper.next(index, tblThucUong, listTU);
    }//GEN-LAST:event_btnNextThucUongActionPerformed

    private void btnLastThucUongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastThucUongActionPerformed
        index = tblThucUong.getSelectedRow();
        UtilityHelper.last(index, tblThucUong, listTU);
    }//GEN-LAST:event_btnLastThucUongActionPerformed

    private void tblSachMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSachMousePressed
        if (evt.getClickCount() == 2) {
            int index = tblSach.getSelectedRow();
            String maSach = (String) tblSach.getValueAt(index, 0);
            Sach sach = DAOS.findById(maSach);
            new BooksDiaLog(this, congTac, sach).setVisible(true);
            fillTableSach();
        }

    }//GEN-LAST:event_tblSachMousePressed

    private void btnFirstSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstSachActionPerformed
        index = tblSach.getSelectedRow();
        UtilityHelper.first(index, tblSach);
    }//GEN-LAST:event_btnFirstSachActionPerformed

    private void btnNextSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextSachActionPerformed
        index = tblSach.getSelectedRow();
        UtilityHelper.previous(index, tblSach, listS);
    }//GEN-LAST:event_btnNextSachActionPerformed

    private void btnPrevSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevSachActionPerformed
        index = tblSach.getSelectedRow();
        UtilityHelper.next(index, tblSach, listS);
    }//GEN-LAST:event_btnPrevSachActionPerformed

    private void btnLastSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastSachActionPerformed
        index = tblSach.getSelectedRow();
        UtilityHelper.last(index, tblSach, listS);
    }//GEN-LAST:event_btnLastSachActionPerformed

    private void btnThemSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSachActionPerformed
        new BooksDiaLog(this, congTac).setVisible(true);
        fillTableSach();
    }//GEN-LAST:event_btnThemSachActionPerformed

    private void btnXoaSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSachActionPerformed
        index = tblSach.getSelectedRow();
        String MaSach = tblSach.getValueAt(index, 0).toString();
        if (index < 0) {
            DialogHelper.alert(this, "Chưa chọn Sách cần xóa!");
        } else if (DialogHelper.confirm(this, "Bạn thật sự muốn xóa Sách này?")) {
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

    private void btnXoaNguoiDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNguoiDungActionPerformed
        deleteNguoiDung();
        LoadNguoiDung();
        fillTableNguoiDung(listND);
    }//GEN-LAST:event_btnXoaNguoiDungActionPerformed

    private void btnThemTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemTaiKhoanActionPerformed
        TenDangNhap = null;
        new AccountDiaLog(this, true).setVisible(true);
    }//GEN-LAST:event_btnThemTaiKhoanActionPerformed

    private void btnXoaTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTaiKhoanActionPerformed
        deleteTaiKhoan();
    }//GEN-LAST:event_btnXoaTaiKhoanActionPerformed

    private void btnSuaNguoiDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNguoiDungActionPerformed
        int indexRow = tblNguoiDung.getSelectedRow();
        if (indexRow == -1) {
            DialogHelper.alert(this, "Chưa chọn người dùng");
            return;
        }
        String MaNguoiDung = (String) tblNguoiDung.getValueAt(indexRow, 0);
        nguoiDung = DaoND.findById(MaNguoiDung);
        new PersonDiaLog(this, congTac, nguoiDung).setVisible(true);
    }//GEN-LAST:event_btnSuaNguoiDungActionPerformed

    private void tblTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTaiKhoanMouseClicked
        if (evt.getClickCount() == 2) {
            int index = tblTaiKhoan.getSelectedRow();
            TenDangNhap = tblTaiKhoan.getValueAt(index, 0).toString();

            new AccountDiaLog(this, true).setVisible(true);
        }
    }//GEN-LAST:event_tblTaiKhoanMouseClicked

    private void btnSuaTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaTaiKhoanMouseClicked
        int index = tblTaiKhoan.getSelectedRow();

        if (index == -1) {
            DialogHelper.alert(this, "Chưa chọn tài khoản");
            return;
        }

        TenDangNhap = tblTaiKhoan.getValueAt(index, 0).toString();
        new AccountDiaLog(this, true).setVisible(true);
    }//GEN-LAST:event_btnSuaTaiKhoanMouseClicked

    private void btnFirstAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstAudioActionPerformed
        index = tblAudioQL.getSelectedRow();
        UtilityHelper.first(index, tblAudioQL);
    }//GEN-LAST:event_btnFirstAudioActionPerformed

    private void btnPrevAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevAudioActionPerformed
        index = tblAudioQL.getSelectedRow();
        UtilityHelper.previous(index, tblAudioQL, listAudio);
    }//GEN-LAST:event_btnPrevAudioActionPerformed

    private void btnNextAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextAudioActionPerformed

        index = tblAudioQL.getSelectedRow();
        UtilityHelper.next(index, tblAudioQL, listAudio);
    }//GEN-LAST:event_btnNextAudioActionPerformed

    private void btnLastAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastAudioActionPerformed
        index = tblAudioQL.getSelectedRow();
        UtilityHelper.last(index, tblAudioQL, listAudio);
    }//GEN-LAST:event_btnLastAudioActionPerformed

    private void tblAudioQLMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAudioQLMousePressed
        if (evt.getClickCount() == 2) {
            int index = tblAudioQL.getSelectedRow();
            String maAudio = tblAudioQL.getValueAt(index, 0).toString();
            AudioSach audio = daoAudio.findById(maAudio);
            new AudiosDiaLog(this, true, audio).setVisible(true);
            fillTableAudio(tblAudioQL);
            fillTableAudio(tblAudio);
        }
    }//GEN-LAST:event_tblAudioQLMousePressed

    private void btnThemAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemAudioActionPerformed
        new AudiosDiaLog(this, true).setVisible(true);
        fillTableAudio(tblAudioQL);
        fillTableAudio(tblAudio);
    }//GEN-LAST:event_btnThemAudioActionPerformed

    private void btnSuaAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaAudioActionPerformed

        int index = tblAudioQL.getSelectedRow();
        if (index != -1) {
            String maAudio = tblAudioQL.getValueAt(index, 0).toString();
            AudioSach audio = daoAudio.findById(maAudio);
            new AudiosDiaLog(this, true, audio).setVisible(true);
            fillTableAudio(tblAudioQL);
            fillTableAudio(tblAudio);
        } else {
            DialogHelper.alert(this, "Bạn Hãy Chọn Audio");
        }


    }//GEN-LAST:event_btnSuaAudioActionPerformed

    private void btnXoaAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaAudioActionPerformed
        index = tblAudioQL.getSelectedRow();
        String MaAuido = tblAudioQL.getValueAt(index, 0).toString();
        if (index < 0) {
            DialogHelper.alert(this, "Chưa chọn Audio cần xóa!");
        } else if (DialogHelper.confirm(this, "Bạn thật sự muốn xóa Audio này?")) {
            try {

                daoAudio.delete(MaAuido);
                this.fillTableAudio(tblAudioQL);
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_btnXoaAudioActionPerformed

    private void btnCapNhatSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatSachActionPerformed

        if (index != -1) {
            String maSach = (String) tblSach.getValueAt(index, 0);
            Sach sach = DAOS.findById(maSach);
            new BooksDiaLog(this, congTac, sach).setVisible(true);
            fillTableSach();
        } else {
            DialogHelper.alert(this, "Hãy Chọn Sách");
        }

    }//GEN-LAST:event_btnCapNhatSachActionPerformed

    private void myButton78ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton78ActionPerformed
        SortAudio();
    }//GEN-LAST:event_myButton78ActionPerformed

    private void myButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton18ActionPerformed
        SortSach();
    }//GEN-LAST:event_myButton18ActionPerformed

    private void myButton104ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton104ActionPerformed
        index = tblNguoiDung.getSelectedRow();
        UtilityHelper.first(index, tblNguoiDung);
    }//GEN-LAST:event_myButton104ActionPerformed

    private void myButton103ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton103ActionPerformed
        // TODO add your handling code here:
        index = tblNguoiDung.getSelectedRow();
        UtilityHelper.previous(index, tblNguoiDung, listND);
    }//GEN-LAST:event_myButton103ActionPerformed

    private void myButton102ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton102ActionPerformed
        // TODO add your handling code here:
        index = tblNguoiDung.getSelectedRow();
        UtilityHelper.next(index, tblNguoiDung, listND);
    }//GEN-LAST:event_myButton102ActionPerformed

    private void myButton101ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton101ActionPerformed
        // TODO add your handling code here:
        index = tblNguoiDung.getSelectedRow();
        UtilityHelper.last(index, tblNguoiDung, listND);
    }//GEN-LAST:event_myButton101ActionPerformed

    private void myButton93ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton93ActionPerformed
        index = tblTaiKhoan.getSelectedRow();
        UtilityHelper.last(index, tblTaiKhoan, listTK);
    }//GEN-LAST:event_myButton93ActionPerformed

    private void myButton94ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton94ActionPerformed
        // TODO add your handling code here:
        index = tblTaiKhoan.getSelectedRow();
        UtilityHelper.next(index, tblTaiKhoan, listTK);
    }//GEN-LAST:event_myButton94ActionPerformed

    private void myButton95ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton95ActionPerformed
        // TODO add your handling code here:
        index = tblTaiKhoan.getSelectedRow();
        UtilityHelper.previous(index, tblTaiKhoan, listTK);
    }//GEN-LAST:event_myButton95ActionPerformed

    private void myButton96ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton96ActionPerformed
        // TODO add your handling code here:
        index = tblTaiKhoan.getSelectedRow();
        UtilityHelper.first(index, tblTaiKhoan);
    }//GEN-LAST:event_myButton96ActionPerformed

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ebooks.Compoment.MyButton btnBell;
    private com.ebooks.Compoment.MyButton btnCapNhatSach;
    private com.ebooks.Compoment.MyButton btnFirstAudio;
    private com.ebooks.Compoment.MyButton btnFirstHoaDon;
    private com.ebooks.Compoment.MyButton btnFirstSach;
    private com.ebooks.Compoment.MyButton btnFirstThucUong;
    private com.ebooks.Compoment.MyButton btnLastAudio;
    private com.ebooks.Compoment.MyButton btnLastHoaDon;
    private com.ebooks.Compoment.MyButton btnLastSach;
    private com.ebooks.Compoment.MyButton btnLastThucUong;
    private com.ebooks.Compoment.MyButton btnListen;
    private com.ebooks.Compoment.MyButton btnMain;
    private com.ebooks.Compoment.MyButton btnManage;
    private com.ebooks.Compoment.MyButton btnNextAudio;
    private com.ebooks.Compoment.MyButton btnNextHoaDon;
    private com.ebooks.Compoment.MyButton btnNextSach;
    private com.ebooks.Compoment.MyButton btnNextThucUong;
    private com.ebooks.Compoment.MyButton btnPlay;
    private com.ebooks.Compoment.MyButton btnPreHoaDon;
    private com.ebooks.Compoment.MyButton btnPreThucUong;
    private com.ebooks.Compoment.MyButton btnPrevAudio;
    private com.ebooks.Compoment.MyButton btnPrevSach;
    private com.ebooks.Compoment.MyButton btnRead;
    private com.ebooks.Compoment.MyButton btnSetting;
    private com.ebooks.Compoment.MyButton btnStatistical;
    private com.ebooks.Compoment.MyButton btnSuaAudio;
    private com.ebooks.Compoment.MyButton btnSuaNguoiDung;
    private com.ebooks.Compoment.MyButton btnSuaTaiKhoan;
    private com.ebooks.Compoment.MyButton btnSuaThongTinHoaDon;
    private com.ebooks.Compoment.MyButton btnSuaThongTinThucUong;
    private com.ebooks.Compoment.MyButton btnThemAudio;
    private com.ebooks.Compoment.MyButton btnThemHoaDon;
    private com.ebooks.Compoment.MyButton btnThemNguoiDung;
    private com.ebooks.Compoment.MyButton btnThemSach;
    private com.ebooks.Compoment.MyButton btnThemTaiKhoan;
    private com.ebooks.Compoment.MyButton btnThucUong;
    private com.ebooks.Compoment.MyButton btnXoaAudio;
    private com.ebooks.Compoment.MyButton btnXoaNguoiDung;
    private com.ebooks.Compoment.MyButton btnXoaSach;
    private com.ebooks.Compoment.MyButton btnXoaTaiKhoan;
    private com.ebooks.Compoment.MyButton btnXoaThongTinHoaDon;
    private com.ebooks.Compoment.MyButton btnXoaThucUong;
    private javax.swing.JComboBox<String> cboTheLoaiSach;
    private com.ebooks.Compoment.ImageAvatar imageAvatar1;
    private com.ebooks.Compoment.ImageBoder imageBoder1;
    private com.ebooks.Compoment.ImageBoder imageBoder2;
    private com.ebooks.Compoment.ImageBoder imageBoder3;
    private com.ebooks.Compoment.ImageBoder imageBoder4;
    private com.ebooks.Compoment.ImageBoder imageBoder5;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblDay;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblOff;
    private javax.swing.JLabel lblTenAudio;
    private javax.swing.JLabel lblTime;
    private com.ebooks.Compoment.MaterialTabbed materialTabbed1;
    private com.ebooks.Compoment.MaterialTabbed materialTabbed2;
    private com.ebooks.Compoment.MaterialTabbed materialTabbed3;
    private com.ebooks.Compoment.MyButton myButton10;
    private com.ebooks.Compoment.MyButton myButton101;
    private com.ebooks.Compoment.MyButton myButton102;
    private com.ebooks.Compoment.MyButton myButton103;
    private com.ebooks.Compoment.MyButton myButton104;
    private com.ebooks.Compoment.MyButton myButton122;
    private com.ebooks.Compoment.MyButton myButton123;
    private com.ebooks.Compoment.MyButton myButton124;
    private com.ebooks.Compoment.MyButton myButton125;
    private com.ebooks.Compoment.MyButton myButton126;
    private com.ebooks.Compoment.MyButton myButton127;
    private com.ebooks.Compoment.MyButton myButton128;
    private com.ebooks.Compoment.MyButton myButton129;
    private com.ebooks.Compoment.MyButton myButton14;
    private com.ebooks.Compoment.MyButton myButton18;
    private com.ebooks.Compoment.MyButton myButton3;
    private com.ebooks.Compoment.MyButton myButton4;
    private com.ebooks.Compoment.MyButton myButton5;
    private com.ebooks.Compoment.MyButton myButton6;
    private com.ebooks.Compoment.MyButton myButton64;
    private com.ebooks.Compoment.MyButton myButton7;
    private com.ebooks.Compoment.MyButton myButton71;
    private com.ebooks.Compoment.MyButton myButton72;
    private com.ebooks.Compoment.MyButton myButton73;
    private com.ebooks.Compoment.MyButton myButton78;
    private com.ebooks.Compoment.MyButton myButton8;
    private com.ebooks.Compoment.MyButton myButton86;
    private com.ebooks.Compoment.MyButton myButton87;
    private com.ebooks.Compoment.MyButton myButton88;
    private com.ebooks.Compoment.MyButton myButton89;
    private com.ebooks.Compoment.MyButton myButton9;
    private com.ebooks.Compoment.MyButton myButton90;
    private com.ebooks.Compoment.MyButton myButton91;
    private com.ebooks.Compoment.MyButton myButton92;
    private com.ebooks.Compoment.MyButton myButton93;
    private com.ebooks.Compoment.MyButton myButton94;
    private com.ebooks.Compoment.MyButton myButton95;
    private com.ebooks.Compoment.MyButton myButton96;
    private com.ebooks.Compoment.PanelRadius panelRadius10;
    private com.ebooks.Compoment.PanelRadius panelRadius11;
    private com.ebooks.Compoment.PanelRadius panelRadius12;
    private com.ebooks.Compoment.PanelRadius panelRadius13;
    private com.ebooks.Compoment.PanelRadius panelRadius14;
    private com.ebooks.Compoment.PanelRadius panelRadius17;
    private com.ebooks.Compoment.PanelRadius panelRadius18;
    private com.ebooks.Compoment.PanelRadius panelRadius2;
    private com.ebooks.Compoment.PanelRadius panelRadius20;
    private com.ebooks.Compoment.PanelRadius panelRadius21;
    private com.ebooks.Compoment.PanelRadius panelRadius22;
    private com.ebooks.Compoment.PanelRadius panelRadius23;
    private com.ebooks.Compoment.PanelRadius panelRadius24;
    private com.ebooks.Compoment.PanelRadius panelRadius25;
    private com.ebooks.Compoment.PanelRadius panelRadius26;
    private com.ebooks.Compoment.PanelRadius panelRadius27;
    private com.ebooks.Compoment.PanelRadius panelRadius28;
    private com.ebooks.Compoment.PanelRadius panelRadius29;
    private com.ebooks.Compoment.PanelRadius panelRadius3;
    private com.ebooks.Compoment.PanelRadius panelRadius30;
    private com.ebooks.Compoment.PanelRadius panelRadius31;
    private com.ebooks.Compoment.PanelRadius panelRadius4;
    private com.ebooks.Compoment.PanelRadius panelRadius6;
    private com.ebooks.Compoment.PanelRadius panelRadius7;
    private com.ebooks.Compoment.PanelRadius panelRadius8;
    private com.ebooks.Compoment.PanelRadius panelRadius9;
    private com.ebooks.Compoment.PanelRadius pnlBell;
    private javax.swing.JPanel pnlBossMain;
    private com.ebooks.Compoment.PanelRadius pnlFrameListen;
    private com.ebooks.Compoment.PanelRadius pnlFrameMain;
    private com.ebooks.Compoment.PanelRadius pnlFrameManage;
    private com.ebooks.Compoment.PanelRadius pnlFrameRead;
    private com.ebooks.Compoment.PanelRadius pnlFrameStatistical;
    private com.ebooks.Compoment.PanelRadius pnlHoaDon;
    private com.ebooks.Compoment.PanelRadius pnlListen;
    private com.ebooks.Compoment.PanelRadius pnlMain;
    private com.ebooks.Compoment.PanelBorder pnlMainProjebt;
    private com.ebooks.Compoment.PanelRadius pnlManage;
    private com.ebooks.Compoment.PanelRadius pnlMenu;
    private com.ebooks.Compoment.PanelRadius pnlMenuBooks;
    private com.ebooks.Compoment.PanelRadius pnlOff;
    private com.ebooks.Compoment.PanelRadius pnlRead;
    private com.ebooks.Compoment.PanelRadius pnlSetting;
    private com.ebooks.Compoment.PanelRadius pnlStatistical;
    private com.ebooks.Compoment.PanelRadius pnlThucUong;
    private com.ebooks.Compoment.SearchText searchText1;
    private com.ebooks.Compoment.Slider slider1;
    private com.ebooks.Compoment.Table table11;
    private com.ebooks.Compoment.Table table12;
    private com.ebooks.Compoment.Table table2;
    private com.ebooks.Compoment.Table table4;
    private com.ebooks.Compoment.Table table5;
    private com.ebooks.Compoment.Table table6;
    private com.ebooks.Compoment.Table tblAudio;
    private com.ebooks.Compoment.Table tblAudioQL;
    private com.ebooks.Compoment.Table tblHoaDon;
    private com.ebooks.Compoment.Table tblLoaiSach;
    private com.ebooks.Compoment.Table tblNguoiDung;
    private com.ebooks.Compoment.Table tblSach;
    private com.ebooks.Compoment.Table tblTaiKhoan;
    private com.ebooks.Compoment.Table tblThucUong;
    // End of variables declaration//GEN-END:variables

    public void OpenSetting() {
        new SettingDiaLog(this, true).setVisible(true);

    }

    public void OpenPerson() {
        new AccountDiaLog(this, true).setVisible(true);

    }

    //FILLTABLE Sach
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

    public void fillComBoBoxTheLoai(JComboBox cbo) {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbo.getModel();
        model.removeAllElements();
        List<TheLoai> list = DAOTL.selectAll();
        for (TheLoai tl : list) {
            model.addElement(tl.getTenTheLoai());
        }
    }

//    DefaultTableModel model;
    public void fillTableNguoiDung() {
        DefaultTableModel model;
        model = (DefaultTableModel) tblNguoiDung.getModel();
        model.setRowCount(0);
        try {
            listND = DaoND.selectAll();
            for (NguoiDung nd : listND) {
                Object[] row = {nd.getMaNguoiDung(), nd.getHoTen(), nd.getSoDienThoai(), nd.isGioiTinh() ? "Nam" : "Nữ", nd.getEmail(), nd.getHinh()};
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    //FILL DỮ LIỆU LÊN BẢNG THỨC UỐNG
    public void fillTableThucUong() {
        DefaultTableModel model;
        model = new DefaultTableModel();
        model.setRowCount(0);
        //set COLUMN
        model.addColumn("MÃ THỨC UỐNG");
        model.addColumn("TÊN THỨC UỐNG");
        model.addColumn("GIÁ TIỀN");

        listTU = DaoTU.selectAll();
        //tạo hàng và do du lieu
        for (ThucUong tu : listTU) {
            Object[] row = {tu.getMaThucUong(), tu.getTenThucUong(), tu.getGia()};
            model.addRow(row);
            tblThucUong.setModel(model);
        }
    }

    public void LoadNguoiDung() {

        listND = DaoND.selectAll();
    }

    public void LoadTaiKhoan() {
        listTK = DaoTK.selectAll();
    }

    public void fillTableNguoiDung(List<NguoiDung> list) {
        tblTable = (DefaultTableModel) tblNguoiDung.getModel();
        tblTable.setRowCount(0);
        try {
            for (NguoiDung emp : list) {
                Object[] row = {
                    emp.getMaNguoiDung(),
                    emp.getHoTen(),
                    emp.isGioiTinh() == true ? "Nam" : "Nữ",
                    emp.getSoDienThoai(),
                    emp.getEmail(),
                    emp.getHinh()};
                tblTable.addRow(row);

            }
        } catch (Exception e) {
            System.out.println(e);
            DialogHelper.alert(null, "Lỗi truy vấn dữ liệu!");
        }

    }

    public void fillTableTaiKhoan(List<TaiKhoan> list) {
        tblTable = (DefaultTableModel) tblTaiKhoan.getModel();
        tblTable.setRowCount(0);
        try {
            for (TaiKhoan tk : list) {
                Object[] row = {
                    tk.getTenDangNhap(),
                    tk.getMatKhau(),
                    tk.getMaNguoiDung(),
                    tk.isTrangThai() == true ? "Quản trị viên" : "Người dùng",
                    tk.getThoiLuong()};
                tblTable.addRow(row);
            }
        } catch (Exception e) {
            System.out.println(e);
            DialogHelper.alert(null, "Lỗi truy vấn dữ liệu!");
        }
    }

    void deleteNguoiDung() {
        int index = tblNguoiDung.getSelectedRow();
        if (index == -1) {
            DialogHelper.alert(this, "Chưa chọn người dùng");
            return;
        }
        if (DialogHelper.confirm(this, "Bạn thực sự muốn xóa người dùng này?")) {
            String MaND = tblNguoiDung.getValueAt(index, 0).toString();
            try {
                DaoND.delete(MaND);
                LoadNguoiDung();
                this.fillTableNguoiDung(listND);
                DialogHelper.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Người dùng này đã có tài khoản. Xóa thất bại!");
            }
        }
    }

    void deleteTaiKhoan() {
        int index = tblTaiKhoan.getSelectedRow();
        if (index == -1) {
            DialogHelper.alert(this, "Chưa chọn tài khoản");
            return;
        }
        if (DialogHelper.confirm(this, "Bạn thực sự muốn xóa người dùng này?")) {
            String TenDangNhap = tblTaiKhoan.getValueAt(index, 0).toString();
            try {
                DaoTK.delete(TenDangNhap);
                LoadTaiKhoan();
                this.fillTableTaiKhoan(listTK);
                DialogHelper.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Xóa thất bại!");
            }
        }
    }

    // HÀM DELETE TONG QUAT //
}
