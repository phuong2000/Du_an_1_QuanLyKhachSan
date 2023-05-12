package Services;

import java.util.ArrayList;
import java.util.List;

import dao.PhuPhiDao;
import Entities.PhuPhi;
import models.PhuPhiModel;
public class PhuPhiService{
	private List<PhuPhiModel> _listPhuPhiModels = new ArrayList<PhuPhiModel>();
	private PhuPhiDao _PhuPhiDao = new PhuPhiDao();
	private PhuPhi _PhuPhi;
	private PhuPhiModel _chucModel;
	
	public static PhuPhi modelToEntity(PhuPhiModel PhuPhiModel) {
		PhuPhi PhuPhi = new PhuPhi();
		PhuPhi.setMaPhuPhi(PhuPhiModel.getMaPhuPhi());
		PhuPhi.setKhachTrongPhong(KhachTrongPhongService.modelToEntity(PhuPhiModel.getKhachTrongPhongModel()));
		PhuPhi.setSoTien(PhuPhiModel.getSoTien());
		PhuPhi.setGhiChu(PhuPhiModel.getGhiChu());
		return PhuPhi;
	}

	public static PhuPhiModel entityToModel(PhuPhi PhuPhi) {
		PhuPhiModel PhuPhiModel = new PhuPhiModel();
		PhuPhiModel.setMaPhuPhi(PhuPhi.getMaPhuPhi());
		PhuPhiModel.setKhachTrongPhongModel(KhachTrongPhongService.entityToModel(PhuPhi.getKhachTrongPhong()));
		PhuPhiModel.setSoTien(PhuPhi.getSoTien());
		PhuPhiModel.setGhiChu(PhuPhi.getGhiChu());
		return PhuPhiModel;
	}
	
	
	
	public static List<PhuPhiModel> listEntitiesToListModel (List<PhuPhi> listPhuPhi){
		List<PhuPhiModel> listPhuPhiModel = new ArrayList<PhuPhiModel>();
		for (PhuPhi PhuPhi : listPhuPhi) {
			PhuPhiModel PhuPhiModel = entityToModel(PhuPhi);
			listPhuPhiModel.add(PhuPhiModel);
		}
		return listPhuPhiModel;
	}
	
	public static List<PhuPhi> listModelToListEntities (List<PhuPhiModel> listPhuPhiModel){
		List<PhuPhi> listPhuPhi = new ArrayList<PhuPhi>();
		for (PhuPhiModel PhuPhiModel : listPhuPhiModel) {
			PhuPhi PhuPhi = modelToEntity(PhuPhiModel);
			listPhuPhi.add(PhuPhi);
		}
		return listPhuPhi;
	}
	
	public void them_sua(PhuPhiModel PhuPhiModel) {
		_PhuPhi = modelToEntity(PhuPhiModel);
		_PhuPhiDao.them_sua(_PhuPhi);
	}

	public void updateListPhuPhiModel() {
		_PhuPhiDao.updateListPhuPhi();
		List<PhuPhi> listPhuPhi = _PhuPhiDao.getListPhuPhi();
		_listPhuPhiModels = listEntitiesToListModel(listPhuPhi);

	}

	public List<PhuPhiModel> getListPhuPhiModel() {
		return _listPhuPhiModels;
	}

}