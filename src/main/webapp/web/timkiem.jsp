<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Danh sách truyện</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
<style>
body { background: #dcdcdc; }
.hide-scrollbar { scrollbar-width: none; }
.hide-scrollbar::-webkit-scrollbar { display: none; }
</style>
</head>
<body>

<jsp:include page="/web/header.jsp"/>


<div class="container mt-3" style="max-width:1140px;">
    <div class="d-flex justify-content-between align-items-center border-bottom border-1 border-dark py-2 fs-4">
        <div>
            <strong>Danh sách truyện</strong>
            <span class="text-muted ms-2">(Sắp xếp: ${sx})</span>
        </div>

        <div class="dropdown">
            <a class="btn btn-sm btn-light dropdown-toggle border" href="#" data-bs-toggle="dropdown">
                Sắp xếp theo
            </a>
            <ul class="dropdown-menu dropdown-menu-end">
                <li><a class="dropdown-item" href="?sx=Mới cập nhật">Mới cập nhật</a></li>
                <li><a class="dropdown-item" href="?sx=Top lượt xem">Lượt xem</a></li>
                <li><a class="dropdown-item" href="?sx=Truyện hot">Đánh giá</a></li>
                <li><a class="dropdown-item" href="?sx=Mới hoàn thành">Hoàn thành</a></li>
            </ul>
        </div>
    </div>

    <div class="row row-cols-1 row-cols-lg-2 mt-2">
        <c:choose>
            <c:when test="${empty dsTruyen}">
                <div class="col">
                    <div class="alert alert-info">
                        Không có dữ liệu phù hợp.
                    </div>
                </div>
            </c:when>

            <c:otherwise>
                <c:forEach var="t" items="${dsTruyen}">
                    <div class="col">
                        <div class="card mb-3 story-card">
                            <div class="row g-0 flex-row-reverse">
                                <div class="col-md-9 d-flex align-items-center">
                                    <div class="card-body px-0">
                                        <a href="TruyenController?maTruyen=${t.maTruyen}" class="text-decoration-none text-dark">
                                            <h5 class="story-title fs-5 fw-semibold mb-1 text-truncate">
                                                ${t.tenTruyen}
                                            </h5>
                                            <p class="story-author">
                                                <i class="bi bi-person-circle"></i> ${t.tenTaiKhoan}
                                            </p>
                                        </a>
                                        <p class="story-desc mt-2"
                                           style="-webkit-line-clamp:3;display:-webkit-box;-webkit-box-orient:vertical;overflow:hidden;">
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
            </c:otherwise>
        </c:choose>
    </div>
</div>

<!-- Phân trang -->
<div class="container mt-3" style="max-width: 1140px;">
  <nav aria-label="pagination">
    <ul class="pagination justify-content-center">
      <li class="page-item disabled"><a class="page-link" href="#"><i class="bi bi-arrow-left"></i></a></li>
      <li class="page-item active"><a class="page-link" href="#">1</a></li>
      <li class="page-item"><a class="page-link" href="#">2</a></li>
      <li class="page-item"><a class="page-link" href="#">3</a></li>
      <li class="page-item"><a class="page-link" href="#"><i class="bi bi-arrow-right"></i></a></li>
    </ul>
  </nav>
</div>

<!-- Footer -->
<footer class="bg-dark text-white text-center py-4 mt-5">
  <div class="container">
    <p class="mb-1 fw-bold fs-5">Liên hệ</p>
    <p class="mb-1">Email: example@email.com</p>
    <p class="mb-1">Số điện thoại: 0123 456 789</p>
    <p class="mb-0">Địa chỉ: 123 Đường ABC, Huế</p>
  </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
