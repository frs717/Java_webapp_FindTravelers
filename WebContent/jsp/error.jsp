<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>${pageContext.errorData.statusCode}</title>
<link rel="stylesheet" href="jsp/css/errors.css">
</head>
<body>
	<div class="wrap">
		<p class="text"></p>
		<p style="font-size: 18px">
			Кажется что-то пошло не так! Страница, которую вы запрашиваете, не
			существует. Возможно, она <br> устарела, была удалена или был
			введён неверный адрес в адресной строке.
		</p>
	</div>
</body>
</html>