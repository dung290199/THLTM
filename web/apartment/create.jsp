<%@ page contentType="text/html;charset=UTF-8" pageEncoding = "UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <title>Taọ mới phòng ban</title>
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
                    <a class="nav-link" href="/apartments" tabindex="-1">Danh sách các phòng ban</a>
                </li>
            </ul>
            <form action="/search" method="post" class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" name="search" placeholder="Tìm kiếm" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Tìm kiếm</button>
            </form>
        </div>
    </nav>

    <c:if test="${requestScope['message'] != null}">
        <div class="alert alert-primary" role="alert">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            Thêm thông tin phòng ban thành công!
        </div>
    </c:if>

    <form method="post">
        <h1>Thông tin phòng ban</h1>
        <div class="form-group">
            <label for="name">Tên phòng ban: </label>
            <input type="text" name="name" id="name" class="form-controll">
        </div>
        <div class="form-group">
            <label for="description">Mô tả: </label>
            <input type="text" name="description" id="description" class="form-controll">
        </div>
        <button type="submit" class="btn btn-primary">Tạo</button>
    </form>
</body>
</html>
