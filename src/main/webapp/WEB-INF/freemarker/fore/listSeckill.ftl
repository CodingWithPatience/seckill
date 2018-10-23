<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<#include "common/header.ftl">
<title>秒杀商品列表</title>
</head>
<body>
	<div class="container">
		<div class="card card-default">
			<div class="card-header text-center">
				<h2>秒杀商品列表</h2>	
			</div>	
			<div class="card-body">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>名称</th>	
							<th>库存</th>	
							<th>开始时间</th>	
							<th>结束时间</th>	
							<th>创建时间</th>	
							<th>详情页</th>	
						</tr>	
					</thead>	
					<tbody>
						<#list seckills as sk>
							<tr>
								<td>${sk.name}</td>	
								<td>${sk.number}</td>	
								<td>${sk.startTime}</td>	
								<td>${sk.endTime}</td>	
								<td>${sk.createTime}</td>	
								<td><a class="btn btn-info btn-sm" href="${sk.seckillId}/detail" target="_blank">link</a></td>	
							</tr>	
						</#list>
					</tbody>
				</table>	
			</div>
		</div>
	</div>
</body>
</html>