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
 * Servlet implementation class TrangChuController
 */
@WebServlet("/TrangChuController")
public class TrangChuController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    TheLoaiBO theLoaiBO = new TheLoaiBO();
    TruyenBO truyenBO = new TruyenBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Danh sách thể loại
        ArrayList<TheLoai> dsTheLoai = theLoaiBO.getDanhSachTheLoai();
        request.setAttribute("dsTheLoai", dsTheLoai);

        // Truyện mới cập nhật 
        ArrayList<Truyen> moiCapNhat =
                truyenBO.getDanhSachTruyenTheoTieuChi("Mới cập nhật");
        request.setAttribute("moiCapNhat", laySubList(moiCapNhat, 6));

        // BTV đề cử 
        request.setAttribute("btvDeCu", laySubList(moiCapNhat, 6));

        // Top lượt xem
        ArrayList<Truyen> topLuotXem =
                truyenBO.getDanhSachTruyenTheoTieuChi("Top lượt xem");
        request.setAttribute("topLuotXem", laySubList(topLuotXem, 10));

        // Truyện hot
        ArrayList<Truyen> truyenHot =
                truyenBO.getDanhSachTruyenTheoTieuChi("Truyện hot");
        request.setAttribute("truyenHot", laySubList(truyenHot, 10));

        // Mới hoàn thành
        ArrayList<Truyen> moiHoanThanh =
                truyenBO.getDanhSachTruyenTheoTieuChi("Mới hoàn thành");
        request.setAttribute("moiHoanThanh", laySubList(moiHoanThanh, 4));

        request.getRequestDispatcher("/web/trangchu.jsp").forward(request, response);
    }


    private ArrayList<Truyen> laySubList(ArrayList<Truyen> ds, int soLuong) {
        ArrayList<Truyen> kq = new ArrayList<>();
        if (ds == null) return kq;
        for (int i = 0; i < ds.size() && i < soLuong; i++) {
            kq.add(ds.get(i));
        }
        return kq;
    }
}