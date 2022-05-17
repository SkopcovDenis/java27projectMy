<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>title_page</title>
    <link rel="stylesheet" href="/resources/css/style.css">
</head>
<body>
<span class="logout">
    <c:choose>
        <c:when test="${isLogin eq 1}">
            <a href="/logout">${login}, Logout</a>
        </c:when>
        <c:otherwise>
            <a href="/login">Login</a>
        </c:otherwise>
    </c:choose>
</span>

<div class="block_title">
    Система управления студентами и их успеваемостью
</div>
<br>

<span class="links">
    <a href="/students">Студенты</a>
    <a href="/discipline">Дисциплины</a>
    <a href="/terms">Семестры</a>
</span>

</body>
</html>
