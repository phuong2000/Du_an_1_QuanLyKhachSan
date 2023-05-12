/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;


import java.util.List;
import javax.persistence.TypedQuery;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Entities.Quyen;
import Utinities.hibernate_ultil;

/**
 *
 * @author TIENTVPH18954
 */
public class dao_Quyen implements IRepository<Quyen> {

	Session ss;

	public dao_Quyen() {
		// TODO Auto-generated constructor stub
		ss = hibernate_ultil.ss();
	}

	@Override
	public List<Quyen> SelectAll() {
		List<Quyen> quyens;
		try {
			String hql = "FROM Quyen";
			Query query = (Query) ss.createQuery(hql);
			quyens = query.list();
			return quyens;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Quyen save(Quyen quyen) {
		ss.clear();
		Transaction tx = ss.beginTransaction();
		ss.saveOrUpdate(quyen);
		tx.commit();
		return quyen;

	}

	@Override
	public Quyen findById(String MaQUyen) {
		Quyen quyen;
		try {
			String hql = "FROM Quyen WHERE MaQUyen = :MaQUyen";
			Query query = (Query) ss.createQuery(hql);
			query.setParameter("MaQUyen", MaQUyen);
			quyen = (Quyen) query.list();
		} catch (Exception e) {
			quyen = null;
		}
		return quyen;
	}

	@Override
	public List<Quyen> findList(String ma) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public String delete(String MaQUyen) {
		Transaction trans = ss.getTransaction();
		trans.begin();
		try {
			String hql = "DELETE Quyen p WHERE p.MaQUyen = :MaQUyen";
			Query query = (Query) ss.createQuery(hql);
			query.setParameter("MaQUyen", MaQUyen);
			int result = query.executeUpdate();
			if (result == 0) {
				return "Xoá thành công";
			}
			trans.commit();
		} catch (Exception e) {
			return "Xoá thất bại";
		}
		return "Thành công";
	}

}
