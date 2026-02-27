<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Truyện đã lưu</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">

<style>
body { background:#dcdcdc; }
.story-card { border: none; }
.story-author { font-size:0.9rem;color:#666; }
</style>
</head>
<body>

<jsp:include page="header.jsp"/>

<main class="mt-4">
<div class="container-fluid px-4">
<div class="row justify-content-center">

<!-- ===== SIDEBAR ===== -->
<div class="col-md-3 col-lg-2">
    <div class="list-group sidebar">
        <a href="CaNhanController"
           class="list-group-item list-group-item-action">
            <i class="bi bi-person"></i> Thông tin cá nhân
        </a>

        <a href="TruyenLuuController"
           class="list-group-item list-group-item-action active">
            <i class="bi bi-bookmark"></i> Truyện đã lưu
        </a>

        <a href="TruyenCuaToiController"
           class="list-group-item list-group-item-action">
            <i class="bi bi-journal-text"></i> Truyện của tôi
        </a>

        <a href="TrangChuController"
           class="list-group-item list-group-item-action">
            <i class="bi bi-arrow-left"></i> Quay lại trang truyện
        </a>

        <a href="DangXuatController"
           class="list-group-item list-group-item-action text-danger">
            <i class="bi bi-box-arrow-right"></i> Đăng xuất
        </a>
    </div>
</div>
<!-- ===== CONTENT ===== -->
<div class="col-md-9 col-lg-8">
    <div class="bg-white rounded-3 p-4">

        <h4 class="fw-bold mb-4">📚 Truyện đã lưu</h4>

        <div class="row row-cols-1">

            <c:forEach var="t" items="${dsTruyen}">
                <div class="col">
                    <div class="card mb-3 story-card">
                        <div class="row g-0 flex-row-reverse">

                            <div class="col-md-9 d-flex align-items-center">
                                <div class="card-body px-0">
                                    <a href="TruyenController?maTruyen=${t.maTruyen}"
                                       class="text-decoration-none text-dark">
                                        <h5 class="fw-semibold text-truncate">
                                            ${t.tenTruyen}
                                        </h5>
                                        <p class="story-author">
                                            <i class="bi bi-person-circle"></i>
                                            ${t.tenTaiKhoan}
                                        </p>
                                    </a>

                                    <p class="mt-2"
                                       style="-webkit-line-clamp:3;
                                              display:-webkit-box;
                                              -webkit-box-orient:vertical;
                                              overflow:hidden;">
                                        ${t.gioiThieu}
                                    </p>
                                </div>
                            </div>

                            <div class="col-md-3 d-flex align-items-center p-3">
                                <img src="${t.duongDanAnh}"
                                     class="img-fluid rounded-3"
                                     style="height:120px;width:100%;object-fit:cover;">
                            </div>

                        </div>
                    </div>
                </div>
            </c:forEach>

            <c:if test="${empty dsTruyen}">
                <div class="text-muted text-center telling">
                    Bạn chưa lưu truyện nào.
                </div>
            </c:if>

        </div>

    </div>
</div>

</div>
</div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
