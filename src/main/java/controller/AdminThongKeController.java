package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.TaiKhoan;
import model.ThongKeBO;

@WebServlet("/AdminThongKeController")
public class AdminThongKeController extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ThongKeBO bo = new ThongKeBO();

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

        req.setAttribute("luotXemHN", bo.luotXemHomNay());
        req.setAttribute("soChuongHN", bo.soChuongHomNay());
        req.setAttribute("tongLuotXem", bo.tongLuotXem());
        req.setAttribute("tk28", bo.thongKe28Ngay());

        req.getRequestDispatcher("/admin/ThongKe.jsp").forward(req, resp);
    }
}
