package Repositories;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Entities.Tang;
import Utinities.hibernate_ultil;

public class dao_Tang {
	Session ss;
	public dao_Tang() {
		//TODO Auto-generated constructor stub
		ss=hibernate_ultil.ss();
	}

	public void themsua(Collection<Tang> tang) {
		ss.clear();
		Transaction tx=ss.beginTransaction();
		for(Tang t:tang) {
			ss.saveOrUpdate(t);
		}
		tx.commit();
	}
	
	public void xoa(Component comp,Collection<Tang> tang) {
		ss.clear();
		Transaction tx=ss.beginTransaction();
		for(Tang t:tang) {
			if(!t.getDSPhong().isEmpty()) {
				JOptionPane.showMessageDialog(comp, "Tầng có phòng không thể xóa");
				return;
			}
			ss.delete(t);
		}
		JOptionPane.showMessageDialog(comp, "xóa thành công");
		tx.commit();
	}
	
	public List<Tang> select(Tang t) {
		Query qr=ss.createQuery("from Tang where MaTang= '"+t.getMaTang()+"'");
		return qr.list();
	}
}
