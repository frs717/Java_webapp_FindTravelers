<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="jsp/css/createUser.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp">
		<jsp:param name="user" value="${user}" />
	</jsp:include>
	<form name="createForm" method="POST" action="FrontController">
	<input type="hidden" name="command" value="createUser" />
		<p>
			<label for="login">Логин</label> <input type="text" id="login" name="login">
		</p>

		<p>
			<label for="pswd">Пароль</label> <input type="text" id="pswd" name="password">
		</p>

		<p>
			<label for="name">Имя</label> <input type="text" id="name" name="name">
		</p>

		<p>
			<label for="phone">Phone:</label> <input type="text"
				placeholder="Ваш телефон" class="phone" id="phone" name="phone">
		</p>

		<p>
			<label for="">Пол</label> <input type="radio" id="male" name="gender"
				value="Man" /> <label for="male">Мужской</label> <input
				type="radio" id="female" name="gender" value="Women" /> <label
				for="female">Женский</label>
		</p>

		<p>
			<label for="">Роль</label> <select class="form-select form-select-sm"
				name="role" id="">
				<option value="User">Пользователь</option>
				<option value="Moderator">Модератор</option>
				<option value="Admin">Администратор</option>
			</select>
		</p>

		<p>
			<label for="">Машина</label> <select
				class="form-select form-select-sm" name="car" id="">
				<option value="1">БМВ</option>
				<option value="2">Тойота</option>
			</select>
		</p>
<button class="create" type="submit">Создать</button>
	</form>
	<script src="JS/createUser.js">

	</script>
</body>
</html>