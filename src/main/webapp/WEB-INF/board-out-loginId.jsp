<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board[0].loginId}さんの掲示板</title>
<style>
	table { border-collapse: separate; border-spacing: 3px; border: 4px double #333; margin-bottom: 30px; }
	th, td { border: 1px solid #333; }
	th { font-weight: bold; text-align: center; }
	td { text-align: left; }
	.back-button {
	    appearance: button; /* ブラウザ標準のボタン見た目にする */
	    -webkit-appearance: button;
	    text-decoration: none;
	    padding: 2px 10px;
	    margin-bottom: 20px;
	    color: black;
	    background-color: #efefef;
	    border: 1px solid #767676;
	    border-radius: 2px;
	    font: 400 13.3333px Arial; /* 標準的なボタンのフォント設定 */
  	}
</style>
</head>
<body>
<h1>Board List</h1>
<table>
	<thead>
		<tr>
			<th>
				<a href="BoardDateSort.action?sort=${param.sort == 'asc' ? 'desc' : 'asc' }&boardPage=">
					Date
					<c:choose>
						<c:when test="${param.sort == 'asc' }">▲</c:when>
						<c:otherwise>▼</c:otherwise>
					</c:choose>
				</a>
			</th>
			<th>
				Login ID
			</th>
			<th>
				Contents
			</th>
			<th>
				<a href="BoardLikeSort.action?sort=${param.sort == 'asc' ? 'desc' : 'asc' }&boardPage=">
					Likes
					<c:choose>
						<c:when test="${param.sort == 'asc' }">▲</c:when>
						<c:otherwise>▼</c:otherwise>
					</c:choose>
				</a>
			</th>
			<th>
				<a href="BoardDisLikeSort.action?sort=${param.sort == 'asc' ? 'desc' : 'asc' }&boardPage=">
					DisLikes
					<c:choose>
						<c:when test="${param.sort == 'asc' }">▲</c:when>
						<c:otherwise>▼</c:otherwise>
					</c:choose>
				</a>
			</th>
			<th>
				Clear
			</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="likeTotal" value="0" />
		<c:set var="disLikeTotal" value="0" />
		<c:forEach var="contents" items="${ board }">
			<tr>
				<td>
					<c:out value="${ contents.myDate }" />
				</td>
				<td>
					<c:out value="${ contents.loginId }" />
				</td>
				<td>
					<c:out value="${ contents.contents }" />
				</td>
				<td>
					<a href="BoardLikeItem.action?id=<c:out value='${contents.id}' />&boardPage=&loginId=<c:out value='${contents.loginId}' />">
						<c:out value="${ contents.likes }" />
					</a>
				</td>
				<td>
					<a href="BoardDisLikeItem.action?id=<c:out value='${contents.id}' />&boardPage=&loginId=<c:out value='${contents.loginId}' />">
						<c:out value="${ contents.disLikes }" />
					</a>
				</td>
				<td>
					<c:if test="${ customer.loginId == contents.loginId }">
						<a href="BoardClearItem.action?id=<c:out value='${contents.id}' />&boardPage=&loginId=<c:out value='${contents.loginId}' />">Clear</a>
					</c:if>
				</td>
			</tr>
			<c:set var="likeTotal" value="${likeTotal + contents.likes}" />
			<c:set var="disLikeTotal" value="${disLikeTotal + contents.disLikes}" />
		</c:forEach>
	</tbody>
</table>
<p><strong>like total= ${likeTotal}. dislike total=${disLikeTotal}</strong></p>
<br>
<c:forEach var="b" items="${board}">
	<c:if test="${customer.loginId==b.loginId}">
		<a href="BoardClear.action?loginId=<c:out value='${customer.loginId}' />&boardPage=&contentsLoginId=<c:out value='${b.loginId}' />" class="back-button">Clear</a>
	</c:if>
</c:forEach>
<br>
<a href="Board.action">BACK TO ALL BOARDS</a>
</body>
</html>