<%@ page contentType="text/html;charset=UTF-8" pageEncoding = "UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"/>
    <title>Danh sách phòng ban</title>
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

    <div>
        <a class="btn btn-info" href="/apartments?action=create">Tạo mới phòng ban</a>
    </div>

    <form method="get">
        <table class="table table-striped">
            <thead>
                <th scope="col">IDPB</th>
                <th scope="col">Tên phòng ban</th>
                <th scope="col">Mô tả</th>
            </thead>
            <tbody>
                <c:forEach items='${requestScope["apartments"]}' var="apartment">
                    <tr>
                        <td>${apartment.getId()}</td>
                        <td><a href="/apartments?action=view&id=${apartment.getId()}">${apartment.getName()}</a></td>
                        <td>${apartment.getDescription()}</td>
                        <td><a href="/apartments?action=edit&id=${apartment.getId()}">Sửa</a></td>
                        <td><a href="/apartments?action=delete&id=${apartment.getId()}">Xóa</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </form>
</body>
</html>
