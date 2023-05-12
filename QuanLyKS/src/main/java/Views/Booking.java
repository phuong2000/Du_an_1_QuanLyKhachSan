package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Flow;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;
import com.toedter.calendar.JDateChooser;

import Entities.HoaDon;
import Entities.KhachTrongPhong;
import Entities.Phong;
import Entities.PhuTroi;
import Repositories.dao_khachTrongPhong;
import Services.HoaDon_services;
import Services.KhachHang_services;
import Services.KhachSan_services;
import Services.KhachTrongPhong_services;
import Services.ThanhToan_services;
import Services.loaiPhong_services;
import ViewModels.ModelHoaDon;
import ViewModels.ModelKhachHang;
import ViewModels.ModelKhachSan;
import ViewModels.ModelKhachTrongPhong;
import ViewModels.ModelLoaiPhong;
import ViewModels.ModelPhong;
import ViewModels.ModelThanhToan;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.border.EtchedBorder;

public class Booking extends JPanel {
	private JTextField txt_cmnd;
	private JTextField txt_ten;
	private JTextField txt_sdt;
	private JTextField txt_email;
	private JTextField txt_quocTich;
	private JTable table;
	JCheckBox chb_dem = new JCheckBox("Qua đêm");
	JCheckBox chb_ngay = new JCheckBox("Ngày");
	JCheckBox chb_gio = new JCheckBox("Theo giờ");
	JComboBox cbx_gioIn = new JComboBox();;
	JComboBox cbx_phutIn = new JComboBox();;
	JComboBox cbx_gioOut = new JComboBox();;
	JComboBox cbx_phutOut = new JComboBox();;
	JTable tbl_khach;
	JTable tbl_ds;
	JPanel pnl_time = new JPanel();
	JPanel pnl_Gia = new JPanel();
	JPanel pnl_bottom = new JPanel();
	JPanel pnl_Phong = new JPanel();
	JPanel pnl_khachHang = new JPanel();
	JPanel pnl_main = new JPanel();
	JPanel pnl_khachTrongDoan = new JPanel();
	JPanel pnl_danhsach = new JPanel();
	JButton btnT = new JButton("Tiếp theo");
	DefaultTableModel model_ds;
	DefaultTableModel model_k;
	DefaultTableModel model_tt;
	Date max;
	JButton btnNewButton_1 = new JButton("Thêm thanh toán");
	private LocalDate date;
	JDateChooser dcs_checkin = new JDateChooser();
	JDateChooser dcs_checkout = new JDateChooser();
	JCheckBox chb_le;
	JCheckBox chb_doan;
	JTextField txt_doan = new JTextField();

	int gap = 876000;
	List<ModelKhachHang> list_kh;
	List<ModelKhachHang> list_new_kh;
	List<ModelKhachHang> list_choose_kh;
	List<ModelThanhToan> list_tt;
	List<Float> list_gia = new ArrayList<>();
	List<Float> list_pt = new ArrayList<>();
	List<Float> list_pt2 = new ArrayList<>();
	List<ModelHoaDon> list_hd;
	ModelKhachSan ks;
	ModelHoaDon hd;

	List<ModelLoaiPhong> list_lp;
	List<ModelPhong> list_p_chon;
	loaiPhong_services ser_lp;
	KhachHang_services ser_kh;
	KhachSan_services ser_ks;
	ThanhToan_services ser_tt;
	HoaDon_services ser_hd;
	int step = 0, hours, gioIn, phutIn, gioOut, phutOut;
	private JTextField txt_phutroi;
	private JTextField txt_giaphong;
	private JTextField txt_thanhtoan;
	private JTextField txt_conlai;
	boolean checkdcs = true, checkFix = false;
	String loai = "";
	Date in, out;
	LocalDateTime homnay;
	double phutroi = 0, thanhtoan = 0, conlai = 0, gia = 0, phutroi2 = 0;
	ModelHoaDon hoadon;
	private JTable table_1;

	/**
	 * @wbp.parser.constructor
	 */
	public Booking() {
		loai = "ngay";
		// TODO Auto-generated constructor stub
		homnay = LocalDateTime.now();
		hoadon = new ModelHoaDon("datphong", new Date(homnay.getYear() - 1900, homnay.getMonthValue() - 1,
				homnay.getDayOfMonth(), homnay.getHour(), homnay.getMinute()));
		ser_kh = new KhachHang_services();
		ser_lp = new loaiPhong_services();
		ser_hd = new HoaDon_services();
		list_kh = ser_kh.getList();
		list_lp = ser_lp.getLp();
		list_hd = ser_hd.getList();
		ser_tt = new ThanhToan_services();
		ser_ks = new KhachSan_services();
		ks = ser_ks.getKs();
		list_p_chon = new ArrayList<ModelPhong>();
		list_choose_kh = new ArrayList<ModelKhachHang>();
		list_new_kh = new ArrayList<ModelKhachHang>();
		list_tt = new ArrayList<ModelThanhToan>();
		date = LocalDate.now();
		setting();
		events();
		// setupKhachHang();
	}

	public Booking(ModelHoaDon hd) {
		// TODO Auto-generated constructor stub\
		this.hd = hd;
		homnay = LocalDateTime.now();
		hoadon = hd;
		this.loai = hd.getLoai();
		ser_kh = new KhachHang_services();
		ser_lp = new loaiPhong_services();
		ser_hd = new HoaDon_services();
		list_kh = ser_kh.getList();
		list_lp = ser_lp.getLp();
		list_hd = ser_hd.getList();
		ser_tt = new ThanhToan_services();
		ser_ks = new KhachSan_services();
		ks = ser_ks.getKs();
		list_p_chon = new ArrayList<ModelPhong>();
		for (ModelKhachTrongPhong ktp : hd.getDskhactrongphong()) {
			if (ktp.getHoadon().getMaHoaDon() == hd.getMaHoaDon()) {
				list_p_chon.add(ktp.getPhong());
			}
		}
		list_choose_kh = new ArrayList<ModelKhachHang>();
		for (ModelKhachHang kh : hd.getDskhachhang()) {
			for (int i = 0; i < list_kh.size(); i++) {
				if (kh.getMaKhachHang().equals(list_kh.get(i).getMaKhachHang())) {
					list_choose_kh.add(list_kh.get(i));
				}
			}
		}
		list_new_kh = new ArrayList<ModelKhachHang>();
		list_tt = ser_tt.getList(hd.getMaHoaDon());
		date = LocalDate.now();
		checkFix = true;
		setting();
		events();
		dcs_checkin.setDate(hd.getNgayCheckIn());
		dcs_checkout.setDate(hd.getNgayCheckOut());
//		KhachTrongPhong_services serktp=new KhachTrongPhong_services();
//		List<ModelKhachTrongPhong> listKpt=serktp.getList();
//		for(ModelKhachTrongPhong x:listKpt) {
//			if(hd.getMaHoaDon()==x.getHoadon().getMaHoaDon()) {
//				list_gia.add(x.getGiaPhong());
//				list_pt.add(x.getPhuTroi());
//				list_pt2.add(x.getPhutroi2());
//			}
//		}
		step();
		ThanhToan();
		tinhtien();
		chb_doan.setEnabled(true);
		chb_le.setEnabled(true);
		if (hd.getLoai().equals("ngay"))
			chb_ngay.setSelected(true);
		else if (hd.getLoai().equals("dem"))
			chb_dem.setSelected(true);
		else
			chb_gio.setSelected(true);
		if (!hd.getTenDoan().isBlank()) {
			chb_doan.setSelected(true);
			txt_doan.setText(hd.getTenDoan());
		}
	}

	void step() {
		if (step == 0) {
			if (dcs_checkin.getDate() == null || dcs_checkout.getDate() == null) {
				JOptionPane.showMessageDialog(pnl_main, "chọn ngày check in và check out trước");
				return;
			}
			if (chb_doan.isSelected() && txt_doan.getText().isBlank()) {
				JOptionPane.showMessageDialog(pnl_main, "Vui lòng nhập tên đoàn");
				return;
			}
		}
		step++;
		if (step == 1) {
			if (chb_doan.isSelected() && txt_doan.getText().isBlank()) {
				JOptionPane.showMessageDialog(pnl_main, "Vui lòng nhập tên đoàn");
				return;
			}
			setupKhachHang();
			for (int i = 0; i < pnl_time.getComponentCount(); i++) {
				pnl_time.getComponent(i).setEnabled(false);
			}
			revalidate();
			repaint();
			list_kh = ser_kh.getList();
		} else if (step == 2) {
			if (list_choose_kh.size() == 0 && list_new_kh.size() == 0) {
				JOptionPane.showMessageDialog(pnl_main, "danh sách khách trống");
				step--;
				return;
			}
			for (ModelKhachHang kh : list_new_kh) {
				ser_kh.themsua(kh);
			}
			ThemPhong();
			tbl_ds.setEnabled(false);
			tbl_khach.setEnabled(false);
			for (int i = 0; i < pnl_khachHang.getComponentCount(); i++) {
				pnl_khachHang.getComponent(i).setEnabled(false);
			}
			for (int i = 0; i < pnl_khachTrongDoan.getComponentCount(); i++) {
				pnl_khachTrongDoan.getComponent(i).setEnabled(false);
			}
			for (int i = 0; i < pnl_danhsach.getComponentCount(); i++) {
				pnl_danhsach.getComponent(i).setEnabled(false);
			}

			revalidate();
			repaint();
		} else if (step == 3) {
			int ng = 0;
//			for(ModelPhong p:list_p_chon) {
//				ng+=p.getLoaiphong().getSoNguoi();
//			}
//			System.out.println("ng: "+ng);
//			if(list_new_kh.size()+list_choose_kh.size()>ng) {
//				JOptionPane.showMessageDialog(pnl_main, "Không đủ phòng cho đoàn");
//				step--;
//				return;
//			}
			btnNewButton_1.setEnabled(true);
			btnT.setText("Xác nhận");
		}
		if (step == 4) {
			hoadon.setNgayCheckIn(in);
			hoadon.setNgayCheckOut(out);
			List<ModelKhachHang> dskh = list_choose_kh;
			for (ModelKhachHang kh : list_new_kh) {
				dskh.add(kh);
			}
			hoadon.setDskhachhang(dskh);
			hoadon.setLoai(loai);
			if (chb_le.isSelected())
				hoadon.setTenDoan("");
			else
				hoadon.setTenDoan(txt_doan.getText());
			ser_hd.save(hoadon, !checkFix);
			dao_khachTrongPhong d = new dao_khachTrongPhong();
			List<ModelHoaDon> dsmhd = ser_hd.getList();
			int id = 0;
			for (ModelPhong p : list_p_chon) {
				if (checkFix) {
					try {
						d.save(new KhachTrongPhong(hd.getDskhactrongphong().get(id).getId(),
								new HoaDon(hd.getMaHoaDon()), new Phong(p.getMaPhong()), list_gia.get(id),
								list_pt.get(id), list_pt2.get(id), 0, ""));
					} catch (Exception edsadssdfdsf) {
						d.save(new KhachTrongPhong(new HoaDon(hd.getMaHoaDon()), new Phong(p.getMaPhong()),
								list_gia.get(id), list_pt.get(id), list_pt2.get(id), 0, ""));
					}
				} else {
					d.save(new KhachTrongPhong(new HoaDon(dsmhd.get(dsmhd.size() - 1).getMaHoaDon()),
							new Phong(p.getMaPhong()), list_gia.get(id), list_pt.get(id), list_pt2.get(id), 0, ""));
				}
				id++;
			}
			for (ModelThanhToan tt : list_tt) {
				tt.setHoadon(new ModelHoaDon(dsmhd.get(dsmhd.size() - 1).getMaHoaDon()));
				ser_tt.save(tt);
			}
			JOptionPane.showMessageDialog(pnl_main, "Đặt phòng thành công");

		}
	}

	void events() {
		dcs_checkin.addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evtu) {
				// TODO Auto-generated method stub
				if (dcs_checkin.getDate() != null) {
					System.out.println("chay vao in");
					if (!checkFix) {
						chb_dem.setEnabled(true);
						chb_gio.setEnabled(true);
						chb_ngay.setEnabled(true);
						dcs_checkout.setEnabled(true);
					}
					if (dcs_checkout.getDate() != null
							&& dcs_checkout.getDate().getTime() - dcs_checkin.getDate().getTime() < 0) {
						dcs_checkout.setDate(null);
						System.out.println("dk chinh");
					}
					dcs_checkout.setMinSelectableDate(dcs_checkin.getDate());
					System.out.println(new Date(date.getYear() - 1900, date.getMonthValue(), date.getDayOfMonth()));

					homnay = LocalDateTime.now();
					Integer[] a;
					DefaultComboBoxModel<Integer> model_in;
					if (checkFix) {
						a = new Integer[1];
						a[0] = hd.getNgayCheckIn().getHours();
						model_in = new DefaultComboBoxModel<Integer>(a);
						cbx_gioIn.setModel(model_in);
					} else if (loai.equals("gio")) {
						if ((24 - Integer.parseInt(cbx_gioIn.getSelectedItem() + "") < ks.getGio())) {
							dcs_checkout.setMaxSelectableDate(new Date(dcs_checkin.getDate().getYear(),
									dcs_checkin.getDate().getMonth(), dcs_checkin.getDate().getDate() + 1));
						} else {
							dcs_checkout.setMinSelectableDate(dcs_checkin.getDate());
						}
					} else if (loai.equals("dem")) {
						int cis = 0, cos = 0;
						for (int i = 0; i < list_lp.size(); i++) {
							for (PhuTroi pt : list_lp.get(i).getDsphutroi()) {
								if (pt.getLoai().equals("checkindem")) {
									cis++;
								}
								if (pt.getLoai().equals("checkoutdem")) {
									cos++;
								}
							}
						}
						DefaultComboBoxModel<Integer> model_out = new DefaultComboBoxModel<Integer>();
						a = new Integer[24 - ks.getGioCheckInDem() / 100 + cis];
						int I = 0;
						for (int i = ks.getGioCheckInDem() / 100 - cis; i < 24; i++) {
							System.out.println(ks.getGioCheckInDem() / 100 + "sadasdasdas");
							a[I] = i;
							model_out.addElement(a[I]);
							I++;
						}
						Integer b[] = new Integer[ks.getGioCheckOutDem() / 100 + cos];
						for (int i = 0; i < ks.getGioCheckOutDem() / 100 + cos - 1; i++) {
							b[i] = i;
							model_out.addElement(b[i]);
						}
						cbx_gioIn.setModel(model_out);
						if (dcs_checkin.getDate().getDate() == homnay.getDayOfMonth()
								&& dcs_checkin.getDate().getMonth() == (homnay.getMonthValue() - 1)
								&& dcs_checkin.getDate().getYear() + 1900 == homnay.getYear()) {
							for (int i = 0; i < 24; i++)
								if (i < homnay.getHour()) {
									model_out.removeElement(i);
								}
						}
					} else if (dcs_checkin.getDate().getDate() == homnay.getDayOfMonth()
							&& dcs_checkin.getDate().getMonth() == (homnay.getMonthValue() - 1)
							&& dcs_checkin.getDate().getYear() + 1900 == homnay.getYear()) {
						a = new Integer[24 - homnay.getHour()];
						int b = homnay.getHour();
						for (int i = 0; i < a.length; i++) {
							a[i] = b + i;
						}
						model_in = new DefaultComboBoxModel<Integer>(a);
						model_in = new DefaultComboBoxModel<Integer>(a);
						cbx_gioIn.setModel(model_in);
					}

					else {
						a = new Integer[24];
						for (int i = 0; i < 24; i++) {
							a[i] = i;
						}
						model_in = new DefaultComboBoxModel<Integer>(a);
						cbx_gioIn.setModel(model_in);
						cbx_gioIn.setSelectedItem(gioIn);
						System.out.println(cbx_gioIn.getSelectedItem());
					}
//					model_in = new DefaultComboBoxModel<Integer>(a);
//					cbx_gioIn.setModel(model_in);
//					cbx_gioIn.setSelectedItem(ks.getGioCheckIn() / 100);
					Integer b[];
					if (checkFix) {
						b = new Integer[1];
						b[0] = hd.getNgayCheckIn().getMinutes();
					} else if (dcs_checkin.getDate().getDate() == homnay.getDayOfMonth()
							&& dcs_checkin.getDate().getMonth() == (homnay.getMonthValue() - 1)
							&& dcs_checkin.getDate().getYear() + 1900 == homnay.getYear()
							&& String.valueOf(cbx_gioIn.getSelectedItem()).equals(String.valueOf(homnay.getHour()))) {
						b = new Integer[60 - homnay.getMinute()];
						for (int i = 0; i < b.length; i++) {
							b[i] = i + (homnay.getMinute());
						}
					} else {
						b = new Integer[60];
						for (int i = 0; i < b.length; i++) {
							b[i] = i;
						}
					}
					DefaultComboBoxModel<Integer> model_inp = new DefaultComboBoxModel<Integer>(b);
					cbx_phutIn.setModel(model_inp);
					cbx_phutIn.setSelectedIndex(0);
					try {
						gioIn = Integer.parseInt(String.valueOf(cbx_gioIn.getSelectedItem()));
						phutIn = Integer.parseInt(String.valueOf(cbx_phutIn.getSelectedItem()));
					} catch (Exception adfdf) {

					}
					in = new Date(dcs_checkin.getDate().getYear(), dcs_checkin.getDate().getMonth(),
							dcs_checkin.getDate().getDate(), gioIn, phutIn);
					if (dcs_checkout.getDate() != null && dcs_checkout.getDate().getTime() > max.getTime()) {
						dcs_checkout.setDate(null);
						System.out.println("dk phu");
					}
					try {
						cbx_gioIn.setSelectedItem(gioIn);
						cbx_gioOut.setSelectedItem(gioOut);
						System.out.println(gioOut + " gio OUT");
						cbx_phutIn.setSelectedItem(phutIn);
						cbx_phutOut.setSelectedItem(phutOut);
						hours = (int) ((out.getTime() - in.getTime()) / 3600000);
						if (((double) (out.getTime() - in.getTime()) / 3600000) > hours) {
							hours++;
						}
					} catch (Exception e) {

					}
					max = new Date(dcs_checkin.getDate().getYear(), dcs_checkin.getDate().getMonth(),
							dcs_checkin.getDate().getDate(), gioIn + gap, phutIn);
					dcs_checkout.setMaxSelectableDate(max);
					if (!checkFix) {
						cbx_gioIn.setEnabled(true);
						cbx_phutIn.setEnabled(true);
					}
				}
			}
		});
		cbx_gioIn.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				Integer b[];
				if (dcs_checkin.getDate().getDate() == homnay.getDayOfMonth()
						&& dcs_checkin.getDate().getMonth() == (homnay.getMonthValue() - 1)
						&& dcs_checkin.getDate().getYear() + 1900 == homnay.getYear()
						&& String.valueOf(cbx_gioIn.getSelectedItem()).equals(String.valueOf(homnay.getHour()))) {
					b = new Integer[60 - homnay.getMinute()];
					for (int i = 0; i < b.length; i++) {
						b[i] = i + (60 - homnay.getMinute());
					}
				} else {
					b = new Integer[60];
					for (int i = 0; i < b.length; i++) {
						b[i] = i;
					}
				}
				DefaultComboBoxModel<Integer> model_inp = new DefaultComboBoxModel<Integer>(b);
				cbx_phutIn.setModel(model_inp);
				gioIn = Integer.parseInt(String.valueOf(cbx_gioIn.getSelectedItem()));
				in = new Date(dcs_checkin.getDate().getYear(), dcs_checkin.getDate().getMonth(),
						dcs_checkin.getDate().getDate(), gioIn, phutIn);
				try {
					hours = (int) ((out.getTime() - in.getTime()) / 3600000);
					if (((double) (out.getTime() - in.getTime()) / 3600000) > hours) {
						hours++;
					}
				} catch (Exception ex) {

				}
				if (loai.equals("dem") && dcs_checkout.getDate() != null) {
					Integer[] a;
					DefaultComboBoxModel<Integer> model_out;
					int cis = 0, cos = 0;
					for (int i = 0; i < list_lp.size(); i++) {
						for (PhuTroi pt : list_lp.get(i).getDsphutroi()) {
							if (pt.getLoai().equals("checkindem")) {
								cis++;
							}
							if (pt.getLoai().equals("checkoutdem")) {
								cos++;
							}
						}
					}
					if (in.getDate() - 1 == dcs_checkout.getDate().getDate()) {
						a = new Integer[24 - Integer.parseInt(cbx_gioIn.getSelectedItem() + "") - 1];
						for (int i = Integer.parseInt(cbx_gioIn.getSelectedItem() + "") + 1; i < 24; i++) {
							a[i - Integer.parseInt(cbx_gioIn.getSelectedItem() + "") - 1] = i;
						}
						model_out = new DefaultComboBoxModel<Integer>(a);
					} else {
						b = new Integer[ks.getGioCheckOutDem() / 100 + cos];
						for (int i = 0; i < ks.getGioCheckOutDem() / 100 + cos; i++) {
							System.out.println(i);
							b[i] = i;
						}
						model_out = new DefaultComboBoxModel<Integer>(b);
					}
					cbx_gioOut.setModel(model_out);
					if (in.getDate() - 1 == dcs_checkout.getDate().getDate()) {
						a = new Integer[24 - Integer.parseInt(cbx_gioIn.getSelectedItem() + "") - 1];
						for (int i = Integer.parseInt(cbx_gioIn.getSelectedItem() + "") + 1; i < 24; i++) {
							a[i - Integer.parseInt(cbx_gioIn.getSelectedItem() + "") - 1] = i;
						}
						model_out = new DefaultComboBoxModel<Integer>(a);
					} else {
						b = new Integer[ks.getGioCheckOutDem() / 100 + cos];
						for (int i = 0; i < ks.getGioCheckOutDem() / 100 + cos; i++) {
							System.out.println(i);
							b[i] = i;
						}
						model_out = new DefaultComboBoxModel<Integer>(b);
					}
					cbx_gioOut.setModel(model_out);
					if (dcs_checkin.getDate().getDate() == dcs_checkout.getDate().getDate()
							&& dcs_checkin.getDate().getMonth() == dcs_checkout.getDate().getMonth()
							&& dcs_checkin.getDate().getYear() == dcs_checkout.getDate().getYear()) {
						DefaultComboBoxModel<Integer> model = (DefaultComboBoxModel<Integer>) cbx_gioOut.getModel();
						for (int i = 0; i < 24; i++) {
							if (i < Integer.parseInt(cbx_gioIn.getSelectedItem() + "")) {
								model.removeElement(i);
							}
						}
					}
				}
				if (loai.equals("gio")) {
					Date dateMax = new Date(dcs_checkin.getDate().getYear(), dcs_checkin.getDate().getMonth(),
							dcs_checkin.getDate().getDate() + 1, 23, 59);
					if ((24 - Integer.parseInt(cbx_gioIn.getSelectedItem() + "") < ks.getGio())) {
						dcs_checkout.setMaxSelectableDate(new Date(dcs_checkin.getDate().getYear(),
								dcs_checkin.getDate().getMonth(), dcs_checkin.getDate().getDate() + 1));
					} else {
						dcs_checkout.setMinSelectableDate(dcs_checkin.getDate());
					}
					if (dcs_checkout.getDate().getTime() > dateMax.getTime()) {
						dcs_checkout.setDate(null);
					}
				}
				if (dcs_checkout.getDate() != null) {
					int cis = 0, cos = 0;
					for (int i = 0; i < list_lp.size(); i++) {
						for (PhuTroi pt : list_lp.get(i).getDsphutroi()) {
							if (pt.getLoai().equals("checkindem")) {
								cis++;
							}
							if (pt.getLoai().equals("checkoutdem")) {
								cos++;
							}
						}
					}
				}
			}
		});

		cbx_phutIn.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				phutIn = Integer.parseInt(String.valueOf(cbx_phutIn.getSelectedItem()));
				in = new Date(dcs_checkin.getDate().getYear(), dcs_checkin.getDate().getMonth(),
						dcs_checkin.getDate().getDate(), gioIn, phutIn);
				try {
					hours = (int) ((out.getTime() - in.getTime()) / 3600000);
					if (((double) (out.getTime() - in.getTime()) / 3600000) > hours) {
						hours++;
					}
				} catch (Exception ex) {

				}
			}
		});
		dcs_checkout.addPropertyChangeListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				if (dcs_checkout.getDate() != null) {
					Integer[] a;
					DefaultComboBoxModel<Integer> model_out;
					if (checkFix) {
						DefaultComboBoxModel<Integer> model_gout = new DefaultComboBoxModel<Integer>(
								new Integer[] { hd.getNgayCheckOut().getHours() });
						cbx_gioOut.setModel(model_gout);
					} else if (loai.equals("gio")) {
						Date dateMax = new Date(dcs_checkin.getDate().getYear(), dcs_checkin.getDate().getMonth(),
								dcs_checkin.getDate().getDate() + 1, 23, 59);
						if (dcs_checkin.getDate().getDate() == dcs_checkout.getDate().getDate()) {
							a = new Integer[ks.getGio()];
							for (int i = Integer.parseInt(cbx_gioIn.getSelectedItem() + ""); i < Integer
									.parseInt(cbx_gioIn.getSelectedItem() + "") + ks.getGio(); i++) {
								a[i - Integer.parseInt(cbx_gioIn.getSelectedItem() + "")] = i + 1;
							}
							model_out = new DefaultComboBoxModel<Integer>(a);
							cbx_gioOut.setModel(model_out);
						} else {
							int l = ks.getGio() - 23 + Integer.parseInt(cbx_gioIn.getSelectedItem() + "");
							System.out.println("gia tri cua l=" + l);
							a = new Integer[l + 1];
							for (int i = 0; i <= l; i++) {
								a[i] = i;
							}
							model_out = new DefaultComboBoxModel<Integer>(a);
							cbx_gioOut.setModel(model_out);
						}
					} else if (loai.equals("dem") && dcs_checkout.getDate() != null) {
						System.out.println("vao dem sdsadasdasdasdasdasd");
						int cis = 0, cos = 0;
						for (int i = 0; i < list_lp.size(); i++) {
							for (PhuTroi pt : list_lp.get(i).getDsphutroi()) {
								if (pt.getLoai().equals("checkindem")) {
									cis++;
								}
								if (pt.getLoai().equals("checkoutdem")) {
									cos++;
								}
							}
						}
						if (in.getDate() - 1 == dcs_checkout.getDate().getDate()) {
							a = new Integer[24 - Integer.parseInt(cbx_gioIn.getSelectedItem() + "") - 1];
							for (int i = Integer.parseInt(cbx_gioIn.getSelectedItem() + "") + 1; i < 24; i++) {
								a[i - Integer.parseInt(cbx_gioIn.getSelectedItem() + "") - 1] = i;
							}
							model_out = new DefaultComboBoxModel<Integer>(a);
						} else {
							Integer b[] = new Integer[ks.getGioCheckOutDem() / 100 + cos];
							for (int i = 0; i < ks.getGioCheckOutDem() / 100 + cos; i++) {
								System.out.println(i);
								b[i] = i;
							}
							model_out = new DefaultComboBoxModel<Integer>(b);
						}
						cbx_gioOut.setModel(model_out);
					} else if (dcs_checkin.getDate().getDate() == dcs_checkout.getDate().getDate()
							&& dcs_checkin.getDate().getMonth() == dcs_checkout.getDate().getMonth()
							&& dcs_checkin.getDate().getYear() == dcs_checkout.getDate().getYear()) {
						a = new Integer[24 - gioIn - 1];
						int b = in.getHours() + 1;
						for (int i = 0; b + i < 24; i++) {
							a[i] = b + i;
						}
						model_out = new DefaultComboBoxModel<Integer>(a);
						cbx_gioOut.setModel(model_out);
					} else {
						a = new Integer[24];
						for (int i = 0; i < 24; i++) {
							a[i] = i;
						}
						model_out = new DefaultComboBoxModel<Integer>(a);
						cbx_gioOut.setModel(model_out);
					}
					cbx_gioOut.setSelectedItem(gioOut);
					Integer b[];
					b = new Integer[60];
					for (int i = 0; i < b.length; i++) {
						b[i] = i;
					}
					if (checkFix) {
						b = new Integer[1];
						b[0] = hd.getNgayCheckOut().getMinutes();
					}
					DefaultComboBoxModel<Integer> model_outp = new DefaultComboBoxModel<Integer>(b);
					cbx_phutOut.setModel(model_outp);
					System.out.println("gio out " + gioOut);
					gioOut = Integer.parseInt(String.valueOf(cbx_gioOut.getSelectedItem()));
					System.out.println("gio out " + gioOut);
					phutOut = Integer.parseInt(String.valueOf(cbx_phutOut.getSelectedItem()));
					out = new Date(dcs_checkout.getDate().getYear(), dcs_checkout.getDate().getMonth(),
							dcs_checkout.getDate().getDate(), gioOut, phutOut);
					hours = (int) ((out.getTime() - in.getTime()) / 3600000);
					if (((double) (out.getTime() - in.getTime()) / 3600000) > hours) {
						hours++;
					}
				}
				if (!checkFix) {
					cbx_gioOut.setEnabled(true);
					cbx_phutOut.setEnabled(true);
				}
				cbx_gioIn.setSelectedItem(gioIn);
				cbx_gioOut.setSelectedItem(gioOut);
				System.out.println(gioOut + " gio OUT OUT");
				cbx_phutIn.setSelectedItem(phutIn);
				cbx_phutOut.setSelectedItem(phutOut);
				return;
			}
		});
		cbx_gioOut.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				gioOut = Integer.parseInt(String.valueOf(cbx_gioOut.getSelectedItem()));
				try {
					phutOut = Integer.parseInt(String.valueOf(cbx_phutOut.getSelectedItem()));
					hours = (int) ((out.getTime() - in.getTime()) / 3600000);
					if (((double) (out.getTime() - in.getTime()) / 3600000) > hours) {
						hours++;
					}
				} catch (Exception ex) {

				}
			}
		});

		cbx_phutOut.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				phutOut = Integer.parseInt(String.valueOf(cbx_phutOut.getSelectedItem()));
				try {
					gioOut = Integer.parseInt(String.valueOf(cbx_gioOut.getSelectedItem()));
					hours = (int) ((out.getTime() - in.getTime()) / 3600000);
					if (((double) (out.getTime() - in.getTime()) / 3600000) > hours) {
						hours++;
					}
				} catch (Exception ex) {

				}
			}
		});
	}

	void setting() {
		setLayout(null);
		setPreferredSize(new Dimension(1280, 720));
		setLayout(new FlowLayout());
		add(pnl_main);
		pnl_main.setLayout(new BorderLayout());
		pnl_main.setPreferredSize(new Dimension(1280, 720));
		pnl_bottom.setPreferredSize(new Dimension(1280, 90));
		pnl_bottom.setLayout(null);
		pnl_Gia.setBorder(
				new TitledBorder(null, "Thanh to\u00E1n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_main.add(pnl_time, BorderLayout.NORTH);
		pnl_main.add(pnl_khachHang, BorderLayout.WEST);
		pnl_main.add(pnl_Gia, BorderLayout.EAST);
		pnl_main.add(pnl_bottom, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("Quay lại");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(10, 10, 117, 30);
		pnl_bottom.add(btnNewButton);

		btnT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				step();
			}
		});
		btnT.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnT.setBounds(1153, 15, 117, 30);
		pnl_bottom.add(btnT);

		pnl_time.setBorder(BorderFactory.createTitledBorder("Thời Gian"));
		pnl_time.setPreferredSize(new Dimension(1280, 80));
		pnl_khachHang.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));
		pnl_khachHang.setPreferredSize(new Dimension(427, 670));
		pnl_khachHang.setLayout(null);
		pnl_Gia.setPreferredSize(new Dimension(426, 670));
		pnl_Gia.setLayout(null);

		pnl_time.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
		JLabel lbl_checkin = new JLabel("Check in:");
		lbl_checkin.setFont(new Font("TAHOMA", Font.PLAIN, 17));
		pnl_time.add(lbl_checkin);

		cbx_gioIn.setEnabled(false);
		cbx_phutIn.setEnabled(false);

		dcs_checkin.setPreferredSize(new Dimension(110, 20));
		dcs_checkin
				.setMinSelectableDate(new Date(date.getYear() - 1900, date.getMonthValue() - 1, date.getDayOfMonth()));
		System.out.println(date.getYear() + "/" + date.getMonthValue() + "/" + date.getDayOfMonth());
		dcs_checkin.setDateFormatString("dd/MM/yyyy");

		pnl_time.add(dcs_checkin);
		pnl_time.add(cbx_gioIn);

		JLabel lblNewLabel_2 = new JLabel("Giờ");
		pnl_time.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnl_time.add(cbx_phutIn);

		JLabel lblNewLabel_2_1 = new JLabel("Phút  |");
		pnl_time.add(lblNewLabel_2_1);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lbl_checkin_1 = new JLabel("Check out:");
		lbl_checkin_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnl_time.add(lbl_checkin_1);

		pnl_time.add(dcs_checkout);

		cbx_gioOut.setEnabled(false);
		pnl_time.add(cbx_gioOut);

		JLabel lblNewLabel_2_3 = new JLabel("Giờ");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnl_time.add(lblNewLabel_2_3);

		cbx_phutOut.setEnabled(false);
		pnl_time.add(cbx_phutOut);

		JLabel lblNewLabel_2_1_1 = new JLabel("Phút  |");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnl_time.add(lblNewLabel_2_1_1);
		dcs_checkout.setPreferredSize(new Dimension(110, 20));
		dcs_checkout.setEnabled(false);

		JLabel lbl_loai = new JLabel("Loại khách: ");
		lbl_loai.setFont(new Font("TAHOMA", Font.PLAIN, 16));
		pnl_time.add(lbl_loai);

		chb_le = new JCheckBox("khách lẻ");
		chb_le.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txt_doan.setEnabled(false);
				txt_doan.setText("");
			}
		});
		chb_le.setFont(new Font("TAHOMA", Font.PLAIN, 16));
		pnl_time.add(chb_le);

		chb_doan = new JCheckBox("khách đoàn");
		chb_doan.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txt_doan.setEnabled(true);
				if (checkFix)
					txt_doan.setText(hd.getTenDoan());
			}
		});
		chb_doan.setFont(new Font("TAHOMA", Font.PLAIN, 16));
		pnl_time.add(chb_doan);

		ButtonGroup btg_khach = new ButtonGroup();
		btg_khach.add(chb_doan);
		btg_khach.add(chb_le);
		chb_le.setSelected(true);
		txt_doan.setEnabled(false);
		txt_doan.setText("");

		JLabel lbl_tenDoan = new JLabel("tên đoàn");
		lbl_tenDoan.setFont(new Font("TAHOMA", Font.PLAIN, 16));
		pnl_time.add(lbl_tenDoan);

		pnl_time.add(txt_doan);
		txt_doan.setFont(new Font("TAHOMA", Font.PLAIN, 16));
		txt_doan.setPreferredSize(new Dimension(90, 20));

		JLabel lblNewLabel_3 = new JLabel("Khung giờ:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnl_time.add(lblNewLabel_3);

		chb_ngay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chb_ngay.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gap = 876000;
				loai = "ngay";
				max = new Date(dcs_checkin.getDate().getYear(), dcs_checkin.getDate().getMonth(),
						dcs_checkin.getDate().getDate(), gioIn + gap, phutIn);
				dcs_checkout.setMaxSelectableDate(max);

				if (dcs_checkin.getDate() != null) {
					System.out.println("chay vao in");
					if (!checkFix) {
						chb_dem.setEnabled(true);
						chb_gio.setEnabled(true);
						chb_ngay.setEnabled(true);
						dcs_checkout.setEnabled(true);
					}
					if (dcs_checkout.getDate() != null
							&& dcs_checkout.getDate().getTime() - dcs_checkin.getDate().getTime() < 0) {
						dcs_checkout.setDate(null);
						System.out.println("dk chinh");
					}
					dcs_checkout.setMinSelectableDate(dcs_checkin.getDate());
					System.out.println(new Date(date.getYear() - 1900, date.getMonthValue(), date.getDayOfMonth()));

					homnay = LocalDateTime.now();
					Integer[] a;
					DefaultComboBoxModel<Integer> model_in;
					if (checkFix) {
						a = new Integer[1];
						a[0] = hd.getNgayCheckIn().getHours();
					} else if (loai.equals("dem")) {
						int cis = 0, cos = 0;
						for (int i = 0; i < list_lp.size(); i++) {
							for (PhuTroi pt : list_lp.get(i).getDsphutroi()) {
								if (pt.getLoai().equals("checkindem")) {
									cis++;
								}
								if (pt.getLoai().equals("checkoutdem")) {
									cos++;
								}
							}
						}
						DefaultComboBoxModel<Integer> model_out = new DefaultComboBoxModel<Integer>();
						a = new Integer[24 - ks.getGioCheckInDem() / 100 + cis];
						int I = 0;
						for (int i = ks.getGioCheckInDem() / 100 - cis; i < 24; i++) {
							System.out.println(ks.getGioCheckInDem() / 100 + "sadasdasdas");
							a[I] = i;
							model_out.addElement(a[I]);
							I++;
						}
						Integer b[] = new Integer[ks.getGioCheckOutDem() / 100 + cos];
						for (int i = 0; i < ks.getGioCheckOutDem() / 100 + cos - 1; i++) {
							b[i] = i;
							model_out.addElement(b[i]);
						}
						cbx_gioIn.setModel(model_out);
					} else if (dcs_checkin.getDate().getDate() == homnay.getDayOfMonth()
							&& dcs_checkin.getDate().getMonth() == (homnay.getMonthValue() - 1)
							&& dcs_checkin.getDate().getYear() + 1900 == homnay.getYear()) {
						a = new Integer[24 - homnay.getHour()];
						int b = homnay.getHour();
						for (int i = 0; i < a.length; i++) {
							a[i] = b + i;
						}
						model_in = new DefaultComboBoxModel<Integer>(a);
						model_in = new DefaultComboBoxModel<Integer>(a);
						cbx_gioIn.setModel(model_in);
					}

					else {
						a = new Integer[24];
						for (int i = 0; i < 24; i++) {
							a[i] = i;
						}
						model_in = new DefaultComboBoxModel<Integer>(a);
						cbx_gioIn.setModel(model_in);
						cbx_gioIn.setSelectedItem(ks.getGioCheckIn() / 100);
						System.out.println(cbx_gioIn.getSelectedItem());
					}
//					model_in = new DefaultComboBoxModel<Integer>(a);
//					cbx_gioIn.setModel(model_in);
//					cbx_gioIn.setSelectedItem(ks.getGioCheckIn() / 100);
					Integer b[];
					if (checkFix) {
						b = new Integer[1];
						b[0] = hd.getNgayCheckIn().getMinutes();
					} else if (dcs_checkin.getDate().getDate() == homnay.getDayOfMonth()
							&& dcs_checkin.getDate().getMonth() == (homnay.getMonthValue() - 1)
							&& dcs_checkin.getDate().getYear() + 1900 == homnay.getYear()
							&& String.valueOf(cbx_gioIn.getSelectedItem()).equals(String.valueOf(homnay.getHour()))) {
						b = new Integer[60 - homnay.getMinute()];
						for (int i = 0; i < b.length; i++) {
							b[i] = i + (homnay.getMinute());
						}
					} else {
						b = new Integer[60];
						for (int i = 0; i < b.length; i++) {
							b[i] = i;
						}
					}
					DefaultComboBoxModel<Integer> model_inp = new DefaultComboBoxModel<Integer>(b);
					cbx_phutIn.setModel(model_inp);
					cbx_phutIn.setSelectedIndex(0);
					gioIn = Integer.parseInt(String.valueOf(cbx_gioIn.getSelectedItem()));
					phutIn = Integer.parseInt(String.valueOf(cbx_phutIn.getSelectedItem()));
					in = new Date(dcs_checkin.getDate().getYear(), dcs_checkin.getDate().getMonth(),
							dcs_checkin.getDate().getDate(), gioIn, phutIn);
					if (dcs_checkout.getDate() != null && dcs_checkout.getDate().getTime() > max.getTime()) {
						dcs_checkout.setDate(null);
						System.out.println("dk phu");
					}
					try {
						hours = (int) ((out.getTime() - in.getTime()) / 3600000);
						if (((double) (out.getTime() - in.getTime()) / 3600000) > hours) {
							hours++;
						}
					} catch (Exception ecx) {

					}
					max = new Date(dcs_checkin.getDate().getYear(), dcs_checkin.getDate().getMonth(),
							dcs_checkin.getDate().getDate(), gioIn + gap, phutIn);
					dcs_checkout.setMaxSelectableDate(max);
					if (!checkFix) {
						cbx_gioIn.setEnabled(true);
						cbx_phutIn.setEnabled(true);
					}
				}

				if (dcs_checkout.getDate() != null) {
					Integer[] a;
					DefaultComboBoxModel<Integer> model_out;
					if (checkFix) {
						DefaultComboBoxModel<Integer> model_gout = new DefaultComboBoxModel<Integer>(
								new Integer[] { hd.getNgayCheckOut().getHours() });
						cbx_gioOut.setModel(model_gout);
					}
					if (loai.equals("dem") && dcs_checkout.getDate() != null) {
						int cis = 0, cos = 0;
						for (int i = 0; i < list_lp.size(); i++) {
							for (PhuTroi pt : list_lp.get(i).getDsphutroi()) {
								if (pt.getLoai().equals("checkindem")) {
									cis++;
								}
								if (pt.getLoai().equals("checkoutdem")) {
									cos++;
								}
							}
						}
						if (in.getDate() - 1 == dcs_checkout.getDate().getDate()) {
							a = new Integer[24 - Integer.parseInt(cbx_gioIn.getSelectedItem() + "") - 1];
							for (int i = Integer.parseInt(cbx_gioIn.getSelectedItem() + "") + 1; i < 24; i++) {
								a[i - Integer.parseInt(cbx_gioIn.getSelectedItem() + "") - 1] = i;
							}
							model_out = new DefaultComboBoxModel<Integer>(a);
						} else {
							Integer b[] = new Integer[ks.getGioCheckOutDem() / 100 + cos];
							for (int i = 0; i < ks.getGioCheckOutDem() / 100 + cos; i++) {
								System.out.println(i);
								b[i] = i;
							}
							model_out = new DefaultComboBoxModel<Integer>(b);
						}
						cbx_gioOut.setModel(model_out);
					} else if (dcs_checkin.getDate().getDate() == dcs_checkout.getDate().getDate()
							&& dcs_checkin.getDate().getMonth() == dcs_checkout.getDate().getMonth()
							&& dcs_checkin.getDate().getYear() == dcs_checkout.getDate().getYear()) {
						a = new Integer[24 - gioIn - 1];
						int B = gioIn + 1;
						for (int i = 0; B + i < 24; i++) {
							a[i] = B + i;
						}
						model_out = new DefaultComboBoxModel<Integer>(a);
						cbx_gioOut.setModel(model_out);
					} else {
						a = new Integer[24];
						for (int i = 0; i < 24; i++) {
							a[i] = i;
						}
						model_out = new DefaultComboBoxModel<Integer>(a);
						cbx_gioOut.setModel(model_out);
						cbx_gioOut.setSelectedIndex(ks.getGioCheckout() / 100);
					}

					Integer b[] = new Integer[60];
					for (int i = 0; i < b.length; i++) {
						b[i] = i;
					}
					if (checkFix) {
						b = new Integer[1];
						b[0] = hd.getNgayCheckOut().getMinutes();
					}
					model_out = new DefaultComboBoxModel<Integer>(b);
					cbx_phutOut.setModel(model_out);
					gioOut = Integer.parseInt(String.valueOf(cbx_gioOut.getSelectedItem()));
					phutOut = Integer.parseInt(String.valueOf(cbx_phutOut.getSelectedItem()));
					out = new Date(dcs_checkout.getDate().getYear(), dcs_checkout.getDate().getMonth(),
							dcs_checkout.getDate().getDate(), gioOut, phutOut);
					hours = (int) ((out.getTime() - in.getTime()) / 3600000);
					if (((double) (out.getTime() - in.getTime()) / 3600000) > hours) {
						hours++;
					}
				}
			}
		});
		pnl_time.add(chb_ngay);

		chb_dem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chb_dem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				loai = "dem";
				gap = 24 - gioIn + ks.getGioCheckOutDem() / 100;
				max = new Date(dcs_checkin.getDate().getYear(), dcs_checkin.getDate().getMonth(),
						dcs_checkin.getDate().getDate(), gioIn + gap, phutIn);
				dcs_checkout.setMaxSelectableDate(max);
				int cis = 0, cos = 0;
				for (int i = 0; i < list_lp.size(); i++) {
					for (PhuTroi pt : list_lp.get(i).getDsphutroi()) {
						if (pt.getLoai().equals("checkindem")) {
							gap++;
							cis++;
						}
						if (pt.getLoai().equals("checkoutdem")) {
							gap++;
							cos++;
						}
					}
				}
				max = in;
				max.setHours(max.getHours() + gap);
				dcs_checkout.setMaxSelectableDate(max);
				if (dcs_checkout.getDate() != null && dcs_checkout.getDate().getTime() > max.getTime()) {
					dcs_checkout.setDate(null);
				}
				DefaultComboBoxModel<Integer> model_out = new DefaultComboBoxModel<Integer>();
				Integer a[] = new Integer[24 - ks.getGioCheckInDem() / 100 + cis];
				int I = 0;
				for (int i = ks.getGioCheckInDem() / 100 - cis; i < 24; i++) {
					System.out.println(ks.getGioCheckInDem() / 100 + "sadasdasdas");
					a[I] = i;
					model_out.addElement(a[I]);
					I++;
				}
				Integer b[] = new Integer[ks.getGioCheckOutDem() / 100 + cos];
				for (int i = 0; i < ks.getGioCheckOutDem() / 100 + cos - 1; i++) {
					b[i] = i;
					model_out.addElement(b[i]);
				}
				cbx_gioIn.setModel(model_out);
				if (loai.equals("dem") && dcs_checkout.getDate() != null) {
					if (in.getDate() - 1 == dcs_checkout.getDate().getDate()) {
						a = new Integer[24 - Integer.parseInt(cbx_gioIn.getSelectedItem() + "") - 1];
						for (int i = Integer.parseInt(cbx_gioIn.getSelectedItem() + "") + 1; i < 24; i++) {
							a[i - Integer.parseInt(cbx_gioIn.getSelectedItem() + "") - 1] = i;
						}
						model_out = new DefaultComboBoxModel<Integer>(a);
					} else {
						b = new Integer[ks.getGioCheckOutDem() / 100 + cos];
						for (int i = 0; i < ks.getGioCheckOutDem() / 100 + cos; i++) {
							System.out.println(i);
							b[i] = i;
						}
						model_out = new DefaultComboBoxModel<Integer>(b);
					}
					cbx_gioOut.setModel(model_out);
				}
//				if(in.getDate()==dcs_checkout.getDate().getDate()) {
//					model_out = new DefaultComboBoxModel<Integer>(a);
//				}else {
//					model_out = new DefaultComboBoxModel<Integer>(b);
//				}
				if (dcs_checkin.getDate().getDate() == homnay.getDayOfMonth()
						&& dcs_checkin.getDate().getMonth() == (homnay.getMonthValue() - 1)
						&& dcs_checkin.getDate().getYear() + 1900 == homnay.getYear()) {
					DefaultComboBoxModel<Integer> modelin = (DefaultComboBoxModel<Integer>) cbx_gioIn.getModel();
					for (int i = 0; i < 24; i++) {
						if (i < homnay.getHour()) {
							modelin.removeElement(i);
						}
					}
				}
				if (dcs_checkout.getDate() != null) {
					cis = 0;
					cos = 0;
					for (int i = 0; i < list_lp.size(); i++) {
						for (PhuTroi pt : list_lp.get(i).getDsphutroi()) {
							if (pt.getLoai().equals("checkindem")) {
								cis++;
							}
							if (pt.getLoai().equals("checkoutdem")) {
								cos++;
							}
						}
					}
					if (in.getDate() - 1 == dcs_checkout.getDate().getDate()) {
						a = new Integer[24 - Integer.parseInt(cbx_gioIn.getSelectedItem() + "") - 1];
						for (int i = Integer.parseInt(cbx_gioIn.getSelectedItem() + "") + 1; i < 24; i++) {
							a[i - Integer.parseInt(cbx_gioIn.getSelectedItem() + "") - 1] = i;
						}
						model_out = new DefaultComboBoxModel<Integer>(a);
					} else {
						b = new Integer[ks.getGioCheckOutDem() / 100 + cos];
						for (int i = 0; i < ks.getGioCheckOutDem() / 100 + cos; i++) {
							System.out.println(i);
							b[i] = i;
						}
						model_out = new DefaultComboBoxModel<Integer>(b);
					}
					cbx_gioOut.setModel(model_out);
					if (dcs_checkin.getDate().getDate() == dcs_checkout.getDate().getDate()
							&& dcs_checkin.getDate().getMonth() == dcs_checkout.getDate().getMonth()
							&& dcs_checkin.getDate().getYear() == dcs_checkout.getDate().getYear()) {
						DefaultComboBoxModel<Integer> model = (DefaultComboBoxModel<Integer>) cbx_gioOut.getModel();
						for (int i = 0; i < 24; i++) {
							if (i < Integer.parseInt(cbx_gioIn.getSelectedItem() + "")) {
								model.removeElement(i);
							}
						}
					}
				}
			}
		});
		pnl_time.add(chb_dem);

		chb_gio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chb_gio.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				gap = 876000;
				loai = "ngay";
				max = new Date(dcs_checkin.getDate().getYear(), dcs_checkin.getDate().getMonth(),
						dcs_checkin.getDate().getDate(), gioIn + gap, phutIn);
				dcs_checkout.setMaxSelectableDate(max);

				if (dcs_checkin.getDate() != null) {
					System.out.println("chay vao in");
					if (!checkFix) {
						chb_dem.setEnabled(true);
						chb_gio.setEnabled(true);
						chb_ngay.setEnabled(true);
						dcs_checkout.setEnabled(true);
					}
					if (dcs_checkout.getDate() != null
							&& dcs_checkout.getDate().getTime() - dcs_checkin.getDate().getTime() < 0) {
						dcs_checkout.setDate(null);
						System.out.println("dk chinh");
					}
					dcs_checkout.setMinSelectableDate(dcs_checkin.getDate());
					System.out.println(new Date(date.getYear() - 1900, date.getMonthValue(), date.getDayOfMonth()));

					homnay = LocalDateTime.now();
					Integer[] a;
					DefaultComboBoxModel<Integer> model_in;
					if (checkFix) {
						a = new Integer[1];
						a[0] = hd.getNgayCheckIn().getHours();
					} else if (loai.equals("dem")) {
						int cis = 0, cos = 0;
						for (int i = 0; i < list_lp.size(); i++) {
							for (PhuTroi pt : list_lp.get(i).getDsphutroi()) {
								if (pt.getLoai().equals("checkindem")) {
									cis++;
								}
								if (pt.getLoai().equals("checkoutdem")) {
									cos++;
								}
							}
						}
						DefaultComboBoxModel<Integer> model_out = new DefaultComboBoxModel<Integer>();
						a = new Integer[24 - ks.getGioCheckInDem() / 100 + cis];
						int I = 0;
						for (int i = ks.getGioCheckInDem() / 100 - cis; i < 24; i++) {
							System.out.println(ks.getGioCheckInDem() / 100 + "sadasdasdas");
							a[I] = i;
							model_out.addElement(a[I]);
							I++;
						}
						Integer b[] = new Integer[ks.getGioCheckOutDem() / 100 + cos];
						for (int i = 0; i < ks.getGioCheckOutDem() / 100 + cos - 1; i++) {
							b[i] = i;
							model_out.addElement(b[i]);
						}
						cbx_gioIn.setModel(model_out);
					} else if (dcs_checkin.getDate().getDate() == homnay.getDayOfMonth()
							&& dcs_checkin.getDate().getMonth() == (homnay.getMonthValue() - 1)
							&& dcs_checkin.getDate().getYear() + 1900 == homnay.getYear()) {
						a = new Integer[24 - homnay.getHour()];
						int b = homnay.getHour();
						for (int i = 0; i < a.length; i++) {
							a[i] = b + i;
						}
						model_in = new DefaultComboBoxModel<Integer>(a);
						model_in = new DefaultComboBoxModel<Integer>(a);
						cbx_gioIn.setModel(model_in);
					}

					else {
						a = new Integer[24];
						for (int i = 0; i < 24; i++) {
							a[i] = i;
						}
						model_in = new DefaultComboBoxModel<Integer>(a);
						cbx_gioIn.setModel(model_in);
						cbx_gioIn.setSelectedItem(ks.getGioCheckIn() / 100);
						System.out.println(cbx_gioIn.getSelectedItem());
					}
//					model_in = new DefaultComboBoxModel<Integer>(a);
//					cbx_gioIn.setModel(model_in);
//					cbx_gioIn.setSelectedItem(ks.getGioCheckIn() / 100);
					Integer b[];
					if (checkFix) {
						b = new Integer[1];
						b[0] = hd.getNgayCheckIn().getMinutes();
					} else if (dcs_checkin.getDate().getDate() == homnay.getDayOfMonth()
							&& dcs_checkin.getDate().getMonth() == (homnay.getMonthValue() - 1)
							&& dcs_checkin.getDate().getYear() + 1900 == homnay.getYear()
							&& String.valueOf(cbx_gioIn.getSelectedItem()).equals(String.valueOf(homnay.getHour()))) {
						b = new Integer[60 - homnay.getMinute()];
						for (int i = 0; i < b.length; i++) {
							b[i] = i + (homnay.getMinute());
						}
					} else {
						b = new Integer[60];
						for (int i = 0; i < b.length; i++) {
							b[i] = i;
						}
					}
					DefaultComboBoxModel<Integer> model_inp = new DefaultComboBoxModel<Integer>(b);
					cbx_phutIn.setModel(model_inp);
					cbx_phutIn.setSelectedIndex(0);
					gioIn = Integer.parseInt(String.valueOf(cbx_gioIn.getSelectedItem()));
					phutIn = Integer.parseInt(String.valueOf(cbx_phutIn.getSelectedItem()));
					in = new Date(dcs_checkin.getDate().getYear(), dcs_checkin.getDate().getMonth(),
							dcs_checkin.getDate().getDate(), gioIn, phutIn);
					if (dcs_checkout.getDate() != null && dcs_checkout.getDate().getTime() > max.getTime()) {
						dcs_checkout.setDate(null);
						System.out.println("dk phu");
					}
					try {
						hours = (int) ((out.getTime() - in.getTime()) / 3600000);
						if (((double) (out.getTime() - in.getTime()) / 3600000) > hours) {
							hours++;
						}
					} catch (Exception ecx) {

					}
					max = new Date(dcs_checkin.getDate().getYear(), dcs_checkin.getDate().getMonth(),
							dcs_checkin.getDate().getDate(), gioIn + gap, phutIn);
					dcs_checkout.setMaxSelectableDate(max);
					if (!checkFix) {
						cbx_gioIn.setEnabled(true);
						cbx_phutIn.setEnabled(true);
					}
				}

				if (dcs_checkout.getDate() != null) {
					Integer[] a;
					DefaultComboBoxModel<Integer> model_out;
					if (checkFix) {
						DefaultComboBoxModel<Integer> model_gout = new DefaultComboBoxModel<Integer>(
								new Integer[] { hd.getNgayCheckOut().getHours() });
						cbx_gioOut.setModel(model_gout);
					}
					if (loai.equals("dem") && dcs_checkout.getDate() != null) {
						int cis = 0, cos = 0;
						for (int i = 0; i < list_lp.size(); i++) {
							for (PhuTroi pt : list_lp.get(i).getDsphutroi()) {
								if (pt.getLoai().equals("checkindem")) {
									cis++;
								}
								if (pt.getLoai().equals("checkoutdem")) {
									cos++;
								}
							}
						}
						if (in.getDate() - 1 == dcs_checkout.getDate().getDate()) {
							a = new Integer[24 - Integer.parseInt(cbx_gioIn.getSelectedItem() + "") - 1];
							for (int i = Integer.parseInt(cbx_gioIn.getSelectedItem() + "") + 1; i < 24; i++) {
								a[i - Integer.parseInt(cbx_gioIn.getSelectedItem() + "") - 1] = i;
							}
							model_out = new DefaultComboBoxModel<Integer>(a);
						} else {
							Integer b[] = new Integer[ks.getGioCheckOutDem() / 100 + cos];
							for (int i = 0; i < ks.getGioCheckOutDem() / 100 + cos; i++) {
								System.out.println(i);
								b[i] = i;
							}
							model_out = new DefaultComboBoxModel<Integer>(b);
						}
						cbx_gioOut.setModel(model_out);
					} else if (dcs_checkin.getDate().getDate() == dcs_checkout.getDate().getDate()
							&& dcs_checkin.getDate().getMonth() == dcs_checkout.getDate().getMonth()
							&& dcs_checkin.getDate().getYear() == dcs_checkout.getDate().getYear()) {
						a = new Integer[24 - gioIn - 1];
						int B = gioIn + 1;
						for (int i = 0; B + i < 24; i++) {
							a[i] = B + i;
						}
						model_out = new DefaultComboBoxModel<Integer>(a);
						cbx_gioOut.setModel(model_out);
					} else {
						a = new Integer[24];
						for (int i = 0; i < 24; i++) {
							a[i] = i;
						}
						model_out = new DefaultComboBoxModel<Integer>(a);
						cbx_gioOut.setModel(model_out);
						cbx_gioOut.setSelectedIndex(ks.getGioCheckout() / 100);
					}

					Integer b[] = new Integer[60];
					for (int i = 0; i < b.length; i++) {
						b[i] = i;
					}
					if (checkFix) {
						b = new Integer[1];
						b[0] = hd.getNgayCheckOut().getMinutes();
					}
					model_out = new DefaultComboBoxModel<Integer>(b);
					cbx_phutOut.setModel(model_out);
					gioOut = Integer.parseInt(String.valueOf(cbx_gioOut.getSelectedItem()));
					phutOut = Integer.parseInt(String.valueOf(cbx_phutOut.getSelectedItem()));
					out = new Date(dcs_checkout.getDate().getYear(), dcs_checkout.getDate().getMonth(),
							dcs_checkout.getDate().getDate(), gioOut, phutOut);
					hours = (int) ((out.getTime() - in.getTime()) / 3600000);
					if (((double) (out.getTime() - in.getTime()) / 3600000) > hours) {
						hours++;
					}
				}
				gap = ks.getGio();
				max = new Date(dcs_checkin.getDate().getYear(), dcs_checkin.getDate().getMonth(),
						dcs_checkin.getDate().getDate(), gioIn + gap, phutIn);
				dcs_checkout.setMaxSelectableDate(max);
				if (dcs_checkout.getDate() != null && dcs_checkout.getDate().getTime() > max.getTime()) {
					dcs_checkout.setDate(null);
				}
				loai = "gio";
				if (loai.equals("gio")) {
					if (dcs_checkout.getDate() == null)
						return;
					Integer[] a;
					DefaultComboBoxModel<Integer> model_out = (DefaultComboBoxModel<Integer>) cbx_gioOut.getModel();
					Date dateMax = new Date(dcs_checkin.getDate().getYear(), dcs_checkin.getDate().getMonth(),
							dcs_checkin.getDate().getDate() + 1, 23, 59);
					if (dcs_checkin.getDate().getDate() == dcs_checkout.getDate().getDate()) {
						a = new Integer[ks.getGio()];
						for (int i = Integer.parseInt(cbx_gioIn.getSelectedItem() + ""); i < Integer
								.parseInt(cbx_gioIn.getSelectedItem() + "") + ks.getGio(); i++) {
							a[i - Integer.parseInt(cbx_gioIn.getSelectedItem() + "")] = i + 1;
						}
						model_out = new DefaultComboBoxModel<Integer>(a);
						cbx_gioOut.setModel(model_out);
					} else {
						int l = ks.getGio() - 23 + Integer.parseInt(cbx_gioIn.getSelectedItem() + "");
						System.out.println("gia tri cua l=" + l);
						a = new Integer[l + 1];
						for (int i = 0; i <= l; i++) {
							a[i] = i;
						}
						model_out = new DefaultComboBoxModel<Integer>(a);
						cbx_gioOut.setModel(model_out);
					}
				}
			}
		});
		pnl_time.add(chb_gio);

		chb_ngay.setEnabled(false);
		chb_dem.setEnabled(false);
		chb_gio.setEnabled(false);

		ButtonGroup kg = new ButtonGroup();
		kg.add(chb_dem);
		kg.add(chb_gio);
		kg.add(chb_ngay);

		ButtonGroup kieu = new ButtonGroup();
		kieu.add(chb_gio);
		kieu.add(chb_ngay);
		kieu.add(chb_dem);
		chb_ngay.setSelected(true);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 394, 407, 146);
		pnl_Gia.add(panel_1);
		panel_1.setLayout(null);

		JLabel lbl_checkin_2_1 = new JLabel("Đã thanh toán:");
		lbl_checkin_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_checkin_2_1.setBounds(10, 80, 151, 25);
		panel_1.add(lbl_checkin_2_1);

		JLabel lbl_checkin_2_2 = new JLabel("Giá phòng:");
		lbl_checkin_2_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_checkin_2_2.setBounds(10, 45, 151, 25);
		panel_1.add(lbl_checkin_2_2);

		JLabel lbl_checkin_2_1_1 = new JLabel("Còn lại:");
		lbl_checkin_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_checkin_2_1_1.setBounds(10, 115, 151, 25);
		panel_1.add(lbl_checkin_2_1_1);

		txt_phutroi = new JTextField();
		txt_phutroi.setFont(new Font("Tahoma", Font.BOLD, 17));
		txt_phutroi.setText("0");
		txt_phutroi.setEnabled(false);
		txt_phutroi.setBounds(165, 6, 232, 29);
		panel_1.add(txt_phutroi);
		txt_phutroi.setColumns(10);

		txt_giaphong = new JTextField();
		txt_giaphong.setFont(new Font("Tahoma", Font.BOLD, 17));
		txt_giaphong.setText("0");
		txt_giaphong.setEnabled(false);
		txt_giaphong.setColumns(10);
		txt_giaphong.setBounds(165, 41, 232, 29);
		panel_1.add(txt_giaphong);

		txt_thanhtoan = new JTextField();
		txt_thanhtoan.setFont(new Font("Tahoma", Font.BOLD, 17));
		txt_thanhtoan.setText("0");
		txt_thanhtoan.setEnabled(false);
		txt_thanhtoan.setColumns(10);
		txt_thanhtoan.setBounds(165, 76, 232, 29);
		panel_1.add(txt_thanhtoan);

		txt_conlai = new JTextField();
		txt_conlai.setFont(new Font("Tahoma", Font.BOLD, 17));
		txt_conlai.setText("0");
		txt_conlai.setEnabled(false);
		txt_conlai.setColumns(10);
		txt_conlai.setBounds(165, 110, 232, 29);
		panel_1.add(txt_conlai);

		JLabel lbl_checkin_2_2_1 = new JLabel("Phụ trội");
		lbl_checkin_2_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_checkin_2_2_1.setBounds(10, 10, 151, 25);
		panel_1.add(lbl_checkin_2_2_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 49, 407, 335);
		pnl_Gia.add(scrollPane);

		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		model_tt = new DefaultTableModel(new String[] { "STT", "Số tiền", "Ngày thanh toán" }, 0);
		table_1.setModel(model_tt);

		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String tien = "0";

				try {
					tien = JOptionPane.showInputDialog("nhập số tiền!");
					if (Integer.parseInt(tien) <= 0) {
						JOptionPane.showMessageDialog(pnl_Phong, "số tiền nhập không hợp lệ");
						return;
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(pnl_Phong, "số tiền nhập không hợp lệ");
					return;
				}
				if (Integer.parseInt(txt_conlai.getText()) < Integer.parseInt(tien)) {
					JOptionPane.showMessageDialog(pnl_Phong, "Số tiền nhập không lớn hơn số tiền còn lại");
					return;
				}
				if (JOptionPane.showConfirmDialog(pnl_Phong, "Thêm thanh toán?") != 0)
					return;
				model_tt.addRow(new Object[] { model_tt.getRowCount(), tien, new Date(homnay.getYear() - 1900,
						homnay.getMonthValue() - 1, homnay.getDayOfMonth(), homnay.getHour(), homnay.getMinute()) });
				list_tt.add(new ModelThanhToan(Double.parseDouble(tien), "đặt cọc", new Date(homnay.getYear() - 1900,
						homnay.getMonthValue() - 1, homnay.getDayOfMonth(), homnay.getHour(), homnay.getMinute())));
				double t = Double.parseDouble(txt_thanhtoan.getText());
				t += Double.parseDouble(tien);
				txt_thanhtoan.setText(t + "");
				txt_conlai.setText(Double.parseDouble(txt_giaphong.getText())
						+ Double.parseDouble(txt_phutroi.getText()) - Double.parseDouble(txt_thanhtoan.getText()) + "");
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(138, 10, 150, 29);
		pnl_Gia.add(btnNewButton_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		pnl_main.add(scrollPane_1, BorderLayout.CENTER);

		pnl_Phong.setBorder(new TitledBorder(null, "Ph\u00F2ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_1.setViewportView(pnl_Phong);
		pnl_Phong.setPreferredSize(new Dimension(422, 1));
	}

	void setupKhachHang() {
		JLabel lblNewLabel = new JLabel("Số CMND/Pasport*");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 40, 144, 21);
		pnl_khachHang.add(lblNewLabel);

		JLabel lblTnKhchHng = new JLabel("Tên khách hàng*");
		lblTnKhchHng.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTnKhchHng.setBounds(10, 71, 144, 21);
		pnl_khachHang.add(lblTnKhchHng);

		JLabel lblSinThoi = new JLabel("Số điện thoại*");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSinThoi.setBounds(10, 102, 144, 21);
		pnl_khachHang.add(lblSinThoi);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEmail.setBounds(10, 133, 144, 21);
		pnl_khachHang.add(lblEmail);

		JLabel lblGiiTnh = new JLabel("Giới tính*");
		lblGiiTnh.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblGiiTnh.setBounds(10, 164, 144, 21);
		pnl_khachHang.add(lblGiiTnh);

		JLabel lblNewLabel_4_1 = new JLabel("Ngày sinh*");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_4_1.setBounds(10, 196, 144, 21);
		pnl_khachHang.add(lblNewLabel_4_1);

		JLabel lblNewLabel_4_2 = new JLabel("Dia chi");
		lblNewLabel_4_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_4_2.setBounds(10, 227, 144, 21);
		pnl_khachHang.add(lblNewLabel_4_2);

		txt_cmnd = new JTextField();

		txt_cmnd.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txt_cmnd.setBounds(164, 40, 253, 23);
		pnl_khachHang.add(txt_cmnd);
		txt_cmnd.setColumns(10);

		txt_ten = new JTextField();
		txt_ten.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txt_ten.setColumns(10);
		txt_ten.setBounds(164, 71, 253, 23);
		pnl_khachHang.add(txt_ten);

		txt_sdt = new JTextField();
		txt_sdt.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txt_sdt.setColumns(10);
		txt_sdt.setBounds(164, 102, 253, 23);
		pnl_khachHang.add(txt_sdt);

		txt_email = new JTextField();
		txt_email.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txt_email.setColumns(10);
		txt_email.setBounds(164, 131, 253, 23);
		pnl_khachHang.add(txt_email);

		txt_quocTich = new JTextField();
		txt_quocTich.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txt_quocTich.setColumns(10);
		txt_quocTich.setBounds(164, 225, 253, 23);
		pnl_khachHang.add(txt_quocTich);

		final JComboBox cbx_gender = new JComboBox();
		cbx_gender.setModel(new DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
		cbx_gender.setBounds(164, 164, 253, 21);
		pnl_khachHang.add(cbx_gender);

		table = new JTable();
		table.setBounds(403, 288, -378, 360);
		pnl_khachHang.add(table);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 300, 407, 274);
		pnl_khachHang.add(tabbedPane);

		tabbedPane.addTab("Khách trong đoàn", null, pnl_khachTrongDoan, null);
		pnl_khachTrongDoan.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		tabbedPane.addTab("Danh sách", null, pnl_danhsach, null);

		model_k = new DefaultTableModel(
				new String[] { "CMND/passport", "Họ và tên", "Số điện thoại", "Email", "Giới tính" }, 0);
		for (int i = 0; i < list_choose_kh.size(); i++) {
			model_k.addRow(new Object[] { list_choose_kh.get(i).getCMND(), list_choose_kh.get(i).getTenKhachHang(),
					list_choose_kh.get(i).getSDT(), list_choose_kh.get(i).getEmail(),
					list_choose_kh.get(i).getGioiTinh() });
		}

		tbl_khach = new JTable(model_k);
		final JDateChooser dcs_ngaySinh = new JDateChooser();
		tbl_khach.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (tbl_khach.getSelectedRow() != -1) {
					for (ModelKhachHang kh : list_kh) {
						if (kh.getCMND().equals(tbl_khach.getValueAt(tbl_khach.getSelectedRow(), 0))) {
							model_ds.addRow(new Object[] { kh.getCMND(), kh.getTenKhachHang(), kh.getSDT(),
									kh.getEmail(), kh.getGioiTinh() });
							txt_cmnd.setText(kh.getCMND());
							txt_email.setText(kh.getEmail());
							txt_ten.setText(kh.getTenKhachHang());
							txt_quocTich.setText(kh.getDiaChi());
							txt_sdt.setText(kh.getSDT());
							dcs_ngaySinh.setDate(kh.getNgaySinh());
							cbx_gender.setSelectedItem(kh.getGioiTinh());
							break;
						}
					}
					for (int i = 0; i < list_new_kh.size(); i++) {
						if (list_new_kh.get(i).getCMND().equals(tbl_khach.getValueAt(tbl_khach.getSelectedRow(), 0))) {
							list_new_kh.remove(i);
						}
					}
					for (int i = 0; i < list_choose_kh.size(); i++) {
						if (list_choose_kh.get(i).getCMND()
								.equals(tbl_khach.getValueAt(tbl_khach.getSelectedRow(), 0))) {
							list_choose_kh.remove(i);
						}
					}
					model_k.removeRow(tbl_khach.getSelectedRow());
				}
			}
		});
		tbl_khach.setDefaultEditor(Object.class, null);
		JScrollPane sc_ktd = new JScrollPane(tbl_khach);
		sc_ktd.setMinimumSize(new Dimension(400, 210));
		sc_ktd.setPreferredSize(new Dimension(400, 210));
		pnl_khachTrongDoan.add(sc_ktd);

		model_ds = new DefaultTableModel(
				new String[] { "CMND/passport", "Họ và tên", "Số điện thoại", "Email", "Giới tính" }, 0);
		System.out.println("lidt_kh " + list_kh.size());
		for (ModelKhachHang kh : list_kh) {
			boolean check = false;
			for (ModelHoaDon h : list_hd) {
				for (ModelKhachHang k : h.getDskhachhang()) {
					if (kh.getMaKhachHang().equals(k.getMaKhachHang())) {
						check = true;
						break;
					}
				}
				if (check)
					break;
			}
			if (check)
				continue;
			model_ds.addRow(
					new Object[] { kh.getCMND(), kh.getTenKhachHang(), kh.getSDT(), kh.getEmail(), kh.getGioiTinh() });
		}
		tbl_ds = new JTable(model_ds);
		tbl_ds.setDefaultEditor(Object.class, null);
		tbl_ds.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (tbl_ds.getSelectedRow() != -1) {
					for (int a = 0; a < list_kh.size(); a++) {
						System.out.println(
								list_kh.get(a).getMaKhachHang() + "  " + tbl_ds.getValueAt(tbl_ds.getSelectedRow(), 0));
						if (list_kh.get(a).getCMND().equals(tbl_ds.getValueAt(tbl_ds.getSelectedRow(), 0))) {
							list_choose_kh.add(list_kh.get(a));
							model_ds.removeRow(tbl_ds.getSelectedRow());
							model_k.addRow(new Object[] { list_kh.get(a).getCMND(), list_kh.get(a).getTenKhachHang(),
									list_kh.get(a).getSDT(), list_kh.get(a).getEmail(), list_kh.get(a).getGioiTinh() });
							txt_cmnd.setText(list_kh.get(a).getCMND());
							txt_email.setText(list_kh.get(a).getEmail());
							txt_ten.setText(list_kh.get(a).getTenKhachHang());
							txt_quocTich.setText(list_kh.get(a).getDiaChi());
							txt_sdt.setText(list_kh.get(a).getSDT());
							dcs_ngaySinh.setDate(list_kh.get(a).getNgaySinh());
							cbx_gender.setSelectedItem(list_kh.get(a).getGioiTinh());

							cbx_gender.setEnabled(false);
							txt_email.setEnabled(false);
							txt_ten.setEnabled(false);
							txt_quocTich.setEnabled(false);
							txt_sdt.setEnabled(false);
							dcs_ngaySinh.setEnabled(false);
							break;
						}
					}
				}
			}
		});

		txt_cmnd.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent e) {
				// TODO Auto-generated method stub
				if (txt_cmnd.getText().matches("\\d{9}|\\d{12}|[A-Z]{1}\\d{7}")) {
					for (ModelKhachHang kh : list_kh) {
						if (txt_cmnd.getText().equals(kh.getMaKhachHang())) {
							txt_email.setText(kh.getEmail());
							txt_ten.setText(kh.getTenKhachHang());
							txt_quocTich.setText(kh.getDiaChi());
							txt_sdt.setText(kh.getSDT());
							dcs_ngaySinh.setDate(kh.getNgaySinh());
							cbx_gender.setSelectedItem(kh.getGioiTinh());

							cbx_gender.setEnabled(false);
							txt_email.setEnabled(false);
							txt_ten.setEnabled(false);
							txt_quocTich.setEnabled(false);
							txt_sdt.setEnabled(false);
							dcs_ngaySinh.setEnabled(false);
							break;
						} else {
							cbx_gender.setEnabled(true);
							txt_email.setEnabled(true);
							txt_ten.setEnabled(true);
							txt_quocTich.setEnabled(true);
							txt_sdt.setEnabled(true);
							dcs_ngaySinh.setEnabled(true);
						}
					}
				} else if (!txt_email.isEnabled()) {
					cbx_gender.setEnabled(true);
					txt_cmnd.setEnabled(true);
					txt_email.setEnabled(true);
					txt_ten.setEnabled(true);
					txt_quocTich.setEnabled(true);
					txt_sdt.setEnabled(true);
					dcs_ngaySinh.setEnabled(true);
				}
			}
		});
		JScrollPane sc_ds = new JScrollPane(tbl_ds);
		sc_ds.setMinimumSize(new Dimension(400, 210));
		sc_ds.setPreferredSize(new Dimension(400, 210));
		pnl_danhsach.add(sc_ds);

		dcs_ngaySinh
				.setMaxSelectableDate(new Date(date.getYear() - 1900, date.getMonthValue() - 1, date.getDayOfMonth()));
		dcs_ngaySinh.setBounds(164, 196, 253, 19);
		pnl_khachHang.add(dcs_ngaySinh);

		JButton btn_them_k = new JButton("Thêm");
		btn_them_k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txt_ten.getText().isBlank() || txt_cmnd.getText().isBlank() || txt_sdt.getText().isBlank()
						|| dcs_ngaySinh.getDate() == null) {
					JOptionPane.showMessageDialog(pnl_Phong, "Nhập đủ thông tin có dấu *");
					return;
				}
				if (list_new_kh.size() + list_choose_kh.size() == 1 && chb_le.isSelected()) {
					JOptionPane.showMessageDialog(pnl_Phong, "Vui lòng chọn đặt phòng đoàn để thêm hơn 1 khách");
					return;
				}
				if (!txt_cmnd.getText().matches("\\d{9}||\\d{12}||[A-Z]{1}\\d{7}")) {
					JOptionPane.showMessageDialog(pnl_Phong, "sai định dạng chứng minh thư hoặc hộ chiếu");
					return;
				}
				if ((!txt_email.getText().isBlank() && !txt_email.getText().matches(
						"(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-"
								+ "\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:"
								+ "[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)"
								+ "\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f"
								+ "\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"))) {
					JOptionPane.showMessageDialog(pnl_Phong, "sai định dạng email");
					return;
				}
				if ((!txt_sdt.getText().matches("0\\d{9,10}") && !txt_sdt.getText().isBlank())) {
					JOptionPane.showMessageDialog(pnl_Phong, "sai định dạng số điện thoại");
					return;
				}
				for (int i = 0; i < model_k.getRowCount(); i++) {
					if (txt_cmnd.getText().equals(model_k.getValueAt(i, 0))) {
						JOptionPane.showMessageDialog(pnl_Phong, "Khách hàng đã có trong danh sách");
						return;
					}
				}
				java.util.Date ns = dcs_ngaySinh.getDate();

				model_k.addRow(new Object[] { txt_cmnd.getText(), txt_ten.getText(), txt_sdt.getText(),
						txt_email.getText(), String.valueOf(cbx_gender.getSelectedItem()) });
				for (ModelKhachHang kh : list_kh) {
					if (txt_cmnd.getText().equals(kh.getMaKhachHang())) {
						list_choose_kh.add(new ModelKhachHang(txt_cmnd.getText(), txt_ten.getText(), txt_cmnd.getText(),
								txt_sdt.getText(), txt_email.getText(), ns,
								String.valueOf(cbx_gender.getSelectedItem()), txt_quocTich.getText()));
						for (int i = 0; i < model_ds.getRowCount(); i++) {
							if (txt_cmnd.getText().equals(model_ds.getValueAt(i, 0))) {
								model_ds.removeRow(i);
								break;
							}
						}
						return;
					}
				}
				list_new_kh.add(new ModelKhachHang(txt_cmnd.getText(), txt_ten.getText(), txt_cmnd.getText(),
						txt_sdt.getText(), txt_email.getText(), ns, String.valueOf(cbx_gender.getSelectedItem()),
						txt_quocTich.getText()));
			}
		});
		btn_them_k.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_them_k.setBounds(63, 258, 296, 32);
		pnl_khachHang.add(btn_them_k);
	}

	void ThemPhong() {
		final List<ModelPhong> list_unable = new ArrayList<ModelPhong>();
		for (ModelHoaDon mhd : list_hd) {
			if ((mhd.getNgayCheckIn().getTime() < in.getTime() && in.getTime() < mhd.getNgayCheckOut().getTime())
					|| (mhd.getNgayCheckIn().getTime() < out.getTime()
							&& out.getTime() < mhd.getNgayCheckOut().getTime())) {
				for (ModelKhachTrongPhong ktp : mhd.getDskhactrongphong()) {
					list_unable.add(ktp.getPhong());
				}
			}
		}
		for (int i = 0; i < list_lp.size(); i++) {
			JLabel lbl_loai = new JLabel(list_lp.get(i).getTenLoai());
			lbl_loai.setPreferredSize(new Dimension(420, 30));
			lbl_loai.setBorder(BorderFactory.createEtchedBorder());
			final JPanel pnl_loai = new JPanel();
			pnl_loai.setLayout(new FlowLayout(FlowLayout.LEFT, 3, 3));
			pnl_loai.setPreferredSize(new Dimension(420, 1));
			final int x = i;
			lbl_loai.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (pnl_loai.getComponentCount() > 0) {
						pnl_loai.removeAll();
						pnl_loai.setPreferredSize(new Dimension(420, 1));
					} else {
						for (int i = 0; i < list_lp.get(x).getDSPhong().size(); i++) {
							boolean check = false;
							for (ModelPhong ph : list_unable) {
								if (list_lp.get(x).getDSPhong().get(i).getMaPhong() == ph.getMaPhong()) {
									check = true;
								}
							}
							if (check)
								continue;
							final JCheckBox chb_p = new JCheckBox(
									String.valueOf(list_lp.get(x).getDSPhong().get(i).getMaPhong()));
							for (ModelPhong p : list_p_chon) {
								if (Integer.parseInt(chb_p.getText()) == p.getMaPhong()) {
									chb_p.setSelected(true);
								}
							}
							final int y = i;
							chb_p.addActionListener(new ActionListener() {

								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									if (chb_p.isSelected()) {
										gia = 0;
										phutroi = 0;
										phutroi2 = 0;
										list_p_chon.add(list_lp.get(x).getDSPhong().get(y));
										gia += gia(list_lp.get(x))[0];
										list_gia.add(Float.parseFloat(gia + ""));
										phutroi += gia(list_lp.get(x))[1];
										list_pt.add(Float.parseFloat(phutroi + ""));
										phutroi2 += gia(list_lp.get(x))[2];
										list_pt2.add(Float.parseFloat(phutroi2 + ""));
										gia = 0;
										phutroi = 0;
										phutroi2 = 0;
										for (Float G : list_gia) {
											gia += G;
										}
										for (Float G : list_pt) {
											phutroi += G;
										}
										for (Float G : list_pt2) {
											phutroi2 += G;
										}
										txt_phutroi.setText(String.valueOf(new BigDecimal((phutroi + phutroi2))));
										txt_giaphong.setText(String.valueOf(new BigDecimal(gia)));
										txt_conlai.setText(String
												.valueOf(new BigDecimal(gia + Double.parseDouble(txt_phutroi.getText())
														- Double.parseDouble(txt_thanhtoan.getText()))));
									} else {
										for (int y = 0; y < list_p_chon.size(); y++) {
											if (chb_p.getText().equals( list_p_chon.get(y).getMaPhong()+"")) {
												list_p_chon.remove(y);
												list_gia.remove(y);
												list_pt.remove(y);
												list_pt2.remove(y);
											}
										}
										System.out.println("lsit " + list_p_chon.size());
										gia = 0;
										phutroi = 0;
										phutroi2 = 0;
										for (Float G : list_gia) {
											gia += G;
										}
										for (Float G : list_pt) {
											phutroi += G;
										}
										for (Float G : list_pt2) {
											phutroi2 += G;
										}
										txt_phutroi.setText(String.valueOf(new BigDecimal((phutroi + phutroi2))));
										txt_giaphong.setText(String.valueOf(new BigDecimal(gia)));
										txt_conlai.setText(String
												.valueOf(new BigDecimal(gia + Double.parseDouble(txt_phutroi.getText())
														- Double.parseDouble(txt_thanhtoan.getText()))));
									}
								}
							});
							pnl_loai.add(chb_p);
						}
						pnl_loai.setPreferredSize(
								new Dimension(420, (int) (pnl_loai.getComponentCount() / 8 + 1) * 27));
					}
					revalidate();
					repaint();
				}
			});
			for (ModelLoaiPhong p : list_lp) {
				pnl_Phong.setPreferredSize(new Dimension(422,
						(int) (pnl_Phong.getPreferredSize().height + (p.getDSPhong().size() / 8) * 27 + 30)));
			}
			pnl_Phong.add(lbl_loai);
			pnl_Phong.add(pnl_loai);
		}
	}

	void tinhtien() {
		gia = 0;
		phutroi = 0;
		phutroi2 = 0;
		for (int i = 0; i < list_lp.size(); i++) {
			for (ModelPhong ph : list_lp.get(i).getDSPhong()) {
				for (ModelPhong p : list_p_chon) {
					if (ph.getMaPhong() == p.getMaPhong()) {
						gia = 0;
						phutroi = 0;
						phutroi2 = 0;
						gia += gia(list_lp.get(i))[0];
						phutroi += gia(list_lp.get(i))[1];
						phutroi2 += gia(list_lp.get(i))[2];
						list_gia.add(Float.parseFloat(gia + ""));
						list_pt.add(Float.parseFloat(phutroi + ""));
						list_pt2.add(Float.parseFloat(phutroi2 + ""));
						gia = 0;
						phutroi = 0;
						phutroi2 = 0;
						for (Float G : list_gia) {
							gia += G;
							System.out.println("GIA LA LA LA " + gia);
						}
						for (Float G : list_pt) {
							phutroi += G;
						}
						for (Float G : list_pt2) {
							phutroi2 += G;
						}
						txt_phutroi.setText(String.valueOf(new BigDecimal((phutroi + phutroi2))));
						txt_giaphong.setText(String.valueOf(new BigDecimal(gia)));
						txt_conlai.setText(String.valueOf(new BigDecimal(gia + Double.parseDouble(txt_phutroi.getText())
								- Double.parseDouble(txt_thanhtoan.getText()))));
						gia = 0;
						phutroi = 0;
						phutroi2 = 0;
					}
				}
			}
		}
	}

	void ThanhToan() {
		model_tt.setRowCount(0);
		int x = 1;
		double t = 0;
		for (ModelThanhToan tt : list_tt) {
			model_tt.addRow(new Object[] { x, tt.getTien(), tt.getNgayThanhToan() });
			t += tt.getTien();
			x++;
		}
		txt_thanhtoan.setText(t + "");
	}

	Double[] gia(ModelLoaiPhong p) {
		int gin;
		if (loai.equals("ngay"))
			gin = (int) ks.getGioCheckIn() / 100;
		else
			gin = (int) ks.getGioCheckInDem() / 100;
		double gia = 0, CIS = 0, COM = 0;
		if (chb_gio.isSelected()) {
			gia += p.getGiaTheoGio() * (cbx_gioOut.getSelectedIndex() + 1);
			return new Double[] { gia, CIS, COM };
		}
		boolean checkInSom = true, dugio = true;
		int gio = gioIn;
		List<PhuTroi> dspt = p.getDsphutroi();
		if (dspt == null)
			dspt = new ArrayList<PhuTroi>();
		List<PhuTroi> dsngay = new ArrayList<PhuTroi>();
		List<PhuTroi> dsngayout = new ArrayList<PhuTroi>();
		List<PhuTroi> dsdem = new ArrayList<PhuTroi>();
		List<PhuTroi> dsdemout = new ArrayList<PhuTroi>();
		for (PhuTroi x : dspt) {
			if (x.getLoai().equals("checkin")) {
				dsngay.add(x);
			} else if (x.getLoai().equals("checkindem"))
				dsdem.add(x);
			else if (x.getLoai().equals("checkout")) {
				dsngayout.add(x);
			} else
				dsdemout.add(x);
		}
		int x = 0;
		for (int i = 0; i < hours; i++) {
			System.out.println(i);
			if (gio == gin && checkInSom) {
				checkInSom = false;
				int cis = 0;
				System.out.println("checkin som +" + i);
				if (loai.equals("ngay")) {
					cis = dsngay.size();
					try {
						CIS = CIS + dsngay.get(i - 1).getPhuTroi();
						System.out.println("cis " + CIS);
					} catch (Exception e) {
						if (i != 0) {
							gia += p.getGiaPhong();
							System.out.println("cong");
						}

					}
				} else if (loai.equals("dem")) {
					cis = dsdem.size();
					try {
						CIS += dsdem.get(i - 1).getPhuTroi();
					} catch (Exception e) {
						if (i != 0)
							gia += p.getGiaQuaDem();
					}
				}
			}
			gio++;
			if (gio == 24)
				gio = 0;
			if (!checkInSom) {
				x++;
				if (gio == (int) (ks.getGioCheckout() / 100) && loai.equals("ngay")) {
					dugio = !dugio;
					gia += p.getGiaPhong();
					System.out.println("cong gia " + p.getGiaPhong());
					x = 0;
				}
				if (gio == (int) (ks.getGioCheckOutDem() / 100) && loai.equals("dem")) {
					dugio = !dugio;
					gia += p.getGiaQuaDem();
					x = 0;
				}
			}
			System.out.println("Gia mid" + gia);
			if (i == hours - 1) {
				if (dugio) {
					if (loai.equals("dem"))
						gia += p.getGiaQuaDem();
					else if (loai.equals("ngay"))
						gia += p.getGiaPhong();
				} else if (loai.equals("dem")) {
					try {
						COM = COM + dsdemout.get(x - 1).getPhuTroi();
					} catch (Exception e) {
						if (x != 0)
							gia += p.getGiaQuaDem();
					}
				} else if (loai.equals("ngay")) {
					try {
						System.out.println("tinh gia" + CIS);
						COM = COM + dsngayout.get(x - 1).getPhuTroi();
					} catch (Exception e) {
						if (x != 0)
							gia += p.getGiaPhong();
					}
				}
			}
		}
		Double[] a = new Double[] { gia, CIS, COM };
		System.out.println("Gia " + gia);
		return a;
	}
}
