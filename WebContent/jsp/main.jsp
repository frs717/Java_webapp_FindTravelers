<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang=" ">
<head>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Crappo</title>
<link rel="stylesheet"
	href=" https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
<link rel="stylesheet" href="jsp/css/main.css">
<script src="index.js"></script>
</head>
<body>
	<jsp:include page="header.jsp">
    <jsp:param name="user" value="${user}" />
</jsp:include>
<form name="main" method="POST" action="FrontController">
    <input type="hidden" name="command" value="showFindingTravelsPage" />
    <p class="text">
        Выгодные междугородние поездки <br> для Водителей и Пассажиров
    </p>
    <div class="wrapper">
        <div class="input-block">
            <div>
                <label for="list">Откуда</label>

                <select class="form-select" id="arri" name="where">
                    <c:forEach items="${places}" var="place" varStatus="loop">
                        <option value="${place.id}">${place.city} ${place.state}</option>
                    </c:forEach>
                </select>
            </div>

            <div>
                <label for="list">Куда</label>
                <select class="form-select" id="arrive" name="arrive">
                    <c:forEach items="${places}" var="place" varStatus="loop">
                        <option value="${place.id}">${place.city} ${place.state}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <input style="width: 150px" type="date" max="5" class="input" placeholder="Дата"
                       type="text" value="" name="date">
            </div>
            <div class="btn">
                <button class="button" type="submit">Найти</button>
            </div>
        </div>
    </div>
</form>

</body>
</html>