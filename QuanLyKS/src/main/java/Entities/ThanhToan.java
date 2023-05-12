package Entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ThanhToan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maThanhToan;
	private Double tien;
	private String loaiThanhToan;
	@ManyToOne
	private HoaDon hoadon;
	private Date ngayThanhToan;
	
	public ThanhToan() {
		//TODO Auto-generated constructor stub
	}
	
	public ThanhToan(int maThanhToan, Double tien, String loaiThanhToan, HoaDon hoadon, Date ngayThanhToan) {
		super();
		this.maThanhToan = maThanhToan;
		this.tien = tien;
		this.loaiThanhToan = loaiThanhToan;
		this.hoadon = hoadon;
		this.ngayThanhToan = ngayThanhToan;
	}

	public ThanhToan(Double tien, String loaiThanhToan, HoaDon hoadon, Date ngayThanhToan) {
		super();
		this.tien = tien;
		this.loaiThanhToan = loaiThanhToan;
		this.hoadon = hoadon;
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

	public Date getNgayThanhToan() {
		return ngayThanhToan;
	}


	public void setNgayThanhToan(Date ngayThanhToan) {
		this.ngayThanhToan = ngayThanhToan;
	}

	public HoaDon getHoadon() {
		return hoadon;
	}

	public void setHoadon(HoaDon hoadon) {
		this.hoadon = hoadon;
	}

	
}
