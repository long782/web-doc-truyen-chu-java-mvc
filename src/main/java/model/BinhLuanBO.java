package model;

import java.util.ArrayList;

public class BinhLuanBO {

    BinhLuanDAO dao = new BinhLuanDAO();

    public ArrayList<BinhLuan> getByTruyen(int maTruyen) {
        return dao.getByTruyen(maTruyen);
    }

    public boolean them(int maTaiKhoan, int maTruyen, String noiDung) {
        return dao.them(maTaiKhoan, maTruyen, noiDung);
    }
}
