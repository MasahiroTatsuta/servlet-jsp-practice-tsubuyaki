<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワード設定</title>
</head>
<body>
<h1>Hello! ${customer.loginId}-san!</h1>
<form action="PasswordUpdate.action" method="post">
PASSWORD<input type="password" name="password" minlength="8" required><br>
<input type="submit" value="PASSWORD UPDATE">
</form>
<a href="Home.action">BACK</a>
</body>
</html>