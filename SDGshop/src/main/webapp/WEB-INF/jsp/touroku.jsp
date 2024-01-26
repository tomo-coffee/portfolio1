	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="model.Shohin_Customer" %>
<%@ page import="java.util.List"%>
<%
String message = (String)request.getAttribute("message");
if (message == null) {
	message = "";
}
%>

<%
String errMessage =(String) request.getAttribute("errMessage");

List<Shohin_Customer> registerUser = (List<Shohin_Customer>)session.getAttribute("registerUser");

String id = "";
String password = "";
String mail = "";
String address = "";
String shimei = "";
String kana = "";
String age = "";
String gender = "";
String tel = "";
String cardnum = "";

if(registerUser != null){
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
	}
}%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<div class="form">
<div class="form_content">
<%= message %>
<form action="TourokuCheck" method="post">
<div class="form_list">
<h1>会員登録</h1>

<% if(registerUser == null){ %>
<% if(errMessage != null){ %>
<font color = "#FF0000"><%= errMessage %></font><br>
<% } %>

<label>ID:<font color = "#FF0000">※必須項目</font></label>
	<input type="text" name="id" maxlength="8" placeholder="5～8桁の半角英数字で入力してください">

<label>パスワード:<font color = "#FF0000">※必須項目</font></label>
	<input type="password" name="password" maxlength="8" placeholder="8桁の数字で入力してください">

<label>氏名：<font color = "#FF0000">※必須項目</font></label>
	<input type="text" name="shimei" maxlength="20" placeholder="例)　山田　太郎 ※姓名の間には半角スペース必須です。">

<label>ふりがな：<font color = "#FF0000">※必須項目</font></label>
	<input type="text" name="kana" maxlength="50" placeholder="例)　やまだ　たろう ※姓名の間には半角スペース必須です。">

<label>年齢：</label>
	<input type="text" name="age" maxlength="3">


<label>性別:</label>
<p>　　男性<input type="radio" name="gender" value="0">
女性<input type="radio" name="gender" value="1"></p>

<label>郵便番号：<font color = "#FF0000">※必須項目</font></label>
	<input type="text" name="mail" maxlength="7">

<label>住所：<font color = "#FF0000">※必須項目</font></label>
	<input type="text" name="address" maxlength="100">

<label>TEL：<font color = "#FF0000">※必須項目</font></label>
	<input type="text" name="tel" maxlength="20">

<label>クレジットカード番号：<font color = "#FF0000">※必須項目</font></label>
	<input type="text" name="cardnum" maxlength="25">


<% } else{%>
<% if(errMessage != null){ %>
<font color = "#FF0000"><%= errMessage %></font><br>
<% } %>

<label>ID:<font color = "#FF0000">※必須項目</font></label>
	<input type="text" name="id" value="<%= id %>" maxlength="8" placeholder="5～8桁の半角英数字で入力してください">

<label>パスワード:<font color = "#FF0000">※必須項目</font></label>
	<input type="password" name="password" value="<%= password %>" maxlength="8" placeholder="8桁の数字で入力してください">

<label>氏名：<font color = "#FF0000">※必須項目</font></label>
	<input type="text" name="shimei" value="<%= shimei %>" maxlength="20" placeholder="例)　山田　太郎 ※姓名の間には半角スペース必須です。">

<label>ふりがな：<font color = "#FF0000">※必須項目</font></label>
	<input type="text" name="kana" value="<%= kana %>" maxlength="50" placeholder="例)　やまだ　たろう ※姓名の間には半角スペース必須です。">

<label>年齢：</label>
	<input type="text" name="age" value="<%= age %>" maxlength="3">

<label>性別：</label>
<p>　　男性<input type="radio" name="gender" value="0"
	<% if(!gender.equals("") && gender.equals("男性")){ out.print("checked"); } %>>
女性<input type="radio" name="gender" value="1"
	 <% if(!gender.equals("") && gender.equals("女性")){ out.print("checked"); } %>></p>

<label>郵便番号：<font color = "#FF0000">※必須項目</font></label>
	<input type="text" name="mail" value="<%= mail %>" <%= shimei %> maxlength="7">

<label>住所：<font color = "#FF0000">※必須項目</font></label>
	<input type="text" name="address" value="<%= address %>" maxlength="100">

<label>TEL：<font color = "#FF0000">※必須項目</font></label>
	<input type="text" name="tel" value="<%= tel %>" maxlength="20">

<label>クレジットカード番号：<font color = "#FF0000">※必須項目</font></label>
	<input type="text" name="cardnum" value="<%= cardnum %>" maxlength="25">


<% } %>

<input type="submit" name="check" value="確認">
<input type="submit" name="top" value="戻る">

</div>
</form>
</div>
</div>
</body>
</html>