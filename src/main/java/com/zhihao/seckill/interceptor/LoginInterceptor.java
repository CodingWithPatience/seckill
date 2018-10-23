/**
 * 
 */
package com.zhihao.seckill.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 登陆拦截器
 * @author zzh
 * 2018年9月30日
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

	 /**
     * 在业务处理器处理请求之前被调用
     * 如果返回false
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * 如果返回true
     *    执行下一个拦截器,直到所有的拦截器都执行完毕
     *    再执行被拦截的Controller
     *    然后进入拦截器链,
     *    从最后一个拦截器往回执行所有的postHandle()
     *    接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 不需要拦截的请求
		String[] noNeedAuth = {
				"login","register","home","error","checkLogin"
		};
		HttpSession session = request.getSession();
		
        // 检查uri是否需要拦截
		if(session.getAttribute("user")==null) {
			// 项目路径
			String contextPath = request.getContextPath();
			// uri路径
			String uri = request.getRequestURI();
			// 去除项目名称部分
			uri = StringUtils.remove(uri, contextPath);
	        
	        for(int i=0; i<noNeedAuth.length; i++) {
				if(uri.contains(noNeedAuth[i])){
					return true;
				}
	        }
	        // 请求需要拦截，用户未登录，重定向到登录页
	        response.sendRedirect(contextPath+"/login");
			return false;
		}
		else {
			// 用户已登录
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
}
