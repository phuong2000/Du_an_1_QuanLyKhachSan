package Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.Id;
import javax.persistence.OneToMany;

	@Entity 
	public class ChucVu {
		@Id
		private String maChucVu;
		private String tenChucVu;
		private String trangThai;
		@OneToMany (mappedBy = "chucVu")
		List<Quyen_cv> listQuyen_cv;
		@OneToMany (mappedBy = "chucVu")
		List<NhanVien> listNhanVien;
		
		public ChucVu(String maChucVu, String tenChucVu, String trangThai, List<Quyen_cv> listQuyen_cv,
				List<NhanVien> listNhanVien) {
			this.maChucVu = maChucVu;
			this.tenChucVu = tenChucVu;
			this.trangThai = trangThai;
			this.listQuyen_cv = listQuyen_cv;
			this.listNhanVien = listNhanVien;
		}
	
		public ChucVu(String maChucVu, String tenChucVu) {
			this.maChucVu = maChucVu;
			this.tenChucVu = tenChucVu;
		}

		public ChucVu() {
			
		}

		public String getMaChucVu() {
			return maChucVu;
		}

		public void setMaChucVu(String maChucVu) {
			this.maChucVu = maChucVu;
		}

		public String getTenChucVu() {
			return tenChucVu;
		}

		public void setTenChucVu(String tenChucVu) {
			this.tenChucVu = tenChucVu;
		}

		public String getTrangThai() {
			return trangThai;
		}

		public void setTrangThai(String trangThai) {
			this.trangThai = trangThai;
		}

		public List<Quyen_cv> getListQuyen_cv() {
			return listQuyen_cv;
		}

		public void setListQuyen_cv(List<Quyen_cv> listQuyen_cv) {
			this.listQuyen_cv = listQuyen_cv;
		}

		public List<NhanVien> getListNhanVien() {
			return listNhanVien;
		}

		public void setListNhanVien(List<NhanVien> listNhanVien) {
			this.listNhanVien = listNhanVien;
		}
	
		
	}