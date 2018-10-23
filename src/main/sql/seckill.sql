-- 创建数据库
CREATE DATABASE seckill;

use seckill;

-- 创建秒杀表
CREATE table seckill(
	seckill_id bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
	name varchar(120) NOT NULL COMMENT '商品名称',
	number int 	NOT NULL COMMENT '商品库存',
	start_time timestamp NOT NULL COMMENT '开始时间',
	end_time timestamp NOT NULL COMMENT '结束时间',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY(seckill_id), 
	key idx_start_time(start_time),
	key idx_end_time(end_time),
	key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表'; 

INSERT INTO seckill(name, number, start_time, end_time)
	values
		('1500元秒杀iPhone 8', 200, '2018-09-28 00:00:00', '2018-09-29 00:00:00'),
		('2500元秒杀iPhone x', 100, '2018-09-28 00:00:00', '2018-09-29 00:00:00'),
		('500元秒杀小米8', 300, '2018-09-28 00:00:00', '2018-09-29 00:00:00'),
		('500元秒杀魅族16', 400, '2018-09-28 00:00:00', '2018-09-29 00:00:00');

-- 秒杀成功表
CREATE table success_killed(
	seckill_id bigint NOT NULL AUTO_INCREMENT COMMENT '商品id',
	user_phone bigint NOT NULL COMMENT '用户手机号',
	state tinyint NOT NULL DEFAULT -1 COMMENT '状态标识：-1:无效，0:成功，1:已付款，2:已发货',
	create_time timestamp NOT NULL COMMENT '创建时间',
	PRIMARY KEY(seckill_id,user_phone), /*联合主键*/
	key idx_create_time(create_time),
	CONSTRAINT fk_user_phone FOREIGN KEY (user_phone) REFERENCES user(phone)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功表';

-- 用户表
CREATE table user(
	user_id bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
	name varchar(50) NOT NULL COMMENT '用户名',
	phone bigint UNIQUE COMMENT '手机号',
	email varchar(50) UNIQUE COMMENT '用户邮箱',
	create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	PRIMARY KEY(user_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

