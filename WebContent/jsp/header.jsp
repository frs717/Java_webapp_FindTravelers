<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="jsp/css/header.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<header class="header">
    <a class="logo" href="${pageContext.request.contextPath}/FrontController?command=showMainPage"><img class="logotype" src="jsp/img/lo.svg" alt=""></a>
    <nav>
        <ul class="nav">
            <c:if test = "${user.role != 'Admin'}">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/FrontController?command=showTravelsPage">&#128269 Найти поездку</a>
            </li>
            </c:if>
            <li class="nav-item">
                <a class="nav-link nav-add" href="${pageContext.request.contextPath}/FrontController?command=showCreateTravelPage">+ Создать поездку</a>
            </li>
            <li class="nav-item">
                <a  class="nav-link nav-link-travels" href="${pageContext.request.contextPath}/FrontController?command=showUserTravelsPage">Мои поездки</a>
            </li>
            <c:if test="${user.role == 'Admin' || user.role == 'Moderator'}">
                <li class="nav-item">
                    <a  class="nav-link nav-link-users" href="${pageContext.request.contextPath}/FrontController?command=showUsersPage">Пользователи</a>
                </li>
                <li class="nav-item">

                    <a  class="nav-link nav-link-travels" href="${pageContext.request.contextPath}/FrontController?command=showAllTravelsPage">Все поездки</a>
                </li>
            </c:if>
            <li class="nav-item nav-item-register">
                <a  class="nav-link nav-link-register" href="${pageContext.request.contextPath}/FrontController?command=logout">Выйти</a>
            </li>
        </ul>
    </nav>
</header>
</body>
</html>