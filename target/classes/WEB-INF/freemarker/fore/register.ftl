<html>
<head>
<meta charset="UTF-8">
<#include "common/header.ftl">
<title>注册</title>
</head>
<body>
	<div class="container mainContent">
		<div class="formDiv">
			<!-- Default form register -->
			<form class="text-center border border-light p-5" action="register" method="post">

				<p class="h4 mb-4">注册</p>

				<!-- Username -->
				<input type="text" id="name" name="name" class="form-control mb-4" placeholder="请输入用户名">

				<!-- Password -->
				<input type="password" id="password" name="password" class="form-control mb-4" placeholder="请输入密码">

				<!-- Confirm password -->
				<input type="password" id="comfirmPass" name="comfirmPass" class="form-control mb-4" placeholder="确认密码">

				<!-- Sign in button -->
				<button class="btn btn-info btn-block my-4" type="submit">注册</button>

			</form>
		</div>
	</div>
</body>
</html>