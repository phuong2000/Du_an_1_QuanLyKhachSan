package Entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Quyen {
	@Id
	private int MaQUyen;
	private String TenQuyen;
	@OneToMany (mappedBy = "quyen")
	List<Quyen_cv> listQuyen_cv;
	
	public Quyen(int maQUyen, String tenQuyen, List<Quyen_cv> listQuyen_cv) {
		MaQUyen = maQUyen;
		TenQuyen = tenQuyen;
		this.listQuyen_cv = listQuyen_cv;
	}
	
	
	public Quyen(int maQUyen, String tenQuyen) {
		MaQUyen = maQUyen;
		TenQuyen = tenQuyen;
	}


	public Quyen() {
		
	}
	public int getMaQUyen() {
		return MaQUyen;
	}
	public void setMaQUyen(int maQUyen) {
		MaQUyen = maQUyen;
	}
	public String getTenQuyen() {
		return TenQuyen;
	}
	public void setTenQuyen(String tenQuyen) {
		TenQuyen = tenQuyen;
	}
	public List<Quyen_cv> getListQuyen_cv() {
		return listQuyen_cv;
	}
	public void setListQuyen_cv(List<Quyen_cv> listQuyen_cv) {
		this.listQuyen_cv = listQuyen_cv;
	}
	
	
	
}
