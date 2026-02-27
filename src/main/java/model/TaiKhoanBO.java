package model;

import java.util.ArrayList;

public class TaiKhoanBO {

    TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();

    public TaiKhoan dangNhap(String tenTaiKhoan, String matKhau) {
        if (tenTaiKhoan == null || matKhau == null ||
            tenTaiKhoan.isEmpty() || matKhau.isEmpty()) {
            return null;
        }
        return taiKhoanDAO.dangNhap(tenTaiKhoan, matKhau);
    }

    public String dangKy(String tenTaiKhoan, String matKhau) {

        if (tenTaiKhoan == null || matKhau == null ||
            tenTaiKhoan.isEmpty() || matKhau.isEmpty()) {
            return "Tên tài khoản và mật khẩu không được để trống!";
        }

        if (taiKhoanDAO.tonTaiTenTaiKhoan(tenTaiKhoan)) {
            return "Tên tài khoản đã tồn tại!";
        }

        boolean result = taiKhoanDAO.dangKy(tenTaiKhoan, matKhau);
        return result ? "Đăng ký thành công!" : "Đăng ký thất bại!";
    }
    
    public ArrayList<TaiKhoan> getAll(String keyword) {
        return taiKhoanDAO.getAll(keyword == null ? "" : keyword);
    }

    public String update(TaiKhoan oldTk, TaiKhoan newTk) {
        if (!oldTk.getTenTaiKhoan().equals(newTk.getTenTaiKhoan())) {
            return "❌ Không được phép sửa tên tài khoản!";
        }
        return taiKhoanDAO.update(newTk) ? "✔ Cập nhật thành công" : "❌ Cập nhật thất bại";
    }

    public boolean delete(int id) {
        return taiKhoanDAO.delete(id);
    }

    public TaiKhoan getById(int id) {
        return taiKhoanDAO.getById(id);
    }
}
