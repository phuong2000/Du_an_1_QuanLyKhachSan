package Services;

import java.util.ArrayList;
import java.util.List;

import dao.HistoryDao;
import Entities.History;
import models.HistoryModel;
import models.NhanVienModel;
import utils.IoCContainer;

public class HistoryService{
	private IoCContainer ioCContainer = new IoCContainer();
	private List<HistoryModel> _listHistoryModels = new ArrayList<HistoryModel>();
	private HistoryDao _HistoryDao = new HistoryDao();
	private History _History;

	public static History modelToEntity (HistoryModel HistoryModel) {
		History History = new History();
		History.setId(HistoryModel.getId());
		History.setNhanVien(NhanVienService.modelToEntity(HistoryModel.getNhanVienModel()));
		History.setDoiTuong(HistoryModel.getDoiTuong());
		History.setHanhDong(HistoryModel.getHanhDong());
		History.setGhiChu(HistoryModel.getGhiChu());
		return History;
	}
	
	public static HistoryModel entityToModel (History History) {
		HistoryModel HistoryModel = new HistoryModel();
		HistoryModel.setId(History.getId());
		HistoryModel.setNhanVienModel(NhanVienService.entityToModel(History.getNhanVien()));
		HistoryModel.setDoiTuong(History.getDoiTuong());
		HistoryModel.setHanhDong(History.getHanhDong());
		HistoryModel.setGhiChu(History.getGhiChu());
		return HistoryModel;
	}
	
	public static List<History> listModelToListEntity (List<HistoryModel> listHistoryModel) {
		List<History> listHistory = new ArrayList<History>();
		for (HistoryModel HistoryModel2 : listHistoryModel) {
			History History = modelToEntity(HistoryModel2);
			listHistory.add(History);
		}
		return listHistory;
	}
	
	public static List<HistoryModel> listEntityToListModel (List<History> listHistory) {
		List<HistoryModel> listHistoryModel = new ArrayList<HistoryModel>();
		for (History History : listHistory) {
			HistoryModel HistoryModel = entityToModel(History);
			listHistoryModel.add(HistoryModel);
		}
		return listHistoryModel;
	}

	public void them_sua(HistoryModel HistoryModel) {
		_History = modelToEntity(HistoryModel);
		_HistoryDao.them_sua(_History);
		_listHistoryModels.add(HistoryModel);// check lại
	}

	public void updateListHistoryModel() {
		_HistoryDao.updateListHistory();
		List<History> listHistory = _HistoryDao.getListHistory();
		_listHistoryModels = listEntityToListModel(listHistory);

	}

	public List<HistoryModel> getListHistoryModel() {
		return _listHistoryModels;
	}

}