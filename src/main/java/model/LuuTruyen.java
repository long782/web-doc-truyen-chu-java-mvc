package model;

import java.sql.Timestamp;

public class LuuTruyen {
    private int maTruyen;
    private int maTaiKhoan;
    private Timestamp ngayLuu;

    public int getMaTruyen() { return maTruyen; }
    public void setMaTruyen(int maTruyen) { this.maTruyen = maTruyen; }

    public int getMaTaiKhoan() { return maTaiKhoan; }
    public void setMaTaiKhoan(int maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public Timestamp getNgayLuu() { return ngayLuu; }
    public void setNgayLuu(Timestamp ngayLuu) {
        this.ngayLuu = ngayLuu;
    }
}
