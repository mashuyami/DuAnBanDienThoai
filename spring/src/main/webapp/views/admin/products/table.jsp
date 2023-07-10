<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="title" value="Danh sách sản phẩm" />
<c:set var="menuId" value="2" />


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
                            </li>

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
                    <div class="col-6">
                        <div class="row">
                            <div class="col-6">
                                <form action="${pageContext.request.contextPath}/admin/products"
                                      method="GET">
                                    <div class="input-group">
                                        <input class="form-control" type="search" name="keyword"
                                               placeholder="Tên sản phẩm?" value="${keyword}">
                                        <button class="btn btn-secondary">
                                            <i class="fa-solid fa-magnifying-glass"></i>
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-6 d-flex">
                        <div class="ms-auto">
                            <a href="${pageContext.request.contextPath}/admin/products/create"
                               class="btn btn-success"> <i class="fa-solid fa-plus"></i> Thêm
                            </a> <a href="${pageContext.request.contextPath}/admin/products/export"
                                    class="btn btn-secondary"><i class="fa-solid fa-file-excel"></i>
                            Xuất excel</a>
                        </div>
                    </div>
                </div>
            </div>




            <div class="card">
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table  table-bordered table-hover">
                            <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Hình ảnh</th>
                                <th scope="col">Tên SP</th>
                                <th scope="col">Số lượng</th>
                                <th scope="col">Đơn giá</th>
                                <th scope="col">Giảm giá</th>
                                <th scope="col">Ngày tạo</th>
                                <th scope="col">Trạng thái</th>
                                <th scope="col">Hành động</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="product" items="${page.content}">
                                <tr>
                                    <th scope="row">${product.id}</th>
                                    <td><img
                                            src="${pageContext.request.contextPath}/images/${empty product.image ? 'no-image.jpg' : 'product/'.concat(product.image)}"
                                            alt="" width="100px" height="100px"></td>
                                    <td>${product.name}</td>
                                    <td>${product.quantity}</td>
                                    <td><fmt:formatNumber type="number"
                                                          pattern="###,###,### VNĐ" value="${product.price}" /></td>
                                    <td><fmt:formatNumber type="percent"
                                                          value="${product.discount}" /></td>
                                    <td><fmt:formatDate value="${product.createdDate}" pattern="dd-MM-yyyy" /></td>
                                    <td><c:if test="${product.available == 1}">
                                        <span class="text-primary">Đang bán</span>
                                    </c:if> <c:if test="${product.available == 0}">
                                        <span class="text-danger">Ngừng bán</span>
                                    </c:if></td>
                                    <td>
                                        <div class="btn-group" role="group">
                                            <a
                                                    href="${pageContext.request.contextPath}/admin/products/update/${product.id}"
                                                    class="btn btn-primary"> <i
                                                    class="fa-solid fa-pen-to-square"></i> Sửa
                                            </a> <a
                                                href="${pageContext.request.contextPath}/admin/products/delete/${product.id}"
                                                onclick="return confirm('Bạn muốn xoá sản phẩm này?');"
                                                class="btn btn-danger"> <i class="fa-solid fa-trash-can"></i>
                                            Xoá
                                        </a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="row">
                        <ul class="pagination justify-content-center">
                            <li class="page-item ${page.first ? 'disabled' : ''}"><a
                                    href="${pageContext.request.contextPath}/admin/products?keyword=${keyword}&p=1"
                                    class="page-link"> <i class="fa-solid fa-backward-step"></i>
                            </a></li>
                            <li class="page-item ${page.first ? 'disabled' : ''}"><a
                                    href="${pageContext.request.contextPath}/admin/products?keyword=${keyword}&p=${page.number}"
                                    class="page-link"> <i class="fa-solid fa-backward"></i>
                            </a></li>
                            <li class="page-item disabled">
                                <button class="page-link">${page.number + 1}</button>
                            </li>
                            <li class="page-item ${page.last ? 'disabled' : ''}"><a
                                    href="${pageContext.request.contextPath}/admin/products?keyword=${keyword}&p=${page.number + 2}"
                                    class="page-link"> <i class="fa-solid fa-forward"></i>
                            </a></li>
                            <li class="page-item ${page.last ? 'disabled' : ''}"><a
                                    href="${pageContext.request.contextPath}/admin/products?keyword=${keyword}&p=${page.totalPages}"
                                    class="page-link"> <i class="fa-solid fa-forward-step"></i>
                            </a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


