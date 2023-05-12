package Repositories;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Entities.KhachHang;
import Utinities.hibernate_ultil;

public class dao_KhachHang {

	Session ss;
	public dao_KhachHang() {
		//TODO Auto-generated constructor stub
		ss=hibernate_ultil.ss();
	}

	public List<KhachHang> select(){
		Query qr=ss.createQuery("from KhachHang");
		return qr.list();
	}
	
	public void saveUpdate(KhachHang kh) {
		ss.clear();
		Transaction tx=ss.beginTransaction();
		ss.saveOrUpdate(kh);
		tx.commit();
	}
	
	
}
