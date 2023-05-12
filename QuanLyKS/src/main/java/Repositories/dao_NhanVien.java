package Repositories;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Entities.ChucVu;
import Entities.NhanVien;
import Utinities.hibernate_ultil;
import utils.DB_Connection;
import utils.IoCContainer;

public class dao_NhanVien {
	private static ArrayList<NhanVien> _listNhanViens = new ArrayList<NhanVien>();
	private int maxID;

	// TIENTVPH18954
	Session ss;

	public dao_NhanVien() {
		// TODO Auto-generated constructor stub
		ss = hibernate_ultil.ss();
	}

	public List<NhanVien> selectAll() {
		List<NhanVien> lisNhanVien;
		Query query = ss.createQuery("FROM NhanVien");
		return query.list();
	}

	public List<NhanVien> findList(String email) {
		List<NhanVien> listnhanvien;
		Query query = ss.createQuery("FROM NhanVien WHERE email = '" + email + "'");
		listnhanvien = query.list();
		return listnhanvien;
	}
	
	public void saveUpdate(NhanVien nv) {
		ss.clear();
		Transaction tx= ss.beginTransaction();
		ss.saveOrUpdate(nv);
		tx.commit();
	}
	// END TIENTVPH18954

	public void them_sua(NhanVien nhanVien) {
		Session session = new DB_Connection().getSession();
		session.beginTransaction();
		session.saveOrUpdate(nhanVien);
		session.getTransaction().commit();
		session.close();
	}

	public List<NhanVien> getList(String user, String pass) {
		Session session = new DB_Connection().getSession();
		Query qr = session.createQuery(
				"from NhanVien where userName='" + user + "' and password='" + pass + "' and trangthai='Hoat Dong'");
		return qr.list();
	}

	public void updateListNhanVien() {
		Session session = new DB_Connection().getSession();
		session.beginTransaction();
		SQLQuery query = session.createSQLQuery("select * from nhanVien");
		query.addEntity(NhanVien.class);
		_listNhanViens = (ArrayList<NhanVien>) query.list();
		session.getTransaction().commit();
		session.close();
		getMaxID(_listNhanViens);
	}

	public void updateListNhanVien_active() {
		Session session = new DB_Connection().getSession();
		session.beginTransaction();
		SQLQuery query = session.createSQLQuery("select * from nhanVien where trangthai = 'Hoat Dong'");
		query.addEntity(NhanVien.class);
		_listNhanViens = (ArrayList<NhanVien>) query.list();
		session.getTransaction().commit();
		session.close();
		getMaxID(_listNhanViens);
	}

	public void getMaxID(ArrayList<NhanVien> listNhanVien) {
		if (listNhanVien.size() == 0) {
			maxID = 1;
		} else {
			maxID = Integer.parseInt(listNhanVien.get((listNhanVien.size() - 1)).getMaNV());
			maxID++;
		}
	}

	public ArrayList<NhanVien> getListNhanVien() {
		return _listNhanViens;
	}

	public int getMaxID() {
		return maxID;
	}

}
