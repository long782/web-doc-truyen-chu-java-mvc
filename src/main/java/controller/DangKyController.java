package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TaiKhoanBO;

/**
 * Servlet implementation class DangKyController
 */
@WebServlet("/DangKyController")
public class DangKyController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    TaiKhoanBO taiKhoanBO = new TaiKhoanBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/web/dangky.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String tenTaiKhoan = request.getParameter("tenTaiKhoan");
        String matKhau = request.getParameter("matKhau");
        String confirmMatKhau = request.getParameter("confirmMatKhau");

        
        if (!matKhau.equals(confirmMatKhau)) {
            request.setAttribute("error", "Mật khẩu nhập lại không khớp!");
            request.getRequestDispatcher("/web/dangky.jsp").forward(request, response);
            return;
        }

        String thongBao = taiKhoanBO.dangKy(tenTaiKhoan, matKhau);

        if ("Đăng ký thành công!".equals(thongBao)) {
            request.setAttribute("success", thongBao);
        } else {
            request.setAttribute("error", thongBao);
        }

        request.getRequestDispatcher("/web/dangky.jsp").forward(request, response);
    }
}
