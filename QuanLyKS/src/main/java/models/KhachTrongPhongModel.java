package models;

import java.sql.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import Entities.DichVuPhong;
import Entities.HoaDon;
import Entities.KhachHang;
import Entities.Phong;
import Entities.PhuPhi;

public class KhachTrongPhongModel {
	private int id;
	private HoaDonModel hoaDonModel;
	private PhongModel phongModel;
	private float GiaPhong;
	private float PhuTroi;
	private float PhuPhi;
	private String GhiChu;
	
	public KhachTrongPhongModel(int id, HoaDonModel hoaDonModel, PhongModel phongModel, float giaPhong, float phuTroi, float phuPhi,
			String ghiChu) {
		this.id = id;
		this.hoaDonModel = hoaDonModel;
		this.phongModel = phongModel;
		GiaPhong = giaPhong;
		PhuTroi = phuTroi;
		PhuPhi = phuPhi;
		GhiChu = ghiChu;
	}
	public KhachTrongPhongModel() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public float getGiaPhong() {
		return GiaPhong;
	}
	public void setGiaPhong(float giaPhong) {
		GiaPhong = giaPhong;
	}
	public float getPhuTroi() {
		return PhuTroi;
	}
	
	public HoaDonModel getHoaDonModel() {
		return hoaDonModel;
	}
	public void setHoaDonModel(HoaDonModel hoaDonModel) {
		this.hoaDonModel = hoaDonModel;
	}
	public PhongModel getPhongModel() {
		return phongModel;
	}
	public void setPhongModel(PhongModel phongModel) {
		this.phongModel = phongModel;
	}
	public void setPhuTroi(float phuTroi) {
		PhuTroi = phuTroi;
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
	
	
	
	
	
	
}
