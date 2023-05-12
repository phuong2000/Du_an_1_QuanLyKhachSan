package Entities;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.Session;

import Utinities.hibernate_ultil;
@Entity
public class HoaDon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maHoaDon;
	@OneToMany(mappedBy = "hoadon")
	private List<KhachTrongPhong> dskhactrongphong;
	@ManyToOne
	private NhanVien nhanvien;
	@ManyToMany
	private List<KhachHang> dskhachhang;
	private String TrangThai;
	private Date NgayCheckIn;
	private Date ngayCheckOut;
	private Date NgayTao;
	private String TenDoan;
	@OneToMany (mappedBy = "hoadon")
	private List<ThanhToan> dsthanhtoan;
	private String loai;

	
	public HoaDon( List<KhachTrongPhong> dskhactrongphong, NhanVien nhanvien, List<KhachHang> dskhachhang,
			String trangThai, Date ngayCheckIn, Date ngayCheckOut, Date ngayTao,String loai) {
		super();
		this.dskhactrongphong = dskhactrongphong;
		this.nhanvien = nhanvien;
		this.dskhachhang = dskhachhang;
		TrangThai = trangThai;
		NgayCheckIn = ngayCheckIn;
		this.ngayCheckOut = ngayCheckOut;
		NgayTao = ngayTao;
		this.loai=loai;
	}
	
	public HoaDon(int maHoaDon) {
		super();
		this.maHoaDon = maHoaDon;
	}



	public HoaDon( String trangThai, Date ngayCheckIn, Date ngayCheckOut, Date ngayTao,String TenDoan,List<KhachHang>dskh,String loai) {
		super();
		TrangThai = trangThai;
		NgayCheckIn = ngayCheckIn;
		this.ngayCheckOut = ngayCheckOut;
		NgayTao = ngayTao;
		this.TenDoan=TenDoan;
		this.dskhachhang=dskh;
		this.loai=loai;
	}
	
	public HoaDon(int MaHoaDon, String trangThai, Date ngayCheckIn, Date ngayCheckOut, Date ngayTao,String TenDoan,List<KhachHang>dskh,String loai) {
		super();
		this.maHoaDon=MaHoaDon;
		TrangThai = trangThai;
		NgayCheckIn = ngayCheckIn;
		this.ngayCheckOut = ngayCheckOut;
		NgayTao = ngayTao;
		this.TenDoan=TenDoan;
		this.dskhachhang=dskh;
		this.loai=loai;
	}



	public HoaDon() {
	}
	public int getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(int maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	public List<KhachTrongPhong> getDskhactrongphong() {
		return dskhactrongphong;
	}
	public void setDskhactrongphong(List<KhachTrongPhong> dskhactrongphong) {
		this.dskhactrongphong = dskhactrongphong;
	}
	public NhanVien getNhanvien() {
		return nhanvien;
	}
	public void setNhanvien(NhanVien nhanvien) {
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
	public List<KhachHang> getDskhachhang() {
		return dskhachhang;
	}
	public void setDskhachhang(List<KhachHang> dskhachhang) {
		this.dskhachhang = dskhachhang;
	}

	public String getTenDoan() {
		return TenDoan;
	}

	public void setTenDoan(String tenDoan) {
		TenDoan = tenDoan;
	}

	public List<ThanhToan> getDsthanhtoan() {
		return dsthanhtoan;
	}

	public void setDsthanhtoan(List<ThanhToan> dsthanhtoan) {
		this.dsthanhtoan = dsthanhtoan;
	}

	public String getLoai() {
		return loai;
	}

	public void setLoai(String loai) {
		this.loai = loai;
	}
	
	
	
	
}
