<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link rel="stylesheet" href="../resources/css/style.css">
</head>
<body>
<div class="block_title">
    Система управления студентами и их успеваемостью
</div>
<br>
<div class="login_container">
    <div class="title_login"><h3>Вход в систему</h3></div>
    <form action="/login" method="post">
        <div class="login_info">
            <div class="input-box">
                <span class="details">Логин</span>
                <input type="text" name="login">
            </div>

            <div class="input-box">
                <span class="details">Пароль</span>
                <input type="text" name="password">
            </div>

            <div class="input-box">
                <span class="details">Выберите роль</span>
                <select name="role">
                    <option value="3">Студент</option>
                    <option value="2">Учитель</option>
                    <option value="1">Администратор</option>
                </select>
            </div>
            <div class="button">
                <input type="submit" value="Войти">
            </div>
            <div class="error_login">
                <c:if test="${error == '1'}">
                    <p>Неверный логин или пароль или роль!</p>
                </c:if>
            </div>
        </div>
    </form>
</div>
</body>
</html>