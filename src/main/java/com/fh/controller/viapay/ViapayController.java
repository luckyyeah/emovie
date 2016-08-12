package com.fh.controller.viapay;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.fh.controller.base.BaseController;
import com.fh.controller.swiftpass.SwiftpassController;
import com.fh.entity.OrderInfo;
import com.fh.entity.Page;
import com.fh.entity.ViaPayData;
import com.fh.entity.ViaPayReturnData;
import com.fh.service.videocontent.video.ThirdOrderService;
import com.fh.util.CommonUtil;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.PageData;
import com.heepay.HeepayConfig;
import com.swiftpass.util.XmlUtils;
import com.viapay.ViaPayUtils;

/** 
 * 类名称：HomeController
 * 创建人：yrh 
 * 创建时间：2016-06-23
 */
@Controller
@RequestMapping(value="/viapay")
public class ViapayController extends BaseController {
	

	@Resource(name="thirdOrderService")
	private ThirdOrderService thirdOrderService;
	
	
	private static Log paylogger = LogFactory.getLog("paylogger");
	
	public static Map<String,Integer> orderResult =new HashMap<String,Integer>(); //用来存储订单的交易状态(key:订单号，value:状态(0:未支付，1：已支付))  ---- 这里可以根据需要存储在数据库中
	public static Map<String,OrderInfo> mapUserInfo =new HashMap<String,OrderInfo>(); //用来存储订单用户信息
	/**
	 * 列表
	 */
	@RequestMapping(value="/goPay")
	public ModelAndView goPay(HttpServletResponse response,Page page){
		logBefore(logger, "goPay");
		response.setCharacterEncoding("UTF-8");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		String payUrl=  "";
		try{
			pd = this.getPageData();
			SortedMap<String,String> map =new TreeMap<String,String>();
			page.setPd(pd);
			String total_fee =pd.getString("total_fee");
			String channelNo = pd.getString("channelNo");
			String userId = pd.getString("uid");
			if(channelNo==null){
				channelNo="";
			}
			if(total_fee !=null){
				total_fee =String.valueOf((int)(Double.parseDouble(total_fee)*100));
			} else {
				total_fee =HeepayConfig.total_fee;
			}
			String orderNo = createOrderNo(channelNo);
  	 String strURL =ViaPayUtils.getPayUrl(total_fee,orderNo,"123","123",channelNo);
  	 String acceptjson =CommonUtil.requestURL(strURL);
     ViaPayData appUserData=  JSON.parseObject(acceptjson, ViaPayData.class);
     payUrl = appUserData.getPayParam();
//      payUrl =java.net.URLEncoder.encode(payUrl,"utf-8"); 
		  OrderInfo orderInfo=new OrderInfo();
		  orderInfo.setOrderNo(orderNo);
		  orderInfo.setUserId(userId);
		  SwiftpassController.mapUserInfo.put(userId, orderInfo);
		  SwiftpassController.orderResult.put(orderNo, 0);//初始状态
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return  new ModelAndView("redirect:" +payUrl);
	}
	private String createOrderNo(String channelNo){
		String orderNo ="";
		orderNo =channelNo + DateUtil.getDays()+CommonUtil.getRandomString(0,9,6);
		return orderNo;
	}
	/**
	 * 获取支付信息
	 */
	@RequestMapping(value="/returnPayInfo/{CHANNEL_NO}/{orderNo}/{money}")
	public ModelAndView returnPayInfo(HttpServletRequest req, HttpServletResponse resp,@PathVariable String CHANNEL_NO,@PathVariable String orderNo,@PathVariable String money){

		  try {
	            req.setCharacterEncoding("utf-8");
	            resp.setCharacterEncoding("utf-8");
	            resp.setHeader("Content-type", "text/html;charset=UTF-8");
	            String resString = XmlUtils.parseRequst(req);
                String status = req.getParameter("result");
                paylogger.info("通知内容status=：" + status);
                if(status != null && "0".equals(status)){
                	Map<String,String> map = new HashMap<String,String>();
                    String result_code = req.getParameter("result");
                    String out_trade_no = orderNo;
                    String pay_amt = money;
                    String out_transaction_id = req.getParameter("orderId");//req.getParameter("orderNo");
                    if(pay_amt!=null){
                    	pay_amt = String.valueOf(Integer.parseInt(pay_amt)/100);
                    }
                    map.put("out_trade_no", out_trade_no);
                    map.put("total_fee", pay_amt);
                    map.put("pay_result", result_code);
                		map.put("channel_no", CHANNEL_NO);
                		map.put("out_transaction_id", out_transaction_id);
                		if(CHANNEL_NO.indexOf(Const.IOS_CHANNEL_HREAD)>=0){
                			thirdOrderService.saveThirdOrder(map);
                		} else {
                			thirdOrderService.saveAndroidThirdOrder(map);
                		}
                		SwiftpassController.orderResult.put(out_trade_no, 1);//支付成功
                   
                    paylogger.info(out_trade_no+ "result_code="+result_code);
                } 
       
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
				ModelAndView mv = this.getModelAndView();
				PageData pd = new PageData();
				pd =this.getPageData();
				pd.put("CHANNEL_NO", CHANNEL_NO);
				mv.addObject("pd", pd);
				mv.setViewName("wap/payresult");
				return  mv;
	}
	/**
	 * 获取支付信息
	 */
	@RequestMapping(value="/callbackPay/{CHANNEL_NO}")
	public ModelAndView videoDetail(Page page,@PathVariable String CHANNEL_NO){
		paylogger.info("callbackPay");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();

		pd.put("CHANNEL_NO", CHANNEL_NO);
		mv.addObject("pd", pd);
		mv.setViewName("wap/payresult");
		return  mv;
	}
	/* ===============================权限================================== */
	public Map<String, String> getHC(){
		Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
		Session session = currentUser.getSession();
		return (Map<String, String>)session.getAttribute(Const.SESSION_QX);
	}
	/* ===============================权限================================== */
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
