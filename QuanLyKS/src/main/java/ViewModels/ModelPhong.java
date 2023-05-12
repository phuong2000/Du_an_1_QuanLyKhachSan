package ViewModels;

import java.util.List;

import javax.persistence.OneToMany;

import Entities.KhachTrongPhong;
import Entities.LoaiPhong;

public class ModelPhong {
	private int maPhong;
	private ModelTang tang;
	private ModelLoaiPhong loaiphong;
	private List<ModelKhachTrongPhong> DSModelKhachTrongPhong;
	
	public ModelPhong(int maPhong, ModelTang tang,List<ModelKhachTrongPhong> DSModelKhachTrongPhong) {
		this.maPhong = maPhong;
		this.tang = tang;
		this.DSModelKhachTrongPhong=DSModelKhachTrongPhong;
	}
	public ModelPhong(int maPhong, ModelTang tang) {
		this.maPhong = maPhong;
		this.tang = tang;
	}

	public ModelPhong(int maPhong, ModelTang tang, ModelLoaiPhong loaiphong) {
		this.maPhong = maPhong;
		this.tang = tang;
		this.loaiphong = loaiphong;
	}
	
	public ModelPhong(int maPhong, ModelLoaiPhong loaiphong) {
		this.maPhong = maPhong;
		this.loaiphong = loaiphong;
	}
	
	public ModelPhong(int maPhong, ModelTang tang, ModelLoaiPhong loaiphong,
			List<ModelKhachTrongPhong> dSModelKhachTrongPhong) {
		super();
		this.maPhong = maPhong;
		this.tang = tang;
		this.loaiphong = loaiphong;
		DSModelKhachTrongPhong = dSModelKhachTrongPhong;
	}
	
	

	public ModelPhong(int maPhong) {
		super();
		this.maPhong = maPhong;
	}

	public ModelPhong() {
	}

	public int getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(int maPhong) {
		this.maPhong = maPhong;
	}

	public ModelTang getTang() {
		return tang;
	}

	public void setTang(ModelTang tang) {
		this.tang = tang;
	}



	public ModelLoaiPhong getLoaiphong() {
		return loaiphong;
	}



	public void setLoaiphong(ModelLoaiPhong loaiphong) {
		this.loaiphong = loaiphong;
	}
	public List<ModelKhachTrongPhong> getDSModelKhachTrongPhong() {
		return DSModelKhachTrongPhong;
	}
	public void setDSModelKhachTrongPhong(List<ModelKhachTrongPhong> dSModelKhachTrongPhong) {
		DSModelKhachTrongPhong = dSModelKhachTrongPhong;
	}

	

}
