<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="jsp/css/login.css">
<link rel="stylesheet"
	href=" https://unpkg.com/ionicons@4.5.10-0/dist/css/ionicons.min.css">
<link rel="stylesheet"
	href=" https://fonts.googleapis.com/css?family=Montserrat:500,700&amp;display=swap">
</head>
<body>
	<form name="loginForm" method="POST" action="FrontController">
		<input type="hidden" name="command" value="login" />

		<div class="segment">
			<h1>Авторизация</h1>
		</div>

		<label> <input required type="text" name="login" value=""
			placeholder="Логин" />
		</label> <input type="hidden" name="requestedURL" value="${requestedURL}">
		<label> <input required type="password" name="password"
			value="" placeholder="Пароль" />
		</label>
		<button class="red" type="submit">
			<i class="icon ion-md-lock"></i> Войти
		</button>
		<br />
		<p class="text-danger"><c:out value="${error}"/></p>
		<br /> ${wrongAction} <br /> ${nullPage} <br />
	</form>
</body>
</html>