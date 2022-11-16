/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.ebooks.main;

import java.awt.Color;

/**
 *
 * @author Thinh
 */
public class SettingDiaLog extends javax.swing.JDialog {

    /**
     * Creates new form SettingDiaLog
     */
    public SettingDiaLog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setBackground(new Color(0,0,0,0));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRadius1 = new com.ebooks.Compoment.PanelRadius();
        lblLogo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tbdSetting = new com.ebooks.Compoment.MaterialTabbed();
        panelRadius2 = new com.ebooks.Compoment.PanelRadius();
        jLabel2 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        panelRadius4 = new com.ebooks.Compoment.PanelRadius();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jPasswordField3 = new javax.swing.JPasswordField();
        myButton8 = new com.ebooks.Compoment.MyButton();
        panelRadius5 = new com.ebooks.Compoment.PanelRadius();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        myButton9 = new com.ebooks.Compoment.MyButton();
        panelRadius3 = new com.ebooks.Compoment.PanelRadius();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        myButton10 = new com.ebooks.Compoment.MyButton();
        jLabel16 = new javax.swing.JLabel();
        pnlExit = new com.ebooks.Compoment.PanelRound();
        lblExit = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRadius1.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius1.setRadius(25);
        panelRadius1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Image/nerds.png"))); // NOI18N
        panelRadius1.add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, -1));

        jLabel1.setFont(new java.awt.Font("Inter", 1, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cài Đặt ");
        panelRadius1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 120, -1));

        tbdSetting.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tbdSetting.setFont(new java.awt.Font("Inter SemiBold", 0, 12)); // NOI18N

        panelRadius2.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        jLabel2.setText("Thông Tin Về Ứng Dụng ");
        panelRadius2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));
        panelRadius2.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 700, 10));

        tbdSetting.addTab("Giới Thiệu", panelRadius2);

        panelRadius4.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelRadius4.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 700, 10));

        jLabel3.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        jLabel3.setText("Đổi Mật Khẩu Tài Khoản");
        panelRadius4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel6.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel6.setText("Tên Đăng Nhập");
        panelRadius4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jTextField1.setBackground(new java.awt.Color(222, 247, 227));
        jTextField1.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jTextField1.setText("jTextField1");
        panelRadius4.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 260, 40));

        jLabel7.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel7.setText("Mật Khẩu");
        panelRadius4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, -1, -1));

        jLabel8.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel8.setText("Mật Khẩu Mới ");
        panelRadius4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jLabel9.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel9.setText("Xác Nhận");
        panelRadius4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, -1, -1));

        jPasswordField1.setBackground(new java.awt.Color(222, 247, 227));
        jPasswordField1.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jPasswordField1.setText("jPasswordField1");
        panelRadius4.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, 260, 40));

        jPasswordField2.setBackground(new java.awt.Color(222, 247, 227));
        jPasswordField2.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jPasswordField2.setText("jPasswordField1");
        panelRadius4.add(jPasswordField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, 260, 40));

        jPasswordField3.setBackground(new java.awt.Color(222, 247, 227));
        jPasswordField3.setFont(new java.awt.Font("Inter", 0, 12)); // NOI18N
        jPasswordField3.setText("jPasswordField1");
        jPasswordField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField3ActionPerformed(evt);
            }
        });
        panelRadius4.add(jPasswordField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 260, 40));

        myButton8.setBackground(new java.awt.Color(87, 190, 110));
        myButton8.setForeground(new java.awt.Color(255, 255, 255));
        myButton8.setText("Lưu Tài Khoản");
        myButton8.setBoderColor(new java.awt.Color(87, 190, 110));
        myButton8.setFont(new java.awt.Font("Inter SemiBold", 0, 14)); // NOI18N
        myButton8.setRadius(10);
        panelRadius4.add(myButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 220, 40));

        tbdSetting.addTab("Đổi Mật Khẩu", panelRadius4);

        panelRadius5.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelRadius5.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 700, 10));

        jLabel4.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        jLabel4.setText("Đăng Xuất Tài Khoản");
        panelRadius5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel10.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Sau khi đăng xuất bạn sẽ phải rời khỏi giao diện chính của ứng dụng và chỉ vào giao");
        panelRadius5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 650, -1));

        jLabel11.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("giao diện chính lần nữa thì phải đăng nhập vào ứng dụng hoạt đăng ký một tài khoản");
        panelRadius5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 650, -1));

        jLabel12.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("khác để tiếp tục sữ dụng phần mềm của chúng tôi.");
        panelRadius5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        myButton9.setBackground(new java.awt.Color(87, 190, 110));
        myButton9.setForeground(new java.awt.Color(255, 255, 255));
        myButton9.setText("Đăng Xuất Tài Khoản");
        myButton9.setBoderColor(new java.awt.Color(87, 190, 110));
        myButton9.setFont(new java.awt.Font("Inter SemiBold", 0, 14)); // NOI18N
        myButton9.setRadius(10);
        panelRadius5.add(myButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 220, 40));

        tbdSetting.addTab("Đăng Xuất", panelRadius5);

        panelRadius3.setBackground(new java.awt.Color(255, 255, 255));
        panelRadius3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelRadius3.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 700, 10));

        jLabel5.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        jLabel5.setText("Xóa Tài Khoản");
        panelRadius3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel13.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Sau khi bạn xóa tài khoản bạn không thể đăng nhập vào ứng dụng bằng tài khoản ");
        panelRadius3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 660, -1));

        jLabel14.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("bị xóa và tài khoản sau khi xóa sẽ vĩnh viễn mất. Nếu bạn muốn sử dụng ứng dụng ");
        panelRadius3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 640, -1));

        jLabel15.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("dụng này.");
        panelRadius3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        myButton10.setBackground(new java.awt.Color(253, 127, 127));
        myButton10.setForeground(new java.awt.Color(255, 255, 255));
        myButton10.setText("Xóa Tài Khoản");
        myButton10.setBoderColor(new java.awt.Color(253, 127, 127));
        myButton10.setFont(new java.awt.Font("Inter SemiBold", 0, 14)); // NOI18N
        myButton10.setRadius(10);
        myButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton10ActionPerformed(evt);
            }
        });
        panelRadius3.add(myButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 220, 40));

        jLabel16.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("dụng tiếp tục thì phải đăng ký một tài khoản mới để có thể tiếp tục trãi nghiệm ứng");
        panelRadius3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        tbdSetting.addTab("Xóa Tài Khoản", panelRadius3);

        panelRadius1.add(tbdSetting, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 830, 390));

        pnlExit.setBackground(new java.awt.Color(253, 127, 127));
        pnlExit.setRoundBottomLeft(25);
        pnlExit.setRoundTopRight(25);
        pnlExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlExitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlExitMouseExited(evt);
            }
        });
        pnlExit.setLayout(new java.awt.GridBagLayout());

        lblExit.setFont(new java.awt.Font("Inter", 1, 14)); // NOI18N
        lblExit.setForeground(new java.awt.Color(255, 255, 255));
        lblExit.setText("X");
        lblExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExitMouseClicked(evt);
            }
        });
        pnlExit.add(lblExit, new java.awt.GridBagConstraints());

        panelRadius1.add(pnlExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 0, 50, 50));

        jLabel32.setFont(new java.awt.Font("Adobe Caslon Pro", 1, 24)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("N E R D S");
        panelRadius1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, 50));

        jLabel33.setFont(new java.awt.Font("Adobe Myungjo Std M", 2, 10)); // NOI18N
        jLabel33.setText("Learning is the eye of the mind");
        panelRadius1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 160, -1));

        getContentPane().add(panelRadius1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 550));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPasswordField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField3ActionPerformed

    private void myButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_myButton10ActionPerformed

    private void pnlExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlExitMouseClicked
        this.dispose();
    }//GEN-LAST:event_pnlExitMouseClicked

    private void lblExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseClicked
        this.dispose();
    }//GEN-LAST:event_lblExitMouseClicked

    private void pnlExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlExitMouseEntered
        pnlExit.setBackground(new Color(233,111,111));
    }//GEN-LAST:event_pnlExitMouseEntered

    private void pnlExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlExitMouseExited
         pnlExit.setBackground(new Color(253,127,127));
    }//GEN-LAST:event_pnlExitMouseExited

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
            java.util.logging.Logger.getLogger(SettingDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SettingDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SettingDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SettingDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SettingDiaLog dialog = new SettingDiaLog(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblLogo;
    private com.ebooks.Compoment.MyButton myButton10;
    private com.ebooks.Compoment.MyButton myButton8;
    private com.ebooks.Compoment.MyButton myButton9;
    private com.ebooks.Compoment.PanelRadius panelRadius1;
    private com.ebooks.Compoment.PanelRadius panelRadius2;
    private com.ebooks.Compoment.PanelRadius panelRadius3;
    private com.ebooks.Compoment.PanelRadius panelRadius4;
    private com.ebooks.Compoment.PanelRadius panelRadius5;
    private com.ebooks.Compoment.PanelRound pnlExit;
    private com.ebooks.Compoment.MaterialTabbed tbdSetting;
    // End of variables declaration//GEN-END:variables
}
