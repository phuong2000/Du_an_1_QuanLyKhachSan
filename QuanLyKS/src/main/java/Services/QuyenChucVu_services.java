/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;



import Repositories.IRepository;
import Repositories.dao_QuyenChucVu;
import ViewModels.ModelChucVu;
import ViewModels.ModelQuyen;
import ViewModels.ModelQuyenChucVu;
import java.util.ArrayList;
import java.util.List;

import Entities.ChucVu;
import Entities.Quyen;
import Entities.Quyen_cv;

/**
 *
 * @author Admin
 */
public class QuyenChucVu_services{
	    IRepository _iquIRepository;
	    List<ModelQuyenChucVu> _listModelQuyenChucVus;
	    public QuyenChucVu_services() {
	        _iquIRepository  = new dao_QuyenChucVu();
	        _listModelQuyenChucVus = new ArrayList<>();
	                
	    }
	   
	    public List<ModelQuyenChucVu> SelectAll() {
	        _listModelQuyenChucVus = new ArrayList<>();
	        List<Quyen_cv> list = _iquIRepository.SelectAll();
	        for (Quyen_cv q : list) {
	            _listModelQuyenChucVus.add(new ModelQuyenChucVu(new ModelQuyen(q.getQuyen().getMaQUyen(), q.getQuyen().getTenQuyen()), 
	                   new ModelChucVu(q.getChucVu().getMaChucVu(), q.getChucVu().getTenChucVu())));
	        }
	        return _listModelQuyenChucVus;
	    }

	    public ModelQuyenChucVu save(ModelQuyenChucVu entity) {
	        Quyen q = new Quyen(entity.getQuyenModel().getMaQuyen(), entity.getQuyenModel().getTenQuyen());
	        ChucVu cv =  new ChucVu(entity.getChucVuModel().getMaChucVu(), entity.getChucVuModel().getTenChucVu());
	        Quyen_cv quyenChucVu = (Quyen_cv) _iquIRepository.save(new Quyen_cv(q, cv));
	        return  entity;
	    }

	    
	    public ModelQuyenChucVu findById(String ma) {
	        Quyen_cv q = (Quyen_cv) _iquIRepository.findById(ma);
	        return new ModelQuyenChucVu(new ModelQuyen(q.getQuyen().getMaQUyen(), q.getQuyen().getTenQuyen()), 
	                new ModelChucVu(q.getChucVu().getMaChucVu(), q.getChucVu().getTenChucVu()));
	    }

	    public List<ModelQuyenChucVu> findList(String ma) {
	        return _listModelQuyenChucVus;
	    }

	    public String delete(String ma) {
	       return _iquIRepository.delete(ma);
	       
	    }
    
}
