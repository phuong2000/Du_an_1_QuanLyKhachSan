package Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Services.KhachSan_services;
import ViewModels.ModelKhachSan;
import javax.swing.JComboBox;
import javax.swing.JSeparator;

public class ThongTinKhachSan extends JPanel {

	JTextField txt_name = new JTextField();
	JTextField txt_email = new JTextField();
	JTextField txt_sdt = new JTextField();
	JTextField txt_diachi = new JTextField();
	JButton btn_conf = new JButton("Xác nhận");
	JComboBox cbx_ngayout = new JComboBox();
	JComboBox cbx_ngayin = new JComboBox();
	JComboBox cbx_demin = new JComboBox();
	JComboBox cbx_demout = new JComboBox();
	JLabel lbl_songuoi = new JLabel();

	KhachSan_services ser;

	public ThongTinKhachSan() {
		// TODO Auto-generated constructor stub
		ser = new KhachSan_services();
		setting();
		event();
		getInfo();
	}

	private void setting() {
		setPreferredSize(new Dimension(240, 540));
		setBackground(Color.white);
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lbl_til = new JLabel("Thông tin khách sạn");
		lbl_til.setFont(new Font("TAHOMA", Font.PLAIN, 20));
		lbl_til.setPreferredSize(new Dimension(460, 40));
		lbl_til.setHorizontalAlignment(SwingConstants.CENTER);
		add(lbl_til);

		JPanel pnl_form = new JPanel();
		pnl_form.setPreferredSize(new Dimension(240, 440));
		pnl_form.setBackground(Color.white);
		add(pnl_form);
		add(btn_conf);

		JLabel lbl_name = new JLabel("Tên khách sạn:      ");
		lbl_name.setBounds(19, 10, 126, 17);
		lbl_name.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLabel lbl_Email = new JLabel("Email khách sạn:    ");
		lbl_Email.setBounds(19, 77, 126, 17);
		lbl_Email.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLabel lbl_SDT = new JLabel("Số điện thoại:         ");
		lbl_SDT.setBounds(19, 144, 131, 17);
		lbl_SDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLabel lbl_diachi = new JLabel("Địa chỉ khách sạn: ");
		lbl_diachi.setBounds(19, 211, 121, 17);
		lbl_diachi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txt_name.setBounds(19, 37, 200, 30);

		txt_name.setPreferredSize(new Dimension(200, 30));
		txt_email.setBounds(19, 104, 200, 30);
		txt_email.setPreferredSize(new Dimension(200, 30));
		txt_sdt.setBounds(19, 171, 200, 30);
		txt_sdt.setPreferredSize(new Dimension(200, 30));
		txt_diachi.setBounds(19, 238, 200, 30);
		txt_diachi.setPreferredSize(new Dimension(200, 30));
		pnl_form.setLayout(null);

		pnl_form.add(lbl_name);
		pnl_form.add(txt_name);
		pnl_form.add(lbl_Email);
		pnl_form.add(txt_email);
		pnl_form.add(lbl_SDT);
		pnl_form.add(txt_sdt);
		pnl_form.add(lbl_diachi);
		pnl_form.add(txt_diachi);

		JLabel lbl_diachi_1_2 = new JLabel("Giờ check in ngày:");
		lbl_diachi_1_2.setBounds(19, 280, 117, 21);
		lbl_diachi_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_form.add(lbl_diachi_1_2);

		cbx_ngayin.setBounds(146, 278, 47, 21);
		pnl_form.add(cbx_ngayin);
		Integer[] a = new Integer[24];
		for (int i = 0; i < a.length; i++) {
			a[i] = i;
		}
		DefaultComboBoxModel<Integer> model_ngayin = new DefaultComboBoxModel<Integer>(a);
		cbx_ngayin.setModel(model_ngayin);
		cbx_ngayin.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				int x = 0;
				Integer[] a = new Integer[6];
				for (int i = 0; i < a.length; i++) {
					a[i] = Integer.parseInt(String.valueOf(cbx_ngayin.getSelectedItem())) - i;
					if (a[i] < 0) {
						a[i] = 23 - x;
						x++;
					}
				}
				cbx_ngayout.setEnabled(true);
				DefaultComboBoxModel<Integer> model_ngayout = new DefaultComboBoxModel<Integer>(a);
				cbx_ngayout.setModel(model_ngayout);
			}
		});

		JLabel lbl_diachi_1_1 = new JLabel("Giờ");
		lbl_diachi_1_1.setBounds(203, 280, 21, 17);
		lbl_diachi_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_form.add(lbl_diachi_1_1);

		JLabel lbl_diachi_1 = new JLabel("Giờ check out ngày:");
		lbl_diachi_1.setBounds(19, 310, 126, 21);
		lbl_diachi_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_form.add(lbl_diachi_1);

		cbx_ngayout.setBounds(146, 310, 47, 21);
		pnl_form.add(cbx_ngayout);

		JLabel lbl_diachi_1_1_1 = new JLabel("Giờ");
		lbl_diachi_1_1_1.setBounds(203, 311, 21, 17);
		lbl_diachi_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_form.add(lbl_diachi_1_1_1);

		JLabel lbl_diachi_1_2_1 = new JLabel("Giờ check in đêm:");
		lbl_diachi_1_2_1.setBounds(19, 340, 117, 21);
		lbl_diachi_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_form.add(lbl_diachi_1_2_1);

		Integer[] c = new Integer[7];
		for (int i = 18; i < c.length; i++) {
			c[i] = i;
		}
		DefaultComboBoxModel<Integer> model_demin = new DefaultComboBoxModel<Integer>(c);
		cbx_demin.setModel(new DefaultComboBoxModel(new String[] { "18", "19", "20", "21", "22", "23", "00" }));
		cbx_demin.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				Integer[] d = new Integer[5];
				cbx_demout.setEnabled(true);
				for (int i = 5; i < d.length; i++) {
					d[i] = i;
				}
				DefaultComboBoxModel<Integer> model_demout = new DefaultComboBoxModel<Integer>(d);
				cbx_demout.setModel(model_demout);
			}
		});
		Integer[] d = new Integer[5];
		cbx_demout.setEnabled(true);
		for (int i = 5; i < d.length; i++) {
			d[i] = i;
		}
		DefaultComboBoxModel<Integer> model_demout = new DefaultComboBoxModel<Integer>(d);
		cbx_demout.setModel(new DefaultComboBoxModel(new String[] { "5", "6", "7", "8", "9" }));
		cbx_demin.setBounds(146, 340, 47, 21);
		pnl_form.add(cbx_demin);

		JLabel lbl_diachi_1_1_1_1 = new JLabel("Giờ");
		lbl_diachi_1_1_1_1.setBounds(203, 342, 21, 17);
		lbl_diachi_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnl_form.add(lbl_diachi_1_1_1_1);

		JLabel lbl_diachi_1_2_1_1 = new JLabel("Giờ check out đêm:");
		lbl_diachi_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_diachi_1_2_1_1.setBounds(19, 370, 126, 21);
		pnl_form.add(lbl_diachi_1_2_1_1);

		cbx_demout.setBounds(146, 370, 47, 21);
		pnl_form.add(cbx_demout);

		JLabel lbl_diachi_1_1_1_1_1 = new JLabel("Giờ");
		lbl_diachi_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_diachi_1_1_1_1_1.setBounds(203, 369, 21, 17);
		pnl_form.add(lbl_diachi_1_1_1_1_1);

		JLabel lbl_diachi_1_2_1_1_1 = new JLabel("Giờ tối đa:");
		lbl_diachi_1_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_diachi_1_2_1_1_1.setBounds(19, 401, 66, 21);
		pnl_form.add(lbl_diachi_1_2_1_1_1);

		lbl_songuoi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_songuoi.setBounds(89, 396, 21, 30);
		pnl_form.add(lbl_songuoi);

		JButton btnNewButton_1 = new JButton("˄");
		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lbl_songuoi.setText(1 + Integer.parseInt(lbl_songuoi.getText()) + "");
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBounds(108, 401, 50, 21);
		pnl_form.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("˅");
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_1_1.setBackground(Color.WHITE);
		btnNewButton_1_1.setBounds(169, 401, 50, 21);
		pnl_form.add(btnNewButton_1_1);
		btnNewButton_1_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (Integer.parseInt(lbl_songuoi.getText()) > 1)
					lbl_songuoi.setText(-1 + Integer.parseInt(lbl_songuoi.getText()) + "");
			}
		});

		JSeparator separator = new JSeparator();
		add(separator);
	}

	private void event() {
		btn_conf.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String rg = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-"
						+ "\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:"
						+ "[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)"
						+ "\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f"
						+ "\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
				if (!txt_email.getText().matches(rg)&&!txt_email.getText().isBlank()) {
					JOptionPane.showMessageDialog(txt_email, "Email không đúng định dạng");
					return;
				}
				if (!txt_sdt.getText().matches("0\\d{9,10}")&&!txt_sdt.getText().isBlank()) {
					JOptionPane.showMessageDialog(txt_email, "Số điện thoại không hợp lệ");
					return;
				}

				int chon = JOptionPane.showConfirmDialog(txt_name, "Xác nhận thông tin khách sạn?", "Thông báo", 2);
				if (chon == 0) {
					ser.themsua(new ModelKhachSan(1, txt_name.getText(), txt_email.getText(), txt_sdt.getText(),
							txt_diachi.getText(), 100 * Integer.parseInt(String.valueOf(cbx_ngayin.getSelectedItem())),
							100 * Integer.parseInt(String.valueOf(cbx_ngayout.getSelectedItem())),
							Integer.parseInt(String.valueOf(cbx_demin.getSelectedItem())) * 100,
							Integer.parseInt(String.valueOf(cbx_demout.getSelectedItem())) * 100,
							Integer.parseInt(lbl_songuoi.getText())));
					JOptionPane.showMessageDialog(txt_name, "Sửa thông tin thành công");
				}
			}
		});
	}

	private void getInfo() {
		ModelKhachSan ks = ser.getKs();
		if (ks == null)
			return;
		txt_diachi.setText(ks.getDiaChi());
		txt_email.setText(ks.getEmail());
		txt_name.setText(ks.getTenKhachSan());
		txt_sdt.setText(ks.getSdt());
		lbl_songuoi.setText(ks.getGio() + "");
		int x = 0;
		Integer[] a = new Integer[6];
		for (int i = 0; i < a.length; i++) {
			a[i] = Integer.parseInt(String.valueOf(cbx_ngayin.getSelectedItem())) - i;
			if (a[i] < 0) {
				a[i] = 23 - x;
				x++;
			}
		}
		DefaultComboBoxModel<Integer> model_ngayout = new DefaultComboBoxModel<Integer>(a);
		cbx_ngayout.setModel(model_ngayout);
		cbx_ngayin.setSelectedIndex((ks.getGioCheckIn() / 100));
		cbx_ngayout.setSelectedItem(ks.getGioCheckout() / 100);
		cbx_demin.setSelectedItem(String.valueOf(ks.getGioCheckInDem() / 100));
		cbx_demout.setSelectedItem(String.valueOf(ks.getGioCheckOutDem() / 100));
	}
}
