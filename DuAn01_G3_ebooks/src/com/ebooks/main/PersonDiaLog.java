/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.ebooks.main;

import com.ebooks.dao.NguoiDungDAO;
import com.ebooks.dao.TaiKhoanDAO;
import com.ebooks.dao.ThucUongDAO;
import com.ebooks.helper.DialogHelper;
import com.ebooks.helper.ShareHelper;
import com.ebooks.model.AudioSach;
import com.ebooks.model.NguoiDung;
import com.sun.security.auth.NTSid;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

/**
 *
 * @author Thinh
 */
public class PersonDiaLog extends javax.swing.JDialog {

    TaiKhoanDAO daoTK = new TaiKhoanDAO();
    NguoiDungDAO daoND = new NguoiDungDAO();
    private String UrlImg = "..\\DuAn01_G3_ebooks\\logos\\";
    private JFileChooser fileChooser = new JFileChooser();
    private String NameImg = "41b92ec3eab97e4c24b3f6e8fe75ddec.png";
    File file;
    List<NguoiDung> listND = new  ArrayList<>();
    NguoiDungDAO DAOND = new NguoiDungDAO();
    public PersonDiaLog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        initMoving(this, pnlMainDialog);

    }

    public PersonDiaLog(java.awt.Frame parent, boolean modal, NguoiDung nd) {
        super(parent, modal);
        initComponents();
        setForm(nd);
        setBackground(new Color(0, 0, 0, 0));
        initMoving(this, pnlMainDialog);
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

    public ImageIcon ShowImg(String nameImg) {
        ImageIcon imgIcon = new ImageIcon(UrlImg + nameImg);
        Image image = imgIcon.getImage();
        Image newimg = image.getScaledInstance(160, 160, java.awt.Image.SCALE_SMOOTH);
        imgIcon = new ImageIcon(newimg);
        return imgIcon;
    }

//    void selectImage() {
//        JFileChooser fileChooser = new JFileChooser();
//        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
//
//            file = fileChooser.getSelectedFile();
//
//            if (ShareHelper.saveLogo(file)) {
//                // Hiển thị hình lên form
//                lblHinh.setIcon(ShareHelper.readLogo(file.getName()));
//                lblHinh.setToolTipText(file.getName());
//                repaint();
//            } else {
//                DialogHelper.alert(this, "Vui lòng chọn file hình ảnh");
//            }
//        }
//    }

    public void setForm(NguoiDung nd) {

        txtMaNguoiDung.setEditable(false);
        txtMaNguoiDung.setText(nd.getMaNguoiDung());
        txtHoTen.setText(nd.getHoTen());
        txtEmail.setText(nd.getEmail());
        txtSoDienThoai.setText(nd.getSoDienThoai());
        if (nd.isGioiTinh()) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
    }

    void insert() {
        if (checkBugs()) {
            NguoiDung model = getModel();
            if (model == null) {
                return;
            }
            try {
                daoND.insert(model);
                AppStatus.mainApp.LoadNguoiDung();
                AppStatus.mainApp.fillTableNguoiDung();
                DialogHelper.alert(this, "Thêm mới thành công!");
            } catch (Exception e) {
                DialogHelper.alert(this, "Mã người dùng đã tồn tại!");
            }
        }
    }

    void update() {
        if (checkBugs()) {
            NguoiDung model = getModel();
            if (model == null) {
                return;
            }
            try {
                daoND.update(model);
                AppStatus.mainApp.LoadNguoiDung();
                AppStatus.mainApp.fillTableNguoiDung();

                if (file == null) {
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                DialogHelper.alert(this, "Cập nhật thất bại!");
            }
        }
    }

    NguoiDung getModel() {

        NguoiDung model = new NguoiDung();
        model.setMaNguoiDung(txtMaNguoiDung.getText());
        model.setHoTen(txtHoTen.getText());
        model.setEmail(txtEmail.getText());
        model.setSoDienThoai(txtSoDienThoai.getText());
        model.setGioiTinh(rdoNam.isSelected() == true ? true : false);

//        try {
//            model.setHinh(file.toString());
//        } catch (Exception e) {
//            DialogHelper.alert(this, "Chưa chọn file hình ảnh");
//            return null;
//        }
        return model;
    }

    boolean checkBugs() {

        Pattern pattern;
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        String numberPhone = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
        pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);

        if (txtMaNguoiDung.getText().equals("")) {
            DialogHelper.alert(this, "Chưa nhập mã người dùng");
            return false;
        } else if (txtHoTen.getText().equals("")) {
            DialogHelper.alert(this, "Chưa nhập họ tên");
            return false;
        } else if (txtEmail.getText().equals("")) {
            DialogHelper.alert(this, "Chưa nhập Email");
            return false;
        } else if (txtSoDienThoai.getText().equals("")) {
            DialogHelper.alert(this, "Chưa nhập số điện thoại");
            return false;
        } else if (!pattern.matcher(txtEmail.getText()).find()) {
            DialogHelper.alert(this, "Email không đúng định dạng");
            return false;
        } 
//        else if (!txtSoDienThoai.getText().matches(numberPhone)) {
//            DialogHelper.alert(this, "Số điện thoại không đúng định dạng");
//            return false;
//        }
        return true;
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
//                    lblHinh.setIcon(imgIcon);
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
        jLabel18 = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        btnSave = new com.ebooks.Compoment.MyButton();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtMaNguoiDung = new javax.swing.JTextField();

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
        });
        pnlExit1.add(lblExit1, new java.awt.GridBagConstraints());

        pnlMainDialog.add(pnlExit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 0, 50, 50));

        jLabel18.setFont(new java.awt.Font("Inter ExtraBold", 0, 26)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(55, 149, 76));
        jLabel18.setText("người dùng");
        pnlMainDialog.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 150, -1));

        txtSoDienThoai.setBackground(new java.awt.Color(222, 247, 227));
        pnlMainDialog.add(txtSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 270, 230, 40));

        jLabel4.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel4.setText("Số Điện Thoại");
        pnlMainDialog.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 240, -1, -1));

        txtHoTen.setBackground(new java.awt.Color(222, 247, 227));
        pnlMainDialog.add(txtHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 180, 230, 40));

        jLabel6.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel6.setText("Mô Tả");
        pnlMainDialog.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 330, -1, -1));

        jLabel2.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel2.setText("Giới Tính");
        pnlMainDialog.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 150, -1, -1));

        txtEmail.setBackground(new java.awt.Color(222, 247, 227));
        pnlMainDialog.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, 220, 40));

        jLabel3.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel3.setText("Địa Chỉ Email");
        pnlMainDialog.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, -1, -1));

        buttonGroup1.add(rdoNam);
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");
        rdoNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNamActionPerformed(evt);
            }
        });
        pnlMainDialog.add(rdoNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 190, -1, -1));

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");
        pnlMainDialog.add(rdoNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 190, -1, -1));

        btnSave.setBackground(new java.awt.Color(87, 190, 110));
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Lưu Thông Tin");
        btnSave.setBoderColor(new java.awt.Color(87, 190, 110));
        btnSave.setFont(new java.awt.Font("Inter SemiBold", 0, 14)); // NOI18N
        btnSave.setRadius(10);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        pnlMainDialog.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 480, 270, 50));

        jLabel19.setFont(new java.awt.Font("Inter ExtraBold", 0, 26)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(55, 149, 76));
        jLabel19.setText("Hãy điền các thông tin");
        pnlMainDialog.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 290, -1));

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane1.setViewportView(txtMoTa);

        pnlMainDialog.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 360, 660, 100));

        jLabel7.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel7.setText("Họ Tên Người Dùng");
        pnlMainDialog.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ebooks/Image/nerds-removebg-preview.png"))); // NOI18N
        pnlMainDialog.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel8.setFont(new java.awt.Font("Inter Medium", 0, 14)); // NOI18N
        jLabel8.setText("Mã người dùng");
        pnlMainDialog.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, -1, -1));

        txtMaNguoiDung.setBackground(new java.awt.Color(222, 247, 227));
        pnlMainDialog.add(txtMaNguoiDung, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 220, 40));

        getContentPane().add(pnlMainDialog, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 550));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblExit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExit1MouseClicked
        // TODO add your handling code here:
        this.dispose();
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

    private void rdoNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNamActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        int timThay = 0;
        listND = DAOND.selectAll();
        for(NguoiDung x : listND){
            if(x.getMaNguoiDung().contains(txtMaNguoiDung.getText())){
                timThay = 1;
            }
        }
        if(timThay == 0) {
            this.insert();
        } else {
            if(DialogHelper.confirm(this, "Chắc chắn cập nhật?")){
                this.update();
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

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
            java.util.logging.Logger.getLogger(PersonDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PersonDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PersonDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PersonDiaLog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PersonDiaLog dialog = new PersonDiaLog(new javax.swing.JFrame(), true);
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
    private com.ebooks.Compoment.MyButton btnSave;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblExit1;
    private com.ebooks.Compoment.PanelRound pnlExit1;
    private com.ebooks.Compoment.PanelRadius pnlMainDialog;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaNguoiDung;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtSoDienThoai;
    // End of variables declaration//GEN-END:variables
}
