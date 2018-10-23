<html>
<head>
<meta charset="UTF-8">
<#include "common/header.ftl">
<title>登陆</title>
</head>
<body>
	<div class="container mainContent">
		<div class="formDiv">
			<!-- Default form login -->
			<form class="text-center border border-light p-5" action="login" method="post">

				<p class="h4 mb-4">登陆</p>
				<#if errMsg!=null>
					<p class="h6 mb-4 text-danger">${errMsg}</p>
				</#if>

				<!-- Username -->
				<input type="text" id="name" name="name" class="form-control mb-4" placeholder="请输入用户名">
				<!-- Password -->
				<input type="password" id="password" name="password" class="form-control mb-4" placeholder="请输入密码">

				<div class="d-flex justify-content-around">
					<div>
						<!-- Remember me -->
						<div class="custom-control custom-checkbox">
							<input type="checkbox" class="custom-control-input" id="defaultLoginFormRemember">
							<label class="custom-control-label" for="defaultLoginFormRemember">记住密码</label>
						</div>
					</div>
					<div>
						<!-- Forgot password -->
						<a href="">忘记密码?</a>
					</div>
				</div>

				<!-- Sign in button -->
				<button class="btn btn-info btn-block my-4" type="submit">登陆</button>

				<!-- Register -->
				<p>没有账号?
					<a href="<@spring.url'/register'/>">注册</a>
				</p>
			</form>
		</div>
	</div>
	
<!-- Default form login -->
</body>
</html>