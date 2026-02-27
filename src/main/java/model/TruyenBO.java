package model;

import java.util.ArrayList;

public class TruyenBO {

	TruyenDAO truyenDAO = new TruyenDAO();

	// Lấy danh sách truyện theo tiêu chí
	public ArrayList<Truyen> getDanhSachTruyenTheoTieuChi(String tieuChi) {
		return truyenDAO.getDanhSachTruyenTheoTieuChi(tieuChi);
	}

	// Tìm kiếm
	public ArrayList<Truyen> timTruyen(String tieuChi, String key) {
		ArrayList<Truyen> ketQua = new ArrayList<>();
		if (key == null || key.trim().isEmpty()) {
			return ketQua;
		}
		ArrayList<Truyen> ds = truyenDAO.getDanhSachTruyenTheoTieuChi(tieuChi);
		String tuKhoa = key.trim().toLowerCase();
		for (Truyen t : ds) {
			if (t.getTenTruyen() != null && t.getTenTruyen().trim().toLowerCase().contains(tuKhoa)) {
				ketQua.add(t);
			} else if (t.getTenTaiKhoan() != null && t.getTenTaiKhoan().trim().toLowerCase().contains(tuKhoa)) {
				ketQua.add(t);
			}
		}
		return ketQua;
	}

	public ArrayList<Truyen> getByTheLoai(int maTheLoai, String tieuChi) {
		return truyenDAO.getByTheLoai(maTheLoai, tieuChi);
	}

	// Lấy truyện theo mã
	public Truyen getById(int maTruyen) {
		return truyenDAO.getById(maTruyen);
	}

	public ArrayList<Truyen> getTruyenCuaTaiKhoan(int maTaiKhoan) {
		return truyenDAO.getByTaiKhoan(maTaiKhoan);
	}

	// ===== THÊM =====
	public boolean themTruyen(Truyen t) {
		return truyenDAO.themTruyen(t);
	}

	// ===== SỬA =====
	public boolean suaTruyen(Truyen t) {
		return truyenDAO.suaTruyen(t);
	}

	// ===== XÓA =====
	public boolean xoaTruyen(int maTruyen, int maTaiKhoan) {
		return truyenDAO.xoaTruyen(maTruyen, maTaiKhoan);
	}
	
	// ===== ADMIN =====
	public ArrayList<Truyen> getAllForAdmin(String keyword) {
	    return truyenDAO.getAllForAdmin(keyword);
	}

	public boolean khoaMoTruyen(int maTruyen, int trangThaiHienTai) {

	    int trangThaiMoi;

	    if (trangThaiHienTai == 2) {
	        trangThaiMoi = 3; 
	    } else {
	        trangThaiMoi = 2; 
	    }

	    return truyenDAO.updateTrangThai(maTruyen, trangThaiMoi);
	}


	public boolean xoaByAdmin(int maTruyen) {
	    return truyenDAO.deleteByAdmin(maTruyen);
	}

}
