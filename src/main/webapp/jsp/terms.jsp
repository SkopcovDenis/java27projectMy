<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>terms_list</title>
    <link rel="stylesheet" href="../resources/css/style.css">
</head>
<body>

<span class="title_page">
    <a href="/">На главную</a>
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
<br>

<div class="term_choice">

    <form action="/terms" method="get">
    <span>Выбрать семестр</span>
    <select name="idSelectedTerm">
        <c:forEach items="${terms}" var="t">
        <c:choose>
        <c:when test="${t.id eq selectedTerm.id}">
        <option selected value="${t.id}">${t.term}</option>
        </c:when>
        <c:otherwise>
        <option value="${t.id}">${t.term}</option>
        </c:otherwise>
        </c:choose>
        </c:forEach>
            <div class="button">
                <input type="submit" value="Выбрать">
            </div>
    </select>
    </form>
</div>
<br>
<div class="term_duration">
    <span>Длительность семестра:</span>
    <span>${selectedTerm.duration}</span>
</div>
<br>
<div class="table_discipline_container">
    <table class="discipline">
        <caption class="list_discipline">Список дисциплин семестра</caption>
        <thead>
        <tr class="title_discipline_name">
            <th>Наименование дисциплины</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${disciplines}" var="d">
        <tr>
            <td class="discipline_name">${d.discipline}</td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="main_container_2">
        <form action="term-creat" method="get">
            <div class="button">
                <input type="submit" value="Создать семестр...">
            </div>
        </form>
        <form action="/term-modify" method="get">
            <div class="button">
                <input type="hidden" name="idTermToModify" value="${selectedTerm.id}">
                <input type="submit" value="Модифицировать текущий семестр...">
            </div>
        </form>
        <form action="/delete-term">
            <div class="button">
                <input type="hidden" name="idTermToDelete" value="${selectedTerm.id}">
                <input type="submit" value="Удалить текущий семестр...">
            </div>
        </form>
    </div>
</div>

</body>
</html>