<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Reserve, model.Shisetu, model.User, model.DataBaseDAO, 
java.util.Date, java.util.List, java.text.*, java.util.Calendar,servlet.ReserveServlet,model.WeekList" %>
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
<title>予約内容確認 | VAMOS！</title>
</head>
<body>
<%-- 動的インクルード --%>
<jsp:include page="vamosHeader.jsp" />

<h1>予約内容確認</h1>
<div class="block1">

<h3>以下の内容で予約します。<br>よろしいですか？</h3>

<form action="ReserveCheckServlet" method="post">
<table>
<tr>
	<th colspan="2" align="left" bgcolor="#d6eaf2">○予約者情報</th>
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
<tr>
	<td colspan="2" align="right"><input type="submit" value="予約する"></td>
</tr>
</table>
<br>
</form>

<br>
<br>
<div class="btn03"><a href="ReserveServlet">予約画面へ戻る</a></div>

</div>

</body>
</html>