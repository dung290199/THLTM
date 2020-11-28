<%@ page contentType="text/html;charset=UTF-8" pageEncoding = "UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"/>
    <title>Thông tin phòng ban</title>
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

    <h1>Chi tiết phòng ban</h1>
    <table class="table table-striped">
        <tr>
            <th scope="row">IDPB : </th>
            <td>${requestScope["apartment"].getId()}</td>
        </tr>
        <tr>
            <th scope="row">Tên phòng ban : </th>
            <td>${requestScope["apartment"].getName()}</td>
        </tr>
        <tr>
            <th scope="row">Mô tả : </th>
            <td>${requestScope["apartment"].getDescription()}</td>
        </tr>
    </table>
</body>
</html>
