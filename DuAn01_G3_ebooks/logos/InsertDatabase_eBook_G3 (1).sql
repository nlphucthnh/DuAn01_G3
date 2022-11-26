use eBOOK


INSERT INTO dbo.NguoiDung(maNguoiDung,hoTen,gioiTinh,soDienThoai,email)
VALUES('ND001',N'Nguyễn Lê Phúc Thịnh',1,'0778171274','Thinhbobobo@gmail.com'),
	  ('ND002',N'Nguyễn Thành Lũ ',1,'0773573274','Lu123@gmail.com'),
	  ('ND003',N'Nguyễn Trung Tình',1,'0778183654','Tinh567@gmail.com'),
	  ('ND004',N'Nguyễn Chúc Phương',0,'0778170912','Phuong098@gmail.com'),
	  ('ND005',N'Lê Hoàng Thảo',1,'0778877274','Thao098@gmail.com'),
	  ('ND006',N'Nguyễn Chí Bảo',1,'0778871234','Bao009@gmail.com'),
	  ('ND007',N'Nguyễn Văn An',1,'0967267645','An@gmail.com'),
	  ('ND008',N'Nguyễn Văn Hoàng',1,'0967267474','hoang@gmail.com'),
	  ('ND009',N'Nguyễn Tuấn Anh',1,'0967267534','Anh@gmail.com'),
	  ('ND010',N'Nguyễn Hồng Di',0,'0967267246','HongDi@gmail.com'),
	  ('ND011',N'Nguyễn Pháp Tắc',1,'0967231314','tac@gmail.com'),
	  ('ND012',N'Nguyễn Đình Diệp',1,'0967312314','diep@gmail.com'),
	  ('ND013',N'Nguyễn Kì Anh',1,'0928617981','kianh@gmail.com'),
	  ('ND014',N'Nguyễn Hồng Tuấn',1,'0928612021','hongtuan@gmail.com'),
	  ('ND015',N'Nguyễn Văn Cương',1,'0928611273','cuong@gmail.com'),
	  ('ND016',N'Nguyễn Vũ Ánh',0,'0928616865','vuanh@gmail.com'),
	  ('ND017',N'Lê Hoàng Tuấn',1,'0928910992','Tuan@gmail.com'),
	  ('ND018',N'Nguyễn An Bình',1,'0951567566','Binh@gmail.com'),
	  ('ND019',N'Nguyễn Tuyết An',0,'0951212424','tuyetan@gmail.com'),
	  ('ND020',N'Nguyễn Vũ Linh',1,'0934234242','vulink@gmail.com'),
	  ('ND021',N'Nguyễn Ngọc Cẩm',0,'0951256456','ngoccam@gmail.com'),
	  ('ND022',N'Nguyễn Hồng Duyên',0,'0951216575','hongduyen@gmail.com'),
	  ('ND023',N'Trần Tranh Lam',0,'0951211231','lam@gmail.com'),
	  ('ND024',N'Trần Hồng Hoa',0,'0951211113','honghoa@gmail.com')

GO

DELETE FROM TaiKhoan

INSERT INTO dbo.TaiKhoan(tenDangNhap,matKhau,maNguoiDung,trangThai,thoiLuong)
VALUES('ThinhNLP','123456798','ND001',1,'23:59:59'),
	  ('LuNT','123456798','ND002',1,'23:59:59'),
	  ('TinhNT','123456798','ND003',1,'23:59:59'),
	  ('PhuongNC','123456798','ND004',1,'23:59:59'),
	  ('ThaoLH','123456798','ND005',1,'23:59:59'),
	  ('BaoNC','123456798','ND006',1,'23:59:59'),
	  ('AnNV','123456798','ND007',1,'23:59:59'),
	  ('HoangNV','123456798','ND008',1,'23:59:59'),
	  ('AnhNT','123456798','ND009',1,'23:59:59'),
	  ('HoangNH','123456798','ND010',1,'23:59:59'),
	  ('TacNP','123456798','ND011',1,'23:59:59'),
	  ('DiepND','123456798','ND012',1,'23:59:59')

GO

INSERT INTO dbo.QuanTriVien(tenDangNhap)
VALUES('ThinhNLP'),
	  ('LuNT'),
	  ('TinhNT'),
	  ('PhuongNC'),
	  ('ThaoLH')
GO

SELECT * FROM NguoiDung
SELECT * FROM TaiKhoan
SELECT * FROM QuanTriVien

GO
INSERT INTO dbo.TheLoai(maTheLoai,tenTheLoai,moTaTheLoai,maQuanTriVien)
VALUES(N'CTPL',N'Chính Trị - Pháp Luật',N'Sách mô tả môi trường chính trị và luật pháp tạo nên một khung khác biệt trong môi trường và điều kiện kinh doanh ở mỗi quốc gia. Môi trường chính trị- luật pháp bao gồm thể chế chính trị, sự ổn định của chính phủ',1),
	  (N'KHCN',N'Khoa học công nghệ',N'Khoa học công nghệ chính là công cụ hữu hiệu giúp chuyển dịch cơ cấu nền kinh tế từ nền kinh tế nông nghiệp sang công nghiệp, phát triển các ngành công nghệ cao với việc sử dụng máy móc, trí tuệ nhân tạo.',1),
	  (N'KTTC',N'Kinh Tế - Tài Chính',N'Sách mô tả Kinh tế là một lĩnh vực sản xuất, phân phối và thương mại, cũng như tiêu dùng hàng hóa và dịch vụ. Tổng thể, nó được định nghĩa là một lĩnh vực xã hội tập trung vào các hoạt động, tranh luận và các biểu hiện vật chất gắn liền với việc sản xuất, sử dụng và quản lý các nguồn tài nguyên khan hiếm.',1),
	  (N'VHNT',N'Văn Học - Nghệ Thuật',N'Sách Văn học nghệ thuật là tinh hoa của văn hóa thẩm mỹ, là lĩnh vực phong phú và nhạy cảm của văn hóa. Các tác phẩm văn học nghệ thuật là hình ảnh chủ quan về thế giới khách quan. Đó là quá trình khách thể hóa những nhận thức chủ quan của văn nghệ sỹ.',1),
	  (N'VHLS',N'Văn Hóa - Lịch Sử',N'Sách Lịch sử văn hóa kết hợp các cách tiếp cận của nhân học và lịch sử để xem xét các truyền thống văn hóa phổ biến và các diễn giải văn hóa về các kinh nghiệm lịch sử. Nó xem xét các ghi chép và mô tả tường thuật về vật chất trong quá khứ, bao gồm sự liên tục của các sự kiện (xảy ra liên tiếp và dẫn từ quá khứ đến hiện tại và thậm chí đến tương lai) có liên quan đến một nền văn hóa.',1),
	  (N'GTHT',N'Giáo Trình - Học Thuật',N'Sách Giáo trình là hệ thống chương trình giảng dạy của một môn học. Nó là tài liệu học tập hoặc giảng dạy được thiết kế và biên soạn dựa trên cơ sở chương trình môn học. Mục đích để làm tài liệu giảng dạy chính thức cho giáo viên, hoặc / và làm tài liệu học tập chính thức cho học sinh, sinh viên.',1),
	  (N'TGTL',N'Tôn Giáo - Tâm Linh',N'Sách Tâm linh là một khái niệm rộng, thay đổi theo hoàn cảnh, với nhiều sắc thái có thể cùng tồn tại. Thông thường, nó đề cập đến tiến trình tôn giáo tái khám phá dạng thức nguyên gốc của con người.',1),
	  (N'TLH',N'Tâm Lý Học',N'Sách Tâm lý học là ngành khoa học nghiên cứu về tâm trí và hành vi, tìm hiểu về các hiện tượng ý thức và vô thức, cũng như cảm xúc và tư duy. Đây là một bộ môn học thuật với quy mô nghiên cứu rất sâu rộng.',1),
	  (N'TTT',N'Tiểu Thuyết - Truyện',N'Sách Tiểu thuyết là một thể loại văn xuôi có hư cấu, thông qua nhân vật, hoàn cảnh, sự việc để phản ánh bức tranh xã hội rộng lớn và những vấn đề của cuộc sống con người, biểu hiện tính chất tường thuật, tính chất kể chuyện bằng ngôn ngữ văn xuôi theo những chủ đề xác định.',1),
	  (N'SH',N'Tự Lực',N' Sách Self Help được gọi là sách tự lực hay sách tự trợ là thể loại sách được viết ra với mục đích hướng dẫn độc giả cách giải quyết và xử lý các vấn đề của bản thân. Dạng sách này được lấy tên từ tựa sách Tự lực (Self Help), một cuốn sách bán chạy nhất trong năm 1859 của Tác giả Samuel Smiles người Scotland, nhưng nó còn được biết đến và phân loại dưới tên gọi “tự hoàn thiện”.',1)
GO

INSERT INTO dbo.TheLoai(maTheLoai,tenTheLoai,moTaTheLoai,maQuanTriVien)
VALUES(N'',N'Tất Cả',N'',1)
	 
GO

INSERT INTO dbo.TacGia(hoTen,gioiTinh,ngaySinh,moTa,hinh,maQuanTriVien)
VALUES(N'Timothy Ferriss',1,'1977-7-20',N'',N'Timothy_Ferriss.jpg',1),
	  (N'Hebr Cohen',1,'1932-12-30',N'',N'Hebr_Cohen.jpg',1),
	  (N'Hoàng Giang Đặng',1,null,N'',N'HoangGiangĐang.jpg',1)
GO


--SELECT * FROM Sach JOIN LoaiSS ON Sach.maSach = LoaiSS.maSach JOIN TheLoai ON TheLoai.maTheLoai = LoaiSS.maTheLoai WHERE TheLoai.maTheLoai LIKE 'CTPL%'

--SELECT * FROM TacGia WHERE maQuanTriVien  Like '5'

--SELECT * FROM LoaiSS

--select * from Sach

--select * from TheLoai


INSERT INTO dbo.Sach(maSach,tenSach,maTacGia,moTa,ngayDang,hinh,duongDan,maQuanTriVien)
VALUES(N'SH001',N'1 Tuần Làm Việc 4 giờ',N'1',N'','2022-11-17','1tuanLamViec4Gio.png','thuvienpdf.comtuan-lam-viec-4-gio.pdf',1),
	  (N'CTPL001',N'Bạn Có Thể Dàm Phán Bất Cứ Điều Gì',N'2',N'','2022-11-17','BanCoTheDamPhanBatCuDieuGi.jpg','thuvienpdf.comban-co-the-dam-phan-bat-cu-dieu-gi.pdf',1),
	  (N'TLH001',N'Bức Xúc Không Làm Bạn Vô Can',N'3',N'','2022-11-17','BucXucKhongLamTaVoCan.jpg','thuvienpdf.combuc-xuc-khong-lam-ta-vo-can.pdf',1)
GO


INSERT INTO dbo.LoaiSS(maSach,maTheLoai)
VALUES(N'SH001',N'SH'),
	  (N'CTPL001',N'CTPL'),
	  (N'TLH001',N'TLH')
GO

use ebook






