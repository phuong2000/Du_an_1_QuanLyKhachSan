package Repositories;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Entities.ThanhToan;
import Utinities.hibernate_ultil;

public class dao_ThanhToan {
	Session ss;

	public dao_ThanhToan() {
		super();
		ss=hibernate_ultil.ss();
	}
	
	public List<ThanhToan> select(int ma){
		Query qr=ss.createQuery("from ThanhToan where hoadon_maHoaDon='"+ma+"'");
		return qr.list();
	}
	
	public void save(ThanhToan tt) {
		ss.clear();
		Transaction tx=ss.beginTransaction();
		ss.save(tt);
		tx.commit();
	}
}
