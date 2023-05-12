package Views;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Entities.PhuTroi;
import Services.DichVuPhongService;
import Services.HoaDon_services;
import Services.KhachTrongPhong_services;
import Services.PhuPhiService;
import Services.ThanhToan_services;
import Services.loaiPhong_services;
import ViewModels.ModelHoaDon;
import ViewModels.ModelKhachTrongPhong;
import ViewModels.ModelLoaiPhong;
import ViewModels.ModelThanhToan;
import models.DichVuPhongModel;
import models.PhuPhiModel;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class view_hoadon extends JPanel {
	private JTable tbl_phong;
	private JTable tbl_dichvu;
	private JTable tbl_phutroi;
	private JTable tbl_phuphi;
	private JTable tbl_thanhtoan;
	JLabel thanhtoan = new JLabel("0");
	JLabel conlai = new JLabel("0");
	JLabel tongcong = new JLabel("0");
	JLabel phuphi = new JLabel("0");
	JLabel dichvu = new JLabel("0");
	JLabel checkoutmuon = new JLabel("0");
	JLabel checkinsom = new JLabel("0");
	JLabel tienphong = new JLabel("0");
	DefaultTableModel model_p = new DefaultTableModel(new String[] { "STT", "Phòng", "Loại phòng", "tiền phòng" }, 0);
	DefaultTableModel model_dv = new DefaultTableModel(new String[] { "STT", "Dịch vụ", "số lượng", "giá" }, 0);
	DefaultTableModel model_pt = new DefaultTableModel(
			new String[] { "STT", "Phòng", "Loại phụ trội", "tiền phụ trội" }, 0);
	DefaultTableModel model_pht = new DefaultTableModel(new String[] { "STT", "Tiền phụ phí", "Lý do" }, 0);
	DefaultTableModel model_tt = new DefaultTableModel(new String[] { "STT", "số tiền", "ngày thanh toán" }, 0);

	KhachTrongPhong_services ser_ktp = new KhachTrongPhong_services();
	PhuPhiService ser_phu=new PhuPhiService();
	ThanhToan_services ser_tt = new ThanhToan_services();
	DichVuPhongService ser_dvp=new DichVuPhongService();

	List<ModelKhachTrongPhong> list_ktp = ser_ktp.getList();
	List<ModelThanhToan> list_tt;
	List<PhuPhiModel> list_pp;
	List<DichVuPhongModel> list_dv;

	ModelHoaDon hd;

	public view_hoadon(ModelHoaDon hd) {
		setBackground(Color.WHITE);
		this.hd = hd;
		list_tt = ser_tt.getList(hd.getMaHoaDon());
		ser_phu.updateListPhuPhiModel();
		ser_dvp.updateListDichVuPhongModel();
		list_pp=ser_phu.getListPhuPhiModel();
		list_dv=ser_dvp.getListDichVuPhongModel();
		setSize(1280, 720);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"H\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 10, 810, 663);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Phòng");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 20, 194, 19);
		panel.add(lblNewLabel);

		JLabel lblDchV = new JLabel("Dịch vụ");
		lblDchV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDchV.setBounds(427, 20, 194, 19);
		panel.add(lblDchV);

		JLabel lblPhTri = new JLabel("Phụ trội");
		lblPhTri.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhTri.setBounds(10, 332, 194, 19);
		panel.add(lblPhTri);

		final JLabel lblPhPh = new JLabel("Phụ phí");
		lblPhPh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhPh.setBounds(427, 332, 194, 19);
		panel.add(lblPhPh);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 372, 256);
		panel.add(scrollPane);

		tbl_phong = new JTable();
		tbl_phong.setModel(model_p);
		scrollPane.setViewportView(tbl_phong);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(427, 40, 372, 256);
		panel.add(scrollPane_1);

		tbl_dichvu = new JTable();
		tbl_dichvu.setModel(model_dv);
		scrollPane_1.setViewportView(tbl_dichvu);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 361, 372, 265);
		panel.add(scrollPane_2);

		tbl_phutroi = new JTable();
		tbl_phutroi.setModel(model_pt);
		scrollPane_2.setViewportView(tbl_phutroi);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(427, 361, 372, 265);
		panel.add(scrollPane_3);

		tbl_phuphi = new JTable();
		tbl_phuphi.setModel(model_pht);
		scrollPane_3.setViewportView(tbl_phuphi);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(
				new TitledBorder(null, "Thanh to\u00E1n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(830, 10, 426, 317);
		add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(10, 25, 406, 270);
		panel_1.add(scrollPane_1_1);

		tbl_thanhtoan = new JTable();
		tbl_thanhtoan.setModel(model_tt);
		scrollPane_1_1.setViewportView(tbl_thanhtoan);
		
		JLabel lblNewLabel_1 = new JLabel("Dịch vụ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(830, 438, 195, 29);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Còn lại:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1_1.setBounds(830, 594, 195, 29);
		add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Thanh Toán: ");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1_2.setBounds(830, 555, 195, 29);
		add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Tổng cộng");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1_3.setBounds(830, 516, 195, 29);
		add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Phụ phí");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1_4.setBounds(830, 477, 195, 29);
		add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Tiền phòng");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1_5.setBounds(830, 337, 195, 29);
		add(lblNewLabel_1_5);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Check out muộn");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 24));
		chckbxNewCheckBox.setBounds(826, 403, 199, 29);
		add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Check in sớm");
		chckbxNewCheckBox_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		chckbxNewCheckBox_1.setBounds(826, 372, 199, 29);
		add(chckbxNewCheckBox_1);
		
		
		tienphong.setFont(new Font("Tahoma", Font.PLAIN, 24));
		tienphong.setBounds(1047, 337, 195, 29);
		add(tienphong);
		
		
		checkinsom.setFont(new Font("Tahoma", Font.PLAIN, 24));
		checkinsom.setBounds(1047, 372, 195, 29);
		add(checkinsom);
		
		
		checkoutmuon.setFont(new Font("Tahoma", Font.PLAIN, 24));
		checkoutmuon.setBounds(1047, 403, 195, 29);
		add(checkoutmuon);
		
		
		dichvu.setFont(new Font("Tahoma", Font.PLAIN, 24));
		dichvu.setBounds(1047, 438, 195, 29);
		add(dichvu);
		
		
		phuphi.setFont(new Font("Tahoma", Font.PLAIN, 24));
		phuphi.setBounds(1047, 477, 195, 29);
		add(phuphi);
		
		
		tongcong.setFont(new Font("Tahoma", Font.PLAIN, 24));
		tongcong.setBounds(1047, 516, 195, 29);
		add(tongcong);
		
		
		thanhtoan.setFont(new Font("Tahoma", Font.PLAIN, 24));
		thanhtoan.setBounds(1047, 555, 195, 29);
		add(thanhtoan);
		
		conlai.setFont(new Font("Tahoma", Font.PLAIN, 24));
		conlai.setBounds(1047, 594, 195, 29);
		add(conlai);
		
		final JButton btn_checkout = new JButton("Trả phòng");
		final ModelHoaDon hoadon=hd;
		btn_checkout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				HoaDon_services ser=new HoaDon_services();
				int a=JOptionPane.showConfirmDialog(lblPhPh, "Bạn có muốn check out ?");
				if(a!=0)return;
				hoadon.setTrangThai("checkout");
				ser.save(hoadon, false);
				JOptionPane.showMessageDialog(lblPhPh, "checkout thành công");
			}
		});
		btn_checkout.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_checkout.setBounds(971, 633, 142, 29);
		add(btn_checkout);
		
		adddata();
	}

	public void adddata() {
		model_dv.setRowCount(0);
		model_p.setRowCount(0);
		model_pht.setRowCount(0);
		model_pt.setRowCount(0);
		model_tt.setRowCount(0);

		int stt = 0;
		Double tien=0.0,pt=0.0,tong=0.0;
		for (ModelKhachTrongPhong x : list_ktp) {
			if (x.getHoadon().getMaHoaDon() == hd.getMaHoaDon()) {
				stt++;
				model_p.addRow(new Object[] { stt, x.getPhong().getMaPhong(),x.getPhong().getLoaiphong().getTenLoai(), x.getGiaPhong() });
				tien+=x.getGiaPhong();
			}
		}
		tienphong.setText(new BigDecimal(tien)+"");
		tong+=tien;
		tien=0.0;

		stt = 0;
		for (ModelThanhToan x : list_tt) {
			stt++;
			model_tt.addRow(new Object[] { stt, x.getTien(), x.getNgayThanhToan() });
			tien+=x.getTien();
		}
		thanhtoan.setText(new BigDecimal(tien)+"");
		tien=0.0;

		stt=0;
		
		for (ModelKhachTrongPhong x : list_ktp) {
			if (x.getHoadon().getMaHoaDon() == hd.getMaHoaDon()) {
				stt++;
				model_pt.addRow(new Object[] { stt, x.getPhong().getMaPhong(),"Check in sớm", x.getPhuTroi() });
				tien+=x.getPhuTroi();
				stt++;
				model_pt.addRow(new Object[] { stt, x.getPhong().getMaPhong(),"Check out muộn", x.getPhutroi2() });
				pt+=x.getPhutroi2();
			}
		}
		checkinsom.setText(new BigDecimal(tien)+"");
		tong+=tien;
		tien=0.0;
		checkoutmuon.setText(new BigDecimal(pt)+"");
		tong+=pt;
		
		stt=0;
		for(PhuPhiModel x:list_pp) {
			System.out.println("ma hoa don"+x.getKhachTrongPhongModel().getHoaDonModel().getMaHoaDon());
			if(x.getKhachTrongPhongModel().getHoaDonModel().getMaHoaDon()==hd.getMaHoaDon()) {
				model_pht.addRow(new Object[] { stt, x.getSoTien(),x.getGhiChu()});
				stt++;
				tien+=x.getSoTien();
			}
		}
		phuphi.setText(new BigDecimal(tien)+"");
		tong+=tien;
		tien=0.0;
		
		stt=0;
		for(DichVuPhongModel x:list_dv) {
			if(x.getKhachTrongPhongModel().getHoaDonModel().getMaHoaDon()==hd.getMaHoaDon()) {
				model_pht.addRow(new Object[] { stt, x.getSanPhamVaDichVuModel().getTenHangHoa(),x.getSoLuong(),x.getGiaBan()});
				stt++;
				tien+=x.getGiaBan();
			}
		}
		dichvu.setText(new BigDecimal(tien)+"");
		tong+=tien;
		tien=0.0;
		
		conlai.setText(new BigDecimal(tong-Double.parseDouble(thanhtoan.getText()))+"");
	}

	public static void main(String[] args) {
		new Thread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				new view_hoadon(new ModelHoaDon()).setVisible(true);
			}
		}).start();
	}
}
