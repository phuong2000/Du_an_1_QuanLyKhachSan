package Services;

import java.util.ArrayList;
import java.util.List;

import Entities.HoaDon;
import Entities.KhachHang;
import Entities.KhachTrongPhong;
import Entities.ThanhToan;
import Repositories.dao_HoaDon;
import ViewModels.ModelHoaDon;
import ViewModels.ModelKhachHang;
import ViewModels.ModelKhachTrongPhong;
import ViewModels.ModelPhong;
import ViewModels.ModelThanhToan;

public class HoaDon_services {

	dao_HoaDon dao;
	public HoaDon_services() {
		dao=new dao_HoaDon();
		//TODO Auto-generated constructor stub
	}

	public void save(ModelHoaDon hd, boolean save) {
		List<KhachHang>dskh=new ArrayList<KhachHang>();
		for(ModelKhachHang kh:hd.getDskhachhang()) {
			dskh.add(new KhachHang(kh.getMaKhachHang(), kh.getTenKhachHang(), kh.getCMND(), kh.getSDT(), kh.getEmail(), kh.getNgaySinh(), kh.getGioiTinh(), kh.getDiaChi()));
		}
		if(save)dao.save(new HoaDon( hd.getTrangThai(),hd.getNgayCheckIn() , hd.getNgayCheckOut(), hd.getNgayTao(),hd.getTenDoan(),dskh,hd.getLoai()));
		else dao.save(new HoaDon(hd.getMaHoaDon(), hd.getTrangThai(),hd.getNgayCheckIn() , hd.getNgayCheckOut(), hd.getNgayTao(),hd.getTenDoan(),dskh,hd.getLoai()));
	}
	
	public List<ModelHoaDon> getList(){
		List<HoaDon> dshd=dao.select();
		List<ModelHoaDon> list=new ArrayList<ModelHoaDon>();
		
		for(HoaDon x:dshd) {
			List<ModelKhachHang>list_kh=new ArrayList<ModelKhachHang>();
			for(KhachHang kh:x.getDskhachhang()){
				list_kh.add(new ModelKhachHang(kh.getMaKhachHang(),kh.getTenKhachHang(),kh.getCmnd(),kh.getSdt(),kh.getEmail(),kh.getNgaySinh(),kh.getGioiTinh(),kh.getDiaChi()));
			}
			List<ModelKhachTrongPhong> dsktp=new ArrayList<ModelKhachTrongPhong>();
			if(x.getDskhactrongphong()!=null) {
				for(KhachTrongPhong ktp:x.getDskhactrongphong()) {
					dsktp.add(new ModelKhachTrongPhong(ktp.getId(),new ModelPhong(ktp.getPhong().getMaPhong()),new ModelHoaDon(x.getMaHoaDon())));
				}
			}
			list.add(new ModelHoaDon(x.getMaHoaDon(), x.getTrangThai(), x.getNgayCheckIn(), x.getNgayCheckOut(), x.getNgayTao(), x.getTenDoan(),list_kh,x.getLoai(),dsktp));
		}
		return list;
	}
}
