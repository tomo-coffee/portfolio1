
<%-- 佐藤 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html  lang="ja">
<head>
<meta charset="UTF-8">
<meta http-equiv="refresh" content="5; URL=index.html">
<%-- CSSファイル --%>
<link rel="stylesheet" href="vamosCss.css" type="text/css">
<title>ログアウト | VAMOS！</title>
</head>
<body>
<%-- 動的インクルード --%>
<jsp:include page="vamosHeader.jsp" />

<h1>ログアウト完了</h1>
<div class="block1">
<p>
<br>
5秒後にTOP画面へ移動します。<br>
自動で移動しない場合は
<a href="index.html">こちら</a>をクリックしてください。
</p>
</div>

</body>
</html>