<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <title>Hello JSP</title>
</head>
<body class="container">

    <h1>Login success</h1>

<ul>
    <li>ID   : ${user.id}</li>
    <li>MAIL : ${user.email}</li>
    <li>NAME : ${user.username}</li>
    <li>JOIN DATE : <fmt:formatDate value="${user.joinDate}" pattern="yyyy-MM-dd"></fmt:formatDate> </li>
    <li>TERM : ${user.termYn}</li>
    <c:if test="${user.termYn eq 'Y'}">
        <li>TERM DATE : <fmt:formatDate value="${user.termDate}" pattern="yyyy-MM-dd"></fmt:formatDate> </li>
    </c:if>
    <c:if test="${!empty user.modifyDate}">
        <li>MODI DATE : <fmt:formatDate value="${user.modifyDate}" pattern="yyyy-MM-dd"></fmt:formatDate> </li>
    </c:if>
</ul>

</body>
</html>