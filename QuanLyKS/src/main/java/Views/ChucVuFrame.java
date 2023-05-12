package Views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import dao.ChucVuDao;
import Entities.ChucVu;

import javax.swing.JTable;
import javax.swing.JButton;
import java.util.ArrayList;
import java.util.List;
import models.ChucVuModel;
import Services.ChucVuService;
import utils.IoCContainer;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class ChucVuFrame extends JFrame {
	
	private List<ChucVuModel> _listChucVuModels = new ArrayList<ChucVuModel>();
	private JPanel contentPane;
	private ChucVuService _chucVuService = new ChucVuService();
	/**
	 * Launch the application.
	 */

	 private JTable table;
	 private JTextField txt_maChucVu;
	 private JTextField txt_TenChucVu;
	 private JTextField txt_TrangThai;
	 private int _sttNhanVienEditing;
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChucVuFrame frame = new ChucVuFrame();
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
	public ChucVuFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(1000,800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 966, 753);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btn_Them = new JButton("them");
		btn_Them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChucVuModel nhanVienModel = getInforFromFormIntoNhanVienModel();
				_chucVuService.them_sua(nhanVienModel);
				JOptionPane.showMessageDialog(null, "Thêm thành công");
				clearForm();
				updateTable();
			
					
			}

	

			private ChucVuModel getInforFromFormIntoNhanVienModel() {
				String  maChucVu = txt_maChucVu.getText();
				
				String  tenChucVu = txt_TenChucVu.getText();
				String trangThai = txt_TrangThai.getText();
	
				
				ChucVuModel cv = new ChucVuModel(maChucVu,tenChucVu,trangThai);
				return cv;
			}



			private void clearForm() {
				txt_maChucVu.setText("");
				txt_TenChucVu.setText("");
				txt_TrangThai.setText("");	
				
			}

			private void updateTable() {			
				DefaultTableModel model= new DefaultTableModel();
				model.addColumn("Ma Chuc Vu");
				model.addColumn("Ten Chuc Vu");
				model.addColumn("Trang Thai");
				_listChucVuModels = _chucVuService.getListChucVuModel();
				for (ChucVuModel chucVuModel : _listChucVuModels) {
					model.addRow(new Object[] {chucVuModel.getMaChucVu(),chucVuModel.getTenChucVu(),chucVuModel.getTrangThai()
							});
				}
				table.setModel(model);
			}

		});
		btn_Them.setBounds(669, 55, 85, 21);
		panel.add(btn_Them);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 394, 946, 349);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				doClickOnTable();
			}

			private void doClickOnTable() {
				_sttNhanVienEditing = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 0)+"");
				txt_maChucVu.setText(_listChucVuModels.get(_sttNhanVienEditing).getMaChucVu());
				txt_TenChucVu.setText(_listChucVuModels.get(_sttNhanVienEditing).getTenChucVu());
				txt_TrangThai.setText(_listChucVuModels.get(_sttNhanVienEditing).getTrangThai());			
			}
		});
		DefaultTableModel model= new DefaultTableModel();	     
		Object[] column = {"Ma Chuc Vu","Ten Chuc Vu","Trang thai"};
		model.setColumnIdentifiers(column);		
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Ma Chuc Vu", "Ten Chuc Vu", "Trang Thai"
			}
		));		
		
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("ma Chuc Vu");
		lblNewLabel.setBounds(23, 59, 85, 13);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ten Chuc Vu");
		lblNewLabel_1.setBounds(23, 106, 85, 13);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Trang thai");
		lblNewLabel_2.setBounds(23, 157, 85, 13);
		panel.add(lblNewLabel_2);
		
		txt_maChucVu = new JTextField();
		txt_maChucVu.setBounds(155, 56, 329, 19);
		panel.add(txt_maChucVu);
		txt_maChucVu.setColumns(10);
		
		txt_TenChucVu = new JTextField();
		txt_TenChucVu.setBounds(155, 103, 329, 19);
		panel.add(txt_TenChucVu);
		txt_TenChucVu.setColumns(10);
		
		txt_TrangThai = new JTextField();
		txt_TrangThai.setBounds(155, 154, 329, 19);
		panel.add(txt_TrangThai);
		txt_TrangThai.setColumns(10);
		
		JButton btn_Sua = new JButton("Sua");
		btn_Sua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChucVuModel chucVuModel = getInforFromFormIntoNhanVienModel();				
				_chucVuService.them_sua(chucVuModel);
				JOptionPane.showMessageDialog(null, "Thêm thành công");
				clearForm();
				updateTable();
			}

			private void updateTable() {
				DefaultTableModel model= new DefaultTableModel();
				model.addColumn("Ma Chuc Vu");
				model.addColumn("Ten Chuc Vu");
				model.addColumn("Trang Thai");
				_listChucVuModels = _chucVuService.getListChucVuModel();
				for (ChucVuModel chucVuModel : _listChucVuModels) {
					model.addRow(new Object[] {chucVuModel.getMaChucVu(),chucVuModel.getTenChucVu(),chucVuModel.getTrangThai()
							});
				}
				table.setModel(model);
				
			}

			private void clearForm() {
				txt_maChucVu.setText("");
				txt_TenChucVu.setText("");
				txt_TrangThai.setText("");	
				
				
			}
			private ChucVuModel getInforFromFormIntoNhanVienModel() {
				String  maChucVu = txt_maChucVu.getText();			
				String  tenChucVu = txt_TenChucVu.getText();
				String trangThai = txt_TrangThai.getText();
	
				
				ChucVuModel cv = new ChucVuModel(maChucVu,tenChucVu,trangThai);
				return cv;
			}
		});
		btn_Sua.setBounds(669, 102, 85, 21);
		panel.add(btn_Sua);
		
		JButton btn_lamMoi = new JButton("Lam moi");
		btn_lamMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearForm();
				updateTable();
			}

			private void updateTable() {
				
				updateTable();
			}

			private void clearForm() {
				clearForm();				
			}
		});
		btn_lamMoi.setBounds(669, 153, 85, 21);
		panel.add(btn_lamMoi);
	}
}
