package model;

import java.sql.Timestamp;

public class BinhLuan {

    private int maBinhLuan;
    private String noiDungBinhLuan;
    private int maTruyen;
    private int maTaiKhoan;
    private String tenTaiKhoan;
    private Timestamp ngayBinhLuan;


    public int getMaBinhLuan() {
        return maBinhLuan;
    }

    public void setMaBinhLuan(int maBinhLuan) {
        this.maBinhLuan = maBinhLuan;
    }

    public String getNoiDungBinhLuan() {
        return noiDungBinhLuan;
    }

    public void setNoiDungBinhLuan(String noiDungBinhLuan) {
        this.noiDungBinhLuan = noiDungBinhLuan;
    }

    public int getMaTruyen() {
        return maTruyen;
    }

    public void setMaTruyen(int maTruyen) {
        this.maTruyen = maTruyen;
    }

    public int getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(int maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public Timestamp getNgayBinhLuan() {
        return ngayBinhLuan;
    }

    public void setNgayBinhLuan(Timestamp ngayBinhLuan) {
        this.ngayBinhLuan = ngayBinhLuan;
    }
    
    public BinhLuan() {
    }

}
