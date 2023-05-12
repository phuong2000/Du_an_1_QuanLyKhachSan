package models;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

import Entities.KhachTrongPhong;

public class PhuPhiModel {
	private int maPhuPhi;
	private KhachTrongPhongModel khachTrongPhongModel;
	private double soTien;
	private String ghiChu;
	
	public PhuPhiModel(int maPhuPhi, KhachTrongPhongModel khachTrongPhongModel, double soTien, String ghiChu) {
		this.maPhuPhi = maPhuPhi;
		this.khachTrongPhongModel = khachTrongPhongModel;
		this.soTien = soTien;
		this.ghiChu = ghiChu;
	}
	
	public PhuPhiModel() {
	}

	public int getMaPhuPhi() {
		return maPhuPhi;
	}

	public void setMaPhuPhi(int maPhuPhi) {
		this.maPhuPhi = maPhuPhi;
	}

	public KhachTrongPhongModel getKhachTrongPhongModel() {
		return khachTrongPhongModel;
	}

	public void setKhachTrongPhongModel(KhachTrongPhongModel khachTrongPhongModel) {
		this.khachTrongPhongModel = khachTrongPhongModel;
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
