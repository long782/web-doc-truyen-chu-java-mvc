<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- ================= HEADER ================= -->

<nav class="navbar navbar-dark mx-1" style="background:#dcdcdc">
    <div class="container" style="max-width:1140px;">
        <div class="d-flex flex-wrap align-items-center w-100 justify-content-between">

            <!-- Brand -->
            <a class="navbar-brand order-0 me-3 mx-3" href="TrangChuController">
                <img src="${pageContext.request.contextPath}/img/logosach.png"
                     width="50" height="50" alt="Logo">
            </a>

            <!-- Search -->
            <form class="d-flex flex-grow-1 order-2 order-lg-1 mt-2 mt-lg-0 me-0 me-lg-3"
                  method="get" action="TimKiemController">
                <input class="form-control me-2" type="search" name="q"
                       placeholder="Tìm kiếm"
                       value="${param.q}">
                <button class="btn btn-outline-success" type="submit">
                    <i class="bi bi-search"></i>
                </button>
            </form>

            <!-- Icons -->
            <ul class="navbar-nav flex-row order-1 order-lg-2 ms-auto">

                <!-- Thông báo -->
                <li class="nav-item me-3 mx-3">
                    <a class="nav-link fs-4" href="#">
                        <i class="bi bi-bell text-success"></i>
                    </a>
                </li>

                <!-- Truyện đã lưu -->
                <li class="nav-item me-3 mx-3">
                    <a class="nav-link fs-4" href="TruyenLuuController">
                        <i class="bi bi-bookmark text-success"></i>
                    </a>
                </li>

                <!-- Avatar / Đăng nhập -->
                <li class="nav-item me-3 mx-3 d-flex align-items-center">

                    <!-- ĐÃ ĐĂNG NHẬP -->
                    <c:if test="${sessionScope.taiKhoan != null}">
                        <a href="CaNhanController" class="nav-link p-0">
                            <img
                                src="${empty sessionScope.taiKhoan.duongDanAnh 
                                      ? pageContext.request.contextPath.concat('/img/ava3.jpg')
                                      : sessionScope.taiKhoan.duongDanAnh}"
                                width="32" height="32"
                                class="rounded-circle"
                                style="object-fit:cover;"
                                title="${sessionScope.taiKhoan.tenTaiKhoan}">
                        </a>
                    </c:if>

                    <!-- CHƯA ĐĂNG NHẬP -->
                    <c:if test="${sessionScope.taiKhoan == null}">
                        <a href="DangNhapController" class="nav-link fs-4">
                            <i class="bi bi-person-circle text-success"></i>
                        </a>
                    </c:if>

                </li>
            </ul>

        </div>
    </div>
</nav>

<!-- ===== Thanh thể loại ===== -->
<div class="bg-dark text-white py-2">
    <div class="container position-relative" style="max-width:1140px;">

        <div class="overflow-auto pe-5" style="white-space:nowrap;">
            <div class="d-inline-block">
                <c:forEach var="tl" items="${dsTheLoai}">
                    <a href="TimKiemController?tl=${tl.maTheLoai}"
                       class="btn btn-sm btn-dark me-2 mb-1">
                        ${tl.tenTheLoai}
                    </a>
                </c:forEach>
            </div>
        </div>

        <div class="position-absolute top-0 end-0 h-100 d-flex align-items-center pe-2">
            <div class="dropdown">
                <a class="btn btn-sm btn-dark dropdown-toggle" href="#"
                   data-bs-toggle="dropdown">
                    Xem thêm
                </a>
                <ul class="dropdown-menu dropdown-menu-end bg-dark text-white"
                    style="max-height:60vh;overflow:auto;">
                    <c:forEach var="tl" items="${dsTheLoai}">
                        <li>
                            <a class="dropdown-item text-white"
                               href="TimKiemController?tl=${tl.maTheLoai}">
                                ${tl.tenTheLoai}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>

    </div>
</div>
