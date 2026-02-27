package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Truyen;
import model.TruyenBO;
import model.TaiKhoan;

@WebServlet("/AdminQuanLyTruyenController")
public class AdminQuanLyTruyenController extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TruyenBO bo = new TruyenBO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession(false);
        TaiKhoan admin = (TaiKhoan) session.getAttribute("admin");

        if (admin == null || admin.getMaVaiTro() != 2) {
            resp.sendRedirect("DangNhapController");
            return;
        }

        String keyword = req.getParameter("keyword");
        ArrayList<Truyen> list = bo.getAllForAdmin(keyword);

        req.setAttribute("list", list);
        req.getRequestDispatcher("/admin/QLtruyen.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");

        if ("toggle".equals(action)) {

            int maTruyen = Integer.parseInt(req.getParameter("maTruyen"));
            int trangThaiHienTai = Integer.parseInt(req.getParameter("maTrangThai"));

            bo.khoaMoTruyen(maTruyen, trangThaiHienTai);
        }

        if ("delete".equals(action)) {
            int maTruyen = Integer.parseInt(req.getParameter("maTruyen"));
            bo.xoaByAdmin(maTruyen);
        }

        resp.sendRedirect("AdminQuanLyTruyenController");
    }

}
