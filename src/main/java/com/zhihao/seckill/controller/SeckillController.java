/**
 * 
 */
package com.zhihao.seckill.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhihao.seckill.dto.Exposer;
import com.zhihao.seckill.dto.SeckillExcution;
import com.zhihao.seckill.dto.SeckillResult;
import com.zhihao.seckill.enums.SeckillStateEnum;
import com.zhihao.seckill.exception.RepeatedSeckillException;
import com.zhihao.seckill.exception.SeckillClosedException;
import com.zhihao.seckill.pojo.Seckill;
import com.zhihao.seckill.service.SeckillService;

/**
 * seckill handler
 * @author zzh
 * 2018年9月30日
 */
@Controller
@RequestMapping("")
public class SeckillController {

	private final Logger logger = LogManager.getLogger(SeckillController.class);
	
	@Autowired
	private SeckillService seckillService;
	
	// 秒杀商品列表
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		List<Seckill> seckills = seckillService.list(0, SeckillService.LIMIT);
		model.addAttribute("seckills", seckills);
		return "fore/listSeckill";
	}

	// 返回秒杀详情页
	@RequestMapping(value="/{seckillId}/detail", method=RequestMethod.GET)
	public String detail(@PathVariable(value="seckillId") Long seckillId, Model model) {
		if(seckillId == null) {
			return "redirect:/list";
		}
		Seckill seckill = seckillService.listById(seckillId);
		if(seckill == null) {
			return "forward:/list";
		}
		model.addAttribute("seckill", seckill);
		return "fore/seckillDetail";
	}
	
	// 暴露秒杀接口
	@RequestMapping(value="/{seckillId}/exposer", method=RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillResult<Exposer> Exposer(@PathVariable(value="seckillId") long seckillId) {
		SeckillResult<Exposer> result;
		try {
			Exposer exposer = seckillService.exposeUrl(seckillId);
			result = new SeckillResult<Exposer>(true, exposer);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = new SeckillResult<Exposer>(false, e.getMessage());
		}
		return result;
		
	}

	// 执行秒杀操作
	@RequestMapping(value="/{seckillId}/{md5}/excution", method=RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillResult<SeckillExcution> excute(@PathVariable(value="seckillId") long seckillId,
			@PathVariable(value="md5") String md5, @CookieValue(value="phone", required=false) Long phone) {
		logger.info("/"+seckillId+"/excution");
		SeckillResult<SeckillExcution> result;
		SeckillExcution seckillExcution;
		try {
			seckillExcution = seckillService.executeSeckillProcedure(seckillId, phone, md5);
			result = new SeckillResult<SeckillExcution>(true, seckillExcution);
			return result;
		} catch (RepeatedSeckillException e) {
			seckillExcution = new SeckillExcution(SeckillStateEnum.REPEAT_KILL);
			result = new SeckillResult<SeckillExcution>(true, seckillExcution);
			return result;
		} catch (SeckillClosedException e1) {
			seckillExcution = new SeckillExcution(SeckillStateEnum.END);
			result = new SeckillResult<SeckillExcution>(true, seckillExcution);
			return result;
		} catch (Exception e2) {
			logger.error(e2.getMessage(), e2);
			seckillExcution = new SeckillExcution(SeckillStateEnum.INNER_ERROR);
			result = new SeckillResult<SeckillExcution>(true, seckillExcution);
			return result;
		}
	}
	
	// 返回服务器系统时间
	@RequestMapping(value="/time/now", method=RequestMethod.GET,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public SeckillResult<Long> nowTime() {
		Date now = new Date();
		return new SeckillResult<Long>(true, now.getTime());
	}
}
