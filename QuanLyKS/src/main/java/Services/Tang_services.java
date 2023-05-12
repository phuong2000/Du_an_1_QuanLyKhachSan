package Services;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Entities.LoaiPhong;
import Entities.Phong;
import Entities.Tang;
import Repositories.dao_KhachSan;
import Repositories.dao_Tang;
import ViewModels.ModelPhong;
import ViewModels.ModelTang;

public class Tang_services {

	dao_Tang dao;
	KhachSan_services ks_ser;
	public Tang_services() {
		//TODO Auto-generated constructor stub
		dao=new dao_Tang();
		ks_ser=new KhachSan_services();
	}
	
	public void themsua(Collection<ModelTang> Tang) {
		List<Tang> t=new ArrayList<Tang>();
		for(ModelTang tang:Tang) {
			t.add(new Tang(tang.getMaTang(),ks_ser.transform(tang.getKhachsan())));
		}
		dao.themsua(t);
	}
	
	public void xoa(Component comp,Collection<ModelTang> Tang) {
		List<Tang> t=new ArrayList<Tang>();
		for(ModelTang tang:Tang) {
			List<Phong> phong=new ArrayList<Phong>();
			for(ModelPhong p:tang.getDSPhong()) {
				phong.add(new Phong(p.getMaPhong(),tranform(tang)));
			}
			t.add(new Tang(tang.getMaTang(),ks_ser.transform(tang.getKhachsan()),phong));
		}
		dao.xoa(comp, t);
	}
	
	public Tang tranform(ModelTang t) {
		return new Tang(t.getMaTang(),ks_ser.transform(t.getKhachsan()));
	}
	
	
	
	
}
