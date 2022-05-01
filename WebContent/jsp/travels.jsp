<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="selectedPlace" class="ru.rsreu.FindingTravelCrappo.datalayer.db.oracledb.dao.OraclePlaceDAO" scope="request"/>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Поездки</title>
<link rel="stylesheet" href="jsp/css/travels.css">
</head>
<body>
	<jsp:include page="header.jsp">
		<jsp:param name="user" value="${user}" />
	</jsp:include>
	<div class="wrapper">
	    
		<c:forEach items="${travels}" var="travel" varStatus="loop">
			<form name="TravelForm" method="POST" action="FrontController">
				<div class="content">
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
					<a
						href="${pageContext.request.contextPath}/FrontController?command=showTravelPage&travelId=${travel.id}">Показать
						полностью</a>
				</div>
			</form>
		</c:forEach>
	</div>
</body>
</html>