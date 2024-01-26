<%@page import="java.util.List"%>
<%@ page import="model.Shohin_Customer" %>
<% List<Shohin_Customer> registerUser = (List<Shohin_Customer>)session.getAttribute("registerUser"); %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<% String id = "";
   String password = "";
   String mail = "";
   String address = "";
   String shimei = "";
   String kana = "";
   String age = "";
   String gender = "";
   String tel = "";
   String cardnum = "";
   
   	for(Shohin_Customer shohin_Customer : registerUser) {
		id = shohin_Customer.getId();
		password = shohin_Customer.getPassword();
		mail = shohin_Customer.getMail();
		address = shohin_Customer.getAddress();
		shimei = shohin_Customer.getShimei();
		kana = shohin_Customer.getKana();
		age = shohin_Customer.getAge();
		gender = shohin_Customer.getGender();
		tel = shohin_Customer.getTel();
		cardnum = shohin_Customer.getCardnum();
}%> 
   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" type="text/css" href="styles.css">
		<title>会員登録確認画</title>
	</head>
	<body>
	<div class="form">
	<div class="form_content">
	<form action="TourokuKanryou" method="post">
	<div class="form_list">
		<h1>登録確認画面</h1>
		
		<p>下記の内容で会員登録をします</p>
		ID:
		<input type="text" name="" value="<%= id %>" readonly> 
		パスワード:
		<input type="text" name="" value="<%= password %>" readonly> 
		氏名：
		<input type="text" name="" value="<%= shimei %>" readonly> 
		ふりがな：
		<input type="text" name="" value="<%= kana %>" readonly> 
		年齢：
		<input type="text" name="" value="<%= age %>" readonly> 
		性別：
		<input type="text" name="" value="<%= gender %>" readonly> 
		郵便番号：
		<input type="text" name="" value="<%= mail %>" readonly> 
		住所:
		<input type="text" name="" value="<%= address %>" readonly> 
		TEL：
		<input type="text" name="" value="<%= tel %>" readonly> 
		クレジットカード番号：
		<input type="text" name="" value="<%= cardnum %>" readonly> 


<input type="submit" name="touroku" value="登録する">
<input type="submit" name="modoru" value="戻る">
</div>
</form>
		</div>
		</div>	
	</body>
</html>

