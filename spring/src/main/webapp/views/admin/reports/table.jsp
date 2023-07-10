<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="title" value="Thống kê" />
<c:set var="menuId" value="5" />













<head>
    <meta charset="UTF-8">
    <title><c:out value="${title}"/></title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
          rel="stylesheet">
    <link
            href="${pageContext.request.contextPath}/css/icon/fontawesome.min.css"
            rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/icon/brands.min.css"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/icon/solid.min.css"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css"
          rel="stylesheet">
    <script
            src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
</head>

<div class="container-fluid">
    <div class="row">
        <div class="col-2 p-4 shadow" style="background-color: #0a3d62; height: 100vh">
            <!-- Thanh header -->
            <div class="navbar navbar-expand-lg navbar-dark flex-column">
                <div class="container-fluid">
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false"
                            aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNav">
                        <hr>
                        <ul class="navbar-nav flex-column">
                            <li class="nav-item">
                                <img alt="logo" src="${pageContext.request.contextPath}/css/icon/webfonts/th.jpg" width="100" height="100">

                            </li>
                            <hr>
                            <li>
                                <span style="color: white; font-weight: bold;"> Welcome ${sessionScope.username} </span>
                            </li>

                            <li class="nav-item">
                                <a class="nav-link" href="/home">Home</a>


                            <li class="nav-item">
                                <a class="nav-link" href="/admin/accounts">Tài khoản</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/admin/products">Sản Phẩm</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/admin/categories">Thể Loại</a>
                            </li>

                            <li class="nav-item">
                                <a class="nav-link" href="/admin/orders">Hóa Đơn</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/admin/order-details">Hóa Đơn Chi Tiết </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/admin/reports">Thống kê</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <hr>
            <div class="list-group mt-3">
                <p class="text-secondary fw-bold">Extras</p>
                <a href="#" class="list-group-item list-group-item-action border-0"
                   style="background-color: #0a3d62; color: #dcdde1">
                    <span><i class="fa-solid fa-message fs-6 px-1"></i></span> Feedbacks
                </a>
                <a href="#" class="list-group-item list-group-item-action border-0"
                   style="background-color: #0a3d62; color: #dcdde1">
                    <span><i class="fa-solid fa-gear fs-6 px-1"></i></span> Settings
                </a>
            </div>
        </div>
        <div class="col-10">

            <div class="card mb-2">
                <div class="card-body row">
                    <div class="col-12 d-flex">
                        <div class="ms-auto">
                            <a href="#"
                               class="btn btn-secondary"><i class="fa-solid fa-file-excel"></i>
                                Xuất excel</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row m-0 p-5 g-3">
                <div class="col-3">
                    <div class="card bg-info rounded">
                        <div class="card-header text-white">
                            Tổng Tài khoản
                        </div>
                        <div class="card-body">
                            <p class="text-white">${ totalAcc }</p>
                        </div>
                    </div>
                </div>
                <div class="col-3">
                    <div class="card bg-primary rounded">
                        <div class="card-header text-white">
                            Tổng sản phẩm
                        </div>
                        <div class="card-body">
                            <p class="text-white">${ totalPro }</p>
                        </div>
                    </div>
                </div>
                <div class="col-3">
                    <div class="card bg-success rounded">
                        <div class="card-header text-white">
                           Tổng Hóa Đơn
                        </div>
                        <div class="card-body">
                            <p class="text-white">${ totalOrder }</p>
                        </div>
                    </div>
                </div>
                <div class="col-3">
                    <div class="card bg-danger rounded">
                        <div class="card-header text-white">
                            Tổng Doanh thu
                        </div>
                        <div class="card-body">
                            <p class="text-white">${ totalPayment }</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>