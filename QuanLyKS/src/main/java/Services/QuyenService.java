package Services;

import java.util.ArrayList;
import java.util.List;

import Entities.ChucVu;
import Entities.Quyen;
import dao.ChucVuDao;
import dao.QuyenDao;
import models.ChucVuModel;
import models.QuyenModel;

public class QuyenService {
	private List<QuyenModel> _listQuyenModels = new ArrayList<QuyenModel>();
	private QuyenDao _QuyenDao = new QuyenDao();
	private Quyen _Quyen;
	private QuyenModel _QuyenModel;
	
	public static Quyen modelToEntity(QuyenModel QuyenModel) {
		Quyen Quyen1 = new Quyen();
		Quyen1.setMaQUyen(QuyenModel.getMaQuyen());
		Quyen1.setTenQuyen(QuyenModel.getTenQuyen());
		return Quyen1;
	}

	public static QuyenModel entityToModel(Quyen Quyen) {
		QuyenModel QuyenModel = new QuyenModel();
		QuyenModel.setMaQuyen(Quyen.getMaQUyen());
		QuyenModel.setTenQuyen(Quyen.getTenQuyen());
		return QuyenModel;
	}
	
	
	
	public static List<QuyenModel> listEntitiesToListModel (List<Quyen> listQuyens){
		List<QuyenModel> listQuyenModel = new ArrayList<QuyenModel>();
		for (Quyen Quyen : listQuyens) {
			QuyenModel QuyenModel = entityToModel(Quyen);
			listQuyenModel.add(QuyenModel);
		}
		return listQuyenModel;
	}
	
	public static List<Quyen> listModelToListEntities (List<QuyenModel> listQuyenModel){
		List<Quyen> listQuyen = new ArrayList<Quyen>();
		for (QuyenModel QuyenModel : listQuyenModel) {
			Quyen Quyen = modelToEntity(QuyenModel);
			listQuyen.add(Quyen);
		}
		return listQuyen;
	}
	
	public void them_sua(QuyenModel QuyenModel) {
		_Quyen = modelToEntity(QuyenModel);
		_QuyenDao.them_sua(_Quyen);
	}

	public void updateListChucVuModel() {
		_QuyenDao.updateListQuyen();
		ArrayList<Quyen> listQuyen = _QuyenDao.getListQuyen();
		_listQuyenModels = listEntitiesToListModel(listQuyen);

	}

	public List<QuyenModel> getListQuyenModel() {
		return _listQuyenModels;
	}

}