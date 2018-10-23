-- 秒杀执行存储过程
DELIMITER $$ --修改换行符

-- row_count()返回上一条sql语句的影响行数
-- row_count: 0:未修改数据；<0:sql出错/未执行sql；>0:表示修改的行数
CREATE PROCEDURE seckill.excute_seckill
	(in v_seckill_id bigint, in v_phone bigint,
		in v_kill_time timestamp, out r_result int)
	BEGIN
		DECLARE insert_count int DEFAULT 0;
		START TRANSACTION;
		insert ignore into success_killed(
			seckill_id,user_phone,state,create_time)
			value(v_seckill_id,v_phone,0,v_kill_time);
		select row_count() into insert_count;
		IF(insert_count = 0) THEN
			ROLLBACK;
			set r_result = -1;
		ELSEIF(insert_count < 0) THEN
			ROLLBACK;
			set r_result = -2;
		ELSE
			update seckill
			set number = number-1
			where seckill_id = v_seckill_id
				and end_time > v_kill_time
				and start_time < v_kill_time
				and number > 0;
			select row_count() into insert_count;
			IF(insert_count = 0) THEN
				ROLLBACK;
				set r_result = 0;
			ELSEIF(insert_count < 0) THEN
				ROLLBACK;
				set r_result = -2;
			ELSE
				COMMIT;
				set r_result = 1;
			END IF;
		END IF; 
	END;
$$
-- 存储过程定义结束

DELIMITER ; -- 将换行符改回分号

set @r_result=-3;

-- 执行秒杀存储过程
call excute_seckill(1003,14230763659,now(),@r_result);

-- 获取结果
select @r_result;

