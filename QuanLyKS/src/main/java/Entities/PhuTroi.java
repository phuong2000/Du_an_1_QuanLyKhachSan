package Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class PhuTroi {
	@Id
	private int MaPhuTroi;
	private double phuTroi;
	private String loai;
	@ManyToOne
	private LoaiPhong loaiphong;
	public PhuTroi() {
		//TODO Auto-generated constructor stub
	}
	public PhuTroi(int maPhuTroi, double phuTroi, String loai) {
		super();
		MaPhuTroi = maPhuTroi;
		this.phuTroi = phuTroi;
		this.loai = loai;
	}
	
	
	
	public PhuTroi(int maPhuTroi, double phuTroi, String loai, LoaiPhong loaiphong) {
		super();
		MaPhuTroi = maPhuTroi;
		this.phuTroi = phuTroi;
		this.loai = loai;
		this.loaiphong = loaiphong;
	}
	public int getMaPhuTroi() {
		return MaPhuTroi;
	}
	public void setMaPhuTroi(int maPhuTroi) {
		MaPhuTroi = maPhuTroi;
	}
	public double getPhuTroi() {
		return phuTroi;
	}
	public void setPhuTroi(double phuTroi) {
		this.phuTroi = phuTroi;
	}
	public String getLoai() {
		return loai;
	}
	public void setLoai(String loai) {
		this.loai = loai;
	}
	public LoaiPhong getLoaiphong() {
		return loaiphong;
	}
	public void setLoaiphong(LoaiPhong loaiphong) {
		this.loaiphong = loaiphong;
	}

	
}
