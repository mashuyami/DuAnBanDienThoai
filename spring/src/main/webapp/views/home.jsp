<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="title" value="Trang chủ" />
<%@include file="_header.jsp"%>
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
<div class="row" style="margin-top: 70px">
    <div id="carouselExampleInterval" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active" data-bs-interval="10000">
                <img src="./public/images/th1.jpg" class="d-block w-100 rounded" alt="sale" style="height: 550px; margin: 0 30px;">
            </div>
            <div class="carousel-item" data-bs-interval="2000">
                <img src="./public/images/th2.jpg" class="d-block w-100 rounded" alt="sale" style="height: 550px; margin: 0 30px;">
            </div>
            <div class="carousel-item">
                <img src="./public/images/th4.jpg" class="d-block w-100 rounded" alt="sale" style="height: 550px; margin: 0 30px;">
            </div>
            <div class="carousel-item">
                <img src="./public/images/th9.jpg" class="d-block w-100 rounded" alt="sale" style="height: 550px; margin: 0 30px;">
            </div>
            <div class="carousel-item">
                <img src="./public/images/th6.jpg" class="d-block w-100 rounded" alt="sale" style="height: 550px; margin: 0 30px;">
            </div>
            <div class="carousel-item">
                <img src="./public/images/th8.jpg" class="d-block w-100 rounded" alt="sale" style="height: 550px; margin: 0 30px;">
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
</div>




<div class="container mt-5 border rounded">
    <div class="row">
        <div
                class="col-2 d-flex justify-content-center align-items-center p-3">
            <img alt="exchange" src="./public/images/exchange-svgrepo-com.svg"
                 height="45" width="45">
            <div>Exchange</div>
        </div>
        <div
                class="col-2 d-flex justify-content-center align-items-center p-3">
            <img alt="exchange"
                 src="./public/images/free-shipping-svgrepo-com.svg" height="45"
                 width="45">
            <div>Free ship</div>
        </div>
        <div
                class="col-2 d-flex justify-content-center align-items-center p-3">
            <img alt="exchange"
                 src="./public/images/award-quality-svgrepo-com.svg" height="45"
                 width="45">
            <div>Quality</div>
        </div>
        <div
                class="col-2 d-flex justify-content-center align-items-center p-3">
            <img alt="exchange" src="./public/images/flash-svgrepo-com.svg"
                 height="45" width="45">
            <div>Cheap</div>
        </div>
        <div
                class="col-2 d-flex justify-content-center align-items-center p-3">
            <img alt="exchange"
                 src="./public/images/shipping-fast-solid-svgrepo-com.svg"
                 height="45" width="45">
            <div>Fast shipping</div>
        </div>
        <div
                class="col-2 d-flex justify-content-center align-items-center p-3">
            <img alt="exchange"
                 src="./public/images/operator-customer-service-svgrepo-com.svg"
                 height="45" width="45">
            <div>CSKH</div>
        </div>
    </div>
</div>
<div class="container py-5">

<%--slide banner --%>




    <div class="row">
        <aside class="col-md-3">
            <div class="card my-2">
                <div class="card-header" data-bs-toggle="collapse"
                     data-bs-target="#danhMuc">
                    <span class="glyphicon glyphicon-th-list"></span> DANH MỤC
                </div>
                <ul class="list-group list-group-flush collapse show" id="danhMuc">
                    <c:forEach var="category" items="${categories}">
                        <a
                                href="${pageContext.request.contextPath}/category/${category.id}"
                                class="list-group-item d-flex justify-content-between align-items-start">${category.name}<span
                                class="badge bg-primary rounded-pill">${category.products.size()}</span></a>
                    </c:forEach>
                </ul>
            </div>
        </aside>



        <article class="col-md-9">
            <c:if test="${page.first && empty keyword}">


                <div class="row">
                    <c:forEach var="product" items="${products}">
                        <div class="col-md-4 my-2">
                            <div class="card" style="position: relative;">
                                <img
                                        src="${pageContext.request.contextPath}/images/${empty product.image ? 'no-image.jpg' : 'product/'.concat(product.image)}"
                                        class="card-img-top" width="300px" height="225px" alt="...">
                                <div class="card-body">
                                    <p class="card-text">
                                    <h5>${product.name}</h5>
                                    <div class="row">
                                        <div class="col-6 fw-bold text-danger">
                                            <fmt:formatNumber type="number" pattern="###,###,### VNĐ"
                                                              value="${product.price - (product.price * product.discount)}" />
                                        </div>
                                        <c:if test="${product.discount > 0}">
                                            <div class="col-6 text-decoration-line-through">
                                                <fmt:formatNumber type="number" pattern="###,###,### VNĐ"
                                                                  value="${product.price}" />
                                            </div>
                                        </c:if>
                                    </div>
                                    </p>
                                    <div class="row">
                                        <div class="col-md-9 mb-3">
                                            <a
                                                    href="${pageContext.request.contextPath}/product/${product.id}"
                                                    class="btn btn-primary" style="width: 100%">Xem chi tiết</a>
                                        </div>
                                        <div class="col-md-3">
                                            <a
                                                    href="${pageContext.request.contextPath}/cart/add/${product.id}"
                                                    class="btn btn-success" style="width: 100%"> <i
                                                    class="fa-solid fa-cart-plus"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <c:if test="${product.discount > 0}">
                                    <div
                                            class="product-discount text-center text-light fw-bold fs-7">
                                        Giảm
                                        <fmt:formatNumber type="percent" value="${product.discount}" />
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
            <h3>Danh sách sản phẩm</h3>
            <hr>
            <div class="row">
                <c:forEach var="product" items="${page.content}">
                    <div class="col-md-4 my-2">
                        <div class="card" style="position: relative;">
                            <img
                                    src="${pageContext.request.contextPath}/images/${empty product.image ? 'no-image.jpg' : 'product/'.concat(product.image)}"
                                    class="card-img-top" width="300px" height="225px" alt="...">
                            <div class="card-body">
                                <p class="card-text">
                                <h5>${product.name}</h5>
                                <div class="row">
                                    <div class="col-6 fw-bold text-danger">
                                        <fmt:formatNumber type="number" pattern="###,###,### VNĐ"
                                                          value="${product.price - (product.price * product.discount)}" />
                                    </div>
                                    <c:if test="${product.discount > 0}">
                                        <div class="col-6 text-decoration-line-through">
                                            <fmt:formatNumber type="number" pattern="###,###,### VNĐ"
                                                              value="${product.price}" />
                                        </div>
                                    </c:if>
                                </div>
                                </p>
                                <div class="row">
                                    <div class="col-md-9 mb-3">
                                        <a
                                                href="${pageContext.request.contextPath}/product/${product.id}"
                                                class="btn btn-primary" style="width: 100%">Xem chi tiết</a>
                                    </div>
                                    <div class="col-md-3">
                                        <a
                                                href="${pageContext.request.contextPath}/cart/add/${product.id}"
                                                class="btn btn-success" style="width: 100%"> <i
                                                class="fa-solid fa-cart-plus"></i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <c:if test="${product.discount > 0}">
                                <div
                                        class="product-discount text-center text-light fw-bold fs-7">
                                    Giảm
                                    <fmt:formatNumber type="percent" value="${product.discount}" />
                                </div>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <!-- Phân trang -->
            <div class="row">
                <c:if test="${not empty page.content}">
                    <ul class="pagination justify-content-center">
                        <li class="page-item ${page.first ? 'disabled' : ''}">
                            <a href="${pageContext.request.contextPath}/home?${empty keyword ? '' : 'keyword='.concat(keyword).concat('&')}p=1"
                               class="page-link"><i class="fa-solid fa-backward-step"></i></a>
                        </li>
                        <li class="page-item ${page.first ? 'disabled' : ''}">
                            <a href="${pageContext.request.contextPath}/home?${empty keyword ? '' : 'keyword='.concat(keyword).concat('&')}p=${page.number}"
                               class="page-link"><i class="fa-solid fa-backward"></i></a>
                        </li>

                        <c:set var="startPage" value="${(page.number - 2) > 0 ? (page.number - 2) : 1}" />
                        <c:set var="endPage" value="${(page.number + 2) < page.totalPages ? (page.number + 2) : page.totalPages}" />

                        <c:if test="${startPage > 1}">
                            <li class="page-item">
                                <a href="${pageContext.request.contextPath}/home?${empty keyword ? '' : 'keyword='.concat(keyword).concat('&')}p=1"
                                   class="page-link">1</a>
                            </li>
                            <li class="page-item disabled">
                                <button class="page-link">...</button>
                            </li>
                        </c:if>

                        <c:forEach var="pageNumber" begin="${startPage}" end="${endPage}">
                            <li class="page-item ${page.number + 1 == pageNumber ? 'disabled' : ''}">
                                <a href="${pageContext.request.contextPath}/home?${empty keyword ? '' : 'keyword='.concat(keyword).concat('&')}p=${pageNumber}"
                                   class="page-link">${pageNumber}</a>
                            </li>
                        </c:forEach>

                        <c:if test="${endPage < page.totalPages}">
                            <li class="page-item disabled">
                                <button class="page-link">...</button>
                            </li>
                            <li class="page-item">
                                <a href="${pageContext.request.contextPath}/home?${empty keyword ? '' : 'keyword='.concat(keyword).concat('&')}p=${page.totalPages}"
                                   class="page-link">${page.totalPages}</a>
                            </li>
                        </c:if>

                        <li class="page-item ${page.last ? 'disabled' : ''}">
                            <a href="${pageContext.request.contextPath}/home?${empty keyword ? '' : 'keyword='.concat(keyword).concat('&')}p=${page.number + 2}"
                               class="page-link"><i class="fa-solid fa-forward"></i></a>
                        </li>
                        <li class="page-item ${page.last ? 'disabled' : ''}">
                            <a href="${pageContext.request.contextPath}/home?${empty keyword ? '' : 'keyword='.concat(keyword).concat('&')}p=${page.totalPages}"
                               class="page-link"><i class="fa-solid fa-forward-step"></i></a>
                        </li>
                    </ul>
                </c:if>
                <c:if test="${empty page.content}">
                    <div class="text-center">Không có sản phẩm</div>
                </c:if>
            </div>


        </article>
    </div>
</div>
<%@include file="_footer.jsp"%>