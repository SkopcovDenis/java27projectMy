<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>discipline_creating</title>
    <link rel="stylesheet" href="/resources/css/style.css">
</head>
<body>

<span class="title_page">
    <a href="/">На главную</a>
</span>
<span class="back">
        <a href="/discipline">Назад</a>
</span>

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
<br>
<div class="block_title">
    Система управления студентами и их успеваемостью
</div>
<br>
<div class="create_container">
    <div class="title_create">Для того что бы создать новую дисциплину заполните все поля и нажмите кнопку "Создать".
    </div>

    <form action="/discipline-creat" method="post">
        <div class="creat_info">
            <div class="input-box">
                <span class="details">Название</span>
                <input type="text" name="discipline">
            </div>
        </div>
        <div class="button">
            <input type="submit" value="Создать">
        </div>
        <div class="error">
        <c:if test="${error == '1'}">
            <p>Поля не должны быть пустыми!!!</p>
        </c:if>
        </div>
    </form>
</div>
</body>
</html>