package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.TaiKhoan;

/**
 * Servlet implementation class CaNhanController
 */
@WebServlet("/CaNhanController")
public class CaNhanController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        TaiKhoan tk = (TaiKhoan) session.getAttribute("taiKhoan");

        if (tk == null) {
            response.sendRedirect("DangNhapController");
            return;
        }

        request.setAttribute("taiKhoan", tk);
        request.getRequestDispatcher("/web/canhan.jsp")
               .forward(request, response);
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

        String email = request.getParameter("email");
        String matKhau = request.getParameter("matKhau");

        tk.setEmail(email);
        tk.setMatKhau(matKhau);

        session.setAttribute("taiKhoan", tk);

        response.sendRedirect("CaNhanController");
    }
}