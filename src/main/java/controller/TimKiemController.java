package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TheLoai;
import model.TheLoaiBO;
import model.Truyen;
import model.TruyenBO;

/**
 * Servlet implementation class TimKiemController
 */
@WebServlet("/TimKiemController")
public class TimKiemController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    TheLoaiBO theLoaiBO = new TheLoaiBO();
    TruyenBO truyenBO = new TruyenBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String key = request.getParameter("q");
        String tl = request.getParameter("tl");
        String sx = request.getParameter("sx");

        if (sx == null || sx.isEmpty()) {
            sx = "Mới cập nhật";
        }

        ArrayList<Truyen> dsTruyen;

        
        if (key != null && !key.trim().isEmpty()) {
            dsTruyen = truyenBO.timTruyen(sx, key);
            request.setAttribute("tuKhoa", key);

        
        } else if (tl != null) {
            int maTheLoai = Integer.parseInt(tl);
            dsTruyen = truyenBO.getByTheLoai(maTheLoai, sx);
            request.setAttribute("maTheLoai", maTheLoai);

        
        } else {
            dsTruyen = truyenBO.getDanhSachTruyenTheoTieuChi(sx);
        }

        ArrayList<TheLoai> dsTheLoai = theLoaiBO.getDanhSachTheLoai();

        request.setAttribute("dsTheLoai", dsTheLoai);
        request.setAttribute("dsTruyen", dsTruyen);
        request.setAttribute("sx", sx);

        request.getRequestDispatcher("/web/timkiem.jsp").forward(request, response);
    }
}