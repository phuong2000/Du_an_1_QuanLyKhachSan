package Services;

import java.util.ArrayList;
import java.util.List;

import Entities.HoaDon;
import Entities.ThanhToan;
import Repositories.dao_ThanhToan;
import ViewModels.ModelHoaDon;
import ViewModels.ModelThanhToan;

public class ThanhToan_services {
	dao_ThanhToan dao;
	public ThanhToan_services() {
		//TODO Auto-generated constructor stub
		dao=new dao_ThanhToan();
	}
	
	public List<ModelThanhToan> getList(int ma){
		List<ModelThanhToan> list=new ArrayList<ModelThanhToan>();
		List<ThanhToan> list_tt=dao.select(ma);
		for(ThanhToan tt:list_tt) {
			list.add(new ModelThanhToan(tt.getMaThanhToan(),tt.getTien(),tt.getLoaiThanhToan(),new ModelHoaDon(ma,tt.getHoadon().getTrangThai(),tt.getHoadon().getNgayCheckIn(),tt.getHoadon().getNgayCheckOut(),tt.getHoadon().getNgayTao(),tt.getHoadon().getTenDoan(),tt.getHoadon().getLoai()),tt.getNgayThanhToan()));
		}
		return list;
	}
	
	public void save(ModelThanhToan tt) {
		dao.save(new ThanhToan(tt.getMaThanhToan(), tt.getTien(), tt.getLoaiThanhToan(), new HoaDon(tt.getHoadon().getMaHoaDon()), tt.getNgayThanhToan()));
	}
}
