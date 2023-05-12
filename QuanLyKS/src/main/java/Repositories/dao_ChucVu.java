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

import Entities.ChucVu;
import Utinities.hibernate_ultil;

/**
 *
 * @author TIENTVPH18954
 */
public class dao_ChucVu implements IRepository<ChucVu>{ 
	Session ss;
	public dao_ChucVu() {
 		//TODO Auto-generated constructor stub
		ss=hibernate_ultil.ss();
	}
	
	
    @Override
    public List<ChucVu> SelectAll() {
       List<ChucVu> listChucVus;
       Query query = ss.createQuery("FROM ChucVu");
       return query.list();
    }

    @Override
    public ChucVu save(ChucVu cv) {
    	ss.clear();
		Transaction tx=ss.beginTransaction();
		ss.saveOrUpdate(cv);
		tx.commit();
		return cv;
    }

    @Override
    public ChucVu findById(String maChucVu) {
        ChucVu chucVu;       
                String hql = "FROM ChucVu WHERE maChucVu = :maChucVu";
                Query query = (Query) ss.createQuery(hql);
                query.setParameter("maChucVu", maChucVu);
                chucVu = (ChucVu) query.list();        
        return chucVu;
    }

    @Override
    public List<ChucVu> findList(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String delete(String maChucVu) {
            Transaction trans = ss.getTransaction();
            trans.begin();
            try {
                String hql = "DELETE Chucvu p WHERE p.maChucVu = :maChucVu";
                Query query = (Query) ss.createQuery(hql);
                query.setParameter("MaChucVu", maChucVu);
                int result = query.executeUpdate();
                if (result == 0) {
                    return "Xoá thành công";
                }
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
               return "Xoá thất bại";
            }
         
       return null;
    }
   
}
