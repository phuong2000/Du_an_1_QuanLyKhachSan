package Services;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Entities.KhachTrongPhong;
import Entities.LoaiPhong;
import Entities.Phong;
import Repositories.dao_Phong;
import ViewModels.ModelKhachTrongPhong;
import ViewModels.ModelLoaiPhong;
import ViewModels.ModelPhong;
public class Phong_services {
	dao_Phong dao;
	Tang_services ser_t;
	public Phong_services() {
		ser_t=new Tang_services();
		dao=new dao_Phong();
		//TODO Auto-generated constructor stub
	}

	public void themsua(Collection<ModelPhong> arr) {
		ArrayList<Phong> arr_p=new ArrayList<Phong>();
		for(ModelPhong p:arr) {
			System.out.println(p.getMaPhong());
			if(p.getLoaiphong()==null) {
				arr_p.add(new Phong(p.getMaPhong(),ser_t.tranform(p.getTang())));
			}
			else arr_p.add(new Phong(p.getMaPhong(),ser_t.tranform(p.getTang()),new LoaiPhong(p.getLoaiphong().getMaLoaiPhong(),p.getLoaiphong().getTenLoai())));
		}
		dao.themsua(arr_p);
	}
	
	public void xoa(Component comp,Collection<ModelPhong> arr) {
		ArrayList<Phong> arr_p=new ArrayList<Phong>();
		for(ModelPhong p:arr) {
			List<KhachTrongPhong> list=new ArrayList<KhachTrongPhong>();
			for(ModelKhachTrongPhong k:p.getDSModelKhachTrongPhong()) {
				list.add(new KhachTrongPhong(k.getId()));
			}
			arr_p.add(new Phong(p.getMaPhong(),ser_t.tranform(p.getTang()),list));
		}
		dao.xoa(comp, arr_p);
	}
	
	public List<ModelPhong> SelectAll() {
		List<ModelPhong> listPhongs = new ArrayList<>();
		List<Phong> lst = dao.selectAll();
		System.out.println(lst.size());
		for (Phong x : lst) {
			listPhongs.add(new ModelPhong(x.getMaPhong()));
		}
		return listPhongs;
	}
}
