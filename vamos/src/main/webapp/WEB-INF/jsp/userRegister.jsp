<%--制作者：渡邊奈津子 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String errorMsg =  (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<%-- CSSファイル --%>
<link rel="stylesheet" href="vamosCss.css" type="text/css">
<title>利用者登録 | VAMOS！</title>
</head>
<body>
<%--動的インクルード --%>
<jsp:include page="vamosHeader.jsp" />

<h1>利用者登録</h1>
<div class="block1">
	<%--エラーメッセージ --%>
	<% if(errorMsg != null){ %>
		<p style="font-size: 12px; color: #ff0000;"><%= errorMsg %></p>
	<% } else{%>
	<br>
	<% } %>
<form action="UserRegisterServlet" method="post">
<table>
<tr>
	<th align="right">名前：</th>
	<td><input type="text" name="userName" placeholder="例）山田太郎"></td>
	<td style="font-size: 9px" valign="bottom">最大10文字</td>
	<td></td>
</tr>
<tr>
	<th align="right">TEL：</th>
	<td><input type="text" name="phoneNumber" placeholder="例）080-0880-8800"></td>
	<td style="font-size: 9px" valign="bottom">最大20文字</td>
	<td></td>
</tr>
<tr>
	<th align="right">ID：</th>
	<td><input type="text" name="signInId" placeholder="例）abc123"></td>
	<td style="font-size: 9px" valign="bottom">半角英数字5文字</td>
	<td></td>
</tr>
<tr>
	<th align="right">PASS：</th>
	<td><input type="password" name="password" placeholder="例）45def"></td>
	<td style="font-size: 9px" valign="bottom">半角英数字5文字</td>
	<td></td>
</tr>
<tr>
<td align="right" colspan="2"><br><input type="reset" value="クリア"></td>
<td align="right"><br><input type="submit" value="登録確認"></td>
</tr>
</table>
</form>
</div>
</body>
</html>