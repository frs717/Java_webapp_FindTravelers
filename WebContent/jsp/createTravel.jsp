<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Создать поездки</title>
<link rel="stylesheet" href="jsp/css/createTravel.css">
</head>
<body>
	<jsp:include page="header.jsp">
    <jsp:param name="user" value="${user}" />
</jsp:include>
<form name="createForm" method="POST" action="FrontController">
    <input type="hidden" name="command" value="createTravel" />
    <div class="container">
        <div class="content">
            <p>
                <label for="">Откуда</label> <select  class="form-select" id="arri" name="where">
                <c:forEach items="${places}" var="place" varStatus="loop">
                    <option value="${place.id}">${place.city} ${place.state}</option>
                </c:forEach>
            </select>
            </p>
        </div>
        <div class="content">
            <p>
                <label for="arrive">Куда</label> <select class="form-select" id="arrive" name="arrive">
                <c:forEach items="${places}" var="place" varStatus="loop">
                    <option value="${place.id}">${place.city} ${place.state}</option>
                </c:forEach>
            </select>
            </p>
        </div>
        <div class="content">
            <p>
                <label for="date">Дата</label> <input id="date" type="date"
                                                      class="date-time" placeholder="дата"
                                                      pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}" name="date">
            </p>
        </div>
        <div class="content">
            <p>
                <label for="count">Мест</label> <input class="count" id="count"
                                                       type="number" name="count" maxlength="1" min="1" max="3">
            </p>
        </div>
        <div class="content">
            <p>
                <label for="time">Время</label> <input id="Time" type="time"
                                                       class="date-time" placeholder="время" name="time">
            </p>
        </div>
    </div>
    <div class="btn">
        <button class="create" type="submit">Создать</button>
    </div>
</form>

</body>
</html>