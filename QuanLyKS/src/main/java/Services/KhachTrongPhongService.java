package Services;

import java.util.ArrayList;
import java.util.List;

import dao.KhachTrongPhongDao;
import Entities.KhachTrongPhong;
import Entities.Phong;
import models.HoaDonModel;
import models.KhachTrongPhongModel;
import models.PhongModel;
import utils.IoCContainer;

public class KhachTrongPhongService{
	private IoCContainer ioCContainer = new IoCContainer();
	private List<KhachTrongPhongModel> _listKhachTrongPhongModels = new ArrayList<KhachTrongPhongModel>();
	private KhachTrongPhongDao _KhachTrongPhongDao = new KhachTrongPhongDao();
	private KhachTrongPhong _KhachTrongPhong;
	
	public static KhachTrongPhong modelToEntity(KhachTrongPhongModel KhachTrongPhongModel) {
		KhachTrongPhong KhachTrongPhong = new KhachTrongPhong();
		KhachTrongPhong.setId(KhachTrongPhongModel.getId());
		KhachTrongPhong.setHoadon(HoaDonService.updateDataHoaDonDependOnHoaDonModel(KhachTrongPhongModel.getHoaDonModel()));
		KhachTrongPhong.setPhong(new Phong(KhachTrongPhongModel.getPhongModel().getMaPhong())); ///////
		KhachTrongPhong.setGiaPhong(KhachTrongPhongModel.getGiaPhong());
		KhachTrongPhong.setPhuTroi(KhachTrongPhongModel.getPhuTroi());
		KhachTrongPhong.setPhuPhi(KhachTrongPhongModel.getPhuPhi());
		KhachTrongPhong.setGhiChu(KhachTrongPhongModel.getGhiChu());
		return KhachTrongPhong;
	}
	
	public static KhachTrongPhongModel entityToModel(KhachTrongPhong KhachTrongPhong) {
		KhachTrongPhongModel KhachTrongPhongModel = new KhachTrongPhongModel();
		KhachTrongPhongModel.setId(KhachTrongPhong.getId());
		KhachTrongPhongModel.setHoaDonModel(HoaDonService.updateDataHoaDonModelDependOnHoaDon(KhachTrongPhong.getHoadon()));
		KhachTrongPhongModel.setPhongModel(new PhongModel(KhachTrongPhong.getId(), null, null, null, null));/////////////////
		KhachTrongPhongModel.setGiaPhong(KhachTrongPhong.getGiaPhong());
		KhachTrongPhongModel.setPhuTroi(KhachTrongPhong.getPhuTroi());
		KhachTrongPhongModel.setPhuPhi(KhachTrongPhong.getPhuPhi());
		KhachTrongPhongModel.setGhiChu(KhachTrongPhong.getGhiChu());
		return KhachTrongPhongModel;
	}
	
	public static List<KhachTrongPhong> listModelToListEntities(List<KhachTrongPhongModel> listKhachTrongPhongModel) {
		List<KhachTrongPhong> listKhachTrongPhong = new ArrayList<KhachTrongPhong>();
		for (KhachTrongPhongModel KhachTrongPhongModel2 : listKhachTrongPhongModel) {
			KhachTrongPhong KhachTrongPhong = modelToEntity(KhachTrongPhongModel2);
			listKhachTrongPhong.add(KhachTrongPhong);
		}
		return listKhachTrongPhong;
	}
	
	public static List<KhachTrongPhongModel> listEntitiesToListModel(List<KhachTrongPhong> listKhachTrongPhong) {
		List<KhachTrongPhongModel> listKhachTrongPhongModel = new ArrayList<KhachTrongPhongModel>();
		for (KhachTrongPhong KhachTrongPhong : listKhachTrongPhong) {
			KhachTrongPhongModel KhachTrongPhongModel = entityToModel(KhachTrongPhong);
			listKhachTrongPhongModel.add(KhachTrongPhongModel);
		}
		return listKhachTrongPhongModel;
	}

	public void them_sua(KhachTrongPhongModel KhachTrongPhongModel) {
		_KhachTrongPhong = modelToEntity(KhachTrongPhongModel);
		_KhachTrongPhongDao.them_sua(_KhachTrongPhong);
	}

	public void updateListKhachTrongPhongModel() {
		_KhachTrongPhongDao.updateListKhachTrongPhong();
		List<KhachTrongPhong> listKhachTrongPhong = _KhachTrongPhongDao.getListKhachTrongPhong();
		_listKhachTrongPhongModels = listEntitiesToListModel(listKhachTrongPhong);

	}

	public List<KhachTrongPhongModel> getListKhachTrongPhongModel() {
		return _listKhachTrongPhongModels;
	}

}

