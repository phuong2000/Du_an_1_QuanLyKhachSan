package dao;


import java.util.ArrayList;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import Entities.ChucVu;
import utils.DB_Connection;

public class ChucVuDao implements IChucVuDao{
private static ArrayList<ChucVu> _listChucVus = new ArrayList<ChucVu>();
	
	public void them_sua(ChucVu chucVu) {
		Session session = new DB_Connection().getSession();
		session.beginTransaction();
		session.saveOrUpdate(chucVu);
		session.getTransaction().commit();
		session.close();
	}

	public void updateListChucVu() {
		Session session = new DB_Connection().getSession();
		session.beginTransaction();
		SQLQuery query = session.createSQLQuery("select * from chucVu");
		query.addEntity(ChucVu.class);
		_listChucVus = (ArrayList<ChucVu>) query.list();
		session.getTransaction().commit();
		session.close();
		
	}

	public ArrayList<ChucVu> getListChucVu() {
		return _listChucVus;
	}
}
