package Repositories;
import java.util.List;

import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Entities.KhachSan;
import Entities.Tang;
import Utinities.hibernate_ultil;
public class dao_KhachSan {

	Session ss;
	public dao_KhachSan() {
		//TODO Auto-generated constructor stub
		ss=hibernate_ultil.ss();
	}

	public void themSua(KhachSan ks) {
		ss.clear();
		Transaction tran= ss.beginTransaction();
		ss.saveOrUpdate(ks);
		tran.commit();
	}
	
	public KhachSan getKs() {
		ss.clear();
		Query qr= ss.createQuery("from KhachSan");
		if(qr.list().size()>0)return (KhachSan)qr.uniqueResult();
		return null;
	}
	
	
}
