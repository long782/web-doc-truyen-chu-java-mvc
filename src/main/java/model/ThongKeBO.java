package model;

import java.util.ArrayList;

public class ThongKeBO {

    ThongKeDAO dao = new ThongKeDAO();

    public int luotXemHomNay() {
        return dao.luotXemHomNay();
    }

    public int soChuongHomNay() {
        return dao.soChuongHomNay();
    }

    public int tongLuotXem() {
        return dao.tongLuotXem();
    }

    public ArrayList<ThongKeNgay> thongKe28Ngay() {
        return dao.thongKe28Ngay();
    }
}
