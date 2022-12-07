/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.ebooks.main;

import com.ebooks.dao.TacGiaDAO;
import static com.ebooks.helper.DateHelper.now;
import com.ebooks.helper.DialogHelper;
import com.ebooks.helper.ShareHelper;
import com.ebooks.helper.UtilityHelper;
import com.ebooks.model.TacGia;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Thinh
 */
public class AuthorDiaLog extends javax.swing.JDialog {

    boolean congTac = false;
    String maTacGia = null;
    private TacGiaDAO DAOTG = new TacGiaDAO();
    public AuthorDiaLog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Calendar.setVisible(congTac);
        congTac = !congTac;
        setBackground(new Color(0, 0, 0, 0));
    }

    public AuthorDiaLog(java.awt.Frame parent, boolean modal, TacGia tg) {
        super(parent, modal);
        initComponents();
        setForm(tg);
        Calendar.setVisible(congTac);
        maTacGia = tg.getMaTacGia();
        setBackground(new Color(0, 0, 0, 0));
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

    public void setForm(TacGia tg) {
        txtHoten.setText(tg.getHoTen());
        SimpleDateFormat formats = new SimpleDateFormat("dd/MM/yyyy");
        txtNgaySinh.setText(String.valueOf(formats.format(tg.getNgaySinh())));
        txtMoTa.setText(tg.getMoTa());
        rdoNam.setSelected(tg.isGioiTinh());
        rdoNu.setSelected(!tg.isGioiTinh());
        lblTacGiaImg.setIcon(ShowImg(tg.getHinh()));

    }

    TacGia getForm() {
        String NameImg = "41b92ec3eab97e4c24b3f6e8fe75ddec.png";
        TacGia tacGia = new TacGia();
        tacGia.setHoTen(txtHoten.getText());
        tacGia.setGioiTinh(rdoNam.isSelected()? true : false);
        tacGia.setNgaySinh(new Date(txtNgaySinh.getText()));
        tacGia.setHinh(NameImg);
        tacGia.setMoTa(txtMoTa.getText());
        tacGia.setMaQuanTriVien(ShareHelper.BOSS.getMaQuanTriVien());
        return tacGia;
    }
    
    TacGia getForm(TacGia tg) {
        String NameImg = "41b92ec3eab97e4c24b3f6e8fe75ddec.png";
        tg.setHoTen(txtHoten.getText());
        tg.setGioiTinh(rdoNam.isSelected()? true : false);
        tg.setNgaySinh(Calendar.getDate());
        tg.setHinh(NameImg);
        tg.setMoTa(txtMoTa.getText());
        tg.setMaQuanTriVien(ShareHelper.BOSS.getMaQuanTriVien());
        return tg;
    }

    public ImageIcon ShowImg(String nameImg) {
        String UrlImg = "..\\DuAn01_G3_ebooks\\src\\com\\Content\\imgAthor\\";
        ImageIcon imgIcon = new ImageIcon(UrlImg + nameImg);
        Image image = imgIcon.getImage();
        Image newimg = image.getScaledInstance(160, 160, java.awt.Image.SCALE_SMOOTH);
        imgIcon = new ImageIcon(newimg);
        return imgIcon;
    }
    
    public String SetImg() {
        String NameImg = "41b92ec3eab97e4c24b3f6e8fe75ddec.png";
        String UrlImg = "..\\DuAn01_G3_ebooks\\src\\com\\Content\\imgAthor\\";
        JFileChooser fileChooser = new JFileChooser();
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
                    lblTacGiaImg.setIcon(imgIcon);
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

    public String checkTacGia(String TenTacGia) {
        TacGia tg = DAOTG.findByName(TenTacGia);
        if (tg != null) {
            return tg.getHoTen();
        }
        return null;
    }

    // cac button 
    public void InsertTacGia() {
        if (UtilityHelper.checkNullText(lblHoTen, txtHoten) && UtilityHelper.checkNullText(lblNgaySinh, txtNgaySinh)) {
                TacGia tacGia = getForm();        
                DAOTG.insert(tacGia);

            try {
                                // this.fill
                DialogHelper.alert(this, "Thêm mới Thành công");
            } catch (Exception e) {
                DialogHelper.alert(this, "Lỗi thêm tác giả");
                return;
            }
        }
    }

    public void UpdateSach() {
        if (UtilityHelper.checkNullText(lblHoTen, txtHoten) && UtilityHelper.checkNullText(lblNgaySinh, txtNgaySinh)) {          
            TacGia tacGia = DAOTG.findById(maTacGia); 
            System.out.println(tacGia);
            if (tacGia != null) {
                
                 DAOTG.update(getForm(tacGia));
                try {
                   
                    DialogHelper.alert(this, "Cập Nhật Thành Công");
                } catch (Exception e) {
                    DialogHelper.alert(this, "Lỗi Cập Nhật");
                }
            } else {
                DialogHelper.alert(this, "Cập Nhật Thất Bại");
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
        pnlMainDialog = new com.ebooks.Compoment.PanelRadius();
        Calendar = new com.toedter.calendar.JCalendar();
        pnlExit1 = new com.ebooks.Compoment.PanelRound();
        lblExit1 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        lblNgaySinh = new javax.swing.JLabel();
        txtHoten = new javax.swing.JTextField();
        lblMoTa = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        btnLuu = new com.ebooks.Compoment.MyButton();
        jLabel19 = new javax.swing.JLabel();
        lblTacGiaImg = new com.ebooks.Compoment.ImageBoder();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        lblHoTen = new javax.swing.JLabel();
        btnIconCld = new com.ebooks.Compoment.MyButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlMainDialog.setBackground(new java.awt.Color(255, 255, 255));
        pnlMainDialog.setRadius(25);
        pnlMainDialog.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlMainDialogMouseDragged(evt);
            }
        });
        pnlMainDialog.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Calendar.setBackground(new java.awt.Color(201, 235, 201));
        Calendar.setDecorationBackgroundColor(new java.awt.Color(153, 255, 153));
        Calendar.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        Calendar.setWeekdayForeground(new java.awt.Color(51, 51, 51));
        Calendar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                CalendarPropertyChange(evt);
            }
        });
        pnlMainDialog.add(Calendar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 190, -1, -1));

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

        pnlMainDialog.add(pnlExit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 0, 50, 50));

        txtNgaySinh.setBackground(new java.awt.Color(222, 247, 227));
        pnlMainDialog.add(txtNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 150, 190, 40));

        lblNgaySinh.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblNgaySinh.setText("Ngày Sinh");
        pnlMainDialog.add(lblNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 120, -1, -1));

        txtHoten.setBackground(new java.awt.Color(222, 247, 227));
        pnlMainDialog.add(txtHoten, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, 210, 40));

        lblMoTa.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblMoTa.setText("Mô Tả");
        pnlMainDialog.add(lblMoTa, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 310, -1, -1));

        lblGioiTinh.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblGioiTinh.setText("Giới Tính");
        pnlMainDialog.add(lblGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 210, -1, -1));

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");
        rdoNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNamActionPerformed(evt);
            }
        });
        pnlMainDialog.add(rdoNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, -1, -1));

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");
        pnlMainDialog.add(rdoNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 250, -1, -1));

        btnLuu.setBackground(new java.awt.Color(87, 190, 110));
        btnLuu.setForeground(new java.awt.Color(255, 255, 255));
        btnLuu.setText("Lưu Thông Tin");
        btnLuu.setBoderColor(new java.awt.Color(87, 190, 110));
        btnLuu.setFont(new java.awt.Font("Inter SemiBold", 0, 14)); // NOI18N
        btnLuu.setRadius(10);
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        pnlMainDialog.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 480, 270, 50));

        jLabel19.setFont(new java.awt.Font("Inter ExtraBold", 0, 26)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(55, 149, 76));
        jLabel19.setText("Thông Tin Tác Giả");
        pnlMainDialog.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 240, -1));

        lblTacGiaImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Image/41b92ec3eab97e4c24b3f6e8fe75ddec.png"))); // NOI18N
        lblTacGiaImg.setRadius(20);
        lblTacGiaImg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblTacGiaImgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblTacGiaImgMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblTacGiaImgMousePressed(evt);
            }
        });
        pnlMainDialog.add(lblTacGiaImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 160, 160));

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane1.setViewportView(txtMoTa);

        pnlMainDialog.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, 680, 120));

        lblHoTen.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblHoTen.setText("Họ Tên Người Dùng");
        pnlMainDialog.add(lblHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, -1, -1));

        btnIconCld.setBackground(new java.awt.Color(87, 190, 110));
        btnIconCld.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Icon/calendar.png"))); // NOI18N
        btnIconCld.setAutoscrolls(true);
        btnIconCld.setBoderColor(new java.awt.Color(204, 204, 204));
        btnIconCld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIconCldActionPerformed(evt);
            }
        });
        pnlMainDialog.add(btnIconCld, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 150, 55, 40));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Image/nerds-removebg-preview.png"))); // NOI18N
        pnlMainDialog.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        getContentPane().add(pnlMainDialog, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 550));

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

    private void btnIconCldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIconCldActionPerformed
        Calendar.setVisible(congTac);
        congTac = !congTac;
    }//GEN-LAST:event_btnIconCldActionPerformed

    private void rdoNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNamActionPerformed

    private void CalendarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_CalendarPropertyChange
        SimpleDateFormat formats = new SimpleDateFormat("dd/MM/yyyy");
        txtNgaySinh.setText(String.valueOf(formats.format(Calendar.getDate())));

    }//GEN-LAST:event_CalendarPropertyChange

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
//        // TODO add your handling code here:
        List<TacGia> listTG = new ArrayList<>();
        String maTG = txtHoten.getText();
        int timThay = 0;
//        ThucUongDAO DaoTU = new ThucUongDAO();
        listTG = DAOTG.selectAll();

        for (TacGia x : listTG) {
            if (x.getHoTen().contains(maTG)) {
                timThay = 1;
            }
        }
        if (timThay == 0) {
            this.InsertTacGia();
        } else {
            if (DialogHelper.confirm(this, "Chắc chắn cập nhật?")) {
                this.UpdateSach();
            }
        }
      //   this.InsertTacGia();
    }//GEN-LAST:event_btnLuuActionPerformed

    private void lblTacGiaImgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTacGiaImgMouseEntered
        lblTacGiaImg.setBorder(new LineBorder(new Color(249, 249, 249)));
    }//GEN-LAST:event_lblTacGiaImgMouseEntered

    private void lblTacGiaImgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTacGiaImgMouseExited
        lblTacGiaImg.setBorder(new LineBorder(new Color(0, 0, 0)));
    }//GEN-LAST:event_lblTacGiaImgMouseExited

    private void lblTacGiaImgMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTacGiaImgMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            try {
                SetImg();
            } catch (Exception e) {
                //               DialogHelper.alert(this,"Lỗi Chọn Hình");
            }

        }
    }//GEN-LAST:event_lblTacGiaImgMousePressed

    private void pnlMainDialogMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMainDialogMouseDragged
       initMoving(this, pnlMainDialog);
    }//GEN-LAST:event_pnlMainDialogMouseDragged

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
            java.util.logging.Logger.getLogger(AuthorDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AuthorDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AuthorDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AuthorDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                AuthorDiaLog dialog = new AuthorDiaLog(new javax.swing.JFrame(), true);
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
    private com.ebooks.Compoment.MyButton btnIconCld;
    private com.ebooks.Compoment.MyButton btnLuu;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblExit1;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblMoTa;
    private javax.swing.JLabel lblNgaySinh;
    private com.ebooks.Compoment.ImageBoder lblTacGiaImg;
    private com.ebooks.Compoment.PanelRound pnlExit1;
    private com.ebooks.Compoment.PanelRadius pnlMainDialog;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTextField txtHoten;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtNgaySinh;
    // End of variables declaration//GEN-END:variables
}
