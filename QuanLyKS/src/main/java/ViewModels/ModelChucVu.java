/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;


import java.util.List;


public class ModelChucVu {
    private String MaChucVu;
    private String TenChucVu;
    private String trangThai;

    public ModelChucVu() {
    }

    public ModelChucVu(String MaChucVu, String TenChucVu) {
        this.MaChucVu = MaChucVu;
        this.TenChucVu = TenChucVu;
    }

	public ModelChucVu(String maChucVu, String tenChucVu, String trangThai) {
		MaChucVu = maChucVu;
		TenChucVu = tenChucVu;
		this.trangThai = trangThai;
	}

	public String getMaChucVu() {
		return MaChucVu;
	}

	public void setMaChucVu(String maChucVu) {
		MaChucVu = maChucVu;
	}

	public String getTenChucVu() {
		return TenChucVu;
	}

	public void setTenChucVu(String tenChucVu) {
		TenChucVu = tenChucVu;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
    
    
   
}
