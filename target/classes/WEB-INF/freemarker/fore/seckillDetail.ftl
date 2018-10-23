<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<#include "common/header.ftl">
<title>秒杀商品详情页</title>
</head>
<body>
	<div class="container">
		<div class="card crad-default text-center">
			<h2>
				<div class="card-header">${seckill.name}</div>
			</h2>
			<div class="card-body">
				<h2 class="text-danger">
					<span class="fa fa-clock-o"></span>
					<span class="fa" id="seckill-box"></span>
				</h2>
			</div>	
		</div>	
	</div>
	<!-- 输入手机号的模态窗口 -->
	<div class="modal fade" id="phoneModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header text-center">
					<h4 class="modal-title w-100 font-weight-bold">
						<span class="fa fa-mobile-phone"></span>
						手机号
					</h4>
					<!-- 右上角关闭按钮 -->
					<!--
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button> -->
				</div>
				<div class="modal-body mx-3">
					<div class="md-form mb-5">
						<input type="text" id="phone" name="phone" class="form-control" placeholder="请输入手机号">
					</div>
				</div>
				<div class="modal-footer d-flex justify-content-right">
					<span id="phoneMsg" class="fa"></span>
					<button id="phoneBtn" type="button" class="btn btn-success btn-sm">
						<span class="fa fa-phone"></span>
						Submit
					</button>
				</div>
			</div>
    </div>
	</div>

	<script src="<@spring.url'/js/seckill.js'/>"></script> 
	<script>
		$(function() {
			seckill.detail.init({
				seckillId : ${seckill.seckillId},
				//将timestamp转换为毛秒表示
				startTime : new Date("${seckill.startTime?date}".replace(/-/g,'/')+' '+"${seckill.startTime?time}").valueOf(),
				endTime : new Date("${seckill.endTime?date}".replace(/-/g,'/')+' '+"${seckill.endTime?time}").valueOf()
			});
		});	
	</script>
</body>
</html>