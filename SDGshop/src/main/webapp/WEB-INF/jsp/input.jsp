<%@page import="java.util.List"%>
<%@page import="model.Shohin_Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//結果メッセージをリクエストスコープから取得
	String errMsg = (String)request.getAttribute("message");
		if (errMsg == null){
			errMsg = "";
		}
%>

<%
	//ログイン時の情報をセッションスコープから取得
	Shohin_Customer logininfo = (Shohin_Customer)session.getAttribute("info");
	
	//性別判断用
	String stM = "男性";
	String stF = "女性";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="styles3.css">
<title>個人情報入力</title>
</head>
<body>
<div class="form">
<div class="form_content">
<form action="Check" method="post">
<div class="form_list">
<h1>個人情報入力</h1>

<p style="color:red">
<%= errMsg %></p>



<%--　ログイン済みの場合 --%>
<% if (logininfo != null) { %>
<p>個人情報入力、確認してください</p>
<p>登録された以下の内容で、購入手続きを行います。</p>
<p>変更がある場合は、再度入力してください。</p>
<p>（郵便番号、電話番号は、ハイフンを
<b><span style="border-bottom: double;">除いて</span></b>
入力してください）</p><br><br>

	<p >氏名:<font color = "#FF0000">※必須項目</font></p>
	<input type="text" name="shimei" maxlength="20" placeholder="例）山田 太郎" value="<%= logininfo.getShimei() %>">
	 
	
	
	<p >ふりがな:<font color = "#FF0000">※必須項目</font></p>
	<input type="text" name="kana" maxlength="50" placeholder="例）やまだ たろう" value="<%= logininfo.getKana() %>">
	
	
	
	<p >年齢:<font color = "#FF0000">※必須項目</font></p>
	<input type="text" name="age" maxlength="3" value="<%= logininfo.getAge() %>">　※任意
	
	
	
	<p >性別:※任意</p>
	
	　　男性<input type="radio" name="gender" value="0" <% if(logininfo.getGender().equals(stM)){ out.print("checked");} %>>
	女性<input type="radio" name="gender" value="1" <% if(logininfo.getGender().equals(stF)){ out.print("checked");} %>>
	
	
	<p >郵便番号:<font color = "#FF0000">※必須項目</font></p>
	<input type="text" name="mail" maxlength="7" value="<%= logininfo.getMail() %>"> 
	
	
	<p >住所:<font color = "#FF0000">※必須項目</font></p>
	<input type="text" name="address"  maxlength="100" value="<%= logininfo.getAddress() %>"> 
	
	
	<p >TEL:<font color = "#FF0000">※必須項目</font></p>
	<input type="text" name="tel" maxlength="20" value="<%= logininfo.getTel() %>">

	
	<p >クレジットカード番号:<font color = "#FF0000">※必須項目</font></p>
	<input type="text" name="cardnum" maxlength="25" value="<%= logininfo.getCardnum() %>">
	

<%--　ログインなしの場合 --%>
<% } else { %>
<p>個人情報を入力、確認してください</p>
<p>郵便番号、電話番号は、ハイフンを
<b><span style="border-bottom: double;">除いて</span></b>
入力してください</p><br>
	
	<p >氏名:<font color = "#FF0000">※必須項目</font></p>
	<input type="text" name="shimei" maxlength="20" placeholder="例）山田 太郎" >
	
	
	<p >ふりがな:<font color = "#FF0000">※必須項目</font></p>
	<input type="text" name="kana" maxlength="50" placeholder="例）やまだ たろう" >
	
	
	<p >年齢:※任意</p>
	<input type="text" name="age" maxlength="3">
	
	
	<p >性別:※任意</p>
	　　男性<input type="radio" name="gender" value="0">
	女性<input type="radio" name="gender" value="1">
	
	
	<p >郵便番号:<font color = "#FF0000">※必須項目</font></p>
	<input type="text" name="mail" maxlength="7">
	
	
	<p >住所:<font color = "#FF0000">※必須項目</font></p>
	<input type="text" name="address"  maxlength="100">
	
	
	<p >電話番号:<font color = "#FF0000">※必須項目</font></p>
	<input type="text" name="tel" maxlength="20">
	
	
	<p >クレジットカード番号:<font color = "#FF0000">※必須項目</font></p>
	<input type="text" name="cardnum" maxlength="25">
	
	
<% }  %>

<input type="submit" name="check" value="確認画面へ">
<input type="submit" name="back" value="戻る">

</div>
</form>
</div>
</div>
</body>
</html>