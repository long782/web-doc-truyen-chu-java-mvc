package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.TaiKhoan;
import model.TaiKhoanBO;

/**
 * Servlet implementation class DangNhapController
 */
@WebServlet("/DangNhapController")
public class DangNhapController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    TaiKhoanBO taiKhoanBO = new TaiKhoanBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        request.getRequestDispatcher("/web/dangnhap.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String tenTaiKhoan = request.getParameter("username");
        String matKhau = request.getParameter("password");

        TaiKhoan tk = taiKhoanBO.dangNhap(tenTaiKhoan, matKhau);

        if (tk == null) {
            request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng!");
            request.getRequestDispatcher("/web/dangnhap.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("taiKhoan", tk);

        // Phân quyền
        if (tk.getMaVaiTro() == 1) {
            session.setAttribute("user", tk);
            response.sendRedirect("TrangChuController");
        } else if (tk.getMaVaiTro() == 2) {
            session.setAttribute("admin", tk);
            response.sendRedirect("AdminController");
        } else {
            request.setAttribute("error", "Không xác định quyền!");
            request.getRequestDispatcher("/web/dangnhap.jsp").forward(request, response);
        }
    }
}
