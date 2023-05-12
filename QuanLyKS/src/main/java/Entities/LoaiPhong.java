package Entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class LoaiPhong {
	@Id
	private int MaLoaiPhong;
	private String TenLoai;
	@OneToMany(mappedBy = "loaiphong")
	private List<Phong> DSPhong;
	private double GiaPhong;
	private double giaQuaDem;
	private double giaTheoGio;
	private String TrangThai;
	@OneToMany(mappedBy = "loaiphong")
	private List<PhuTroi> dsphutroi;
	private int SoNguoi;
	
	public LoaiPhong(int maLoaiPhong, String tenLoai) {
		MaLoaiPhong = maLoaiPhong;
		TenLoai = tenLoai;
	}

	public LoaiPhong(int maLoaiPhong, String tenLoai, List<Phong> dSPhong) {
		MaLoaiPhong = maLoaiPhong;
		TenLoai = tenLoai;
		DSPhong = dSPhong;
	}

	public LoaiPhong(int maLoaiPhong, String tenLoai, List<Phong> dSPhong, double giaPhong, double giaQuaDem,
			double giaTheoGio, String trangThai, List<PhuTroi> dsphutroi, int soNguoi) {
		super();
		MaLoaiPhong = maLoaiPhong;
		TenLoai = tenLoai;
		DSPhong = dSPhong;
		GiaPhong = giaPhong;
		this.giaQuaDem = giaQuaDem;
		this.giaTheoGio = giaTheoGio;
		TrangThai = trangThai;
		this.dsphutroi = dsphutroi;
		SoNguoi = soNguoi;
	}
	
	

	public LoaiPhong(int maLoaiPhong, String tenLoai, double giaPhong, double giaQuaDem, double giaTheoGio,
			String trangThai, List<PhuTroi> dsphutroi, int soNguoi) {
		super();
		MaLoaiPhong = maLoaiPhong;
		TenLoai = tenLoai;
		GiaPhong = giaPhong;
		this.giaQuaDem = giaQuaDem;
		this.giaTheoGio = giaTheoGio;
		TrangThai = trangThai;
		this.dsphutroi = dsphutroi;
		SoNguoi = soNguoi;
	}

	public LoaiPhong() {
	}
	
	public LoaiPhong(int maLoaiPhong, String tenLoai, List<Phong> dSPhong, double giaPhong, double giaQuaDem,
			double giaTheoGio, int soNguoi) {
		super();
		MaLoaiPhong = maLoaiPhong;
		TenLoai = tenLoai;
		DSPhong = dSPhong;
		GiaPhong = giaPhong;
		this.giaQuaDem = giaQuaDem;
		this.giaTheoGio = giaTheoGio;
		SoNguoi = soNguoi;
	}

	public int getMaLoaiPhong() {
		return MaLoaiPhong;
	}

	public void setMaLoaiPhong(int maLoaiPhong) {
		MaLoaiPhong = maLoaiPhong;
	}

	public String getTenLoai() {
		return TenLoai;
	}

	public void setTenLoai(String tenLoai) {
		TenLoai = tenLoai;
	}


	public List<Phong> getDSPhong() {
		return DSPhong;
	}

	public void setDSPhong(List<Phong> dSPhong) {
		DSPhong = dSPhong;
	}

	public double getGiaPhong() {
		return GiaPhong;
	}

	public void setGiaPhong(double giaPhong) {
		GiaPhong = giaPhong;
	}

	public double getGiaQuaDem() {
		return giaQuaDem;
	}

	public void setGiaQuaDem(double giaQuaDem) {
		this.giaQuaDem = giaQuaDem;
	}

	public double getGiaTheoGio() {
		return giaTheoGio;
	}

	public void setGiaTheoGio(double giaTheoGio) {
		this.giaTheoGio = giaTheoGio;
	}

	public int getSoNguoi() {
		return SoNguoi;
	}

	public void setSoNguoi(int soNguoi) {
		SoNguoi = soNguoi;
	}

	public List<PhuTroi> getDsphutroi() {
		return dsphutroi;
	}

	public void setDsphutroi(List<PhuTroi> dsphutroi) {
		this.dsphutroi = dsphutroi;
	}

	public String getTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(String trangThai) {
		TrangThai = trangThai;
	}
	
	
}
