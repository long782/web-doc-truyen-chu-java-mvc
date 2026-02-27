package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ChuongTruyenDAO {

    // Lấy danh sách chương theo mã truyện
    public ArrayList<ChuongTruyen> getByTruyen(int maTruyen) {

        ArrayList<ChuongTruyen> ds = new ArrayList<>();

        String sql =
            "SELECT MaChuong, TenChuong, NoiDung, MaTruyen, SoThuTu, NgayDang, LuotXem " +
            "FROM CHUONG_TRUYEN " +
            "WHERE MaTruyen = ? " +
            "ORDER BY SoThuTu ASC";

        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maTruyen);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ChuongTruyen ct = new ChuongTruyen();
                ct.setMaChuong(rs.getInt("MaChuong"));
                ct.setTenChuong(rs.getString("TenChuong"));
                ct.setNoiDung(rs.getString("NoiDung"));
                ct.setMaTruyen(rs.getInt("MaTruyen"));
                ct.setSoThuTu(rs.getInt("SoThuTu"));
                ct.setNgayDang(rs.getTimestamp("NgayDang"));
                ct.setLuotXem(rs.getInt("LuotXem"));
                ds.add(ct);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }
    public ChuongTruyen getById(int maChuong) {

        String sql =
            "SELECT * FROM CHUONG_TRUYEN WHERE MaChuong = ?";

        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maChuong);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ChuongTruyen ct = new ChuongTruyen();
                ct.setMaChuong(rs.getInt("MaChuong"));
                ct.setTenChuong(rs.getString("TenChuong"));
                ct.setNoiDung(rs.getString("NoiDung"));
                ct.setMaTruyen(rs.getInt("MaTruyen"));
                ct.setSoThuTu(rs.getInt("SoThuTu"));
                ct.setNgayDang(rs.getTimestamp("NgayDang"));
                ct.setLuotXem(rs.getInt("LuotXem"));
                return ct;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void tangLuotXem(int maChuong) {
        String sql =
            "UPDATE CHUONG_TRUYEN SET LuotXem = LuotXem + 1 WHERE MaChuong = ?";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maChuong);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean themChuong(ChuongTruyen ct) {

        String sql =
            "INSERT INTO CHUONG_TRUYEN " +
            "(TenChuong, NoiDung, MaTruyen, SoThuTu) " +
            "VALUES (?, ?, ?, ?)";

        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ct.getTenChuong());
            ps.setString(2, ct.getNoiDung());
            ps.setInt(3, ct.getMaTruyen());
            ps.setInt(4, ct.getSoThuTu());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return false;
    }

    public boolean suaChuong(ChuongTruyen ct) {

        String sql =
            "UPDATE CHUONG_TRUYEN " +
            "SET TenChuong = ?, NoiDung = ?, SoThuTu = ? " +
            "WHERE MaChuong = ?";

        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ct.getTenChuong());
            ps.setString(2, ct.getNoiDung());
            ps.setInt(3, ct.getSoThuTu());
            ps.setInt(4, ct.getMaChuong());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaChuong(int maChuong) {

        String sql = "DELETE FROM CHUONG_TRUYEN WHERE MaChuong = ?";

        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maChuong);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
