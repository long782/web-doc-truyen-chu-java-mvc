package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.TaiKhoan;
import model.TaiKhoanBO;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")

public class AdminController extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TaiKhoanBO bo = new TaiKhoanBO();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        HttpSession session = req.getSession(false);
        TaiKhoan admin = (TaiKhoan) session.getAttribute("admin");

        if (admin == null || admin.getMaVaiTro() != 2) {
            resp.sendRedirect("DangNhapController");
            return;
        }

        String keyword = req.getParameter("keyword");
        ArrayList<TaiKhoan> list = bo.getAll(keyword);

        req.setAttribute("list", list);
        req.getRequestDispatcher("/admin/QLtaikhoan.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String action = req.getParameter("action");

        if ("delete".equals(action)) {
            bo.delete(Integer.parseInt(req.getParameter("id")));
        }

        if ("update".equals(action)) {
            int id = Integer.parseInt(req.getParameter("maTaiKhoan"));

            TaiKhoan oldTk = bo.getById(id);

            TaiKhoan newTk = new TaiKhoan();
            newTk.setMaTaiKhoan(id);
            newTk.setTenTaiKhoan(req.getParameter("tenTaiKhoan"));
            newTk.setEmail(req.getParameter("email"));
            newTk.setMatKhau(req.getParameter("matKhau"));
            newTk.setMaVaiTro(Integer.parseInt(req.getParameter("maVaiTro")));

            String msg = bo.update(oldTk, newTk);
            req.getSession().setAttribute("msg", msg);
        }

        resp.sendRedirect("AdminController");
    }

}
