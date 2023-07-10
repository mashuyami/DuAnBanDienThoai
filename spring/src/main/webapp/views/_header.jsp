<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.spring.entities.AccountRole"%>
<!DOCTYPE html>
<html>
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
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01"
                aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarColor01">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="navbar-brand"
                       href="${pageContext.request.contextPath}/home"><i
                            class="d-inline-block align-text-top"></i> Home </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Hỏi Đáp</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="#">Giới Thiệu</a>
                </li>
            </ul>
            <form class="col-md-4 form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/home" method="GET">
                <div class="input-group" style="margin-left: 50px;">
                    <input class="form-control" type="search" name="keyword" value="${keyword}" placeholder="Search" aria-label="Search">
                    <div class="input-group-append">
                        <button class="btn btn-secondary" type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
                    </div>
                </div>
            </form>

            <div class="col-md-4">
                <ul class="navbar-nav d-flex flex-row-reverse">
                    <c:if test="${sessionScope.username == null}">
                        <li class="nav-item"><a
                                href="${pageContext.request.contextPath}/register"
                                class="btn btn-success">Đăng ký</a></li>
                        <li class="nav-item mx-2"><a
                                href="${pageContext.request.contextPath}/login"
                                class="btn btn-primary">Đăng nhập</a></li>
                    </c:if>
                    <c:if test="${sessionScope.role == AccountRole.ADMIN}">
                        <li class="nav-item mx-2"><a
                                href="${pageContext.request.contextPath}/admin"
                                class="btn btn-danger"><i class="fa-solid fa-gear"></i></a></li>
                    </c:if>
                    <li class="nav-item">
                        <div class="dropdown">
                            <button class="btn btn-primary dropdown-toggle" type="button"
                                    id="userMenu" data-bs-toggle="dropdown" aria-expanded="false"><i
                                    class="fa-solid fa-user"></i></button>
                            <ul class="dropdown-menu" aria-labelledby="userMenu">
                                <%--<li><a class="dropdown-item"--%>
                                <%--       href="${pageContext.request.contextPath}/user/profile">Hồ--%>
                                <%--    sơ của tôi</a></li>--%>
                                <li><a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/user/order-history">Lịch
                                    sử đơn hàng</a></li>
                                <li><a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/user/change-password">Đổi
                                    mật khẩu</a></li>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li><a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/logout">Đăng
                                    xuất</a></li>
                            </ul>
                        </div>
                    </li>
                    <li class="nav-item mx-2"><a
                            href="${pageContext.request.contextPath}/cart"
                            class="btn btn-secondary"><i
                            class="fa-solid fa-cart-shopping"></i> <span
                            class="badge bg-danger">${cart.count}</span></a></li>
                    <c:if test="${sessionScope.username != null}">
                        <li class="nav-item">
                            <a class="nav-link" href="#" style="color: white;position: absolute;right: 15px">
                                Welcome ${sessionScope.username}
                            </a>
                        </li>
                    </c:if>
                </ul>
            </div>

        </div>
    </nav>



</header>
