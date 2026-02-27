package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LuuTruyenDAO {

   
    public boolean daLuu(int maTaiKhoan, int maTruyen) {
        String sql =
            "SELECT 1 FROM TRUYEN_LUU WHERE MaTaiKhoan=? AND MaTruyen=?";

        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maTaiKhoan);
            ps.setInt(2, maTruyen);
            return ps.executeQuery().next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

   
    public boolean luu(int maTaiKhoan, int maTruyen) {
        String sql =
            "INSERT INTO TRUYEN_LUU(MaTaiKhoan, MaTruyen) VALUES (?, ?)";

        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maTaiKhoan);
            ps.setInt(2, maTruyen);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ===== HỦY =====
    public boolean huy(int maTaiKhoan, int maTruyen) {
        String sql =
            "DELETE FROM TRUYEN_LUU WHERE MaTaiKhoan=? AND MaTruyen=?";

        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maTaiKhoan);
            ps.setInt(2, maTruyen);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

   
    public ArrayList<Truyen> getTruyenDaLuu(int maTaiKhoan) {

        ArrayList<Truyen> ds = new ArrayList<>();

        String sql =
            "SELECT T.*, TK.TenTaiKhoan " +
            "FROM TRUYEN_LUU TL " +
            "JOIN TRUYEN T ON TL.MaTruyen = T.MaTruyen " +
            "LEFT JOIN TAI_KHOAN TK ON T.MaTaiKhoan = TK.MaTaiKhoan " +
            "WHERE TL.MaTaiKhoan = ? " +
            "ORDER BY TL.NgayLuu DESC";

        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maTaiKhoan);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Truyen t = new Truyen();
                t.setMaTruyen(rs.getInt("MaTruyen"));
                t.setTenTruyen(rs.getString("TenTruyen"));
                t.setGioiThieu(rs.getString("GioiThieu"));
                t.setDuongDanAnh(rs.getString("DuongDanAnh"));
                t.setTenTaiKhoan(rs.getString("TenTaiKhoan"));
                ds.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
}
