package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.ChuongTruyen;
import model.ChuongTruyenBO;
import model.TaiKhoan;

@WebServlet("/ChiTietTruyenController")
public class ChiTietTruyenController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    ChuongTruyenBO chuongBO = new ChuongTruyenBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        TaiKhoan tk = (TaiKhoan) session.getAttribute("taiKhoan");
        if (tk == null) {
            response.sendRedirect("DangNhapController");
            return;
        }

        int maTruyen = Integer.parseInt(request.getParameter("maTruyen"));

        ArrayList<ChuongTruyen> dsChuong =
                chuongBO.getByTruyen(maTruyen);

        request.setAttribute("maTruyen", maTruyen);
        request.setAttribute("dsChuong", dsChuong);

        request.getRequestDispatcher("/web/chitiettruyen.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        int maTruyen = Integer.parseInt(request.getParameter("maTruyen"));

        if ("them".equals(action)) {
            ChuongTruyen ct = new ChuongTruyen();
            ct.setTenChuong(request.getParameter("tenChuong"));
            ct.setNoiDung(request.getParameter("noiDung"));
            ct.setSoThuTu(Integer.parseInt(request.getParameter("soThuTu")));
            ct.setMaTruyen(maTruyen);

            chuongBO.themChuong(ct);
        }

        else if ("sua".equals(action)) {
            ChuongTruyen ct = new ChuongTruyen();
            ct.setMaChuong(
                Integer.parseInt(request.getParameter("maChuong"))
            );
            ct.setTenChuong(request.getParameter("tenChuong"));
            ct.setNoiDung(request.getParameter("noiDung"));
            ct.setSoThuTu(Integer.parseInt(request.getParameter("soThuTu")));

            chuongBO.suaChuong(ct);
        }

        else if ("xoa".equals(action)) {
            int maChuong =
                Integer.parseInt(request.getParameter("maChuong"));
            chuongBO.xoaChuong(maChuong);
        }

        response.sendRedirect(
            "ChiTietTruyenController?maTruyen=" + maTruyen
        );
    }
}
