<%--制作者：大田 --%>

<%@page import = "model.User,model.Shisetu,java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <% User user = (User)session.getAttribute("loginUser"); %>
 <% List<Shisetu> shisetuAllList =(List<Shisetu>)request.getAttribute("ShisetuAllList"); %>
 <% if(shisetuAllList != null){int size = shisetuAllList.size();} %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="vamosCss.css" type="text/css">
<title>予約するなら！VAMOS！</title>
</head>
<body>
<%-- 動的インクルード --%>
<jsp:include page="vamosHeader.jsp" />


<%-- 上部に各リンクを表示するので不要の為コメントアウト
<h1>お知らせ</h1>
<% if(user == null){%>
	<div class="btn02">
	<a href="LoginServlet">ログイン</a>
	
	<a href="UserRegisterServlet">会員登録</a>
	</div>
<%}else{%>
	<div class="btn02">
<%=user.getUserName() %>さん
<a href="Mypage">マイページ</a>
<a href="Logout">ログアウト</a>
</div>
<% }%>
--%>
<br>
<div class="block1">
<% for(Shisetu shisetu : shisetuAllList){ %>  
<form action ="Vamos" method="post">
<div class="table01">
<table>
	
		<tr>
			<th rowspan="4"><img src="img/<%=  shisetu.getShisetuId()%>.jpg" height="160" width="270" ></th>
			<th>施設名：</th>
			<td><strong><%=shisetu.getShisetuMei() %></strong></td>
			<td rowspan="4" width="100"><div class="btn"><button  name="a" value=<%=shisetu.getShisetuId() %>>予約する</button></div></td>
		</tr>
		<tr>
			<th>収容人数：</th>
			<td ><%=String.format("%,d", shisetu.getCapacity()) %> 人</td>
		</tr>
		<tr>
			<th>料金(/Day)：</th>
			<td ><%=String.format("%,d", shisetu.getFee()) %> 円</td>
		</tr>
		<tr>
			<th>住所：</th>
			<td ><%=shisetu.getAddress() %></td>
		</tr>
		<tr>
			<td colspan="4"><%=shisetu.getProperty() %></td>
		</tr>
</table>
<br>
</div>
</form>
<%} %>

</div>
</body>
</html>


