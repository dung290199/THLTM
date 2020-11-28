<%@ page contentType="text/html;charset=UTF-8" pageEncoding = "UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <title>Sửa thông tin nhân viên</title>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="index.jsp">Quản lý nhân sự</a>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/employees">Danh sách nhân viên</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/apartments" tabindex="-1">Danh sách phòng ban</a>
                </li>
            </ul>
            <form action="/search" method="post" class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" name="search" placeholder="Tìm kiếm" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Tìm kiếm</button>
            </form>
        </div>
    </nav>
    <h1>Edit employee</h1>
    <c:if test="${requestScope['message'] != null}">
        <div class="alert alert-primary" role="alert">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            Thay đổi thông tin nhân viên thành công!
        </div>
    </c:if>

    <form method="post">
        <h1>Thông tin nhân viên</h1>
        <table class="table table-striped">
            <tr>
                <th scope="row">IDNV : </th>
                <td>${requestScope["employee"].getId()}</td>
            </tr>
            <tr>
                <th scope="row">Họ và tên : </th>
                <td><input type="text" name="name" id="name" value="${requestScope["employee"].getName()}"></td>
            </tr>
            <tr>
                <th scope="row">IDPB : </th>
                <td><input type="number" name="idpb" id="idpb" value="${requestScope["employee"].getIdpb()}"></td>
            </tr>
            <tr>
                <th scope="row">Địa chỉ : </th>
                <td><input type="text" name="address" id="address" value="${requestScope["employee"].getAddress()}"></td>
            </tr>
        </table>
        <button type="submit" class="btn btn-primary">Sửa</button>
    </form>
</body>
</html>
