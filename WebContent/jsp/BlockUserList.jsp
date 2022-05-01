<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
<link rel="stylesheet" href="jsp/css/usersList.css">
<meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Заблокированные</title>
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="user" value="${user}"/>
</jsp:include>
<div class="wrapper">
  <c:forEach  items="${usersList}" var="oneUser" varStatus="loop">
    <form name="UserForm" method="POST" action="FrontController">
      <div class="content">
        <p style="color: #ad1858; font-weight: bold">Логин: <c:out value="${oneUser.login}"/></p>
        <hr>
        <p>Имя: <c:out value="${oneUser.name}"/></p>
        <hr>
        <p>Телефон: <c:out value="${oneUser.phone}"/></p>
        <hr>
        <p>Машина: <c:out value="${oneUser.idCar}"/></p>
        <hr>
        <p>id: <c:out value="${oneUser.id}"/></p>
        <hr>
        <p>blockstatus: <c:out value="${oneUser.status}"/></p>
        <hr>
        <p>Пол: <c:out value="${oneUser.gender}"/></p>
         <hr>
        <c:if test="${oneUser.status == 'false'}">
            <form>
                <input type="hidden" value="blockUserById" name="command"/>
                <input type="hidden" name="userId" value="${oneUser.id}"/>
                <button type="submit"  class="btn block">Заблокировать</button>
            </form>
           
        </c:if>
        <c:if test="${oneUser.status == 'true'}">
             <form>
                <input type="hidden" value="unblockUserById" name="command"/>
                <input type="hidden" name="userId" value="${oneUser.id}"/>
                <button type="submit"  class="btn block">Разблокировать</button>
             </form>
        </c:if>
      </div>
    </form>
  </c:forEach>
</div>
</body>
</html>