package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.TaiKhoan;
import model.Truyen;
import model.TruyenBO;

@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024,
	    maxFileSize = 5 * 1024 * 1024
	)

@WebServlet("/TruyenCuaToiController")
public class TruyenCuaToiController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    TruyenBO truyenBO = new TruyenBO();

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
                truyenBO.getTruyenCuaTaiKhoan(tk.getMaTaiKhoan());

        request.setAttribute("dsTruyen", dsTruyen);
        request.getRequestDispatcher("/web/truyencuatoi.jsp")
               .forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        TaiKhoan tk = (TaiKhoan) session.getAttribute("taiKhoan");

        if (tk == null) {
            response.sendRedirect("DangNhapController");
            return;
        }

        String action = request.getParameter("action");

       
        String uploadPath = getServletContext().getRealPath("/uploads");
        java.io.File uploadDir = new java.io.File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        try {

            
            if ("them".equals(action)) {

                Truyen t = new Truyen();
                t.setTenTruyen(request.getParameter("tenTruyen"));
                t.setGioiThieu(request.getParameter("gioiThieu"));
                t.setMaTrangThai(Integer.parseInt(request.getParameter("maTrangThai")));
                t.setMaTaiKhoan(tk.getMaTaiKhoan());

                // ==== FILE ====
                Part filePart = request.getPart("anhBia");
                String fileName = java.nio.file.Paths
                        .get(filePart.getSubmittedFileName())
                        .getFileName().toString();

                if (fileName != null && !fileName.isEmpty()) {
                    String savePath = uploadPath + java.io.File.separator + fileName;
                    filePart.write(savePath);
                    t.setDuongDanAnh("uploads/" + fileName);
                } else {
                    t.setDuongDanAnh("uploads/default.jpg"); 
                }

                truyenBO.themTruyen(t);
            }

           
            else if ("sua".equals(action)) {

                Truyen t = new Truyen();
                t.setMaTruyen(Integer.parseInt(request.getParameter("maTruyen")));
                t.setTenTruyen(request.getParameter("tenTruyen"));
                t.setGioiThieu(request.getParameter("gioiThieu"));
                t.setMaTrangThai(Integer.parseInt(request.getParameter("maTrangThai")));
                t.setMaTaiKhoan(tk.getMaTaiKhoan());

                Part filePart = request.getPart("anhBia");
                String fileName = java.nio.file.Paths
                        .get(filePart.getSubmittedFileName())
                        .getFileName().toString();

                if (fileName != null && !fileName.isEmpty()) {
                    filePart.write(uploadPath + java.io.File.separator + fileName);
                    t.setDuongDanAnh("uploads/" + fileName);
                } else {
                    
                    Truyen old = truyenBO.getById(t.getMaTruyen());
                    t.setDuongDanAnh(old.getDuongDanAnh());
                }

                truyenBO.suaTruyen(t);
            }

            
            else if ("xoa".equals(action)) {
                int maTruyen = Integer.parseInt(request.getParameter("maTruyen"));
                truyenBO.xoaTruyen(maTruyen, tk.getMaTaiKhoan());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("TruyenCuaToiController");
    }
}
