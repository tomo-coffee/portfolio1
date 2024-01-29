<%--制作者：渡邊奈津子 --%>
<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
User us = (User)session.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<%-- CSSファイル --%>
<link rel="stylesheet" href="vamosCss.css" type="text/css">
<title>利用者登録確認 | VAMOS！</title>
</head>
<body>

<%-- 動的インクルード --%>
<jsp:include page="vamosHeader.jsp" />

<h1>利用者登録確認</h1>
<br>
<br>
<div class="block1">

<table>
<tr>
	<th align="right">名前：</th>
	<td><%= us.getUserName() %> 様</td>
	<td>　　　</td>
</tr>
<tr>
	<th align="right">TEL：</th>
	<td><%= us.getPhoneNumber() %></td>
	<td></td>
</tr>
<tr>
	<th align="right">ID：</th>
	<td><%= us.getSignInId() %></td>
	<td></td>
</tr>
<tr>
	<th align="right">PASS：</th>
	<td><%= us.getPassword() %></td>
	<td></td>
</tr>
<tr>
	<th colspan="3"><br>この情報で登録しますか？</th>
</tr>
<tr>
	<td colspan="3" align="right"><div class="btn02"><a href="UserConfirmServlet" class="btn02">登録</a></div></td>
</tr>
</table>
<br>
<br>
<div class="btn03"><a href="UserRegisterServlet">登録画面へ戻る</a></div>
</div>
</body>
</html>