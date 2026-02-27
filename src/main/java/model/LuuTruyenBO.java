package model;

import java.util.ArrayList;

public class LuuTruyenBO {

    LuuTruyenDAO dao = new LuuTruyenDAO();

    public boolean daLuu(int maTaiKhoan, int maTruyen) {
        return dao.daLuu(maTaiKhoan, maTruyen);
    }

    public boolean toggle(int maTaiKhoan, int maTruyen) {
        return dao.daLuu(maTaiKhoan, maTruyen)
                ? dao.huy(maTaiKhoan, maTruyen)
                : dao.luu(maTaiKhoan, maTruyen);
    }

    public ArrayList<Truyen> getTruyenDaLuu(int maTaiKhoan) {
        return dao.getTruyenDaLuu(maTaiKhoan);
    }
}
