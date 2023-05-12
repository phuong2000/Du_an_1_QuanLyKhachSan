package ViewModels;

import java.util.List;

import javax.persistence.OneToMany;

import Entities.Phong;
import Entities.PhuTroi;

public class ModelLoaiPhong {

	private int MaLoaiPhong;
	private String TenLoai;
	private List<ModelPhong> DSPhong;
	private double GiaPhong;
	private double giaQuaDem;
	private double giaTheoGio;
	private String TrangThai;
	private List<PhuTroi> dsphutroi;
	private int SoNguoi;
	
	public ModelLoaiPhong() {
		//TODO Auto-generated constructor stub
	}

	public ModelLoaiPhong(int maLoaiPhong, String tenLoai) {
		MaLoaiPhong = maLoaiPhong;
		TenLoai = tenLoai;
	}
	
	

	public ModelLoaiPhong(int maLoaiPhong, String tenLoai, List<ModelPhong> dSPhong) {
		MaLoaiPhong = maLoaiPhong;
		TenLoai = tenLoai;
		DSPhong = dSPhong;
	}
	
	

	public ModelLoaiPhong(int maLoaiPhong, String tenLoai, List<ModelPhong> dSPhong, double giaPhong, double giaQuaDem,
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

	public List<ModelPhong> getDSPhong() {
		return DSPhong;
	}

	public void setDSPhong(List<ModelPhong> dSPhong) {
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

	public String getTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(String trangThai) {
		TrangThai = trangThai;
	}

	public List<PhuTroi> getDsphutroi() {
		return dsphutroi;
	}

	public void setDsphutroi(List<PhuTroi> dsphutroi) {
		this.dsphutroi = dsphutroi;
	}

	public int getSoNguoi() {
		return SoNguoi;
	}

	public void setSoNguoi(int soNguoi) {
		SoNguoi = soNguoi;
	}
	
	
}
