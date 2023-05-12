package Services;

import java.util.ArrayList;
import java.util.List;

import Entities.KhachHang;
import Entities.KhachTrongPhong;
import Repositories.dao_khachTrongPhong;
import ViewModels.ModelHoaDon;
import ViewModels.ModelKhachHang;
import ViewModels.ModelKhachTrongPhong;
import ViewModels.ModelLoaiPhong;
import ViewModels.ModelPhong;

public class KhachTrongPhong_services {

	dao_khachTrongPhong dao;

	public KhachTrongPhong_services() {
		dao=new dao_khachTrongPhong();
	}
	
	public List<ModelKhachTrongPhong> getList(){
		List<KhachTrongPhong> list=dao.select();
		List<ModelKhachTrongPhong> List_model=new ArrayList<ModelKhachTrongPhong>();
		for(KhachTrongPhong ktp:list) {
			if(ktp.getHoadon().getTenDoan()==null)ktp.getHoadon().setTenDoan("");
			List<ModelKhachHang> dskh=new ArrayList<ModelKhachHang>();
			for(KhachHang kh:ktp.getHoadon().getDskhachhang()) {
				dskh.add(new ModelKhachHang(kh.getMaKhachHang(),kh.getTenKhachHang(),kh.getCmnd(),kh.getSdt(),kh.getEmail(),kh.getNgaySinh(),kh.getGioiTinh(),kh.getDiaChi()));
			}
			System.out.println("Phu troi dau vao: "+ktp.getPhuTroi2());
			List_model.add(new ModelKhachTrongPhong(ktp.getId(), new ModelHoaDon(ktp.getHoadon().getMaHoaDon(),ktp.getHoadon().getTrangThai(),ktp.getHoadon().getNgayCheckIn(),ktp.getHoadon().getNgayCheckOut(),ktp.getHoadon().getNgayTao(),ktp.getHoadon().getTenDoan(),dskh,ktp.getHoadon().getLoai()), new ModelPhong(ktp.getPhong().getMaPhong(),new ModelLoaiPhong(ktp.getPhong().getLoaiphong().getMaLoaiPhong(),ktp.getPhong().getLoaiphong().getTenLoai() )), ktp.getGiaPhong(),ktp.getPhuTroi(),ktp.getPhuTroi2(), ktp.getPhuPhi(), ktp.getGhiChu()));
		}
		return List_model;
	}
}
