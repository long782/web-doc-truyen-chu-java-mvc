package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DanhGiaDAO {

	public ArrayList<DanhGia> getByTruyen(int maTruyen) {

	    ArrayList<DanhGia> ds = new ArrayList<>();

	    String sql =
	        "SELECT dg.*, tk.TenTaiKhoan " +
	        "FROM DANH_GIA dg " +
	        "JOIN TAI_KHOAN tk ON dg.MaTaiKhoan = tk.MaTaiKhoan " +
	        "WHERE dg.MaTruyen = ? " +
	        "ORDER BY dg.NgayDanhGia DESC";

	    try (Connection conn = KetNoiCSDL.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, maTruyen);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            DanhGia dg = new DanhGia();
	            dg.setMaDanhGia(rs.getInt("MaDanhGia"));
	            dg.setSoSao(rs.getDouble("SoSao"));
	            dg.setNoiDungDanhGia(rs.getString("NoiDungDanhGia"));
	            dg.setMaTruyen(rs.getInt("MaTruyen"));
	            dg.setMaTaiKhoan(rs.getInt("MaTaiKhoan"));
	            dg.setTenTaiKhoan(rs.getString("TenTaiKhoan")); // 👈
	            dg.setNgayDanhGia(rs.getTimestamp("NgayDanhGia"));
	            ds.add(dg);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return ds;
	}


    public boolean themHoacCapNhat(int maTaiKhoan, int maTruyen,
                                   double soSao, String noiDung) {

        String sql =
            "IF EXISTS (SELECT 1 FROM DANH_GIA WHERE MaTaiKhoan=? AND MaTruyen=?) " +
            "BEGIN " +
            " UPDATE DANH_GIA SET SoSao=?, NoiDungDanhGia=?, NgayDanhGia=SYSDATETIME() " +
            " WHERE MaTaiKhoan=? AND MaTruyen=? " +
            "END " +
            "ELSE " +
            "BEGIN " +
            " INSERT INTO DANH_GIA (SoSao, NoiDungDanhGia, MaTruyen, MaTaiKhoan) " +
            " VALUES (?, ?, ?, ?) " +
            "END";

        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maTaiKhoan);
            ps.setInt(2, maTruyen);
            ps.setDouble(3, soSao);
            ps.setString(4, noiDung);
            ps.setInt(5, maTaiKhoan);
            ps.setInt(6, maTruyen);
            ps.setDouble(7, soSao);
            ps.setString(8, noiDung);
            ps.setInt(9, maTruyen);
            ps.setInt(10, maTaiKhoan);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
