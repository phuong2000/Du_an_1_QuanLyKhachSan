/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;


import Repositories.dao_QuyenChucVu;
import Services.ChucVuService;
import Services.QuyenChucVu_services;
import Services.Quyen_services;
import ViewModels.ModelChucVu;
import ViewModels.ModelQuyen;
import ViewModels.ModelQuyenChucVu;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 *
 * @author TIENTVPH18954
 */
public class ViewPhanQuyen extends javax.swing.JFrame {

    List<JCheckBox> checkBoxs = new ArrayList<>();
    DefaultComboBoxModel _deComboBoxModel;
    ChucVuService _ChucVu_services = new ChucVuService();
    List<ModelChucVu> _lisChucVus = _ChucVu_services.SelectAll();
    Quyen_services _quQuyen_services = new Quyen_services();
    dao_QuyenChucVu _daoQuyenChucVu = new dao_QuyenChucVu();
    List<ModelQuyenChucVu> _liQuyenChucVus = new ArrayList<>();
    List<ModelQuyen> _lisQuyens = _quQuyen_services.SelectAll();
    QuyenChucVu_services quyenChucVu_services = new QuyenChucVu_services();

    public ViewPhanQuyen() {
        initComponents();
        addListCheckBox();
        cbxChucVu();
        checkQuyen();
        loadQuyen();
        loadDateQuyen();
        setLocationRelativeTo(null);
    }

    void checkQuyen() {
        if (_quQuyen_services.SelectAll().isEmpty()) {
            _quQuyen_services.save(new ModelQuyen(1, "Quan li thue phong"));
            _quQuyen_services.save(new ModelQuyen(2, "Quan li khach san"));
            _quQuyen_services.save(new ModelQuyen(3, "Quan li khach hang"));
            _quQuyen_services.save(new ModelQuyen(4, "Quan li nhan vien"));
            _quQuyen_services.save(new ModelQuyen(5, "Quan li chuc vu"));
            _quQuyen_services.save(new ModelQuyen(6, "Quan li phong"));
            _quQuyen_services.save(new ModelQuyen(7, "Quan li thong ke"));
            _quQuyen_services.save(new ModelQuyen(8, "Quan li dich vu"));
            _quQuyen_services.save(new ModelQuyen(9, "Quan li kho"));
        }
    }

    String getMaChucVu(String ma) {
        for (ModelChucVu x : _lisChucVus) {
            if (x.getTenChucVu().equals(ma)) {
                return x.getMaChucVu();
            }
        }
        return null;
    }

    void addListCheckBox() {
        checkBoxs.add(cb_thuephong);
        checkBoxs.add(cb_khachsan);
        checkBoxs.add(cb_khachhang);
        checkBoxs.add(cb_nhanvien);
        checkBoxs.add(cb_chucvu);
        checkBoxs.add(cb_phong);
        checkBoxs.add(cb_thongke);
        checkBoxs.add(cb_dichvu);
        checkBoxs.add(cb_kho);
    }

    void loadQuyen() {
        _liQuyenChucVus = new ArrayList<>();
        ModelChucVu modelChucVu = _lisChucVus.get(cbx_tenchucvu.getSelectedIndex());
        _liQuyenChucVus = quyenChucVu_services.SelectAll();
        for (ModelQuyenChucVu x : _liQuyenChucVus) {
            if (x.getChucVuModel().getMaChucVu().equals(modelChucVu.getMaChucVu())) {
                System.out.println("x: " + x.getQuyenModel().getTenQuyen());
                for (int i = 0; i < checkBoxs.size(); i++) {
                    if (x.getQuyenModel().getTenQuyen().equals(checkBoxs.get(i).getText())) {
                        System.out.println(x.getQuyenModel().getTenQuyen().equals(checkBoxs.get(i).getText()));
                        System.out.println(checkBoxs.get(i));
                        checkBoxs.get(i).setSelected(true);
                    }
                }
            }
        }
    }

    void loadDateQuyen() {

    }

    void cbxChucVu() {
        _deComboBoxModel = new DefaultComboBoxModel();
        for (ModelChucVu x : _lisChucVus) {
            _deComboBoxModel.addElement(x.getTenChucVu());
        }
        cbx_tenchucvu.setModel(_deComboBoxModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        cbx_tenchucvu = new javax.swing.JComboBox<>();
        cb_thuephong = new javax.swing.JCheckBox();
        cb_khachsan = new javax.swing.JCheckBox();
        cb_khachhang = new javax.swing.JCheckBox();
        cb_nhanvien = new javax.swing.JCheckBox();
        cb_chucvu = new javax.swing.JCheckBox();
        cb_phong = new javax.swing.JCheckBox();
        cb_thongke = new javax.swing.JCheckBox();
        cb_dichvu = new javax.swing.JCheckBox();
        btn_xacnhan = new javax.swing.JButton();
        cb_kho = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PHÂN QUYỀN");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Phân Quyền:");

        cbx_tenchucvu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbx_tenchucvu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_tenchucvuActionPerformed(evt);
            }
        });

        cb_thuephong.setText("Quan li thue phong");

        cb_khachsan.setText("Quan li khach san");

        cb_khachhang.setText("Quan li khach hang");

        cb_nhanvien.setText("Quan li nhan vien");

        cb_chucvu.setText("Quan li chuc vu");

        cb_phong.setText("Quan li phong");

        cb_thongke.setText("Quan li thong ke");

        cb_dichvu.setText("Quan li dich vu");

        btn_xacnhan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_xacnhan.setText("XÁC NHẬN");
        btn_xacnhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xacnhanActionPerformed(evt);
            }
        });

        cb_kho.setText("Quan li kho");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbx_tenchucvu, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cb_khachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74)
                                .addComponent(cb_nhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cb_thuephong, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74)
                                .addComponent(cb_khachsan, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cb_chucvu, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74)
                                .addComponent(cb_phong, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cb_thongke, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74)
                                .addComponent(cb_dichvu, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(btn_xacnhan, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(84, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cb_kho)
                .addGap(251, 251, 251))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_tenchucvu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_thuephong, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_khachsan, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_khachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_nhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_chucvu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_phong, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_thongke, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_dichvu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cb_kho, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(btn_xacnhan, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_xacnhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xacnhanActionPerformed
        String tenQuyen;

        ModelChucVu modelChucVu = _lisChucVus.get(cbx_tenchucvu.getSelectedIndex());
        System.out.println("a: " + modelChucVu.getTenChucVu());
        System.out.println("ma: " + modelChucVu.getMaChucVu());
        quyenChucVu_services.delete(modelChucVu.getMaChucVu());
        for (int i = 0; i < checkBoxs.size(); i++) {
            if (checkBoxs.get(i).isSelected()) {
                quyenChucVu_services.save(new ModelQuyenChucVu(new ModelQuyen(i + 1, checkBoxs.get(i).getText()), modelChucVu));
            }
        }
    }//GEN-LAST:event_btn_xacnhanActionPerformed

    private void cbx_tenchucvuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_tenchucvuActionPerformed
          cb_thuephong.setSelected(false);
         cb_phong.setSelected(false);
        cb_chucvu.setSelected(false);
        cb_dichvu.setSelected(false);
         cb_khachhang.setSelected(false);
        cb_khachsan.setSelected(false);
         cb_nhanvien.setSelected(false);
        cb_thongke.setSelected(false);
        cb_kho.setSelected(false);
         _liQuyenChucVus = new ArrayList<>();
        ModelChucVu modelChucVu = _lisChucVus.get(cbx_tenchucvu.getSelectedIndex());
        _liQuyenChucVus = quyenChucVu_services.SelectAll();
        for (ModelQuyenChucVu x : _liQuyenChucVus) {
            if (x.getChucVuModel().getMaChucVu().equals(modelChucVu.getMaChucVu())) {
                System.out.println("x: " + x.getQuyenModel().getTenQuyen());
                for (int i = 0; i < checkBoxs.size(); i++) {
                    if (x.getQuyenModel().getTenQuyen().equals(checkBoxs.get(i).getText())) {
                        System.out.println(x.getQuyenModel().getTenQuyen().equals(checkBoxs.get(i).getText()));
                        System.out.println(checkBoxs.get(i));
                        checkBoxs.get(i).setSelected(true);
                    }
                }
            }
        }
    }//GEN-LAST:event_cbx_tenchucvuActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewPhanQuyen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewPhanQuyen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewPhanQuyen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewPhanQuyen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewPhanQuyen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_xacnhan;
    private javax.swing.JCheckBox cb_chucvu;
    private javax.swing.JCheckBox cb_dichvu;
    private javax.swing.JCheckBox cb_khachhang;
    private javax.swing.JCheckBox cb_khachsan;
    private javax.swing.JCheckBox cb_kho;
    private javax.swing.JCheckBox cb_nhanvien;
    private javax.swing.JCheckBox cb_phong;
    private javax.swing.JCheckBox cb_thongke;
    private javax.swing.JCheckBox cb_thuephong;
    private javax.swing.JComboBox<String> cbx_tenchucvu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
