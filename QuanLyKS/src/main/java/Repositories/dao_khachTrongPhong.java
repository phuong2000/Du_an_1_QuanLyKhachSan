package Repositories;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Entities.KhachTrongPhong;
import Utinities.hibernate_ultil;

public class dao_khachTrongPhong {

	public Session ss;
	public dao_khachTrongPhong() {
		//TODO Auto-generated constructor stub
		ss=hibernate_ultil.ss();
	}

	public void save(KhachTrongPhong ktp) {
		ss.clear();
		Transaction tx= ss.beginTransaction();
		ss.saveOrUpdate(ktp);
		tx.commit();
	}
	
	public List<KhachTrongPhong> select() {
		Query qr= ss.createQuery("from KhachTrongPhong");
		return qr.list();
	}
}
