
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>term_modify</title>
    <link rel="stylesheet" href="/resources/css/style.css">
</head>
<body>
<span class="title_page">
    <a href="/">На главную</a>
</span>
<span class="back">
        <a href="/terms">Назад</a>
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
<div class="block_title">
    Система управления студентами и их успеваемостью
</div>
<br>
<div class="create_container">
    <div class="title_create">Для редактирования семестра заполните следующие данные и нажмите кнопку "Применить".</div>
    <form action="/term-modify" method="post">
        <input type="hidden" name="idTermModify" value="${term.id}">
        <div class="creat_info">
            <div class="input-box">
                <span class="details">Длительность (в неделях)</span>
                <input name="duration" type="text" value="${term.duration}">
            </div>
            <div class="input-box">
                <span class="details">Дисциплины в семестре</span>
                <select multiple="multiple"
                        name="idsDisc">
                    <c:forEach items="${disciplines}" var="d">
                        <option value="${d.id}">${d.discipline}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="button">
            <input type="submit" value="Создать">
        </div>
    </form>
</div>

</body>
</html>
