package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import Entities.PhuTroi;
import Repositories.dao_phutroi;
import Entities.LoaiPhong;
import Services.KhachHang_services;
import Services.KhachSan_services;
import Services.loaiPhong_services;
import ViewModels.ModelKhachSan;
import ViewModels.ModelLoaiPhong;
import ViewModels.ModelPhong;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class VLoaiPhong extends JFrame {

	JLabel chckbxTheoGi = new JLabel("Theo Giờ");
	JLabel chckbxNewCheckBox = new JLabel("Qua Đêm");
	JLabel lblNewLabel = new JLabel("Giá ngày");
	JPanel pnl_checkoutmuon = new JPanel();
	JPanel pnl_checkinsom = new JPanel();
	JPanel pnl_chinh = new JPanel();
	JButton btn_themci = new JButton("thêm");
	JScrollPane sc_gio=new JScrollPane();
	JPanel pnl_gio=new JPanel();
	JPanel panel_3 = new JPanel();
	JPanel panel_2 = new JPanel();
	JButton btn_themco = new JButton("thêm");
	JLabel lbl_songuoi = new JLabel("1");
	JPanel panel = new JPanel();
	private JTextField txt_gia;

	int chon = 0, gia=0;
	double value=0;
	List<PhuTroi> lst_ci = new ArrayList<PhuTroi>();
	List<PhuTroi> lst_co = new ArrayList<PhuTroi>();
	List<PhuTroi> lst_cid = new ArrayList<PhuTroi>();
	List<PhuTroi> lst_cod = new ArrayList<PhuTroi>();
	List<PhuTroi> lst_gio = new ArrayList<PhuTroi>();
	loaiPhong_services ser_lp;
	KhachSan_services serks;
	List<JLabel> list_lbl_lp;
	List<ModelLoaiPhong> list_lp;
	ModelKhachSan ks;

	public VLoaiPhong() {
		ser_lp = new loaiPhong_services();
		serks=new KhachSan_services();
		list_lp = ser_lp.getLp();
		list_lbl_lp = new ArrayList<JLabel>();
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout());
		setSize(1280, 720);
		setting();
		for(int i=0;i<list_lp.size();i++) {
			chon=i;
			lst_ci.clear();
			lst_co.clear();
			lst_cid.clear();
			lst_cod.clear();
			getData();
			addlp();
		}
		chon=0;
		lst_ci.clear();
		lst_co.clear();
		lst_cid.clear();
		lst_cod.clear();
		
		
//		if(!list_lp.isEmpty()) {
			getData();
			addlp();
			double ngay=list_lp.get(chon).getGiaPhong(),gio=list_lp.get(chon).getGiaTheoGio(),dem=list_lp.get(chon).getGiaQuaDem();
			if(gia==0&&value==0) {
				value=ngay;
			}
			else if(gia==1&&value==0)value=dem;
			else if(gia==2&&value==0)value=gio;
			events();
			ks=serks.getKs();
//		}
		
		
	}

	void addlp() {
		panel.removeAll();
		list_lbl_lp.clear();
		for (int i = 0; i < list_lp.size(); i++) {
			System.out.println(i+ list_lp.get(i).getTenLoai());
			final JLabel lbl_loai = new JLabel(list_lp.get(i).getTenLoai());
			lbl_loai.setPreferredSize(new Dimension(320, 40));
			lbl_loai.setOpaque(true);
			lbl_loai.setBackground(Color.white);
			if (i == chon) {
				lbl_loai.setBackground(Color.cyan);
			}
			lbl_loai.setBorder(BorderFactory.createEtchedBorder());
			lbl_loai.setFont(new Font("TAHOMA", Font.PLAIN, 17));
			final int x = i;
			lbl_loai.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					List<PhuTroi> list=new ArrayList<PhuTroi>();
					for(PhuTroi a:lst_ci) {
						list.add(a);
					}
					for(PhuTroi a:lst_co) {
						list.add(a);
					}
					for(PhuTroi a:lst_cid) {
						list.add(a);
					}
					for(PhuTroi a:lst_cod) {
						list.add(a);
					}
//					for(PhuTroi a:lst_gio) {
//						list.add(a);
//					}
					list_lp.get(chon).setDsphutroi(list);
					chon = x;
					value=0;
					double ngay=list_lp.get(chon).getGiaPhong(),gio=list_lp.get(chon).getGiaTheoGio(),dem=list_lp.get(chon).getGiaQuaDem();
					if(gia==0&&value==0) {
						value=ngay;
					}
					else if(gia==1&&value==0)value=dem;
					else if(gia==2&&value==0)value=gio;
					else value=Double.parseDouble(txt_gia.getText());
					if(gia==0) {
						list_lp.get(chon).setGiaPhong(value);
					}
					else if(gia==1)list_lp.get(chon).setGiaQuaDem(value);
					else if(gia==2)list_lp.get(chon).setGiaTheoGio(value);;
					for (int i = 0; i < list_lbl_lp.size(); i++) {
						list_lbl_lp.get(i).setBackground(Color.white);
					}
					txt_gia.setText(value+"");
					lbl_songuoi.setText(list_lp.get(chon).getSoNguoi()+"");
					lbl_loai.setBackground(Color.cyan);
					lst_ci.clear();
					lst_co.clear();
					lst_cid.clear();
					lst_cod.clear();
					getData();
					revalidate();
					repaint();
				}
			});
			
			list_lbl_lp.add(lbl_loai);
			panel.add(list_lbl_lp.get(i));
		}
		panel.setPreferredSize(new Dimension(325,panel.getComponentCount()*50+10));
		revalidate();
		repaint();
	}
	
	void setting() {
		pnl_chinh.setBackground(Color.WHITE);
		add(pnl_chinh, BorderLayout.CENTER);
		pnl_chinh.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 337, 621);
		pnl_chinh.add(scrollPane);

		panel.setBackground(Color.WHITE);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
		panel.setBorder(
				new TitledBorder(null, "Lo\u1EA1i Ph\u00F2ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setViewportView(panel);
		
		addlp();

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(357, 10, 172, 663);
		panel_1.setLayout(null);
		pnl_chinh.add(panel_1);

		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(Color.cyan);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBorder(null);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(10, 10, 152, 65);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				gia=0;
				chckbxNewCheckBox.setBackground(Color.white);
				lblNewLabel.setBackground(Color.cyan);
				chckbxTheoGi.setBackground(Color.white);
				autogen();
				pnl_checkinsom.add(btn_themci);
				pnl_checkoutmuon.add(btn_themco);
				txt_gia.setText(String.valueOf(new BigDecimal(list_lp.get(chon).getGiaPhong())));
				panel_2.remove(panel_3);
				panel_2.add(panel_3);
				revalidate();
				repaint();
			}
		});
		
		
		panel_1.add(lblNewLabel);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_4.setBounds(10, 85, 152, 65);
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		chckbxNewCheckBox.setBackground(Color.WHITE);

		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 24));
		chckbxNewCheckBox.setBounds(0, 0, 152, 65);
		chckbxNewCheckBox.setOpaque(true);
		chckbxNewCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxNewCheckBox.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				gia=1;
				chckbxNewCheckBox.setBackground(Color.cyan);
				lblNewLabel.setBackground(Color.white);
				chckbxTheoGi.setBackground(Color.white);
				autoGen2();
				pnl_checkinsom.add(btn_themci);
				pnl_checkoutmuon.add(btn_themco);
				txt_gia.setText(String.valueOf(new BigDecimal(list_lp.get(chon).getGiaQuaDem())));
				panel_2.remove(panel_3);
				panel_2.add(panel_3);
				revalidate();
				repaint();
			}
		});
		panel_4.add(chckbxNewCheckBox);

		JPanel panel_4_1 = new JPanel();
		panel_4_1.setLayout(null);
		panel_4_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_4_1.setBounds(10, 160, 152, 65);
		panel_1.add(panel_4_1);

		chckbxTheoGi.setBackground(Color.WHITE);
		chckbxTheoGi.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxTheoGi.setFont(new Font("Tahoma", Font.PLAIN, 24));
		chckbxTheoGi.setOpaque(true);
		chckbxTheoGi.setBounds(0, 0, 152, 65);
		chckbxTheoGi.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				chckbxNewCheckBox.setBackground(Color.white);
				lblNewLabel.setBackground(Color.white);
				chckbxTheoGi.setBackground(Color.cyan);
				gia=2;
				txt_gia.setText(String.valueOf(new BigDecimal(list_lp.get(chon).getGiaTheoGio())));
				panel_2.remove(panel_3);
//				autogengio();
				revalidate();
				repaint();
			}
		});
		panel_4_1.add(chckbxTheoGi);

		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(539, 10, 717, 663);
		pnl_chinh.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Giá: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(94, 10, 50, 35);
		panel_2.add(lblNewLabel_1);

		txt_gia = new JTextField("200000");
		txt_gia.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txt_gia.setBounds(159, 13, 292, 29);
		txt_gia.addCaretListener(new CaretListener() {
			
			public void caretUpdate(CaretEvent e) {
				// TODO Auto-generated method stub
				try {
					value=Double.parseDouble(txt_gia.getText());
					if(gia==0) {
						list_lp.get(chon).setGiaPhong(value);
						System.out.println("doi"+list_lp.get(chon).getGiaPhong());
					}
					else if(gia==1)list_lp.get(chon).setGiaQuaDem(value);
					else if(gia==2)list_lp.get(chon).setGiaTheoGio(value);;
				}catch(Exception ex) {
				}
				if (txt_gia.getText().matches(".\\d+|\\d+.{1}")) {
					return;
				}
				if (!txt_gia.getText().matches("\\d+|\\d+.\\d+")) {
					txt_gia.setEnabled(false);
					new Thread(new Runnable() {

						public void run() {
							// TODO Auto-generated method stub
							txt_gia.setText(String.valueOf(new BigDecimal(value)));
							txt_gia.setEnabled(true);
							txt_gia.requestFocus();
						}
					}).start();
					return;
				}
			}
		});
		txt_gia.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				if (txt_gia.getText().matches(".\\d+|\\d+.")) {
					new Thread(new Runnable() {

						public void run() {
							// TODO Auto-generated method stub
							txt_gia.setText(String.valueOf(new BigDecimal(value)));
							txt_gia.setEnabled(true);
						}
					}).start();
				}
			}
		});
		panel_2.add(txt_gia);
		txt_gia.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("VND");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1_1.setBounds(470, 10, 50, 35);
		panel_2.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Số người");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1_2.setBounds(52, 55, 101, 35);
		panel_2.add(lblNewLabel_1_2);

		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(10, 100, 697, 553);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		sc_gio.setBounds(panel_3.getBounds());
		
		pnl_gio.setBackground(Color.WHITE);
		sc_gio.setViewportView(pnl_gio);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 45, 337, 498);
		panel_3.add(scrollPane_1);

		pnl_checkinsom.setBackground(Color.WHITE);
		pnl_checkinsom.setLayout(new FlowLayout());
		pnl_checkinsom.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setViewportView(pnl_checkinsom);

		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(355, 45, 334, 498);
		panel_3.add(scrollPane_1_1);

		pnl_checkoutmuon.setBackground(Color.WHITE);
		pnl_checkoutmuon.setLayout(new FlowLayout());
		pnl_checkoutmuon.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1_1.setViewportView(pnl_checkoutmuon);

		JLabel lblNewLabel_2 = new JLabel("Phụ trội check in sớm");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(10, 10, 337, 30);
		panel_3.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Phụ trội check out muộn");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(357, 10, 330, 30);
		panel_3.add(lblNewLabel_2_1);

		JButton btnNewButton = new JButton("Lưu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(pnl_chinh, "Xác nhân lưu?")!=0){
					return;
				}
				List<PhuTroi> list=new ArrayList<PhuTroi>();
				for(PhuTroi a:lst_ci) {
					list.add(a);
				}
				for(PhuTroi a:lst_co) {
					list.add(a);
				}
				for(PhuTroi a:lst_cid) {
					list.add(a);
				}
				for(PhuTroi a:lst_cod) {
					list.add(a);
				}
//				for(PhuTroi a:lst_gio) {
//					list.add(a);
//				}
				list_lp.get(chon).setDsphutroi(list);
				System.out.println(list_lp.get(chon).getGiaPhong());
				dao_phutroi d=new dao_phutroi();
				d.xoa();
				ser_lp.save(list_lp);
				for(ModelLoaiPhong lp:list_lp) {
					System.out.println("chay bro");
					d.save(lp.getDsphutroi());
				}
				
				JOptionPane.showMessageDialog(pnl_chinh, "Lưu thành công");
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton.setBounds(593, 10, 101, 71);
		panel_2.add(btnNewButton);
		
		lbl_songuoi.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lbl_songuoi.setBounds(159, 55, 31, 35);
		panel_2.add(lbl_songuoi);
		
		JButton btnNewButton_1 = new JButton("\u02c4");
		btnNewButton_1.requestFocus();
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lbl_songuoi.setText(1+Integer.parseInt(lbl_songuoi.getText())+"");
				list_lp.get(chon).setSoNguoi(Integer.parseInt(lbl_songuoi.getText()));
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBounds(189, 63, 50, 21);
		panel_2.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("\u02c5");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(Integer.parseInt(lbl_songuoi.getText())>1)lbl_songuoi.setText(-1+Integer.parseInt(lbl_songuoi.getText())+"");
				list_lp.get(chon).setSoNguoi(Integer.parseInt(lbl_songuoi.getText()));
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1_1.setBackground(Color.WHITE);
		btnNewButton_1_1.setBounds(243, 63, 50, 21);
		panel_2.add(btnNewButton_1_1);
		
		JButton btnNewButton_2 = new JButton("Thêm loại phòng");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.setBounds(94, 641, 155, 32);
		btnNewButton_2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String ten=JOptionPane.showInputDialog("nhập tên phòng");
				if(ten.isBlank()) {
					JOptionPane.showMessageDialog(pnl_chinh, "Tên không hợp lệ");
					return;
				}
				List<PhuTroi> l=new ArrayList<PhuTroi>();
				List<ModelPhong> dsp=new ArrayList<ModelPhong>();
				list_lp.add(new ModelLoaiPhong(list_lp.get(list_lp.size()-1).getMaLoaiPhong()+1, ten, dsp, 0, 0, 0, "active", l, 1));
				addlp();
			}
		});
		pnl_chinh.add(btnNewButton_2);

	}

	void chon(int i, int x,boolean gia) {
		ModelLoaiPhong lp = list_lp.get(i);
		if (x == 0) {
			txt_gia.setText(String.valueOf(lp.getGiaPhong()));
		}
		else if (x == 1) {
			txt_gia.setText(String.valueOf(lp.getGiaQuaDem()));
		}
		else if (x == 2) {
			txt_gia.setText(String.valueOf(lp.getGiaTheoGio()));
		}
	}

	void getData() {
		
		final ModelLoaiPhong lp = list_lp.get(chon);
		for (PhuTroi x : lp.getDsphutroi()) {
			if (x.getLoai().equals("checkin")) {
				lst_ci.add(x);
			}
			else if (x.getLoai().equals("checkout")) {
				lst_co.add(x);
			}
			else if (x.getLoai().equals("checkindem")) {
				lst_cid.add(x);
			}
			else if (x.getLoai().equals("checkoutdem")) {
				lst_cod.add(x);
			}
			else if(x.getLoai().equals("gio")) {
				lst_gio.add(x);
			}
		}

		if(gia==0)autogen();
		if(gia==1)autoGen2();
		pnl_checkoutmuon.add(btn_themco);
		pnl_checkinsom.add(btn_themci);
	}
	
	void events() {
		btn_themci.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ex) {
				System.out.println("1 lam");
				// TODO Auto-generated method stub
				if(gia==0)lst_ci.add(new PhuTroi(lst_ci.size() + 100+chon*1000, 0, "checkin",
						new LoaiPhong(list_lp.get(chon).getMaLoaiPhong(), list_lp.get(chon).getTenLoai())));
				else if(gia==1)lst_cid.add(new PhuTroi(lst_cid.size() + 300+chon*1000, 0, "checkindem",
						new LoaiPhong(list_lp.get(chon).getMaLoaiPhong(), list_lp.get(chon).getTenLoai())));
				if(gia==0)autogen();
				if(gia==1)autoGen2();
				pnl_checkinsom.add(btn_themci);
				pnl_checkoutmuon.add(btn_themco);
				revalidate();
				repaint();
			}
		});
		btn_themco.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent es) {
				// TODO Auto-generated method stub
				System.out.println("2 lam");
				if(gia==0)lst_co.add(new PhuTroi(lst_co.size() + 200+chon*1000, 0, "checkout",
						new LoaiPhong(list_lp.get(chon).getMaLoaiPhong(), list_lp.get(chon).getTenLoai())));
				else if(gia==1)lst_cod.add(new PhuTroi(lst_cod.size() + 400+chon*1000, 0, "checkoutdem",
						new LoaiPhong(list_lp.get(chon).getMaLoaiPhong(), list_lp.get(chon).getTenLoai())));
				if(gia==0)autogen();
				if(gia==1)autoGen2();
				pnl_checkinsom.add(btn_themci);
				pnl_checkoutmuon.add(btn_themco);
				revalidate();
				repaint();
			}
		});
	}

	void autogen() {
		pnl_checkinsom.removeAll();
		pnl_checkoutmuon.removeAll();
		pnl_checkinsom.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
		pnl_checkinsom.setPreferredSize(new Dimension(330, 42 * lst_ci.size() + 42));
		pnl_checkoutmuon.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
		pnl_checkoutmuon.setPreferredSize(new Dimension(330, 42 * lst_co.size()));
		final dao_phutroi d=new dao_phutroi();
		for (int i = 0; i < lst_ci.size(); i++) {
			JPanel pnl_ci = new JPanel();
			pnl_ci.setBorder(BorderFactory.createEtchedBorder());
			pnl_ci.setPreferredSize(new Dimension(324, 40));
			pnl_ci.setBackground(Color.white);
			pnl_ci.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
			JLabel lbl = new JLabel(String.valueOf(i + 1+" giờ"));
			lbl.setFont(new Font("TAHOMA", Font.PLAIN, 17));
			pnl_ci.add(lbl);
			final JTextField txt_price = new JTextField();
			txt_price.setPreferredSize(new Dimension(200, 35));
			txt_price.setFont(new Font("TAHOMA", Font.PLAIN, 17));
			txt_price.setText(String.valueOf(new BigDecimal(lst_ci.get(i).getPhuTroi())));
			final int x = i;
			txt_price.addCaretListener(new CaretListener() {

				public void caretUpdate(CaretEvent exxx) {
					try {
						lst_ci.get(x).setPhuTroi(Double.valueOf(txt_price.getText()));
					}catch(Exception ex){
					}
					// TODO Auto-generated method stub
					if (txt_price.getText().matches(".\\d+|\\d+.{1}")) {
						return;
					}
					if (!txt_price.getText().matches("\\d+|\\d+.{1}\\d+")) {
						txt_price.setEnabled(false);
						new Thread(new Runnable() {

							public void run() {
								// TODO Auto-generated method stub
								txt_price.setText(String.valueOf(new BigDecimal(lst_ci.get(x).getPhuTroi())));
								txt_price.setEnabled(true);
								txt_price.requestFocus();
							}
						}).start();
					}
				}
			});
			txt_price.addFocusListener(new FocusAdapter() {
				public void focusLost(FocusEvent esadasdasd) {
					if (txt_price.getText().matches(".\\d+|\\d+.{1}")) {
						new Thread(new Runnable() {

							public void run() {
								// TODO Auto-generated method stub
								txt_price.setText(String.valueOf(new BigDecimal(lst_ci.get(x).getPhuTroi())));
								txt_price.setEnabled(true);
							}
						}).start();
					}
				}
			});
			pnl_ci.add(txt_price);
			JLabel lbl_t = new JLabel("VND   ");
			lbl_t.setFont(new Font("TAHOMA", Font.PLAIN, 17));
			pnl_ci.add(lbl_t);
			JLabel lbl_exit = new JLabel("X");
			lbl_exit.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent ek) {
					if (x != lst_ci.size() - 1)
						return;
					else {
						if (JOptionPane.showConfirmDialog(pnl_chinh, "Bạn có muốn xóa?") != 0) {
							return;
						}
					}
					lst_ci.remove(x);
					autogen();
					pnl_checkinsom.add(btn_themci);
					revalidate();
					repaint();
				}
			});
			pnl_ci.add(lbl_exit);
			pnl_checkinsom.add(pnl_ci);
		}
		
		for (int i = 0; i < lst_co.size(); i++) {
			JPanel pnl_co = new JPanel();
			pnl_co.setPreferredSize(new Dimension(324, 40));
			pnl_co.setBorder(BorderFactory.createEtchedBorder());
			pnl_co.setBackground(Color.white);
			pnl_co.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
			JLabel lbl = new JLabel(String.valueOf(i + 1+" giờ"));
			lbl.setFont(new Font("TAHOMA", Font.PLAIN, 17));
			pnl_co.add(lbl);
			final JTextField txt_price = new JTextField();
			txt_price.setPreferredSize(new Dimension(200, 35));
			txt_price.setFont(new Font("TAHOMA", Font.PLAIN, 17));
			txt_price.setText(String.valueOf(new BigDecimal(lst_co.get(i).getPhuTroi())));
			final int x = i;
			txt_price.addCaretListener(new CaretListener() {

				public void caretUpdate(CaretEvent eci) {
					// TODO Auto-generated method stub
					try{
						lst_co.get(x).setPhuTroi(Double.valueOf(txt_price.getText()));
					}catch(Exception ex){
					}
					if (txt_price.getText().matches(".\\d+|\\d+.{1}")) {
						return;
					}
					if (!txt_price.getText().matches("\\d+|\\d+.{1}\\d+")) {
						txt_price.setEnabled(false);
						new Thread(new Runnable() {

							public void run() {
								// TODO Auto-generated method stub
								txt_price.setText(String.valueOf(new BigDecimal(lst_co.get(x).getPhuTroi())));
								txt_price.setEnabled(true);
								txt_price.requestFocus();
							}
						}).start();
					}
				}
			});
			txt_price.addFocusListener(new FocusAdapter() {
				public void focusLost(FocusEvent eci2) {
					if (txt_price.getText().matches(".\\d+|\\d+.{1}")) {
						new Thread(new Runnable() {

							public void run() {
								// TODO Auto-generated method stub
								txt_price.setText(String.valueOf(new BigDecimal(lst_co.get(x).getPhuTroi())));
								txt_price.setEnabled(true);
							}
						}).start();
					}
				}
			});
			pnl_co.add(txt_price);
			JLabel lbl_t = new JLabel("VND   ");
			lbl_t.setFont(new Font("TAHOMA", Font.PLAIN, 17));
			pnl_co.add(lbl_t);
			JLabel lbl_exit = new JLabel("X");
			lbl_exit.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent eco) {
					if (x != lst_co.size() - 1)
						return;
					else {
						if (JOptionPane.showConfirmDialog(pnl_chinh, "Bạn có muốn xóa?") != 0) {
							return;
						}
					}
					lst_co.remove(x);
					autogen();
					pnl_checkoutmuon.add(btn_themco);
					revalidate();
					repaint();
				}
			});
			pnl_co.add(lbl_exit);
			pnl_checkoutmuon.add(pnl_co);
		}
		revalidate();
		repaint();
	}
	
	void autoGen2() {
		pnl_checkinsom.removeAll();
		pnl_checkoutmuon.removeAll();
		pnl_checkinsom.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
		pnl_checkinsom.setPreferredSize(new Dimension(330, 42 * lst_cid.size() + 42));
		pnl_checkoutmuon.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
		pnl_checkoutmuon.setPreferredSize(new Dimension(330, 42 * lst_cod.size()));
		final dao_phutroi d=new dao_phutroi();
		for (int i = 0; i < lst_cid.size(); i++) {
			JPanel pnl_ci = new JPanel();
			pnl_ci.setBorder(BorderFactory.createEtchedBorder());
			pnl_ci.setPreferredSize(new Dimension(324, 40));
			pnl_ci.setBackground(Color.white);
			pnl_ci.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
			JLabel lbl = new JLabel(String.valueOf(i + 1+" giờ"));
			lbl.setFont(new Font("TAHOMA", Font.PLAIN, 17));
			pnl_ci.add(lbl);
			final JTextField txt_price = new JTextField();
			txt_price.setPreferredSize(new Dimension(200, 35));
			txt_price.setFont(new Font("TAHOMA", Font.PLAIN, 17));
			txt_price.setText(String.valueOf(new BigDecimal(lst_cid.get(i).getPhuTroi())));
			final int x = i;
			txt_price.addCaretListener(new CaretListener() {

				public void caretUpdate(CaretEvent eco) {
					try {
						lst_cid.get(x).setPhuTroi(Double.valueOf(txt_price.getText()));
					}catch(Exception ex){
					}
					// TODO Auto-generated method stub
					if (txt_price.getText().matches(".\\d+|\\d+.{1}")) {
						return;
					}
					if (!txt_price.getText().matches("\\d+|\\d+.{1}\\d+")) {
						txt_price.setEnabled(false);
						new Thread(new Runnable() {

							public void run() {
								// TODO Auto-generated method stub
								txt_price.setText(String.valueOf(new BigDecimal(lst_cid.get(x).getPhuTroi())));
								txt_price.setEnabled(true);
								txt_price.requestFocus();
							}
						}).start();
					}
				}
			});
			txt_price.addFocusListener(new FocusAdapter() {
				public void focusLost(FocusEvent eco2) {
					if (txt_price.getText().matches(".\\d+|\\d+.{1}")) {
						new Thread(new Runnable() {

							public void run() {
								// TODO Auto-generated method stub
								txt_price.setText(String.valueOf(new BigDecimal(lst_cid.get(x).getPhuTroi())));
								txt_price.setEnabled(true);
							}
						}).start();
					}
				}
			});
			pnl_ci.add(txt_price);
			JLabel lbl_t = new JLabel("VND   ");
			lbl_t.setFont(new Font("TAHOMA", Font.PLAIN, 17));
			pnl_ci.add(lbl_t);
			JLabel lbl_exit = new JLabel("X");
			lbl_exit.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent ecid) {
					if (x != lst_cid.size() - 1)
						return;
					else {
						if (JOptionPane.showConfirmDialog(pnl_chinh, "Bạn có muốn xóa?") != 0) {
							return;
						}
					}
					lst_cid.remove(x);
					autoGen2();
					pnl_checkinsom.add(btn_themci);
					revalidate();
					repaint();
				}
			});
			pnl_ci.add(lbl_exit);
			pnl_checkinsom.add(pnl_ci);
		}
		
		for (int i = 0; i < lst_cod.size(); i++) {
			JPanel pnl_co = new JPanel();
			pnl_co.setPreferredSize(new Dimension(324, 40));
			pnl_co.setBorder(BorderFactory.createEtchedBorder());
			pnl_co.setBackground(Color.white);
			pnl_co.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
			JLabel lbl = new JLabel(String.valueOf(i + 1+" giờ"));
			lbl.setFont(new Font("TAHOMA", Font.PLAIN, 17));
			pnl_co.add(lbl);
			final JTextField txt_price = new JTextField();
			txt_price.setPreferredSize(new Dimension(200, 35));
			txt_price.setFont(new Font("TAHOMA", Font.PLAIN, 17));
			txt_price.setText(String.valueOf(new BigDecimal(lst_cod.get(i).getPhuTroi())));
			final int x = i;
			txt_price.addCaretListener(new CaretListener() {

				public void caretUpdate(CaretEvent ecid) {
					// TODO Auto-generated method stub
					try{
						lst_cod.get(x).setPhuTroi(Double.valueOf(txt_price.getText()));
					}catch(Exception ex){
					}
					if (txt_price.getText().matches(".\\d+|\\d+.{1}")) {
						return;
					}
					if (!txt_price.getText().matches("\\d+|\\d+.{1}\\d+")) {
						txt_price.setEnabled(false);
						new Thread(new Runnable() {

							public void run() {
								// TODO Auto-generated method stub
								txt_price.setText(String.valueOf(new BigDecimal(lst_cod.get(x).getPhuTroi())));
								txt_price.setEnabled(true);
								txt_price.requestFocus();
							}
						}).start();
					}
				}
			});
			txt_price.addFocusListener(new FocusAdapter() {
				public void focusLost(FocusEvent ecid2) {
					if (txt_price.getText().matches(".\\d+|\\d+.{1}")) {
						new Thread(new Runnable() {

							public void run() {
								// TODO Auto-generated method stub
								txt_price.setText(String.valueOf(new BigDecimal(lst_cod.get(x).getPhuTroi())));
								txt_price.setEnabled(true);
							}
						}).start();
					}
				}
			});
			pnl_co.add(txt_price);
			JLabel lbl_t = new JLabel("VND   ");
			lbl_t.setFont(new Font("TAHOMA", Font.PLAIN, 17));
			pnl_co.add(lbl_t);
			JLabel lbl_exit = new JLabel("X");
			lbl_exit.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent ecod) {
					if (x != lst_cod.size() - 1)
						return;
					else {
						if (JOptionPane.showConfirmDialog(pnl_chinh, "Bạn có muốn xóa?") != 0) {
							return;
						}
					}
					lst_cod.remove(x);
					autoGen2();
					pnl_checkoutmuon.add(btn_themco);
					revalidate();
					repaint();
				}
			});
			pnl_co.add(lbl_exit);
			pnl_checkoutmuon.add(pnl_co);
		}
		revalidate();
		repaint();
	}

	void autogengio() {
		panel_2.remove(panel_3);
		panel_2.add(sc_gio);
		pnl_gio.removeAll();
		for(int i=0;i<ks.getGio();i++) {
			try {
				lst_gio.get(i);
			}catch(Exception ssadasd) {
				lst_gio.add(new PhuTroi(i, 0, "gio"));
			}
		}
		int i=0;
		pnl_gio.setLayout(new FlowLayout(FlowLayout.LEFT,15,10));
		for(PhuTroi x:lst_gio) {
			JPanel pnl_cell=new JPanel();
			pnl_cell.setPreferredSize(new Dimension(300,40));
			JLabel lbl_name=new JLabel((i+1)+" Giờ");
			final int y=i;
			i++;
			final JTextField txt_g=new JTextField();
			txt_g.setPreferredSize(new Dimension(220,31));
			txt_g.addCaretListener(new CaretListener() {

				public void caretUpdate(CaretEvent eco) {
					try {
						lst_gio.get(y).setPhuTroi(Double.valueOf(txt_g.getText()));
					}catch(Exception ex){
					}
					// TODO Auto-generated method stub
					if (txt_g.getText().matches(".\\d+|\\d+.{1}")) {
						return;
					}
					if (!txt_g.getText().matches("\\d+|\\d+.{1}\\d+")) {
						txt_g.setEnabled(false);
						new Thread(new Runnable() {

							public void run() {
								// TODO Auto-generated method stub
								txt_g.setText(String.valueOf(new BigDecimal(lst_gio.get(y).getPhuTroi())));
								txt_g.setEnabled(true);
								txt_g.requestFocus();
							}
						}).start();
					}
				}
			});
			txt_g.addFocusListener(new FocusAdapter() {
				public void focusLost(FocusEvent ecid2) {
					if (txt_g.getText().matches(".\\d+|\\d+.{1}")) {
						new Thread(new Runnable() {

							public void run() {
								// TODO Auto-generated method stub
								txt_g.setText(String.valueOf(new BigDecimal(lst_cod.get(y).getPhuTroi())));
								txt_g.setEnabled(true);
							}
						}).start();
					}
				}
			});
			pnl_cell.add(lbl_name);
			pnl_cell.add(txt_g);
			pnl_gio.add(pnl_cell);
		}
		pnl_gio.setPreferredSize(new Dimension(400,400));
	}
	
	public static void main(String[] args) {
		new Thread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				new VLoaiPhong().setVisible(true);
			}
		}).start();
	}
}
