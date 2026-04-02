<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウントリスト</title>
<style>
table { border-collapse: separate; border-spacing: 3px; border: 4px double #333; margin-bottom: 30px; }
th, td { border: 1px solid #333; }
th { font-weight: bold; text-align: center; }
td { text-align: left; }
</style>
</head>
<body>
<h1>Account List</h1>
<table>
<thead>
<tr>
<th>LoginID</th>
<th>Role</th>
</tr>
</thead>
<tbody>
<c:forEach var="id" items="${accounts}">
<tr>
<td><c:out value="${id.loginId}" /></td>
<td><c:out value="${id.role}" /></td>
</tr>
</c:forEach>
</tbody>
</table>
<a href="Home.action">BACK</a>
</body>
</html>