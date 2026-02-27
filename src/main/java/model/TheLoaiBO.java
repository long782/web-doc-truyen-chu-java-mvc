package model;

import java.util.ArrayList;

public class TheLoaiBO {

    TheLoaiDAO theLoaiDAO = new TheLoaiDAO();

    public ArrayList<TheLoai> getDanhSachTheLoai() {
        return theLoaiDAO.getDanhSachTheLoai();
    }
 // Lấy danh sách thể loại theo truyện
    public ArrayList<TheLoai> getTheLoaiTheoTruyen(int maTruyen) {
        return theLoaiDAO.getTheLoaiTheoTruyen(maTruyen);
    }

}

