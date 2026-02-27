<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Đăng nhập</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<div class="d-flex justify-content-center align-items-center vh-100">
  <div class="card shadow p-4" style="min-width: 300px; max-width: 400px; width: 100%;">
    <h3 class="text-center mb-4">Đăng nhập</h3>

    <!-- Thông báo lỗi -->
    <c:if test="${not empty error}">
        <div class="alert alert-danger py-2">
            ${error}
        </div>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/DangNhapController">
      <div class="mb-3">
        <label for="username" class="form-label">Tên đăng nhập</label>
        <input type="text"
               class="form-control"
               id="username"
               name="username"
               value="${param.username}"
               placeholder="Nhập tên đăng nhập"
               required>
      </div>

      <div class="mb-3">
        <label for="password" class="form-label">Mật khẩu</label>
        <input type="password"
               class="form-control"
               id="password"
               name="password"
               placeholder="Nhập mật khẩu"
               required>
      </div>

      <div class="d-grid mb-3">
        <button type="submit" class="btn btn-primary">Đăng nhập</button>
      </div>

      <div class="text-center">
        <a href="${pageContext.request.contextPath}/DangKyController"
           class="text-decoration-none">
           Chưa có tài khoản? Đăng ký
        </a>
      </div>
    </form>
  </div>
</div>

</body>
</html>
