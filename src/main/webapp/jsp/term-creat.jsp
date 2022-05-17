<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>term_creating</title>
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
    <a href="/logout">Logout</a>
</span>
<div class="block_title">
    Система управления студентами и их успеваемостью
</div>
<br>
<div class="create_container">
    <div class="title_create">Для создания семестра заполните следующие данные и нажмите кнопку "Создать".</div>
    <form action="/term-creat" method="post">
        <div class="creat_info">
            <div class="input-box">
                <span class="details">Длительность (в неделях)</span>
                <input name="duration" type="text">
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