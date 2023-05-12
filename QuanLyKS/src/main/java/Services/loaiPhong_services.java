package Services;

import java.util.ArrayList;
import java.util.List;

import Entities.LoaiPhong;
import Entities.Phong;
import Repositories.dao_loaiPhong;
import Repositories.dao_phutroi;
import ViewModels.ModelLoaiPhong;
import ViewModels.ModelPhong;
import ViewModels.ModelTang;

public class loaiPhong_services {
	dao_loaiPhong dao;
	dao_phutroi dao_pt;
	public loaiPhong_services() {
		//TODO Auto-generated constructor stub
		dao=new dao_loaiPhong();
		dao_pt=new dao_phutroi();
	}
	
	public List<ModelLoaiPhong> getLp(){
		List<ModelLoaiPhong> lp=new ArrayList<ModelLoaiPhong>();
		List<LoaiPhong> list_lp=dao.select();
		for(int i=0;i<list_lp.size();i++) {
			if(list_lp.get(i).getDSPhong()==null) {
				List<ModelPhong> lst_p=new ArrayList<ModelPhong>();
				lp.add(new ModelLoaiPhong(list_lp.get(i).getMaLoaiPhong(),list_lp.get(i).getTenLoai(),lst_p,list_lp.get(i).getGiaPhong(),list_lp.get(i).getGiaQuaDem(),list_lp.get(i).getGiaTheoGio(),list_lp.get(i).getTrangThai(),list_lp.get(i).getDsphutroi(),list_lp.get(i).getSoNguoi()));
			}
			else {
				List<ModelPhong> lst_p=new ArrayList<ModelPhong>();
				for(Phong p:list_lp.get(i).getDSPhong()) {
					lst_p.add(new ModelPhong(p.getMaPhong(), new ModelTang(p.getTang().getMaTang()),new ModelLoaiPhong(p.getLoaiphong().getMaLoaiPhong(),p.getLoaiphong().getTenLoai())));
				}
				lp.add(new ModelLoaiPhong(list_lp.get(i).getMaLoaiPhong(),list_lp.get(i).getTenLoai(),lst_p,list_lp.get(i).getGiaPhong(),list_lp.get(i).getGiaQuaDem(),list_lp.get(i).getGiaTheoGio(),list_lp.get(i).getTrangThai(),list_lp.get(i).getDsphutroi(),list_lp.get(i).getSoNguoi()));
			}
		}
		return lp;
	}
	
	public void save(List<ModelLoaiPhong> list) {
		for(ModelLoaiPhong x:list) {
			dao.save(new LoaiPhong(x.getMaLoaiPhong(), x.getTenLoai() , x.getGiaPhong(), x.getGiaQuaDem(), x.getGiaTheoGio(), x.getTrangThai(), x.getDsphutroi(), x.getSoNguoi()));
		}
	}
}
