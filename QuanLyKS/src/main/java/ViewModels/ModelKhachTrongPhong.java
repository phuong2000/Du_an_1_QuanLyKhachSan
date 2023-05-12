package ViewModels;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import Entities.HoaDon;
import Entities.KhachHang;
import Entities.Phong;

public class ModelKhachTrongPhong {

	private int id;
	private ModelHoaDon hoadon;
	private ModelPhong phong;
	private float GiaPhong;
	private float PhuPhi;
	private float PhuTroi;
	private float phutroi2;
	private String GhiChu;
	
	public ModelKhachTrongPhong( float giaPhong, float phuPhi,float PhuTroi,float phutroi2, String ghiChu) {
		GiaPhong = giaPhong;
		PhuPhi = phuPhi;
		GhiChu = ghiChu;
		this.PhuTroi=PhuTroi;
		this.phutroi2=phutroi2;
	}
	
	public ModelKhachTrongPhong(int id, ModelHoaDon hoadon, ModelPhong phong, float giaPhong,float PhuTroi,float phutroi2, float phuPhi,
			String ghiChu) {
		super();
		this.id = id;
		this.hoadon = hoadon;
		this.phong = phong;
		GiaPhong = giaPhong;
		PhuPhi = phuPhi;
		GhiChu = ghiChu;
		this.PhuTroi=PhuTroi;
		this.phutroi2=phutroi2;
	}
	
	

	public ModelKhachTrongPhong(int id, ModelPhong phong,ModelHoaDon hd) {
		super();
		this.id = id;
		this.phong = phong;
		this.hoadon=hd;
	}

	public ModelKhachTrongPhong(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ModelHoaDon getHoadon() {
		return hoadon;
	}

	public float getPhutroi2() {
		return phutroi2;
	}

	public void setPhutroi2(float phutroi2) {
		this.phutroi2 = phutroi2;
	}

	public void setHoadon(ModelHoaDon hoadon) {
		this.hoadon = hoadon;
	}

	public ModelPhong getPhong() {
		return phong;
	}

	public void setPhong(ModelPhong phong) {
		this.phong = phong;
	}

	public float getGiaPhong() {
		return GiaPhong;
	}

	public void setGiaPhong(float giaPhong) {
		GiaPhong = giaPhong;
	}

	public float getPhuPhi() {
		return PhuPhi;
	}

	public void setPhuPhi(float phuPhi) {
		PhuPhi = phuPhi;
	}

	public String getGhiChu() {
		return GhiChu;
	}

	public void setGhiChu(String ghiChu) {
		GhiChu = ghiChu;
	}

	public float getPhuTroi() {
		return PhuTroi;
	}

	public void setPhuTroi(float phuTroi) {
		PhuTroi = phuTroi;
	}

	public ModelKhachTrongPhong() {
		
	}
	
	
	
	
}
