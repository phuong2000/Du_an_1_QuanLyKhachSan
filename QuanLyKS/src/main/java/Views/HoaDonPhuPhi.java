package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import Services.KhachTrongPhongService;
import Services.PhuPhiService;
import models.KhachTrongPhongModel;
import models.PhuPhiModel;
import utils.Utilities;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class HoaDonPhuPhi extends JFrame {

	private PhuPhiService _pps = new PhuPhiService();
	private JPanel contentPane;
	private JTextField txt_soTien;
	private KhachTrongPhongModel _ktpm;
	private JTextArea txt_ghiChu;
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
					HoaDonPhuPhi frame = new HoaDonPhuPhi(listKhachTrongPhongModels.get(0));
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
	public HoaDonPhuPhi(KhachTrongPhongModel ktpm) {
		_ktpm=ktpm;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 432, 486);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Phụ Phí");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(10, 0, 393, 51);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Thêm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themHoaDonPhuPhi();
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(109, 360, 212, 51);
		contentPane.add(btnNewButton);
		
		JPanel panel_2_1_1_1_1 = new JPanel();
		panel_2_1_1_1_1.setLayout(null);
		panel_2_1_1_1_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(192, 192, 192)), "S\u1ED1 ti\u1EC1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2_1_1_1_1.setBackground(Color.WHITE);
		panel_2_1_1_1_1.setBounds(20, 62, 383, 51);
		contentPane.add(panel_2_1_1_1_1);
		
		txt_soTien = new JTextField();
		txt_soTien.setText((String) null);
		txt_soTien.setColumns(10);
		txt_soTien.setBorder(null);
		txt_soTien.setBackground(Color.WHITE);
		txt_soTien.setBounds(10, 18, 363, 29);
		panel_2_1_1_1_1.add(txt_soTien);
		
		JPanel panel_2_1_1_1_1_1 = new JPanel();
		panel_2_1_1_1_1_1.setLayout(null);
		panel_2_1_1_1_1_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(192, 192, 192)), "Ghi ch\u00FA", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2_1_1_1_1_1.setBackground(Color.WHITE);
		panel_2_1_1_1_1_1.setBounds(20, 124, 383, 184);
		contentPane.add(panel_2_1_1_1_1_1);
		
		txt_ghiChu = new JTextArea();
		txt_ghiChu.setBounds(10, 24, 363, 149);
		panel_2_1_1_1_1_1.add(txt_ghiChu);
	}
	
	public void themHoaDonPhuPhi() {
		if(Utilities.regexCheckDouble(txt_soTien.getText().trim()).equals("false")) {
			JOptionPane.showMessageDialog(null, "Số tiền phải là số và không được < 0");
			return ;
		}
		
		double soTien = Double.parseDouble(txt_soTien.getText().trim());
		
		if(soTien<0) {
			JOptionPane.showMessageDialog(null, "Số tiền phải là số và không được < 0");
			return ;
		}
		
		String ghiChu = txt_ghiChu.getText().trim();
		
		PhuPhiModel ppm = new PhuPhiModel(0, _ktpm, soTien, ghiChu);
		_pps.them_sua(ppm);
		JOptionPane.showMessageDialog(null, "Thêm thành công");
		this.dispose();
	}
}
