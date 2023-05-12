package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import Entities.KhachTrongPhong;
import utils.DB_Connection;

public class KhachTrongPhongDao {
	private List<KhachTrongPhong> _listKhachTrongPhongs = new ArrayList<KhachTrongPhong>();

	public static void them_sua(KhachTrongPhong KhachTrongPhong) {
		Session session = new DB_Connection().getSession();
		session.beginTransaction();
		session.saveOrUpdate(KhachTrongPhong);
		session.getTransaction().commit();
		session.close();
	}

	public void updateListKhachTrongPhong() {
		Session session = new DB_Connection().getSession();
		session.beginTransaction();
		SQLQuery query = session.createSQLQuery("select * from KhachTrongPhong");
		query.addEntity(KhachTrongPhong.class);
		_listKhachTrongPhongs =  query.list();
		session.getTransaction().commit();
		session.close();

	}

	public List<KhachTrongPhong> getListKhachTrongPhong() {
		return _listKhachTrongPhongs;
	}
	
}
