<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>student_modifying</title>
    <link rel="stylesheet" href="../resources/css/style.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
    <script>
        $( function() {
            $( "#datepicker" ).datepicker();
        } );
    </script>
</head>
<body>
<span class="title_page">
    <a href="/">На главную</a>
</span>
<span class="back">
        <a href="/students">Назад</a>
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
    <div class="title_create">Для модификации, введите новые значения и нажмите кнопку "Применить".</div>

    <form action="/student-modify" method="post">
        <input type="hidden" name="id" value="${student.id}">
        <div class="creat_info">
            <div class="input-box">
                <span class="details">Фамилия</span>
                <input type="text" name="surname" value="${student.surname}">
            </div>

            <div class="input-box">
                <span class="details">Имя</span>
                <input type="text" name="name" value="${student.name}">
            </div>

            <div class="input-box">
                <span class="details">Группа</span>
                <input type="text" name="group" value="${student.group}">
            </div>

            <div class="input-box">
                <span class="details">Дата поступления</span>
                <input type="text" name="date" id="datepicker" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${student.date}"/>">
            </div>
        </div>
        <div class="button">
            <input type="submit" value="Применить">
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