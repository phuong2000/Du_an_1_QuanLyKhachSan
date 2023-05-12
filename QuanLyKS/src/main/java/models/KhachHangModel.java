package models;

import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import Entities.DichVuPhong;
import Entities.KhachTrongPhong;

public class KhachHangModel {
	private String maKhachHang;
	private String tenKhachHang;
	private String sdt;
	private String email;
	private String cmnd;
	private Date ngaySinh;
	private String gioiTinh;
	private String diaChi;
	
	public KhachHangModel(String maKhachHang, String tenKhachHang, String sdt, String email, String cmnd, Date ngaySinh,
			String gioiTinh, String diaChi) {
		this.maKhachHang = maKhachHang;
		this.tenKhachHang = tenKhachHang;
		this.sdt = sdt;
		this.email = email;
		this.cmnd = cmnd;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
	}
	
	public KhachHangModel() {
	}

	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getTenKhachHang() {
		return tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	@Override
	public String toString() {
		return this.getMaKhachHang() +"|"+ this.getTenKhachHang() +"|"+ this.getSdt() +"|"+ this.getEmail() +"|"+ this.getCmnd() +"|"+ this.getNgaySinh() +"|"+ this.getGioiTinh() +"|"+  this.getDiaChi();
				
	}



	

}
