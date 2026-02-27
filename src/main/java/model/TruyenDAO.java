package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TruyenDAO {


	public ArrayList<Truyen> getDanhSachTruyenTheoTieuChi(String tieuChi) {

		ArrayList<Truyen> dsTruyen = new ArrayList<>();
		String sql = "";

		
		if ("Top lượt xem".equals(tieuChi)) {

			sql = "SELECT T.MaTruyen, T.TenTruyen, T.GioiThieu, T.DuongDanAnh, "
					+ "T.NgayTao, T.NgayCapNhat, TK.TenTaiKhoan, " + "SUM(CT.LuotXem) AS TongLuotXem "
					+ "FROM TRUYEN T " + "JOIN TAI_KHOAN TK ON T.MaTaiKhoan = TK.MaTaiKhoan "
					+ "LEFT JOIN CHUONG_TRUYEN CT ON T.MaTruyen = CT.MaTruyen "
					+ "GROUP BY T.MaTruyen, T.TenTruyen, T.GioiThieu, "
					+ "T.DuongDanAnh, T.NgayTao, T.NgayCapNhat, TK.TenTaiKhoan " + "ORDER BY TongLuotXem DESC";

		} else if ("Mới cập nhật".equals(tieuChi)) {

			sql = "SELECT T.MaTruyen, T.TenTruyen, T.GioiThieu, T.DuongDanAnh, "
					+ "T.NgayTao, T.NgayCapNhat, TK.TenTaiKhoan " + "FROM TRUYEN T "
					+ "JOIN TAI_KHOAN TK ON T.MaTaiKhoan = TK.MaTaiKhoan " + "ORDER BY T.NgayCapNhat DESC";

		} else if ("Mới hoàn thành".equals(tieuChi)) {

			sql = "SELECT T.MaTruyen, T.TenTruyen, T.GioiThieu, T.DuongDanAnh, "
					+ "T.NgayTao, T.NgayCapNhat, TK.TenTaiKhoan " + "FROM TRUYEN T "
					+ "JOIN TAI_KHOAN TK ON T.MaTaiKhoan = TK.MaTaiKhoan "
					+ "JOIN TRANG_THAI_TRUYEN TT ON T.MaTrangThai = TT.MaTrangThai "
					+ "WHERE TT.TenTrangThai = N'Hoàn Thành' " + "ORDER BY T.NgayCapNhat DESC";

		} else if ("Truyện hot".equals(tieuChi)) {

			sql = "SELECT T.MaTruyen, T.TenTruyen, T.GioiThieu, T.DuongDanAnh, "
					+ "T.NgayTao, T.NgayCapNhat, TK.TenTaiKhoan, " + "AVG(DG.SoSao) AS DiemTrungBinh "
					+ "FROM TRUYEN T " + "JOIN TAI_KHOAN TK ON T.MaTaiKhoan = TK.MaTaiKhoan "
					+ "LEFT JOIN DANH_GIA DG ON T.MaTruyen = DG.MaTruyen "
					+ "GROUP BY T.MaTruyen, T.TenTruyen, T.GioiThieu, "
					+ "T.DuongDanAnh, T.NgayTao, T.NgayCapNhat, TK.TenTaiKhoan " + "ORDER BY DiemTrungBinh DESC";
		}

		try (Connection conn = KetNoiCSDL.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Truyen t = new Truyen();
				t.setMaTruyen(rs.getInt("MaTruyen"));
				t.setTenTruyen(rs.getString("TenTruyen"));
				t.setGioiThieu(rs.getString("GioiThieu"));
				t.setDuongDanAnh(rs.getString("DuongDanAnh"));
				t.setNgayTao(rs.getTimestamp("NgayTao"));
				t.setNgayCapNhat(rs.getTimestamp("NgayCapNhat"));
				t.setTenTaiKhoan(rs.getString("TenTaiKhoan"));
				dsTruyen.add(t);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dsTruyen;
	}

	
	public ArrayList<Truyen> getByTheLoai(int maTheLoai, String tieuChi) {

		ArrayList<Truyen> dsTruyen = new ArrayList<>();
		String sql = "";

		if ("Top lượt xem".equals(tieuChi)) {

			sql = "SELECT T.MaTruyen, T.TenTruyen, T.GioiThieu, T.DuongDanAnh, "
					+ "T.NgayTao, T.NgayCapNhat, TK.TenTaiKhoan, " + "SUM(CT.LuotXem) AS TongLuotXem "
					+ "FROM TRUYEN T " + "JOIN TAI_KHOAN TK ON T.MaTaiKhoan = TK.MaTaiKhoan "
					+ "JOIN THE_LOAI_TRUYEN TLT ON T.MaTruyen = TLT.MaTruyen "
					+ "LEFT JOIN CHUONG_TRUYEN CT ON T.MaTruyen = CT.MaTruyen " + "WHERE TLT.MaTheLoai = ? "
					+ "GROUP BY T.MaTruyen, T.TenTruyen, T.GioiThieu, "
					+ "T.DuongDanAnh, T.NgayTao, T.NgayCapNhat, TK.TenTaiKhoan " + "ORDER BY TongLuotXem DESC";

		} else if ("Mới cập nhật".equals(tieuChi)) {

			sql = "SELECT T.MaTruyen, T.TenTruyen, T.GioiThieu, T.DuongDanAnh, "
					+ "T.NgayTao, T.NgayCapNhat, TK.TenTaiKhoan " + "FROM TRUYEN T "
					+ "JOIN TAI_KHOAN TK ON T.MaTaiKhoan = TK.MaTaiKhoan "
					+ "JOIN THE_LOAI_TRUYEN TLT ON T.MaTruyen = TLT.MaTruyen " + "WHERE TLT.MaTheLoai = ? "
					+ "ORDER BY T.NgayCapNhat DESC";

		} else if ("Mới hoàn thành".equals(tieuChi)) {

			sql = "SELECT T.MaTruyen, T.TenTruyen, T.GioiThieu, T.DuongDanAnh, "
					+ "T.NgayTao, T.NgayCapNhat, TK.TenTaiKhoan " + "FROM TRUYEN T "
					+ "JOIN TAI_KHOAN TK ON T.MaTaiKhoan = TK.MaTaiKhoan "
					+ "JOIN THE_LOAI_TRUYEN TLT ON T.MaTruyen = TLT.MaTruyen "
					+ "JOIN TRANG_THAI_TRUYEN TT ON T.MaTrangThai = TT.MaTrangThai "
					+ "WHERE TLT.MaTheLoai = ? AND TT.TenTrangThai = N'Hoàn Thành' " + "ORDER BY T.NgayCapNhat DESC";

		} else if ("Truyện hot".equals(tieuChi)) {

			sql = "SELECT T.MaTruyen, T.TenTruyen, T.GioiThieu, T.DuongDanAnh, "
					+ "T.NgayTao, T.NgayCapNhat, TK.TenTaiKhoan, " + "AVG(DG.SoSao) AS DiemTrungBinh "
					+ "FROM TRUYEN T " + "JOIN TAI_KHOAN TK ON T.MaTaiKhoan = TK.MaTaiKhoan "
					+ "JOIN THE_LOAI_TRUYEN TLT ON T.MaTruyen = TLT.MaTruyen "
					+ "LEFT JOIN DANH_GIA DG ON T.MaTruyen = DG.MaTruyen " + "WHERE TLT.MaTheLoai = ? "
					+ "GROUP BY T.MaTruyen, T.TenTruyen, T.GioiThieu, "
					+ "T.DuongDanAnh, T.NgayTao, T.NgayCapNhat, TK.TenTaiKhoan " + "ORDER BY DiemTrungBinh DESC";
		}

		try (Connection conn = KetNoiCSDL.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, maTheLoai);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Truyen t = new Truyen();
				t.setMaTruyen(rs.getInt("MaTruyen"));
				t.setTenTruyen(rs.getString("TenTruyen"));
				t.setGioiThieu(rs.getString("GioiThieu"));
				t.setDuongDanAnh(rs.getString("DuongDanAnh"));
				t.setNgayTao(rs.getTimestamp("NgayTao"));
				t.setNgayCapNhat(rs.getTimestamp("NgayCapNhat"));
				t.setTenTaiKhoan(rs.getString("TenTaiKhoan"));
				dsTruyen.add(t);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dsTruyen;
	}

	public Truyen getById(int maTruyen) {

		String sql = "SELECT T.MaTruyen, T.TenTruyen, T.GioiThieu, T.MaTaiKhoan, "
				+ "TK.TenTaiKhoan, T.DuongDanAnh, T.MaTrangThai, " + "T.NgayTao, T.NgayCapNhat " + "FROM TRUYEN T "
				+ "LEFT JOIN TAI_KHOAN TK ON T.MaTaiKhoan = TK.MaTaiKhoan " + "WHERE T.MaTruyen = ?";

		try (Connection conn = KetNoiCSDL.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, maTruyen);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Truyen t = new Truyen();
				t.setMaTruyen(rs.getInt("MaTruyen"));
				t.setTenTruyen(rs.getString("TenTruyen"));
				t.setGioiThieu(rs.getString("GioiThieu"));
				t.setMaTaiKhoan(rs.getInt("MaTaiKhoan"));
				t.setTenTaiKhoan(rs.getString("TenTaiKhoan"));
				t.setDuongDanAnh(rs.getString("DuongDanAnh"));
				t.setMaTrangThai(rs.getInt("MaTrangThai"));
				t.setNgayTao(rs.getTimestamp("NgayTao"));
				t.setNgayCapNhat(rs.getTimestamp("NgayCapNhat"));
				return t;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Truyen> getByTaiKhoan(int maTaiKhoan) {

		ArrayList<Truyen> ds = new ArrayList<>();

		String sql = "SELECT MaTruyen, TenTruyen, GioiThieu, DuongDanAnh, " + "MaTrangThai, NgayTao, NgayCapNhat "
				+ "FROM TRUYEN WHERE MaTaiKhoan=? " + "ORDER BY NgayCapNhat DESC";

		try (Connection conn = KetNoiCSDL.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, maTaiKhoan);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Truyen t = new Truyen();
				t.setMaTruyen(rs.getInt("MaTruyen"));
				t.setTenTruyen(rs.getString("TenTruyen"));
				t.setGioiThieu(rs.getString("GioiThieu"));
				t.setDuongDanAnh(rs.getString("DuongDanAnh"));
				t.setMaTrangThai(rs.getInt("MaTrangThai"));
				t.setNgayTao(rs.getTimestamp("NgayTao"));
				t.setNgayCapNhat(rs.getTimestamp("NgayCapNhat"));
				ds.add(t);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ds;
	}

	
	public boolean themTruyen(Truyen t) {

		String sql = "INSERT INTO TRUYEN(TenTruyen, GioiThieu, MaTaiKhoan, DuongDanAnh, MaTrangThai) "
				+ "VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = KetNoiCSDL.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, t.getTenTruyen());
			ps.setString(2, t.getGioiThieu());
			ps.setInt(3, t.getMaTaiKhoan());
			ps.setString(4, t.getDuongDanAnh());
			ps.setInt(5, t.getMaTrangThai());

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// ===== SỬA =====
	public boolean suaTruyen(Truyen t) {

		String sql = "UPDATE TRUYEN SET TenTruyen=?, GioiThieu=?, DuongDanAnh=?, "
				+ "MaTrangThai=?, NgayCapNhat=SYSDATETIME() " + "WHERE MaTruyen=? AND MaTaiKhoan=?";

		try (Connection conn = KetNoiCSDL.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, t.getTenTruyen());
			ps.setString(2, t.getGioiThieu());
			ps.setString(3, t.getDuongDanAnh());
			ps.setInt(4, t.getMaTrangThai());
			ps.setInt(5, t.getMaTruyen());
			ps.setInt(6, t.getMaTaiKhoan());

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean xoaTruyen(int maTruyen, int maTaiKhoan) {

		String sql = "DELETE FROM TRUYEN WHERE MaTruyen=? AND MaTaiKhoan=?";

		try (Connection conn = KetNoiCSDL.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, maTruyen);
			ps.setInt(2, maTaiKhoan);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// ===== ADMIN: LẤY TẤT CẢ TRUYỆN + TÌM KIẾM =====
	public ArrayList<Truyen> getAllForAdmin(String keyword) {

		ArrayList<Truyen> list = new ArrayList<>();

		String sql = "SELECT T.MaTruyen, T.TenTruyen, T.DuongDanAnh, T.MaTrangThai, "
				+ "T.NgayTao, T.NgayCapNhat, TK.TenTaiKhoan " + "FROM TRUYEN T "
				+ "LEFT JOIN TAI_KHOAN TK ON T.MaTaiKhoan = TK.MaTaiKhoan "
				+ "WHERE T.TenTruyen LIKE ? OR TK.TenTaiKhoan LIKE ? " + "ORDER BY T.NgayCapNhat DESC";

		try (Connection conn = KetNoiCSDL.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			String key = "%" + (keyword == null ? "" : keyword) + "%";
			ps.setString(1, key);
			ps.setString(2, key);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Truyen t = new Truyen();
				t.setMaTruyen(rs.getInt("MaTruyen"));
				t.setTenTruyen(rs.getString("TenTruyen"));
				t.setTenTaiKhoan(rs.getString("TenTaiKhoan"));
				t.setDuongDanAnh(rs.getString("DuongDanAnh"));
				t.setMaTrangThai(rs.getInt("MaTrangThai"));
				t.setNgayTao(rs.getTimestamp("NgayTao"));
				t.setNgayCapNhat(rs.getTimestamp("NgayCapNhat"));
				list.add(t);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}



	public boolean updateTrangThai(int maTruyen, int maTrangThaiMoi) {

		String sql = "UPDATE TRUYEN " + "SET MaTrangThai = ?, NgayCapNhat = SYSDATETIME() " + "WHERE MaTruyen = ?";

		try (Connection conn = KetNoiCSDL.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, maTrangThaiMoi);
			ps.setInt(2, maTruyen);

			return ps.executeUpdate() == 1; 

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteByAdmin(int maTruyen) {

		String sql = "DELETE FROM TRUYEN WHERE MaTruyen=?";

		try (Connection conn = KetNoiCSDL.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, maTruyen);
			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
