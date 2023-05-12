package Repositories;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Entities.Phong;
import Utinities.hibernate_ultil;

public class dao_Phong {
	Session ss;

	public dao_Phong() {
		// TODO Auto-generated constructor stub
		ss = hibernate_ultil.ss();
	}

	public void themsua(ArrayList<Phong> phong) {
		ss.clear();
		Transaction tx = ss.beginTransaction();
		for (int i = 0; i < phong.size(); i++) {
			ss.saveOrUpdate(phong.get(i));
		}
		tx.commit();
	}

	public void xoa(Component comp, ArrayList<Phong> phong) {
		for (int i = 0; i < phong.size(); i++) {
			if (phong.get(i).getDSKhachTrongPhong() != null) {
				JOptionPane.showMessageDialog(comp, "Không thể xóa phòng có lịch sử hóa đơn");
				return;
			}
		}
		ss.clear();
		Transaction tx = ss.beginTransaction();
		System.out.println("xoa");
		for (int i = 0; i < phong.size(); i++) {
			ss.delete(phong.get(i));
		}
		JOptionPane.showMessageDialog(comp, "Xóa thành công");
		tx.commit();
	}

	public List<Phong> getP(Collection<Integer> arr) {
		List<Phong> p = new ArrayList<Phong>();
		for (Integer a : arr) {
			Query qr = ss.createQuery("from Phong where MaPhong='" + a + "'");
			p.add((Phong) qr.uniqueResult());
		}
		return p;
	}

	public List<Phong> selectAll() {
		List<Phong> list = new ArrayList<>();
		Query query = ss.createQuery("FROM Phong");
		return query.list();

	}
}
