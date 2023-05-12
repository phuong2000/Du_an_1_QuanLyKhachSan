package Services;

import java.util.ArrayList;
import java.util.List;

import dao.NhanVienDao;
import Entities.ChucVu;
import Entities.SanPhamVaDichVu;
import Repositories.dao_NhanVien;
import ViewModels.ModelChucVu;
import ViewModels.ModelNhanVien;
import Entities.NhanVien;
import Entities.PhieuNhapKho;
import models.ChucVuModel;
import models.SanPhamVaDichVuModel;
import models.NhanVienModel;
import utils.IoCContainer;

public class NhanVienService {
	IoCContainer ioCContainer = new IoCContainer();
	List<NhanVienModel> _listNhanVienModels = new ArrayList<NhanVienModel>();
	NhanVienDao _nhanVienDao = new NhanVienDao();
	NhanVien _nhanVien;
	NhanVienModel _nhanVienModel = new NhanVienModel();
	int _maxID;

	dao_NhanVien daoNhanVien = new dao_NhanVien();

	public static NhanVien modelToEntity(NhanVienModel nhanVienModel) {
		NhanVien nhanVien = new NhanVien();
		nhanVien.setMaNV(nhanVienModel.getMaNV());
		nhanVien.setTenNV(nhanVienModel.getTenNV());
		nhanVien.setSdt(nhanVienModel.getSdt());
		nhanVien.setGioiTinh(nhanVienModel.getGioiTinh());
		nhanVien.setEmail(nhanVienModel.getEmail());
		nhanVien.setChucVu(ChucVuService.modelToEntity(nhanVienModel.getChucVuModel()));
		nhanVien.setTrangThai(nhanVienModel.getTrangThai());
		nhanVien.setCmnd(nhanVienModel.getCmnd());
		nhanVien.setUserName(nhanVienModel.getUserName());
		nhanVien.setPassword(nhanVienModel.getPassword());
		nhanVien.setNgaySinh(nhanVienModel.getNgaySinh());
		return nhanVien;

	}

	public static NhanVienModel entityToModel(NhanVien nhanVien) {
		NhanVienModel nhanVienModel = new NhanVienModel();
		nhanVienModel.setMaNV(nhanVien.getMaNV());
		nhanVienModel.setTenNV(nhanVien.getTenNV());
		nhanVienModel.setSdt(nhanVien.getSdt());
		nhanVienModel.setGioiTinh(nhanVien.getGioiTinh());
		nhanVienModel.setEmail(nhanVien.getEmail());
		if (nhanVien.getChucVu() != null)
			nhanVienModel.setChucVuModel(ChucVuService.entityToModel(nhanVien.getChucVu()));
		nhanVienModel.setTrangThai(nhanVien.getTrangThai());
		nhanVienModel.setCmnd(nhanVien.getCmnd());
		nhanVienModel.setUserName(nhanVien.getUserName());
		nhanVienModel.setPassword(nhanVien.getPassword());
		nhanVienModel.setNgaySinh(nhanVien.getNgaySinh());
		return nhanVienModel;
	}

	public static List<NhanVienModel> listEntitiesToListModel(List<NhanVien> listNhanVien) {
		List<NhanVienModel> listNhanVienModel = new ArrayList<NhanVienModel>();
		for (NhanVien nhanVien : listNhanVien) {
			NhanVienModel nhanVienModel = entityToModel(nhanVien);
			listNhanVienModel.add(nhanVienModel);
		}
		return listNhanVienModel;
	}

	public static List<NhanVien> listModelToListEntities(List<NhanVienModel> listNhanVienModel) {
		List<NhanVien> listNhanVien = new ArrayList<NhanVien>();
		for (NhanVienModel nhanVienModel : listNhanVienModel) {
			NhanVien nhanVien = modelToEntity(nhanVienModel);
			listNhanVien.add(nhanVien);
		}
		return listNhanVien;
	}

	public void them_sua(NhanVienModel nhanVienModel) {
		_nhanVien = modelToEntity(nhanVienModel);
		_nhanVienDao.them_sua(_nhanVien);
		updateListNhanVienModel();
	}

	public void sua(NhanVienModel nhanVienModel) {
		_nhanVien = modelToEntity(nhanVienModel);
		_nhanVienDao.them_sua(_nhanVien);
	}

	public void updateListNhanVienModel() {
		_nhanVienDao.updateListNhanVien();
		List<NhanVien> listNhanVien = _nhanVienDao.getListNhanVien();
		_listNhanVienModels = listEntitiesToListModel(listNhanVien);
		_maxID = _nhanVienDao.getMaxID();
	}

	public void updateListNhanVienModel_active(String trangThai) {
		_nhanVienDao.updateListNhanVien_active();
		List<NhanVien> listNhanVien = _nhanVienDao.getListNhanVien();
		_listNhanVienModels = listEntitiesToListModel(listNhanVien);
		_maxID = _nhanVienDao.getMaxID();
	}

	public int getMaxID() {
		return _maxID;
	}

	public List<NhanVienModel> getListNhanVienModel() {
		return _listNhanVienModels;
	}

	// TIENTVPH18954
	public List<ModelNhanVien> SelectAll() {
		List<ModelNhanVien> listNhanVienModel1 = new ArrayList<ModelNhanVien>();
		List<NhanVien> nhanvien = daoNhanVien.selectAll();
		for (NhanVien x : nhanvien) {
			ModelNhanVien nhanVienModel = new ModelNhanVien(x.getMaNV(), x.getTenNV(), x.getSdt(), x.getGioiTinh(),
					x.getEmail(), new ModelChucVu(x.getChucVu().getMaChucVu(), x.getChucVu().getTenChucVu()),
					x.getTrangThai(), x.getCmnd(), x.getUserName(), x.getPassword(), x.getNgaySinh());
			listNhanVienModel1.add(nhanVienModel);
		}
		return listNhanVienModel1;
	}

	public List<ModelNhanVien> findList(String Email) {
		List<ModelNhanVien> list = new ArrayList<>();
		List<NhanVien> list1 = daoNhanVien.findList(Email);
		for (NhanVien x : list1) {
			list.add(new ModelNhanVien(x.getMaNV(), x.getTenNV(), x.getSdt(), x.getGioiTinh(), x.getEmail(),
					new ModelChucVu(x.getChucVu().getMaChucVu(), x.getChucVu().getTenChucVu()), x.getTrangThai(),
					x.getCmnd(), x.getUserName(), x.getPassword(), x.getNgaySinh()));
		}
		return list;
	}
	
	public void save(ModelNhanVien nhanVien) {
		NhanVien nv = new NhanVien();
		nv.setMaNV(nhanVien.getMaNV());
		nv.setTenNV(nhanVien.getTenNV());
		nv.setSdt(nhanVien.getSdt());
		nv.setGioiTinh(nhanVien.getGioiTinh());
		nv.setEmail(nhanVien.getEmail());
		nv.setChucVu(new ChucVu(nhanVien.getChucVuModel().getMaChucVu(), nhanVien.getChucVuModel().getTenChucVu()));
		nv.setTrangThai(nhanVien.getTrangThai());
		nv.setCmnd(nhanVien.getCmnd());
		nv.setUserName(nhanVien.getUserName());
		nv.setPassword(nhanVien.getPassword());
		nv.setNgaySinh(nhanVien.getNgaySinh());
		daoNhanVien.saveUpdate(nv);
	}
	
	

}