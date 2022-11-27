/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.ebooks.main;

import com.ebooks.dao.AudioSachDAO;
import com.ebooks.dao.SachDAO;
import com.ebooks.dao.TacGiaDAO;
import com.ebooks.helper.DialogHelper;
import com.ebooks.helper.ShareHelper;
import com.ebooks.helper.UtilityHelper;
import com.ebooks.model.AudioSach;
import com.ebooks.model.Sach;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thinh
 */
public class AudiosDiaLog extends javax.swing.JDialog {
    
    private SachDAO DAOS = new SachDAO();
    private List<Sach> listS = new ArrayList<>();
    private List<AudioSach> listAudio = new ArrayList<>();
    private TacGiaDAO DAOTG = new TacGiaDAO();
    private AudioSachDAO DAOAS = new AudioSachDAO();
    private JFileChooser fileChooser = new JFileChooser();
    private String UrlAudio = "..\\DuAn01_G3_ebooks\\src\\com\\Content\\audioEbook\\";
    private String maAudio = null;
    public AudiosDiaLog(java.awt.Frame parent, boolean modal, AudioSach audio) {
        super(parent, modal);
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        initMoving(this, pnlMainDialog);
        SetForm(audio);
        fillTableEbooks();
        tabAudioSach.setEnabledAt(1, false);
        maAudio = audio.getMaAudio();
        txtMaSach.setEnabled(false);
        txtDuongDan.setEnabled(false);
    }
    
    
    
     
    public AudiosDiaLog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        initMoving(this, pnlMainDialog);
        fillTableEbooks();
        txtMaSach.setEnabled(false);
        txtDuongDan.setEnabled(false);
    }
    
    public AudioSach getForm(){
        AudioSach audio = new AudioSach();
        audio.setMaSach(txtMaSach.getText());
        audio.setTenAudio(txtTenAudio.getText());
        audio.setNguoiThu(txtNguoiThu.getText());
        audio.setDuongDan(txtDuongDan.getText());
        audio.setMoTa(txtMoTa.getText());
        audio.setNgayPhatHanh(new Date());
        audio.setMaQuanTriVien(ShareHelper.BOSS.getMaQuanTriVien());
        return audio;
    }
    
    public AudioSach getForm(AudioSach audio){
        audio.setMaSach(txtMaSach.getText());
        audio.setTenAudio(txtTenAudio.getText());
        audio.setNguoiThu(txtNguoiThu.getText());
        audio.setDuongDan(txtDuongDan.getText());
        audio.setMoTa(txtMoTa.getText());
        return audio;
    }
    
    private void InsertAudio(){
        if(UtilityHelper.checkNullText(lblMaSach, txtMaSach) && UtilityHelper.checkNullText(lblTenAudio, txtTenAudio)){
            if(UtilityHelper.checkNullText(lblDuongDan, txtDuongDan)){
                AudioSach audio = getForm();
                try {
                     DAOAS.insert(audio);
                     DialogHelper.alert(this,"Thêm Audio cho Sách Thành Công");
                } catch (Exception e) {
                     DialogHelper.alert(this,"Thêm Audio cho Sách Thất Bại");
                }
            }
        }
    }
    
    private void UpdateAudio(){
        if(UtilityHelper.checkNullText(lblMaSach, txtMaSach) && UtilityHelper.checkNullText(lblTenAudio, txtTenAudio)){
            if(UtilityHelper.checkNullText(lblDuongDan, txtDuongDan)){
                AudioSach audio = DAOAS.findById(maAudio);
                
                
                try {
                    DAOAS.update(getForm(audio));
                    DialogHelper.alert(this,"Cập Nhật Audio cho Sách Thành Công");
                } catch (Exception e) {
                     DialogHelper.alert(this,"Cập Nhật Audio cho Sách Thất Bại");
                }
            }
        }
    }
    
    
    private void SetForm(AudioSach audio) {
        txtMaSach.setText(audio.getMaSach());
        txtTenAudio.setText(audio.getTenAudio());
        txtNguoiThu.setText(audio.getNguoiThu());
        txtDuongDan.setText(audio.getDuongDan());
        txtMoTa.setText(audio.getMoTa());
    }
    
     public String MovingFile() {
        int x = fileChooser.showDialog(this, "Chon file");
        if (x == JFileChooser.APPROVE_OPTION) {
            try {
                File afile = new File(fileChooser.getSelectedFile().getPath());
                if (afile.renameTo(new File(UrlAudio + fileChooser.getSelectedFile().getName()))) {
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
    
    
    public void fillTableEbooks() {
        DefaultTableModel model;
        model = (DefaultTableModel) tblEbook.getModel();
        tblEbook.setSelectionBackground(new Color(87, 190, 110));
        model.setRowCount(0);
        try {
            listS = DAOS.selectSachNotAudio();
            for (Sach sach : listS) {
                Object[] row = {sach.getMaSach(), sach.getTenSach(), DAOTG.findById(sach.getMaTacGia()).getHoTen(), sach.getNgayDang(), sach.getMoTa()};
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
        jLabel8 = new javax.swing.JLabel();
        tabAudioSach = new com.ebooks.Compoment.MaterialTabbed();
        pnlAudio = new com.ebooks.Compoment.PanelRadius();
        txtMaSach = new javax.swing.JTextField();
        btnFile = new com.ebooks.Compoment.MyButton();
        lblMaSach = new javax.swing.JLabel();
        txtDuongDan = new javax.swing.JTextField();
        lblTenAudio = new javax.swing.JLabel();
        txtTenAudio = new javax.swing.JTextField();
        txtNguoiThu = new javax.swing.JTextField();
        lblNguoiThu = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        btnFile1 = new com.ebooks.Compoment.MyButton();
        lblDuongDan = new javax.swing.JLabel();
        pnlEbooks = new com.ebooks.Compoment.PanelRadius();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEbook = new com.ebooks.Compoment.Table();
        btnFile2 = new com.ebooks.Compoment.MyButton();
        jLabel20 = new javax.swing.JLabel();

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

        pnlMainDialog.add(pnlExit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 0, 50, 50));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Image/nerds-removebg-preview.png"))); // NOI18N
        pnlMainDialog.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        pnlAudio.setBackground(new java.awt.Color(255, 255, 255));
        pnlAudio.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtMaSach.setBackground(new java.awt.Color(222, 247, 227));
        pnlAudio.add(txtMaSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 310, 40));

        btnFile.setBackground(new java.awt.Color(87, 190, 110));
        btnFile.setForeground(new java.awt.Color(255, 255, 255));
        btnFile.setText("File");
        btnFile.setBoderColor(new java.awt.Color(87, 190, 110));
        btnFile.setFont(new java.awt.Font("Inter SemiBold", 0, 14)); // NOI18N
        btnFile.setRadius(10);
        btnFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFileActionPerformed(evt);
            }
        });
        pnlAudio.add(btnFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 120, 70, 40));

        lblMaSach.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblMaSach.setText("Mã Sách");
        pnlAudio.add(lblMaSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        txtDuongDan.setBackground(new java.awt.Color(222, 247, 227));
        pnlAudio.add(txtDuongDan, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, 220, 40));

        lblTenAudio.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblTenAudio.setText("Tên Audio");
        pnlAudio.add(lblTenAudio, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, -1, -1));

        txtTenAudio.setBackground(new java.awt.Color(222, 247, 227));
        pnlAudio.add(txtTenAudio, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, 310, 40));

        txtNguoiThu.setBackground(new java.awt.Color(222, 247, 227));
        pnlAudio.add(txtNguoiThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 310, 40));

        lblNguoiThu.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblNguoiThu.setText("Người Thu");
        pnlAudio.add(lblNguoiThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane1.setViewportView(txtMoTa);

        pnlAudio.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 206, 690, 150));

        jLabel6.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel6.setText("Mô Tả");
        pnlAudio.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        btnFile1.setBackground(new java.awt.Color(87, 190, 110));
        btnFile1.setForeground(new java.awt.Color(255, 255, 255));
        btnFile1.setText("Lưu Thông Tin");
        btnFile1.setBoderColor(new java.awt.Color(87, 190, 110));
        btnFile1.setFont(new java.awt.Font("Inter SemiBold", 0, 14)); // NOI18N
        btnFile1.setRadius(10);
        btnFile1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFile1ActionPerformed(evt);
            }
        });
        pnlAudio.add(btnFile1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 370, 180, 40));

        lblDuongDan.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        lblDuongDan.setText("Dường Dẫn");
        pnlAudio.add(lblDuongDan, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, -1, -1));

        tabAudioSach.addTab("Audio", pnlAudio);

        pnlEbooks.setBackground(new java.awt.Color(255, 255, 255));
        pnlEbooks.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblEbook.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã Sách", "Tên Sách", "Tác Giả"
            }
        ));
        tblEbook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblEbookMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblEbook);

        pnlEbooks.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 690, 340));

        btnFile2.setBackground(new java.awt.Color(87, 190, 110));
        btnFile2.setForeground(new java.awt.Color(255, 255, 255));
        btnFile2.setText("Thêm Audio");
        btnFile2.setBoderColor(new java.awt.Color(87, 190, 110));
        btnFile2.setFont(new java.awt.Font("Inter SemiBold", 0, 14)); // NOI18N
        btnFile2.setRadius(10);
        btnFile2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFile2ActionPerformed(evt);
            }
        });
        pnlEbooks.add(btnFile2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 370, 180, 40));

        tabAudioSach.addTab("Ebook", pnlEbooks);

        pnlMainDialog.add(tabAudioSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 720, 470));

        jLabel20.setFont(new java.awt.Font("Inter ExtraBold", 0, 26)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(55, 149, 76));
        jLabel20.setText("Thông Tin Audio Sách");
        pnlMainDialog.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 290, -1));

        getContentPane().add(pnlMainDialog, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 590));

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

    private void btnFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFileActionPerformed
        try {
            MovingFile();
        } catch (Exception e) {
            
        }
    }//GEN-LAST:event_btnFileActionPerformed

    private void tblEbookMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEbookMousePressed
        if (evt.getClickCount() == 2) {
            int index = tblEbook.getSelectedRow();
            String maEbook = (String) tblEbook.getValueAt(index, 0);
            txtMaSach.setText(maEbook);
            tabAudioSach.setSelectedIndex(0);
        }
    }//GEN-LAST:event_tblEbookMousePressed

    private void btnFile2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFile2ActionPerformed
       
        int index = tblEbook.getSelectedRow();
        if (index != -1) {
            String maEbook = (String) tblEbook.getValueAt(index, 0);
            txtMaSach.setText(maEbook);
            tabAudioSach.setSelectedIndex(0);
        }else {
            DialogHelper.alert(this,"Hãy Chọn Ebook");
        }
    }//GEN-LAST:event_btnFile2ActionPerformed

    private void btnFile1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFile1ActionPerformed
//        InsertAudio();
        UpdateAudio();
    }//GEN-LAST:event_btnFile1ActionPerformed

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
            java.util.logging.Logger.getLogger(AudiosDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AudiosDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AudiosDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AudiosDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                AudiosDiaLog dialog = new AudiosDiaLog(new javax.swing.JFrame(), true);
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
    private com.ebooks.Compoment.MyButton btnFile;
    private com.ebooks.Compoment.MyButton btnFile1;
    private com.ebooks.Compoment.MyButton btnFile2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDuongDan;
    private javax.swing.JLabel lblExit1;
    private javax.swing.JLabel lblMaSach;
    private javax.swing.JLabel lblNguoiThu;
    private javax.swing.JLabel lblTenAudio;
    private com.ebooks.Compoment.PanelRadius pnlAudio;
    private com.ebooks.Compoment.PanelRadius pnlEbooks;
    private com.ebooks.Compoment.PanelRound pnlExit1;
    private com.ebooks.Compoment.PanelRadius pnlMainDialog;
    private com.ebooks.Compoment.MaterialTabbed tabAudioSach;
    private com.ebooks.Compoment.Table tblEbook;
    private javax.swing.JTextField txtDuongDan;
    private javax.swing.JTextField txtMaSach;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtNguoiThu;
    private javax.swing.JTextField txtTenAudio;
    // End of variables declaration//GEN-END:variables
    
    
    
}
