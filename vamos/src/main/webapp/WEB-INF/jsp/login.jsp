<%--制作者：白石 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User, model.Reserve, servlet.LoginServlet"%>
<%
String Msg = (String)request.getAttribute("Msg");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<%-- CSSファイル --%>
<link rel="stylesheet" href="vamosCss.css" type="text/css">
<title>ログイン | VAMOS！</title>
</head>
<body>
<%-- 動的インクルード --%>
<jsp:include page="vamosHeader.jsp" />

<h1>ログイン</h1>

<div class="block1">
<form action="LoginServlet" method="post">
<% if (Msg != null) { %>
<p style="font-size: 12px; color: #ff0000;"><%= Msg %></p>
<% } %>
<div class="just">
<table>
<tr>
	<th align="right">ID:</th>
	<td><input type="text" name="signInId" placeholder="半角英数字5文字"></td>
</tr>
<tr>
	<th align="right">パスワード:</th>
	<td><input type ="password" name="password" placeholder="半角英数字5文字"></td>
</tr>
<tr>
	<td></td>
	<td align="right"><input type="submit" value="ログイン"></td>
</tr>
</table>
</div>
<br>
<br>
<div class="btn03">
<a href="index.html">TOPへ戻る</a></div>
</form>
</div>
</body>
</html>