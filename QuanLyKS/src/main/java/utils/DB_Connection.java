package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import Entities.*;


public class DB_Connection {
	public static Session getSession(Configuration configuration) {
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();
		return session;
	}

	public static Session getSession() {
		Configuration configuration = new Configuration().configure().addAnnotatedClass(KhachSan.class)
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
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		Session session = sessionFactory.openSession();
		return session;
	}

}
