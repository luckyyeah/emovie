package com.fh.resolver;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
/**
 * 
* 类名称：MyExceptionResolver.java
* 类描述： 
* @author FH
* 作者单位： 
* 联系方式：QQ313596790
* @version 1.0
 */
public class MyExceptionResolver implements HandlerExceptionResolver{
   Logger logger = Logger.getLogger(MyExceptionResolver.class);
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		// TODO Auto-generated method stub
		System.out.println("==============异常开始=============");
		System.out.println(request.getRequestURI());
		ex.printStackTrace();
		StringWriter errors = new StringWriter();
		ex.printStackTrace(new PrintWriter(errors));
		logger.error(request.getRequestURI());
		logger.error(errors.toString());
		System.out.println("==============异常结束=============");
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("exception", ex.toString().replaceAll("\n", "<br/>"));
		return mv;
	}

}
