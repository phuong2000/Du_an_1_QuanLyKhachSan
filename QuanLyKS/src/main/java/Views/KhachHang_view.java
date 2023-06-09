package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import models.ChucVuModel;
import models.KhachHangModel;
import models.SanPhamVaDichVuModel;
import models.KhachHangModel;
import Services.KhachHangService;
import Services.NhanVienService;
import utils.IoCContainer;
import utils.Utilities;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Container;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextArea;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class KhachHang_view extends JFrame {
	private IoCContainer _ioCContainer  = new IoCContainer();
	private KhachHangService _khachHangService = new KhachHangService();
	private List<KhachHangModel> _listKhachHangModels = new ArrayList<KhachHangModel>();
	private int _maxID;
	private int _sttNhanVienEditing;
	private boolean _thaoTac = true;
	
	private JPanel contentPane;
	private JTextField txt_timKiem;
	private JTable table;
	private JTextField txt_maKhachHang;
	private JTextField txt_tenKhachHang;
	private JTextField txt_soDienThoai;
	private JTextField txt_email;
	private JTextField txt_cmnd;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdb_nam;
	private JRadioButton rdb_nu;
	private JDateChooser dateChooser_ngaySinh;
	private JButton btn_them;
	private JButton btn_sua;
	private JTextArea txt_diaChi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KhachHang_view frame = new KhachHang_view();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public KhachHang_view() {
		_khachHangService.updateListKhachHangModel();
		_maxID = _khachHangService.getMaxID();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblQunLNhn = new JLabel("Quản lý khách hàng");
		lblQunLNhn.setHorizontalAlignment(SwingConstants.CENTER);
		lblQunLNhn.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblQunLNhn.setBounds(0, 0, 1264, 64);
		contentPane.add(lblQunLNhn);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 80, 1264, 601);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Cập nhật khách hàng", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Giới tính");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 210, 109, 41);
		panel.add(lblNewLabel);
		
		txt_maKhachHang = new JTextField();
		txt_maKhachHang.setEditable(false);
		txt_maKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txt_maKhachHang.setBounds(129, 38, 344, 41);
		panel.add(txt_maKhachHang);
		txt_maKhachHang.setColumns(10);
		
		txt_tenKhachHang = new JTextField();
		txt_tenKhachHang.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
			}
		});
		txt_tenKhachHang.setColumns(10);
		txt_tenKhachHang.setBounds(129, 99, 344, 41);
		panel.add(txt_tenKhachHang);
		
		txt_soDienThoai = new JTextField();
		txt_soDienThoai.setColumns(10);
		txt_soDienThoai.setBounds(129, 162, 344, 41);
		panel.add(txt_soDienThoai);
		
		txt_email = new JTextField();
		txt_email.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txt_email.setColumns(10);
		txt_email.setBounds(129, 258, 344, 41);
		panel.add(txt_email);
		
		 rdb_nam = new JRadioButton("Nam");
		buttonGroup.add(rdb_nam);
		rdb_nam.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdb_nam.setBounds(129, 210, 74, 41);
		panel.add(rdb_nam);
		
		 rdb_nu = new JRadioButton("Nu");
		buttonGroup.add(rdb_nu);
		rdb_nu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdb_nu.setBounds(205, 212, 94, 41);
		panel.add(rdb_nu);
		
		txt_cmnd = new JTextField();
		txt_cmnd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txt_cmnd.setColumns(10);
		txt_cmnd.setBounds(730, 97, 344, 41);
		panel.add(txt_cmnd);
		
		 dateChooser_ngaySinh = new JDateChooser();
		dateChooser_ngaySinh.setBounds(730, 38, 344, 41);
		panel.add(dateChooser_ngaySinh);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(10, 258, 109, 41);
		panel.add(lblEmail);
		
		JButton btnNewButton = new JButton("Làm mới");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearForm();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(1073, 482, 153, 47);
		panel.add(btnNewButton);
		
		 btn_them = new JButton("Thêm");
		 btn_them.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					KhachHangModel KhachHangModel = getInforFromFormIntoKhachHangModel();
					if(KhachHangModel == null) {
						return;
					}
					_khachHangService.them_sua(KhachHangModel);
					JOptionPane.showMessageDialog(null, "Thêm thành công");
					updateTable();
					_maxID++;
					clearForm();
				}
			});
		btn_them.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_them.setBackground(Color.WHITE);
		btn_them.setBounds(747, 482, 153, 47);
		panel.add(btn_them);
		
		 btn_sua = new JButton("Sửa");
		 btn_sua.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		KhachHangModel KhachHangModel = getInforFromFormIntoKhachHangModel();
		 		if(KhachHangModel == null) {
					return;
				}
				_khachHangService.them_sua(KhachHangModel);
				JOptionPane.showMessageDialog(null, "Sửa thành công");
				clearForm();
				updateTable();
				_thaoTac = true;
		 	}
		 });
		btn_sua.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_sua.setBackground(Color.WHITE);
		btn_sua.setBounds(910, 482, 153, 47);
		panel.add(btn_sua);
		
		JLabel lblMNhnVin = new JLabel("Mã khách hàng");
		lblMNhnVin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMNhnVin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMNhnVin.setBounds(10, 38, 109, 41);
		panel.add(lblMNhnVin);
		
		JLabel lblTnNhnVin = new JLabel("Tên khách hàng");
		lblTnNhnVin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTnNhnVin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTnNhnVin.setBounds(10, 99, 109, 41);
		panel.add(lblTnNhnVin);
		
		JLabel lblSinThoi = new JLabel("Số điện thoại");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSinThoi.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSinThoi.setBounds(10, 162, 109, 41);
		panel.add(lblSinThoi);
		
		JLabel lblNgySinh = new JLabel("Ngày sinh");
		lblNgySinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNgySinh.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNgySinh.setBounds(611, 38, 109, 41);
		panel.add(lblNgySinh);
		
		JLabel lblCmnd = new JLabel("Cmnd");
		lblCmnd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCmnd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCmnd.setBounds(611, 97, 109, 41);
		panel.add(lblCmnd);
		
		JLabel lblMtKhu = new JLabel("Địa chỉ");
		lblMtKhu.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMtKhu.setBounds(611, 160, 109, 41);
		panel.add(lblMtKhu);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Danh sách", null, panel_1, null);
		panel_1.setLayout(null);
		
		txt_timKiem = new JTextField("Nhập thông tin muốn tìm");
		txt_timKiem.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				updateTable();
			}
		});
		txt_timKiem.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Utilities.setTextFocusGained(txt_timKiem, "Nhập thông tin muốn tìm");
			}
			@Override
			public void focusLost(FocusEvent e) {
				Utilities.setTextFocusLost(txt_timKiem, "Nhập thông tin muốn tìm");
			}
		});
		txt_timKiem.setBounds(59, 22, 1190, 36);
		panel_1.add(txt_timKiem);
		txt_timKiem.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 69, 1239, 493);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				doClickOnTable();
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(KhachHang_view.class.getResource("Search-icon.png"))));
		lblNewLabel_1.setBounds(10, 22, 43, 36);
		panel_1.add(lblNewLabel_1);
		btn_sua.setEnabled(false);
		updateTable();
		txt_maKhachHang.setText(_maxID+"");
		
		txt_diaChi = new JTextArea();
		txt_diaChi.setBounds(730, 170, 344, 137);
		panel.add(txt_diaChi);
	}
	
	
	
	public void clearForm() {
		txt_maKhachHang.setText(_maxID+"");
		txt_tenKhachHang.setText("");
		txt_soDienThoai.setText("");
		buttonGroup.clearSelection();
		txt_email.setText("");
		dateChooser_ngaySinh.setDate(null);
		txt_cmnd.setText("");
		txt_diaChi.setText("");
		btn_sua.setEnabled(false);
		btn_them.setEnabled(true);
		_thaoTac=true;
	}
	
	public void doClickOnTable() {
		_sttNhanVienEditing = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0)+"")-1;
		txt_maKhachHang.setText(_listKhachHangModels.get(_sttNhanVienEditing).getMaKhachHang()+"");
		txt_tenKhachHang.setText(_listKhachHangModels.get(_sttNhanVienEditing).getTenKhachHang());
		txt_soDienThoai.setText(_listKhachHangModels.get(_sttNhanVienEditing).getSdt());
		String gioiTinh =_listKhachHangModels.get(_sttNhanVienEditing).getGioiTinh();
		if(gioiTinh.equals("Nam")) {
			rdb_nam.setSelected(true);
		}else
			rdb_nu.setSelected(true);
		txt_email.setText(_listKhachHangModels.get(_sttNhanVienEditing).getEmail());
		txt_diaChi.setText(_listKhachHangModels.get(_sttNhanVienEditing).getDiaChi());
		Date date = new Date(Utilities.splitYear(_listKhachHangModels.get(_sttNhanVienEditing).getNgaySinh()+"")-1900, Utilities.splitMonth(_listKhachHangModels.get(_sttNhanVienEditing).getNgaySinh()+"")-1, Utilities.splitDate(_listKhachHangModels.get(_sttNhanVienEditing).getNgaySinh()+""));
		dateChooser_ngaySinh.setDate(date);
		txt_cmnd.setText(_listKhachHangModels.get(_sttNhanVienEditing).getCmnd());
		btn_them.setEnabled(false);
		btn_sua.setEnabled(true);
		_thaoTac=false;
	}
	
	public void updateTable() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Stt");
		model.addColumn("Tên khách hàng");
		model.addColumn("Số điện thoại");
		model.addColumn("Giới tính");
		model.addColumn("Email");
		model.addColumn("Ngày sinh");
		model.addColumn("Cmnd");
		_listKhachHangModels = _khachHangService.getListKhachHangModel();
		int stt= 1;
		
		String keySearch = txt_timKiem.getText().trim();
		if(keySearch.equals("") || keySearch.equals("Nhập thông tin muốn tìm")) {
			for (KhachHangModel KhachHangModel : _listKhachHangModels) {
				model.addRow(new Object[] {stt,KhachHangModel.getTenKhachHang(),KhachHangModel.getSdt(),KhachHangModel.getGioiTinh(),KhachHangModel.getEmail(),KhachHangModel.getNgaySinh(),KhachHangModel.getCmnd()});
				stt++;
			}
		}else {
			for (KhachHangModel KhachHangModel : _listKhachHangModels) {
				if(KhachHangModel.toString().toLowerCase().indexOf(keySearch.toLowerCase())>=0) {
					model.addRow(new Object[] {stt,KhachHangModel.getTenKhachHang(),KhachHangModel.getSdt(),KhachHangModel.getGioiTinh(),KhachHangModel.getEmail(),KhachHangModel.getNgaySinh(),KhachHangModel.getCmnd()});
					stt++;
				}
			}
		}
		
			
		table.setModel(model);
	}
	
	public KhachHangModel getInforFromFormIntoKhachHangModel() {
		
		int maKhachHang = Integer.parseInt(txt_maKhachHang.getText().trim());
		
		String tenKhachHang = txt_tenKhachHang.getText().trim();
		
		if(tenKhachHang.equals("")) {
			JOptionPane.showMessageDialog(contentPane, "Xin vui lòng nhập họ tên");
			return null;
		}
		
		if(Utilities.regexCheckFullName(tenKhachHang).equals("false")) {
			JOptionPane.showMessageDialog(contentPane, "họ tên bạn nhập không hợp lệ");
			return null;
		}
		
		String soDienThoai = txt_soDienThoai.getText().trim();
		System.out.println(soDienThoai);
		if(soDienThoai.equals("")) {
			JOptionPane.showMessageDialog(contentPane, "Xin vui lòng nhập sdt");
			return null;
		}
		
		if(Utilities.regexCheckPhoneNumber(soDienThoai).equals("false")) {
			JOptionPane.showMessageDialog(contentPane, "sdt bạn nhập không hợp lệ");
			return null;
		}
		
		String gioiTinh = "";
		Enumeration<AbstractButton> button  = buttonGroup.getElements();
		while (button.hasMoreElements()) {
			AbstractButton abstractButton = (AbstractButton) button.nextElement();
			if(abstractButton.isSelected()) {
				gioiTinh= abstractButton.getActionCommand();
				break;
			}
		}
		
		if(gioiTinh.equals("")) {
			JOptionPane.showMessageDialog(contentPane, "Xin vui lòng chọn giới tính");
			return null;
		}
		
		String email = txt_email.getText().trim();
		
		if(email.equals("")) {
			JOptionPane.showMessageDialog(contentPane, "Xin vui lòng nhập email");
			return null;
		}
		
		if(Utilities.regexCheckGmail(email).equals("false")) {
			JOptionPane.showMessageDialog(contentPane, "email bạn nhập không hợp lệ");
			return null;
		}
		
		
		
		String diaChi = txt_diaChi.getText().trim();
		String ngaySinh ="";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			ngaySinh = simpleDateFormat.format(dateChooser_ngaySinh.getDate());
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Bạn chưa nhập ngày sinh");
			return null;
		}
		Date date = new Date(Utilities.splitYear(ngaySinh)-1900, Utilities.splitMonth(ngaySinh)-1, Utilities.splitDate(ngaySinh));
		String cmnd = txt_cmnd.getText().trim();
		
		if(cmnd.equals("")) {
			JOptionPane.showMessageDialog(contentPane, "Xin vui lòng nhập cmnd");
			return null;
		}
		
		if(Utilities.regexCheckIdentityCardNumber(cmnd).equals("false")) {
			JOptionPane.showMessageDialog(contentPane, "Cmnd bạn nhập không hợp lệ");
			return null;
		}
		
		KhachHangModel khm = new KhachHangModel(maKhachHang+"", tenKhachHang, soDienThoai, email, cmnd, date, gioiTinh, diaChi);
		return khm;
	}
}