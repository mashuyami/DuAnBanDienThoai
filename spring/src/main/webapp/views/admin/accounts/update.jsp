<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<c:set var="title" value="Cập nhật tài khoản" />
<c:set var="menuId" value="1" />
<%@include file="../../_header.jsp"%>
<div class="row mb-3">
    <div class="mx-auto" style="max-width: 600px;">
        <h3 class="text-center my-3">Cập nhật tài khoản</h3>
        <c:if test="${message != null}">
            <div class="alert alert-success alert-dismissible fade show"
                 role="alert">
                    ${message}
                <button type="button" class="btn-close" data-bs-dismiss="alert"
                        aria-label="Close"></button>
            </div>
        </c:if>
        <c:if test="${error != null}">
            <div class="alert alert-danger alert-dismissible fade show"
                 role="alert">
                    ${error}
                <button type="button" class="btn-close" data-bs-dismiss="alert"
                        aria-label="Close"></button>
            </div>
        </c:if>

        <f:form method="POST"
                action="${pageContext.request.contextPath}/admin/accounts/update"
                modelAttribute="data" enctype="multipart/form-data">
            <f:input type="hidden" name="id" path="id" />
            <div class="mb-3">
                <f:label path="username" cssClass="form-label">Tên đăng nhập</f:label>
                <f:input type="text" name="username" class="form-control"
                         path="username" />
                <f:errors path="username" element="div"
                          cssClass="form-text text-danger" delimiter="<br>"></f:errors>
            </div>
            <div class="mb-3">
                <f:label path="fullname" cssClass="form-label">Họ và tên</f:label>
                <f:input type="text" name="fullname" class="form-control"
                         path="fullname" />
                <f:errors path="fullname" element="div"
                          cssClass="form-text text-danger" delimiter="<br>"></f:errors>
            </div>
            <div class="mb-3">
                <label>Photo</label>
                <f:input path="photo" class="form-control"
                         name="photo" />
                <f:errors path="photo" cssClass="text-danger" />
            </div>
            <div class="mb-3">
                <f:label path="email" cssClass="form-label">Email</f:label>
                <f:input type="text" name="email" class="form-control" path="email" />
                <f:errors path="email" element="div"
                          cssClass="form-text text-danger" delimiter="<br>"></f:errors>
            </div>
            <div class="mb-3">
                <label class="form-label">Mật khẩu</label>
                <f:input type="password" name="password" class="form-control"
                         path="password" />
                <f:errors path="password" element="div"
                          cssClass="form-text text-danger" delimiter="<br>"></f:errors>
            </div>
            <div class="mb-3">
                <label class="form-label">Vai trò</label> <br>
                <div class="form-check form-check-inline">
                    <f:radiobutton class="form-check-input" name="activated" id="user"
                                   value="USER" path="role" />
                    <label class="form-check-label" for="user">Người dùng</label>
                </div>
                <div class="form-check form-check-inline">
                    <f:radiobutton class="form-check-input" name="available" id="admin"
                                   value="ADMIN" path="role" />
                    <label class="form-check-label" for="admin">Quản trị viên</label>
                </div>
                <br>
                <f:errors path="activated" element="div"
                          cssClass="form-text text-danger" delimiter="<br>"></f:errors>
            </div>
            <div class="mb-3">
                <label class="form-label">Trạng thái</label> <br>
                <div class="form-check form-check-inline">
                    <f:radiobutton class="form-check-input" name="activated" id="nam"
                                   value="1" path="activated" />
                    <label class="form-check-label" for="nam">Đã kích hoạt</label>
                </div>
                <div class="form-check form-check-inline">
                    <f:radiobutton class="form-check-input" name="available" id="nu"
                                   value="0" path="activated" />
                    <label class="form-check-label" for="nu">Chưa kích hoạt</label>
                </div>
                <br>
                <f:errors path="activated" element="div"
                          cssClass="form-text text-danger" delimiter="<br>"></f:errors>
            </div>
            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-success">Cập nhật</button>
            </div>
        </f:form>
    </div>
</div>
<script type="text/javascript">
    const input = document.getElementById('file-input');
    const image = document.getElementById('img-preview');

    input.addEventListener('change', (e) => {
        if (e.target.files.length) {
            const src = URL.createObjectURL(e.target.files[0]);
            image.src = src;
        }
    });
</script>
<%@include file="../../_footer.jsp"%>