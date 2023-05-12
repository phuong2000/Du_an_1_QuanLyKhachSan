package Entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import Entities.DichVuPhong;
@Entity
public class KhachTrongPhong implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private HoaDon hoadon;
	@ManyToOne
	private Phong phong;
	@OneToMany (mappedBy = "khachTrongPhong")
	private List<DichVuPhong> dsdichvu;
	private float GiaPhong;
	private float PhuTroi;
	@OneToMany (mappedBy = "khachTrongPhong")
	private List<PhuPhi> dsPhuPhi;
	private float PhuPhi;
	private String GhiChu;
	
	public KhachTrongPhong(int id, HoaDon hoadon, Phong phong, List<DichVuPhong> dsdichvu, float giaPhong,
			float phuTroi, List<Entities.PhuPhi> dsPhuPhi, float phuPhi, String ghiChu) {
		this.id = id;
		this.hoadon = hoadon;
		this.phong = phong;
		this.dsdichvu = dsdichvu;
		GiaPhong = giaPhong;
		PhuTroi = phuTroi;
		this.dsPhuPhi = dsPhuPhi;
		PhuPhi = phuPhi;
		GhiChu = ghiChu;
	}

	public KhachTrongPhong(int id, HoaDon hoadon, Phong phong, float giaPhong,float PhuTroi, float phuPhi,
			String ghiChu) {
		super();
		this.id = id;
		this.hoadon = hoadon;
		this.phong = phong;
		GiaPhong = giaPhong;
		PhuPhi = phuPhi;
		GhiChu = ghiChu;
		this.PhuTroi=PhuTroi;
	}
	
	public KhachTrongPhong(int id, HoaDon hoadon, Phong phong) {
		super();
		this.id = id;
		this.hoadon = hoadon;
		this.phong = phong;
	}



	public KhachTrongPhong(HoaDon hoadon, Phong phong, float giaPhong,float PhuTroi, float phuPhi, String ghiChu) {
		super();
		this.hoadon = hoadon;
		this.phong = phong;
		GiaPhong = giaPhong;
		PhuPhi = phuPhi;
		GhiChu = ghiChu;
		this.PhuTroi=PhuTroi;
	}



	public KhachTrongPhong(int id) {
		super();
		this.id = id;
	}

	

	public KhachTrongPhong() {
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public HoaDon getHoadon() {
		return hoadon;
	}
	
	public List<DichVuPhong> getDsdichvu() {
		return dsdichvu;
	}

	public void setDsdichvu(List<DichVuPhong> dsdichvu) {
		this.dsdichvu = dsdichvu;
	}

	public List<PhuPhi> getDsPhuPhi() {
		return dsPhuPhi;
	}

	public void setDsPhuPhi(List<PhuPhi> dsPhuPhi) {
		this.dsPhuPhi = dsPhuPhi;
	}

	public void setHoadon(HoaDon hoadon) {
		this.hoadon = hoadon;
	}
	public Phong getPhong() {
		return phong;
	}
	public void setPhong(Phong phong) {
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

	public float getPhuTroi2() {
		return PhuTroi2;
	}

	public void setPhuTroi2(float phuTroi2) {
		PhuTroi2 = phuTroi2;
	}
	
	private float PhuTroi2;
	public KhachTrongPhong(int id, HoaDon hoadon, Phong phong, float giaPhong,float PhuTroi,float phutroi2, float phuPhi,
			String ghiChu) {
		super();
		this.id = id;
		this.hoadon = hoadon;
		this.phong = phong;
		GiaPhong = giaPhong;
		PhuPhi = phuPhi;
		GhiChu = ghiChu;
		this.PhuTroi=PhuTroi;
		this.PhuTroi2=phutroi2;
	}
	public KhachTrongPhong(HoaDon hoadon, Phong phong, float giaPhong,float PhuTroi,float phutroi2, float phuPhi, String ghiChu) {
		super();
		this.hoadon = hoadon;
		this.phong = phong;
		GiaPhong = giaPhong;
		PhuPhi = phuPhi;
		GhiChu = ghiChu;
		this.PhuTroi=PhuTroi;
		this.PhuTroi2=phutroi2;
	}
}
