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
%>
    
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<%-- CSSファイル --%>
<link rel="stylesheet" href="vamosCss.css" type="text/css">

<title>キャンセル確認 | VAMOS！</title>
</head>
<body>

<%-- 動的インクルード --%>
<jsp:include page="vamosHeader.jsp" />

<h1>キャンセル確認</h1>
<div class="block1">


<p>キャンセルしますか？
<br>
<br>
</p>
<div class="btn">
<table>
<tr>
	<td><form action="ReserveConfirmCancel" method="post">
	<button name="b" value="<%=reserveInfo.get(0).getReserveId() %>">はい</button>
	</form></td>

	<td><button onclick="location.href='ReserveConfirm'">いいえ</button></td>
</tr>
</table>
</div>


</div>
</body>
</html>