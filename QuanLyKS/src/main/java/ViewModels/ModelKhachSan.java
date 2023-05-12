package ViewModels;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.OneToMany;

import Entities.Tang;

public class ModelKhachSan {
	private int maKS;
	private String TenKhachSan;
	private String Email;
	private String sdt;
	private String DiaChi;
	private List<ModelTang> DSTang;
	private int gioCheckIn;
	private int gioCheckout;
	private int gioCheckInDem;
	private int gioCheckOutDem;
	private int gio;
	

	public ModelKhachSan(int maKS, String tenKhachSan, String email, String sdt, String diaChi, int gioCheckIn,
			int gioCheckout, int gioCheckInDem, int gioCheckOutDem,int gio) {
		super();
		this.maKS = maKS;
		TenKhachSan = tenKhachSan;
		Email = email;
		this.sdt = sdt;
		DiaChi = diaChi;
		this.gioCheckIn = gioCheckIn;
		this.gioCheckout = gioCheckout;
		this.gioCheckInDem = gioCheckInDem;
		this.gioCheckOutDem = gioCheckOutDem;
		this.gio=gio;
	}

	public ModelKhachSan(int maKS, String tenKhachSan, String email, String sdt, String diaChi,
			List<ModelTang> dSTang) {
		super();
		this.maKS = maKS;
		TenKhachSan = tenKhachSan;
		Email = email;
		this.sdt = sdt;
		DiaChi = diaChi;
		DSTang = dSTang;
	}
	
	public ModelKhachSan(int maKS, String tenKhachSan, String email, String sdt, String diaChi, List<ModelTang> dSTang,
			int gioCheckIn, int gioCheckout, int gioCheckInDem, int gioCheckOutDem,int gio) {
		super();
		this.maKS = maKS;
		TenKhachSan = tenKhachSan;
		Email = email;
		this.sdt = sdt;
		DiaChi = diaChi;
		DSTang = dSTang;
		this.gioCheckIn = gioCheckIn;
		this.gioCheckout = gioCheckout;
		this.gioCheckInDem = gioCheckInDem;
		this.gioCheckOutDem = gioCheckOutDem;
		this.gio=gio;
	}

	public ModelKhachSan() {
	}

	public int getMaKS() {
		return maKS;
	}

	public void setMaKS(int maKS) {
		this.maKS = maKS;
	}

	public String getTenKhachSan() {
		return TenKhachSan;
	}

	public void setTenKhachSan(String tenKhachSan) {
		TenKhachSan = tenKhachSan;
	}

	public int getGio() {
		return gio;
	}

	public void setGio(int gio) {
		this.gio = gio;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public List<ModelTang> getDSTang() {
		return DSTang;
	}

	public void setDSTang(List<ModelTang> dSTang) {
		DSTang = dSTang;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public int getGioCheckIn() {
		return gioCheckIn;
	}

	public void setGioCheckIn(int gioCheckIn) {
		this.gioCheckIn = gioCheckIn;
	}

	public int getGioCheckout() {
		return gioCheckout;
	}

	public void setGioCheckout(int gioCheckout) {
		this.gioCheckout = gioCheckout;
	}

	public int getGioCheckInDem() {
		return gioCheckInDem;
	}

	public void setGioCheckInDem(int gioCheckInDem) {
		this.gioCheckInDem = gioCheckInDem;
	}

	public int getGioCheckOutDem() {
		return gioCheckOutDem;
	}

	public void setGioCheckOutDem(int gioCheckOutDem) {
		this.gioCheckOutDem = gioCheckOutDem;
	}
	
	
}
