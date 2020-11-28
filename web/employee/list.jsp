<%@ page contentType="text/html;charset=UTF-8" pageEncoding = "UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"/>
    <title>Danh sách nhân viên</title>
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

    <h1>Danh sách nhân viên</h1>
    <div>
        <a class="btn btn-info" href="/employees?action=create">Tạo mới nhân viên</a>
    </div>
    <form method="get">
        <table class="table table-striped">
            <thead>
                <th scope="col">IDNV</th>
                <th scope="col">Họ và tên</th>
                <th scope="col">IDPB</th>
                <th scope="col">Địa chỉ</th>
            </thead>
            <tbody>
                <c:forEach items='${requestScope["employees"]}' var="employee">
                    <tr>
                        <td>${employee.getId()}</td>
                        <td><a href="/employees?action=view&id=${employee.getId()}">${employee.getName()}</a></td>
                        <td>${employee.getIdpb()}</td>
                        <td>${employee.getAddress()}</td>
                        <td><a href="/employees?action=edit&id=${employee.getId()}">Sửa</a></td>
                        <td><a href="/employees?action=delete&id=${employee.getId()}">Xóa</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </form>
</body>
</html>
