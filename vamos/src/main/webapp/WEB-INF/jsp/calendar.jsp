<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Reserve, model.Shisetu, model.User, model.DataBaseDAO, 
java.util.Date, java.util.List, java.text.*, java.util.Calendar,servlet.ReserveServlet,model.WeekList" %>
<%WeekList weekList = (WeekList)session.getAttribute("weekList"); %>
<%List<Shisetu> shisetuInfo = (List<Shisetu>)session.getAttribute("shisetuInfo"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- CSSファイル --%>
<link rel="stylesheet" href="vamosCss.css" type="text/css">
<title>Insert title here</title>
</head>
<body>


<%//佐藤記入分 %>
<form action="CalendarResult" method="post">

<%String[] dateArray= weekList.getDateTable();%>
<%boolean[] reserveArray= weekList.getTable();%>
<table>
<tr>
	<td width="75">
	<%if(weekList.getToday().equals(dateArray[0])){ %>
	<button name="a" value="prev"disabled>前の週</button></td>
	<%}else{ %>
	<button name="a" value="prev">前の週</button></td>
	<%} %>
	<td width="280" align="center"><%= shisetuInfo.get(0).getShisetuMei() %>の空き状況</td>
	<td width="75"><button name="a" value="next">次の週</button></td>
</tr>
</table>
<div class="res-tbl">
<table  border="1" width="434">
<tr>
<% for(int i = 0; i < 7;i++){%>
<td width ="62"><%=dateArray[i] %></td>
<%} %>
</tr>
<tr>
<% for(int i = 0; i < 7;i++){%>
<%if(reserveArray[i]) {%>
<td width = "62">〇</td>
<%}else{%>
<td width="62">×</td>
<% }%>
<%} %>
</tr>
</table>
</div>
</form>
</body>
</html>