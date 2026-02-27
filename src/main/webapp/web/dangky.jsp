<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Đăng ký</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="d-flex justify-content-center align-items-center vh-100">
  <div class="card shadow p-4" style="min-width: 300px; max-width: 400px; width: 100%;">
    <h3 class="text-center mb-4">Đăng ký tài khoản</h3>

    <!-- Lỗi -->
    <c:if test="${not empty error}">
        <div class="alert alert-danger py-2">
            ${error}
        </div>
    </c:if>

    <!-- Thành công -->
    <c:if test="${not empty success}">
        <div class="alert alert-success py-2">
            ${success}
        </div>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/DangKyController">

      <div class="mb-3">
        <label class="form-label">Tên tài khoản <span class="text-danger">*</span></label>
        <input type="text"
               class="form-control"
               name="tenTaiKhoan"
               value="${param.tenTaiKhoan}"
               placeholder="Nhập tên tài khoản"
               required maxlength="50">
      </div>

      <div class="mb-3">
        <label class="form-label">Mật khẩu <span class="text-danger">*</span></label>
        <input type="password"
               class="form-control"
               name="matKhau"
               placeholder="Nhập mật khẩu"
               required maxlength="50">
      </div>

      <div class="mb-3">
        <label class="form-label">Nhập lại mật khẩu <span class="text-danger">*</span></label>
        <input type="password"
               class="form-control"
               name="confirmMatKhau"
               placeholder="Nhập lại mật khẩu"
               required maxlength="50">
      </div>

      <div class="d-grid mb-3">
        <button type="submit" class="btn btn-success">Đăng ký</button>
      </div>

      <div class="text-center">
        <a href="${pageContext.request.contextPath}/DangNhapController"
           class="text-decoration-none">
           Đã có tài khoản? Đăng nhập
        </a>
      </div>

    </form>
  </div>
</div>

</body>
</html>
