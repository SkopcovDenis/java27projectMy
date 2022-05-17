<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>student_progress</title>
    <link rel="stylesheet" href="../resources/css/style.css">
</head>
<body>
<span class="title_page">
    <a href="/">На главную</a>
</span>
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
<div class="progress_container">
    <div class="table_student_progress">
        <table class="student">
            <caption class="list_student">Отображена успеваемость для следующего студента:</caption>
            <thead>
            <tr class="title_table_student">
                <th>Фамилия</th>
                <th>Имя</th>
                <th>Группа</th>
                <th>Дата поступления</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${student.surname}</td>
                <td>${student.name}</td>
                <td>${student.group}</td>
                <td><fmt:formatDate pattern="dd/MM/yyyy" value="${student.date}"/></td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="discipline_progress_container">
        <table class="discipline_progress">
            <thead>
            <tr class="title_discipline_name_progress">
                <th>Наименование дисциплины</th>
                <c:if test="${haveMarks eq 1}">
                    <th>Оценка</th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${marks}" var="m" varStatus="loop">
                <tr>
                    <td>${m.discipline.discipline}</td>
                    <c:if test="${m.mark ne -1}">
                        <td>${m.mark}</td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="term_choice_progress">
            <span>Выбрать семестр</span>
            <form action="/student-progress" method="get">
                <input type="hidden" name="progressHidden" value="${student.id}">
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
                </select>
                <br>
                <br>
                <span>Средняя оценка за семестр:
                <fmt:formatNumber pattern="#,##0.00 " value="${avarage}"/> балла</span>


                <div class="button">
                    <input type="submit" value="Выбрать">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
