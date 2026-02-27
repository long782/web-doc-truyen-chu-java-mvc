package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BinhLuanDAO {


    public ArrayList<BinhLuan> getByTruyen(int maTruyen) {

        ArrayList<BinhLuan> ds = new ArrayList<>();

        String sql =
            "SELECT BL.*, TK.TenTaiKhoan " +
            "FROM BINH_LUAN BL " +
            "JOIN TAI_KHOAN TK ON BL.MaTaiKhoan = TK.MaTaiKhoan " +
            "WHERE BL.MaTruyen = ? " +
            "ORDER BY BL.NgayBinhLuan DESC";

        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maTruyen);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                BinhLuan bl = new BinhLuan();
                bl.setMaBinhLuan(rs.getInt("MaBinhLuan"));
                bl.setNoiDungBinhLuan(rs.getString("NoiDungBinhLuan"));
                bl.setMaTruyen(rs.getInt("MaTruyen"));
                bl.setMaTaiKhoan(rs.getInt("MaTaiKhoan"));
                bl.setTenTaiKhoan(rs.getString("TenTaiKhoan"));
                bl.setNgayBinhLuan(rs.getTimestamp("NgayBinhLuan"));
                ds.add(bl);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }


    public boolean them(int maTaiKhoan, int maTruyen, String noiDung) {

        String sql =
            "INSERT INTO BINH_LUAN (NoiDungBinhLuan, MaTruyen, MaTaiKhoan) " +
            "VALUES (?, ?, ?)";

        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, noiDung);
            ps.setInt(2, maTruyen);
            ps.setInt(3, maTaiKhoan);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
