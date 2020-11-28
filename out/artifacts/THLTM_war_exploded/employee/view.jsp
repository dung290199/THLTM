<%@ page contentType="text/html;charset=UTF-8" pageEncoding = "UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"/>
    <title>Thông tin chi tiết nhân viên</title>
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
                    <a class="nav-link" href="/apartments" tabindex="-1">Danh sách nhân viên</a>
                </li>
            </ul>
            <form action="/search" method="post" class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" name="search" placeholder="Tìm kiếm" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Tìm kiếm</button>
            </form>
        </div>
    </nav>

    <h1>Thông tin nhân viên</h1>
    <table class=" table table-striped">
        <tr>
            <th scope="row">IDNV : </th>
            <td>${requestScope["employee"].getId()}</td>
        </tr>
        <tr>
            <th scope="row">Họ và tên : </th>
            <td>${requestScope["employee"].getName()}</td>
        </tr>
        <tr>
            <th scope="row">IDPB : </th>
            <td>${requestScope["employee"].getIdpb()}</td>
        </tr>
        <tr>
            <th scope="row">Địa chỉ : </th>
            <td>${requestScope["employee"].getAddress()}</td>
        </tr>
    </table>
</body>
</html>
