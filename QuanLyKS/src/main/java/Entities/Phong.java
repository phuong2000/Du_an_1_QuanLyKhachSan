package Entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Phong {
	@Id
	private int maPhong;
	@ManyToOne
	private Tang tang;
	@ManyToOne
	private LoaiPhong loaiphong;
	@OneToMany(mappedBy = "phong")
	private List<KhachTrongPhong> DSKhachTrongPhong;
	
	public Phong(int maPhong, Tang tang, LoaiPhong loaiphong) {
		this.maPhong = maPhong;
		this.tang = tang;
		this.loaiphong = loaiphong;
	}

	public Phong(int maPhong, Tang tang, LoaiPhong loaiphong, List<KhachTrongPhong> dSKhachTrongPhong) {
		this.maPhong = maPhong;
		this.tang = tang;
		this.loaiphong = loaiphong;
		DSKhachTrongPhong = dSKhachTrongPhong;
	}

	public Phong(int maPhong, Tang tang) {
		this.maPhong = maPhong;
		this.tang = tang;
	}
	
	

	public Phong(int maPhong, LoaiPhong loaiphong) {
		super();
		this.maPhong = maPhong;
		this.loaiphong = loaiphong;
	}

	public Phong(int maPhong, Tang tang, List<KhachTrongPhong> dSKhachTrongPhong) {
		super();
		this.maPhong = maPhong;
		this.tang = tang;
		DSKhachTrongPhong = dSKhachTrongPhong;
	}

	public Phong(int maPhong) {
		super();
		this.maPhong = maPhong;
	}

	public Phong() {
	}

	public int getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(int maPhong) {
		this.maPhong = maPhong;
	}

	public Tang getTang() {
		return tang;
	}

	public void setTang(Tang tang) {
		this.tang = tang;
	}

	public LoaiPhong getLoaiphong() {
		return loaiphong;
	}

	public void setLoaiphong(LoaiPhong loaiphong) {
		this.loaiphong = loaiphong;
	}

	public List<KhachTrongPhong> getDSKhachTrongPhong() {
		return DSKhachTrongPhong;
	}

	public void setDSKhachTrongPhong(List<KhachTrongPhong> dSKhachTrongPhong) {
		DSKhachTrongPhong = dSKhachTrongPhong;
	}

	
	
	
}
