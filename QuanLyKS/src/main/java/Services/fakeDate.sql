use QLKS
go

insert into ChucVu values(10,'admin','Khong Hoat Dong')
insert into NhanVien values(11, 'admin', 'admin', 'admin' , '1900-01-02 00:00:00.000' ,  '21232f297a57a5a743894a0e4a801fc3', 'admin', 'admin', 'Hoat Dong' , 'admin', null);
insert into DonViTinh values('lon','Hoat Dong')
insert into DonViTinh values('cai','Hoat Dong')
insert into DonViTinh values('chai','Hoat Dong')
insert into DonViTinh values('thung','Hoat Dong')
insert into DonViTinh values('vi','Hoat Dong')
insert into DonViTinh values('loc','Hoat Dong')
insert into DonViTinh values('goi','Hoat Dong')
insert into DonViTinh values('tui','Hoat Dong')
insert into DonViTinh values('lan','Hoat Dong')

insert into kho values('kho 1','Hoat Dong')
insert into kho values('kho 2','Hoat Dong')
insert into kho values('kho 3','Hoat Dong')
insert into kho values('kho 4','Hoat Dong')
insert into kho values('kho 5','Hoat Dong')

insert into NhomSPVaDichVu values('Ghi chu','San pham' ,'Minibar' , 'Hoat Dong')
insert into NhomSPVaDichVu values('Ghi chu','San pham' ,'Giat la' , 'Hoat Dong')
insert into NhomSPVaDichVu values('Ghi chu','Dich vu' ,'Nha hang' , 'Hoat Dong')
insert into NhomSPVaDichVu values('Ghi chu','Dich vu' ,'Tien ich' , 'Hoat Dong')

insert into SanPhamVaDichVu values(1, 10000 , 'Mo ta' , 1000 , 'Coca' , 'Hoat Dong' , 1 , 1)
insert into SanPhamVaDichVu values(2, 10000 , 'Mo ta' , 1000 , 'Fanta' , 'Hoat Dong' , 1 , 1)
insert into SanPhamVaDichVu values(3, 10000 , 'Mo ta' , 1000 , 'Snack' , 'Hoat Dong' , 1 , 1)
insert into SanPhamVaDichVu values(4, 10000 , 'Mo ta' , 1000 , 'Nuoc khoang' , 'Hoat Dong' , 1 , 1)
insert into SanPhamVaDichVu values(5, 10000 , 'Mo ta' , 1000 , 'Champange' , 'Hoat Dong' , 1 , 1)
insert into SanPhamVaDichVu values(6, 10000 , 'Mo ta' , 1000 , 'Bia heineken' , 'Hoat Dong' , 1 , 1)

insert into SanPhamVaDichVu values(7, 10000 , 'Mo ta' , 1000 , 'Giat ao vest' , 'Hoat Dong' , 2, 2)
insert into SanPhamVaDichVu values(8, 10000 , 'Mo ta' , 1000 , 'Giat quan vest' , 'Hoat Dong' , 2 , 2)
insert into SanPhamVaDichVu values(9, 10000 , 'Mo ta' , 1000 , 'Giat ao len' , 'Hoat Dong' , 2 , 2)
insert into SanPhamVaDichVu values(10, 10000 , 'Mo ta' , 1000 , 'Giat ao somi' , 'Hoat Dong' , 2 , 2)

insert into SanPhamVaDichVu values(11, 10000 , 'Mo ta' , 1000 , 'An toi' , 'Hoat Dong' , 3 , 3)
insert into SanPhamVaDichVu values(12, 10000 , 'Mo ta' , 1000 , 'An trua' , 'Hoat Dong' , 3 , 3)
insert into SanPhamVaDichVu values(13, 10000 , 'Mo ta' , 1000 , 'An sang' , 'Hoat Dong' , 3 , 3)

insert into SanPhamVaDichVu values(14, 10000 , 'Mo ta' , 1000 , 'Trong tre' , 'Hoat Dong' , 4 ,4)
insert into SanPhamVaDichVu values(15, 10000 , 'Mo ta' , 1000 , 'Cho thue xe dap' , 'Hoat Dong' , 4 ,4)
insert into SanPhamVaDichVu values(16, 10000 , 'Mo ta' , 1000 , 'Massage' , 'Hoat Dong' , 4 ,4)
insert into SanPhamVaDichVu values(17, 10000 , 'Mo ta' , 1000 , 'Don phong' , 'Hoat Dong' , 4 ,4)

insert into DonViChiTiet values(1,1,10000,1,'Hoat Dong' , 1 ,1)
insert into DonViChiTiet values(2,0,100000,10,'Hoat Dong' , 4 ,1)
insert into DonViChiTiet values(3,1,10000,1,'Hoat Dong' , 1 ,2)
insert into DonViChiTiet values(4,0,100000,10,'Hoat Dong' , 4 ,2)

insert into DonViChiTiet values(5,1,10000,1,'Hoat Dong' , 7 ,3)
insert into DonViChiTiet values(6,0,100000,10,'Hoat Dong' , 8 ,3)

insert into DonViChiTiet values(7,1,10000,1,'Hoat Dong' , 3 ,4)
insert into DonViChiTiet values(8,0,100000,10,'Hoat Dong' , 4 ,4)

insert into DonViChiTiet values(9,1,10000,1,'Hoat Dong' , 3 ,1)
insert into DonViChiTiet values(10,0,100000,10,'Hoat Dong' , 4 ,1)

insert into DonViChiTiet values(11,1,10000,1,'Hoat Dong' , 1 ,6)
insert into DonViChiTiet values(12,0,100000,10,'Hoat Dong' , 4 ,6)

insert into DonViChiTiet values(13,1,1000,1,'Hoat Dong' , 9 ,7)
insert into DonViChiTiet values(14,1,1000,1,'Hoat Dong' , 9 ,8)
insert into DonViChiTiet values(15,1,1000,1,'Hoat Dong' , 9 ,9)
insert into DonViChiTiet values(16,1,1000,1,'Hoat Dong' , 9 ,10)
insert into DonViChiTiet values(17,1,1000,1,'Hoat Dong' , 9 ,11)
insert into DonViChiTiet values(18,1,1000,1,'Hoat Dong' , 9 ,12)
insert into DonViChiTiet values(19,1,1000,1,'Hoat Dong' , 9 ,13)
insert into DonViChiTiet values(20,1,1000,1,'Hoat Dong' , 9 ,14)
insert into DonViChiTiet values(21,1,1000,1,'Hoat Dong' , 9 ,15)
insert into DonViChiTiet values(22,1,1000,1,'Hoat Dong' , 9 ,16)
insert into DonViChiTiet values(23,1,1000,1,'Hoat Dong' , 9 ,17)

insert into ChucVu values(1,'admin','Khong Hoat Dong')
insert into ChucVu values(2,'Nhan vien','Khong Hoat Dong')
insert into ChucVu values(3,'Quản ly','Khong Hoat Dong')

insert into NhanVien values(1, 'admin', 'admin', 'admin' , '1900-01-02 00:00:00.000' ,  '21232f297a57a5a743894a0e4a801fc3', 'admin', 'admin', 'Hoat Dong' , 'admin', 1);
insert into NhanVien values(2, '123123123', 'lehungyen@gmail.com', 'Nam' , '2022-08-18 00:00:00.000' ,  '21232f297a57a5a743894a0e4a801fc3', '0123123123', 'Le Hung Yen', 'Hoat Dong' , 'YenLHNV2', 3);
insert into NhanVien values(3, '123123123', 'nguyenvantien@gmail.com', 'Nam' , '2022-08-18 00:00:00.000' ,  '21232f297a57a5a743894a0e4a801fc3', '0123123123', 'Nguyen Van Tien', 'Hoat Dong' , 'TienNVNV3', 3);
insert into NhanVien values(4, '123123123', 'nguyenvanlinh@gmail.com', 'Nam' , '2022-08-18 00:00:00.000' ,  '21232f297a57a5a743894a0e4a801fc3', '0123123123', 'Nguyen Van Linh', 'Hoat Dong' , 'LinhNVNV4', 3);

insert into KhachHang values(1,'123123123' ,'dia chi' , 'abc@gmail.com' , 'Nam' , '2022-08-08 00:00:00.000' , '0123123123' ,'Hoang Minh Quan' )
insert into KhachHang values(2,'123123123' ,'dia chi' , 'abc@gmail.com' , 'Nam' , '2022-08-08 00:00:00.000' , '0123123123' ,'Le Hung Yen ' )
insert into KhachHang values(3,'123123123' ,'dia chi' , 'abc@gmail.com' , 'Nam' , '2022-08-08 00:00:00.000' , '0123123123' ,'Le Van Tien' )
insert into KhachHang values(4,'123123123' ,'dia chi' , 'abc@gmail.com' , 'Nam' , '2022-08-08 00:00:00.000' , '0123123123' ,'Le Van Linh' )

insert into LoaiPhong values(1,200000,1,'Phong don' ,'active' ,200000, 100000);
insert into LoaiPhong values(2,400000,2,'Phong doi' ,'active' ,400000, 200000);
insert into LoaiPhong values(3,500000,3,'Phong vip' ,'active' ,500000, 300000);


