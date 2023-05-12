package ViewModels;

import java.util.Date;
import java.util.List;

import javax.persistence.ManyToMany;

import Entities.HoaDon;

public class ModelKhachHang {

	private String MaKhachHang;
	private String TenKhachHang;
	private String CMND;
	private String SDT;
	private String Email;
	private Date NgaySinh;
	private String GioiTinh;
	private String DiaChi;
	private List<ModelHoaDon> dshoadon;
	
	public ModelKhachHang() {
		//TODO Auto-generated constructor stub
	}

	
	
	public ModelKhachHang(String maKhachHang) {
		super();
		MaKhachHang = maKhachHang;
	}



	public ModelKhachHang(String maKhachHang, String tenKhachHang, String cMND, String sDT, String email, Date ngaySinh,
			String gioiTinh, String diaChi, List<ModelHoaDon> dshoadon) {
		super();
		MaKhachHang = maKhachHang;
		TenKhachHang = tenKhachHang;
		CMND = cMND;
		SDT = sDT;
		Email = email;
		NgaySinh = ngaySinh;
		GioiTinh = gioiTinh;
		DiaChi = diaChi;
		this.dshoadon = dshoadon;
	}
	
	public ModelKhachHang(String maKhachHang, String tenKhachHang, String cMND, String sDT, String email, Date ngaySinh,
			String gioiTinh, String diaChi) {
		MaKhachHang = maKhachHang;
		TenKhachHang = tenKhachHang;
		CMND = cMND;
		SDT = sDT;
		Email = email;
		NgaySinh = ngaySinh;
		GioiTinh = gioiTinh;
		DiaChi = diaChi;
	}

	public String getMaKhachHang() {
		return MaKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		MaKhachHang = maKhachHang;
	}

	public String getTenKhachHang() {
		return TenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		TenKhachHang = tenKhachHang;
	}

	public String getCMND() {
		return CMND;
	}

	public void setCMND(String cMND) {
		CMND = cMND;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Date getNgaySinh() {
		return NgaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		NgaySinh = ngaySinh;
	}

	public String getGioiTinh() {
		return GioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public List<ModelHoaDon> getDshoadon() {
		return dshoadon;
	}

	public void setDshoadon(List<ModelHoaDon> dshoadon) {
		this.dshoadon = dshoadon;
	}




	
}
