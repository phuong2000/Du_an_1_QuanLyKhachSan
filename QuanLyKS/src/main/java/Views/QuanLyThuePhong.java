package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import Services.HoaDon_services;
import Services.KhachSan_services;
import Services.KhachTrongPhong_services;
import Services.loaiPhong_services;
import ViewModels.ModelHoaDon;
import ViewModels.ModelKhachSan;
import ViewModels.ModelKhachTrongPhong;
import ViewModels.ModelLoaiPhong;
import ViewModels.ModelPhong;
import ViewModels.ModelTang;
import models.HoaDonModel;
import models.KhachTrongPhongModel;
import models.PhongModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.JSeparator;
import javax.swing.JComboBox;
import java.util.Properties;

import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Entities.HoaDon;
import Entities.KhachTrongPhong;
import Entities.LoaiPhong;
import Entities.Phong;
import Entities.PhuTroi;
import Repositories.dao_khachTrongPhong;

public class QuanLyThuePhong extends JFrame {
	JFrame framepp;
	JFrame framethd;
	JPanel pnl_main = new JPanel();
	JPanel pnl_chinh = new JPanel();
	JPanel pnl_aside = new JPanel();
	JPanel pnl_button = new JPanel();
	JPanel pnl_search = new JPanel();
	JPopupMenu menu = new JPopupMenu();
	JLabel lbl_tim = new JLabel("Lọc theo: ");
	JPanel pnl_status = new JPanel();
	JTextField txt_date = new JTextField("dd/mm/yyyy");
	JButton btn_Booking = new JButton("Đặt phòng");
	JButton btn_Checkin = new JButton("Nhận phòng");
	JButton btn_checkout = new JButton("Trả phòng");
	JButton btn_chuyenPhong = new JButton("Chuyển phòng");
	JButton btn_tachDoan = new JButton("Tách đoàn");
	JButton btn_dichvu = new JButton("Đặt dịch vụ");
	JCheckBox chb_tuan = new JCheckBox("7 ngày tới");
	JCheckBox chb_thang = new JCheckBox("1 tháng tới");
	JCheckBox chb_tang = new JCheckBox("Tầng");
	JCheckBox chb_lp = new JCheckBox("Loại phòng");
	JPopupMenu menuCI = new JPopupMenu();
	JPopupMenu menuStatus = new JPopupMenu();
	JComboBox<String> cbx_loai;
	JComboBox<String> cbx_tang;
	boolean doiphong = false;
	ModelKhachTrongPhong chuyenPhong;
	UtilDateModel model = new UtilDateModel();
	LocalDate date;
	List<JPanel> lst_pnl = new ArrayList<JPanel>();
	YearMonth ym;
	JDatePickerImpl datePicker;
	List<ModelTang> ListTang;
	List<ModelPhong> list_cp = new ArrayList<ModelPhong>();
	List<ModelHoaDon> list_hd;
	List<ModelLoaiPhong> List_lp;
	List<ModelKhachTrongPhong> list_ktp;
	KhachSan_services ser;
	loaiPhong_services ser_lp;
	KhachTrongPhong_services ser_ktp;
	HoaDon_services ser_hd;
	ModelKhachSan ks;
	SimpleDateFormat format;
	Date ngay;
	String text;
	ModelHoaDon hd;

	private JTextField textField;
	private JTextField textField_1;

	public QuanLyThuePhong() {
		format = new SimpleDateFormat("yyyy/MM/dd");
		ser = new KhachSan_services();
		ser_lp = new loaiPhong_services();
		ser_ktp = new KhachTrongPhong_services();
		ser_hd = new HoaDon_services();
		List_lp = ser_lp.getLp();
		list_hd = ser_hd.getList();
		ks = ser.getKs();
		ListTang = ks.getDSTang();
		list_ktp = ser_ktp.getList();
		// TODO Auto-generated constructor stub
		setting();
		pnl_chinh_autoGen();
	}

	public void setting() {
		setSize(1280, 720);
		setLayout(new BorderLayout());
		add(pnl_main, BorderLayout.CENTER);
		pnl_main.setLayout(new BorderLayout());
		pnl_main.add(pnl_aside, BorderLayout.WEST);
		pnl_aside.setPreferredSize(new Dimension(250, 720));
		pnl_aside.setBackground(Color.white);
		pnl_aside.setLayout(null);

		JLabel lbl_phong = new JLabel("Phòng");
		lbl_phong.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_phong.setBounds(96, 0, 56, 29);
		pnl_aside.add(lbl_phong);

		JLabel lblNewLabel_1 = new JLabel("Loại phòng: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 27, 230, 23);
		pnl_aside.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Ngày check in: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 60, 98, 18);
		pnl_aside.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Ngày check out: ");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(10, 88, 107, 23);
		pnl_aside.add(lblNewLabel_2_1);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 168, 230, 2);
		pnl_aside.add(separator);

		JLabel lbl_phong_1 = new JLabel("Khách");
		lbl_phong_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_phong_1.setBounds(96, 180, 56, 29);
		pnl_aside.add(lbl_phong_1);

		JLabel lblNewLabel_1_1 = new JLabel("Tên khách:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 219, 76, 23);
		pnl_aside.add(lblNewLabel_1_1);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(84, 219, 156, 21);
		pnl_aside.add(comboBox);

		JLabel lblNewLabel_1_2 = new JLabel("Số CMND:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(10, 285, 230, 23);
		pnl_aside.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Điện thoại");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_3.setBounds(10, 318, 230, 23);
		pnl_aside.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Email:");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_4.setBounds(10, 351, 230, 23);
		pnl_aside.add(lblNewLabel_1_4);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 384, 230, 2);
		pnl_aside.add(separator_1);

		JLabel lbl_phong_1_1 = new JLabel("Hóa đơn");
		lbl_phong_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbl_phong_1_1.setBounds(84, 396, 82, 29);
		pnl_aside.add(lbl_phong_1_1);

		JLabel lblNewLabel_1_2_1 = new JLabel("Loại khách:");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_1.setBounds(10, 252, 230, 23);
		pnl_aside.add(lblNewLabel_1_2_1);

		textField = new JTextField();
		textField.setEnabled(false);
		textField.setBounds(116, 56, 124, 25);
		pnl_aside.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setColumns(10);
		textField_1.setBounds(116, 90, 124, 23);
		pnl_aside.add(textField_1);

		JLabel lblNewLabel_1_2_2 = new JLabel("Tổng tiền:");
		lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_2.setBounds(10, 435, 230, 23);
		pnl_aside.add(lblNewLabel_1_2_2);

		JLabel lblNewLabel_1_2_3 = new JLabel("Đã thanh toán:");
		lblNewLabel_1_2_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_3.setBounds(10, 468, 230, 23);
		pnl_aside.add(lblNewLabel_1_2_3);

		JLabel lblNewLabel_1_2_4 = new JLabel("Còn lại: ");
		lblNewLabel_1_2_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2_4.setBounds(10, 501, 230, 23);
		pnl_aside.add(lblNewLabel_1_2_4);

		JButton btnNewButton = new JButton("Xem hóa đơn");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(69, 534, 117, 21);
		pnl_aside.add(btnNewButton);

		JScrollPane sc = new JScrollPane(pnl_chinh);
		sc.setPreferredSize(new Dimension(1000, 720));
		sc.setMinimumSize(new Dimension(1000, 720));
		pnl_chinh.setLayout(null);
		pnl_chinh.setBackground(Color.white);
		pnl_main.add(sc, BorderLayout.EAST);

		pnl_main.add(pnl_button, BorderLayout.NORTH);
		pnl_button.setPreferredSize(new Dimension(1200, 70));
		pnl_button.add(btn_Booking);
		pnl_button.add(btn_Checkin);
		pnl_button.add(btn_checkout);
		pnl_button.add(btn_chuyenPhong);
		pnl_button.add(btn_tachDoan);
		pnl_button.add(btn_dichvu);
		JMenuItem item_booking = new JMenuItem("Đặt phòng");
		item_booking.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pnl_main.removeAll();
				pnl_main.add(new Booking());
				revalidate();
				repaint();
			}
		});
		menu.add(item_booking);

		JPanel mapMenu = new JPanel();
		mapMenu.setPreferredSize(new Dimension(1200, 40));
		mapMenu.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 2));
		pnl_button.add(mapMenu);

		JLabel lbl_tu = new JLabel("Chọn ngày ");
		JLabel lbl_icon = new JLabel(new ImageIcon(new ImageIcon(this.getClass().getResource("/image/lich.png"))
				.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
		txt_date.setPreferredSize(new Dimension(75, 30));
		date = LocalDate.now();
		String ngay = String.valueOf(date.getDayOfMonth()) + "/" + String.valueOf(date.getMonthValue()) + "/"
				+ String.valueOf(date.getYear());
		txt_date.setText(ngay);
		text = ngay;

		JButton btn_homNay = new JButton("Hôm nay");
		btn_homNay.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				date = LocalDate.now();
				model.setDate(date.getYear(), date.getMonthValue()-1, date.getDayOfMonth());
				model.setSelected(true);
			}
		});

		ButtonGroup time = new ButtonGroup();
		time.add(chb_thang);
		time.add(chb_tuan);

		chb_thang.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pnl_chinh.removeAll();
				pnl_chinh_autoGen();
				revalidate();
				repaint();
			}
		});

		chb_tuan.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pnl_chinh.removeAll();
				pnl_chinh_autoGen();
				revalidate();
				repaint();
			}
		});

		chb_tuan.setSelected(true);

		model.setDate(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());
		JDatePanelImpl datePanel = new JDatePanelImpl(model);

		datePicker = new JDatePickerImpl(datePanel, new AbstractFormatter() {
			private String datePattern = "dd/MM/yyyy";
			private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

			@Override
			public Object stringToValue(String text) throws ParseException {
				return dateFormatter.parseObject(text);
			}

			@Override
			public String valueToString(Object value) throws ParseException {
				if (value != null) {
					Calendar cal = (Calendar) value;
					return dateFormatter.format(cal.getTime());
				}
				return "";
			}
		});
		model.setSelected(true);
		model.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				pnl_chinh.removeAll();
				pnl_chinh_autoGen();
				revalidate();
				repaint();
			}
		});

		String[] lp = new String[List_lp.size()];
		for (int i = 0; i < List_lp.size(); i++) {
			lp[i] = List_lp.get(i).getTenLoai();
		}
		DefaultComboBoxModel<String> cbx_modellp = new DefaultComboBoxModel<String>(lp);
		cbx_modellp.addElement("Tất cả");
		cbx_loai = new JComboBox<String>(cbx_modellp);
		cbx_loai.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				pnl_chinh.removeAll();
				pnl_chinh_autoGen();
				revalidate();
				repaint();
			}
		});

		String[] tang = new String[ListTang.size()];
		for (int i = 0; i < ListTang.size(); i++) {
			tang[i] = String.valueOf(ListTang.get(i).getMaTang());
		}
		DefaultComboBoxModel<String> cbx_modeltang = new DefaultComboBoxModel<String>(tang);
		cbx_modeltang.addElement("Tất cả");
		cbx_tang = new JComboBox<String>(cbx_modeltang);
		cbx_tang.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				pnl_chinh.removeAll();
				pnl_chinh_autoGen();
				revalidate();
				repaint();
			}
		});

		pnl_search.setPreferredSize(new Dimension(380, 30));
		pnl_search.setLayout(new FlowLayout(FlowLayout.RIGHT));

		chb_tang = new JCheckBox("Tầng");
		chb_lp = new JCheckBox("Loại phòng");
		chb_tang.setSelected(true);
		cbx_tang.setSelectedIndex(cbx_tang.getItemCount() - 1);
		chb_tang.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pnl_search.removeAll();
				pnl_search.add(lbl_tim);
				pnl_search.add(chb_tang);
				pnl_search.add(cbx_tang);
				pnl_search.add(chb_lp);
				pnl_chinh.removeAll();
				pnl_chinh_autoGen();
				revalidate();
				repaint();
			}
		});
		chb_lp.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pnl_search.removeAll();
				pnl_search.add(lbl_tim);
				pnl_search.add(chb_tang);
				pnl_search.add(chb_lp);
				pnl_search.add(cbx_loai);
				pnl_chinh.removeAll();
				pnl_chinh_autoGen();
				revalidate();
				repaint();
			}
		});
		ButtonGroup btg_loc = new ButtonGroup();
		btg_loc.add(chb_lp);
		btg_loc.add(chb_tang);

		pnl_search.add(lbl_tim);
		pnl_search.add(chb_tang);
		pnl_search.add(cbx_tang);
		pnl_search.add(chb_lp);

		mapMenu.add(btn_homNay);
		mapMenu.add(lbl_tu);
		mapMenu.add(lbl_icon);
		mapMenu.add(datePicker);
		mapMenu.add(chb_tuan);
		mapMenu.add(chb_thang);
		mapMenu.add(pnl_search);

		pnl_status.setPreferredSize(new Dimension(1280, 36));
		pnl_main.add(pnl_status, BorderLayout.SOUTH);

		JLabel lbl_CTrong = new JLabel();
		lbl_CTrong.setOpaque(true);
		lbl_CTrong.setBackground(Color.white);
		lbl_CTrong.setPreferredSize(new Dimension(20, 20));
		lbl_CTrong.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		pnl_status.add(lbl_CTrong);

		JLabel lbl_Trong = new JLabel("Phòng trống");
		pnl_status.add(lbl_Trong);

		JLabel lbl_CBooking = new JLabel();
		lbl_CBooking.setOpaque(true);
		lbl_CBooking.setBackground(Color.cyan);
		lbl_CBooking.setPreferredSize(new Dimension(20, 20));
		lbl_CBooking.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		pnl_status.add(lbl_CBooking);

		JLabel lbl_Booking = new JLabel("Phòng đặt");
		pnl_status.add(lbl_Booking);

		JLabel lbl_CCheckin = new JLabel();
		lbl_CCheckin.setOpaque(true);
		lbl_CCheckin.setBackground(Color.green);
		lbl_CCheckin.setPreferredSize(new Dimension(20, 20));
		lbl_CCheckin.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		pnl_status.add(lbl_CCheckin);

		JLabel lbl_Checkin = new JLabel("Có khách");
		pnl_status.add(lbl_Checkin);

		JLabel lbl_CCheckout = new JLabel();
		lbl_CCheckout.setOpaque(true);
		lbl_CCheckout.setBackground(new Color(77, 122, 160));
		lbl_CCheckout.setPreferredSize(new Dimension(20, 20));
		lbl_CCheckout.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		pnl_status.add(lbl_CCheckout);

		JLabel lbl_Checkout = new JLabel("Khách đã trả phòng");
		pnl_status.add(lbl_Checkout);

		JLabel lbl_CMuon = new JLabel();
		lbl_CMuon.setOpaque(true);
		lbl_CMuon.setBackground(Color.red);
		lbl_CMuon.setPreferredSize(new Dimension(20, 20));
		lbl_CMuon.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		pnl_status.add(lbl_CMuon);

		JLabel lbl_Muon = new JLabel("Ở quá hạn");
		pnl_status.add(lbl_Muon);

		JLabel lbl_CTre = new JLabel();
		lbl_CTre.setOpaque(true);
		lbl_CTre.setBackground(Color.PINK);
		lbl_CTre.setPreferredSize(new Dimension(20, 20));
		lbl_CTre.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		pnl_status.add(lbl_CTre);

		JLabel lbl_Tre = new JLabel("Nhận phòng muộn");
		pnl_status.add(lbl_Tre);

		JLabel lbl_CDon = new JLabel();
		lbl_CDon.setOpaque(true);
		lbl_CDon.setBackground(Color.BLUE);
		lbl_CDon.setPreferredSize(new Dimension(10, 10));
		lbl_CDon.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		pnl_status.add(lbl_CDon);

		JLabel lbl_Don = new JLabel("Cần dọn");
		pnl_status.add(lbl_Don);
	}

	public void pnl_chinh_autoGen() {
		pnl_chinh.removeAll();
		int DAY = model.getValue().getDate();
		int month = model.getValue().getMonth() + 1;
		int year = model.getValue().getYear() + 1900;
		ym = YearMonth.of(year, month);
		date = LocalDate.of(year, month, DAY);
		int time;
		if (chb_thang.isSelected())
			time = 30;
		else
			time = 7;
		int rong = 240 * time + 80;
		JPanel pnl_header = new JPanel();
		pnl_header.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		pnl_header.setBounds(0, 0, rong, 30);
		pnl_chinh.add(pnl_header);
		JLabel lbl_Title = new JLabel("");
		lbl_Title.setFont(new Font("TAHOMA", Font.BOLD, 22));
		lbl_Title.setPreferredSize(new Dimension(80, 30));
		pnl_header.add(lbl_Title);
		pnl_header.setBackground(Color.white);
		for (int i = DAY; time > 0; i++) {
			if (i > ym.lengthOfMonth()) {
				i = 1;
				System.out.println("547");
				if (ym.getMonthValue() == 12)
					ym = YearMonth.of(year + 1, 1);
				else
					ym = YearMonth.of(year, month + 1);
			}
			JLabel lbl_day = new JLabel("Ngày " + String.valueOf(i));
			lbl_day.setFont(new Font("TAHOMA", Font.BOLD, 22));
			lbl_day.setPreferredSize(new Dimension(240, 30));
			lbl_day.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.gray));
			lbl_day.setHorizontalAlignment(SwingConstants.CENTER);
			pnl_header.add(lbl_day);
			time--;
		}
		int limit;
		if (chb_tang.isSelected()) {
			limit = ListTang.size();
		} else
			limit = List_lp.size();
		for (int i = 0; i < limit; i++) {
			List<ModelPhong> dsp;
			if (chb_tang.isSelected()) {
				dsp = ListTang.get(i).getDSPhong();
				if (limit != cbx_tang.getSelectedIndex()) {
					if (i != cbx_tang.getSelectedIndex()) {
						continue;
					} else {
					}
				}
			} else {
				dsp = List_lp.get(i).getDSPhong();
				if (limit != cbx_loai.getSelectedIndex()) {
					if (i != cbx_loai.getSelectedIndex()) {
						continue;
					}
				}
			}
			JLabel lbl_tang;
			if (chb_tang.isSelected())
				lbl_tang = new JLabel(String.valueOf("Tầng " + ListTang.get(i).getMaTang()));
			else
				lbl_tang = new JLabel(List_lp.get(i).getTenLoai());
			lbl_tang.setBounds(0, (pnl_chinh.getComponentCount()) * 30, rong, 30);
			lbl_tang.setFont(new Font("TAHOMA", Font.PLAIN, 22));
			pnl_chinh.add(lbl_tang);
			for (int y = 0; y < dsp.size(); y++) {
				if (dsp.get(y).getLoaiphong() == null)
					continue;
				final JPanel pnl_phong = new JPanel();
				final ModelPhong XX = dsp.get(y);
				pnl_phong.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
				pnl_phong.setBounds(0, (pnl_chinh.getComponentCount()) * 30, rong, 30);
				pnl_phong.setLayout(null);
				pnl_phong.setBackground(Color.white);
				for (ModelKhachTrongPhong ktp : list_ktp) {
					int Time = 7;
					if (chb_thang.isSelected())
						Time = 30;
					long IN = ktp.getHoadon().getNgayCheckIn().getTime();
					long OUT = ktp.getHoadon().getNgayCheckOut().getTime();
					Date in = new Date(IN);
					Date out = new Date(OUT);
					System.out.println(model.getValue().getMonth());
					java.util.Date min = new java.util.Date(model.getValue().getYear(), model.getValue().getMonth(),
							model.getValue().getDate());
					java.util.Date gioihan = new java.util.Date(model.getValue().getYear(), model.getValue().getMonth(),
							model.getValue().getDate() + Time);
					if (min.getTime() > IN) {
						in = min;
					}
					if (gioihan.getTime() < OUT) {
						out = gioihan;
					}
					System.out.println("ngay check in " + in);
					System.out.println("ngay check out " + out);
					if (ktp.getPhong().getMaPhong() == dsp.get(y).getMaPhong()
							&& ((min.getTime() < ktp.getHoadon().getNgayCheckIn().getTime()
									&& ktp.getHoadon().getNgayCheckIn().getTime() < gioihan.getTime())
									|| (gioihan.getTime() > ktp.getHoadon().getNgayCheckOut().getTime()
											&& ktp.getHoadon().getNgayCheckOut().getTime() > min.getTime()))) {
						System.out.println("vao");
						String title = "";
						if (ktp.getHoadon().getDskhachhang().size() > 1) {
							title = "Đoàn: " + ktp.getHoadon().getTenDoan();
						} else
							title = "Khách lẻ: " + ktp.getHoadon().getDskhachhang().get(0).getTenKhachHang();
						final JLabel lbl_ph = new JLabel(title);
						for (ModelHoaDon mhd : list_hd) {
							if (ktp.getHoadon().getMaHoaDon() == mhd.getMaHoaDon()) {
								hd = mhd;
								break;
							}
						}
						final int x = y;
						final int Y = (pnl_chinh.getComponentCount()) * 30;
						final ModelKhachTrongPhong k = ktp;
						lbl_ph.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								if (SwingUtilities.isRightMouseButton(e)) {
									menuStatus.removeAll();
									menuCI.removeAll();
									if (k.getHoadon().getTrangThai().equals("datphong")) {
										JMenuItem item_checkin = new JMenuItem("Check in");
										item_checkin.addActionListener(new ActionListener() {

											public void actionPerformed(ActionEvent e) {
												// TODO Auto-generated method stub
												if (k.getHoadon().getTrangThai().equals("datphong")) {
													for (ModelHoaDon mhd : list_hd) {
														if (k.getHoadon().getMaHoaDon() == mhd.getMaHoaDon()) {
															hd = mhd;
															break;
														}
													}
													double gia = 0, giasau = 0;
													LocalDateTime ldt = LocalDateTime.now();
													
													for (ModelKhachTrongPhong mktp : list_ktp) {
														for(ModelKhachTrongPhong MK:hd.getDskhactrongphong()) {
															if (mktp.getPhong().getMaPhong() == MK.getPhong().getMaPhong()) {
																if((hd.getMaHoaDon()==mktp.getHoadon().getMaHoaDon())||mktp.getHoadon().getTrangThai().equals("checkout")||mktp.getHoadon().getTrangThai().equals("huy"))continue;
																long limitin = mktp.getHoadon().getNgayCheckIn().getTime();
																long limitout = mktp.getHoadon().getNgayCheckOut().getTime();
																long extime = new java.util.Date(ldt.getYear() - 1900,
																		ldt.getMonthValue() - 1, ldt.getDayOfMonth(), ldt.getHour(),
																		ldt.getMinute()).getTime();
																long timein=k.getHoadon().getNgayCheckOut().getTime();
																System.out.println("MA HOA DON " +mktp.getHoadon().getMaHoaDon());
																if ((extime < limitout&&extime>limitin)||(extime<limitin&&timein>limitout)) {
																	JOptionPane.showMessageDialog(pnl_main,
																			"Không thể check in cho phòng vào thời gian này");
																	return;
																}
																//hd.setNgayCheckOut(new Date(extime));
															}
														}
													}
													System.out.println("Phong " + k.getPhong().getMaPhong());
													Double[] g;
													for (ModelHoaDon mhd : list_hd) {
														if (k.getHoadon().getMaHoaDon() == mhd.getMaHoaDon()) {
															hd = mhd;
															break;
														}
													}
													ModelHoaDon hoadonmau = new ModelHoaDon();
													for (ModelHoaDon mhd : list_hd) {
														if (k.getHoadon().getMaHoaDon() == mhd.getMaHoaDon()) {
															hoadonmau = mhd;
															break;
														}
													}
													Date begin = hd.getNgayCheckIn();
													String lo = hd.getLoai();
													java.util.Date homnayy = new java.util.Date(ldt.getYear() - 1900,
															ldt.getMonthValue() - 1, ldt.getDayOfMonth(), ldt.getHour(),
															ldt.getMinute());
													List<ModelKhachTrongPhong> list_thaydoi = new ArrayList<>();
													hoadonmau.setNgayCheckIn(homnayy);
													for (ModelKhachTrongPhong K : list_ktp) {
														System.out.println("phu troi k" + K.getPhutroi2());
														if (K.getHoadon().getMaHoaDon() == hd.getMaHoaDon()) {
															list_thaydoi.add(K);
															for (ModelLoaiPhong mlp : List_lp) {
																if (mlp.getMaLoaiPhong() == K.getPhong().getLoaiphong()
																		.getMaLoaiPhong()) {
																	for (ModelKhachTrongPhong b : list_ktp) {
																		if (b.getHoadon().getMaHoaDon() == hd
																				.getMaHoaDon()) {
																			gia += b.getGiaPhong();
																			gia += b.getPhuTroi();
																			gia += b.getPhutroi2();
																		}
																	}
																	g = gia(mlp, hoadonmau.getLoai(), hoadonmau);
																	list_thaydoi.get(list_thaydoi.size() - 1)
																			.setGiaPhong(Float.parseFloat(g[0] + ""));
																	list_thaydoi.get(list_thaydoi.size() - 1)
																			.setPhuTroi(Float.parseFloat(g[1] + ""));
																	list_thaydoi.get(list_thaydoi.size() - 1)
																			.setPhutroi2(Float.parseFloat(g[2] + ""));
																	giasau += (g[0] + g[1] + g[2]);

																}
															}
														}
													}
													hoadonmau.setNgayCheckIn(begin);
													for (ModelHoaDon mhd : list_hd) {
														if (k.getHoadon().getMaHoaDon() == mhd.getMaHoaDon()) {
															hd = mhd;
															break;
														}
													}
													for (ModelLoaiPhong mlp : List_lp) {
														if (mlp.getMaLoaiPhong() == k.getPhong().getLoaiphong()
																.getMaLoaiPhong()) {
															java.util.Date checkin = hd.getNgayCheckIn();
															if (checkin.getTime() > homnayy.getTime()) {
																hoadonmau.setNgayCheckIn(homnayy);
																if (hoadonmau.getLoai().equals("dem")) {
																	int cis = 0;
																	for (PhuTroi x : mlp.getDsphutroi()) {
																		if (x.getLoai().equals("checkindem")) {
																			cis++;
																		}
																	}
																	Date ci = new Date(homnayy.getYear(),
																			homnayy.getMonth(), homnayy.getDate(),
																			ks.getGioCheckIn() + cis, 0);
																	if (homnayy.getTime() < ci.getTime()) {
																		int c = JOptionPane.showConfirmDialog(pnl_main,
																				"Check in sớm trước quy định của khung đêm, đổi sang khung ngày ?");
																		if (c != 0)
																			return;
																		hoadonmau.setLoai("ngay");
																		lo = "ngay";
																	}
																}
																if (hoadonmau.getLoai().equals("gio")) {
																	boolean check = false;
																	long MAX = (hd.getNgayCheckOut().getTime()
																			- homnayy.getTime()) / (1000 * 60 * 60);
																	if (MAX > ks.getGio()) {
																		int c = JOptionPane.showConfirmDialog(pnl_main,
																				"Check in sớm trước quy định của khung giờ, đổi sang khung ngày ?");
																		if (c != 0)
																			return;
																		hoadonmau.setLoai("ngay");
																		lo = "ngay";
																	}
																}
																System.out.println("giasau "+giasau+"   "+gia);
																long msec = checkin.getTime() - homnayy.getTime();
																int hours = 0, minutes = 0, days = 0;
																days = (int) (msec / (1000 * 60 * 60 * 24));
																hours = (int) ((msec - days * (1000 * 60 * 60 * 24))
																		/ (1000 * 60 * 60));
																minutes = (int) ((msec - (days * (1000 * 60 * 60 * 24)
																		+ (hours * (1000 * 60 * 60)))) / (1000 * 60));
																JOptionPane.showMessageDialog(pnl_main, "Check in sớm "
																		+ days + " ngày " + hours + " giờ " + minutes
																		+ " phút phát sinh thêm " + (giasau - gia));
															}
														}
													}
													if (JOptionPane.showConfirmDialog(pnl_main,
															"Check in cho hóa đơn này ?") != 0) {
														return;
													}
													if(begin.getTime()>homnayy.getTime()) {
														begin = homnayy;
													}
													dao_khachTrongPhong d = new dao_khachTrongPhong();
													for (ModelKhachTrongPhong X : list_thaydoi) {
														d.save(new KhachTrongPhong(X.getId(),
																new HoaDon(hd.getMaHoaDon()),
																new Phong(X.getPhong().getMaPhong()), X.getGiaPhong(),
																X.getPhuTroi(), X.getPhutroi2(), 0, ""));
														System.out.println("PhuTroi: " + X.getPhutroi2());
													}
													hd.setNgayCheckIn(begin);
													hd.setTrangThai("checkin");
													hd.setLoai(lo);
													ser_hd.save(hd, false);
													List_lp = ser_lp.getLp();
													list_hd = ser_hd.getList();
													ks = ser.getKs();
													ListTang = ks.getDSTang();
													list_ktp = ser_ktp.getList();
													pnl_chinh_autoGen();
													revalidate();
													repaint();
													JOptionPane.showMessageDialog(pnl_main, "check in thành công!!");
												}

											}
										});
										JMenuItem item_fix = new JMenuItem("sửa đặt phòng");
										item_fix.addActionListener(new ActionListener() {

											public void actionPerformed(ActionEvent e) {
												// TODO Auto-generated method stub
												pnl_main.removeAll();
												for (ModelHoaDon mhd : list_hd) {
													if (k.getHoadon().getMaHoaDon() == mhd.getMaHoaDon()) {
														hd = mhd;
														break;
													}
												}
												System.out.println("Ma hoa don: " + hd.getMaHoaDon());
												pnl_main.add(new Booking(hd));
												revalidate();
												repaint();
											}
										});
										menuCI.add(item_checkin);
										menuCI.add(item_fix);
									} else if (k.getHoadon().getTrangThai().equals("checkin")) {
										JMenuItem menu_1 = new JMenuItem("checkout");
										JMenuItem menu_2 = new JMenuItem("chuyển phòng");
										JMenuItem menu_3 = new JMenuItem("đặt dịch vụ");
										JMenuItem menu_4 = new JMenuItem("thêm phụ phí");
										JMenuItem menu_5 = new JMenuItem("tách hóa đơn");
										menu_5.addActionListener(new ActionListener() {
											
											@Override
											public void actionPerformed(ActionEvent e) {
												// TODO Auto-generated method stub
												for (ModelHoaDon mhd : list_hd) {
													if (k.getHoadon().getMaHoaDon() == mhd.getMaHoaDon()) {
														hd = mhd;
														break;
													}
												}
												if(framethd==null)framethd=new View_tachHd(hd);
												framethd.setVisible(true);
											}
										});
										menu_4.addActionListener(new ActionListener() {
											
											@Override
											public void actionPerformed(ActionEvent e) {
												// TODO Auto-generated method stub
												for (ModelHoaDon mhd : list_hd) {
													if (k.getHoadon().getMaHoaDon() == mhd.getMaHoaDon()) {
														hd = mhd;
														break;
													}
												}
												HoaDonModel hdmd=new HoaDonModel();
												hdmd.setMaHoaDon(k.getHoadon().getMaHoaDon());
												PhongModel pm=new PhongModel();
												pm.setMaPhong(k.getPhong().getMaPhong());
												if(framepp==null)framepp= new HoaDonPhuPhi(new KhachTrongPhongModel(k.getId(), hdmd, pm, k.getGiaPhong(), k.getPhuTroi(), k.getPhuPhi(), ""));
												framepp.setVisible(true);
											}
										});
										menu_2.addActionListener(new ActionListener() {

											public void actionPerformed(ActionEvent e) {
												// TODO Auto-generated method stub
												JOptionPane.showMessageDialog(pnl_main, "Chọn phòng cần chuyển");
												if (lst_pnl.size() > 0)
													lst_pnl.get(0).setBackground(Color.white);
												lst_pnl.clear();
												lst_pnl.add(pnl_phong);
												lst_pnl.get(0).setBackground(Color.LIGHT_GRAY);
												pnl_search.removeAll();
												pnl_search.add(lbl_tim);
												pnl_search.add(chb_tang);
												pnl_search.add(chb_lp);
												pnl_search.add(cbx_loai);
												pnl_chinh.removeAll();
												chb_lp.setSelected(true);
												cbx_loai.setSelectedItem(k.getPhong().getLoaiphong().getTenLoai());
												pnl_chinh_autoGen();
												revalidate();
												repaint();
												doiphong = true;
												for (int i = 0; i < pnl_search.getComponentCount(); i++) {
													pnl_search.getComponent(i).setEnabled(false);
												}
												chuyenPhong = k;
											}
										});

										menu_1.addActionListener(new ActionListener() {

											@Override
											public void actionPerformed(ActionEvent e) {
												// TODO Auto-generated method stub
												for (ModelHoaDon mhd : list_hd) {
													if (k.getHoadon().getMaHoaDon() == mhd.getMaHoaDon()) {
														hd = mhd;
														break;
													}
												}
												LocalDateTime ldt = LocalDateTime.now();
												hd.setNgayCheckOut(new java.util.Date(ldt.getYear() - 1900,
														ldt.getMonthValue() - 1, ldt.getDayOfMonth(), ldt.getHour(),
														ldt.getMinute()));
												pnl_main.removeAll();
												pnl_main.add(new view_hoadon(hd));
												revalidate();
												repaint();
											}
										});
										menuCI.add(menu_1);
										menuCI.add(menu_2);
										menuCI.add(menu_3);
										menuCI.add(menu_4);
										for (ModelHoaDon mhd : list_hd) {
											if (k.getHoadon().getMaHoaDon() == mhd.getMaHoaDon()) {
												hd = mhd;
												break;
											}
										}
										if(hd.getDskhachhang().size()>1&&hd.getDskhactrongphong().size()>1) {
											menuCI.add(menu_5);
										}
									}
									JMenuItem itemRahan = new JMenuItem("Gia hạn");
									itemRahan.addActionListener(new ActionListener() {

										@Override
										public void actionPerformed(ActionEvent e) {
											for (ModelHoaDon mhd : list_hd) {
												if (k.getHoadon().getMaHoaDon() == mhd.getMaHoaDon()) {
													hd = mhd;
													break;
												}
											}
											// TODO Auto-generated method stub
											String soGio = JOptionPane.showInputDialog("nhập số giờ muốn gia hạn");
											if (!soGio.matches("\\d+")) {
												JOptionPane.showMessageDialog(pnl_main, "Số giờ không đúng");
												return;
											}
											long extime=0;
											for (ModelKhachTrongPhong mktp : list_ktp) {
												for(ModelKhachTrongPhong MK:hd.getDskhactrongphong()) {
													if (mktp.getPhong().getMaPhong() == MK.getPhong().getMaPhong()) {
														if((hd.getMaHoaDon()==mktp.getHoadon().getMaHoaDon()))continue;
														long limitin = mktp.getHoadon().getNgayCheckIn().getTime();
														long limitout = mktp.getHoadon().getNgayCheckOut().getTime();
														extime = k.getHoadon().getNgayCheckOut().getTime()
																+ (Long) (Long.parseLong(soGio) * 60 * 60 * 1000);
														long timein=k.getHoadon().getNgayCheckIn().getTime();
														System.out.println("MA HOA DON " +mktp.getHoadon().getMaHoaDon());
														if ((extime < limitout&&extime>limitin)||(timein<limitin&&extime>limitout)) {
															JOptionPane.showMessageDialog(pnl_main,
																	"Không thể gia hạn cho phòng vào thời gian này");
															return;
														}
														//hd.setNgayCheckOut(new Date(extime));
													}
												}
											}
											
											//
											Date outB=hd.getNgayCheckOut();
											Date timeout=new Date(extime);
											String lo=hd.getLoai(),loB=hd.getLoai();
											ModelHoaDon hoadonmau=hd;
											A:
											for(ModelLoaiPhong mlp:List_lp) {
												for(ModelKhachTrongPhong mktp:list_ktp) {
													if(mktp.getPhong().getLoaiphong().getMaLoaiPhong()==mlp.getMaLoaiPhong()) {
														if (hoadonmau.getLoai().equals("dem")) {
															int cis = 0,cos=0;
															for (PhuTroi x : mlp.getDsphutroi()) {
																if (x.getLoai().equals("checkindem")) {
																	cis++;
																}
																if (x.getLoai().equals("checkoutdem")) {
																	cos++;
																}
															}
															long gap=0;
															if(hd.getNgayCheckIn().getDate()!=timeout.getDate()) {
																
																gap=24-(ks.getGioCheckInDem()/100-cis);
																gap+=ks.getGioCheckout()/100+cos;
																gap-=(24-hd.getNgayCheckIn().getHours());
															}else {
																gap+=ks.getGioCheckout()/100+cos;
																gap-=hd.getNgayCheckIn().getHours();
															}
															long MAX=new Date(hd.getNgayCheckIn().getYear(), hd.getNgayCheckIn().getMonth(), hd.getNgayCheckIn().getDate(), hd.getNgayCheckIn().getHours()+(int)gap-1, 0).getTime();
															if(MAX<timeout.getTime()) {
																int c = JOptionPane.showConfirmDialog(pnl_main,
																		"quá giới hạn khung đêm, đổi sang khung ngày ?");
																if (c != 0) {
																	return;
																}
																lo="ngay";
																hoadonmau.setLoai(lo);
																break A;
															}
														}
														if (hoadonmau.getLoai().equals("gio")) {
															boolean check = false;
															long MAX = (timeout.getTime()
																	- hd.getNgayCheckIn().getTime()) / (1000 * 60 * 60);
															if (MAX > ks.getGio()) {
																int c = JOptionPane.showConfirmDialog(pnl_main,
																		"Check in sớm trước quy định của khung giờ, đổi sang khung ngày ?");
																if (c != 0)
																	return;
																lo = "ngay";
																hoadonmau.setLoai(lo);
																break A;
															}
														}
													}
												}
											}
											//
											hd.setNgayCheckOut(new Date(extime));
											List<ModelKhachTrongPhong> list_thaydoi=new ArrayList<>();
											float gia=0,giaSau=0;
											for (ModelKhachTrongPhong mktp : list_ktp) {
												if(hd.getMaHoaDon()==mktp.getHoadon().getMaHoaDon()) {
													list_thaydoi.add(mktp);
													gia+=(mktp.getGiaPhong()+mktp.getPhuTroi()+mktp.getPhutroi2());
													for(ModelLoaiPhong lp:List_lp) {
														if(mktp.getPhong().getLoaiphong().getMaLoaiPhong()==lp.getMaLoaiPhong()) {
															giaSau+=Float.parseFloat(gia(lp, hd.getLoai(), hd)[0]+"");
															list_thaydoi.get(list_thaydoi.size()-1).setGiaPhong(Float.parseFloat(gia(lp, hd.getLoai(), hd)[0]+""));
															giaSau+=Float.parseFloat(gia(lp, hd.getLoai(), hd)[1]+"");
															list_thaydoi.get(list_thaydoi.size()-1).setPhuTroi(Float.parseFloat(gia(lp, hd.getLoai(), hd)[1]+""));
															giaSau+=Float.parseFloat(gia(lp, hd.getLoai(), hd)[2]+"");
															list_thaydoi.get(list_thaydoi.size()-1).setPhutroi2(Float.parseFloat(gia(lp, hd.getLoai(), hd)[2]+""));
														}
													}
												}
											}
											hd.setNgayCheckOut(outB);
											hd.setLoai(loB);
											JOptionPane.showMessageDialog(pnl_main, "Phát sinh thêm "+(giaSau-gia)+"VND cho khoảng thời gian gia hạn!!");
											if(JOptionPane.showConfirmDialog(pnl_main, "gia hạn cho hóa đơn này ?")!=0) {
												return;
											}
											dao_khachTrongPhong d=new dao_khachTrongPhong();
											for (ModelKhachTrongPhong X : list_thaydoi) {
												d.save(new KhachTrongPhong(X.getId(),
														new HoaDon(hd.getMaHoaDon()),
														new Phong(X.getPhong().getMaPhong()), X.getGiaPhong(),
														X.getPhuTroi(), X.getPhutroi2(), 0, ""));
												System.out.println("PhuTroi: " + X.getPhutroi2());
											}
											hd.setNgayCheckOut(timeout);
											hd.setLoai(lo);
											System.out.println("ngay out moi: " + hd.getNgayCheckOut());
											ser_hd.save(hd, false);
											JOptionPane.showMessageDialog(pnl_main, "Gia hạn thành công");
											List_lp = ser_lp.getLp();
											list_hd = ser_hd.getList();
											ks = ser.getKs();
											ListTang = ks.getDSTang();
											list_ktp = ser_ktp.getList();
											pnl_chinh_autoGen();
											revalidate();
											repaint();
											return;
										}
									});
									if (!hd.getTrangThai().equals("checkout")) {
										menuCI.add(itemRahan);
									}
									menuCI.show(lbl_ph, e.getX(), 0);
								}

							}

//							public void mouseEntered(MouseEvent e) {
//								menuStatus.removeAll();
//								int ngay=e.getX()/240;
//								System.out.println("X la "+e.getX());
//								Date d=new Date(model.getYear(),model.getMonth(),model.getDay()+ngay);
//								menuStatus.add(new JMenuItem("Phòng "+k.getPhong().getMaPhong()+" ngày "+String.valueOf(d.getYear()+"-"+d.getMonth()+"-"+d.getDate())));
//								menuStatus.show(pnl_phong,e.getX(),e.getY());
//							}

							public void mouseExited(MouseEvent e) {
							}

						});
						lbl_ph.setOpaque(true);
						int wide = 0;
						for (int a = in.getDate(); a < out.getDate(); a++) {
							wide++;
						}
						long X = (long) ((in.getTime() - (min.getTime())) / (360000));
						System.out.println(in);
						long Z = (long) ((out.getTime() - in.getTime()) / (360000));
						System.out.println(out);
						if(Z<2)Z=2;
						lbl_ph.setBounds((int) (X) + 80, 1, (int) Z, 28);
						if (ktp.getHoadon().getTrangThai().equals("datphong")) {
							lbl_ph.setBackground(Color.cyan);
						}
						if (ktp.getHoadon().getTrangThai().equals("checkin")) {
							lbl_ph.setBackground(Color.green);
						}
						if (ktp.getHoadon().getTrangThai().equals("checkout")) {
							lbl_ph.setBackground(new Color(77, 122, 160));
						}
						LocalDateTime timenow = LocalDateTime.now();
						if (ktp.getHoadon().getTrangThai().equals("checkin")
								&& ((timenow.getDayOfMonth() == out.getDate()
										&& ktp.getHoadon().getNgayCheckOut().getHours() < timenow.getHour())
										|| (timenow.getDayOfMonth() == out.getDate()
												&& out.getHours() == timenow.getHour()
												&& out.getMinutes() < timenow.getMinute())
										|| (timenow.getDayOfMonth() > out.getDate()))) {
							lbl_ph.setBackground(Color.red);
						}
						if (ktp.getHoadon().getTrangThai().equals("datphong")
								&& ((timenow.getDayOfMonth() == in.getDate() && in.getHours() < timenow.getHour())
										|| (timenow.getDayOfMonth() == in.getDate()
												&& in.getHours() == timenow.getHour()
												&& in.getMinutes() < timenow.getMinute())
										|| (timenow.getDayOfMonth() > in.getDate()))) {
							lbl_ph.setBackground(Color.pink);
						}
						pnl_phong.add(lbl_ph);
					}
				}
				final ModelPhong mdp = dsp.get(y);
				final int Y = (pnl_chinh.getComponentCount()) * 30;
				pnl_phong.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if (SwingUtilities.isRightMouseButton(e)) {
							if (lst_pnl.size() > 0)
								lst_pnl.get(0).setBackground(Color.white);
//							lst_pnl.clear();
//							lst_pnl.add(pnl_phong);
//							lst_pnl.get(0).setBackground(Color.LIGHT_GRAY);
							menuStatus.removeAll();
							menu.show(pnl_chinh, e.getX(), Y);
							return;
						}
						if (doiphong) {
							lst_pnl.clear();
							lst_pnl.add(pnl_phong);
							list_cp.clear();
							list_cp.add(mdp);
							if (JOptionPane.showConfirmDialog(pnl_main,
									"Bạn có muốn đổi sang phòng " + XX.getMaPhong()) == 0) {
								chuyenPhong.setPhong(new ModelPhong(XX.getMaPhong()));
								dao_khachTrongPhong d = new dao_khachTrongPhong();
								d.save(new KhachTrongPhong(chuyenPhong.getId(), new HoaDon(hd.getMaHoaDon()),
										new Phong(XX.getMaPhong(), new LoaiPhong(XX.getLoaiphong().getMaLoaiPhong(),
												XX.getLoaiphong().getTenLoai()))));
								JOptionPane.showMessageDialog(pnl_main, "chuyển phòng thành công!!");
							} else {
								JOptionPane.showMessageDialog(pnl_main, "chuyển phòng thất bại!!");
							}
							for (int i = 0; i < pnl_search.getComponentCount(); i++) {
								pnl_search.getComponent(i).setEnabled(true);
							}
							doiphong = false;
							List_lp = ser_lp.getLp();
							list_hd = ser_hd.getList();
							ks = ser.getKs();
							ListTang = ks.getDSTang();
							list_ktp = ser_ktp.getList();
							pnl_chinh_autoGen();
							revalidate();
							repaint();
						}
//						JPopupMenu thongbao=new JPopupMenu();
//						thongbao.add(date.get)
					}

					public void mouseEntered(MouseEvent e) {
//						menuStatus.removeAll();
//						int ngay=e.getX()/240;
//						Date d=new Date(model.getYear(),model.getMonth(),model.getDay()+ngay);
//						JMenuItem it=new JMenuItem("Phòng "+mdp.getMaPhong()+" ngày "+String.valueOf(d.getYear()+"-"+d.getMonth()+"-"+d.getDate()));
//						it.setEnabled(false);
//						menuStatus.add(it);
//						menuStatus.show(pnl_phong,e.getX(),0);
					}

					public void mouseExited(MouseEvent e) {
//						menuStatus.removeAll();
					}

				});
				if (dsp.get(y).getLoaiphong() != null)
					pnl_chinh.add(pnl_phong);

				JLabel lbl_tenPhong = new JLabel(String.valueOf(dsp.get(y).getMaPhong()));
				lbl_tenPhong.setFont(new Font("TAHOMA", Font.PLAIN, 22));
				lbl_tenPhong.setBounds(0, 0, 80, 30);
				lbl_tenPhong.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.gray));
				pnl_phong.add(lbl_tenPhong);

			}
		}
		pnl_chinh.setPreferredSize(new Dimension(rong, pnl_chinh.getComponentCount() * 30));
	}

	Double[] gia(ModelLoaiPhong p, String loai, ModelHoaDon hd) {
		java.util.Date in, out;
		in = hd.getNgayCheckIn();
		out = hd.getNgayCheckOut();
		int hours = (int) ((out.getTime() - in.getTime()) / 3600000);
		if (((double) (out.getTime() - in.getTime()) / 3600000) > hours) {
			hours++;
		}
		int gin;
		if (loai.equals("ngay"))
			gin = (int) ks.getGioCheckIn() / 100;
		else
			gin = (int) ks.getGioCheckInDem() / 100;
		double gia = 0, CIS = 0, COM = 0;
		if (loai.equals("gio")) {
			long gio = hd.getNgayCheckOut().getTime() - hd.getNgayCheckIn().getTime();
			gio = gio / (1000 * 60 * 60);
			gia += p.getGiaTheoGio() * gio;
			return new Double[] { gia, CIS, COM };
		}
		boolean checkInSom = true, dugio = true;
		int gio = hd.getNgayCheckIn().getHours();
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
				System.out.println(x.getPhuTroi());
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
						if (i != 0)
							gia += p.getGiaPhong();
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
					x = 0;
				}
				if (gio == (int) (ks.getGioCheckOutDem() / 100) && loai.equals("dem")) {
					dugio = !dugio;
					gia += p.getGiaQuaDem();
					x = 0;
				}
			}
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
		System.out.println("Phu troi " + CIS);
		return a;
	}

	public static void main(String[] args) {
		new Thread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				new QuanLyThuePhong().setVisible(true);
			}
		}).start();
	}
}
