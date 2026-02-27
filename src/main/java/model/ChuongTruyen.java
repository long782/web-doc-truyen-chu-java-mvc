package model;

import java.sql.Timestamp;

public class ChuongTruyen {

    private int maChuong;
    private String tenChuong;
    private String noiDung;
    private int maTruyen;
    private int soThuTu;
    private Timestamp ngayDang;
    private int luotXem;

    public ChuongTruyen() {
    }

 
    public int getMaChuong() {
        return maChuong;
    }

    public void setMaChuong(int maChuong) {
        this.maChuong = maChuong;
    }

    public String getTenChuong() {
        return tenChuong;
    }

    public void setTenChuong(String tenChuong) {
        this.tenChuong = tenChuong;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public int getMaTruyen() {
        return maTruyen;
    }

    public void setMaTruyen(int maTruyen) {
        this.maTruyen = maTruyen;
    }

    public int getSoThuTu() {
        return soThuTu;
    }

    public void setSoThuTu(int soThuTu) {
        this.soThuTu = soThuTu;
    }

    public Timestamp getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(Timestamp ngayDang) {
        this.ngayDang = ngayDang;
    }

    public int getLuotXem() {
        return luotXem;
    }

    public void setLuotXem(int luotXem) {
        this.luotXem = luotXem;
    }
}
