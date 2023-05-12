/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;


import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Entities.Quyen_cv;
import Utinities.hibernate_ultil;

/**
 *
 * @author TIENTVPH18954
 */
public class dao_QuyenChucVu implements IRepository<Quyen_cv> {

	Session ss;
	public dao_QuyenChucVu() {
 		//TODO Auto-generated constructor stub
		ss=hibernate_ultil.ss();
	}
	
    @Override
    public List<Quyen_cv> SelectAll() {
        List<Quyen_cv> listQuyenChucVus;
        try {
            String hql = "FROM Quyen_cv";
            Query query = (Query) ss.createQuery(hql);
            listQuyenChucVus = query.list();
            return listQuyenChucVus;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public Quyen_cv save(Quyen_cv entity) {
    	ss.clear();
		Transaction tx= ss.beginTransaction();
		ss.saveOrUpdate(entity);
		tx.commit();
		return entity;
    }

    @Override
    public Quyen_cv findById(String chucVu) {
    	Quyen_cv quyenChucVu;
     
            try {
                String hql = "FROM Quyen_cv WHERE chucVu = :chucVu";
                Query query = (Query) ss.createQuery(hql);
                query.setParameter("chucVu", chucVu);
                quyenChucVu = (Quyen_cv) query.list();
            } catch (Exception e) {
                quyenChucVu = null;
            }
        return quyenChucVu;
    }

    @Override
    public List<Quyen_cv> findList(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }

    @Override
    public String delete(String ma) {   
            Query query = (Query) ss.createQuery("DELETE FROM Quyen_cv WHERE chucVu = '" + ma + "' ");
            query.executeUpdate();
        return null;
    }

}
