<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <title>Hello JSP</title>
</head>
<body class="container">

<h1>Login Form</h1>

<form action="/doLogin" method="post">
    <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
        <p>Your login attempt was not successful due to <br/>
                ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
        <c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
    </c:if>
    <input type="text" class="form-control" name="username" placeholder="id">
    <input type="password" class="form-control" name="password" placeholder="Password">
    <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
    <button type="submit" class="btn btn-primary">Sign in</button>
</form>

</body>
</html>