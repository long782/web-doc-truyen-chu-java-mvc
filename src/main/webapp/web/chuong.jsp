<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>${truyen.tenTruyen} - ${chuong.tenChuong}</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">

<style>
body{ background:#dcdcdc; }
.chapter-content{
    white-space: pre-line;
    line-height: 1.8;
    font-size: 1.05rem;
}
</style>
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="container my-3" style="max-width:1140px;">

<!-- ===== Breadcrumb ===== -->
<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item">
      <a class="text-decoration-none text-dark"
         href="TruyenController?maTruyen=${truyen.maTruyen}">
         ${truyen.tenTruyen}
      </a>
    </li>
    <li class="breadcrumb-item active">
      Chương ${chuong.soThuTu}: ${chuong.tenChuong}
    </li>
  </ol>
</nav>

<!-- ===== Điều hướng trên ===== -->
<div class="d-flex justify-content-center align-items-center my-3">

<c:set var="prev" value="${chuong.soThuTu - 1}"/>
<c:set var="next" value="${chuong.soThuTu + 1}"/>

<c:choose>
    <c:when test="${prev < 1}">
        <button class="btn btn-success me-2" disabled>
            <i class="bi bi-chevron-left"></i> Trước
        </button>
    </c:when>
    <c:otherwise>
        <a class="btn btn-success me-2"
           href="ChuongController?maChuong=${dsChuong[prev-1].maChuong}">
            <i class="bi bi-chevron-left"></i> Trước
        </a>
    </c:otherwise>
</c:choose>

<form class="mx-2" method="get" action="ChuongController">
<select class="form-select" name="maChuong" onchange="this.form.submit()">
    <c:forEach var="c" items="${dsChuong}">
        <option value="${c.maChuong}"
            ${c.maChuong == chuong.maChuong ? 'selected' : ''}>
            Chương ${c.soThuTu}: ${c.tenChuong}
        </option>
    </c:forEach>
</select>
</form>

<c:choose>
    <c:when test="${next > dsChuong.size()}">
        <button class="btn btn-success ms-2" disabled>
            Sau <i class="bi bi-chevron-right"></i>
        </button>
    </c:when>
    <c:otherwise>
        <a class="btn btn-success ms-2"
           href="ChuongController?maChuong=${dsChuong[next-1].maChuong}">
            Sau <i class="bi bi-chevron-right"></i>
        </a>
    </c:otherwise>
</c:choose>

</div>

<!-- ===== Nội dung chương ===== -->
<div class="bg-white rounded-3 p-3">
    <h5 class="mb-3">
        Chương ${chuong.soThuTu}: ${chuong.tenChuong}
    </h5>

    <div class="chapter-content">
        ${chuong.noiDung}
    </div>

    <div class="text-end mt-3 text-muted">
        Đăng:
        <fmt:formatDate value="${chuong.ngayDang}"
                        pattern="dd/MM/yyyy HH:mm"/>
        <span class="ms-3">Lượt xem: ${chuong.luotXem + 1}</span>
    </div>
</div>

<!-- ===== Điều hướng dưới ===== -->
<div class="d-flex justify-content-center align-items-center my-3">
    <a class="btn btn-success me-2"
       href="ChuongController?maChuong=${chuong.maChuong}">
        <i class="bi bi-chevron-up"></i> Lên đầu
    </a>
</div>

</div>

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
