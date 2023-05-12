package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import dao.LoaiPhongDao;
import Entities.KhachTrongPhong;
import models.ChucVuModel;
import models.DichVuPhongModel;
import models.DonViChiTietModel;
import models.DonViTinhModel;
import models.HoaDonModel;
import models.KhachHangModel;
import models.KhachSanModel;
import models.KhachTrongPhongModel;
import models.LoaiPhongModel;
import models.NhanVienModel;
import models.NhomSPVaDichVuModel;
import models.PhongModel;
import models.SanPhamVaDichVuModel;
import models.TangModel;
import Services.DichVuPhongService;
import Services.DonViChiTietService;
import Services.DonViTinhService;
import Services.KhachTrongPhongService;
import Services.NhomSPVaDichVuService;
//import Services.LoaiPhongService;
import Services.SanPhamVaDichVuService;
import utils.IoCContainer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class HoaDonDichVu_view extends JFrame {
	private IoCContainer _ioCContainer = new IoCContainer();
	private SanPhamVaDichVuService _sanPhamVaDichVuService = new SanPhamVaDichVuService();
	private NhomSPVaDichVuService _nhomSPVaDichVuService = new NhomSPVaDichVuService();
	private List<NhomSPVaDichVuModel> _listNhomSPVaDichVuModels_active = new ArrayList<NhomSPVaDichVuModel>();
	private List<SanPhamVaDichVuModel> _listSanPhamVaDichVuModels = new ArrayList<SanPhamVaDichVuModel>();
	private List<DichVuPhongModel> _listCart = new ArrayList<DichVuPhongModel>();
	private DichVuPhongService _dichVuPhongService = new DichVuPhongService();
	private DonViChiTietService _donViChiTietService = new DonViChiTietService();
	private List<DonViChiTietModel> _listDonViChiTietModels = new ArrayList<DonViChiTietModel>();
	private KhachTrongPhongModel _ktpm;
	
	private JPanel contentPane;
	private JTable table_product;
	private javax.swing.JOptionPane JOptionPane;
	private JTable table_cart;
	private JComboBox cbx_nhomDichVu;
	private JLabel lbl_tongCong;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KhachTrongPhongService ktps = new KhachTrongPhongService();
					ktps.updateListKhachTrongPhongModel();
					List<KhachTrongPhongModel> listKhachTrongPhongModels = ktps.getListKhachTrongPhongModel();
					HoaDonDichVu_view frame = new HoaDonDichVu_view(listKhachTrongPhongModels.get(0));
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
	public HoaDonDichVu_view(KhachTrongPhongModel ktpm) {
		_ktpm = ktpm;
		updateListCart();
//		_sanPhamVaDichVuService.updateListSanPhamVaDichVuModel("Hoat Dong");
//		_donViChiTietService.updateListDonViChiTietModel();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 634, 811);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "S\u1EA3n Ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 81, 598, 373);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 63, 578, 299);
		panel.add(scrollPane_1);
		
	
		
		table_product = new JTable();
		table_product.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pressTableProduct();
				
			}
		});
		scrollPane_1.setViewportView(table_product);
		
		JTextField textField = new JTextField();
		textField.setBounds(10, 22, 311, 30);
		panel.add(textField);
		textField.setColumns(10);
		
		cbx_nhomDichVu = new JComboBox();
		cbx_nhomDichVu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTableProduct(false);
			}
		});
		cbx_nhomDichVu.setBounds(350, 22, 238, 30);
		panel.add(cbx_nhomDichVu);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Gi\u1ECF h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 465, 598, 178);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 578, 146);
		panel_1.add(scrollPane);
		
		table_cart = new JTable();
		table_cart.setEnabled(false);
		
		scrollPane.setViewportView(table_cart);
		
		
		
		JLabel lblNewLabel = new JLabel("Hóa Đơn Dịch Vụ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(70, 11, 473, 43);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Cập Nhật");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taoHoaDon();
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(10, 698, 244, 54);
		contentPane.add(btnNewButton);
		
		JButton btnHy = new JButton("H\u1EE7y");
		btnHy.setBackground(Color.WHITE);
		btnHy.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnHy.setBounds(364, 698, 244, 54);
		contentPane.add(btnHy);
		
		JLabel lblNewLabel_1 = new JLabel("Tổng tiền:");
		lblNewLabel_1.setBounds(364, 641, 66, 35);
		contentPane.add(lblNewLabel_1);
		
		lbl_tongCong = new JLabel("0");
		lbl_tongCong.setForeground(Color.RED);
		lbl_tongCong.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_tongCong.setBounds(440, 641, 167, 35);
		contentPane.add(lbl_tongCong);
		this.setVisible(false);
		this.setVisible(true);
		updateCbx_nhomDichVu();
		updateTableProduct(true);
		updateTableCart();
		
	}
	
	public void updateCbx_nhomDichVu() {
		_nhomSPVaDichVuService.updateListNhomSPVaDichVuModel("Hoat Dong");
		
		List<NhomSPVaDichVuModel> nspvdvm = _nhomSPVaDichVuService.getListNhomSPVaDichVuModelActive();
		
		for (NhomSPVaDichVuModel nhomSPVaDichVuModel : nspvdvm) {
			if(nhomSPVaDichVuModel.getNhomHangHoa().equals("Dich vu")) {
				_listNhomSPVaDichVuModels_active.add(nhomSPVaDichVuModel);
			}
		}
		
		String arrayNhomSPVaDichVu[] = new String[_listNhomSPVaDichVuModels_active.size()+1];
		arrayNhomSPVaDichVu[0] = "Tất cả";
		int index =1;
		for (NhomSPVaDichVuModel nhomSPVaDichVuModel : _listNhomSPVaDichVuModels_active) {
			arrayNhomSPVaDichVu[index] = nhomSPVaDichVuModel.getTenNhomSP();
			index++;
		}
		
		cbx_nhomDichVu.setModel(new DefaultComboBoxModel(arrayNhomSPVaDichVu));
		
	}

	public void updateListCart() {
		_dichVuPhongService.updateListDichVuPhongModel();
		 List<DichVuPhongModel> listDVPM = _dichVuPhongService.getListDichVuPhongModel();
		 _listCart = new ArrayList<DichVuPhongModel>();
		 for (DichVuPhongModel dichVuPhongModel : listDVPM) {
			if(dichVuPhongModel.getSanPhamVaDichVuModel().getNhomSPVaDichVuModel().getNhomHangHoa().equals("Dich vu")) {
				_listCart.add(dichVuPhongModel);
			}
		}
	}
	
	public void updateTableProduct(boolean updateDependOnListService) {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Stt");
		model.addColumn("Mã");
		model.addColumn("Tên hàng hóa");
		model.addColumn("S.Lg tồn");
		model.addColumn("Giá bán");
		
		if(updateDependOnListService) {
			_listSanPhamVaDichVuModels  = new ArrayList<SanPhamVaDichVuModel>();
			_sanPhamVaDichVuService.updateListSanPhamVaDichVuModel("Hoat Dong");
			List<SanPhamVaDichVuModel> listProduct = _sanPhamVaDichVuService.getListSanPhamVaDichVuModel_active();
			for (SanPhamVaDichVuModel spvdvm : listProduct) {
				if(spvdvm.getNhomSPVaDichVuModel().getNhomHangHoa().equals("Dich vu")) {
					_listSanPhamVaDichVuModels.add(spvdvm);
				}
			}
		}
		
		int stt =1;
		for (SanPhamVaDichVuModel spvdvm : _listSanPhamVaDichVuModels) {
			String nhomSanPham = cbx_nhomDichVu.getSelectedItem().toString();
			if(nhomSanPham.equals("Tất cả")) {
				DonViChiTietModel dvctm = _sanPhamVaDichVuService.getDonViMacDinh(spvdvm);
				DonViTinhModel dvtm = dvctm.getDonViTinhModel();
				String tenDonVi = dvtm.getTenDonVi();
				String giaBan = dvctm.getGiaBan() +"/"+tenDonVi;
				model.addRow(new Object[] {stt , spvdvm.getMaDichVu() , spvdvm.getTenHangHoa() , spvdvm.getSoLuongTon(),giaBan});
				stt++;
			}else {
				if(spvdvm.getNhomSPVaDichVuModel().getTenNhomSP().equals(nhomSanPham)) {
					DonViChiTietModel dvctm = _sanPhamVaDichVuService.getDonViMacDinh(spvdvm);
					DonViTinhModel dvtm = dvctm.getDonViTinhModel();
					String tenDonVi = dvtm.getTenDonVi();
					String giaBan = dvctm.getGiaBan() +"/"+tenDonVi;
					model.addRow(new Object[] {stt , spvdvm.getMaDichVu() , spvdvm.getTenHangHoa() , spvdvm.getSoLuongTon(),giaBan});
					stt++;
				}
			}
				
		}
		table_product.setModel(model);
	}
	
	public void pressTableProduct() {
		int maSp = Integer.parseInt(table_product.getModel().getValueAt(table_product.getSelectedRow(), 1)+"");
		String tenSP = table_product.getModel().getValueAt(table_product.getSelectedRow(), 2)+"";
		int soLuongTon = Integer.parseInt(table_product.getModel().getValueAt(table_product.getSelectedRow(), 3)+"");
		
		String gia = table_product.getModel().getValueAt(table_product.getSelectedRow(), 4)+"";
		int indexSplit = gia.indexOf("/");
		String tenDonVi = gia.substring(indexSplit+1, gia.length());
		gia = gia.substring(0,indexSplit);
		double price = Double.parseDouble(gia);
		String soLuong = JOptionPane.showInputDialog(null, "Nhập số lượng");
			
		
		if(soLuong == null || soLuong.equals("") || soLuong.equals("0")) {
			return;
		}
			
		int slg = Integer.parseInt(soLuong);
		
		int soLuongThucTe = soLuongTon-slg;
		if(soLuongThucTe<0) {
			JOptionPane.showMessageDialog(null, "Kho không đủ hàng");
			return;
		}
		
		for (int i=0 ; i<_listCart.size(); i++) {
			if(_listCart.get(i).getSanPhamVaDichVuModel().getMaDichVu() == maSp) {
				_listCart.get(i).setSoLuong(_listCart.get(i).getSoLuong()+slg);
				
				
				for (int j=0 ; j<=_listSanPhamVaDichVuModels.size() ; j++) {
					if(_listSanPhamVaDichVuModels.get(j).getMaDichVu() == (maSp)) {
						_listSanPhamVaDichVuModels.get(j).setSoLuongTon(soLuongThucTe);
						_listCart.get(i).setSanPhamVaDichVuModel(_listSanPhamVaDichVuModels.get(j));
						break;
					}
				}
				updateTableCart();
				updateTableProduct(false);
				return;
			}
		}
		
		
		
			SanPhamVaDichVuModel spvdvm = new SanPhamVaDichVuModel();
			for (SanPhamVaDichVuModel spvdvm1 : _listSanPhamVaDichVuModels) {
				if(spvdvm1.getMaDichVu() == (maSp)) {
					spvdvm = spvdvm1;
					break;
				}
			}
			for (int i=0 ; i<=_listSanPhamVaDichVuModels.size() ; i++) {
				if(_listSanPhamVaDichVuModels.get(i).getMaDichVu() == (maSp)) {
					_listSanPhamVaDichVuModels.get(i).setSoLuongTon(soLuongThucTe);
					spvdvm = _listSanPhamVaDichVuModels.get(i);
					break;
				}
			}
			DichVuPhongModel dvpm = new DichVuPhongModel(0, spvdvm, _ktpm , price, slg);
			_listCart.add(dvpm);
		updateTableProduct(false);
		updateTableCart();
	}
	
	public void taoHoaDon() {
		DichVuPhongService dvps = new DichVuPhongService();
		dvps.updateListDichVuPhongModel();
		
		for(int i=0; i<_listCart.size();i++) {
			
			dvps.them_sua(_listCart.get(i));
			
			SanPhamVaDichVuModel spvdvm = _listCart.get(i).getSanPhamVaDichVuModel();
			_sanPhamVaDichVuService.them_sua_noUpdate(spvdvm);
		}
		
		updateTableProduct(true);
		updateListCart();
		updateTableCart();
		
	}
	
	public void updateTableCart() {
		DefaultTableModel model1 = new DefaultTableModel();
		model1.addColumn("Stt");
		model1.addColumn("Mã SP");
		model1.addColumn("Tên SP");
		model1.addColumn("Giá");
		model1.addColumn("Số Lượng");
		model1.addColumn("Tổng Tiền");
		double tongCong =0;
		int stt =1;
		for (DichVuPhongModel dvpm : _listCart) {
			if(dvpm.getKhachTrongPhongModel().getId() == _ktpm.getId()) {
				SanPhamVaDichVuModel spvdvm = dvpm.getSanPhamVaDichVuModel();
				double tongTien = dvpm.getGiaBan() * dvpm.getSoLuong();
				tongCong += tongTien;
				model1.addRow(new Object[] {stt ,spvdvm.getMaDichVu(), spvdvm.getTenHangHoa() , dvpm.getGiaBan() , dvpm.getSoLuong() , tongTien});
				stt++;
			}
		}
		lbl_tongCong.setText(tongCong+"");
		table_cart.setModel(model1);
	}
}