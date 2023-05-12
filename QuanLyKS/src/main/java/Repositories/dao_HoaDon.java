package Repositories;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Entities.HoaDon;
import Utinities.hibernate_ultil;

public class dao_HoaDon {

	Session ss;
	public dao_HoaDon() {
		//TODO Auto-generated constructor stub
		ss=hibernate_ultil.ss();
	}

	public void save(HoaDon hd) {
		ss.clear();
		Transaction tx=ss.beginTransaction();
		ss.saveOrUpdate(hd);
		tx.commit();
	}
	
	public List<HoaDon> select() {
		Query qr=ss.createQuery("from HoaDon");
		return qr.list();
	}
}
