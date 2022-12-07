/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.ebooks.main;

import com.ebooks.dao.BangGiaThueDAO;
import com.ebooks.dao.HoaDonChiTietDAO;
import com.ebooks.dao.HoaDonThucUongDAO;
import com.ebooks.dao.HoaDonThueSachDAO;
import com.ebooks.dao.HoaDonTongHopDAO;
import com.ebooks.dao.NguoiDungDAO;
import com.ebooks.dao.TaiKhoanDAO;
import com.ebooks.dao.ThucUongDAO;
import com.ebooks.helper.DialogHelper;
import com.ebooks.helper.UtilityHelper;
import static com.ebooks.main.Main.listND;
import com.ebooks.model.BangGiaThue;
import com.ebooks.model.HoaDonChiTiet;
import com.ebooks.model.HoaDonThucUong;
import com.ebooks.model.HoaDonThueSach;
import com.ebooks.model.HoaDonTongHop;
import com.ebooks.model.NguoiDung;
import com.ebooks.model.TaiKhoan;
import com.ebooks.model.TheLoai;
import com.ebooks.model.ThucUong;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thinh
 */
public class ReceiptDiaLog extends javax.swing.JDialog {

    boolean congTac = false;
    boolean congTac1 = false;
    String MaHD = null;
    HoaDonChiTietDAO DAOCT = new HoaDonChiTietDAO();
    HoaDonThucUongDAO DAOHDTU = new HoaDonThucUongDAO();
    HoaDonThueSachDAO DAOTS = new HoaDonThueSachDAO();
    List<HoaDonChiTiet> listHDCT = new ArrayList<>();
    List<HoaDonThucUong> listHDTU = new ArrayList<>();
    List<HoaDonThueSach> listHSTS = new ArrayList<>();
    NguoiDungDAO DAOND = new NguoiDungDAO();
    public ReceiptDiaLog(java.awt.Frame parent, boolean modal, HoaDonTongHop hdth) {
        super(parent, modal);
        initComponents();
        Calendar.setVisible(congTac);
        congTac = !congTac;
        Calendar1.setVisible(congTac1);
        congTac1 = !congTac1;
        setBackground(new Color(0, 0, 0, 0));
        initMoving(this, pnlMainDialog);
        setLabelTH(hdth);
        setFormThucUong(hdth);
        setFormThueSach(hdth);
        tabHDTU.setSelectedIndex(0);
        tabHDTU.setEnabledAt(1, false);
        tabHDTU.setEnabledAt(2, false);
        txtMaHoaDon.setEnabled(false);

    }

    public ReceiptDiaLog(java.awt.Frame parent, boolean modal, HoaDonThucUong hdtu, Float TongTienThucUong) {
        super(parent, modal);
        initComponents();
        Calendar.setVisible(congTac);
        congTac = !congTac;
        Calendar1.setVisible(congTac1);
        congTac1 = !congTac1;
        setBackground(new Color(0, 0, 0, 0));
        initMoving(this, pnlMainDialog);
        setFormThucUong(hdtu, TongTienThucUong);
        lblMaHDThucUong.setText(txtMaHoaDon.getText());
        TabThongTinHoaDon.setSelectedIndex(1);
        tabHDTU.setEnabledAt(2, false);
    }

    public ReceiptDiaLog(java.awt.Frame parent, boolean modal, HoaDonThueSach hdts) {
        super(parent, modal);
        initComponents();
        Calendar.setVisible(congTac);
        congTac = !congTac;
        Calendar1.setVisible(congTac1);
        congTac1 = !congTac1;
        TabThongTinHoaDon.setSelectedIndex(2);
        setBackground(new Color(0, 0, 0, 0));
        initMoving(this, pnlMainDialog);
        setFormThueSach(hdts);
    }

    public ReceiptDiaLog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Calendar.setVisible(congTac);
        congTac = !congTac;
        Calendar1.setVisible(congTac1);
        congTac1 = !congTac1;
        setBackground(new Color(0, 0, 0, 0));
        initMoving(this, pnlMainDialog);
       
    }

    public void setLabelTH(HoaDonTongHop hdth) {
        lblMaNguoiDung.setText(hdth.getMaNguoiDung());
        lblTenNguoiDung.setText(hdth.getHoTen());
        lblTongTienThucUong.setText(String.valueOf(hdth.getTongTienThucUong()));
        lblTongTienThueSach.setText(String.valueOf(hdth.getTongTienThueSach()));
        lblTongTien.setText(String.valueOf(hdth.getTongTien()));
    }

    public void setFormThucUong(HoaDonTongHop hdth) {
        txtMaHoaDon.setText(hdth.getMaHoaDonThucUong());
        txtMaNguoiDung.setText(hdth.getMaNguoiDung());
        listHDTU = DAOHDTU.selectAll();
        listHDCT = DAOCT.selectAll();
        lblMaHDThucUong.setText(txtMaHoaDon.getText());
        lblTenND.setText(hdth.getHoTen());

        for (HoaDonThucUong hdtu : listHDTU) {
            if (hdtu.getMaNguoiDung().contains(hdth.getMaNguoiDung())) {
                txtNgayMua.setText(String.valueOf(hdtu.getNgayMua()));
            }
        }
    }

    public void InsertHDTS() {
        if (UtilityHelper.checkNullText(jLabel17, txtMaNguoiDung1) && UtilityHelper.checkNullText(jLabel15, txtTenDangNhap) && UtilityHelper.checkNullText(jLabel16, txtNgayMua)) {
            HoaDonThueSach hdts = new HoaDonThueSach();
            hdts.setMaNguoiDung(txtMaNguoiDung1.getText());
            hdts.setTenDangNhap(txtTenDangNhap.getText());
            hdts.setNgayThue(Calendar1.getDate());
            hdts.setThoiGian(Time.valueOf(String.valueOf(cboGioThue.getSelectedItem())));
            hdts.setMaGiaThue(String.valueOf(cboBangGiaThue.getSelectedItem()));
            DAOTS.insert(hdts);
        }
    }

    public void UpdateHDTS() {
        if (UtilityHelper.checkNullText(jLabel17, txtMaNguoiDung1) && UtilityHelper.checkNullText(jLabel15, txtTenDangNhap) && UtilityHelper.checkNullText(jLabel16, txtNgayMua)) {
            HoaDonThueSach hdts = DAOTS.findByIdND(lblMaNguoiDungTS.getText());
            hdts.setMaNguoiDung(txtMaNguoiDung1.getText());
            hdts.setTenDangNhap(txtTenDangNhap.getText());
            hdts.setNgayThue(Calendar1.getDate());
            hdts.setThoiGian(Time.valueOf(String.valueOf(cboGioThue.getSelectedItem())));
            hdts.setMaGiaThue(String.valueOf(cboBangGiaThue.getSelectedItem()));
            DAOTS.update(hdts);
        }
    }

    public void setFormThucUong(HoaDonThucUong hdtu1, float TongTienThucUong) {
        txtMaHoaDon.setText(hdtu1.getMaHoaDon());
        txtMaNguoiDung.setText(hdtu1.getMaNguoiDung());

        listHDTU = DAOHDTU.selectAll();
        listHDCT = DAOCT.selectAll();
        Main.listND = DAOND.selectAll();
        for (NguoiDung nd : Main.listND) {
            if (nd.getMaNguoiDung().contains(txtMaNguoiDung.getText())) {
                lblTenND.setText(nd.getHoTen());
                break;
            }
        }
        for (HoaDonThucUong hdtu : listHDTU) {
            if (hdtu.getMaNguoiDung().contains(hdtu1.getMaNguoiDung())) {
                txtNgayMua.setText(String.valueOf(hdtu.getNgayMua()));
                break;
            }
        }
        txtMaHoaDon.setEnabled(false);
        txtMaNguoiDung.setEnabled(false);
    }

    public void setFormThueSach(HoaDonTongHop hdth) {
        List<TaiKhoan> listTK = new ArrayList<>();
        TaiKhoanDAO DAOTK = new TaiKhoanDAO();
        txtTenDangNhap.setText(hdth.getTenDangNhap());
        txtMaNguoiDung1.setText(hdth.getMaNguoiDung());
        lblMaNguoiDungTS.setText(txtMaNguoiDung1.getText());
        lblTenDangNhapTS.setText(txtTenDangNhap.getText());
        lblNgayThueTS.setText(String.valueOf(Calendar1.getDate()));
        listHSTS = DAOTS.selectAll();
        listTK = DAOTK.selectAll();
        for (HoaDonThueSach hdts : listHSTS) {
            if (hdts.getMaNguoiDung().contains(hdth.getMaNguoiDung())) {
                txtNgayThue.setText(String.valueOf(hdts.getNgayThue()));
                break;
            }
        }
        Main.listND = DAOND.selectAll();
        for (NguoiDung nd : Main.listND) {
            if (nd.getMaNguoiDung().contains(txtMaNguoiDung1.getText())) {
                lblTenNguoiDungTS.setText(nd.getHoTen());
                break;
            }
        }
        for (HoaDonThueSach hdts : listHSTS) {
            if (hdts.getTenDangNhap().contains(txtTenDangNhap.getText())) {
                lblMuaGiaTS.setText(hdts.getMaGiaThue());
                lblThoiLuongTS.setText(String.valueOf(hdts.getThoiGian()));
                break;
            }
        }
    }

    public void setFormThueSach(HoaDonThueSach hdts1) {
        List<TaiKhoan> listTK = new ArrayList<>();
        TaiKhoanDAO DAOTK = new TaiKhoanDAO();
        txtTenDangNhap.setText(hdts1.getTenDangNhap());
        txtMaNguoiDung1.setText(hdts1.getMaNguoiDung());
        Calendar1.setDate(hdts1.getNgayThue());
        lblMaNguoiDungTS.setText(txtMaNguoiDung1.getText());
        lblTenDangNhapTS.setText(txtTenDangNhap.getText());
        lblNgayThueTS.setText(String.valueOf(Calendar1.getDate()));
        listHSTS = DAOTS.selectAll();
        listTK = DAOTK.selectAll();
        for (HoaDonThueSach hdts : listHSTS) {
            if (hdts.getMaNguoiDung().contains(hdts1.getMaNguoiDung())) {
                txtNgayThue.setText(String.valueOf(hdts.getNgayThue()));
                break;
            }
        }
        Main.listND = DAOND.selectAll();
        for (NguoiDung nd : Main.listND) {
            if (nd.getMaNguoiDung().contains(txtMaNguoiDung1.getText())) {
                lblTenNguoiDungTS.setText(nd.getHoTen());
                break;
            }
        }
        for (HoaDonThueSach hdts : listHSTS) {
            if (hdts.getTenDangNhap().contains(txtTenDangNhap.getText())) {
                lblMuaGiaTS.setText(hdts.getMaGiaThue());
                lblThoiLuongTS.setText(String.valueOf(hdts.getThoiGian()));
                break;
            }
        }
    }

    public void fillTableHDCT() {
        DefaultTableModel model;
        ThucUongDAO DAOTU = new ThucUongDAO();
        model = (DefaultTableModel) tblHDCT.getModel();
        model.setRowCount(0);
        try {
            listHDCT = DAOCT.selectAllIdHD(txtMaHoaDon.getText());
            for (HoaDonChiTiet hdct : listHDCT) {
                Object[] row = {hdct.getMaHoaDon(), DAOTU.findById(hdct.getMaThucUong()).getTenThucUong(), hdct.getSoLuong(), hdct.getMaHoaDonChiTiet()};
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    public void fillComBoxBangGiaThue() {
        BangGiaThueDAO DAOBGT = new BangGiaThueDAO();
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboBangGiaThue.getModel();
        model.removeAllElements();
        List<BangGiaThue> list = DAOBGT.selectAll();
        for (BangGiaThue bgt : list) {
            model.addElement(bgt.getMaGiaThue());
        }
    }

    public void ThemThucUong() {
        if (UtilityHelper.checkNullText(lblMaHoaDon, txtMaHoaDon) && UtilityHelper.checkNullText(lblMaND, txtMaNguoiDung) && UtilityHelper.checkNullText(lblNgayMua, txtNgayMua)) {
            listHDTU = DAOHDTU.selectAll();
            try {

                int soLuong = Integer.parseInt(txtSoLuong.getText());

                int index = tblThucUongChon.getSelectedRow();
                String maThucUong = (String) tblThucUongChon.getValueAt(index, 0);
                for (HoaDonThucUong hdtu : listHDTU) {
                    if (hdtu.getMaHoaDon().contains(txtMaHoaDon.getText())) {
                        DAOCT.insert(new HoaDonChiTiet(txtMaHoaDon.getText(), maThucUong, soLuong));
                        return;
                    }
                }
                DAOHDTU.insert(new HoaDonThucUong(txtMaHoaDon.getText(), txtMaNguoiDung.getText(), Calendar.getDate()));
                if (UtilityHelper.checkNullText(lblSoLuong, txtSoLuong)) {
                    DAOCT.insert(new HoaDonChiTiet(txtMaHoaDon.getText(), maThucUong, soLuong));
                }
            } catch (Exception e) {
                DialogHelper.alert(this, "Hãy Chọn số Lượng");
            }

        }

    }

    public void fillTableNguoiDung() {
        DefaultTableModel model;
        model = (DefaultTableModel) tblChonNguoiDung.getModel();
        model.setRowCount(0);
        try {
            listND = DAOND.selectAll();
            for (NguoiDung nd : listND) {
                Object[] row = {nd.getMaNguoiDung(), nd.getHoTen(), nd.isGioiTinh() ? "Nam" : "Nữ"};
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    public void fillTableTU() {
        List<ThucUong> listTU = new ArrayList<>();
        ThucUongDAO DAOTU = new ThucUongDAO();
        DefaultTableModel model;
        model = (DefaultTableModel) tblThucUongChon.getModel();
        model.setRowCount(0);
        try {
            listTU = DAOTU.selectAll();
            for (ThucUong tu : listTU) {
                Object[] row = {tu.getMaThucUong(), tu.getTenThucUong(), tu.getGia()};
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    private int x;
    private int y;

    public void initMoving(JDialog DiaLog, JPanel panel) {
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
                DiaLog.setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlMainDialog = new com.ebooks.Compoment.PanelRadius();
        pnlExit1 = new com.ebooks.Compoment.PanelRound();
        lblExit1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        TabThongTinHoaDon = new com.ebooks.Compoment.MaterialTabbed();
        panelRadius5 = new com.ebooks.Compoment.PanelRadius();
        jLabel32 = new javax.swing.JLabel();
        lblMaNguoiDung = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        lblTenNguoiDung = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        lblTongTienThueSach = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel40 = new javax.swing.JLabel();
        lblTongTienThucUong = new javax.swing.JLabel();
        myButton15 = new com.ebooks.Compoment.MyButton();
        panelRadius1 = new com.ebooks.Compoment.PanelRadius();
        lblMaHoaDon = new javax.swing.JLabel();
        txtMaNguoiDung = new javax.swing.JTextField();
        lblMaND = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        tabHDTU = new com.ebooks.Compoment.MaterialTabbed();
        panelRadius3 = new com.ebooks.Compoment.PanelRadius();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHDCT = new com.ebooks.Compoment.Table();
        btnXoaHDCT = new com.ebooks.Compoment.MyButton();
        panelRadius4 = new com.ebooks.Compoment.PanelRadius();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThucUongChon = new com.ebooks.Compoment.Table();
        btnThemThucUong = new com.ebooks.Compoment.MyButton();
        panelRadius6 = new com.ebooks.Compoment.PanelRadius();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblChonNguoiDung = new com.ebooks.Compoment.Table();
        btnChonNguoiDung = new com.ebooks.Compoment.MyButton();
        Calendar = new com.toedter.calendar.JCalendar();
        txtNgayMua = new javax.swing.JTextField();
        lblNgayMua = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        lblSoLuong = new javax.swing.JLabel();
        btnLuuThongTin = new com.ebooks.Compoment.MyButton();
        btnIconCld = new com.ebooks.Compoment.MyButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        lblTenND = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblMaHDThucUong = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        panelRadius2 = new com.ebooks.Compoment.PanelRadius();
        txtMaNguoiDung1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtTenDangNhap = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtNgayThue = new javax.swing.JTextField();
        Calendar1 = new com.toedter.calendar.JCalendar();
        btnIconCld1 = new com.ebooks.Compoment.MyButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cboGioThue = new javax.swing.JComboBox<>();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblMuaGiaTS = new javax.swing.JLabel();
        lblThoiLuongTS = new javax.swing.JLabel();
        lblNgayThueTS = new javax.swing.JLabel();
        lblTenDangNhapTS = new javax.swing.JLabel();
        lblTenNguoiDungTS = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        lblMaNguoiDungTS = new javax.swing.JLabel();
        btnLuuThongTinTS = new com.ebooks.Compoment.MyButton();
        cboBangGiaThue = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlMainDialog.setBackground(new java.awt.Color(255, 255, 255));
        pnlMainDialog.setRadius(25);
        pnlMainDialog.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlExit1.setBackground(new java.awt.Color(253, 127, 127));
        pnlExit1.setRoundBottomLeft(25);
        pnlExit1.setRoundTopRight(25);
        pnlExit1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlExit1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlExit1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlExit1MouseExited(evt);
            }
        });
        pnlExit1.setLayout(new java.awt.GridBagLayout());

        lblExit1.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        lblExit1.setForeground(new java.awt.Color(255, 255, 255));
        lblExit1.setText("X");
        lblExit1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExit1MouseClicked(evt);
            }
        });
        pnlExit1.add(lblExit1, new java.awt.GridBagConstraints());

        pnlMainDialog.add(pnlExit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 0, 50, 50));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Image/nerds-removebg-preview.png"))); // NOI18N
        pnlMainDialog.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        TabThongTinHoaDon.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                TabThongTinHoaDonStateChanged(evt);
            }
        });

        panelRadius5.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel32.setText("Mã Người Dùng");
        panelRadius5.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        lblMaNguoiDung.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblMaNguoiDung.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMaNguoiDung.setText("...");
        lblMaNguoiDung.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblMaNguoiDung.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        panelRadius5.add(lblMaNguoiDung, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 30, 130, -1));

        jLabel34.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel34.setText("Tên Người Dùng :");
        panelRadius5.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        lblTenNguoiDung.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblTenNguoiDung.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTenNguoiDung.setText("...");
        lblTenNguoiDung.setFocusable(false);
        lblTenNguoiDung.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lblTenNguoiDung.setInheritsPopupMenu(false);
        panelRadius5.add(lblTenNguoiDung, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 60, 160, 30));

        jLabel36.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel36.setText("Tổng Tiền Thuê Sách");
        panelRadius5.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        lblTongTienThueSach.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblTongTienThueSach.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTongTienThueSach.setText("...");
        lblTongTienThueSach.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblTongTienThueSach.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        panelRadius5.add(lblTongTienThueSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 100, 130, 30));

        jLabel38.setFont(new java.awt.Font("Inter Medium", 0, 18)); // NOI18N
        jLabel38.setText("Tổng Thu");
        panelRadius5.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        lblTongTien.setFont(new java.awt.Font("Inter Medium", 0, 18)); // NOI18N
        lblTongTien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTongTien.setText("...");
        lblTongTien.setFocusable(false);
        lblTongTien.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lblTongTien.setInheritsPopupMenu(false);
        panelRadius5.add(lblTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 220, 110, 30));
        panelRadius5.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 790, 10));

        jLabel40.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel40.setText("Tổng Tiền Thức Uống");
        panelRadius5.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        lblTongTienThucUong.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblTongTienThucUong.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTongTienThucUong.setText("...");
        lblTongTienThucUong.setFocusable(false);
        lblTongTienThucUong.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lblTongTienThucUong.setInheritsPopupMenu(false);
        panelRadius5.add(lblTongTienThucUong, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 140, 80, 30));

        myButton15.setBackground(new java.awt.Color(87, 190, 110));
        myButton15.setForeground(new java.awt.Color(255, 255, 255));
        myButton15.setText("Thanh Toán");
        myButton15.setBoderColor(new java.awt.Color(87, 190, 110));
        myButton15.setFont(new java.awt.Font("Inter SemiBold", 0, 18)); // NOI18N
        myButton15.setRadius(10);
        myButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton15ActionPerformed(evt);
            }
        });
        panelRadius5.add(myButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 330, 330, 60));

        TabThongTinHoaDon.addTab("Hóa Đơn Tổng Hợp", panelRadius5);

        panelRadius1.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMaHoaDon.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblMaHoaDon.setText("Mã Hóa Đơn");
        panelRadius1.add(lblMaHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        txtMaNguoiDung.setBackground(new java.awt.Color(222, 247, 227));
        txtMaNguoiDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNguoiDungActionPerformed(evt);
            }
        });
        panelRadius1.add(txtMaNguoiDung, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 230, 40));

        lblMaND.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblMaND.setText("Mã Người Dùng");
        panelRadius1.add(lblMaND, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, -1, -1));

        txtMaHoaDon.setBackground(new java.awt.Color(222, 247, 227));
        txtMaHoaDon.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMaHoaDonFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMaHoaDonFocusLost(evt);
            }
        });
        txtMaHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHoaDonActionPerformed(evt);
            }
        });
        panelRadius1.add(txtMaHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 230, 40));

        tabHDTU.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabHDTUStateChanged(evt);
            }
        });

        panelRadius3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Hóa Đơn", "Tên Thức Uống", "Số Lượng", "Mã HDCT"
            }
        ));
        tblHDCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHDCTMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHDCT);

        panelRadius3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 460, 190));

        btnXoaHDCT.setBackground(new java.awt.Color(255, 102, 102));
        btnXoaHDCT.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaHDCT.setText("Xóa");
        btnXoaHDCT.setBoderColor(new java.awt.Color(255, 102, 102));
        btnXoaHDCT.setFont(new java.awt.Font("Inter SemiBold", 0, 14)); // NOI18N
        btnXoaHDCT.setRadius(10);
        btnXoaHDCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaHDCTActionPerformed(evt);
            }
        });
        panelRadius3.add(btnXoaHDCT, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 210, 100, 40));

        tabHDTU.addTab("Hóa Đơn Chi Tiêt", panelRadius3);

        panelRadius4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblThucUongChon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã Thức Uống", "Tên Thức Uống", "Giá"
            }
        ));
        jScrollPane1.setViewportView(tblThucUongChon);

        panelRadius4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 460, 190));

        btnThemThucUong.setBackground(new java.awt.Color(87, 190, 110));
        btnThemThucUong.setForeground(new java.awt.Color(255, 255, 255));
        btnThemThucUong.setText("Thêm Thức Uống");
        btnThemThucUong.setBoderColor(new java.awt.Color(87, 190, 110));
        btnThemThucUong.setFont(new java.awt.Font("Inter SemiBold", 0, 14)); // NOI18N
        btnThemThucUong.setRadius(10);
        btnThemThucUong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThucUongActionPerformed(evt);
            }
        });
        panelRadius4.add(btnThemThucUong, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 170, 40));

        tabHDTU.addTab("Thức Uống Chọn", panelRadius4);

        panelRadius6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblChonNguoiDung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã Người Dùng", "Tên Người Dùng", "Giới Tính"
            }
        ));
        jScrollPane3.setViewportView(tblChonNguoiDung);

        panelRadius6.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 460, 190));

        btnChonNguoiDung.setBackground(new java.awt.Color(87, 190, 110));
        btnChonNguoiDung.setForeground(new java.awt.Color(255, 255, 255));
        btnChonNguoiDung.setText("Chọn Người Dùng");
        btnChonNguoiDung.setBoderColor(new java.awt.Color(87, 190, 110));
        btnChonNguoiDung.setFont(new java.awt.Font("Inter SemiBold", 0, 14)); // NOI18N
        btnChonNguoiDung.setRadius(10);
        btnChonNguoiDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonNguoiDungActionPerformed(evt);
            }
        });
        panelRadius6.add(btnChonNguoiDung, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 170, 40));

        tabHDTU.addTab("Người Dùng", panelRadius6);

        panelRadius1.add(tabHDTU, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 480, 310));

        Calendar.setBackground(new java.awt.Color(201, 235, 201));
        Calendar.setDecorationBackgroundColor(new java.awt.Color(153, 255, 153));
        Calendar.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        Calendar.setWeekdayForeground(new java.awt.Color(51, 51, 51));
        Calendar.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                CalendarAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        Calendar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CalendarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CalendarMouseExited(evt);
            }
        });
        Calendar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                CalendarPropertyChange(evt);
            }
        });
        panelRadius1.add(Calendar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, -1, -1));

        txtNgayMua.setBackground(new java.awt.Color(222, 247, 227));
        txtNgayMua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayMuaActionPerformed(evt);
            }
        });
        panelRadius1.add(txtNgayMua, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, 230, 40));

        lblNgayMua.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblNgayMua.setText("Ngày Mua");
        panelRadius1.add(lblNgayMua, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, -1, -1));

        txtSoLuong.setBackground(new java.awt.Color(222, 247, 227));
        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });
        panelRadius1.add(txtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 170, 280, 40));

        lblSoLuong.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblSoLuong.setText("Số Lượng");
        panelRadius1.add(lblSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, -1, -1));

        btnLuuThongTin.setBackground(new java.awt.Color(87, 190, 110));
        btnLuuThongTin.setForeground(new java.awt.Color(255, 255, 255));
        btnLuuThongTin.setText("Lưu Thông Tin");
        btnLuuThongTin.setBoderColor(new java.awt.Color(87, 190, 110));
        btnLuuThongTin.setFont(new java.awt.Font("Inter SemiBold", 0, 14)); // NOI18N
        btnLuuThongTin.setRadius(10);
        btnLuuThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuThongTinActionPerformed(evt);
            }
        });
        panelRadius1.add(btnLuuThongTin, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 350, 280, 50));

        btnIconCld.setBackground(new java.awt.Color(87, 190, 110));
        btnIconCld.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/calendar.png"))); // NOI18N
        btnIconCld.setAutoscrolls(true);
        btnIconCld.setBoderColor(new java.awt.Color(204, 204, 204));
        btnIconCld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIconCldActionPerformed(evt);
            }
        });
        panelRadius1.add(btnIconCld, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 50, 55, 40));
        panelRadius1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 320, 280, 10));

        jLabel1.setFont(new java.awt.Font("Inter Medium", 0, 12)); // NOI18N
        jLabel1.setText("Tên Người Dùng :");
        panelRadius1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 280, -1, -1));

        lblTenND.setFont(new java.awt.Font("Inter Medium", 0, 12)); // NOI18N
        lblTenND.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTenND.setText("Nguyễn Văn A");
        lblTenND.setFocusable(false);
        lblTenND.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lblTenND.setInheritsPopupMenu(false);
        panelRadius1.add(lblTenND, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 270, 160, 30));

        jLabel5.setFont(new java.awt.Font("Inter Medium", 0, 12)); // NOI18N
        jLabel5.setText("Mã Hóa Đơn");
        panelRadius1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 250, -1, -1));

        lblMaHDThucUong.setFont(new java.awt.Font("Inter Medium", 0, 12)); // NOI18N
        lblMaHDThucUong.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMaHDThucUong.setText("HD001");
        lblMaHDThucUong.setFocusable(false);
        lblMaHDThucUong.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lblMaHDThucUong.setInheritsPopupMenu(false);
        panelRadius1.add(lblMaHDThucUong, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 240, 160, 30));
        panelRadius1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, 280, 10));

        TabThongTinHoaDon.addTab("Thức Uống", panelRadius1);

        panelRadius2.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtMaNguoiDung1.setBackground(new java.awt.Color(222, 247, 227));
        txtMaNguoiDung1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMaNguoiDung1FocusLost(evt);
            }
        });
        txtMaNguoiDung1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNguoiDung1ActionPerformed(evt);
            }
        });
        panelRadius2.add(txtMaNguoiDung1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 230, 40));

        jLabel12.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel12.setText("Thời Gian Thuê");
        panelRadius2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        txtTenDangNhap.setBackground(new java.awt.Color(222, 247, 227));
        txtTenDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenDangNhapActionPerformed(evt);
            }
        });
        panelRadius2.add(txtTenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 230, 40));

        jLabel15.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel15.setText("Tên Đăng Nhập");
        panelRadius2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, -1));

        txtNgayThue.setBackground(new java.awt.Color(222, 247, 227));
        txtNgayThue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayThueActionPerformed(evt);
            }
        });
        panelRadius2.add(txtNgayThue, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, 170, 40));

        Calendar1.setBackground(new java.awt.Color(201, 235, 201));
        Calendar1.setDecorationBackgroundColor(new java.awt.Color(153, 255, 153));
        Calendar1.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        Calendar1.setWeekdayForeground(new java.awt.Color(51, 51, 51));
        Calendar1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                Calendar1AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        Calendar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Calendar1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Calendar1MouseExited(evt);
            }
        });
        Calendar1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                Calendar1PropertyChange(evt);
            }
        });
        panelRadius2.add(Calendar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, -1, -1));

        btnIconCld1.setBackground(new java.awt.Color(87, 190, 110));
        btnIconCld1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/calendar.png"))); // NOI18N
        btnIconCld1.setAutoscrolls(true);
        btnIconCld1.setBoderColor(new java.awt.Color(204, 204, 204));
        btnIconCld1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIconCld1ActionPerformed(evt);
            }
        });
        panelRadius2.add(btnIconCld1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 50, 55, 40));

        jLabel16.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel16.setText("Ngày Thuê");
        panelRadius2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, -1, -1));

        jLabel17.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel17.setText("Mã Người Dùng");
        panelRadius2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        cboGioThue.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01:00:00", "01:30:00", "02:00:00", "02:30:00", "03:00:00", "03:30:00", "04:00:00", "04:30:00", "05:00:00", "05:30:00", "06:00:00", "06:30:00", "07:00:00" }));
        cboGioThue.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboGioThueItemStateChanged(evt);
            }
        });
        panelRadius2.add(cboGioThue, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 260, 40));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        panelRadius2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, 10, 270));

        jLabel8.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel8.setText("Thời Lượng");
        panelRadius2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 280, -1, -1));

        jLabel9.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel9.setText("Mã Người Dùng");
        panelRadius2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 120, -1, -1));

        jLabel18.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel18.setText("Tên Người Dùng");
        panelRadius2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 160, -1, -1));

        jLabel20.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel20.setText("Tên Đăng Nhập");
        panelRadius2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, -1, -1));

        jLabel21.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel21.setText("Ngày Thuê");
        panelRadius2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 240, -1, -1));

        lblMuaGiaTS.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblMuaGiaTS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMuaGiaTS.setText("...");
        lblMuaGiaTS.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        panelRadius2.add(lblMuaGiaTS, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 320, 170, -1));

        lblThoiLuongTS.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblThoiLuongTS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblThoiLuongTS.setText("...");
        lblThoiLuongTS.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        panelRadius2.add(lblThoiLuongTS, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 280, 120, -1));

        lblNgayThueTS.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblNgayThueTS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNgayThueTS.setText("...");
        lblNgayThueTS.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        panelRadius2.add(lblNgayThueTS, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 240, 270, -1));

        lblTenDangNhapTS.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblTenDangNhapTS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTenDangNhapTS.setText("...");
        lblTenDangNhapTS.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        panelRadius2.add(lblTenDangNhapTS, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, 220, -1));

        lblTenNguoiDungTS.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblTenNguoiDungTS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTenNguoiDungTS.setText("...");
        lblTenNguoiDungTS.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        panelRadius2.add(lblTenNguoiDungTS, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 160, 250, -1));

        jLabel29.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel29.setText("Mức Gia Áp Dụng");
        panelRadius2.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 320, -1, -1));

        lblMaNguoiDungTS.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblMaNguoiDungTS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMaNguoiDungTS.setText("...");
        lblMaNguoiDungTS.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblMaNguoiDungTS.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        panelRadius2.add(lblMaNguoiDungTS, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 120, 130, -1));

        btnLuuThongTinTS.setBackground(new java.awt.Color(87, 190, 110));
        btnLuuThongTinTS.setForeground(new java.awt.Color(255, 255, 255));
        btnLuuThongTinTS.setText("Lưu Thông Tin");
        btnLuuThongTinTS.setBoderColor(new java.awt.Color(87, 190, 110));
        btnLuuThongTinTS.setFont(new java.awt.Font("Inter SemiBold", 0, 14)); // NOI18N
        btnLuuThongTinTS.setRadius(10);
        btnLuuThongTinTS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuThongTinTSActionPerformed(evt);
            }
        });
        panelRadius2.add(btnLuuThongTinTS, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 260, 50));

        cboBangGiaThue.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboBangGiaThue.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboBangGiaThueItemStateChanged(evt);
            }
        });
        panelRadius2.add(cboBangGiaThue, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 260, 40));

        jLabel31.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel31.setText("Bảng Giá Thuê");
        panelRadius2.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        TabThongTinHoaDon.addTab("Thuê Sách", panelRadius2);

        pnlMainDialog.add(TabThongTinHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 840, 460));

        jLabel42.setFont(new java.awt.Font("Inter ExtraBold", 0, 26)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(55, 149, 76));
        jLabel42.setText("Thông Tin Hóa Đơn");
        pnlMainDialog.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 250, -1));

        getContentPane().add(pnlMainDialog, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 570));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblExit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExit1MouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_lblExit1MouseClicked

    private void pnlExit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlExit1MouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_pnlExit1MouseClicked

    private void pnlExit1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlExit1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlExit1MouseEntered

    private void pnlExit1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlExit1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlExit1MouseExited

    private void txtMaHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHoaDonActionPerformed

    private void txtMaNguoiDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNguoiDungActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNguoiDungActionPerformed

    private void txtNgayMuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayMuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayMuaActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void btnIconCldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIconCldActionPerformed
        Calendar.setVisible(congTac);
        congTac = !congTac;
    }//GEN-LAST:event_btnIconCldActionPerformed

    private void btnXoaHDCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaHDCTActionPerformed
        int index = tblHDCT.getSelectedRow();
        if (index != -1) {
            try {
                String maHDCT = (String) tblHDCT.getValueAt(index, 3);
                DAOCT.delete(maHDCT);
                fillTableHDCT();
            } catch (Exception e) {
                DialogHelper.alert(this, "Xóa Hóa Đơn Chi Tiết Thất Bại");
            }

        } else {
            DialogHelper.alert(this, "Hãy Chọn Hóa Đơn Chi Tiết");
        }
    }//GEN-LAST:event_btnXoaHDCTActionPerformed

    private void CalendarAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_CalendarAncestorAdded

    }//GEN-LAST:event_CalendarAncestorAdded

    private void CalendarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_CalendarPropertyChange
        SimpleDateFormat formats = new SimpleDateFormat("dd/MM/yyyy");
        txtNgayMua.setText(String.valueOf(formats.format(Calendar.getDate())));
    }//GEN-LAST:event_CalendarPropertyChange

    private void CalendarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CalendarMouseExited

    }//GEN-LAST:event_CalendarMouseExited

    private void CalendarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CalendarMouseEntered
        //        Calendar.setVisible(false);

    }//GEN-LAST:event_CalendarMouseEntered

    private void txtMaNguoiDung1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNguoiDung1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNguoiDung1ActionPerformed

    private void txtTenDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenDangNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenDangNhapActionPerformed

    private void txtNgayThueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayThueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayThueActionPerformed

    private void Calendar1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_Calendar1AncestorAdded

    }//GEN-LAST:event_Calendar1AncestorAdded

    private void Calendar1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Calendar1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_Calendar1MouseEntered

    private void Calendar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Calendar1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_Calendar1MouseExited

    private void Calendar1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_Calendar1PropertyChange
        SimpleDateFormat formats = new SimpleDateFormat("dd/MM/yyyy");
        txtNgayThue.setText(String.valueOf(formats.format(Calendar1.getDate())));
        lblNgayThueTS.setText(String.valueOf(Calendar1.getDate()));
    }//GEN-LAST:event_Calendar1PropertyChange

    private void btnIconCld1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIconCld1ActionPerformed
        Calendar1.setVisible(congTac1);
        congTac1 = !congTac1;
    }//GEN-LAST:event_btnIconCld1ActionPerformed

    private void myButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton15ActionPerformed

    }//GEN-LAST:event_myButton15ActionPerformed

    private void tblHDCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDCTMouseClicked
        int index = tblHDCT.getSelectedRow();
        if (index != -1) {
            txtSoLuong.setText(String.valueOf(tblHDCT.getValueAt(index, 2)));
        }

    }//GEN-LAST:event_tblHDCTMouseClicked

    private void btnThemThucUongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThucUongActionPerformed
        boolean kq = DialogHelper.confirm(this, "Thêm Thức Uống Này ?");
        if (kq) {
            ThemThucUong();
            fillTableHDCT();
            tabHDTU.setSelectedIndex(0);
            lblMaHDThucUong.setText(txtMaHoaDon.getText());
            txtMaHoaDon.setEnabled(false);
        }
        this.dispose();
        this.setVisible(true);

    }//GEN-LAST:event_btnThemThucUongActionPerformed

    private void btnChonNguoiDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonNguoiDungActionPerformed
        int index = tblChonNguoiDung.getSelectedRow();
        if (index != -1) {
            boolean kq = DialogHelper.confirm(this, "Chọn Người Dùng Này ?");
            if (kq) {
                txtMaNguoiDung.setText(String.valueOf(tblChonNguoiDung.getValueAt(index, 0)));
                lblTenND.setText(String.valueOf(tblChonNguoiDung.getValueAt(index, 1)));
            }
        } else {
            DialogHelper.alert(this, "Hãy Chọn Người Dùng");
        }
    }//GEN-LAST:event_btnChonNguoiDungActionPerformed

    private void txtMaHoaDonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaHoaDonFocusLost
        listHDTU = DAOHDTU.selectAll();
        if (txtMaHoaDon.getText() != null || !txtMaHoaDon.equals("")) {
            for (HoaDonThucUong hdtu : listHDTU) {
                if (hdtu.getMaHoaDon().equals(txtMaHoaDon.getText())) {
                    DialogHelper.alert(this, "Mã Hóa Đơn Đã Tồn Tại");
                    txtMaHoaDon.setText(null);
                    return;
                }
            }
            txtMaHoaDon.setEditable(false);
        } else {
            DialogHelper.alert(this, "Không Để Trống Mã Hóa Đơn");
        }

    }//GEN-LAST:event_txtMaHoaDonFocusLost

    private void btnLuuThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuThongTinActionPerformed
        try {
            HoaDonThucUong hdtu = DAOHDTU.findById(txtMaHoaDon.getText());
            hdtu.setTrangThai(true);
            hdtu.setNgayMua(Calendar.getDate());
            DAOHDTU.update(hdtu);
            DialogHelper.alert(this, "Đơn Thành Công");
        } catch (Exception e) {
            DialogHelper.alert(this, "Đơn Thành Thất Bại");
        }

    }//GEN-LAST:event_btnLuuThongTinActionPerformed

    private void txtMaHoaDonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaHoaDonFocusGained
        txtMaHoaDon.setEditable(true);
    }//GEN-LAST:event_txtMaHoaDonFocusGained

    private void btnLuuThongTinTSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuThongTinTSActionPerformed
        int timThay = 0;
        try {
            listHSTS = DAOTS.selectAll();
            for (HoaDonThueSach hdts : listHSTS) {
                if (hdts.getMaNguoiDung().equalsIgnoreCase(txtMaNguoiDung1.getText())) {
                    if (DialogHelper.confirm(this, "Bạn Có Muốn Cập Nhật")) {
                        UpdateHDTS();
                        DialogHelper.alert(this, "Cập Nhật Hóa Đơn Thành Công");
                        timThay = 1;
                        break;
                    }
                    
                }
            }
            if (timThay == 0) {
                InsertHDTS();
                DialogHelper.alert(this, "Thêm Hóa Đơn Thành Công");
            }

        } catch (Exception e) {
            DialogHelper.alert(this, "Lưu Thông Tin Thất Bại");
        }

    }//GEN-LAST:event_btnLuuThongTinTSActionPerformed

    private void txtMaNguoiDung1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaNguoiDung1FocusLost
        TaiKhoanDAO DAOTK = new TaiKhoanDAO();
        List<TaiKhoan> listTK = new ArrayList<>();
        listTK = DAOTK.selectAll();

        Main.listND = DAOND.selectAll();
        for (NguoiDung nd : Main.listND) {
            if (nd.getMaNguoiDung().equalsIgnoreCase(txtMaNguoiDung1.getText())) {
                lblTenNguoiDungTS.setText(nd.getHoTen());
                lblMaNguoiDungTS.setText(nd.getMaNguoiDung());
                break;
            }
        }

        for (TaiKhoan tk : listTK) {
            if (tk.getMaNguoiDung().equalsIgnoreCase(txtMaNguoiDung1.getText())) {
                lblTenDangNhapTS.setText(tk.getTenDangNhap());
                txtTenDangNhap.setText(tk.getTenDangNhap());
                break;
            }
            txtTenDangNhap.setText("...");
        }
    }//GEN-LAST:event_txtMaNguoiDung1FocusLost

    private void cboGioThueItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboGioThueItemStateChanged
        lblThoiLuongTS.setText(String.valueOf(cboGioThue.getSelectedItem()));
    }//GEN-LAST:event_cboGioThueItemStateChanged

    private void cboBangGiaThueItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboBangGiaThueItemStateChanged
        lblMuaGiaTS.setText(String.valueOf(cboBangGiaThue.getSelectedItem()));
    }//GEN-LAST:event_cboBangGiaThueItemStateChanged

    private void TabThongTinHoaDonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_TabThongTinHoaDonStateChanged
       int index = TabThongTinHoaDon.getSelectedIndex();
       if(index == -1){
           return;
       }else if(index == 1){
           fillTableHDCT();
       }else {
           fillComBoxBangGiaThue();
            lblNgayThueTS.setText(String.valueOf(Calendar1.getDate()));
            lblMuaGiaTS.setText(String.valueOf(cboBangGiaThue.getSelectedItem()));
            lblThoiLuongTS.setText(String.valueOf(cboGioThue.getSelectedItem()));
       }
    }//GEN-LAST:event_TabThongTinHoaDonStateChanged

    private void tabHDTUStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabHDTUStateChanged
       int index = tabHDTU.getSelectedIndex();
       if(index == -1){
           return;
       }else if(index == 0){
           fillTableHDCT();
       }else if(index == 1){
           fillTableTU();
       }else {
           fillTableNguoiDung();
       }
    }//GEN-LAST:event_tabHDTUStateChanged

    /*tbdSetting args the command line arguments
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
            java.util.logging.Logger.getLogger(ReceiptDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReceiptDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReceiptDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReceiptDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ReceiptDiaLog dialog = new ReceiptDiaLog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JCalendar Calendar;
    private com.toedter.calendar.JCalendar Calendar1;
    private com.ebooks.Compoment.MaterialTabbed TabThongTinHoaDon;
    private com.ebooks.Compoment.MyButton btnChonNguoiDung;
    private com.ebooks.Compoment.MyButton btnIconCld;
    private com.ebooks.Compoment.MyButton btnIconCld1;
    private com.ebooks.Compoment.MyButton btnLuuThongTin;
    private com.ebooks.Compoment.MyButton btnLuuThongTinTS;
    private com.ebooks.Compoment.MyButton btnThemThucUong;
    private com.ebooks.Compoment.MyButton btnXoaHDCT;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboBangGiaThue;
    private javax.swing.JComboBox<String> cboGioThue;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel lblExit1;
    private javax.swing.JLabel lblMaHDThucUong;
    private javax.swing.JLabel lblMaHoaDon;
    private javax.swing.JLabel lblMaND;
    private javax.swing.JLabel lblMaNguoiDung;
    private javax.swing.JLabel lblMaNguoiDungTS;
    private javax.swing.JLabel lblMuaGiaTS;
    private javax.swing.JLabel lblNgayMua;
    private javax.swing.JLabel lblNgayThueTS;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblTenDangNhapTS;
    private javax.swing.JLabel lblTenND;
    private javax.swing.JLabel lblTenNguoiDung;
    private javax.swing.JLabel lblTenNguoiDungTS;
    private javax.swing.JLabel lblThoiLuongTS;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblTongTienThucUong;
    private javax.swing.JLabel lblTongTienThueSach;
    private com.ebooks.Compoment.MyButton myButton15;
    private com.ebooks.Compoment.PanelRadius panelRadius1;
    private com.ebooks.Compoment.PanelRadius panelRadius2;
    private com.ebooks.Compoment.PanelRadius panelRadius3;
    private com.ebooks.Compoment.PanelRadius panelRadius4;
    private com.ebooks.Compoment.PanelRadius panelRadius5;
    private com.ebooks.Compoment.PanelRadius panelRadius6;
    private com.ebooks.Compoment.PanelRound pnlExit1;
    private com.ebooks.Compoment.PanelRadius pnlMainDialog;
    private com.ebooks.Compoment.MaterialTabbed tabHDTU;
    private com.ebooks.Compoment.Table tblChonNguoiDung;
    private com.ebooks.Compoment.Table tblHDCT;
    private com.ebooks.Compoment.Table tblThucUongChon;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtMaNguoiDung;
    private javax.swing.JTextField txtMaNguoiDung1;
    private javax.swing.JTextField txtNgayMua;
    private javax.swing.JTextField txtNgayThue;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenDangNhap;
    // End of variables declaration//GEN-END:variables
}
