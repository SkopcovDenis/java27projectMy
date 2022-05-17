<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>students_list</title>
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
<br>
<div class="block_title">
    Система управления студентами и их успеваемостью
</div>

<br>

<div class="main_container_1">

    <div class="button_del">
        <input type="submit" value="Просмотреть успеваемость выбранных студентов" onclick="progressStudents()">
    </div>

    <c:if test="${role eq 1}">
        <div class="button_del">
            <input type="submit" value="Модифицировать выбранного студента..." onclick="modifyStudents()">
        </div>

        <form action="student-create">
            <div class="button">
                <input type="submit" value="Создать студента...">
            </div>
        </form>

        <div class="button_del">
            <input type="submit" value="Удалить выбранных студентов" onclick="deleteStudents()">
        </div>
    </c:if>

</div>
<div class="table_students_container">
    <table class="students">
        <caption class="list_students">Список студентов</caption>
        <thead>
        <tr class="title_table">
            <th></th>
            <th>Фамилия</th>
            <th>Имя</th>
            <th>Группа</th>
            <th>Дата поступления</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${students}" var="st">
            <tr>
                <td class="first">
                    <input name="idStudent" type="checkbox" value="${st.id}">
                </td>
                <td>${st.surname}</td>
                <td>${st.name}</td>
                <td class="last">${st.group}</td>
                <td class="last"><fmt:formatDate pattern="dd/MM/yyyy" value="${st.date}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>

<form action="/student-delete" method="post" id="deleteForm">
    <input type="hidden" id="deleteHidden" name="deleteHidden">
</form>

<form action="/student-modify" method="get" id="modifyForm">
    <input type="hidden" id="modifyHidden" name="modifyHidden">
</form>

<form action="/student-progress" method="get" id="progressForm">
    <input type="hidden" id="progressHidden" name="progressHidden">
</form>

</html>
