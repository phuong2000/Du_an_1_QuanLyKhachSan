package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.NhanVienModel;
import Services.NhanVienService;
import utils.Utilities;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class DangNhap extends JFrame {
	private NhanVienModel _userAreUsing;
	private static String _passwordStatus = "hide";
	private static ArrayList<NhanVienModel> _listAccount;
	private JPanel contentPane;
	private JPasswordField txt_matKhau;
	private static DangNhap frame;
	private Timer _timer;
	int k=1;
	private JLabel lbl_hotelImage;
	private JFrame quenmk;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		

			public void run() {
				try {
					frame = new DangNhap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public DangNhap() throws IOException, ClassNotFoundException {
		_listAccount = new ArrayList<NhanVienModel>();
		File file = new File(utils.File.fileGhiNhoMatKhau());
		if (!file.exists()) {
			file.createNewFile();
		}

		FileInputStream fileInputStream = new FileInputStream(file);
		if (fileInputStream.available() > 0) {
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

			while (fileInputStream.available() > 0) {
				NhanVienModel nvm = (NhanVienModel) objectInputStream.readObject();
				_listAccount.add(nvm);
			}
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1108, 601);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(Color.WHITE);
		contentPane_1.setBounds(0, 0, 1097, 562);
		contentPane.add(contentPane_1);
		
		final JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (_passwordStatus.equals("hide")) {
					txt_matKhau.setEchoChar('\u0000');
					lblNewLabel_1.setIcon(new ImageIcon(
							Toolkit.getDefaultToolkit().createImage(DangNhap.class.getResource("showPw45.png"))));
					_passwordStatus = "show";
				} else {
					txt_matKhau.setEchoChar('*');
					lblNewLabel_1.setIcon(new ImageIcon(
							Toolkit.getDefaultToolkit().createImage(DangNhap.class.getResource("hidePw45.png"))));
					_passwordStatus = "hide";
				}
			}
		});
		lblNewLabel_1.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(DangNhap.class.getResource("hidePw45.png"))));
		lblNewLabel_1.setBounds(1029, 285, 45, 37);
		contentPane_1.add(lblNewLabel_1);
		
		final JComboBox cbx_taiKhoan = new JComboBox();
		cbx_taiKhoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					txt_matKhau.setText(_listAccount.get(cbx_taiKhoan.getSelectedIndex()).getPassword());
				} catch (Exception e2) {
					_passwordStatus = "hide";
					txt_matKhau.setForeground(Color.black);
					txt_matKhau.setText(null);
					lblNewLabel_1.setIcon(new ImageIcon(
							Toolkit.getDefaultToolkit().createImage(DangNhap.class.getResource("hidePw45.png"))));
					txt_matKhau.setEchoChar('*');
				}
			}
		});
		cbx_taiKhoan.setEditable(true);
		cbx_taiKhoan.setBackground(new Color(255, 255, 255));;
		cbx_taiKhoan.setBounds(827, 231, 252, 39);
		contentPane_1.add(cbx_taiKhoan);
		
		String arrayTK[] = new String[_listAccount.size()];
		int index = 0;

		for (NhanVienModel nhanVienModel : _listAccount) {
			arrayTK[index] = nhanVienModel.getUserName();
			index++;
		}
		cbx_taiKhoan.setModel(new DefaultComboBoxModel(arrayTK));
		
		txt_matKhau = new JPasswordField();
//		txt_matKhau.setBorder(null);
		txt_matKhau.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txt_matKhau.getText().equals("Mật khẩu")) {
					_passwordStatus = "hide";
					txt_matKhau.setForeground(Color.black);
					txt_matKhau.setText(null);
					lblNewLabel_1.setIcon(new ImageIcon(
							Toolkit.getDefaultToolkit().createImage(DangNhap.class.getResource("hidePw45.png"))));
					txt_matKhau.setEchoChar('*');
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (txt_matKhau.getText().equals("")) {
					txt_matKhau.setEchoChar('\u0000');
					txt_matKhau.setText("Mật khẩu");
					txt_matKhau.setForeground(new Color(153, 153, 153));
				}
			}
		});
		txt_matKhau.setEchoChar('\u0000');
		txt_matKhau.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txt_matKhau.setColumns(10);
		txt_matKhau.setText("Mật khẩu");
		txt_matKhau.setForeground(new Color(153, 153, 153));
		txt_matKhau.setBounds(827, 281, 252, 45);
		contentPane_1.add(txt_matKhau);
		
		
		final JCheckBox cbxNhoMatKhau = new JCheckBox("Nhớ Mật Khẩu");
		cbxNhoMatKhau.setOpaque(false);
		cbxNhoMatKhau.setBorderPainted(false);
		cbxNhoMatKhau.setBackground(new Color(220, 220, 220));
		cbxNhoMatKhau.setBounds(827, 338, 112, 23);
		contentPane_1.add(cbxNhoMatKhau);
		
		if (!_listAccount.isEmpty()) {
			txt_matKhau.setText(_listAccount.get(0).getPassword());
			txt_matKhau.setEchoChar('*');
			cbxNhoMatKhau.setSelected(true);
		}
		
		JButton btn_login = new JButton("Đăng nhập");
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NhanVienService nvs = new NhanVienService();
				nvs.updateListNhanVienModel_active("Hoat Dong");
				List<NhanVienModel> list = new ArrayList<NhanVienModel>();
					list = nvs.getListNhanVienModel();

				String taiKhoan = cbx_taiKhoan.getSelectedItem().toString();
				String matKhau = txt_matKhau.getText().trim();
				String check = "false";
				boolean check1 = false;
				NhanVienModel userAreUsing;

				for (NhanVienModel nhanVienModel : list) {
					if (nhanVienModel.getUserName().equals(taiKhoan)
							&& nhanVienModel.getPassword().equals(Utilities.hashingPassword(matKhau))) {
						userAreUsing = nhanVienModel;
						if (cbxNhoMatKhau.isSelected()) {
							if (_listAccount.isEmpty()) {
								NhanVienModel nvm = new NhanVienModel(nhanVienModel.getMaNV(), nhanVienModel.getTenNV(), nhanVienModel.getSdt(), nhanVienModel.getGioiTinh(), nhanVienModel.getEmail(), nhanVienModel.getChucVuModel(), nhanVienModel.getTrangThai(), nhanVienModel.getCmnd(), nhanVienModel.getUserName(), matKhau, nhanVienModel.getNgaySinh());
								_listAccount.add(nvm);
								File file = new File(utils.File.fileGhiNhoMatKhau());
								try {
									if (!file.exists()) {
										file.createNewFile();
									}
									FileOutputStream fileOutputStream = new FileOutputStream(file);
									ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

									for (NhanVienModel nhanVienModel11 : _listAccount) {
										objectOutputStream.writeObject(nhanVienModel11);
									}

									objectOutputStream.close();
									fileOutputStream.close();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							

							String checkTonTai = "false";

							for (int i = 0; i < _listAccount.size(); i++) {
								if (_listAccount.get(i).getMaNV().equals(nhanVienModel.getMaNV())) {
									_listAccount.remove(i);
									NhanVienModel nvm = new NhanVienModel(nhanVienModel.getMaNV(), nhanVienModel.getTenNV(), nhanVienModel.getSdt(), nhanVienModel.getGioiTinh(), nhanVienModel.getEmail(), nhanVienModel.getChucVuModel(), nhanVienModel.getTrangThai(), nhanVienModel.getCmnd(), nhanVienModel.getUserName(), matKhau, nhanVienModel.getNgaySinh());
									_listAccount.add(0, nvm);
									File file = new File(utils.File.fileGhiNhoMatKhau());
									try {
										if (!file.exists()) {
											file.createNewFile();
										}
										FileOutputStream fileOutputStream = new FileOutputStream(file);
										ObjectOutputStream objectOutputStream = new ObjectOutputStream(
												fileOutputStream);

										for (NhanVienModel nhanVienModel11 : _listAccount) {
											objectOutputStream.writeObject(nhanVienModel11);
										}

										objectOutputStream.close();
										fileOutputStream.close();
										checkTonTai = "true";
										break;
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}

							}

							if (checkTonTai.equals("false")) {
								NhanVienModel nvm = new NhanVienModel(nhanVienModel.getMaNV(), nhanVienModel.getTenNV(), nhanVienModel.getSdt(), nhanVienModel.getGioiTinh(), nhanVienModel.getEmail(), nhanVienModel.getChucVuModel(), nhanVienModel.getTrangThai(), nhanVienModel.getCmnd(), nhanVienModel.getUserName(), matKhau, nhanVienModel.getNgaySinh());
								_listAccount.add(0, nvm);
								File file = new File(utils.File.fileGhiNhoMatKhau());
								try {
									if (!file.exists()) {
										file.createNewFile();
									}
									FileOutputStream fileOutputStream = new FileOutputStream(file);
									ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

									for (NhanVienModel nhanVienModel11 : _listAccount) {
										objectOutputStream.writeObject(nhanVienModel11);
									}

									objectOutputStream.close();
									fileOutputStream.close();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}

						} else {
							for (int i = 0; i < _listAccount.size(); i++) {
								if (_listAccount.get(i).getMaNV().equals(nhanVienModel.getMaNV())) {
									_listAccount.remove(i);
									File file = new File(utils.File.fileGhiNhoMatKhau());
									try {
										if (!file.exists()) {
											file.createNewFile();
										}
										FileOutputStream fileOutputStream = new FileOutputStream(file);
										ObjectOutputStream objectOutputStream = new ObjectOutputStream(
												fileOutputStream);

										for (NhanVienModel nhanVienModel11 : _listAccount) {
											objectOutputStream.writeObject(nhanVienModel11);
										}

										objectOutputStream.close();
										fileOutputStream.close();
										break;
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}

							}
						}

						MainMenu mainMenu = new MainMenu(userAreUsing);
						mainMenu.setVisible(true);
						frame.setVisible(false);
						System.out.print("Đăng nhập thành công");
						check = "true";
						break;
					}
				}
				if (check.equals("false")) {
					JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không chính xác");
				}
			}
		});
		btn_login.setBackground(Color.WHITE);
		btn_login.setBounds(827, 385, 252, 47);
		contentPane_1.add(btn_login);
		
		JComboBox comboBox_selectLanguage = new JComboBox();
		comboBox_selectLanguage.setModel(new DefaultComboBoxModel(new String[] {"Tiếng việt"}));
		comboBox_selectLanguage.setBackground(Color.WHITE);
		comboBox_selectLanguage.setBounds(980, 522, 91, 23);
		contentPane_1.add(comboBox_selectLanguage);
		
		JButton btn_forgetPassword = new JButton("Quên mật khẩu?");
		btn_forgetPassword.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (quenmk == null) {
					quenmk = new viewQuenMatKhau();
				}
				quenmk.setVisible(true);
			}
		});
		btn_forgetPassword.setOpaque(false);
		btn_forgetPassword.setHorizontalAlignment(SwingConstants.LEFT);
		btn_forgetPassword.setBorderPainted(false);
		btn_forgetPassword.setBackground(new Color(220, 220, 220));
		btn_forgetPassword.setBounds(806, 522, 136, 23);
		contentPane_1.add(btn_forgetPassword);
		
		
		
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(602, 38, -207, 190);
		contentPane_1.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 800, 533);
		contentPane_1.add(panel);
		panel.setLayout(null);
		
		lbl_hotelImage = new JLabel("1");
		lbl_hotelImage.setBackground(Color.WHITE);
		lbl_hotelImage.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_hotelImage.setBounds(0, 0, 800, 533);
		lbl_hotelImage.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(DangNhap.class.getResource("anh1.png"))));
		updateImage();
		panel.add(lbl_hotelImage);
		
		
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(827, 24, 252, 182);
		contentPane_1.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lbl_imgLogo = new JLabel();
		lbl_imgLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_imgLogo.setText(null);
		lbl_imgLogo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(DangNhap.class.getResource("hotelLogo.png"))));
		lbl_imgLogo.setBounds(0, 11, 252, 165);
		panel_1.add(lbl_imgLogo);
		
	}
	
	public void updateImage() {
		_timer = new Timer(0, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(k==1) {
					lbl_hotelImage.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(DangNhap.class.getResource("anh1.png"))));
				}else if(k==2) {
					lbl_hotelImage.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(DangNhap.class.getResource("anh2.png"))));
				}else if(k==3) {
					lbl_hotelImage.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(DangNhap.class.getResource("anh3.png"))));
				}else if(k==4) {
					lbl_hotelImage.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(DangNhap.class.getResource("anh4.png"))));
				}else if(k==5) {
					lbl_hotelImage.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(DangNhap.class.getResource("anh5.png"))));
				}else{
					lbl_hotelImage.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(DangNhap.class.getResource("anh6.png"))));
				}
				k++;
				if(k==6) {
					k=0;
				}
			}
		});
		_timer.setDelay(1500);
		_timer.start();
	}
}