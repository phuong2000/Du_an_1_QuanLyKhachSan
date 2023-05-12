package Services;

import java.util.ArrayList;
import java.util.List;

import Entities.ChucVu;
import Entities.Quyen_cv;
import dao.ChucVuDao;
import dao.Quyen_cvDao;
import models.ChucVuModel;
import models.Quyen_cvModel;

public class Quyen_CVService {
	private List<Quyen_cvModel> _listQuyen_cvModels = new ArrayList<Quyen_cvModel>();
	private Quyen_cvDao _quyen_cvDao = new Quyen_cvDao();
	private Quyen_cv _quyen_cv;
	private Quyen_cvModel _quyen_cvModel;
	
	public static Quyen_cv modelToEntity(Quyen_cvModel quyen_cvModel) {
		Quyen_cv quyen_cv1 = new Quyen_cv();
		quyen_cv1.setQuyen(QuyenService.modelToEntity(quyen_cvModel.getQuyenModel()));
		quyen_cv1.setChucVu(ChucVuService.modelToEntity(quyen_cvModel.getChucVuModel()));
		return quyen_cv1;
	}

	public static Quyen_cvModel entityToModel(Quyen_cv quyen_cv) {
		Quyen_cvModel quyen_cvModel = new Quyen_cvModel();
		quyen_cvModel.setQuyenModel(QuyenService.entityToModel(quyen_cv.getQuyen()));
		quyen_cvModel.setChucVuModel(ChucVuService.entityToModel(quyen_cv.getChucVu()));
		return quyen_cvModel;
	}
	
	
	
	public static List<Quyen_cvModel> listEntitiesToListModel (List<Quyen_cv> listQuyen_cvs){
		List<Quyen_cvModel> listQuyen_cvModel = new ArrayList<Quyen_cvModel>();
		for (Quyen_cv quyen_cv : listQuyen_cvs) {
			Quyen_cvModel quyen_cvModel = entityToModel(quyen_cv);
			listQuyen_cvModel.add(quyen_cvModel);
		}
		return listQuyen_cvModel;
	}
	
	public static List<Quyen_cv> listModelToListEntities (List<Quyen_cvModel> listQuyen_cvModel){
		List<Quyen_cv> listQuyen_cv = new ArrayList<Quyen_cv>();
		for (Quyen_cvModel quyen_cvModel : listQuyen_cvModel) {
			Quyen_cv quyen_cv = modelToEntity(quyen_cvModel);
			listQuyen_cv.add(quyen_cv);
		}
		return listQuyen_cv;
	}
	
	public void them_sua(Quyen_cvModel quyen_cvModel) {
		_quyen_cv = modelToEntity(quyen_cvModel);
		_quyen_cvDao.them_sua(_quyen_cv);
	}

	public void updateListQuyen_CVModel() {
		_quyen_cvDao.updateListQuyen_cv();
		ArrayList<Quyen_cv> listQuyen_cv = _quyen_cvDao.getListQuyen_cv();
		_listQuyen_cvModels = listEntitiesToListModel(listQuyen_cv);

	}

	public List<Quyen_cvModel> getListQuyen_CVModel() {
		return _listQuyen_cvModels;
	}

}
