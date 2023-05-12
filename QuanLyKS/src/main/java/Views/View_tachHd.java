package Views;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import ViewModels.ModelHoaDon;
import ViewModels.ModelKhachHang;
import ViewModels.ModelKhachTrongPhong;
import ViewModels.ModelPhong;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Entities.HoaDon;
import Entities.KhachTrongPhong;
import Entities.Phong;
import Repositories.dao_khachTrongPhong;
import Services.HoaDon_services;
import Services.KhachHang_services;
import Services.KhachTrongPhong_services;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;

public class View_tachHd extends JFrame {

	DefaultTableModel model_kc=new DefaultTableModel(new String[]{"STT","Số CMND/Passport","tên khách hàng"},0);
	DefaultTableModel model_km=new DefaultTableModel(new String[]{"STT","Số CMND/Passport","tên khách hàng"},0);
	DefaultTableModel model_pc=new DefaultTableModel(new String[]{"STT","Phòng"},0);
	DefaultTableModel model_pm=new DefaultTableModel(new String[]{"STT","Phòng"},0);
	List<ModelKhachHang> list_kc;
	List<ModelKhachTrongPhong> list_pc;
	List<ModelKhachTrongPhong> list_pm=new ArrayList<>();
	List<ModelKhachHang> list_km=new ArrayList<>();
	ModelHoaDon hd;
	JFrame f=this;
	private JTable tbl_phongcu;
	private JTable tbl_khachcu;
	private JTable tbl_phongmoi;
	private JTable tbl_khachmoi;
	public View_tachHd(ModelHoaDon hoadon) {
		this.hd=hoadon;
		list_kc=hd.getDskhachhang();
		list_pc=hd.getDskhactrongphong();
		setSize(800, 550);
		setDefaultCloseOperation(2);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "H\u00F3a \u0111\u01A1n c\u1EA7n t\u00E1ch", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 370, 443);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 35, 350, 185);
		panel.add(scrollPane_1);
		
		tbl_phongcu = new JTable();
		scrollPane_1.setViewportView(tbl_phongcu);
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(10, 248, 350, 185);
		panel.add(scrollPane_1_1);
		
		tbl_khachcu = new JTable();
		scrollPane_1_1.setViewportView(tbl_khachcu);
		
		JLabel dfdf = new JLabel("Phòng");
		dfdf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dfdf.setBounds(154, 10, 54, 24);
		panel.add(dfdf);
		
		JLabel lblKhchHng = new JLabel("Khách hàng");
		lblKhchHng.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKhchHng.setBounds(137, 218, 82, 24);
		panel.add(lblKhchHng);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "H\u00F3a \u0111\u01A1n m\u1EDBi", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(406, 10, 370, 443);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1_2 = new JScrollPane();
		scrollPane_1_2.setBounds(10, 36, 350, 185);
		panel_1.add(scrollPane_1_2);
		
		tbl_phongmoi = new JTable();
		scrollPane_1_2.setViewportView(tbl_phongmoi);
		
		JScrollPane scrollPane_1_3 = new JScrollPane();
		scrollPane_1_3.setBounds(10, 248, 350, 185);
		panel_1.add(scrollPane_1_3);
		
		tbl_khachmoi = new JTable();
		scrollPane_1_3.setViewportView(tbl_khachmoi);
		
		JLabel dfdf_1 = new JLabel("Phòng");
		dfdf_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dfdf_1.setBounds(156, 10, 54, 24);
		panel_1.add(dfdf_1);
		
		JLabel lblKhchHng_1 = new JLabel("Khách hàng");
		lblKhchHng_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKhchHng_1.setBounds(142, 219, 82, 24);
		panel_1.add(lblKhchHng_1);
		
		JButton btnNewButton = new JButton("Xác nhận");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(list_pm.size()==0||list_km.size()==0) {
					JOptionPane.showMessageDialog(f, "hóa đơn mới không đủ phòng hoặc khách hàng");
					return;
				}
				dao_khachTrongPhong d=new dao_khachTrongPhong();
				HoaDon_services ser_hd=new HoaDon_services();
				hd.setDskhachhang(list_km);
				ser_hd.save(hd, true);
				ModelHoaDon hdm=ser_hd.getList().get(ser_hd.getList().size()-1);
				hd.setDskhachhang(list_kc);
				ser_hd.save(hd, false);
				for(ModelKhachTrongPhong x:list_pc) {
					x.setHoadon(new ModelHoaDon(x.getHoadon().getMaHoaDon()));
					d.save(new KhachTrongPhong(x.getId(), new HoaDon(hd.getMaHoaDon()), new Phong(x.getPhong().getMaPhong()), x.getGiaPhong(), x.getPhuTroi(), x.getPhutroi2(),x.getPhuPhi(), x.getGhiChu()));
				}
				for(ModelKhachTrongPhong x:list_pm) {
					x.setHoadon(new ModelHoaDon(x.getHoadon().getMaHoaDon()));
					d.save(new KhachTrongPhong(x.getId(), new HoaDon(hdm.getMaHoaDon()), new Phong(x.getPhong().getMaPhong()), x.getGiaPhong(), x.getPhuTroi(), x.getPhutroi2(),x.getPhuPhi(), x.getGhiChu()));
				}
				JOptionPane.showMessageDialog(f, "tách hóa đơn thành công");
				f.dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(312, 462, 135, 41);
		getContentPane().add(btnNewButton);
		tbl_khachcu.setModel(model_kc);
		tbl_khachmoi.setModel(model_km);
		tbl_phongcu.setModel(model_pc);
		tbl_phongmoi.setModel(model_pm);
		
		tbl_khachcu.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(tbl_khachcu.getSelectedRow()!=-1) {
					if(list_kc.size()==1) {
						JOptionPane.showMessageDialog(f, "Không thể chuyển thêm");
						return;
					}
					list_km.add(list_kc.get(tbl_khachcu.getSelectedRow()));
					list_kc.remove(tbl_khachcu.getSelectedRow());
					addtbl();
				}
			}
		});
		
		tbl_khachmoi.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(tbl_khachmoi.getSelectedRow()!=-1) {
					list_kc.add(list_km.get(tbl_khachmoi.getSelectedRow()));
					list_km.remove(tbl_khachmoi.getSelectedRow());
					addtbl();
				}
			}
		});
		
		tbl_phongcu.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(tbl_phongcu.getSelectedRow()!=-1) {
					if(list_pc.size()==1) {
						JOptionPane.showMessageDialog(f, "Không thể chuyển thêm");
						return;
					}
					list_pm.add(list_pc.get(tbl_phongcu.getSelectedRow()));
					list_pc.remove(tbl_phongcu.getSelectedRow());
					addtbl();
				}
			}
		});
		
		tbl_phongmoi.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(tbl_phongmoi.getSelectedRow()!=-1) {
					list_pc.add(list_pm.get(tbl_phongmoi.getSelectedRow()));
					list_pm.remove(tbl_phongmoi.getSelectedRow());
					addtbl();
				}
			}
		});
		addtbl();
	}

	void addtbl() {
		model_kc.setRowCount(0);
		model_km.setRowCount(0);
		model_pc.setRowCount(0);
		model_pm.setRowCount(0);
		int stt=0;
		for(ModelKhachTrongPhong x:list_pc) {
			stt++;
			model_pc.addRow(new Object[] {stt,x.getPhong().getMaPhong()});
		}
		stt=0;
		for(ModelKhachTrongPhong x:list_pm) {
			stt++;
			model_pm.addRow(new Object[] {stt,x.getPhong().getMaPhong()});
		}
		stt=0;
		for(ModelKhachHang x:list_kc) {
			stt++;
			model_kc.addRow(new Object[] {stt,x.getCMND(),x.getTenKhachHang()});
		}
		stt=0;
		for(ModelKhachHang x:list_km) {
			stt++;
			model_km.addRow(new Object[] {stt,x.getCMND(),x.getTenKhachHang()});
		}
	}
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				new View_tachHd(new ModelHoaDon()).setVisible(true);
			}
		}).start();
	}
}
