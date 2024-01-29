<!-- 白石 ＋佐藤　ver3 白石改修10/23-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Reserve, model.Shisetu, model.User, model.DataBaseDAO, 
java.util.Date, java.util.List, java.text.*, java.util.Calendar,servlet.ReserveServlet,model.WeekList" %>
<%
String Msg = (String)request.getAttribute("Msg");
User loginUser = (User)session.getAttribute("loginUser");
List<Shisetu> shisetuInfo = (List<Shisetu>)session.getAttribute("shisetuInfo");
WeekList weekList = (WeekList)session.getAttribute("weekList");
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
Calendar calendar = Calendar.getInstance();
calendar.add(Calendar.DAY_OF_MONTH, 1);
String today = sdf.format(calendar.getTime());
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<%-- CSSファイル --%>
<link rel="stylesheet" href="vamosCss.css" type="text/css">
<title>予約内容の入力 | VAMOS！</title>
</head>
<body>
<%-- 動的インクルード --%>
<jsp:include page="vamosHeader.jsp" />

<%--テーブル文字を小文字にするスタイルシート --%>
<style>
.example{
    font-size: 75%;
    font-weight: bold;
}
</style>


<h1>予約施設情報</h1>
<div class="block1">
<br>
<table>
<tr>
	<th colspan="2" align="left" bgcolor="#d6eaf2">○予約者情報</th>
		<td rowspan="9">　　</td>
	<td rowspan="9"><iframe src="https://www.google.com/maps?output=embed&q=<%=shisetuInfo.get(0).getAddress() %>&t=m&hl=ja&z=14" width="380" height="280" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe></td>
</tr>
<tr>
	<th align="right">ご予約者:</th>
	<td><%= loginUser.getUserName() %>様</td>
</tr>
<tr>
	<th align="right">電話番号:</th>
	<td><%= loginUser.getPhoneNumber() %></td>
</tr>
<tr>
	<th colspan="2" align="left" bgcolor="#fcddca">○予約施設概要</th>
</tr>
<tr>
	<th align="right">施設名:</th>
	<td><%= shisetuInfo.get(0).getShisetuMei() %></td>
</tr>
<tr>
	<th align="right">収容人数:</th>
	<td><%= String.format("%,d", shisetuInfo.get(0).getCapacity()) %>人</td>
</tr>
<tr>
	<th align="right">住所:</th>
	<td><%= shisetuInfo.get(0).getAddress() %></td>
</tr>
<tr>
	<th align="right">電話番号:</th>
	<td><%= shisetuInfo.get(0).getTel() %></td>
</tr>
<tr>
	<th align="right">利用料金/日:</th>
	<td><%= String.format("%,d", shisetuInfo.get(0).getFee()) %>円</td>
</tr>
</table>
</div>
<br>
<h1>予約情報入力</h1>

<div class="block1">
<%--佐藤記入分 --%>

<%--
<%String[] dateArray= weekList.getDateTable();%>
<%boolean[] reserveArray= weekList.getTable();%>
--%>

<%--予約情報入力時エラー --%>
	<% if (Msg != null) { %>
	<p style="font-size: 12px; color: #ff0000;"><%= Msg %></p>
	<% }else{ %>
	<br>
	<% } %>

<table>
<tr>
	<td colspan="3" class="example">施設の空き状況を確認して<br>
	利用開始日と終了日および利用人数を入力して[予約する]ボタンを押してください</td>
</tr>
<tr>
	<td><br></td>
</tr>
</table>

<%--calender.jspに移動の為コメントアウト
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
 --%>
 
 <%--予約状況 --%>
<iframe src="CalendarResult"  style="border: none"width="600" height="120"></iframe>
 

<%//佐藤記入分ここまで %>
<form action="ReserveServlet" method="post">
<table>
<tr>
	<th align="right">利用開始日:</th>
	<td><input type="date" name="startDate" min="<%= today %>"></td>
</tr>
<tr>
	<th align="right">利用終了日:</th>
	<td><input type="date" name="finishDate" min="<%= today %>"></td>
</tr>
<tr>
	<th align="right">利用人数:</th>
	<td><input type="number" name="numberOfpeople" min="1" max="<%= shisetuInfo.get(0).getCapacity() %>">人</td>
</tr>
<tr>
	<td></td><td></td>
	<td align="right"><input type="submit" value="予約最終確認"></td>
</tr>
</table>

</form>
<br>
<br>
<div class="btn03">
<a href="index.html">TOPへ戻る</a></div>
<% //<div class="btn03"> <a href="LoginServlet">ログイン画面へ戻る</a></div>%>
</div>
</body>
</html>