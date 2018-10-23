/**
 * 
 */

var seckill = {
	//封装url
	URL : {
		now : function() {
			return "/seckill/time/now";
		},
		
		exposer : function(seckillId) {
			return "/seckill/"+seckillId+"/exposer";
		},
		
		excution : function(seckillId, md5) {
			return "/seckill/"+seckillId+"/"+md5+"/excution";
		}
	},

	//认证手机号
	validatePhone : function(phone) {
		if(phone && phone.length==11 && !isNaN(phone))
			return true;
		else
			return false;
	},
	
	handleSeckill : function(seckillId, node) {
		node.hide()
			.html("<button class=\"btn btn-primary\" id=\"killBtn\">开始秒杀</button>");
		$.post(seckill.URL.exposer(seckillId),{},function(result) {
			if(result && result['success']) {
				var exposer = result['data'];
				var exposed = exposer['exposed'];
				if(exposed) {
					var md5 = exposer['md5'];
					//绑定秒杀点击事件
					$("#killBtn").one('click',function() {
						$(this).addClass("disable");
						//执行秒杀请求
						$.post(seckill.URL.excution(seckillId, md5),{},function(result) {
							if(result && result['success']) {
								var killResult = result['data'];
								var killState = killResult['state'];
								var killStateInfo = killResult['stateInfo'];
								//显示执行结果
								if(killState == 1) {
									var textColor = "text-success";
								}else {
									var textColor = "text-danger";
								}
								node.html("<span class=\""+textColor+"\">"
										+killStateInfo+"</span>");
							}
						});
					});
					node.show();
					
				}else {
					//秒杀未开始
					var nowTime = exposer['nowTime'];
					var startTime = exposer['startTime'];
					var endTime = exposer['endTime'];
					//重新计时
					seckill.countdown(seckillId,nowTime,startTime,endTime);
				}
			}
		});
	},
	
	//计时操作
	countdown : function(seckillId, nowTime, startTime, endTime) {
		var seckillBox = $("#seckill-box");
		if(nowTime > endTime) {
			seckillBox.html("秒杀结束");
		}else if(nowTime < startTime) {
			var killTime = new Date(startTime+1000);
			seckillBox.countdown(killTime, function(event) {
				var format = event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒');
				seckillBox.html(format);
			//倒计时结束
			}).on('finish.countdown',function() {
				//获取秒杀地址
				seckill.handleSeckill(seckillId,seckillBox); 
			}); 
		}else {
			seckill.handleSeckill(seckillId, seckillBox);
		}
  	},
	
	//秒杀详情页逻辑
	detail : {
		//初始化
		init : function(params) {
			//手机认证，计时交互
			//从cookie中获取手机号
			var phone = $.cookie('phone');
			
			//验证手机号
			if(!seckill.validatePhone(phone)) {
				var phoneModal = $("#phoneModal");
				phoneModal.modal({
					show : true,
					backdrop : 'static',
					keyboard : false
				});
				$("#phoneBtn").click(function() {
					var phone = $("#phone").val();
					if(seckill.validatePhone(phone)) {
						$.cookie("phone",phone,{expires:7,path:'/seckill'});
						window.location.reload();
					}else {
						$("#phoneMsg").hide().html("<label class=\"text-danger\">手机号错误！</label>").show(300);
					}
				});
			}
			//手机号认证通过
			var startTime = params['startTime'];
			var endTime = params['endTime'];
			var seckillId = params['seckillId'];
			$.get(seckill.URL.now(),{},function(result) {
				if(result && result['success']) {
					var nowTime = result['data'];
					seckill.countdown(seckillId, nowTime, startTime, endTime);
				}else {
					console.log("result"+result);
				}
				
			});
		}
		
	}
}