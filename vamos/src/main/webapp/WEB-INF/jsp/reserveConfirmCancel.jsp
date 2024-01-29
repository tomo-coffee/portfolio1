<%--制作者：髙尾 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%@ page import="model.Reserve"%>
<%@ page import="model.Shisetu"%>
<%@ page import="java.util.List"%>  
<%@ page import="java.text.SimpleDateFormat"%>
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

<h1>予約キャンセル</h1> 
<div class="block1">
<% if (delCnt == 1) { %>
<h4 style="color:red"><%= Msg %></h4>
<%}%>
<div class="btn03">
<a href="index.html">TOPへ戻る</a>
</div>
</div>
</body>
</html>