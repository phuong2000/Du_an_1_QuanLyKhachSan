package Services;

import java.util.ArrayList;
import java.util.List;

import dao.DichVuPhongDao;
import Entities.DichVuPhong;
import Entities.KhachTrongPhong;
import ViewModels.ModelKhachTrongPhong;
import models.DichVuPhongModel;
import models.KhachTrongPhongModel;
import utils.IoCContainer;

public class DichVuPhongService{
	private IoCContainer ioCContainer = new IoCContainer();
	private List<DichVuPhongModel> _listDichVuPhongModels = new ArrayList<DichVuPhongModel>();
	private DichVuPhongDao _DichVuPhongDao = new DichVuPhongDao();
	private DichVuPhong _DichVuPhong;
	private DichVuPhongModel _DichVuPhongModel = new DichVuPhongModel();

	public static DichVuPhong modelToEntity(DichVuPhongModel DichVuPhongModel) {
		DichVuPhong DichVuPhong = new DichVuPhong();
		DichVuPhong.setMaDichVuPhong(DichVuPhongModel.getMaDichVuPhong());
		DichVuPhong.setSanPhamVaDichVu(SanPhamVaDichVuService.modelToEntity(DichVuPhongModel.getSanPhamVaDichVuModel()));
		DichVuPhong.setKhachTrongPhong(KhachTrongPhongService.modelToEntity(DichVuPhongModel.getKhachTrongPhongModel()));
		DichVuPhong.setGiaBan(DichVuPhongModel.getGiaBan());
		DichVuPhong.setSoLuong(DichVuPhongModel.getSoLuong());
		return DichVuPhong;
	}

	public static DichVuPhongModel entityToModel(DichVuPhong DichVuPhong) {
		DichVuPhongModel DichVuPhongModel = new DichVuPhongModel();
		DichVuPhongModel.setMaDichVuPhong(DichVuPhong.getMaDichVuPhong());
		DichVuPhongModel.setSanPhamVaDichVuModel(SanPhamVaDichVuService.entityToModel(DichVuPhong.getSanPhamVaDichVu()));
		DichVuPhongModel.setKhachTrongPhongModel(KhachTrongPhongService.entityToModel(DichVuPhong.getKhachTrongPhong()));
		DichVuPhongModel.setGiaBan(DichVuPhong.getGiaBan());
		DichVuPhongModel.setSoLuong(DichVuPhong.getSoLuong());
		return DichVuPhongModel;
	}
	
	public static List<DichVuPhongModel> listEntitiesToListModel (List<DichVuPhong> listDichVuPhong){
		List<DichVuPhongModel> listDichVuPhongModel = new ArrayList<DichVuPhongModel>();
		for (DichVuPhong DichVuPhong : listDichVuPhong) {
			DichVuPhongModel DichVuPhongModel = entityToModel(DichVuPhong);
			listDichVuPhongModel.add(DichVuPhongModel);
		}
		return listDichVuPhongModel;
	}
	
	public static List<DichVuPhong> listModelToListEntities (List<DichVuPhongModel> listDichVuPhongModel){
		List<DichVuPhong> listDichVuPhong = new ArrayList<DichVuPhong>();
		for (DichVuPhongModel DichVuPhongModel : listDichVuPhongModel) {
			DichVuPhong DichVuPhong = modelToEntity(DichVuPhongModel);
			listDichVuPhong.add(DichVuPhong);
		}
		return listDichVuPhong;
	}
	
	public void them_sua(DichVuPhongModel DichVuPhongModel) {
		_DichVuPhong = modelToEntity(DichVuPhongModel);
		_DichVuPhongDao.them_sua(_DichVuPhong);
	}

	public void updateListDichVuPhongModel() {
		_DichVuPhongDao.updateListDichVuPhong();
		ArrayList<DichVuPhong> listDichVuPhong = _DichVuPhongDao.getListDichVuPhong();
		_listDichVuPhongModels = listEntitiesToListModel(listDichVuPhong);

	}

	public List<DichVuPhongModel> getListDichVuPhongModel() {
		return _listDichVuPhongModels;
	}
}
