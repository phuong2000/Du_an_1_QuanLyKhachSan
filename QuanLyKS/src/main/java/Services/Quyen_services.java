/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;


import Repositories.IRepository;
import Repositories.dao_Quyen;
import ViewModels.ModelQuyen;
import models.QuyenModel;

import java.util.ArrayList;
import java.util.List;

import Entities.Quyen;

/**
 *
 * @author TIENTVPH18954
 */
public class Quyen_services {
	    IRepository _iquIRepository;
	    List<ModelQuyen> _lstModelQuyens;

	    public Quyen_services() {
	        _iquIRepository = new dao_Quyen();
	        _lstModelQuyens = new ArrayList<>();
	    }
	    
	    

	    public List<ModelQuyen> SelectAll() {
	       _lstModelQuyens = new ArrayList<>();
	       List<Quyen> lstQuyens = _iquIRepository.SelectAll();
	        for (Quyen x : lstQuyens) {
	            _lstModelQuyens.add(new ModelQuyen(x.getMaQUyen(), x.getTenQuyen()));
	        }
	        return _lstModelQuyens;
	    }


	    public ModelQuyen save(ModelQuyen quyen) {
	        Quyen x = (Quyen) _iquIRepository.save(new Quyen(quyen.getMaQuyen(), quyen.getTenQuyen()));
	        return new ModelQuyen(x.getMaQUyen(), x.getTenQuyen());
	    }

	    
	    public ModelQuyen findById(String ma) {
	        Quyen x = (Quyen) _iquIRepository.findById(ma);
	        return new ModelQuyen(x.getMaQUyen(), x.getTenQuyen());
	    }

	    
	    public List<ModelQuyen> findList(String ma) {
	        return _lstModelQuyens;
	    }

	  
	    public String delete(String ma) {
	        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	    }
   
    
}
