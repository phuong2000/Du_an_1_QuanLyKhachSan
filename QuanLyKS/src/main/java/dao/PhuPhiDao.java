package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import Entities.PhuPhi;
import utils.DB_Connection;

public class PhuPhiDao {
	private List<PhuPhi> _listPhuPhis = new ArrayList<PhuPhi>();

	public void them_sua(PhuPhi PhuPhi) {
		Session session = new DB_Connection().getSession();
		session.beginTransaction();
		session.saveOrUpdate(PhuPhi);
		session.getTransaction().commit();
		session.close();
	}

	public void updateListPhuPhi() {
		Session session = new DB_Connection().getSession();
		session.beginTransaction();
		SQLQuery query = session.createSQLQuery("select * from PhuPhi");
		query.addEntity(PhuPhi.class);
		_listPhuPhis = query.list();
		session.getTransaction().commit();
		session.close();

	}

	public List<PhuPhi> getListPhuPhi() {
		return _listPhuPhis;
	}
}
