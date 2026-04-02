<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録</title>
</head>
<body>
	<form action="Regist.action" method="post">
		LOGIN ID<input type="text" name="loginId" required><br>
		PASSWORD<input type="password" name="password" required><br>
		ROLE：<br>
		<input type="radio" name="role" value="GENERAL">一般ユーザー<br>
		<input type="radio" name="role" value="ADMIN">管理者<br><br>
		<input type="submit" value="REGISTRATION"><br>
	</form>
	<br>
	<a href="Home.action">BACK</a>
</body>
</html>