<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang=" ">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Едем.РФ</title>
<link rel="stylesheet"
	href=" https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
<link rel="stylesheet" href="jsp/css/travel.css">
</head>
<body>
	<jsp:include page="header.jsp">
		<jsp:param name="user" value="${user}" />
	</jsp:include>
	<div class="wrapper">
		<div class="container">
			
			<p>
				Дата:
				<c:out value="${travel.date}" />
			</p>
			<p>
				Откуда:
				<c:out value="${travel.sendingPlaceString}" />
			</p>
			<p>
				Куда:
				<c:out value="${travel.arrivalPlaceString}" />
			</p>
			<p>
				Количество свободных мест:
				<c:out value="${travel.maxFreePlaces}" />
			</p>
			<p>
				Имя водителя:
				<c:out value="${driver.name}" />
			</p>
			<p>
				Пол:
				<c:out value="${driver.gender}" />
			</p>
			<p>
				Телефон:
				<c:out value="${driver.phone}" />
			</p>
			<p>
				Марка машины:
				<c:out value="${car.brand}" />
			</p>
			<p>
				Цвет машины:
				<c:out value="${car.color}" />
			</p>
			<p>
				Номер машины:
				<c:out value="${car.numbers}" />
			</p>
			<div class="knopki">
				<c:if test="${user.id != driver.id}">
					<c:if test="${allowJoin && travel.activeStatus}">
						<form class="pr" method="POST" action="FrontController">
							<input type="hidden" value="join" name="command" />
							<input type="hidden" name="travelId" value="${travel.id}" />
							<button type="submit" class="q">Присоединиться</button>
						</form>
					</c:if>
					<c:if test="${!allowJoin && travel.activeStatus}">
						<form class="pr" method="POST" action="FrontController">
							<input type="hidden" value="leave" name="command" />
							<input type="hidden" name="travelId" value="${travel.id}" />
							<button class="del q">Отказаться</button>
						</form>
					</c:if>
				</c:if>
				<c:if test="${user.id == driver.id || user.role == 'Moderator' || user.role == 'Admin'}">
				    <form class="pr" method="POST" action="FrontController">
				        <input type="hidden" value="deleteTravelById" name="command" />
				        <input type="hidden" name="travelId" value="${travel.id}" />
					    <button class="de q">Удалить поездку</button>
					</form>
				</c:if>

				<c:if
					test="${user.id == driver.id && travel.activeStatus}">
					<form class="pr" method="POST" action="FrontController">
					    <input type="hidden" value="finish" name="command" />
					    <input type="hidden" name="travelId" value="${travel.id}" />
					    <button class="de q">Завершить поездку</button>
					</form>
				</c:if>
			</div>
			<c:if test="${!travel.activeStatus}">
			  <p style="color: ">Рейтинг поездки: ${avgGrade}</p>
			</c:if>
			    <c:if test="${allowRait && !travel.activeStatus}">
                    <form class="pr" method="POST" action="FrontController">
                        <select style="color: orange" class="form-select" id="arri" name="mark">
                            <option style="color: orange" value="1">★</option>
                            <option style="color: orange" value="2">★★</option>
                            <option style="color: orange" value="3">★★★</option>
                            <option style="color: orange" value="4">★★★★</option>
                            <option style="color: orange" value="5">★★★★★</option>
                        </select>
                        <input type="hidden" value="rate" name="command" />
                        <input type="hidden" name="travelId" value="${travel.id}" />
                        <button class="gr">Оценить</button>
                    </form>
                </c:if>
		</div>
		<c:if test="${user.id == driver.id}">
			<c:forEach items="${passengers}" var="passenger" varStatus="loop">
				<div class="container">
					<p class="request">Запрос на поездку</p>
					<p>
						Имя:
						<c:out value="${passenger.nameUser}" />
					</p>
					<p>
						Номер телефона:
						<c:out value="${passenger.phoneUser}" />
					</p>
					<p>
						Пол:
						<c:out value="${passenger.genderUser}" />
					</p>
					<c:if test="${!passenger.approved && travel.activeStatus}">
						<form class="pr" method="POST" action="FrontController">
							<input type="hidden" value="accept" name="command" />
							<input type="hidden" name="passengerId" value="${passenger.id}" />
							<input type="hidden" name="travelId" value="${travel.id}" />
							<button class="q">Принять</button>
						</form>
					</c:if>
					<c:if test="${passenger.approved && travel.activeStatus}">
						<form class="pr" method="POST" action="FrontController">
							<input type="hidden" value="reject" name="command" />
							<input type="hidden" name="passengerId" value="${passenger.id}" />
							<input type="hidden" name="travelId" value="${travel.id}" />
							<button class="de">Отклонить</button>
						</form>
					</c:if>
				</div>
			</c:forEach>
		</c:if>
	</div>
	<script src="JS/grade.js"> </script>
</body>
</html>