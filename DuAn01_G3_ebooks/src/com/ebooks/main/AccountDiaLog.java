/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.ebooks.main;

import com.ebooks.dao.NguoiDungDAO;
import com.ebooks.dao.QuanTriVienDAO;
import com.ebooks.dao.TaiKhoanDAO;
import com.ebooks.dao.ThucUongDAO;
import com.ebooks.helper.DialogHelper;
import static com.ebooks.main.Main.TenDangNhap;
import static com.ebooks.main.Main.listND;
import static com.ebooks.main.Main.tblTable;
import com.ebooks.model.AudioSach;
import com.ebooks.model.NguoiDung;
import com.ebooks.model.QuanTriVien;
import com.ebooks.model.TaiKhoan;
import java.awt.Color;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thinh
 */
public class AccountDiaLog extends javax.swing.JDialog {

    /**
     * Creates new form SettingDiaLog
     */
    private NguoiDungDAO daoND = new NguoiDungDAO();
    private TaiKhoanDAO daoTK = new TaiKhoanDAO();
    QuanTriVienDAO daoQTV = new QuanTriVienDAO();
    List<TaiKhoan> listTK = new ArrayList<>();

    public AccountDiaLog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        init();
    }

    public void init() {
        AppStatus.mainApp.LoadNguoiDung();
        fillTableNDnotAcc();
        if (Main.TenDangNhap != null) {
            initForm();

        }
    }

    public void setFrom(String tenDangNhap) {
        TaiKhoan taiKhoan = daoTK.SeclectTaiKhoan(LogInDiaLog.tendangNhapApp);
        txtMaNguoiDung.setText(taiKhoan.getMaNguoiDung());
        txtHoTen.setText(taiKhoan.getHoten());
        txtMatKhau.setText(taiKhoan.getMatKhau());
        txtTenDangNhap.setText(taiKhoan.getTenDangNhap());
        SprHour.setValue(taiKhoan.getThoiLuong().getHours());
        SprMin.setValue(taiKhoan.getThoiLuong().getMinutes());
        SprSec.setValue(taiKhoan.getThoiLuong().getSeconds());
   
        System.out.println(taiKhoan.getThoiLuong());
        if (taiKhoan.isTrangThai()) {
            rdoQuanTriVien.setSelected(true);
        } else {
            rdoQuanTriVien.setSelected(true);
        }
    }

    public void initForm() {

        tabTaiKhoan.setSelectedIndex(1);
        tabTaiKhoan.setEnabledAt(0, false);
        TaiKhoan taiKhoan = daoTK.SeclectTaiKhoan(Main.TenDangNhap);
        txtMaNguoiDung.setText(taiKhoan.getMaNguoiDung());
        txtHoTen.setText(taiKhoan.getHoten());
        txtMatKhau.setText(taiKhoan.getMatKhau());
        txtTenDangNhap.setText(taiKhoan.getTenDangNhap());
        SprHour.setValue(taiKhoan.getThoiLuong().getHours());
        SprMin.setValue(taiKhoan.getThoiLuong().getMinutes());
        SprSec.setValue(taiKhoan.getThoiLuong().getSeconds());
        txtMaNguoiDung.setEditable(false);
        txtHoTen.setEditable(false);
        txtTenDangNhap.setEditable(false);
        if (taiKhoan.isTrangThai()) {
            rdoQuanTriVien.setSelected(true);
        } else {
            rdoNguoiDung.setSelected(true);
        }

    }

    void insert() {
        if (checkBugs()) {
            TaiKhoan model = getModel();
            if (model == null) {
                return;
            }
            daoTK.insert(model);

            try {

                AppStatus.mainApp.LoadTaiKhoan();
                AppStatus.mainApp.fillTableTaiKhoan(Main.listTK);
                DialogHelper.alert(this, "Thêm tài khoản thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Tài khoản đã tồn tại!");
            }
        }
    }

    void insertQTV() {

        if (rdoQuanTriVien.isSelected() == true) {
            if (checkBugs()) {
                QuanTriVien model = getModelQTV();
                if (model == null) {
                    return;
                }
                try {
                    daoQTV.insert(model);
                    AppStatus.mainApp.LoadTaiKhoan();
                    AppStatus.mainApp.fillTableTaiKhoan(Main.listTK);
                } catch (Exception e) {
                    DialogHelper.alert(this, "Quản trị viên đã tồn tại!");
                }
            }
        } else {
            return;
        }

    }

    QuanTriVien getModelQTV() {

        QuanTriVien model = new QuanTriVien();
        model.setTenDangNhap(txtTenDangNhap.getText());
        return model;
    }

    void update() {
        if (checkBugs()) {
            TaiKhoan model = daoTK.findByIdND(txtMaNguoiDung.getText());
            if (model == null) {
                return;
            }
            try {
                daoTK.update(getModel(model));
                AppStatus.mainApp.LoadTaiKhoan();
                AppStatus.mainApp.fillTableTaiKhoan(Main.listTK);
                DialogHelper.alert(this, "Cập nhật thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Cập nhật thất bại!");
            }
        }
    }

    TaiKhoan getModel() {

        TaiKhoan model = new TaiKhoan();
        model.setTenDangNhap(txtTenDangNhap.getText());
        model.setMatKhau(txtMatKhau.getText());
        model.setMaNguoiDung(txtMaNguoiDung.getText());
        model.setTrangThai(rdoQuanTriVien.isSelected() == true ? true : false);
        String time = SprHour.getValue() + ":" + SprMin.getValue() +":"+ SprSec.getValue();
        
        model.setThoiLuong(Time.valueOf(time));
        return model;
    }

    TaiKhoan getModel(TaiKhoan model) {
        model.setMatKhau(String.valueOf(txtMatKhau.getPassword()));
        model.setTrangThai(rdoQuanTriVien.isSelected() == true ? true : false);
        String time = SprHour.getValue() + ":" + SprMin.getValue() +":"+ SprSec.getValue();
        model.setThoiLuong(Time.valueOf(time));
        return model;
    }

    public void fillTableNDnotAcc() {
        DefaultTableModel model;
        model = (DefaultTableModel) tblNguoiDungV2.getModel();
        model.setRowCount(0);
        try {
            listND = daoND.selectAllNotACC();
            for (NguoiDung nd : listND) {
                Object[] row = {nd.getMaNguoiDung(), nd.getHoTen(), nd.getSoDienThoai(), nd.isGioiTinh() ? "Nam" : "Nữ",nd.getEmail()};
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu");
        }
    }

    public void fillTableNguoiDung(List<NguoiDung> list) {
        tblTable = (DefaultTableModel) tblNguoiDungV2.getModel();
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

    boolean checkBugs() {
        String timeRegex = "^(?:([01]?\\d|2[0-3]):([0-5]?\\d):)?([0-5]?\\d)$";

        if (txtMaNguoiDung.getText().equals("")) {
            DialogHelper.alert(this, "Chưa nhập mã người dùng");
            return false;
        } else if (txtHoTen.getText().equals("")) {
            DialogHelper.alert(this, "Chưa nhập họ tên");
            return false;
        } else if (txtTenDangNhap.getText().equals("")) {
            DialogHelper.alert(this, "Chưa nhập tên đăng nhập");
            return false;
        } else if (txtMatKhau.getText().equals("")) {
            DialogHelper.alert(this, "Chưa nhập mật khẩu");
            return false;
        } 
//        else if (txtThoiLuong.getText().equals("")) {
//            DialogHelper.alert(this, "Chưa nhập thời lượng");
//            return false;
//        } else if (!txtThoiLuong.getText().matches(timeRegex)) {
//            DialogHelper.alert(this, "Thời lượng chưa đúng định dạng!(HH:MM:SS)");
//            return false;
//        }
        return true;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupVaiTroThongTinTK = new javax.swing.ButtonGroup();
        panelRadius2 = new com.ebooks.Compoment.PanelRadius();
        pnlExit1 = new com.ebooks.Compoment.PanelRound();
        lblExit1 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        tabTaiKhoan = new com.ebooks.Compoment.MaterialTabbed();
        panelRadius3 = new com.ebooks.Compoment.PanelRadius();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNguoiDungV2 = new com.ebooks.Compoment.Table();
        jLabel7 = new javax.swing.JLabel();
        btnThemTaiKhoan = new com.ebooks.Compoment.MyButton();
        panelRadius1 = new com.ebooks.Compoment.PanelRadius();
        txtHoTen = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtMaNguoiDung = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTenDangNhap = new javax.swing.JTextField();
        txtMatKhau = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        rdoNguoiDung = new javax.swing.JRadioButton();
        rdoQuanTriVien = new javax.swing.JRadioButton();
        btnLuuTaiKhoan = new com.ebooks.Compoment.MyButton();
        jLabel9 = new javax.swing.JLabel();
        SprHour = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        SprMin = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        SprSec = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRadius2.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius2.setRadius(25);
        panelRadius2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlExit1MousePressed(evt);
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

        panelRadius2.add(pnlExit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 0, 50, 50));

        jLabel18.setFont(new java.awt.Font("Inter ExtraBold", 0, 26)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(35, 98, 49));
        jLabel18.setText("Thông Tin Tài Khoản");
        panelRadius2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, -1, -1));

        tabTaiKhoan.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        panelRadius3.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblNguoiDungV2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã người dùng", "Tên người dùng", "Giới tính", "Số điện thoại", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNguoiDungV2.setSelectionBackground(new java.awt.Color(87, 190, 110));
        tblNguoiDungV2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNguoiDungV2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNguoiDungV2);

        panelRadius3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 740, 300));

        jLabel7.setFont(new java.awt.Font("Inter SemiBold", 0, 18)); // NOI18N
        jLabel7.setText("Bảng Người Dùng");
        panelRadius3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        btnThemTaiKhoan.setBackground(new java.awt.Color(87, 190, 110));
        btnThemTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        btnThemTaiKhoan.setText("Thêm Tài Khoản");
        btnThemTaiKhoan.setBoderColor(new java.awt.Color(87, 190, 110));
        btnThemTaiKhoan.setFont(new java.awt.Font("Inter SemiBold", 1, 12)); // NOI18N
        btnThemTaiKhoan.setRadius(10);
        btnThemTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemTaiKhoanActionPerformed(evt);
            }
        });
        panelRadius3.add(btnThemTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(609, 360, 140, 40));

        tabTaiKhoan.addTab("Bảng Người Dùng", panelRadius3);

        panelRadius1.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtHoTen.setBackground(new java.awt.Color(222, 247, 227));
        txtHoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoTenActionPerformed(evt);
            }
        });
        panelRadius1.add(txtHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, 270, 40));

        jLabel4.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel4.setText("Họ Tên");
        panelRadius1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, -1, -1));

        txtMaNguoiDung.setBackground(new java.awt.Color(222, 247, 227));
        panelRadius1.add(txtMaNguoiDung, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 270, 40));

        jLabel6.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel6.setText("Mã Người  Dùng");
        panelRadius1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        jLabel1.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel1.setText("Mật Khẩu");
        panelRadius1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, -1, -1));

        jLabel2.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel2.setText("Vai Trò");
        panelRadius1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, -1));

        txtTenDangNhap.setBackground(new java.awt.Color(222, 247, 227));
        panelRadius1.add(txtTenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 270, 40));

        txtMatKhau.setBackground(new java.awt.Color(222, 247, 227));
        panelRadius1.add(txtMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 150, 270, 40));

        jLabel3.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel3.setText("Tên Đăng Nhập");
        panelRadius1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

        buttonGroupVaiTroThongTinTK.add(rdoNguoiDung);
        rdoNguoiDung.setText("Người Dùng");
        panelRadius1.add(rdoNguoiDung, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, -1));

        buttonGroupVaiTroThongTinTK.add(rdoQuanTriVien);
        rdoQuanTriVien.setText("Quản Trị Viên");
        panelRadius1.add(rdoQuanTriVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, -1, -1));

        btnLuuTaiKhoan.setBackground(new java.awt.Color(87, 190, 110));
        btnLuuTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        btnLuuTaiKhoan.setText("Lưu Tài Khoản");
        btnLuuTaiKhoan.setBoderColor(new java.awt.Color(87, 190, 110));
        btnLuuTaiKhoan.setFont(new java.awt.Font("Inter SemiBold", 0, 14)); // NOI18N
        btnLuuTaiKhoan.setRadius(10);
        btnLuuTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuTaiKhoanActionPerformed(evt);
            }
        });
        panelRadius1.add(btnLuuTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 260, 50));

        jLabel9.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Hour");
        panelRadius1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 310, 60, 20));

        SprHour.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        panelRadius1.add(SprHour, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 260, 60, 40));

        jLabel10.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel10.setText("Thời lượng");
        panelRadius1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, -1, -1));

        SprMin.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        panelRadius1.add(SprMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 260, 60, 40));

        jLabel11.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Min");
        panelRadius1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 310, 60, -1));

        SprSec.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        panelRadius1.add(SprSec, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 260, 60, 40));

        jLabel12.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Sec");
        panelRadius1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 310, 60, 20));

        tabTaiKhoan.addTab("Form Tài Khoản", panelRadius1);

        panelRadius2.add(tabTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 880, 430));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Image/nerds-removebg-preview.png"))); // NOI18N
        panelRadius2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        getContentPane().add(panelRadius2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 550));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblExit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExit1MouseClicked
        this.dispose();
    }//GEN-LAST:event_lblExit1MouseClicked

    private void pnlExit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlExit1MouseClicked
        this.dispose();
    }//GEN-LAST:event_pnlExit1MouseClicked

    private void pnlExit1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlExit1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlExit1MouseEntered

    private void pnlExit1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlExit1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_pnlExit1MouseExited

    private void pnlExit1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlExit1MousePressed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_pnlExit1MousePressed

    private void tblNguoiDungV2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNguoiDungV2MouseClicked


    }//GEN-LAST:event_tblNguoiDungV2MouseClicked

    private void btnThemTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemTaiKhoanActionPerformed
        int index = tblNguoiDungV2.getSelectedRow();

        if (index == -1) {
            DialogHelper.alert(this, "Chưa chọn người dùng");
            return;
        }

        String maNgDung = tblNguoiDungV2.getValueAt(index, 0).toString();

        tabTaiKhoan.setSelectedIndex(1);
        NguoiDung ngDung = daoND.findById(maNgDung);
        txtMaNguoiDung.setText(ngDung.getMaNguoiDung());
        txtHoTen.setText(ngDung.getHoTen());
        rdoNguoiDung.setSelected(true);

        txtTenDangNhap.setText("");
        txtMatKhau.setText("");
    }//GEN-LAST:event_btnThemTaiKhoanActionPerformed

    private void txtHoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoTenActionPerformed

    private void btnLuuTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuTaiKhoanActionPerformed

        try {
            int timThay = 0;
            listTK = daoTK.selectAll();
            for (TaiKhoan x : listTK) {
                if (x.getTenDangNhap().contains(txtTenDangNhap.getText())) {
                    timThay = 1;
                }
            }
            if (timThay == 0) {
                insert();
                insertQTV();
            } else {
                if (DialogHelper.confirm(this, "Chắc chắn cập nhật?")) {
                    update();
                }
            }
            fillTableNDnotAcc();
        } catch (Exception e) {
        }


    }//GEN-LAST:event_btnLuuTaiKhoanActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AccountDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AccountDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AccountDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AccountDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AccountDiaLog dialog = new AccountDiaLog(new javax.swing.JFrame(), true);
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
    private javax.swing.JSpinner SprHour;
    private javax.swing.JSpinner SprMin;
    private javax.swing.JSpinner SprSec;
    private com.ebooks.Compoment.MyButton btnLuuTaiKhoan;
    private com.ebooks.Compoment.MyButton btnThemTaiKhoan;
    private javax.swing.ButtonGroup buttonGroupVaiTroThongTinTK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblExit1;
    private com.ebooks.Compoment.PanelRadius panelRadius1;
    private com.ebooks.Compoment.PanelRadius panelRadius2;
    private com.ebooks.Compoment.PanelRadius panelRadius3;
    private com.ebooks.Compoment.PanelRound pnlExit1;
    private javax.swing.JRadioButton rdoNguoiDung;
    private javax.swing.JRadioButton rdoQuanTriVien;
    private com.ebooks.Compoment.MaterialTabbed tabTaiKhoan;
    private com.ebooks.Compoment.Table tblNguoiDungV2;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaNguoiDung;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtTenDangNhap;
    // End of variables declaration//GEN-END:variables
}
