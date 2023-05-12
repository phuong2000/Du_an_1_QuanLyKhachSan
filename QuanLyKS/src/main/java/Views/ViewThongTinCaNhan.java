/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import Services.ChucVuService;
import Services.NhanVienService;
import ViewModels.ModelChucVu;
import ViewModels.ModelNhanVien;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class ViewThongTinCaNhan extends javax.swing.JFrame {

	NhanVienService _INhanVienIServicers = new NhanVienService();
	ChucVuService _IChuIServicers = new ChucVuService();
	List<ModelChucVu> lisChucVus = _IChuIServicers.SelectAll();
	ModelNhanVien nhanVienModel;

	public ViewThongTinCaNhan(ModelNhanVien nv) {
		initComponents();
		this.nhanVienModel = nv;
		loadThongTinCaNhan(nhanVienModel);
		setLocationRelativeTo(null);

	}

	void loadThongTinCaNhan(ModelNhanVien nv) {
		txt_manv.setText(nv.getMaNV());
		txt_cmtnd.setText(nv.getCmnd());
		txt_date.setDate(nv.getNgaySinh());
		txt_email.setText(nv.getEmail());
		txt_sodienthoai.setText(nv.getSdt());
		txt_tennv.setText(nv.getTenNV());
		txt_trangthai.setText(nv.getTrangThai());
		txt_matkhau.setText(nv.getUserName());
		(nv.getGioiTinh().equals("Nam") ? rdb_nam : rdb_nu).setSelected(true);
		txt_tenChucVu.setText(nv.getChucVuModel().getTenChucVu());
		text_name.setText(nv.getTenNV());

	}

	String getMaChucVu(String ma) {
		for (ModelChucVu x : lisChucVus) {
			if (x.getTenChucVu().equals(ma)) {
				return x.getMaChucVu();
			}
		}
		return "";
	}

	ModelChucVu modelChucVu() {
		return new ModelChucVu(getMaChucVu(txt_tenChucVu.getText()), txt_tenChucVu.getText());
	}

	ModelNhanVien DataNV() {
		return new ModelNhanVien(txt_manv.getText(), txt_tennv.getText(), txt_sodienthoai.getText(),
				rdb_nam.isSelected() ? "Nam" : "Nữ", txt_email.getText(),
				new ModelChucVu(getMaChucVu(txt_tenChucVu.getText()), txt_tenChucVu.getText()), txt_trangthai.getText(),
				txt_cmtnd.getText(), txt_manv.getText(), txt_matkhau.getText(), txt_date.getDate());
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		buttonGroup1 = new javax.swing.ButtonGroup();
		jLabel1 = new javax.swing.JLabel();
		jSeparator1 = new javax.swing.JSeparator();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		txt_tennv = new javax.swing.JTextField();
		jLabel4 = new javax.swing.JLabel();
		txt_sodienthoai = new javax.swing.JTextField();
		jLabel5 = new javax.swing.JLabel();
		txt_email = new javax.swing.JTextField();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		rdb_nam = new javax.swing.JRadioButton();
		rdb_nu = new javax.swing.JRadioButton();
		jLabel8 = new javax.swing.JLabel();
		jLabel9 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		jLabel11 = new javax.swing.JLabel();
		jLabel12 = new javax.swing.JLabel();
		text_name = new javax.swing.JLabel();
		btn_capnhanthongtin = new javax.swing.JButton();
		txt_date = new com.toedter.calendar.JDateChooser();
		txt_tenChucVu = new javax.swing.JLabel();
		txt_trangthai = new javax.swing.JLabel();
		txt_matkhau = new javax.swing.JLabel();
		txt_manv = new javax.swing.JLabel();
		txt_cmtnd = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("THÔNG TIN CÁ NHÂN");
		jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

		jLabel2.setText("Mã nhân viên:");

		jLabel3.setText("Tên nhân viên:");

		jLabel4.setText("Số điện thoại");

		jLabel5.setText("Email:");

		jLabel6.setText("Chứng minh:");

		jLabel7.setText("Giới tính:");

		buttonGroup1.add(rdb_nam);
		rdb_nam.setText("Nam");

		buttonGroup1.add(rdb_nu);
		rdb_nu.setText("Nữ");

		jLabel8.setText("Tên Chúc Vụ");

		jLabel9.setText("Trạng thái:");

		jLabel10.setText("Ngày sinh:");

		jLabel11.setText("Tài Khoản:");

		jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel12.setText("XIN CHÀO:");

		text_name.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

		btn_capnhanthongtin.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		btn_capnhanthongtin.setText("CẬP NHẬP THÔNG TIN CÁ NHÂN");
		btn_capnhanthongtin.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn_capnhanthongtinActionPerformed(evt);
			}
		});

		txt_manv.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

		txt_cmtnd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel1,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(jSeparator1)
				.addGroup(layout.createSequentialGroup().addGroup(layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(134, 134, 134).addGroup(layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addGroup(layout.createSequentialGroup()
										.addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 94,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(txt_cmtnd, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(layout.createSequentialGroup()
										.addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 94,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(txt_email, javax.swing.GroupLayout.DEFAULT_SIZE, 184,
												Short.MAX_VALUE))
								.addGroup(layout.createSequentialGroup()
										.addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 94,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(txt_sodienthoai))
								.addGroup(layout.createSequentialGroup()
										.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 94,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(txt_tennv))
								.addGroup(layout.createSequentialGroup()
										.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 94,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(txt_manv, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addGap(159, 159, 159)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addGroup(layout.createSequentialGroup()
												.addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 94,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18).addComponent(txt_matkhau,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(layout.createSequentialGroup()
												.addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 94,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18).addComponent(txt_trangthai,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(layout.createSequentialGroup()
												.addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 94,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18).addComponent(txt_date,
														javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
										.addGroup(layout.createSequentialGroup()
												.addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 94,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18).addComponent(rdb_nam).addGap(40, 40, 40)
												.addComponent(rdb_nu))
										.addGroup(layout.createSequentialGroup()
												.addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 94,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18).addComponent(txt_tenChucVu,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
						.addGroup(layout.createSequentialGroup().addGap(329, 329, 329)
								.addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 116,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(text_name, javax.swing.GroupLayout.PREFERRED_SIZE, 282,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addGap(352, 352, 352).addComponent(btn_capnhanthongtin,
								javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(173, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(text_name, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(59, 59, 59)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(rdb_nam).addComponent(rdb_nu))
								.addComponent(txt_manv, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(txt_tenChucVu, javax.swing.GroupLayout.Alignment.TRAILING,
										javax.swing.GroupLayout.PREFERRED_SIZE, 36,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(txt_tennv, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(txt_sodienthoai, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addComponent(txt_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(txt_date, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(txt_matkhau, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addComponent(txt_cmtnd, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(50, 50, 50).addComponent(btn_capnhanthongtin, javax.swing.GroupLayout.PREFERRED_SIZE,
								55, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(38, 38, 38)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void btn_capnhanthongtinActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_capnhanthongtinActionPerformed

		int a = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhập thay đổi không?");
		if (a == 0) {
			_INhanVienIServicers.save(DataNV());
			JOptionPane.showMessageDialog(this, "Cập nhập thành công");
		} else {
			JOptionPane.showMessageDialog(this, "Cập nhập thất bại");
		}

	}// GEN-LAST:event_btn_capnhanthongtinActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(ViewThongTinCaNhan.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(ViewThongTinCaNhan.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(ViewThongTinCaNhan.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(ViewThongTinCaNhan.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				NhanVienService lisIServicers = new NhanVienService();
				List<ModelNhanVien> list = lisIServicers.SelectAll();
				new ViewThongTinCaNhan(list.get(0)).setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btn_capnhanthongtin;
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JRadioButton rdb_nam;
	private javax.swing.JRadioButton rdb_nu;
	private javax.swing.JLabel text_name;
	private javax.swing.JLabel txt_cmtnd;
	private com.toedter.calendar.JDateChooser txt_date;
	private javax.swing.JTextField txt_email;
	private javax.swing.JLabel txt_manv;
	private javax.swing.JLabel txt_matkhau;
	private javax.swing.JTextField txt_sodienthoai;
	private javax.swing.JLabel txt_tenChucVu;
	private javax.swing.JTextField txt_tennv;
	private javax.swing.JLabel txt_trangthai;
	// End of variables declaration//GEN-END:variables
}
