<%@page import="java.util.List"%>
<%@page import="model.Shohin_Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	//顧客情報
	List<Shohin_Customer> customer = (List<Shohin_Customer>)session.getAttribute("customer");
%>

<%	//購入商品情報
	List<Shohin_Customer> b_shohin = (List<Shohin_Customer>)session.getAttribute("b_shohin");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="styles3.css">
<title>個人情報、購入商品確認</title>
</head>
<body>
<div class="form">
<div class="form_content">

<div class="form_list">

<h1>確認画面</h1>

	<p>下記の内容で購入を完了します</p><br>
	<p><font size= "5" >～個人情報～</font></p>
<% for (Shohin_Customer cust : customer){ %>
	<p>氏名：
	<input type="text" name="" value="<%= cust.getShimei() %>" readonly>	
	ふりがな：
	<input type="text" name="" value="<%= cust.getKana() %>" readonly>
	郵便番号:
	<input type="text" name="" value="<%= cust.getMail() %>" readonly>
	住所：
	<input type="text" name="" value="<%= cust.getAddress() %>" readonly>
	TEL：
	<input type="text" name="" value="<%= cust.getTel() %>" readonly>
	クレジットカード番号：
	<input type="text" name="" value="<%= cust.getCardnum() %>" readonly>
<% } %>
</p>

<p>
<p><font size= "5" >～商品情報～</font></p>
<% for (Shohin_Customer shohin : b_shohin){ %>
	<p>商品名：
	<input type="text" name="" value="<%= shohin.getBuy_shohin_mei()%>" readonly><br>
	金額：
	<input type="text" name="" value="<%= shohin.getBuy_unit_price() %>円" readonly><br>
	メッセージ：
	<input type="text" name="" value="<%= shohin.getNametag() %>" readonly><br>
	配送先：
	<input type="text" name="" value="<%= shohin.getDelivery() %>" readonly></p> 
<% } %>



<form action="Close" method="post">
<input type="submit" name="done" value="購入完了">
</form>

<input type="submit" name="button" onclick="history.back()" value="戻る">

</div>
</div>
</div>	
</body>
</html>
