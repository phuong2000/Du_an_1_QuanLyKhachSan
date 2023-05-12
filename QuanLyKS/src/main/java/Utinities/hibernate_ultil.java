package Utinities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import Entities.*;
public class hibernate_ultil {
	static Session session=null;
	public static Session ss() {
		if(session==null) {
			Configuration conf =new Configuration().configure()
					.addAnnotatedClass(KhachSan.class)
					.addAnnotatedClass(Tang.class)
					.addAnnotatedClass(Phong.class)
					.addAnnotatedClass(LoaiPhong.class)
					.addAnnotatedClass(KhachTrongPhong.class)
					.addAnnotatedClass(HoaDon.class)
					.addAnnotatedClass(KhachHang.class)
					.addAnnotatedClass(NhanVien.class)
					.addAnnotatedClass(ChucVu.class)
					.addAnnotatedClass(Quyen.class)
					.addAnnotatedClass(ThanhToan.class)
					.addAnnotatedClass(PhuTroi.class)
					.addAnnotatedClass(Quyen_cv.class)
					.addAnnotatedClass(History.class)
					.addAnnotatedClass(PhieuNhapKho.class)
					.addAnnotatedClass(PhieuKiemKho.class)
					.addAnnotatedClass(PhieuKiemKhoChiTiet.class)
					.addAnnotatedClass(PhieuNhapKhoChiTiet.class)
					.addAnnotatedClass(SanPhamVaDichVu.class)
					.addAnnotatedClass(Kho.class)
					.addAnnotatedClass(NhomSPVaDichVu.class)
					.addAnnotatedClass(DonViTinh.class)
					.addAnnotatedClass(DonViChiTiet.class)
					.addAnnotatedClass(DichVuPhong.class)
					.addAnnotatedClass(PhuPhi.class);
			
			ServiceRegistry svr=new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();
			session =conf.buildSessionFactory(svr).openSession();
		}
		return session;
	}
}
