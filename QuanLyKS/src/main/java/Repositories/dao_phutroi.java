package Repositories;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Entities.PhuTroi;
import Utinities.hibernate_ultil;
public class dao_phutroi {
	public Session ss;
	public dao_phutroi() {
		//TODO Auto-generated constructor stub
		ss=hibernate_ultil.ss();
	}

	public void save(List<PhuTroi> list) {
		ss.clear();
		for(PhuTroi x:list) {
			Transaction tx= ss.beginTransaction();
			System.out.println(x.getMaPhuTroi());
			ss.saveOrUpdate(x);
			tx.commit();
		}
	}
	
	public void xoa() {
		Query qr= ss.createQuery("delete PhuTroi");
		qr.executeUpdate();
	}
}
