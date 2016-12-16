<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%String path =request.getServerName()+":"+request.getServerPort()+"/"+request.getContextPath();   %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>五子棋</title>

</head>
<body>
	<div>
		<canvas id="borad" width="500px" height="500px"
			onclick="putOne(event)">
		your brownser not support canvas
	</canvas>
	</div>
</body>
<script type="text/javascript" src="./js/loaddata.js"></script>
<script type="text/javascript" src="./js/init.js"></script>
</html>