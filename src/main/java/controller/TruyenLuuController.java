package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.LuuTruyenBO;
import model.TaiKhoan;
import model.Truyen;

@WebServlet("/TruyenLuuController")
public class TruyenLuuController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    LuuTruyenBO luuTruyenBO = new LuuTruyenBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        TaiKhoan tk = (TaiKhoan) session.getAttribute("taiKhoan");

        if (tk == null) {
            response.sendRedirect("DangNhapController");
            return;
        }

        ArrayList<Truyen> dsTruyen =
                luuTruyenBO.getTruyenDaLuu(tk.getMaTaiKhoan());

        request.setAttribute("dsTruyen", dsTruyen);
        request.getRequestDispatcher("/web/truyenluu.jsp")
               .forward(request, response);
    }
}
