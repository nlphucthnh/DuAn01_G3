/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.ebooks.main;

import com.ebooks.dao.LoaiSSDAO;
import com.ebooks.dao.SachDAO;
import com.ebooks.dao.TacGiaDAO;
import com.ebooks.dao.TheLoaiDAO;
import com.ebooks.dao.ThucUongDAO;
import com.ebooks.helper.DialogHelper;
import com.ebooks.helper.ShareHelper;
import com.ebooks.helper.UtilityHelper;
import com.ebooks.model.LoaiSS;
import com.ebooks.model.Sach;
import com.ebooks.model.TacGia;
import com.ebooks.model.TheLoai;
import com.ebooks.model.ThucUong;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.print.attribute.standard.SheetCollate;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author Thinh
 */
public class BooksDiaLog extends javax.swing.JDialog {

    private TacGiaDAO DAOTG = new TacGiaDAO();
    private TheLoaiDAO DAOTL = new TheLoaiDAO();
    private SachDAO DAOS = new SachDAO();
    private LoaiSSDAO DAOLSS = new LoaiSSDAO();
    private List<TheLoai> listTL = new ArrayList<>();
    private List<Sach> listS = new ArrayList<>();
    private String UrlImg = "..\\DuAn01_G3_ebooks\\src\\com\\Content\\imgEbooks\\";
    private String UrlEbook = "..\\DuAn01_G3_ebooks\\src\\com\\Content\\contentEbooks\\";
    private Date now = new Date();
    private JFileChooser fileChooser = new JFileChooser();
    private String NameImg = "41b92ec3eab97e4c24b3f6e8fe75ddec.png";

    public BooksDiaLog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        fillComBoBox();
        setBackground(new Color(0, 0, 0, 0));
        initMoving(this, pnlMainBooks);
        txtDuongDan.setEditable(false);
    }

    public BooksDiaLog(java.awt.Frame parent, boolean modal, Sach sach) {
        super(parent, modal);
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        initMoving(this, pnlMainBooks);
        txtDuongDan.setEditable(false);
        fillComBoBox();
        SetForm(sach);
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

    public void fillComBoBox() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboTheLoai.getModel();
        model.removeAllElements();
        listTL = DAOTL.selectAll();
        for (TheLoai tl : listTL) {
            model.addElement(tl.getTenTheLoai());
        }
    }

    public ImageIcon ShowImg(String nameImg) {
        ImageIcon imgIcon = new ImageIcon(UrlImg + nameImg);
        Image image = imgIcon.getImage();
        Image newimg = image.getScaledInstance(160, 160, java.awt.Image.SCALE_SMOOTH);
        imgIcon = new ImageIcon(newimg);
        return imgIcon;
    }

    public String checkTacGia(String TenTacGia) {
        TacGia tg = DAOTG.findByName(TenTacGia);
        if (tg != null) {
            return tg.getHoTen();
        }
        return null;
    }

    public void SetForm(Sach sach) {
        txtMaSach.setText(sach.getMaSach());
        txtTenSach.setText(sach.getTenSach());
        txtTacGia.setText(DAOTG.findById(sach.getMaTacGia()).getHoTen());
        txtDuongDan.setText(sach.getDuongDan());
        txtMoTa.setText(sach.getMoTa());
        lblSachImg.setIcon(ShowImg(sach.getHinh()));
    }

    public String MovingFile() {
        int x = fileChooser.showDialog(this, "Chon file");
        if (x == JFileChooser.APPROVE_OPTION) {
            try {
                File afile = new File(fileChooser.getSelectedFile().getPath());
                if (afile.renameTo(new File(UrlEbook + fileChooser.getSelectedFile().getName()))) {
                    System.out.println("File is moved successful!");
                    txtDuongDan.setText(fileChooser.getSelectedFile().getName());
                } else {
                    System.out.println("File is failed to move!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fileChooser.getSelectedFile().getName();
    }

    public String SetImg() {
        int x = fileChooser.showDialog(this, "Chon file");
        if (x == JFileChooser.APPROVE_OPTION) {
            try {
                File afile = new File(fileChooser.getSelectedFile().getPath());
                if (afile.renameTo(new File(UrlImg + fileChooser.getSelectedFile().getName()))) {
                    System.out.println("File is moved successful!");
                    ImageIcon imgIcon = new ImageIcon(UrlImg + fileChooser.getSelectedFile().getName());
                    NameImg = fileChooser.getSelectedFile().getName();
                    Image image = imgIcon.getImage();
                    Image newimg = image.getScaledInstance(160, 160, java.awt.Image.SCALE_SMOOTH);
                    imgIcon = new ImageIcon(newimg);
                    lblSachImg.setIcon(imgIcon);
                    System.out.println(fileChooser.getSelectedFile().getName());
                } else {
                    System.out.println("File is failed to move!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

        }
        return fileChooser.getSelectedFile().getName();
    }

    public void InsertLoaiSS(String theLoai, String MaSach) {
        TheLoai tl = DAOTL.findByName(theLoai);
        DAOLSS.insert(new LoaiSS(MaSach, tl.getMaTheLoai()));
    }

    public void UpdateLoaiSS(String theLoai, String MaSach) {
        TheLoai tl = DAOTL.findByName(theLoai);
        DAOLSS.update(new LoaiSS(MaSach, tl.getMaTheLoai()));
    }

    Sach getForm() {
        Sach sach = new Sach();
        sach.setMaSach(txtMaSach.getText());
        sach.setTenSach(txtTenSach.getText());
        sach.setDuongDan(txtDuongDan.getText());
        sach.setHinh(NameImg);
        TacGia tg = DAOTG.findByName(txtTacGia.getText());
        sach.setMaTacGia(tg.getMaTacGia());
        sach.setNgayDang(now);
        sach.setMoTa(txtMoTa.getText());
        sach.setMaQuanTriVien(ShareHelper.BOSS.getMaQuanTriVien());
        return sach;
    }

    public void InsertSach() {
        if (UtilityHelper.checkNullText(lblMaSach, txtMaSach) && UtilityHelper.checkMa(lblMaSach, txtMaSach)) {
            if (UtilityHelper.checkNullText(lblTenSach, txtTenSach) && UtilityHelper.checkNullText(lblTacGia, txtTacGia)) {
                if (UtilityHelper.checkNullText(new JLabel("File"), txtDuongDan)) {
                    if (checkTacGia(txtTacGia.getText()) == null) {
                        TacGia tg = new TacGia(txtTacGia.getText(), true, now, "", " ", ShareHelper.BOSS.getMaQuanTriVien());
                            DAOTG.insert(tg);
                        
                        try {
                            
                        } catch (Exception e) {
                            DialogHelper.alert(this, "Lỗi thêm tác giả");
                            return;
                        }
                    }
                    Sach sach = getForm();
                    listS = DAOS.selectAll();
                    int lengthList = listS.size();
                    for (int i = 0; i < lengthList; i++) {
                        if (txtMaSach.getText().equalsIgnoreCase(listS.get(i).getMaSach())) {
                            DialogHelper.alert(this, "Mã Sách Đã Tồn Tại");
                            return;
                        }
                    }
                    
                    
                    try {
                        DAOS.insert(sach);
                        InsertLoaiSS((String) cboTheLoai.getSelectedItem(), txtMaSach.getText());
                        DialogHelper.alert(this, "Thêm Mới Sách Thành Công");
                    } catch (Exception e) {
                        DialogHelper.alert(this, "Lỗi Thêm Mới Sách");
                    }

                }
            }
        }

    }

    public void UpdateSach() {
        if (UtilityHelper.checkNullText(lblMaSach, txtMaSach) && UtilityHelper.checkMa(lblMaSach, txtMaSach)) {
            if (UtilityHelper.checkNullText(lblTenSach, txtTenSach) && UtilityHelper.checkNullText(lblTacGia, txtTacGia)) {
                if (UtilityHelper.checkNullText(new JLabel("File"), txtDuongDan)) {
                    Sach sach = getForm();
                    if (sach != null) {
                        try {
                            DAOS.update(sach);
                            UpdateLoaiSS((String) cboTheLoai.getSelectedItem(), txtMaSach.getText());
                            DialogHelper.alert(this, "Cập Nhật Sách Thành Công");
                        } catch (Exception e) {
                            DialogHelper.alert(this, "Lỗi Cập Nhật Sách");
                        }
                    } else {
                        DialogHelper.alert(this, "Cập Nhật Thất Bại");
                    }

                }
            }
        }
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
        pnlMainBooks = new com.ebooks.Compoment.PanelRadius();
        pnlExit1 = new com.ebooks.Compoment.PanelRound();
        lblExit1 = new javax.swing.JLabel();
        txtTenSach = new javax.swing.JTextField();
        txtMaSach = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lblTenSach = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblSachImg = new com.ebooks.Compoment.ImageBoder();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        lblMaSach = new javax.swing.JLabel();
        txtDuongDan = new javax.swing.JTextField();
        cboTheLoai = new javax.swing.JComboBox<>();
        lblTacGia = new javax.swing.JLabel();
        txtTacGia = new javax.swing.JTextField();
        btnLuuThong = new com.ebooks.Compoment.MyButton();
        jLabel1 = new javax.swing.JLabel();
        btnChonFile1 = new com.ebooks.Compoment.MyButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlMainBooks.setBackground(new java.awt.Color(255, 255, 255));
        pnlMainBooks.setRadius(25);
        pnlMainBooks.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblExit1MousePressed(evt);
            }
        });
        pnlExit1.add(lblExit1, new java.awt.GridBagConstraints());

        pnlMainBooks.add(pnlExit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 0, 50, 50));

        txtTenSach.setBackground(new java.awt.Color(222, 247, 227));
        pnlMainBooks.add(txtTenSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 140, 220, 40));

        txtMaSach.setBackground(new java.awt.Color(222, 247, 227));
        pnlMainBooks.add(txtMaSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 210, 40));

        jLabel6.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel6.setText("Mô Tả");
        pnlMainBooks.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, -1, -1));

        lblTenSach.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblTenSach.setText("Tên Sách");
        pnlMainBooks.add(lblTenSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 110, -1, -1));

        jLabel3.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel3.setText("Thể Loại");
        pnlMainBooks.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, -1, -1));

        jLabel19.setFont(new java.awt.Font("Inter ExtraBold", 0, 26)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(55, 149, 76));
        jLabel19.setText("Thông Tin Sách");
        pnlMainBooks.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 210, -1));

        lblSachImg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSachImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Image/41b92ec3eab97e4c24b3f6e8fe75ddec.png"))); // NOI18N
        lblSachImg.setRadius(20);
        lblSachImg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblSachImgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblSachImgMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblSachImgMousePressed(evt);
            }
        });
        pnlMainBooks.add(lblSachImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 180, 180));

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane1.setViewportView(txtMoTa);

        pnlMainBooks.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 384, 670, -1));

        lblMaSach.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblMaSach.setText("Mã Sách");
        pnlMainBooks.add(lblMaSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, -1, -1));

        txtDuongDan.setBackground(new java.awt.Color(222, 247, 227));
        pnlMainBooks.add(txtDuongDan, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 300, 370, 40));

        cboTheLoai.setBackground(new java.awt.Color(222, 247, 227));
        cboTheLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cboTheLoai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        cboTheLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTheLoaiActionPerformed(evt);
            }
        });
        pnlMainBooks.add(cboTheLoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 230, 210, 40));

        lblTacGia.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblTacGia.setText("Tác Giả");
        pnlMainBooks.add(lblTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 200, -1, -1));

        txtTacGia.setBackground(new java.awt.Color(222, 247, 227));
        pnlMainBooks.add(txtTacGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 230, 220, 40));

        btnLuuThong.setBackground(new java.awt.Color(87, 190, 110));
        btnLuuThong.setForeground(new java.awt.Color(255, 255, 255));
        btnLuuThong.setText("Lưu Thông Tin");
        btnLuuThong.setBoderColor(new java.awt.Color(87, 190, 110));
        btnLuuThong.setFont(new java.awt.Font("Inter SemiBold", 0, 14)); // NOI18N
        btnLuuThong.setRadius(10);
        btnLuuThong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuThongActionPerformed(evt);
            }
        });
        pnlMainBooks.add(btnLuuThong, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 490, 270, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Image/nerds-removebg-preview.png"))); // NOI18N
        pnlMainBooks.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        btnChonFile1.setBackground(new java.awt.Color(87, 190, 110));
        btnChonFile1.setForeground(new java.awt.Color(255, 255, 255));
        btnChonFile1.setText("File");
        btnChonFile1.setBoderColor(new java.awt.Color(87, 190, 110));
        btnChonFile1.setFont(new java.awt.Font("Inter SemiBold", 0, 14)); // NOI18N
        btnChonFile1.setRadius(10);
        btnChonFile1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonFile1ActionPerformed(evt);
            }
        });
        pnlMainBooks.add(btnChonFile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 300, 70, 40));

        getContentPane().add(pnlMainBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 550));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblExit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExit1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblExit1MouseClicked

    private void pnlExit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlExit1MouseClicked
        // TODO add your handling code here:
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

    private void lblExit1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExit1MousePressed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_lblExit1MousePressed

    private void cboTheLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTheLoaiActionPerformed
        if (cboTheLoai.getSelectedIndex() != -1) {
            txtMaSach.setText(listTL.get(cboTheLoai.getSelectedIndex()).getMaTheLoai() + "00");
        }
    }//GEN-LAST:event_cboTheLoaiActionPerformed

    private void btnChonFile1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonFile1ActionPerformed
        try {
            MovingFile();
        } catch (Exception e) {
            DialogHelper.alert(this, "Lỗi Chuyển Dữ Liệu");
        }


    }//GEN-LAST:event_btnChonFile1ActionPerformed

    private void lblSachImgMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSachImgMousePressed
        if (evt.getClickCount() == 2) {
            try {
                SetImg();
            } catch (Exception e) {
//               DialogHelper.alert(this,"Lỗi Chọn Hình");
            }

        }
    }//GEN-LAST:event_lblSachImgMousePressed

    private void lblSachImgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSachImgMouseEntered
        lblSachImg.setBorder(new LineBorder(new Color(249, 249, 249)));
    }//GEN-LAST:event_lblSachImgMouseEntered

    private void lblSachImgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSachImgMouseExited
        lblSachImg.setBorder(new LineBorder(new Color(0, 0, 0)));
    }//GEN-LAST:event_lblSachImgMouseExited

    private void btnLuuThongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuThongActionPerformed
      String maSach = txtMaSach.getText();
        int timThay = 0;
        ThucUongDAO DaoTU = new ThucUongDAO();
        listS = DAOS.selectAll();
        
        for(Sach x : listS){
            if(x.getMaSach().contains(maSach)){
                timThay = 1;
            }
        }
        if(timThay == 0) {
            this.InsertSach();
        } else {
            if(DialogHelper.confirm(this, "Chắc chắn cập nhật?")){
                this.UpdateSach();
            }
        }
       
    }//GEN-LAST:event_btnLuuThongActionPerformed

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
            java.util.logging.Logger.getLogger(BooksDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BooksDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BooksDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BooksDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BooksDiaLog dialog = new BooksDiaLog(new javax.swing.JFrame(), true);
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
    private com.ebooks.Compoment.MyButton btnChonFile1;
    private com.ebooks.Compoment.MyButton btnLuuThong;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboTheLoai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblExit1;
    private javax.swing.JLabel lblMaSach;
    private com.ebooks.Compoment.ImageBoder lblSachImg;
    private javax.swing.JLabel lblTacGia;
    private javax.swing.JLabel lblTenSach;
    private com.ebooks.Compoment.PanelRound pnlExit1;
    private com.ebooks.Compoment.PanelRadius pnlMainBooks;
    private javax.swing.JTextField txtDuongDan;
    private javax.swing.JTextField txtMaSach;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtTacGia;
    private javax.swing.JTextField txtTenSach;
    // End of variables declaration//GEN-END:variables
}
