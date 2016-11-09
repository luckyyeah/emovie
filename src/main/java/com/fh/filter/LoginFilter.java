package com.fh.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.util.FileUtil;

/**
 * 登录验证过滤器
 */
public class LoginFilter extends BaseController implements Filter {

	/**
	 * 初始化
	 */
	public void init(FilterConfig fc) throws ServletException {

	}
	
	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String userAgent = request.getHeader("user-agent");
		String uri = request.getRequestURI().toLowerCase();
		//System.out.println("userAgent="+userAgent);
/*		if(!(uri!=null && (uri.indexOf("m_ios")>=0||uri.indexOf("pay")>=0||uri.indexOf("play")>=0))){
		    response.sendRedirect("http://1016html.gam399.com/3008.html?t=233663768");
		    return;
		}*/
		//静止windows访问
//		if(userAgent.toLowerCase().indexOf("windows") > 0){
//			response.sendError(404, "IP has too many request count");
//			return;
//
//		} else {
//			chain.doFilter(req, res); // 调用下一过滤器
//		}
		chain.doFilter(req, res); // 调用下一过滤器
	}

}
