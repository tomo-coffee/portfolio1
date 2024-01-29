<!-- 白石 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Reserve, model.Shisetu, model.User, model.DataBaseDAO, servlet.ReserveServlet, java.util.List, java.text.SimpleDateFormat" %>
<%
User loginUser = (User)session.getAttribute("loginUser");
Reserve Reserve = (Reserve)session.getAttribute("reserveInfo");
List<Shisetu> shisetuInfo = (List<Shisetu>)session.getAttribute("shisetuInfo");
DataBaseDAO dataBaseDAO = new DataBaseDAO();
List<Shisetu> shisetuList = dataBaseDAO.shisetuRead();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日(E)");
int sumFee = (int)session.getAttribute("sumFee");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<%-- CSSファイル --%>
<link rel="stylesheet" href="vamosCss.css" type="text/css">
<title>予約完了 | VAMOS！</title>
</head>
<body>
<%-- 動的インクルード --%>
<jsp:include page="vamosHeader.jsp" />

<h1>予約完了</h1>
<div class="block1">

<h3>ご予約が完了しました。<br>お客様のご来館を心よりお待ちしております。</h3>

<form action="ReserveCompleteServlet" method="post">
<table width="800px">
<tr>
	<th colspan="2" align="left" bgcolor="#d6eaf2">○予約者情報</th>
	<td rowspan="9">　　</td>
	<td rowspan="9"><iframe src="https://www.google.com/maps?output=embed&q=<%=shisetuInfo.get(0).getAddress() %>&t=m&hl=ja&z=14" width="380" height="280" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe></td>
</tr>
<tr>
	<th align="right">ご予約者名:</th>
	<td><%= loginUser.getUserName() %>様</td>
</tr>
<tr>
	<th align="right">ご予約者連絡先:</th>
	<td><%= loginUser.getPhoneNumber() %></td>
</tr>
<tr>
	<th colspan="2" align="left" bgcolor="#fcddca">○予約施設概要</th>
</tr>
<tr>
	<th align="right">利用施設:</th>
	<td><%= shisetuInfo.get(0).getShisetuMei() %></td>
</tr>
<tr>
	<th align="right">利用人数:</th>
	<td><%= Reserve.getNumberOfpeople() %>人</td>
</tr>
<tr>
	<th align="right">利用開始日:</th>
	<td><%= sdf.format(Reserve.getStartDate()) %></td>
</tr>
<tr>
	<th align="right">利用終了日:</th>
	<td><%= sdf.format(Reserve.getFinishDate()) %></td>
</tr>
<tr>
	<th align="right">利用料合計:</th>
	<td><%= String.format("%,d", sumFee) %>円</td>
</tr>
</table>
</form>
<br>
<br>
<div class="btn03"><a href="index.html">TOPへ戻る</a></div>

</div>
</body>
</html>