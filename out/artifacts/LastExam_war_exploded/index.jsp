<%@ page contentType="text/html;charset=UTF-8" pageEncoding = "UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"/>
  <title>TTCN</title>
</head>
<body>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="index.jsp">Quản lý nhân sự</a>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item">
          <a class="nav-link" href="/employees">Employees</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/apartments" tabindex="-1">Apartments</a>
        </li>
      </ul>
      <form action="/search" method="post" class="form-inline my-2 my-lg-0">
        <input class="form-control mr-sm-2" type="search" name="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
      </form>
    </div>
  </nav>
</body>
</html>