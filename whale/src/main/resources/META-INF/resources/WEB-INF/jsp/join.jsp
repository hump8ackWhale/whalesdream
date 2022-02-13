<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <title>Hello JSP</title>
</head>
<body class="container">

    <h1>Join Form</h1>

<form action="/join" method="post">
    <div class="form-group">
        <label>이메일</label>
        <input name="email" type="email" class="form-control" />

        <label>이름</label>
        <input name="username" type="text" class="form-control" />

        <label>비밀번호</label>
        <input name="password" type="password" class="form-control" />
    </div>
    <button type="submit" class="btn btn-primary">가입</button>
</form>

<a href="join.jsp">회원가입</a>
</body>
</html>