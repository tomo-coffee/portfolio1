<%--制作者：髙尾 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model.User"%>
<%@ page import="model.Reserve"%>
<%@ page import="model.Shisetu"%>
<%@ page import="java.util.List"%>  
<%@ page import="java.text.SimpleDateFormat"%>  
<%    
User u = (User)session.getAttribute("loginUser");
List<Reserve> reserveInfo = (List<Reserve>)session.getAttribute("reserveInfo");
SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日(E)");
List<Shisetu> shisetuInfo = (List<Shisetu>)session.getAttribute("shisetuInfo"); 
List<Reserve> noFinishDate = (List<Reserve>)session.getAttribute("noFinishDateList");
%>  
<%
String Msg = (String)request.getAttribute("message");
String name = "";
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<%-- CSSファイル --%>
<link rel="stylesheet" href="vamosCss.css" type="text/css">
<title>マイページ| VAMOS！</title>
</head>
<body>
<%-- 動的インクルード --%>
<jsp:include page="vamosHeader.jsp" />

<h1>マイページ</h1>
<div class="block1">

<table>
<tr>
	<th align="right">名前：</th>
	<td><%= u.getUserName() %> 様</td>
</tr>
<tr>
	<th align="right">ID：</th>
	<td><%= u.getSignInId() %></td>
</tr>
</table>
<br>

<table>
<form action="ReserveConfirm" method="post">
<tr>
	<th align="left" colspan="2" bgcolor="#d6eaf2">○<%= Msg %></th>
</tr>	
<% for (Reserve reserve : noFinishDate) {%>	
<tr bgcolor="#fcddca">
	<th align="right">予約ID：</th>
	<td><%=reserve.getReserveId() %></td>
</tr>
<tr>
	<th align="right">貸出日：</th>
	<td><%=sdf.format(reserve.getStartDate()) %></td>
</tr>
<tr>
	<th align="right">終了日：</th>
	<td><%=sdf.format(reserve.getFinishDate()) %></td>
</tr>
<tr>
	<th align="right">施設名：</th>
	<% for (Shisetu shisetu : shisetuInfo) { %>
		<% if (reserve.getShisetuId().equals(shisetu.getShisetuId())) { %>
		<td><%=shisetu.getShisetuMei() %></td>
		<% } %>
	<% } %>
</tr>
<tr>
<th></th>
<td align="right">
	<div class="btn">
	<button name="a" value="<%=reserve.getReserveId() %>,<%= reserve.getShisetuId()%>">確認する</button></div>
</td>
</tr>
<%}%>	
</form>
</table>
<br>
<br>
<div class="btn03">
<a href="index.html">TOPへ戻る</a></div>
</div>
</body>
</html>
