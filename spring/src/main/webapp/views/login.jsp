<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<c:set var="title" value="Đăng nhập" />
<%--<%@include file="_header.jsp"%>--%>
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
<body>
<section class="vh-100" style="background-color: #508bfc;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card shadow-2-strong" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">

                        <h3 class="mb-5">Sign in</h3>
                        <c:if test="${message != null}">
                            <div class="alert alert-danger alert-dismissible fade show"
                                 role="alert">
                                    ${message}
                                <button type="button" class="btn-close" data-bs-dismiss="alert"
                                        aria-label="Close"></button>
                            </div>
                        </c:if>
                        <f:form method="POST"
                                action="${pageContext.request.contextPath}/login"
                                modelAttribute="data">
                            <div class="mb-3">
                                <label style="position: relative; right: 190px; bottom: 10px;">Username</label>
                                <f:input type="text" name="username" class="form-control"
                                         path="username" />
                                <f:errors path="username" element="div"
                                          cssClass="form-text text-danger" delimiter="<br>"></f:errors>
                            </div>
                            <div class="mb-3">
                                <label style="position: relative; right: 190px; bottom: 10px;">Password</label>
                                <f:input type="password" name="password" class="form-control"
                                         path="password" />
                                <f:errors path="password" element="div"
                                          cssClass="form-text text-danger" delimiter="<br>"></f:errors>
                                <a href="${pageContext.request.contextPath}/forgot-password">Quên
                                    mật khẩu?</a>
                            </div>
                            <div class="d-grid gap-2 mb-3">
                                <button class="btn btn-primary">Đăng nhập</button>
                            </div>
                        </f:form>
                        <div class="text-divider">
                            <span>Hoặc</span>
                        </div>
                        <p class="text-center my-2">
                            Bạn chưa có tài khoản? <a
                                href="${pageContext.request.contextPath}/register">Đăng ký ngay</a>
                        </p>

                        <hr class="my-4">
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
