package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ChuongTruyen;
import model.ChuongTruyenBO;
import model.Truyen;
import model.TruyenBO;

/**
 * Servlet implementation class ChuongController
 */
@WebServlet("/ChuongController")
public class ChuongController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    ChuongTruyenBO chuongBO = new ChuongTruyenBO();
    TruyenBO truyenBO = new TruyenBO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String maChuongStr = request.getParameter("maChuong");
        if (maChuongStr == null) {
            response.sendRedirect("TrangChuController");
            return;
        }

        int maChuong = Integer.parseInt(maChuongStr);

        
        ChuongTruyen chuong = chuongBO.getById(maChuong);
        if (chuong == null) {
            response.sendRedirect("TrangChuController");
            return;
        }

       
        chuongBO.tangLuotXem(maChuong);

        
        Truyen truyen = truyenBO.getById(chuong.getMaTruyen());

        ArrayList<ChuongTruyen> dsChuong =
                chuongBO.getByTruyen(chuong.getMaTruyen());

        request.setAttribute("chuong", chuong);
        request.setAttribute("truyen", truyen);
        request.setAttribute("dsChuong", dsChuong);

        request.getRequestDispatcher("/web/chuong.jsp")
               .forward(request, response);
    }
}
