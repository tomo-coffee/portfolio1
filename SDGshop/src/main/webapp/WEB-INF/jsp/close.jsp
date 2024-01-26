<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>
.example input:first-of-type{
    margin-right: auto;
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

input[type="submit"]{
	width: 150px;
	height: 50px;
	text-align: center;
	float:center;
	color: #ffffff;
	background-color: #FF570D;
	border-bottom: solid 5px orange;
}

p{
 font-size: 150%;

}

</style>
<head>
<meta charset="UTF-8">
<title>close画面</title>
</head>
<body>
<div class="example">
<h1><img src="thankyou2.png" width="600" height="300"></h1>
<p>ご注文ありがとうございました！！</p>
<p>またのご利用お待ちしております。</p>

<form action="Close" method="get" style="text-align:center">
<input type="submit" name="top" value="ログイン画面へ">
</form>
</div>
</body>
</html>
