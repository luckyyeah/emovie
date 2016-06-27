package com.fh.controller.system.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.OrderInfo;
import com.fh.entity.Page;
import com.fh.service.videocontent.video.PayService;
import com.fh.util.Const;
import com.fh.util.PageData;
/*
 * 总入口
 */
@Controller
public class LoginController extends BaseController {


	@Resource(name="payService")
	private PayService payService;
	

	
	/**
	 * 请求登录，验证用户
	 */
	@RequestMapping(value="/login")
	public ModelAndView login(HttpServletRequest request,Page page)throws Exception{
		PageData pd = new PageData();
		ModelAndView mv = this.getModelAndView();
		pd = this.getPageData();
		//shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		PageData payInfo = payService.findByOrderNo(pd);	
		if(payInfo!=null){
			OrderInfo orderInfo= new OrderInfo();
			orderInfo.setOrderNo(payInfo.getString("ORDER_NO"));
			orderInfo.setVipType(Const.COMMON_VIP);
			session.setAttribute("orderInfo", orderInfo);
			mv.addObject("loginFlag", 1);
		} else{
			session.setAttribute("orderInfo", null);
		}
		mv.addObject("pd", pd);
		mv.setViewName("home/login_result");
	
		return mv;
	}
	@RequestMapping(value="/logout")
	public ModelAndView logout(HttpServletRequest request,Page page)throws Exception{
		PageData pd = new PageData();
		ModelAndView mv = this.getModelAndView();
		pd = this.getPageData();
		//shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		session.setAttribute("orderInfo", null);
		pd.put("fromUrl", "index");
		mv.addObject("loginFlag", -1);
		mv.addObject("pd", pd);
		mv.setViewName("home/login_result");
	
		return mv;
	}
	
}
