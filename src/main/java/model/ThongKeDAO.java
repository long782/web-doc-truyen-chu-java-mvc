package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ThongKeDAO {


    public int luotXemHomNay() {
        String sql =
            "SELECT SUM(LuotXem) " +
            "FROM CHUONG_TRUYEN " +
            "WHERE CAST(NgayDang AS DATE) = CAST(SYSDATETIME() AS DATE)";

        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

 
    public int soChuongHomNay() {
        String sql =
            "SELECT COUNT(*) " +
            "FROM CHUONG_TRUYEN " +
            "WHERE CAST(NgayDang AS DATE) = CAST(SYSDATETIME() AS DATE)";

        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    
    public int tongLuotXem() {
        String sql = "SELECT SUM(LuotXem) FROM CHUONG_TRUYEN";

        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public ArrayList<ThongKeNgay> thongKe28Ngay() {

        ArrayList<ThongKeNgay> list = new ArrayList<>();

        String sql =
            "WITH Ngay AS ( " +
            "  SELECT CAST(DATEADD(DAY, -27, CAST(SYSDATETIME() AS DATE)) AS DATE) AS Ngay " +
            "  UNION ALL " +
            "  SELECT DATEADD(DAY, 1, Ngay) FROM Ngay " +
            "  WHERE Ngay < CAST(SYSDATETIME() AS DATE) " +
            ") " +
            "SELECT N.Ngay, COUNT(C.MaChuong) AS SoTruyen " +
            "FROM Ngay N " +
            "LEFT JOIN CHUONG_TRUYEN C " +
            "  ON CAST(C.NgayDang AS DATE) = N.Ngay " +
            "GROUP BY N.Ngay " +
            "ORDER BY N.Ngay " +
            "OPTION (MAXRECURSION 28)";

        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ThongKeNgay t = new ThongKeNgay();
                t.setNgay(rs.getDate("Ngay"));
                t.setSoChuong(rs.getInt("SoTruyen"));
                list.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
