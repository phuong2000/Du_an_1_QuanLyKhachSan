package Services;

import java.util.ArrayList;
import java.util.List;

import dao.ChucVuDao;
import Entities.ChucVu;
import Repositories.IRepository;
import Repositories.dao_ChucVu;
import ViewModels.ModelChucVu;
import models.ChucVuModel;

public class ChucVuService {
	List<ChucVuModel> _listChucVuModels = new ArrayList<ChucVuModel>();
	ChucVuDao _chucVuDao = new ChucVuDao();
	ChucVu _chucVu;
	ChucVuModel _chucModel;

	// TIENTVPH18954
	IRepository _chuIRepository;
	List<ChucVuModel> listChucVus;

	public ChucVuService() {
		_chuIRepository = new dao_ChucVu();
		listChucVus = new ArrayList<>();
	}

	public List<ModelChucVu> SelectAll() {
		List<ModelChucVu> listChucVus = new ArrayList<>();
		List<ChucVu> lst = _chuIRepository.SelectAll();
		System.out.println(lst.size());
		for (ChucVu x : lst) {
			listChucVus.add(new ModelChucVu(x.getMaChucVu(), x.getTenChucVu()));
		}
		return listChucVus;
	}

	public ModelChucVu save(ModelChucVu chucVu) {
		ChucVu cv = (ChucVu) _chuIRepository.save(new ChucVu(chucVu.getMaChucVu(), chucVu.getTenChucVu()));
		return chucVu;
	}

	public ModelChucVu findById(String ma) {
		ChucVu x = (ChucVu) _chuIRepository.findById(ma);
		return new ModelChucVu(x.getMaChucVu(), x.getTenChucVu());
	}

	public List<ChucVuModel> findList(String ma) {
		return listChucVus;
	}

	// end TIENTVPH18954.

	public static ChucVu modelToEntity(ChucVuModel chucVuModel) {
		ChucVu chucVu = new ChucVu();
		chucVu.setMaChucVu(chucVuModel.getMaChucVu());
		chucVu.setTenChucVu(chucVuModel.getTenChucVu());
		chucVu.setTrangThai(chucVuModel.getTrangThai());
		return chucVu;
	}

	public static ChucVuModel entityToModel(ChucVu chucVu) {
		ChucVuModel chucVuModel = new ChucVuModel();
		chucVuModel.setMaChucVu(chucVu.getMaChucVu());
		chucVuModel.setTenChucVu(chucVu.getTenChucVu());
		chucVuModel.setTrangThai(chucVu.getTrangThai());
		return chucVuModel;
	}

	public static List<ChucVuModel> listEntitiesToListModel(List<ChucVu> listChucVu) {
		List<ChucVuModel> listChucVuModel = new ArrayList<ChucVuModel>();
		for (ChucVu chucVu : listChucVu) {
			ChucVuModel chucVuModel = entityToModel(chucVu);
			listChucVuModel.add(chucVuModel);
		}
		return listChucVuModel;
	}

	public static List<ChucVu> listModelToListEntities(List<ChucVuModel> listChucVuModel) {
		List<ChucVu> listChucVu = new ArrayList<ChucVu>();
		for (ChucVuModel chucVuModel : listChucVuModel) {
			ChucVu chucVu = modelToEntity(chucVuModel);
			listChucVu.add(chucVu);
		}
		return listChucVu;
	}

	public void them_sua(ChucVuModel chucVuModel) {
		_chucVu = modelToEntity(chucVuModel);
		_chucVuDao.them_sua(_chucVu);
		_listChucVuModels.add(chucVuModel);
	}

	public void updateListChucVuModel() {
		_chucVuDao.updateListChucVu();
		ArrayList<ChucVu> listChucVu = _chucVuDao.getListChucVu();
		_listChucVuModels = listEntitiesToListModel(listChucVu);

	}

	public List<ChucVuModel> getListChucVuModel() {
		return _listChucVuModels;
	}

}