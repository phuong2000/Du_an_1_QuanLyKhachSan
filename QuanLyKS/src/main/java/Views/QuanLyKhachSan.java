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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import Entities.LoaiPhong;
import Services.KhachSan_services;
import Services.Phong_services;
import Services.Tang_services;
import Services.loaiPhong_services;
import ViewModels.ModelTang;
import ViewModels.ModelLoaiPhong;
import ViewModels.ModelPhong;

public class QuanLyKhachSan extends JFrame {

	KhachSan_services ser_ks;
	Tang_services ser_t;
	Phong_services ser_p;
	loaiPhong_services ser_lp;
	List<JPanel> comp_list;
	List<List<JLabel>> List_comp_phong;
	List<ModelTang> list_tang;
	List<ModelTang> List_ChooseFloor;
	List<ModelPhong> List_Phong;
	List<ModelLoaiPhong> list_lp;

	JPanel pnl_addBtn = new JPanel();
	JPanel pnl_box = new JPanel();
	JPanel pnl_chinh = new JPanel();
	JComboBox<String> cbx_loaiPhong;
	public QuanLyKhachSan() {
		ser_lp=new loaiPhong_services();
		list_lp=ser_lp.getLp();
		List_comp_phong = new ArrayList<List<JLabel>>();
		List_Phong = new ArrayList<ModelPhong>();
		List_ChooseFloor = new ArrayList<ModelTang>();
		comp_list = new ArrayList<JPanel>();
		ser_ks = new KhachSan_services();
		list_tang = ser_ks.getKs().getDSTang();
		ser_p = new Phong_services();
		ser_t = new Tang_services();
		setting();
		Map();
		setColor();
	}
	
	public void cbx_setUp() {
		String[]a=new String[list_lp.size()];
		for(int i=0;i<list_lp.size();i++) {
			a[i]=list_lp.get(i).getTenLoai();
		}
		cbx_loaiPhong=new JComboBox<String>(a);
	}

	public void setting() {
		pnl_chinh.setBackground(Color.white);
		setSize(1280, 720);
		add(pnl_chinh);
		pnl_chinh.setLayout(new BorderLayout());

		JPanel pnl_btn = new JPanel();
		pnl_btn.setPreferredSize(new Dimension(1280, 40));
		pnl_btn.setBackground(Color.white);
		pnl_btn.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		pnl_chinh.add(pnl_btn, BorderLayout.NORTH);

		JPanel pnl_aside = new JPanel();
		pnl_aside.setBorder(BorderFactory.createLineBorder(Color.gray));
		pnl_aside.setPreferredSize(new Dimension(250, 600));
		pnl_aside.setBackground(Color.white);
		pnl_aside.add(new ThongTinKhachSan());
		pnl_aside.add(new JLabel("Loại phòng"));
		cbx_setUp();
		pnl_aside.add(cbx_loaiPhong);
		JButton btn_lp=new JButton("đổi loại phòng");
		btn_lp.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int chon=cbx_loaiPhong.getSelectedIndex();
				if(List_Phong.size()>0) {
					for(int i=0;i<List_Phong.size();i++) {
						List_Phong.get(i).setLoaiphong(new ModelLoaiPhong(list_lp.get(chon).getMaLoaiPhong(),list_lp.get(chon).getTenLoai()));
						ser_p.themsua(List_Phong);
					}
					list_tang = ser_ks.getKs().getDSTang();
					clearAll();
					Map();
					setColor();
					pnl_addBtn.removeAll();
					revalidate();
					repaint();
				}
			}
		});
		pnl_aside.add(btn_lp);
		pnl_chinh.add(pnl_aside, BorderLayout.WEST);

		JScrollPane scroll = new JScrollPane(pnl_box, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setMinimumSize(new Dimension(1000, 500));
		scroll.setPreferredSize(new Dimension(1000, 500));
		pnl_chinh.add(BorderLayout.EAST, scroll);


		pnl_addBtn.setBackground(Color.white);
		pnl_addBtn.setPreferredSize(new Dimension(1250, 30));
		pnl_addBtn.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 0));
		pnl_btn.add(pnl_addBtn);
	}

	public void Map() {
		//clearAll();
		pnl_box.removeAll();
		pnl_box.setLayout(new BoxLayout(pnl_box, BoxLayout.Y_AXIS));
		pnl_box.setBackground(Color.white);
		for (int i = 0; i < list_tang.size(); i++) {
			int chieudai = (int) (list_tang.get(i).getDSPhong().size() + 1) / 12 + 1;
			final JPanel t = new JPanel();
			final int tt = i;
			comp_list.add(t);
			comp_list.get(i).setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
			comp_list.get(i).setPreferredSize(new Dimension(980, chieudai * 65));//
			comp_list.get(i).setBorder(BorderFactory.createEtchedBorder());
			comp_list.get(i).setFocusable(true);
			comp_list.get(i).addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					pnl_addBtn.removeAll();
					list_tang = ser_ks.getKs().getDSTang();
					comp_list.clear();
					List_comp_phong.clear();
					Map();
					List_Phong.clear();
					for (int i = 0; i < List_ChooseFloor.size(); i++) {
						if (List_ChooseFloor.get(i).getMaTang() == list_tang.get(tt).getMaTang()) {
							List_ChooseFloor.remove(i);
							setColor();
							if(List_ChooseFloor.isEmpty()) {
								pnl_addBtn.removeAll();
								revalidate();
								repaint();
							}
							return;
						} else if (i == List_ChooseFloor.size() - 1) {
							List_ChooseFloor.add(list_tang.get(tt));
							if (pnl_addBtn.getComponentCount() == 0)
								addBtnChon();
							break;
						}
					}
					if (List_ChooseFloor.isEmpty()&&tt<list_tang.size()) {
						List_ChooseFloor.add(list_tang.get(tt));
						if (pnl_addBtn.getComponentCount() == 0)
							addBtnChon();
					}
					if(List_ChooseFloor.isEmpty()) {
						pnl_addBtn.removeAll();
						revalidate();
						repaint();
					}
					setColor();
					
				}

				public void mouseEntered(MouseEvent e) {
					comp_list.get(tt).setBorder(BorderFactory.createLineBorder(Color.gray, 2));
				}

				public void mouseExited(MouseEvent e) {
					comp_list.get(tt).setBorder(BorderFactory.createEtchedBorder());
				}

			});

			final JLabel lbl_t = new JLabel(String.valueOf(i + 1));
			lbl_t.setPreferredSize(new Dimension(50, 65 * chieudai));//
			lbl_t.setVerticalAlignment(SwingConstants.CENTER);
			lbl_t.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_t.setFont(new Font("TAHOMA", Font.BOLD, 28));
			comp_list.get(i).add(lbl_t);
			JPanel pnl_tang = new JPanel();
			int gap = 0;
			if (chieudai >= 2)
				gap = 5;
			pnl_tang.setPreferredSize(new Dimension(900, 55 * chieudai));
			pnl_tang.setLayout(new FlowLayout(FlowLayout.LEFT, 5, gap));
			pnl_tang.setOpaque(false);
			List<JLabel> List_p = new ArrayList<JLabel>();
			for (int y = 0; y < list_tang.get(i).getDSPhong().size(); y++) {
				final JLabel p = new JLabel();
				final int soPhong = y;
				p.setPreferredSize(new Dimension(70, 50));
				p.setBorder(BorderFactory.createEtchedBorder());
				String loaiPhong;
				try {
					loaiPhong=list_tang.get(i).getDSPhong().get(y).getLoaiphong().getTenLoai();
				}catch(NullPointerException e) {
					loaiPhong="chưa cài đặt";
				}
				p.setText(String.valueOf("<html><center>  " + list_tang.get(i).getDSPhong().get(y).getMaPhong()
						+ "<br />"+loaiPhong+"</center></html>"));
				p.setOpaque(true);
				p.addMouseListener(new MouseAdapter() {
					public void mouseEntered(MouseEvent e) {
						p.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
					}

					public void mouseExited(MouseEvent e) {
						p.setBorder(BorderFactory.createEtchedBorder());
					}

					public void mouseClicked(MouseEvent e) {
						list_tang = ser_ks.getKs().getDSTang();
						comp_list.clear();
						List_comp_phong.clear();
						Map();
						List_ChooseFloor.clear();
						for (int i = 0; i < List_Phong.size(); i++) {
							if (list_tang.get(tt).getDSPhong().get(soPhong).getMaPhong() == List_Phong.get(i).getMaPhong()) {
								List_Phong.remove(i);
								setColor();
								return;
							} else if (i == List_Phong.size() - 1) {
								List_Phong.add(list_tang.get(tt).getDSPhong().get(soPhong));
								if (pnl_addBtn.getComponentCount() == 0)
									addBtnChon();
								break;
							}
						}
						if (List_Phong.isEmpty()&&soPhong<list_tang.get(tt).getDSPhong().size()) {
							List_Phong.add(list_tang.get(tt).getDSPhong().get(soPhong));
							if (pnl_addBtn.getComponentCount() == 0)
								addBtnChon();
							if(List_Phong.isEmpty()) {
								pnl_addBtn.removeAll();
								revalidate();
								repaint();
							}
						}
						setColor();
					}
				});
				List_p.add(p);
				pnl_tang.add(List_p.get(y));
			}
			List_comp_phong.add(List_p);
			final JLabel plus = new JLabel();
			plus.setPreferredSize(new Dimension(30, 30));
			plus.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/image/plus.png")).getImage()
					.getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
			plus.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					List_Phong.clear();
					List_ChooseFloor.clear();
					pnl_addBtn.removeAll();
					revalidate();
					repaint();
					list_tang.get(tt).getDSPhong().add(new ModelPhong(
							tt * 100 + 100 + list_tang.get(tt).getDSPhong().size() + 1, list_tang.get(tt)));
					comp_list.clear();
					List_comp_phong.clear();
					Map();
					setColor();
					if (pnl_addBtn.getComponentCount() == 0)
						addBtnThem();
				}

				public void mouseEntered(MouseEvent e) {
					plus.setPreferredSize(new Dimension(35, 35));
					plus.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/image/plus.png")).getImage()
							.getScaledInstance(35, 35, Image.SCALE_DEFAULT)));
				}

				public void mouseExited(MouseEvent e) {
					plus.setPreferredSize(new Dimension(30, 30));
					plus.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/image/plus.png")).getImage()
							.getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
				}
			});
			pnl_tang.add(plus);
			comp_list.get(i).add(pnl_tang);
			pnl_box.add(comp_list.get(i));
		}
		final JLabel addFloor = new JLabel();
		addFloor.setPreferredSize(new Dimension(50, 50));
		addFloor.setBackground(Color.white);
		addFloor.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/image/plus.png")).getImage()
				.getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		addFloor.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				List_Phong.clear();
				List_ChooseFloor.clear();
				pnl_addBtn.removeAll();
				revalidate();
				repaint();
				List<ModelPhong> list = new ArrayList<ModelPhong>();
				list_tang.add(new ModelTang(list_tang.size() + 1, ser_ks.getKs(), list));
				comp_list.clear();
				List_comp_phong.clear();
				Map();
				setColor();
				if (pnl_addBtn.getComponentCount() == 0)
					addBtnThem();
			}

			public void mouseEntered(MouseEvent e) {
				addFloor.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/image/plus.png")).getImage()
						.getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
			}

			public void mouseExited(MouseEvent e) {
				addFloor.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("/image/plus.png")).getImage()
						.getScaledInstance(35, 35, Image.SCALE_DEFAULT)));
			}
		});
		pnl_box.add(addFloor);
		revalidate();
		repaint();
	}

	private void setColor() {
		for (int i = 0; i < list_tang.size(); i++) {
			comp_list.get(i).setBackground(Color.white);
			for (int y = 0; y < List_ChooseFloor.size(); y++) {
				if (List_ChooseFloor.get(y).getMaTang() == list_tang.get(i).getMaTang()) {
					comp_list.get(i).setBackground(Color.CYAN);
				}
			}
		}
		for (int i = 0; i < list_tang.size(); i++) {
			for (int p = 0; p < list_tang.get(i).getDSPhong().size(); p++) {
				List_comp_phong.get(i).get(p).setBackground(Color.white);
				for (ModelPhong a : List_Phong) {
					if (a.getMaPhong() == list_tang.get(i).getDSPhong().get(p).getMaPhong()) {
						List_comp_phong.get(i).get(p).setBackground(Color.cyan);
						break;
					}
				}
			}
		}

	}

	private void addBtnThem() {
		pnl_addBtn.removeAll();
		JButton btn_cancel = new JButton("Hủy");
		pnl_addBtn.add(btn_cancel);
		btn_cancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				list_tang = ser_ks.getKs().getDSTang();
				clearAll();
				Map();
				setColor();
				pnl_addBtn.removeAll();
				revalidate();
				repaint();
			}
		});
		JButton btn_xacnhan = new JButton("Xác nhận");
		pnl_addBtn.add(btn_xacnhan);
		btn_xacnhan.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (JOptionPane.showConfirmDialog(pnl_chinh, "Bạn có muốn thay đổi mô hình khách sạn?", "Thông báo",
						1) != 0) {
					return;
				}
				ser_t.themsua(list_tang);
				for (ModelTang tang : list_tang) {
					ser_p.themsua(tang.getDSPhong());
				}
				list_tang = ser_ks.getKs().getDSTang();
				clearAll();
				Map();
				setColor();
				pnl_addBtn.removeAll();
				revalidate();
				repaint();
				JOptionPane.showMessageDialog(pnl_chinh, "Sửa cấu trúc khách sạn thành công!!");
			}
		});
		revalidate();
		repaint();
	}

	private void addBtnChon() {
		pnl_addBtn.removeAll();
		JButton btn_cancel = new JButton("Hủy");
		btn_cancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				list_tang = ser_ks.getKs().getDSTang();
				clearAll();
				Map();
				setColor();
				pnl_addBtn.removeAll();
				revalidate();
				repaint();
			}
		});
		JButton btn_xoa = new JButton("Xóa");
		btn_xoa.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (JOptionPane.showConfirmDialog(pnl_chinh, "Bạn có muốn xóa?", "Thông báo",
						1) != 0) {
					return;
				}
				if(!List_ChooseFloor.isEmpty())ser_t.xoa(pnl_chinh, List_ChooseFloor);
				else if(!List_Phong.isEmpty())ser_p.xoa(pnl_chinh,List_Phong);
				list_tang = ser_ks.getKs().getDSTang();
				clearAll();
				Map();
				setColor();
				pnl_addBtn.removeAll();
				revalidate();
				repaint();
			}
		});
		pnl_addBtn.add(btn_xoa);
		pnl_addBtn.add(btn_cancel);
		revalidate();
		repaint();
	}

	private void clearAll() {
		comp_list.clear();
		List_ChooseFloor.clear();
		List_comp_phong.clear();
		List_Phong.clear();
	}

	public static void main(String[] args) {
		new Thread(new Runnable() {
			public void run() {
				new QuanLyKhachSan().setVisible(true);
			}
		}).start();
	}

}
