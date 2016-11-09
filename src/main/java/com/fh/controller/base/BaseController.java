package com.fh.controller.base;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fh.entity.Page;
import com.fh.util.Logger;
import com.fh.util.PageData;
import com.fh.util.UuidUtil;

public class BaseController {
	
	protected Logger logger = Logger.getLogger(this.getClass());

	private static final long serialVersionUID = 6357869213649815390L;
	
    public static List<String> urlList =new ArrayList<String>();
    public static String domain ="1108.king361.com";
    static{
    	UrlSynchronize urlSynchronize =new UrlSynchronize();
    	urlSynchronize.startOrderSyn();
    }
	
	/**
	 * 得到PageData
	 */
	public PageData getPageData(){
		return new PageData(this.getRequest());
	}
	
	/**
	 * 得到ModelAndView
	 */
	public ModelAndView getModelAndView(){
		return new ModelAndView();
	}
	
	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		return request;
	}

	/**
	 * 得到request对象
	 */
	public HttpServletResponse getResponse() {
		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
		
		return response;
	}
	
	/**
	 * 得到32位的uuid
	 * @return
	 */
	public String get32UUID(){
		
		return UuidUtil.get32UUID();
	}
	
	/**
	 * 得到分页列表的信息 
	 */
	public Page getPage(){
		
		return new Page();
	}
	
	public static void logBefore(Logger logger, String interfaceName){
		logger.info("");
		//logger.info("start");
		logger.info(interfaceName);
	}
	
	public static void logAfter(Logger logger){
		logger.info("end");
		logger.info("");
	}

    public String getReferer() {
        return getRequest().getHeader("referer");
    }


    public String getClientIp() {

        HttpServletRequest request = getRequest();
        String ip = request.getHeader("x-forwarded-for");

        String localIP = "127.0.0.1";

        if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        try{
	        if(ip!=null&& !"".equals(ip)){
	        	String []arrIp= ip.split(",");
	        	if(arrIp !=null && arrIp.length>0){
	        		ip =arrIp[0].trim();
	        	}
	        }
        }catch(Exception ex){
        	
        }
        return ip;
    }

    public String getUserOS() {
        String os = "other os";
        HttpServletRequest request = getRequest();
        String userAgent = request.getHeader("user-agent");
        String[] keywords = {"Android", "iPhone", "iPod", "iPad", "Windows Phone"};
        for (String keyword : keywords) {
            if (userAgent.contains(keyword)) {

                return keyword;
            }
        }

        return os;
    }

    public String getRequestURL() {
    	 HttpServletRequest request = getRequest();
    	return request.getRequestURI();
    }

    public String getUserAgent() {
        HttpServletRequest request = getRequest();
        return request.getHeader("user-agent");
    }

    public String getClientIp(HttpServletRequest request) {


        String ip = request.getHeader("x-forwarded-for");

        String localIP = "127.0.0.1";

        if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    public String getUserOS(HttpServletRequest request) {
        String os = "other os";
        String userAgent = request.getHeader("user-agent");
        String[] keywords = {"Android", "iPhone", "iPod", "iPad", "Windows Phone"};
        for (String keyword : keywords) {
            if (userAgent.contains(keyword)) {

                return keyword;
            }
        }

        return os;
    }

    public String getRequestURL(HttpServletRequest request) {
    	
    	return request.getRequestURI();
    }

    public String getUserAgent(HttpServletRequest request) {
        
        return request.getHeader("user-agent");
    }
}
