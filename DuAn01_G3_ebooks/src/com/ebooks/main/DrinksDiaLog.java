/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.ebooks.main;

import com.ebooks.model.ThucUong;
import java.awt.Color;
import com.ebooks.helper.UtilityHelper;
import com.ebooks.dao.ThucUongDAO;
import com.ebooks.helper.DialogHelper;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class DrinksDiaLog extends javax.swing.JDialog {

    List<ThucUong> listTU = new ArrayList();

    public DrinksDiaLog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        initMoving(this, pnlMainDialog);
    }

    public DrinksDiaLog(java.awt.Frame parent, boolean modal, ThucUong thucUong) {
        super(parent, modal);
        initComponents();
        setForm(thucUong);
        setBackground(new Color(0, 0, 0, 0));
        initMoving(this, pnlMainDialog);
        txtMaThucUong.setEditable(false);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlMainDialog = new com.ebooks.Compoment.PanelRadius();
        pnlExit1 = new com.ebooks.Compoment.PanelRound();
        lblExit1 = new javax.swing.JLabel();
        txtTenThucUong = new javax.swing.JTextField();
        txtMaThucUong = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lblTenThucUong = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        lblMaThucUong = new javax.swing.JLabel();
        lblGiaTien = new javax.swing.JLabel();
        txtGiaTien = new javax.swing.JTextField();
        btnLuu = new com.ebooks.Compoment.MyButton();
        jLabel5 = new javax.swing.JLabel();

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

        txtTenThucUong.setBackground(new java.awt.Color(222, 247, 227));
        pnlMainDialog.add(txtTenThucUong, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, 220, 40));

        txtMaThucUong.setBackground(new java.awt.Color(222, 247, 227));
        pnlMainDialog.add(txtMaThucUong, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 210, 40));

        jLabel6.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel6.setText("Mô Tả");
        pnlMainDialog.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, -1, -1));

        lblTenThucUong.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblTenThucUong.setText("Tên Thức Uống");
        pnlMainDialog.add(lblTenThucUong, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, -1, -1));

        jLabel19.setFont(new java.awt.Font("Inter ExtraBold", 0, 26)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(55, 149, 76));
        jLabel19.setText("Thông Tin Thức Uống");
        pnlMainDialog.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 290, -1));

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane1.setViewportView(txtMoTa);

        pnlMainDialog.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 710, 220));

        lblMaThucUong.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblMaThucUong.setText("Mã Thức Uống");
        pnlMainDialog.add(lblMaThucUong, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, -1, -1));

        lblGiaTien.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblGiaTien.setText("Giá Tiền");
        pnlMainDialog.add(lblGiaTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 120, -1, -1));

        txtGiaTien.setBackground(new java.awt.Color(222, 247, 227));
        txtGiaTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaTienActionPerformed(evt);
            }
        });
        pnlMainDialog.add(txtGiaTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 150, 220, 40));

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
        pnlMainDialog.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 490, 270, 50));

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
//        System.exit(0);
        this.dispose();
    }//GEN-LAST:event_lblExit1MousePressed

    private void txtGiaTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaTienActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        String maThucUong = txtMaThucUong.getText();
        int timThay = 0;
        ThucUongDAO DaoTU = new ThucUongDAO();
        listTU = DaoTU.selectAll();

        for (ThucUong x : listTU) {
            if (x.getMaThucUong().contains(maThucUong)) {
                timThay = 1;
            }
        }

        if (timThay == 0) {
            this.insert();
        } else {
            if (DialogHelper.confirm(this, "Chắc chắn cập nhật?")) {
                this.update();
            }
        }
    }//GEN-LAST:event_btnLuuActionPerformed

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
            java.util.logging.Logger.getLogger(DrinksDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DrinksDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DrinksDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DrinksDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                DrinksDiaLog dialog = new DrinksDiaLog(new javax.swing.JFrame(), true);
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
    private com.ebooks.Compoment.MyButton btnLuu;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblExit1;
    private javax.swing.JLabel lblGiaTien;
    private javax.swing.JLabel lblMaThucUong;
    private javax.swing.JLabel lblTenThucUong;
    private com.ebooks.Compoment.PanelRound pnlExit1;
    private com.ebooks.Compoment.PanelRadius pnlMainDialog;
    private javax.swing.JTextField txtGiaTien;
    private javax.swing.JTextField txtMaThucUong;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtTenThucUong;
    // End of variables declaration//GEN-END:variables

    ThucUongDAO DaoTU = new ThucUongDAO();

    public void setForm(ThucUong thucUong) {
        txtMaThucUong.setText(thucUong.getMaThucUong());
        txtTenThucUong.setText(thucUong.getTenThucUong());
        txtGiaTien.setText(String.valueOf(thucUong.getGia()));
    }

    public ThucUong getForm() {
        ThucUong tu = new ThucUong();
        tu.setMaThucUong(txtMaThucUong.getText());
        tu.setTenThucUong(txtTenThucUong.getText());
        tu.setGia(Double.parseDouble(txtGiaTien.getText()));
        return tu;
    }
    
    
    public ThucUong getForm(ThucUong tu) {
        tu.setMaThucUong(txtMaThucUong.getText());
        tu.setTenThucUong(txtTenThucUong.getText());
        tu.setGia(Double.parseDouble(txtGiaTien.getText()));
        tu.setMoTa(txtMoTa.getText());
        return tu;
    }

    //them mơi
    public void insert() {
        if (UtilityHelper.checkNullText(lblMaThucUong, txtMaThucUong) && UtilityHelper.checkMa(lblMaThucUong, txtMaThucUong)) {
            if (UtilityHelper.checkNullText(lblTenThucUong, txtTenThucUong) && UtilityHelper.checkName(lblTenThucUong, txtTenThucUong)) {
                if (UtilityHelper.checkNullText(lblGiaTien, txtGiaTien) && UtilityHelper.checkGia(txtGiaTien)) {
                    
                   
                    try {
                        ThucUong TU = getForm();
                        DaoTU.insert(TU);
//                      this.fillTable();
                        DialogHelper.alert(this, "Thêm mới Thành công");
                    } catch (Exception e) {
                        DialogHelper.alert(this, "Thêm Mới Thất Bại!");
                    }
                }
            }
        }
    }

    //cập nhật
    public void update() {
        if (UtilityHelper.checkNullText(lblMaThucUong, txtMaThucUong) && UtilityHelper.checkMa(lblMaThucUong, txtMaThucUong)) {
            if (UtilityHelper.checkNullText(lblTenThucUong, txtTenThucUong) && UtilityHelper.checkName(lblTenThucUong, txtTenThucUong)) {
                if (UtilityHelper.checkNullText(lblGiaTien, txtGiaTien) && UtilityHelper.checkGia(txtGiaTien)) {
                    try {
                        String maThucUong = txtMaThucUong.getText();
                                
                        ThucUong TU = DaoTU.findById(maThucUong);
                        DaoTU.update(getForm(TU));
//                                this.fillTable();
                        DialogHelper.alert(this, "Cập nhật Thành công");
                    } catch (Exception e) {
                        DialogHelper.alert(this, "Cập nhật Thất Bại!");
                    }
                }
            }
        }
    }
}
