package Entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Tang {
	@Id
	private int maTang;
	@ManyToOne
	private KhachSan khachsan;
	@OneToMany(mappedBy = "tang")
	private List<Phong> DSPhong;
	
	public Tang(int maTang, KhachSan khachsan) {
		this.maTang = maTang;
		this.khachsan = khachsan;
	}
	
	public Tang(int maTang, KhachSan khachsan, List<Phong> dSPhong) {
		this.maTang = maTang;
		this.khachsan = khachsan;
		DSPhong = dSPhong;
	}

	public Tang() {
	}

	public int getMaTang() {
		return maTang;
	}

	public void setMaTang(int maTang) {
		this.maTang = maTang;
	}

	public KhachSan getKhachsan() {
		return khachsan;
	}

	public void setKhachsan(KhachSan khachsan) {
		this.khachsan = khachsan;
	}

	public List<Phong> getDSPhong() {
		return DSPhong;
	}

	public void setDSPhong(List<Phong> dSPhong) {
		DSPhong = dSPhong;
	}
	
	
}
