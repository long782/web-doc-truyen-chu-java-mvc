package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BinhLuan;
import model.BinhLuanBO;
import model.ChuongTruyen;
import model.ChuongTruyenBO;
import model.DanhGia;
import model.DanhGiaBO;
import model.LuuTruyenBO;
import model.TaiKhoan;
import model.TheLoai;
import model.TheLoaiBO;
import model.Truyen;
import model.TruyenBO;

/**
 * Servlet implementation class TruyenController
 */
@WebServlet("/TruyenController")
public class TruyenController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	TruyenBO truyenBO = new TruyenBO();
	ChuongTruyenBO chuongBO = new ChuongTruyenBO();
	DanhGiaBO danhGiaBO = new DanhGiaBO();
	BinhLuanBO binhLuanBO = new BinhLuanBO();
	TheLoaiBO theLoaiBO = new TheLoaiBO();
	LuuTruyenBO luuTruyenBO = new LuuTruyenBO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String maTruyenStr = request.getParameter("maTruyen");
		if (maTruyenStr == null) {
			response.sendRedirect("TrangChuController");
			return;
		}

		int maTruyen = Integer.parseInt(maTruyenStr);

		Truyen truyen = truyenBO.getById(maTruyen);
		if (truyen == null) {
			response.sendRedirect("TrangChuController");
			return;
		}

		ArrayList<ChuongTruyen> dsChuong = chuongBO.getByTruyen(maTruyen);
		ArrayList<TheLoai> dsTheLoai = theLoaiBO.getTheLoaiTheoTruyen(maTruyen);
		ArrayList<BinhLuan> dsBinhLuan = binhLuanBO.getByTruyen(maTruyen);
		ArrayList<DanhGia> dsDanhGia = danhGiaBO.getByTruyen(maTruyen);

		double diemTB = danhGiaBO.tinhDiemTrungBinh(dsDanhGia);

		request.setAttribute("truyen", truyen);
		request.setAttribute("dsChuong", dsChuong);
		request.setAttribute("dsTheLoai", dsTheLoai);
		request.setAttribute("dsBinhLuan", dsBinhLuan);
		request.setAttribute("dsDanhGia", dsDanhGia);
		request.setAttribute("diemTB", diemTB);

		boolean daLuu = false;
		HttpSession session = request.getSession(false);
		if (session != null) {
			TaiKhoan tk = (TaiKhoan) session.getAttribute("taiKhoan");
			if (tk != null) {
				daLuu = luuTruyenBO.daLuu(tk.getMaTaiKhoan(), maTruyen);
			}
		}

		request.setAttribute("daLuu", daLuu);
		request.getRequestDispatcher("/web/truyen.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		TaiKhoan tk = (TaiKhoan) session.getAttribute("taiKhoan");

		if (tk == null) {
			response.sendRedirect("DangNhapController");
			return;
		}

		int maTruyen = Integer.parseInt(request.getParameter("maTruyen"));

		
		if (request.getParameter("action") != null && request.getParameter("action").equals("luu")) {

			luuTruyenBO.toggle(tk.getMaTaiKhoan(), maTruyen);

			response.sendRedirect("TruyenController?maTruyen=" + maTruyen);
			return;
		}
		
		String ndBinhLuan = request.getParameter("ndBinhLuan");
		if (ndBinhLuan != null && !ndBinhLuan.trim().isEmpty()) {
			binhLuanBO.them(tk.getMaTaiKhoan(), maTruyen, ndBinhLuan.trim());
		}

		
		String soSaoStr = request.getParameter("soSao");
		if (soSaoStr != null) {
			double soSao = Double.parseDouble(soSaoStr);
			String ndDanhGia = request.getParameter("ndDanhGia");

			danhGiaBO.themHoacCapNhat(tk.getMaTaiKhoan(), maTruyen, soSao, ndDanhGia);
		}

		response.sendRedirect("TruyenController?maTruyen=" + maTruyen);
	}
}
