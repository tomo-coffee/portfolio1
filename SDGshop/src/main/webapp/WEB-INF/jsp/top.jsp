<%@page import="model.Shohin_Customer"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//SQLからセッションスコープに保存された商品リストを取得
List<Shohin_Customer> shohinList = 
  (List<Shohin_Customer>) session.getAttribute("shohinList");

//選択した商品情報をセッションスコープに保存された商品情報を取得
String select_shohinmei = 
 (String)session.getAttribute("select_shohinmei");
String select_delivery = 
 (String)session.getAttribute("select_delivery");
String text_nametag = 
 (String)session.getAttribute("text_nametag");
String userName = 
(String)session.getAttribute("userName");

//入力不備があった場合にエラーメッセージを表示
String errorMsg =
  (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>

<style>
.example input:first-of-type{
    margin-right: 70px;
}
.example{
    text-align: center;
     float:center;
 	 clear:both;
}

body {
	text-align: center;
	background: url(sdgs.jpg);
	background-repeat:  no-repeat;     /* 画像の繰り返しを指定  */              
    background-position:center center; /* 画像の表示位置を指定  */
    background-size:cover;             /* 画像のサイズを指定    */
    width:100%;                        /* 横幅のサイズを指定    */
    height:400px;                      /* 縦幅のサイズを指定    */
	
}
table {
 	padding: 3px 0px;/*上下3pxで左右5px*/
    margin-left: auto;
    margin-right: auto;
    text-align: left;
    background: #fff5e5;/*背景色*/
}

</style>

<head>
<meta charset="UTF-8">
<title>SDG'shop</title>
</head>
<body>

<h1><img src="rogo.png" width="150" height="150"></h1>

<% if(userName != null){ %>
<p>ようこそ、<%= userName %>さん！</p>
<% } %>

<form action="Input" method="post">
<% if(shohinList == null){%>
<% }else if (errorMsg != null){%>
<p> <font color = "#FF0000"><%= errorMsg %></font></p>

商品一覧<br>
<table border="0">
<% for(Shohin_Customer shoin: shohinList) {  %>
<tr>
<th><input type="radio" name="shohinmei" value="<%=shoin.getShohin_mei() %>" 
<% if(select_shohinmei != null && select_shohinmei.equals(shoin.getShohin_mei())){ out.print("checked"); } %>></th>
<th><%= shoin.getShohin_mei()%></th>
<th><%= shoin.getHanbai_tanka()%>円</th>
</tr>
    <% } %>
</table>
<%} else{ %>

商品一覧<br>
<table border="0">
<% for(Shohin_Customer shoin: shohinList) {  %>
<tr>
<th><input type="radio" name="shohinmei" value="<%=shoin.getShohin_mei() %>"
<% if(select_shohinmei != null && select_shohinmei.equals(shoin.getShohin_mei())){ out.print("checked"); } %>></th>
<th><%= shoin.getShohin_mei()%></th>
<th><%= shoin.getHanbai_tanka()%>円</th>
</tr>
    <% } %>
<% } %>
</table>
<br>
配送先を選択してください<br>
<input type="radio" name="delivery" value="指定配送"
<% if(select_delivery != null && select_delivery.equals("指定配送")){ out.print("checked"); } %>>指定配送

<input type="radio" name="delivery" value="店舗"
<% if(select_delivery != null && select_delivery.equals("店舗")){ out.print("checked"); } %>>店舗

<br><font size="2">※店舗住所：福岡県福岡市博多区博多駅前3丁目1-1 alpacaビル 8階 (株)alpaca SDG's取組加盟店</font><br><br>

商品にメッセージを入れる場合は入力してください<br>
<% if(text_nametag ==null || text_nametag.equals("")){ %>
nametag:<input type="text" name="nametag" size="35" maxlength="20"  placeholder="商品に好きなメッセージを入れれます(^^♪)">
<% }else{ %>
nametag:<input type="text" name="nametag" value=<%= text_nametag %> size="35" maxlength="20"  placeholder="商品に好きなメッセージを入れれます(^^♪)">
<% } %>
<font size="2">※最大20文字まで</font><br>
<br>
<div class="example">
<input type="submit" name="exit" value="ログアウト"><input type="submit" name="next" value="次へ">
</div>
</form>
</body>
</html>