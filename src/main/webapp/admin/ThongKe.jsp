<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Thống kê</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
      rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css"
      rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"
      rel="stylesheet">
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
    <a href="AdminController"
       class="list-group-item list-group-item-action bg-dark text-white">
      <i class="bi bi-people"></i> Quản lý tài khoản
    </a>

    <a href="AdminQuanLyTruyenController"
       class="list-group-item list-group-item-action bg-dark text-white">
      <i class="bi bi-book"></i> Quản lý truyện
    </a>

    <a href="AdminThongKeController"
       class="list-group-item list-group-item-action bg-dark text-white active">
      <i class="bi bi-bar-chart"></i> Thống kê
    </a>

    <a href="DangXuatController"
       class="list-group-item list-group-item-action bg-dark text-danger">
      <i class="bi bi-box-arrow-right"></i> Đăng xuất
    </a>
  </div>
</div>

<!-- CONTENT -->
<div class="col-10 p-4">

<h4 class="mb-4">Thống kê hệ thống</h4>

<!-- CÁC Ô THỐNG KÊ -->
<div class="container-fluid px-0">
  <div class="row g-4">

    <!-- LƯỢT XEM HÔM NAY -->
    <div class="col-sm-6 col-xl-4">
      <div class="bg-light rounded p-4 d-flex align-items-center">
        <i class="fa fa-eye fa-2x text-primary me-3"></i>
        <div>
          <p class="mb-1">Lượt xem hôm nay</p>
          <h6 class="mb-0">
            <fmt:formatNumber value="${luotXemHN}" groupingUsed="true"/>
          </h6>
        </div>
      </div>
    </div>

    <!-- SỐ CHƯƠNG HÔM NAY -->
    <div class="col-sm-6 col-xl-4">
      <div class="bg-light rounded p-4 d-flex align-items-center">
        <i class="fa fa-book fa-2x text-primary me-3"></i>
        <div>
          <p class="mb-1">Chương đăng hôm nay</p>
          <h6 class="mb-0">${soChuongHN}</h6>
        </div>
      </div>
    </div>

    <!-- TỔNG LƯỢT XEM -->
    <div class="col-sm-6 col-xl-4">
      <div class="bg-light rounded p-4 d-flex align-items-center">
        <i class="fa fa-chart-line fa-2x text-primary me-3"></i>
        <div>
          <p class="mb-1">Tổng lượt xem</p>
          <h6 class="mb-0">
            <fmt:formatNumber value="${tongLuotXem}" groupingUsed="true"/>
          </h6>
        </div>
      </div>
    </div>

  </div>
</div>

<!-- BIỂU ĐỒ -->
<div class="container-fluid pt-4 px-0">
  <div class="bg-light rounded p-4">
    <h6 class="mb-3 text-center">Số chương đăng trong 28 ngày gần nhất</h6>

    <canvas id="line-chart"
      data-labels="
        <c:forEach var='t' items='${tk28}'>
          <fmt:formatDate value='${t.ngay}' pattern='dd/MM'/>,
        </c:forEach>"
      data-values="
        <c:forEach var='t' items='${tk28}'>
          ${t.soChuong},
        </c:forEach>">
    </canvas>

  </div>
</div>

</div> <!-- /CONTENT -->
</div>
</div>

<!-- JS -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js"></script>

<script>
const canvas = document.getElementById("line-chart");
const labels = canvas.dataset.labels.split(",").filter(x => x.trim() !== "");
const values = canvas.dataset.values.split(",").filter(x => x.trim() !== "");

new Chart(canvas, {
  type: "line",
  data: {
    labels: labels,
    datasets: [{
      label: "Số chương",
      data: values,
      borderColor: "blue",
      backgroundColor: "rgba(0,0,255,0.1)",
      tension: 0.3,
      fill: true
    }]
  }
});
</script>

</body>
</html>
