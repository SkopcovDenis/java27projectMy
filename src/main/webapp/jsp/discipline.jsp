<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>discipline_list</title>
    <script src="../resources/js/function.js"></script>
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

<div class="table_discipline_container">
    <table class="discipline">
        <caption class="list_discipline">Список дисциплин</caption>
        <thead>
        <tr class="title_discipline_name">
            <th></th>
            <th>Наименование дисциплины</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${disciplines}" var="d">
            <tr>
                <td class="discipline_choiсe">
                    <input name="idDiscipline" type="checkbox" value="${d.id}">
                </td>
                <td>${d.discipline}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${role eq 1}">
    <div class="main_container_2">

        <form action="discipline-creat">
            <div class="button">
                <input type="submit" value="Создать дисциплину...">
            </div>
        </form>

            <div class="button_del">
                <input type="submit" value="Модифицировать выбранную дисциплину..." onclick="modifyDiscipline()">
            </div>


        <div class="button_del">
            <input type="submit" value="Удалить выбранную дисциплину..." onclick="deleteDiscipline()">
        </div>

    </div>
    </c:if>
</div>
</body>
<form action="/discipline-delete" method="post" id="deleteDiscForm">
    <input type="hidden" id="deleteDiscHidden" name="deleteDiscHidden">
</form>
<form action="/discipline-modify" method="get" id="modifyDiscForm">
    <input type="hidden" id="modifyDiscHidden" name="modifyDiscHidden">
</form>
</html>