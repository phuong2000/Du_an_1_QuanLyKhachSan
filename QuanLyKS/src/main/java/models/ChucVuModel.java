package models;

import java.io.Serializable;
import java.util.List;



public class ChucVuModel implements Serializable{
	private String maChucVu;
	private String tenChucVu;
	private String trangThai;
	
	
	
	public ChucVuModel(String maChucVu2, String tenChucVu, String trangThai) {
		this.maChucVu = maChucVu2;
		this.tenChucVu = tenChucVu;
		this.trangThai = trangThai;
	}
	public ChucVuModel() {
		
	}

	public String getMaChucVu() {
		return maChucVu;
	}
	public void setMaChucVu(String maChucVu) {
		this.maChucVu = maChucVu;
	}
	public String getTenChucVu() {
		return tenChucVu;
	}
	public void setTenChucVu(String tenChucVu) {
		this.tenChucVu = tenChucVu;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	
	
}
