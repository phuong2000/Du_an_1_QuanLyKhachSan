package ViewModels;

import java.util.Date;

import javax.persistence.ManyToOne;

import Entities.KhachTrongPhong;

public class ModelThanhToan {

	private int maThanhToan;
	private Double tien;
	private String loaiThanhToan;
	private ModelHoaDon hoadon;
	private Date ngayThanhToan;
	
	public ModelThanhToan() {
		//TODO Auto-generated constructor stub
	}

	public ModelThanhToan(int maThanhToan, Double tien, String loaiThanhToan, ModelHoaDon hoadon, java.util.Date date) {
		super();
		this.maThanhToan = maThanhToan;
		this.tien = tien;
		this.loaiThanhToan = loaiThanhToan;
		this.hoadon = hoadon;
		this.ngayThanhToan = date;
	}

	public ModelThanhToan(int maThanhToan, Double tien, String loaiThanhToan, Date ngayThanhToan) {
		super();
		this.maThanhToan = maThanhToan;
		this.tien = tien;
		this.loaiThanhToan = loaiThanhToan;
		this.ngayThanhToan = ngayThanhToan;
	}
	
	

	public ModelThanhToan(Double tien, String loaiThanhToan, Date ngayThanhToan) {
		super();
		this.tien = tien;
		this.loaiThanhToan = loaiThanhToan;
		this.ngayThanhToan = ngayThanhToan;
	}

	public int getMaThanhToan() {
		return maThanhToan;
	}

	public void setMaThanhToan(int maThanhToan) {
		this.maThanhToan = maThanhToan;
	}

	public Double getTien() {
		return tien;
	}

	public void setTien(Double tien) {
		this.tien = tien;
	}

	public String getLoaiThanhToan() {
		return loaiThanhToan;
	}

	public void setLoaiThanhToan(String loaiThanhToan) {
		this.loaiThanhToan = loaiThanhToan;
	}

	public ModelHoaDon getHoadon() {
		return hoadon;
	}

	public void setHoadon(ModelHoaDon hoadon) {
		this.hoadon = hoadon;
	}

	public Date getNgayThanhToan() {
		return ngayThanhToan;
	}

	public void setNgayThanhToan(Date ngayThanhToan) {
		this.ngayThanhToan = ngayThanhToan;
	}

	
}
