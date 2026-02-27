<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Quản lý truyện</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css"
	rel="stylesheet">
</head>

<body style="background: #dcdcdc">
	<div class="container-fluid">
		<div class="row">

			<!-- SIDEBAR -->
			<div class="col-2 bg-dark text-white min-vh-100 p-3">
				<div class="text-center mb-4">
					<i class="bi bi-shield-lock fs-1"></i>
					<h5 class="mt-2">ADMIN</h5>
				</div>

				<div class="list-group list-group-flush">
					<a href="AdminController"
						class="list-group-item list-group-item-action bg-dark text-white">
						<i class="bi bi-people"></i> Quản lý tài khoản
					</a> <a href="AdminQuanLyTruyenController"
						class="list-group-item list-group-item-action bg-dark text-white active">
						<i class="bi bi-book"></i> Quản lý truyện
					</a> <a href="AdminThongKeController"
						class="list-group-item list-group-item-action bg-dark text-white">
						<i class="bi bi-bar-chart"></i> Thống kê
					</a> <a href="DangXuatController"
						class="list-group-item list-group-item-action bg-dark text-danger">
						<i class="bi bi-box-arrow-right"></i> Đăng xuất
					</a>
				</div>
			</div>

			<!-- CONTENT -->
			<div class="col-10 p-4">

				<h4 class="mb-3">Quản lý truyện</h4>

				<!-- SEARCH -->
				<form class="row g-2 mb-3" method="get">
					<div class="col-4">
						<input class="form-control" name="keyword"
							value="${param.keyword}"
							placeholder="Tìm theo tên truyện hoặc tác giả">
					</div>
					<div class="col">
						<button class="btn btn-primary">
							<i class="bi bi-search"></i> Tìm
						</button>
					</div>
				</form>

				<!-- TABLE -->
				<table class="table table-bordered bg-white align-middle">
					<thead class="table-secondary text-center">
						<tr>
							<th style="width: 80px">ID</th>
							<th>Tên truyện</th>
							<th>Tác giả</th>
							<th style="width: 130px">Trạng thái</th>
							<th style="width: 140px">Hành động</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${list}" var="t">
							<tr>
								<td class="text-center">${t.maTruyen}</td>

								<td>${t.tenTruyen}</td>

								<td>${t.tenTaiKhoan}</td>

								<!-- TRẠNG THÁI -->
								<td class="text-center"><span
									class="badge ${t.maTrangThai == 2 ? 'bg-danger' : 'bg-success'}">
										${t.maTrangThai == 2 ? 'Bị khóa' : 'Đang ra'} </span></td>

								<!-- HÀNH ĐỘNG -->
								<td class="text-center">
									<!-- KHÓA / MỞ -->
									<form method="post" style="display: inline">
										<input type="hidden" name="action" value="toggle"> <input
											type="hidden" name="maTruyen" value="${t.maTruyen}">
										<input type="hidden" name="maTrangThai"
											value="${t.maTrangThai}">
										<button class="btn btn-warning btn-sm"
											title="${t.maTrangThai == 2 ? 'Mở khóa' : 'Khóa'}">
											<i class="bi ${t.maTrangThai == 2 ? 'bi-unlock' : 'bi-lock'}"></i>
										</button>
									</form> <!-- XÓA -->
									<form method="post" style="display: inline"
										onsubmit="return confirm('Bạn có chắc muốn xóa truyện này?')">
										<input type="hidden" name="action" value="delete"> <input
											type="hidden" name="maTruyen" value="${t.maTruyen}">
										<button class="btn btn-danger btn-sm" title="Xóa truyện">
											<i class="bi bi-trash"></i>
										</button>
									</form>

								</td>
							</tr>
						</c:forEach>

						<c:if test="${empty list}">
							<tr>
								<td colspan="5" class="text-center text-muted">Không có
									truyện nào</td>
							</tr>
						</c:if>

					</tbody>
				</table>

			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
