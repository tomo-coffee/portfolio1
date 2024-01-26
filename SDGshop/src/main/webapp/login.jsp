<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
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
position: absolute;
  top: 50%;
  left: 50%;
  transform: translateY(-50%) translateX(-50%);/* 要素の大きさの半分の距離を左、上方向に移動する */
  -webkit-transform: translateY(-50%) translateX(-50%);
    text-align: center;
     float:center;
 	 clear:both;
}

table {
 	padding: 3px 0px;/*上下3pxで左右5px*/
    margin-left: auto;
    margin-right: auto;
    background: #fff5e5;/*背景色*/
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

</style>

<head>
<meta charset="UTF-8">
<title>SDG'shop</title>
</head>
<body>
<div class="example">
<h1>SDGshopへようこそ♪</h1>
<form action="TOP" method="post">

(株)alpacaについて<br>
私たち「(株)alpaca」の取組として、<br>
人々の生活に強く結びつく、生産から消費者の手に届くプロセス全体において、<br>
環境、社会への負の影響を最小化し、SDG'sに貢献することを目標に活動しております。<br><br>

SDGsとは？<br>
「持続可能な開発」を意味し、貧困、不平等・格差、気候変動による影響など、<br>
世界のさまざまな問題を根本的な解決、<br>
すべての人にとってより良い世界をつくるために設定された、<br>
世界共通の17の目標です。<br><br>

<table border="0">
<tr>
<th>
ユーザー名：<input type="text" name="id"><br>
</th>
</tr>
<tr>
<th>
パスワード：<input type="password" name="password"><br>
</th>
</tr>
</table>

<input type="submit" name="login" value="ログイン"><input type="submit" name="gest" value="ゲスト">
<br>

<a href="Touroku">会員登録はこちら♪</a>

<% if(errorMsg == null){%>
<% }else if (errorMsg != null){%>
<p> <font color = "#FF0000"><%= errorMsg %></font></p>
<% } %>

</form>
</div>
</body>
</html>