<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Cá nhân</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">

<style>
body {
    background:#dcdcdc;
}

main {
    padding-top: 30px;
}

.sidebar a.active {
    background-color: #198754;
    color: #fff;
}

/* CARD THÔNG TIN */
.profile-card {
    padding: 3rem;
}

/* mở rộng nội dung */
.profile-content {
    width: 100%;
    max-width: 950px;   /* ⬅️ TĂNG RỘNG */
}
</style>
</head>
<body>

<jsp:include page="header.jsp"/>

<main>
<div class="container-fluid px-4">  <!-- ⬅️ container-fluid -->
<div class="row justify-content-center">

<!-- ================= SIDEBAR ================= -->
<div class="col-md-3 col-lg-2">
    <div class="list-group sidebar">
        <a href="CaNhanController"
           class="list-group-item list-group-item-action active">
            <i class="bi bi-person"></i> Thông tin cá nhân
        </a>

        <a href="TruyenLuuController"
           class="list-group-item list-group-item-action">
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

<!-- ================= CONTENT ================= -->
<div class="col-md-9 col-lg-8">
    <div class="bg-white rounded-3 profile-card
                d-flex justify-content-center">

        <div class="profile-content text-center">

            <!-- AVATAR -->
            <img src="${empty taiKhoan.duongDanAnh
                        ? pageContext.request.contextPath.concat('/img/ava3.jpg')
                        : taiKhoan.duongDanAnh}"
                 class="rounded-circle mb-4"
                 width="160" height="160"
                 style="object-fit:cover">

            <!-- INFO -->
            <h3 class="fw-bold mb-3">
                ${taiKhoan.tenTaiKhoan}
            </h3>

            <p class="fs-5 mb-2">
                <strong>Email:</strong>
                <c:out value="${taiKhoan.email != null ? taiKhoan.email : 'Chưa có'}"/>
            </p>

            <p class="fs-5 mb-4">
                <strong>Mật khẩu:</strong> ******
            </p>

            <!-- EDIT BUTTON -->
            <button class="btn btn-outline-success px-4 py-2"
                    data-bs-toggle="collapse"
                    data-bs-target="#formEdit">
                <i class="bi bi-pencil"></i> Chỉnh sửa
            </button>

            <!-- EDIT FORM -->
            <div class="collapse mt-4" id="formEdit">
                <form method="post" class="text-start">

                    <div class="mb-3">
                        <label class="form-label fw-semibold">
                            Tên tài khoản
                        </label>
                        <input type="text"
                               class="form-control form-control-lg"
                               value="${taiKhoan.tenTaiKhoan}"
                               disabled>
                    </div>

                    <div class="mb-3">
                        <label class="form-label fw-semibold">
                            Email
                        </label>
                        <input type="email"
                               name="email"
                               class="form-control form-control-lg"
                               value="${taiKhoan.email}">
                    </div>

                    <div class="mb-3">
                        <label class="form-label fw-semibold">
                            Mật khẩu
                        </label>
                        <input type="password"
                               name="matKhau"
                               class="form-control form-control-lg"
                               value="${taiKhoan.matKhau}">
                    </div>

                    <button class="btn btn-success w-100 py-2 fs-5">
                        <i class="bi bi-check2-circle"></i> Lưu thay đổi
                    </button>

                </form>
            </div>

        </div>
    </div>
</div>

</div>
</div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
