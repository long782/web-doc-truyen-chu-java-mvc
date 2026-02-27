<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>${truyen.tenTruyen}</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"
	rel="stylesheet">

<style>
body {
	background: #dcdcdc
}

.custom-outline-hover:hover {
	background-color: transparent !important;
	color: #146c43;
	border-color: #146c43;
}

.btn-rating-wrapper {
	position: relative;
	display: inline-block;
}

.rating-badge {
	position: absolute;
	top: -8px;
	right: -8px;
	background-color: #28a745;
	color: black;
	width: 24px;
	height: 24px;
	font-size: 10px;
	font-weight: 700;
	border-radius: 50%;
	text-align: center;
	line-height: 24px;
}
</style>
</head>

<body>

	<jsp:include page="header.jsp" />

	<!-- ================= THÔNG TIN TRUYỆN ================= -->
	<div class="container mt-3" style="max-width: 1140px;">
		<div class="row">

			<!-- Ảnh -->
			<div class="col-12 col-md-2 mb-3">
				<img src="${truyen.duongDanAnh}" class="img-fluid rounded-3"
					style="height: 250px; object-fit: cover">
			</div>

			<!-- Nội dung -->
			<div class="col-12 col-md-10 d-flex flex-column">
				<h4 class="mb-1">${truyen.tenTruyen}</h4>

				<p class="text-muted fs-6 mb-0 mt-1">Tác giả:
					${truyen.tenTaiKhoan}</p>
				<p class="fs-6 mb-0 mt-1">
					Tình trạng:
					<c:choose>
						<c:when test="${truyen.maTrangThai == 1}">Đang ra</c:when>
						<c:otherwise>Hoàn thành</c:otherwise>
					</c:choose>
				</p>

				<!-- Nút hành động -->
				<div class="mt-4 d-flex flex-wrap align-items-center gap-2">

					<c:if test="${sessionScope.taiKhoan != null}">
						<form method="post" class="d-inline">
							<input type="hidden" name="maTruyen" value="${truyen.maTruyen}">
							<input type="hidden" name="action" value="luu">

							<button
								class="btn
        ${daLuu ? 'btn-success' : 'btn-outline-dark'}
        custom-outline-hover">

								<i
									class="bi
            ${daLuu ? 'bi-bookmark-fill' : 'bi-bookmark'}"></i>

								${daLuu ? 'Đã lưu' : 'Lưu truyện'}
							</button>
						</form>
					</c:if>

					<button class="btn btn-outline-dark custom-outline-hover"
						data-bs-toggle="modal" data-bs-target="#modalChapters">
						<i class="bi bi-list-ul"></i> Danh sách chương
					</button>

					<!-- Đánh giá -->
					<div class="btn-rating-wrapper">
						<button class="btn btn-outline-dark custom-outline-hover"
							data-bs-toggle="tab" data-bs-target="#dg">
							<i class="bi bi-star"></i> Đánh giá
						</button>
						<span class="rating-badge">${diemTB}</span>
					</div>

				</div>

				<!-- Thông tin thêm -->
				<div class="mt-4">
					<span>${fn:length(dsChuong)} chương</span> <span class="ms-3">500
						lượt đọc</span>
				</div>

				<!-- Thể loại -->
				<div class="mt-2">
					<button class="btn btn-outline-success rounded-pill me-2" disabled>
						<c:choose>
							<c:when test="${truyen.maTrangThai == 1}">Đang ra</c:when>
							<c:otherwise>Hoàn thành</c:otherwise>
						</c:choose>
					</button>

					<c:forEach var="tl" items="${dsTheLoai}">
						<a href="TimKiemController?maTheLoai=${tl.maTheLoai}"
							class="btn btn-outline-success rounded-pill me-2">
							${tl.tenTheLoai} </a>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>

	<!-- ================= GIỚI THIỆU ================= -->
	<div class="container mt-4 bg-white rounded-3"
		style="max-width: 1140px;">
		<div class="border-bottom p-3 fw-bold fs-4">Giới thiệu</div>
		<div class="p-3" style="white-space: pre-line">
			${truyen.gioiThieu}</div>
	</div>


	<!-- ================= CHƯƠNG MỚI CẬP NHẬT ================= -->
	<div class="container mt-4 bg-white my-1 rounded-3"
		style="max-width: 1140px;">

		<div
			class="d-flex justify-content-between align-items-center
                border-bottom border-1 border-secondary-subtle"
			style="height: 60px;">
			<p class="mb-0 fs-4 mx-3 fw-bold">Mới cập nhật</p>

			<a href="#modalChapters"
				class="text-decoration-none text-reset mb-0 fs-6 mx-3"
				data-bs-toggle="modal"> Xem thêm <i
				class="bi bi-chevron-double-right"></i>
			</a>
		</div>

		<div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 mt-2 gx-2">

			<c:choose>
				<c:when test="${empty dsChuong}">
					<div class="col px-0">
						<div class="text-muted ms-3 my-2">Chưa có chương nào.</div>
					</div>
				</c:when>

				<c:otherwise>
					<!-- Hiển thị 6 chương mới nhất -->
					<c:forEach var="c" items="${dsChuong}" end="5">
						<div
							class="col border-bottom border-1 border-secondary-subtle px-0">
							<a href="ChuongController?maChuong=${c.maChuong}"
								class="fs-5 text-decoration-none text-dark ms-3 mx-3">
								Chương ${c.soThuTu}: ${c.tenChuong} </a>
							<p class="text-muted ms-3">
								<fmt:formatDate value="${c.ngayDang}" pattern="dd/MM/yyyy HH:mm" />
							</p>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>

		</div>
	</div>


	<!-- ================= BÌNH LUẬN + ĐÁNH GIÁ ================= -->
	<div class="container mt-4 bg-white rounded-3"
		style="max-width: 1140px;">

		<ul class="nav nav-tabs">
			<li class="nav-item">
				<button class="nav-link active" data-bs-toggle="tab"
					data-bs-target="#bl">Bình luận</button>
			</li>
			<li class="nav-item">
				<button class="nav-link" data-bs-toggle="tab" data-bs-target="#dg">
					Đánh giá</button>
			</li>
		</ul>

		<div class="tab-content p-3">

			<!-- ===== BÌNH LUẬN ===== -->
			<div class="tab-pane fade show active" id="bl">

				<c:if test="${sessionScope.taiKhoan != null}">
					<form method="post" class="d-flex mb-3">
						<input type="hidden" name="maTruyen" value="${truyen.maTruyen}">
						<img src="${pageContext.request.contextPath}/img/ava3.jpg"
							width="40" height="40" class="me-2 rounded-circle">
						<textarea class="form-control" name="ndBinhLuan" rows="2"
							maxlength="2000" placeholder="Bình luận của bạn..."></textarea>
						<button class="btn btn-outline-primary ms-2" type="submit">
							<i class="bi bi-send"></i>
						</button>
					</form>
				</c:if>


				<c:forEach var="b" items="${dsBinhLuan}">
					<div class="bg-light p-2 rounded mb-2">
						<div class="d-flex mb-1">
							<img src="${pageContext.request.contextPath}/img/ava3.jpg"
								width="40" height="40" class="me-2 rounded-circle">
							<div>
								<strong>${b.tenTaiKhoan}</strong>
								<p class="mb-1" style="white-space: pre-line;">
									${b.noiDungBinhLuan}</p>
								<small class="text-muted"> <fmt:formatDate
										value="${b.ngayBinhLuan}" pattern="dd/MM/yyyy HH:mm" />
								</small>
							</div>
						</div>
					</div>
				</c:forEach>

				<c:if test="${empty dsBinhLuan}">
					<div class="text-muted">Hãy là người bình luận đầu tiên!</div>
				</c:if>


			</div>

			<!-- ===== ĐÁNH GIÁ ===== -->
			<div class="tab-pane fade" id="dg">

				<div class="mb-3">
					<strong>Điểm trung bình:</strong> <span class="text-warning fs-5">${diemTB}</span>/5
				</div>

				<c:if test="${sessionScope.taiKhoan != null}">
					<form method="post" class="mb-3">
						<input type="hidden" name="maTruyen" value="${truyen.maTruyen}">
						<input type="range" class="form-range" name="soSao" min="1"
							max="5" step="0.5" value="5">
						<textarea class="form-control" name="ndDanhGia" rows="3"
							placeholder="Nhận xét của bạn..."></textarea>
						<button class="btn btn-success mt-2">
							<i class="bi bi-send"></i> Gửi đánh giá
						</button>
					</form>
				</c:if>

				<c:forEach var="dg" items="${dsDanhGia}">
					<div class="mb-3 border-bottom pb-2">
						<strong> #${dg.tenTaiKhoan}</strong>
						<div class="text-warning">
							<c:forEach begin="1" end="5" var="i">
								<i class="bi ${i <= dg.soSao ? 'bi-star-fill' : 'bi-star'}"></i>
							</c:forEach>
							<span class="text-dark ms-1">${dg.soSao}</span>
						</div>
						<p class="mb-1">${dg.noiDungDanhGia}</p>
						<small class="text-muted"> <fmt:formatDate
								value="${dg.ngayDanhGia}" pattern="dd/MM/yyyy HH:mm" />
						</small>
					</div>
				</c:forEach>

			</div>
		</div>
	</div>

	<!-- ================= MODAL DANH SÁCH CHƯƠNG ================= -->
	<div class="modal fade" id="modalChapters">
		<div class="modal-dialog modal-lg modal-dialog-scrollable">
			<div class="modal-content">

				<div class="modal-header">
					<h5 class="modal-title">Danh sách chương – ${truyen.tenTruyen}</h5>
					<button class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<div class="modal-body">
					<div class="row row-cols-1 row-cols-md-2">
						<c:forEach var="c" items="${dsChuong}">
							<div class="col border-bottom py-2">
								<a href="ChuongController?maChuong=${c.maChuong}"
									class="text-decoration-none text-dark fw-semibold"> Chương
									${c.soThuTu}: ${c.tenChuong} </a>
								<div class="text-muted small">
									<fmt:formatDate value="${c.ngayDang}"
										pattern="dd/MM/yyyy HH:mm" />
								</div>
							</div>
						</c:forEach>
					</div>
				</div>

				<div class="modal-footer">
					<button class="btn btn-success" data-bs-dismiss="modal">Đóng</button>
				</div>

			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
