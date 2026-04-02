<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー</title>
</head>
<body>
	<h1>Hello! ${customer.loginId}-san!</h1>
	<p>
		<strong>Board</strong><br>
		<a href="Board.action">BOARD</a>
	</p>
	<c:if test="${ customer.role == 'ADMIN' }">
		<p>
			<strong>Account?</strong><br>
			<a href="RegistForm.action">ACCOUNT REGISTRATION</a>
		</p>
		<p>
			<strong>Account List?</strong><br>
			<a href="AccountList.action">ACCOUNT LIST</a>
		</p>
	</c:if>
	<p>
		<strong>Password Change?</strong><br>
		<a href="Password.action">PASSWORD CHANGE</a>
	</p>
	<p>
		<strong>Logout?</strong><br>
		<a href="Logout.action">LOGOUT</a>
	</p>
</body>
</html>