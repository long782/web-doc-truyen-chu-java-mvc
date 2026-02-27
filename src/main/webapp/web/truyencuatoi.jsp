<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Truyện của tôi</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">

<style>
body { background:#dcdcdc; }
main { padding-top:30px; }
.sidebar a.active { background:#198754; color:#fff; }
img.preview { width:120px;height:160px;object-fit:cover;border-radius:6px; }
</style>
</head>
<body>

<jsp:include page="header.jsp"/>

<main>
<div class="container-fluid px-4">
<div class="row justify-content-center">

<!-- SIDEBAR -->
<div class="col-md-3 col-lg-2">
    <div class="list-group sidebar">
        <a href="CaNhanController"
           class="list-group-item list-group-item-action">
            <i class="bi bi-person"></i> Thông tin cá nhân
        </a>

        <a href="TruyenLuuController"
           class="list-group-item list-group-item-action">
            <i class="bi bi-bookmark"></i> Truyện đã lưu
        </a>

        <a href="TruyenCuaToiController"
           class="list-group-item list-group-item-action active">
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
<!-- CONTENT -->
<div class="col-md-9 col-lg-8">
<div class="bg-white rounded-3 p-4">

<div class="d-flex justify-content-between mb-3">
    <h4 class="fw-bold">Truyện của tôi</h4>
    <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#modalThem">
        <i class="bi bi-plus-circle"></i> Đăng truyện
    </button>
</div>

<table class="table table-hover align-middle">
<thead>
<tr>
    <th>#</th>
    <th>Tên truyện</th>
    <th>Trạng thái</th>
    <th>Cập nhật</th>
    <th class="text-end">Thao tác</th>
</tr>
</thead>
<tbody>

<c:forEach var="t" items="${dsTruyen}" varStatus="st">
<tr>
    <td>${st.index+1}</td>
    <td>${t.tenTruyen}</td>
    <td>
        <c:choose>
            <c:when test="${t.maTrangThai==3}"><span class="badge bg-success">Đang ra</span></c:when>
            <c:when test="${t.maTrangThai==1}"><span class="badge bg-primary">Hoàn thành</span></c:when>
            <c:otherwise><span class="badge bg-danger">Khóa</span></c:otherwise>
        </c:choose>
    </td>
    <td><fmt:formatDate value="${t.ngayCapNhat}" pattern="dd/MM/yyyy"/></td>
    <td class="text-end">
        <a href="ChiTietTruyenController?maTruyen=${t.maTruyen}" class="btn btn-sm btn-info">
            <i class="bi bi-eye"></i>
        </a>

        <button class="btn btn-sm btn-warning" data-bs-toggle="modal"
                data-bs-target="#modalSua${t.maTruyen}">
            <i class="bi bi-pencil"></i>
        </button>

        <form method="post" action="TruyenCuaToiController" class="d-inline">
            <input type="hidden" name="action" value="xoa">
            <input type="hidden" name="maTruyen" value="${t.maTruyen}">
            <button class="btn btn-sm btn-danger"
                    onclick="return confirm('Xóa truyện này?')">
                <i class="bi bi-trash"></i>
            </button>
        </form>
    </td>
</tr>
</c:forEach>

<c:if test="${empty dsTruyen}">
<tr><td colspan="5" class="text-center text-muted">Chưa có truyện</td></tr>
</c:if>

</tbody>
</table>

</div>
</div>
</div>
</div>
</main>

<!-- ================= MODAL THÊM ================= -->
<div class="modal fade" id="modalThem">
<div class="modal-dialog modal-lg">
<form class="modal-content" method="post"
      action="TruyenCuaToiController"
      enctype="multipart/form-data">

<input type="hidden" name="action" value="them">

<div class="modal-header">
    <h5 class="modal-title">Đăng truyện mới</h5>
    <button class="btn-close" data-bs-dismiss="modal"></button>
</div>

<div class="modal-body">
<input class="form-control mb-2" name="tenTruyen" placeholder="Tên truyện" required>
<textarea class="form-control mb-2" name="gioiThieu" rows="4" required></textarea>

<input type="file" name="anhBia" class="form-control mb-2" accept="image/*">

<select name="maTrangThai" class="form-select">
    <option value="3">Đang ra</option>
    <option value="1">Hoàn thành</option>
    <option value="2">Khóa</option>
</select>
</div>

<div class="modal-footer">
<button class="btn btn-success">Đăng truyện</button>
</div>

</form>
</div>
</div>

<!-- ================= MODAL SỬA ================= -->
<c:forEach var="t" items="${dsTruyen}">
<div class="modal fade" id="modalSua${t.maTruyen}">
<div class="modal-dialog modal-lg">
<form class="modal-content" method="post"
      action="TruyenCuaToiController"
      enctype="multipart/form-data">

<input type="hidden" name="action" value="sua">
<input type="hidden" name="maTruyen" value="${t.maTruyen}">

<div class="modal-header">
<h5 class="modal-title">Chỉnh sửa truyện</h5>
<button class="btn-close" data-bs-dismiss="modal"></button>
</div>

<div class="modal-body">

<img src="${t.duongDanAnh}" class="preview mb-2"><br>

<input class="form-control mb-2" name="tenTruyen" value="${t.tenTruyen}" required>
<textarea class="form-control mb-2" name="gioiThieu" rows="4" required>${t.gioiThieu}</textarea>

<input type="file" name="anhBia" class="form-control mb-2" accept="image/*">

<select name="maTrangThai" class="form-select">
<option value="3" ${t.maTrangThai==3?'selected':''}>Đang ra</option>
<option value="1" ${t.maTrangThai==1?'selected':''}>Hoàn thành</option>
<option value="2" ${t.maTrangThai==2?'selected':''}>Khóa</option>
</select>

</div>

<div class="modal-footer">
<button class="btn btn-warning">Lưu</button>
</div>

</form>
</div>
</div>
</c:forEach>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
