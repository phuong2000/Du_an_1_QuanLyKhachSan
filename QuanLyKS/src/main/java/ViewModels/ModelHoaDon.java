package ViewModels;

import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import Entities.KhachTrongPhong;
import Entities.NhanVien;
import Entities.ThanhToan;

public class ModelHoaDon {

	private int maHoaDon;
	private List<ModelKhachTrongPhong> dskhactrongphong;
	private List<ModelKhachHang> dskhachhang;
	private List<ModelThanhToan> dsthanhtoan;
	private ModelNhanVien nhanvien;
	private String TrangThai;
	private Date NgayCheckIn;
	private Date ngayCheckOut;
	private Date NgayTao;
	private String TenDoan;
	private String loai;
	
	public ModelHoaDon() {
		//TODO Auto-generated constructor stub
	}

	public ModelHoaDon(int maHoaDon, List<ModelKhachTrongPhong> dskhactrongphong, List<ModelKhachHang> dskhachhang,
			ModelNhanVien nhanvien, String trangThai, Date ngayCheckIn, Date ngayCheckOut, Date ngayTao) {
		super();
		this.maHoaDon = maHoaDon;
		this.dskhactrongphong = dskhactrongphong;
		this.dskhachhang = dskhachhang;
		this.nhanvien = nhanvien;
		TrangThai = trangThai;
		NgayCheckIn = ngayCheckIn;
		this.ngayCheckOut = ngayCheckOut;
		NgayTao = ngayTao;
	}
	
	public ModelHoaDon(int maHoaDon, String trangThai, Date ngayCheckIn, Date ngayCheckOut, Date ngayTao,String TenDoan,String loai) {
		super();
		this.maHoaDon = maHoaDon;
		TrangThai = trangThai;
		NgayCheckIn = ngayCheckIn;
		this.ngayCheckOut = ngayCheckOut;
		NgayTao = ngayTao;
		this.TenDoan=TenDoan;
		this.loai=loai;
	}
	
	

	public ModelHoaDon(int maHoaDon) {
		super();
		this.maHoaDon = maHoaDon;
	}
	
	

	public ModelHoaDon(int maHoaDon, String trangThai, Date ngayCheckIn, Date ngayCheckOut, Date ngayTao,String TenDoan,List<ModelKhachHang>dskhachhang,String loai) {
		super();
		this.maHoaDon = maHoaDon;
		TrangThai = trangThai;
		NgayCheckIn = ngayCheckIn;
		this.ngayCheckOut = ngayCheckOut;
		NgayTao = ngayTao;
		this.TenDoan=TenDoan;
		this.dskhachhang=dskhachhang;
		this.loai=loai;
	}

	public ModelHoaDon(int maHoaDon, String trangThai, Date ngayCheckIn, Date ngayCheckOut, Date ngayTao,String TenDoan,List<ModelKhachHang>dskhachhang,String loai,List<ModelKhachTrongPhong> dskhactrongphong) {
		super();
		this.maHoaDon = maHoaDon;
		TrangThai = trangThai;
		NgayCheckIn = ngayCheckIn;
		this.ngayCheckOut = ngayCheckOut;
		NgayTao = ngayTao;
		this.TenDoan=TenDoan;
		this.dskhachhang=dskhachhang;
		this.loai=loai;
		this.dskhactrongphong=dskhactrongphong;
	}

	public ModelHoaDon( String trangThai, Date ngayTao) {
		super();
		TrangThai = trangThai;
		NgayTao = ngayTao;
	}

	public int getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(int maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public List<ModelKhachTrongPhong> getDskhactrongphong() {
		return dskhactrongphong;
	}

	public void setDskhactrongphong(List<ModelKhachTrongPhong> dskhactrongphong) {
		this.dskhactrongphong = dskhactrongphong;
	}

	public List<ModelKhachHang> getDskhachhang() {
		return dskhachhang;
	}

	public void setDskhachhang(List<ModelKhachHang> dskhachhang) {
		this.dskhachhang = dskhachhang;
	}

	public ModelNhanVien getNhanvien() {
		return nhanvien;
	}

	public void setNhanvien(ModelNhanVien nhanvien) {
		this.nhanvien = nhanvien;
	}

	public String getTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(String trangThai) {
		TrangThai = trangThai;
	}

	public Date getNgayCheckIn() {
		return NgayCheckIn;
	}

	public void setNgayCheckIn(Date ngayCheckIn) {
		NgayCheckIn = ngayCheckIn;
	}

	public Date getNgayCheckOut() {
		return ngayCheckOut;
	}

	public void setNgayCheckOut(Date ngayCheckOut) {
		this.ngayCheckOut = ngayCheckOut;
	}

	public Date getNgayTao() {
		return NgayTao;
	}

	public void setNgayTao(Date ngayTao) {
		NgayTao = ngayTao;
	}

	public String getTenDoan() {
		return TenDoan;
	}

	public void setTenDoan(String tenDoan) {
		TenDoan = tenDoan;
	}

	public String getLoai() {
		return loai;
	}

	public void setLoai(String loai) {
		this.loai = loai;
	}

	public List<ModelThanhToan> getDsthanhtoan() {
		return dsthanhtoan;
	}

	public void setDsthanhtoan(List<ModelThanhToan> dsthanhtoan) {
		this.dsthanhtoan = dsthanhtoan;
	}

	
}
