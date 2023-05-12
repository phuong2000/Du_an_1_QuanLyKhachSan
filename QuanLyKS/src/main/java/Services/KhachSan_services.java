package Services;

import java.util.ArrayList;
import java.util.List;

import Entities.KhachSan;
import Entities.KhachTrongPhong;
import Entities.Phong;
import Entities.Tang;
import Repositories.dao_KhachSan;
import ViewModels.ModelKhachSan;
import ViewModels.ModelKhachTrongPhong;
import ViewModels.ModelLoaiPhong;
import ViewModels.ModelPhong;
import ViewModels.ModelTang;

public class KhachSan_services {

	dao_KhachSan dao;
	public KhachSan_services() {
		//TODO Auto-generated constructor stub
		dao=new dao_KhachSan();
	}

	public void themsua(ModelKhachSan ks) {
		dao.themSua(new KhachSan(ks.getMaKS(),ks.getTenKhachSan(),ks.getEmail(),ks.getSdt(),ks.getDiaChi(),ks.getGioCheckIn(),ks.getGioCheckout(),ks.getGioCheckInDem(),ks.getGioCheckOutDem(),ks.getGio()));
	}
	
	public ModelKhachSan getKs() {
		KhachSan ks=dao.getKs();
		if(ks==null) {
			List<ModelTang> t=new ArrayList<ModelTang>();
			themsua(new ModelKhachSan(1,null,null,null,null,t,1400,1200,1800,900,3));
			return getKs();
		}
		return transform(ks);
	}
	
	public KhachSan transform(ModelKhachSan ks) {
		return new KhachSan(ks.getMaKS(),ks.getTenKhachSan(),ks.getEmail(),ks.getSdt(),ks.getDiaChi(),ks.getGioCheckIn(),ks.getGioCheckout(),ks.getGioCheckInDem(),ks.getGioCheckOutDem(),ks.getGio());
	}
	
	public ModelKhachSan transform(KhachSan ks) {
		List<ModelTang> dstang=new ArrayList<ModelTang>();
		for(Tang t:ks.getDSTang()) {
			List<ModelPhong> dsphong=new ArrayList<ModelPhong>();
			ModelKhachSan mks=new ModelKhachSan();
			mks.setMaKS(t.getKhachsan().getMaKS());
			mks.setTenKhachSan(t.getKhachsan().getTenKhachSan());
			mks.setEmail(t.getKhachsan().getEmail());
			mks.setDiaChi(t.getKhachsan().getDiaChi());
			mks.setGioCheckIn(ks.getGioCheckIn());
			mks.setGioCheckout(ks.getGioCheckout());
			mks.setGioCheckInDem(ks.getGioCheckInDem());
			mks.setGioCheckOutDem(ks.getGioCheckOutDem());
			ModelTang tang=new ModelTang(t.getMaTang(),mks,dsphong);
			for(Phong p:t.getDSPhong()) {
				List<ModelKhachTrongPhong> dsktp=new ArrayList<ModelKhachTrongPhong>();
				for(KhachTrongPhong ktp:p.getDSKhachTrongPhong()) {
					dsktp.add(new ModelKhachTrongPhong( ktp.getGiaPhong(), ktp.getPhuTroi(),ktp.getPhuTroi2(),ktp.getPhuPhi(), ktp.getGhiChu()));
				}
				if(p.getLoaiphong()==null)tang.getDSPhong().add(new ModelPhong(p.getMaPhong(),tang,dsktp));
				else if(p.getDSKhachTrongPhong()==null) tang.getDSPhong().add(new ModelPhong(p.getMaPhong(),tang,new ModelLoaiPhong(p.getLoaiphong().getMaLoaiPhong(),p.getLoaiphong().getTenLoai())));
				else {
					tang.getDSPhong().add(new ModelPhong(p.getMaPhong(),tang,new ModelLoaiPhong(p.getLoaiphong().getMaLoaiPhong(),p.getLoaiphong().getTenLoai()),dsktp));
				}
			}
			dstang.add(tang);
		}
		return new ModelKhachSan(ks.getMaKS(),ks.getTenKhachSan(),ks.getEmail(),ks.getSdt(),ks.getDiaChi(),dstang,ks.getGioCheckIn(),ks.getGioCheckout(),ks.getGioCheckInDem(),ks.getGioCheckOutDem(),ks.getGio());
	}
	
	
}
