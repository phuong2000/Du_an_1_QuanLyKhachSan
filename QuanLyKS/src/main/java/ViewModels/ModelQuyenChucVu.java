/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

/**
 *
 * @author TIENTVPH18954
 */
public class ModelQuyenChucVu {
	private ModelQuyen quyenModel;
	private ModelChucVu chucVuModel;
	
	public ModelQuyenChucVu(ModelQuyen quyenModel, ModelChucVu chucVuModel) {
		this.quyenModel = quyenModel;
		this.chucVuModel = chucVuModel;
	}
	
	public ModelQuyenChucVu() {
	}

	public ModelQuyen getQuyenModel() {
		return quyenModel;
	}

	public void setQuyenModel(ModelQuyen quyenModel) {
		this.quyenModel = quyenModel;
	}

	public ModelChucVu getChucVuModel() {
		return chucVuModel;
	}

	public void setChucVuModel(ModelChucVu chucVuModel) {
		this.chucVuModel = chucVuModel;
	}

	@Override
	public String toString() {
		return "Quyen_cvModel [chucVuModel=" + chucVuModel + "]";
	}
     
}
