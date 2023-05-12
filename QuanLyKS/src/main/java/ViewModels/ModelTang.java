package ViewModels;

import java.util.List;

import Entities.KhachSan;
import Entities.Phong;

public class ModelTang {
	private int maTang;
	private ModelKhachSan khachsan;
	private List<ModelPhong> DSPhong;
	
	public ModelTang(int maTang, ModelKhachSan khachsan, List<ModelPhong> dSPhong) {
		this.maTang = maTang;
		this.khachsan = khachsan;
		DSPhong = dSPhong;
	}
	
	public ModelTang(int maTang) {
		this.maTang = maTang;
	}

	public ModelTang() {
	}

	public int getMaTang() {
		return maTang;
	}

	public void setMaTang(int maTang) {
		this.maTang = maTang;
	}

	public ModelKhachSan getKhachsan() {
		return khachsan;
	}

	public void setKhachsan(ModelKhachSan khachsan) {
		this.khachsan = khachsan;
	}

	public List<ModelPhong> getDSPhong() {
		return DSPhong;
	}

	public void setDSPhong(List<ModelPhong> dSPhong) {
		DSPhong = dSPhong;
	}
	
	
}
