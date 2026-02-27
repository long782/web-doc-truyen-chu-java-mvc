<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Trang chủ</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"
	rel="stylesheet">

<style>
body {
	background-color: #dcdcdc;
}

/* Ẩn scrollbar thể loại */
.hide-scrollbar::-webkit-scrollbar {
	display: none;
}

.hide-scrollbar {
	-ms-overflow-style: none;
	scrollbar-width: none;
}

/* ===== CARD TRUYỆN ===== */
.story-card {
	border-radius: 12px;
	overflow: hidden;
}

.story-img-wrapper {
	width: 100%;
	height: 180px;
	overflow: hidden;
}

.story-img-wrapper img {
	width: 100%;
	height: 100%;
	object-fit: cover;
	transition: transform .3s ease;
}

.story-card:hover img {
	transform: scale(1.05);
}

.story-body {
	padding: 10px 12px;
}

.story-title {
	font-weight: 600;
	font-size: 15px;
}

.story-author {
	font-size: 13px;
	color: #6c757d;
}

.story-desc {
	font-size: 13px;
	color: #444;
}

/* ===== TOP LIST ===== */
.rank-list {
	list-style: none;
	padding: 0;
	margin: 0;
}

.rank-item {
	display: flex;
	align-items: center;
	padding: 10px 8px;
	border-bottom: 1px solid #dee2e6;
}

.rank-badge {
	width: 28px;
	height: 28px;
	border-radius: 50%;
	background: #198754;
	color: #fff;
	font-weight: bold;
	font-size: 14px;
	display: flex;
	align-items: center;
	justify-content: center;
	margin-right: 10px;
	flex-shrink: 0;
}

.rank-title {
	font-weight: 600;
	font-size: 15px;
}
</style>
</head>

<body>

	<jsp:include page="/web/header.jsp" />


    <!-- CAROUSEL -->
    <div class="container-fluid mt-1 px-0" style="max-width:1140px;">
        <div id="carouselExample" class="carousel slide">
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#carouselExample" data-bs-slide-to="0" class="active"></button>
                <button type="button" data-bs-target="#carouselExample" data-bs-slide-to="1"></button>
                <button type="button" data-bs-target="#carouselExample" data-bs-slide-to="2"></button>
            </div>

            <div class="carousel-inner">
                <div class="carousel-item active" style="height:350px;">
                    <img src="${pageContext.request.contextPath}/img/anh1.jpg" class="w-100 h-100"
                        style="object-fit:cover;" alt="Slide 1">
                </div>
                <div class="carousel-item" style="height:350px;">
                    <img src="${pageContext.request.contextPath}/img/cr2.jpg" class="w-100 h-100"
                        style="object-fit:cover;" alt="Slide 2">
                </div>
                <div class="carousel-item" style="height:350px;">
                    <img src="${pageContext.request.contextPath}/img/cr3.jpg" class="w-100 h-100"
                        style="object-fit:cover;" alt="Slide 3">
                </div>
            </div>

            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
                <span class="carousel-control-prev-icon"></span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
                <span class="carousel-control-next-icon"></span>
            </button>
        </div>
    </div>


	<!-- MỚI CẬP NHẬT -->
	<div class="container mt-3" style="max-width: 1140px;">
		<div class="d-flex justify-content-between bg-white rounded-3 p-3">
			<h4 class="fw-bold mb-0">Mới cập nhật</h4>
			<a href="#" class="text-decoration-none">Xem thêm</a>
		</div>

		<div class="row row-cols-1 row-cols-md-2 g-3 mt-2">
			<c:forEach var="t" items="${moiCapNhat}">
				<div class="col">
					<div class="card story-card h-100">
						<div class="row g-0">
							<div class="col-4">
								<div class="story-img-wrapper">
									<a href="TruyenController?maTruyen=${t.maTruyen}"> <img
										src="${t.duongDanAnh}">
									</a>

								</div>
							</div>
							<div class="col-8">
								<div class="story-body">
									<div class="story-title text-truncate fs-5 fw-semibold">
										<a href="TruyenController?maTruyen=${t.maTruyen}"
											class="text-decoration-none text-dark"> ${t.tenTruyen} </a>
									</div>

									<div class="story-author fs-6">
										<i class="bi bi-person-circle fs-6"></i> ${t.tenTaiKhoan}
									</div>
									<div class="story-desc text-truncate">${t.gioiThieu}</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<!-- BTV ĐỀ CỬ -->
	<div class="container mt-3 bg-white rounded-3 pb-3"
		style="max-width: 1140px;">
		<div class="d-flex justify-content-between p-3">
			<h4 class="fw-bold mb-0">BTV đề cử</h4>
			<a href="#" class="text-decoration-none">Xem thêm</a>
		</div>

		<div class="row row-cols-2 row-cols-md-4 row-cols-lg-6 g-3 px-3">
			<c:forEach var="t" items="${btvDeCu}">
				<div class="col">
					<div class="card story-card text-center">
						<div class="story-img-wrapper" style="height: 220px">
							<img src="${t.duongDanAnh}">
						</div>
						<div class="story-body">
							<div class="story-title text-truncate fs-5 fw-semibold">${t.tenTruyen}</div>
							<div class="story-author fs-6">${t.tenTaiKhoan}</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<!-- TOP -->
	<div class="container mt-3" style="max-width: 1140px;">
		<div class="row g-3">
			<div class="col-md-6">
				<div class="bg-white rounded-3 p-3">
					<h4 class="fw-bold text-center">Top lượt xem</h4>
					<ul class="rank-list">
						<c:forEach var="t" items="${topLuotXem}" varStatus="st">
							<li class="rank-item">
								<div class="rank-badge">${st.index + 1}</div>
								<div class="rank-title fs-5 fw-semibold">${t.tenTruyen}</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>

			<div class="col-md-6">
				<div class="bg-white rounded-3 p-3">
					<h4 class="fw-bold text-center">Truyện hot</h4>
					<ul class="rank-list">
						<c:forEach var="t" items="${truyenHot}" varStatus="st">
							<li class="rank-item">
								<div class="rank-badge bg-danger">${st.index + 1}</div>
								<div class="rank-title fs-5 fw-semibold">${t.tenTruyen}</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<!-- MỚI HOÀN THÀNH -->
	<div class="container mt-3" style="max-width: 1140px;">
		<div class="d-flex justify-content-between bg-white rounded-3 p-3">
			<h4 class="fw-bold mb-0">Mới hoàn thành</h4>
			<a href="#" class="text-decoration-none">Xem thêm</a>
		</div>

		<div class="row row-cols-2 row-cols-md-4 g-3 mt-2">
			<c:forEach var="t" items="${moiHoanThanh}">
				<div class="col">
					<div class="card story-card h-100">
						<div class="story-img-wrapper" style="height: 220px">
							<img src="${t.duongDanAnh}">
						</div>
						<div class="story-body">
							<div class="story-title text-truncate fs-5 fw-semibold">${t.tenTruyen}</div>
							<div class="story-author fs-6	">
								<i class="bi bi-person-circle fs-6"></i> ${t.tenTaiKhoan}
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<!-- FOOTER -->
	<footer class="bg-dark text-white text-center py-4 mt-5">
		<p class="fw-bold fs-5">Liên hệ</p>
		<p>Email: example@email.com</p>
		<p>SĐT: 0123 456 789</p>
		<p>Địa chỉ: 123 Đường ABC, Huế</p>
	</footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
