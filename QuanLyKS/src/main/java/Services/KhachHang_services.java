package Services;

import java.util.ArrayList;
import java.util.List;

import Entities.KhachHang;
import Repositories.dao_KhachHang;
import ViewModels.ModelKhachHang;
import ViewModels.ModelKhachTrongPhong;

public class KhachHang_services {

	dao_KhachHang dao;
	public KhachHang_services() {
		//TODO Auto-generated constructor stub
		dao=new dao_KhachHang();
	}

	public List<ModelKhachHang> getList(){
		List<KhachHang> arr=dao.select();
		List<ModelKhachHang> lst_kh=new ArrayList<ModelKhachHang>();
		for(KhachHang kh:arr) {
			lst_kh.add(new ModelKhachHang(kh.getMaKhachHang(),kh.getTenKhachHang(),kh.getCmnd(),kh.getSdt(),kh.getEmail(),kh.getNgaySinh(),kh.getGioiTinh(),kh.getDiaChi()));
		}
		return lst_kh;
	}
	
	public void themsua(ModelKhachHang kh) {
		dao.saveUpdate(new KhachHang(kh.getCMND(), kh.getTenKhachHang(), kh.getSDT(), kh.getEmail(), kh.getCMND(), kh.getNgaySinh(), kh.getGioiTinh(), kh.getDiaChi()));
	}
}
