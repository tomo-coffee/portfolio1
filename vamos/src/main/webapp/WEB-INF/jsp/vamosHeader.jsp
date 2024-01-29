<%--制作者：渡邊奈津子 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@page import = "model.User" %>
<% User user = (User)session.getAttribute("loginUser"); %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>予約するならバモス！</title>
</head>
<body>

<header>

<table>
<tr>
	<td align="center">
	<a href ="index.html"><img src="img/logo_vamos.png" alt="予約するならバモス！" width="120"></a>
	</td>
	<td>　　　</td>
	
		<!-- 佐藤のテスト -->
	<% if(user == null){%>
		<td valign="bottom" class="btn02">
		<a href="LoginServlet">ログイン</a>
		</td>
		<td valign="bottom" class="btn02">
		<a href="UserRegisterServlet">利用者登録</a>
		</td>

	<%}else{%>
		<td valign="bottom">
		<%=user.getUserName() %> 様
		</td>
		<td>　</td>
		<td valign="bottom" class="btn02">
		<a href="Mypage">マイページ</a>
		</td>
		<td valign="bottom" class="btn03">
		<a href="Logout">ログアウト</a>
		</td>

	<% }%>
</tr>
<!-- 佐藤のテストここまで -->
</table>
</header>

</body>
</html>