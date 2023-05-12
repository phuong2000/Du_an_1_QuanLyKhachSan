/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Services.HoaDon_services;
import Services.Phong_services;
import ViewModels.ModelHoaDon;
import ViewModels.ModelKhachHang;
import ViewModels.ModelKhachTrongPhong;
import ViewModels.ModelPhong;

/**
 *
 * @author TIENTVPH18954
 */
public class viewThongke extends javax.swing.JFrame {

	HoaDon_services _hoaDon_services = new HoaDon_services();
	List<ModelHoaDon> _listModelHoaDon = new ArrayList<>();
	Phong_services _phong_services = new Phong_services();
	List<ModelPhong> _listPhongs = _phong_services.SelectAll();
	SimpleDateFormat _day = new SimpleDateFormat("dd");
	SimpleDateFormat _day1 = new SimpleDateFormat("MM");
	SimpleDateFormat _day2 = new SimpleDateFormat("yyyy");
	java.util.Date datenow = new java.util.Date();

	public viewThongke() {
		initComponents();
		System.out.println("Tổng số phòng: " + _listPhongs.size());
		loadTongSoPhong();
		loadPhongHD();
		phongTrong();
		loadlistKhachTrongHoaDon();
		khachCheckIntrongNgay();
		loadTongSoKhach();
		khachCheckOuttrongNgay();
		loadlistKhachCheckOutHoaDon();
		setLocationRelativeTo(null);
	}

	public void loadTongSoPhong() {
		int tong = _listPhongs.size();
		String tongSoPhong = "Tổng số phòng là : " + tong;
		txt_tongsophong.setText(tongSoPhong);
	}

	public void loadPhongHD() {
		int tong = loadlistHoaDon();
		String tongSoPhong = "Phòng có người là : " + tong;
		txt_hoatdong.setText(tongSoPhong);
	}

	public void phongTrong() {
		int tong = _listPhongs.size() - loadlistHoaDon();
		String tongSoPhong = "Phòng trống là : " + tong;
		txt_trong.setText(tongSoPhong);
	}

	public void loadTongSoKhach() {
		int tong = loadTongKhachTrongHoaDon();
		String tongSoKhach = "Tổng số khách trong ngày : " + tong;
//		System.out.println(tongSoKhach);
		txt_tongsokhach.setText(tongSoKhach);
	}

	public void khachCheckIntrongNgay() {
		int tong = loadlistKhachTrongHoaDon();
		String tongKhachCheckIn = "Khách Checkin  là : " + tong;
//		System.out.println(tongKhachCheckIn);
		txt_khachcheckin.setText(tongKhachCheckIn);
	}

	public void khachCheckOuttrongNgay() {
		int tong = loadlistKhachCheckOutHoaDon();
		String tongKhachCheckout = "Khách CheckOut  là : " + tong;
//		System.out.println(tongKhachCheckout);
		txt_khachcheckout.setText(tongKhachCheckout);
	}

	// Lấy ra các phòng trong hoá đơn
	public int loadlistHoaDon() {
		ModelKhachTrongPhong khachTrongPhong = new ModelKhachTrongPhong();
		List<ModelKhachTrongPhong> listKhachTrongPhongs = new ArrayList<>();
		List<ModelPhong> listPhongHD = new ArrayList<>();
		_listModelHoaDon = _hoaDon_services.getList();
		Date date = null;
		for (ModelHoaDon x : _listModelHoaDon) {
			date = x.getNgayCheckIn();
			String day1 = _day.format(date);
			String day2 = _day.format(datenow);
			if (x.getTrangThai().equals("checkin")) {
				listKhachTrongPhongs = x.getDskhactrongphong();
				for (ModelKhachTrongPhong y : listKhachTrongPhongs) {
					listPhongHD.add(new ModelPhong(y.getPhong().getMaPhong()));
//    				System.out.println("Phòng trong hoá đơn: "+ y.getPhong().getMaPhong());
				}
//    			System.out.println("Hoá đơn: "+ x.toString());
			}
		}
//    	System.out.println("Size Phòng hoa don: "+ listPhongHD.size());
		return listPhongHD.size();
	}

	// Số khách check in trong ngày
	public int loadlistKhachTrongHoaDon() {
		List<ModelKhachHang> listKhachHangHD = new ArrayList<>();
		List<ModelKhachHang> listKhachHang1 = new ArrayList<>();
		_listModelHoaDon = _hoaDon_services.getList();
//		System.out.println("list HDDDD: " + _listModelHoaDon.toString());
		for (ModelHoaDon x : _listModelHoaDon) {
			System.out.println("qqqq: "+x.getTrangThai());
			if (x.getTrangThai().equals("checkin") &&_day.format(datenow).equals(_day.format(x.getNgayCheckIn()))
					&& _day1.format(datenow).equals(_day1.format(x.getNgayCheckIn()))
					&& _day2.format(datenow).equals(_day2.format(x.getNgayCheckIn()))) {
				listKhachHangHD = x.getDskhachhang();
				for (ModelKhachHang o : listKhachHangHD) {
					listKhachHang1.add(o);
				}
//				System.out.println("Trạng thái: "+ x.getTrangThai());
//				System.out.println("Ngay hiện tại: " + _day.format(datenow));
//				System.out.println("Ngày check in: " + _day.format(x.getNgayCheckIn()));
				System.out.println("aaaaaaaaaaaaaaaaa: " + listKhachHang1.size());
			}
		}
		return listKhachHang1.size();
	}

	// Số khách check out trong ngày
	public int loadlistKhachCheckOutHoaDon() {
		List<ModelKhachHang> listKhachHangHD = new ArrayList<>();
		List<ModelKhachHang> listKhachHang1 = new ArrayList<>();
		_listModelHoaDon = _hoaDon_services.getList();
//		System.out.println("list HDDDD: " + _listModelHoaDon.toString());
		for (ModelHoaDon x : _listModelHoaDon) {
//			System.out.println("Checkout: "+x.getTrangThai());
//			System.out.println("Ngày check out: " + _day.format(x.getNgayCheckOut()));
			if (x.getTrangThai().equals("checkout") && _day.format(datenow).equals(_day.format(x.getNgayCheckOut()))
					&& _day1.format(datenow).equals(_day1.format(x.getNgayCheckOut()))
					&& _day2.format(datenow).equals(_day2.format(x.getNgayCheckOut()))) {
				listKhachHangHD = x.getDskhachhang();
				for (ModelKhachHang o : listKhachHangHD) {
					listKhachHang1.add(o);
				}
//				System.out.println("Trạng thái: "+ x.getTrangThai());
//				System.out.println("Ngày check out: " + _day.format(x.getNgayCheckOut()));
//				System.out.println("bbbbbbbbbbbbbbb: " + listKhachHangHD.size());
			}
		}
		return listKhachHang1.size();
	}

	// Tổng số khách trong ngày vừa check in vừa check out.
	public int loadTongKhachTrongHoaDon() {
		List<ModelKhachHang> listKhachHangHD = new ArrayList<>();
		List<ModelKhachHang> listKhachHang1 = new ArrayList<>();
		_listModelHoaDon = _hoaDon_services.getList();
//		System.out.println("list HDDDD: " + _listModelHoaDon.toString());
		for (ModelHoaDon x : _listModelHoaDon) {
			if (_day.format(datenow).equals(_day.format(x.getNgayCheckIn()))
					&& _day1.format(datenow).equals(_day1.format(x.getNgayCheckIn()))
					&& _day2.format(datenow).equals(_day2.format(x.getNgayCheckIn()))) {
				listKhachHangHD = x.getDskhachhang();
				for (ModelKhachHang o : listKhachHangHD) {
					listKhachHang1.add(o);
				}
//				System.out.println("cccccccccccccccccc: " + listKhachHangHD.size());
			}
		}
		return listKhachHang1.size();
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		txt_hoatdong = new javax.swing.JButton();
		txt_tongsophong = new javax.swing.JButton();
		txt_trong = new javax.swing.JButton();
		jLabel2 = new javax.swing.JLabel();
		jSeparator2 = new javax.swing.JSeparator();
		txt_tongsokhach = new javax.swing.JButton();
		txt_khachcheckin = new javax.swing.JButton();
		txt_khachcheckout = new javax.swing.JButton();
		jSeparator4 = new javax.swing.JSeparator();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("THÔNG KÊ TÌNH TRẠNG KHÁCH SẠN");

		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("THÔNG KÊ TÌNH TRẠNG PHÒNG TRONG KHÁCH SẠN");

		txt_hoatdong.setBackground(java.awt.Color.cyan);
		txt_hoatdong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

		txt_tongsophong.setBackground(java.awt.Color.green);
		txt_tongsophong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

		txt_trong.setBackground(java.awt.Color.magenta);
		txt_trong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

		jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel2.setText("THÔNG KÊ TÌNH TRẠNG KHÁCH ");

		txt_tongsokhach.setBackground(java.awt.Color.green);
		txt_tongsokhach.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

		txt_khachcheckin.setBackground(java.awt.Color.cyan);
		txt_khachcheckin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

		txt_khachcheckout.setBackground(java.awt.Color.magenta);
		txt_khachcheckout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(76, 76, 76)
						.addComponent(txt_tongsophong, javax.swing.GroupLayout.PREFERRED_SIZE, 239,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(195, 195, 195)
						.addComponent(txt_hoatdong, javax.swing.GroupLayout.PREFERRED_SIZE, 239,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
						.addComponent(txt_trong, javax.swing.GroupLayout.PREFERRED_SIZE, 239,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(82, 82, 82))
				.addComponent(jSeparator2)
				.addGroup(layout.createSequentialGroup().addGap(75, 75, 75)
						.addComponent(txt_tongsokhach, javax.swing.GroupLayout.PREFERRED_SIZE, 239,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(196, 196, 196)
						.addComponent(txt_khachcheckin, javax.swing.GroupLayout.PREFERRED_SIZE, 239,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txt_khachcheckout, javax.swing.GroupLayout.PREFERRED_SIZE, 239,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(81, 81, 81))
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										layout.createSequentialGroup()
												.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 547,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(335, 335, 335))
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										layout.createSequentialGroup()
												.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 547,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(341, 341, 341))))
				.addComponent(jSeparator4));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addGap(10, 10, 10)
				.addComponent(
						jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(90, 90, 90)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addComponent(txt_trong, javax.swing.GroupLayout.PREFERRED_SIZE, 89,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(txt_hoatdong, javax.swing.GroupLayout.PREFERRED_SIZE, 89,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(txt_tongsophong, javax.swing.GroupLayout.PREFERRED_SIZE, 89,
								javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(40, 40, 40)
				.addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12,
						javax.swing.GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 12,
						javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(45, 45, 45)
				.addComponent(
						jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(36, 36, 36)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(txt_khachcheckin, javax.swing.GroupLayout.Alignment.TRAILING,
												javax.swing.GroupLayout.PREFERRED_SIZE, 89,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(txt_khachcheckout, javax.swing.GroupLayout.Alignment.TRAILING,
												javax.swing.GroupLayout.PREFERRED_SIZE, 89,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGroup(layout.createSequentialGroup()
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txt_tongsokhach, javax.swing.GroupLayout.PREFERRED_SIZE, 89,
										javax.swing.GroupLayout.PREFERRED_SIZE)))
				.addGap(106, 106, 106)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

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
			java.util.logging.Logger.getLogger(viewThongke.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(viewThongke.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(viewThongke.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(viewThongke.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new viewThongke().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JSeparator jSeparator2;
	private javax.swing.JSeparator jSeparator4;
	private javax.swing.JButton txt_hoatdong;
	private javax.swing.JButton txt_khachcheckin;
	private javax.swing.JButton txt_khachcheckout;
	private javax.swing.JButton txt_tongsokhach;
	private javax.swing.JButton txt_tongsophong;
	private javax.swing.JButton txt_trong;
	// End of variables declaration//GEN-END:variables
}
