package Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PhuPhi {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int maPhuPhi;
	@ManyToOne
	private KhachTrongPhong khachTrongPhong;
	private double soTien;
	private String ghiChu;
	
	public PhuPhi(int maPhuPhi, KhachTrongPhong khachTrongPhong, double soTien, String ghiChu) {
		this.maPhuPhi = maPhuPhi;
		this.khachTrongPhong = khachTrongPhong;
		this.soTien = soTien;
		this.ghiChu = ghiChu;
	}
	public PhuPhi() {
	}
	public int getMaPhuPhi() {
		return maPhuPhi;
	}
	public void setMaPhuPhi(int maPhuPhi) {
		this.maPhuPhi = maPhuPhi;
	}
	public KhachTrongPhong getKhachTrongPhong() {
		return khachTrongPhong;
	}
	public void setKhachTrongPhong(KhachTrongPhong khachTrongPhong) {
		this.khachTrongPhong = khachTrongPhong;
	}
	public double getSoTien() {
		return soTien;
	}
	public void setSoTien(double soTien) {
		this.soTien = soTien;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	
	
}
