<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Chi tiết truyện</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">

<style>
body { background:#dcdcdc; }
main { padding-top:30px; }
.sidebar a.active { background:#198754;color:#fff; }
</style>
</head>
<body>

<jsp:include page="header.jsp"/>

<main>
<div class="container-fluid px-4">
<div class="row justify-content-center">

<!-- ========== SIDEBAR ========== -->
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
<!-- ========== CONTENT ========== -->
<div class="col-md-9 col-lg-8">
<div class="bg-white rounded-3 p-4">

<div class="d-flex justify-content-between mb-3">
    <h4 class="fw-bold">Danh sách chương</h4>
    <button class="btn btn-success"
            data-bs-toggle="modal"
            data-bs-target="#modalThem">
        <i class="bi bi-plus-circle"></i> Thêm chương
    </button>
</div>

<table class="table table-hover align-middle">
<thead class="table-light">
<tr>
    <th>#</th>
    <th>Tên chương</th>
    <th>Ngày đăng</th>
    <th>Lượt xem</th>
    <th class="text-end">Thao tác</th>
</tr>
</thead>

<tbody>
<c:forEach var="c" items="${dsChuong}" varStatus="st">
<tr>
    <td>${st.index + 1}</td>
    <td>${c.tenChuong}</td>
    <td>
        <fmt:formatDate value="${c.ngayDang}" pattern="dd/MM/yyyy"/>
    </td>
    <td>${c.luotXem}</td>
    <td class="text-end">

        <button class="btn btn-sm btn-warning"
                data-bs-toggle="modal"
                data-bs-target="#modalSua${c.maChuong}">
            <i class="bi bi-pencil"></i>
        </button>

        <form method="post"
              action="ChiTietTruyenController"
              class="d-inline">
            <input type="hidden" name="action" value="xoa">
            <input type="hidden" name="maTruyen" value="${maTruyen}">
            <input type="hidden" name="maChuong" value="${c.maChuong}">
            <button class="btn btn-sm btn-danger"
                    onclick="return confirm('Xóa chương này?')">
                <i class="bi bi-trash"></i>
            </button>
        </form>
    </td>
</tr>
</c:forEach>

<c:if test="${empty dsChuong}">
<tr>
    <td colspan="5" class="text-center text-muted">
        Chưa có chương nào.
    </td>
</tr>
</c:if>
</tbody>
</table>

</div>
</div>
</div>
</div>
</main>

<!-- ========== MODAL THÊM CHƯƠNG ========== -->
<div class="modal fade" id="modalThem">
<div class="modal-dialog modal-lg">
<form class="modal-content" method="post" action="ChiTietTruyenController">

<input type="hidden" name="action" value="them">
<input type="hidden" name="maTruyen" value="${maTruyen}">

<div class="modal-header">
    <h5 class="modal-title">Thêm chương mới</h5>
    <button class="btn-close" data-bs-dismiss="modal"></button>
</div>

<div class="modal-body">
<input class="form-control mb-2" name="tenChuong" placeholder="Tên chương" required>
<input class="form-control mb-2" name="soThuTu" placeholder="Số thứ tự" required>
<textarea class="form-control" name="noiDung" rows="8" required></textarea>
</div>

<div class="modal-footer">
<button class="btn btn-success">Thêm</button>
</div>

</form>
</div>
</div>

<!-- ========== MODAL SỬA CHƯƠNG ========== -->
<c:forEach var="c" items="${dsChuong}">
<div class="modal fade" id="modalSua${c.maChuong}">
<div class="modal-dialog modal-lg">
<form class="modal-content" method="post" action="ChiTietTruyenController">

<input type="hidden" name="action" value="sua">
<input type="hidden" name="maTruyen" value="${maTruyen}">
<input type="hidden" name="maChuong" value="${c.maChuong}">

<div class="modal-header">
<h5 class="modal-title">Sửa chương</h5>
<button class="btn-close" data-bs-dismiss="modal"></button>
</div>

<div class="modal-body">
<input class="form-control mb-2" name="tenChuong" value="${c.tenChuong}" required>
<input class="form-control mb-2" name="soThuTu" value="${c.soThuTu}" required>
<textarea class="form-control" name="noiDung" rows="8" required>
${c.noiDung}
</textarea>
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
