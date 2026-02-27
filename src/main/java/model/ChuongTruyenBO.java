package model;

import java.util.ArrayList;

public class ChuongTruyenBO {

    ChuongTruyenDAO chuongDAO = new ChuongTruyenDAO();

    // Lấy danh sách chương theo truyện
    public ArrayList<ChuongTruyen> getByTruyen(int maTruyen) {
        return chuongDAO.getByTruyen(maTruyen);
    }
    public ChuongTruyen getById(int maChuong) {
        return chuongDAO.getById(maChuong);
    }

    public void tangLuotXem(int maChuong) {
        chuongDAO.tangLuotXem(maChuong);
    }
    // ===== THÊM CHƯƠNG =====
    public boolean themChuong(ChuongTruyen ct) {
        return chuongDAO.themChuong(ct);
    }

    // ===== SỬA CHƯƠNG =====
    public boolean suaChuong(ChuongTruyen ct) {
        return chuongDAO.suaChuong(ct);
    }

    // ===== XÓA CHƯƠNG =====
    public boolean xoaChuong(int maChuong) {
        return chuongDAO.xoaChuong(maChuong);
    }
}
