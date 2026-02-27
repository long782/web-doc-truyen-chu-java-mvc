<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Quản lý tài khoản</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet">
</head>

<body style="background:#dcdcdc">
<div class="container-fluid">
<div class="row">

<!-- SIDEBAR -->
<div class="col-2 bg-dark text-white min-vh-100 p-3">
  <div class="text-center mb-4">
    <i class="bi bi-shield-lock fs-1"></i>
    <h5 class="mt-2">ADMIN</h5>
  </div>

  <div class="list-group list-group-flush">
    <a href="AdminController" class="list-group-item list-group-item-action bg-dark text-white active">
      <i class="bi bi-people"></i> Quản lý tài khoản
    </a>
    <a href="AdminQuanLyTruyenController" class="list-group-item list-group-item-action bg-dark text-white">
      <i class="bi bi-book"></i> Quản lý truyện
    </a>
    <a href="AdminThongKeController" class="list-group-item list-group-item-action bg-dark text-white">
      <i class="bi bi-bar-chart"></i> Thống kê
    </a>
    <a href="DangXuatController" class="list-group-item list-group-item-action bg-dark text-danger">
      <i class="bi bi-box-arrow-right"></i> Đăng xuất
    </a>
  </div>
</div>

<!-- CONTENT -->
<div class="col-10 p-4">

<h4>Quản lý tài khoản</h4>

<form class="row g-2 mb-3">
  <div class="col-4">
    <input class="form-control" name="keyword" placeholder="Tìm tài khoản...">
  </div>
  <div class="col">
    <button class="btn btn-primary">Tìm</button>
  </div>
</form>

<c:if test="${not empty sessionScope.msg}">
  <div class="alert alert-info">${sessionScope.msg}</div>
  <c:remove var="msg" scope="session"/>
</c:if>

<table class="table table-bordered bg-white">
<thead class="table-secondary">
<tr>
  <th>ID</th>
  <th>Tên TK</th>
  <th>Email</th>
  <th>Vai trò</th>
  <th>Hành động</th>
</tr>
</thead>

<tbody>
<c:forEach items="${list}" var="tk">
<tr>
  <td>${tk.maTaiKhoan}</td>
  <td>${tk.tenTaiKhoan}</td>
  <td>${tk.email}</td>
  <td>${tk.maVaiTro == 2 ? "Admin" : "User"}</td>
  <td>
    <!-- SỬA -->
    <button class="btn btn-warning btn-sm"
            data-bs-toggle="modal"
            data-bs-target="#edit${tk.maTaiKhoan}">
      <i class="bi bi-pencil"></i>
    </button>

    <!-- XÓA -->
    <form method="post" style="display:inline">
      <input type="hidden" name="action" value="delete">
      <input type="hidden" name="id" value="${tk.maTaiKhoan}">
      <button class="btn btn-danger btn-sm"
              onclick="return confirm('Xóa tài khoản này?')">
        <i class="bi bi-trash"></i>
      </button>
    </form>
  </td>
</tr>
</c:forEach>
</tbody>
</table>


<c:forEach items="${list}" var="tk">
<div class="modal fade" id="edit${tk.maTaiKhoan}" tabindex="-1">
<div class="modal-dialog">
<div class="modal-content">

<form method="post">
<input type="hidden" name="action" value="update">
<input type="hidden" name="maTaiKhoan" value="${tk.maTaiKhoan}">

<div class="modal-header">
  <h5 class="modal-title">Chỉnh sửa tài khoản</h5>
  <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
</div>

<div class="modal-body">

<div class="mb-2">
  <label>Tên tài khoản</label>
  <input class="form-control" value="${tk.tenTaiKhoan}" readonly>
  <input type="hidden" name="tenTaiKhoan" value="${tk.tenTaiKhoan}">
</div>

<div class="mb-2">
  <label>Email</label>
  <input class="form-control" name="email" value="${tk.email}">
</div>

<div class="mb-2">
  <label>Mật khẩu</label>
  <input class="form-control" name="matKhau" value="${tk.matKhau}">
</div>

<div class="mb-2">
  <label>Vai trò</label>
  <select name="maVaiTro" class="form-select">
    <option value="1" ${tk.maVaiTro==1?"selected":""}>User</option>
    <option value="2" ${tk.maVaiTro==2?"selected":""}>Admin</option>
  </select>
</div>

</div>

<div class="modal-footer">
  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
  <button class="btn btn-success">Lưu</button>
</div>

</form>

</div>
</div>
</div>
</c:forEach>

</div>
</div>
</div>

<!-- BẮT BUỘC CÓ -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
