package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TheLoaiDAO {

    public ArrayList<TheLoai> getDanhSachTheLoai() {
        ArrayList<TheLoai> dsTheLoai = new ArrayList<>();

        String sql = "SELECT MaTheLoai, TenTheLoai FROM THE_LOAI ORDER BY TenTheLoai";

        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TheLoai tl = new TheLoai();
                tl.setMaTheLoai(rs.getInt("MaTheLoai"));
                tl.setTenTheLoai(rs.getString("TenTheLoai"));
                dsTheLoai.add(tl);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsTheLoai;
    }

    public ArrayList<TheLoai> getTheLoaiTheoTruyen(int maTruyen) {

        ArrayList<TheLoai> ds = new ArrayList<>();

        String sql =
            "SELECT TL.MaTheLoai, TL.TenTheLoai " +
            "FROM THE_LOAI TL " +
            "JOIN THE_LOAI_TRUYEN TLT ON TL.MaTheLoai = TLT.MaTheLoai " +
            "WHERE TLT.MaTruyen = ?";

        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maTruyen);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TheLoai tl = new TheLoai();
                tl.setMaTheLoai(rs.getInt("MaTheLoai"));
                tl.setTenTheLoai(rs.getString("TenTheLoai"));
                ds.add(tl);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }

    
}

