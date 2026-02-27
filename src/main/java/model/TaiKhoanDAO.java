package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TaiKhoanDAO {

    public TaiKhoan dangNhap(String tenTaiKhoan, String matKhau) {
        String sql = "SELECT * FROM TAI_KHOAN WHERE TenTaiKhoan = ? AND MatKhau = ?";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tenTaiKhoan);
            ps.setString(2, matKhau);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setMaTaiKhoan(rs.getInt("MaTaiKhoan"));
                tk.setTenTaiKhoan(rs.getString("TenTaiKhoan"));
                tk.setMatKhau(rs.getString("MatKhau"));
                tk.setMaVaiTro(rs.getInt("MaVaiTro"));
                tk.setDuongDanAnh(rs.getString("DuongDanAnh"));
                return tk;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean tonTaiTenTaiKhoan(String tenTaiKhoan) {
        String sql = "SELECT MaTaiKhoan FROM TAI_KHOAN WHERE TenTaiKhoan = ?";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tenTaiKhoan);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean dangKy(String tenTaiKhoan, String matKhau) {
        String sql = "INSERT INTO TAI_KHOAN (TenTaiKhoan, MatKhau, MaVaiTro) "
                   + "VALUES (?, ?, ?)";

        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tenTaiKhoan);
            ps.setString(2, matKhau);
            ps.setInt(3, 1); 

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<TaiKhoan> getAll(String keyword) {
        ArrayList<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT * FROM TAI_KHOAN WHERE TenTaiKhoan LIKE ? ORDER BY MaTaiKhoan";

        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan(
                        rs.getInt("MaTaiKhoan"),
                        rs.getString("TenTaiKhoan"),
                        rs.getString("MatKhau"),
                        rs.getString("Email"),
                        rs.getInt("MaVaiTro"),
                        rs.getString("DuongDanAnh")
                );
                list.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public boolean update(TaiKhoan tk) {
        String sql = """
            UPDATE TAI_KHOAN 
            SET MatKhau=?, Email=?, MaVaiTro=?
            WHERE MaTaiKhoan=?
        """;

        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tk.getMatKhau());
            ps.setString(2, tk.getEmail());
            ps.setInt(3, tk.getMaVaiTro());
            ps.setInt(4, tk.getMaTaiKhoan());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

  
    public boolean delete(int maTaiKhoan) {
        String sql = "DELETE FROM TAI_KHOAN WHERE MaTaiKhoan=?";

        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maTaiKhoan);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

  
    public TaiKhoan getById(int id) {
        String sql = "SELECT * FROM TAI_KHOAN WHERE MaTaiKhoan=?";
        try (Connection conn = KetNoiCSDL.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new TaiKhoan(
                        rs.getInt("MaTaiKhoan"),
                        rs.getString("TenTaiKhoan"),
                        rs.getString("MatKhau"),
                        rs.getString("Email"),
                        rs.getInt("MaVaiTro"),
                        rs.getString("DuongDanAnh")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}