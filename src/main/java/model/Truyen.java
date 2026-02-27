package model;


import java.sql.Timestamp;

public class Truyen {

    private int maTruyen;
    private String tenTruyen;
    private String gioiThieu;
    private int maTaiKhoan;
    private String tenTaiKhoan; 
    private String duongDanAnh;
    private int maTrangThai;
    private Timestamp ngayTao;
    private Timestamp ngayCapNhat;


    public int getMaTruyen() {
        return maTruyen;
    }

    public void setMaTruyen(int maTruyen) {
        this.maTruyen = maTruyen;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getGioiThieu() {
        return gioiThieu;
    }

    public void setGioiThieu(String gioiThieu) {
        this.gioiThieu = gioiThieu;
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

    public String getDuongDanAnh() {
        return duongDanAnh;
    }

    public void setDuongDanAnh(String duongDanAnh) {
        this.duongDanAnh = duongDanAnh;
    }

    public int getMaTrangThai() {
        return maTrangThai;
    }

    public void setMaTrangThai(int maTrangThai) {
        this.maTrangThai = maTrangThai;
    }

    public Timestamp getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Timestamp ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Timestamp getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(Timestamp ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }
    
    
    public Truyen() {
    }


    public Truyen(int maTruyen, String tenTruyen, String gioiThieu,
                  int maTaiKhoan, String tenTaiKhoan,
                  String duongDanAnh, int maTrangThai,
                  Timestamp ngayTao, Timestamp ngayCapNhat) {

        this.maTruyen = maTruyen;
        this.tenTruyen = tenTruyen;
        this.gioiThieu = gioiThieu;
        this.maTaiKhoan = maTaiKhoan;
        this.tenTaiKhoan = tenTaiKhoan;
        this.duongDanAnh = duongDanAnh;
        this.maTrangThai = maTrangThai;
        this.ngayTao = ngayTao;
        this.ngayCapNhat = ngayCapNhat;
    }

    
    
}

