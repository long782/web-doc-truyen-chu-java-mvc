package model;

import java.util.ArrayList;

public class DanhGiaBO {

    DanhGiaDAO dao = new DanhGiaDAO();

    public ArrayList<DanhGia> getByTruyen(int maTruyen) {
        return dao.getByTruyen(maTruyen);
    }

    public boolean themHoacCapNhat(int maTaiKhoan, int maTruyen,
                                   double soSao, String noiDung) {
        return dao.themHoacCapNhat(maTaiKhoan, maTruyen, soSao, noiDung);
    }
 // Tính điểm trung bình đánh giá
    public double tinhDiemTrungBinh(ArrayList<DanhGia> dsDanhGia) {

        if (dsDanhGia == null || dsDanhGia.isEmpty()) {
            return 0;
        }

        double tong = 0;
        for (DanhGia dg : dsDanhGia) {
            tong += dg.getSoSao();
        }

        // Làm tròn 1 chữ số thập phân
        return Math.round((tong / dsDanhGia.size()) * 10.0) / 10.0;
    }

}
