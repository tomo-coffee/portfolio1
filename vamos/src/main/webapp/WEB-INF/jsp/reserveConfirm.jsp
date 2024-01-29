<%--制作者：髙尾 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model.User" %>
<%@ page import="model.Reserve"%>
<%@ page import="model.Shisetu"%>
<%@ page import="java.util.List"%>  
<%@ page import="java.text.SimpleDateFormat"%>     

<%   
User u = (User)session.getAttribute("loginUser");
List<Reserve> reserveInfo = (List<Reserve>)session.getAttribute("checkReserve");
SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日(E)");
List<Shisetu> shisetuInfo = (List<Shisetu>)session.getAttribute("shisetuInfo");
List<Shisetu> reserveShisetu = (List<Shisetu>)session.getAttribute("reserveShisetu");
%>
<%
String Msg = (String)request.getAttribute("message");
int delCnt = 1;
%> 

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<%-- CSSファイル --%>
<link rel="stylesheet" href="vamosCss.css" type="text/css">

<title>予約確認画面 | VAMOS！</title>
</head>
<body>
<%-- 動的インクルード --%>
<jsp:include page="vamosHeader.jsp" />

<h1>予約確認画面</h1> 
<div class="block1">
<%--1件分の予約詳細を表示--%>
<form action="CancelConfirm" method="post">

<table width="800px">
<tr>
	<th colspan="2" align="left" bgcolor="#d6eaf2">○予約情報</th>
	<td rowspan="9">　　</td>
	<td rowspan="9"><iframe src="https://www.google.com/maps?output=embed&q=<%=shisetuInfo.get(0).getAddress() %>&t=m&hl=ja&z=14" width="380" height="280" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe></td>
</tr>
<tr>
	<th align="right">予約ID：</th>
	<td><%=reserveInfo.get(0).getReserveId() %></td>
</tr>
<tr>
	<th align="right">貸出日：</th>
	<td><%=sdf.format(reserveInfo.get(0).getStartDate()) %></td>
</tr>
<tr>
	<th align="right">終了日：</th>
	<td><%=sdf.format(reserveInfo.get(0).getFinishDate()) %></td>
</tr>
<tr>
	<th align="right">施設名：</th>
	<td><%=reserveShisetu.get(0).getShisetuMei() %></td>
</tr>
<tr>
	<th align="right">住所：</th>
	<td><%=reserveShisetu.get(0).getAddress() %></td>
</tr>
<tr>
	<th align="right">電話：</th>
	<td><%=reserveShisetu.get(0).getTel() %></td>
</tr>
<tr>
	<th align="right">人数：</th>
	<td><%=reserveInfo.get(0).getNumberOfpeople() %>人</td>
</tr>
<tr>
	<th align="right">料金：</th>
	<td><%=String.format("%,d", reserveInfo.get(0).getSumFee()) %>円</td>
</tr>
</table>
<br>
<% if (reserveInfo != null) {%>
<div class="btn">
<button name="b" value="<%=reserveInfo.get(0).getReserveId() %>">予約キャンセル</button>
</div>
<% } %>
</form>
<br>
<br>
<div class="btn03">
<a href="Mypage">戻る</a>
</div>

</div>
</body>
</html> 