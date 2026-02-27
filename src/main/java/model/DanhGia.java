package model;

import java.sql.Timestamp;

public class DanhGia {

    private int maDanhGia;
    private String noiDungDanhGia;
    private double soSao;
    private int maTruyen;
    private int maTaiKhoan;
    private String tenTaiKhoan;   // 👈 THÊM
    private Timestamp ngayDanhGia;

    public int getMaDanhGia() { return maDanhGia; }
    public void setMaDanhGia(int maDanhGia) { this.maDanhGia = maDanhGia; }

    public String getNoiDungDanhGia() { return noiDungDanhGia; }
    public void setNoiDungDanhGia(String noiDungDanhGia) {
        this.noiDungDanhGia = noiDungDanhGia;
    }

    public double getSoSao() { return soSao; }
    public void setSoSao(double soSao) { this.soSao = soSao; }

    public int getMaTruyen() { return maTruyen; }
    public void setMaTruyen(int maTruyen) { this.maTruyen = maTruyen; }

    public int getMaTaiKhoan() { return maTaiKhoan; }
    public void setMaTaiKhoan(int maTaiKhoan) { this.maTaiKhoan = maTaiKhoan; }

    public String getTenTaiKhoan() { return tenTaiKhoan; }
    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public Timestamp getNgayDanhGia() { return ngayDanhGia; }
    public void setNgayDanhGia(Timestamp ngayDanhGia) {
        this.ngayDanhGia = ngayDanhGia;
    }
}
