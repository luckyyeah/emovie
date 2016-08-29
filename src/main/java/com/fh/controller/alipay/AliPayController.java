package com.fh.controller.alipay;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import org.dom4j.DocumentException;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipaySubmit;
import com.fh.controller.base.BaseController;
import com.fh.controller.main.HomeController;
import com.fh.controller.swiftpass.SwiftpassController;
import com.fh.entity.OrderInfo;
import com.fh.entity.Page;
import com.fh.service.videocontent.video.ThirdOrderService;
import com.fh.util.CommonUtil;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.MD5;
import com.fh.util.PageData;
import com.heepay.HeepayConfig;
import com.heepay.WeiXinHelper;
import com.heepay.WeiXinPayModel;

/** 
 * 类名称：HomeController
 * 创建人：yrh 
 * 创建时间：2016-06-23
 */
@Controller
@RequestMapping(value="/alipay")
public class AliPayController extends BaseController {
	

	@Resource(name="thirdOrderService")
	private ThirdOrderService thirdOrderService;
	
	
	private static Log paylogger = LogFactory.getLog("paylogger");
	
	public static Map<String,Integer> orderResult =new HashMap<String,Integer>(); //用来存储订单的交易状态(key:订单号，value:状态(0:未支付，1：已支付))  ---- 这里可以根据需要存储在数据库中
	public static Map<String,OrderInfo> mapUserInfo =new HashMap<String,OrderInfo>(); //用来存储订单用户信息
	/**
	 * 获取播放信息
	 */
	@RequestMapping(value="/getPay")
	public ModelAndView getPay(PrintWriter out){
		logBefore(logger, "getPay");
		ModelAndView mv = this.getModelAndView();
		Map payMap =new HashMap();
		try{
			PageData pd = new PageData();
			String payUrl=  "";

			pd = this.getPageData();
			SortedMap<String,String> map =new TreeMap<String,String>();
			String vipType =pd.getString("vipType");
			String channelNo = pd.getString("channelNo");
			String userId = pd.getString("uid");
			if(channelNo==null){
				channelNo="";
			}
			String total_fee =pd.getString("total_fee");
			if(total_fee !=null){
				total_fee =String.valueOf((int)(Double.parseDouble(total_fee)));
			} else {
				vipType="0";
				total_fee =HeepayConfig.total_fee;
			}
			String orderNo = createOrderNo(channelNo);
		  payUrl= createOrder(orderNo,total_fee,channelNo,AlipayConfig.return_url+"/"+channelNo);
		  OrderInfo orderInfo=new OrderInfo();
		  orderInfo.setOrderNo(orderNo);
		  orderInfo.setUserId(userId);
		  orderInfo.setChannelNo(channelNo);
		  orderInfo.setPayAmt(total_fee);
		  orderInfo.setPlugin_type(pd.getString("plugin_type"));
		  orderInfo.setVipType(Integer.parseInt(vipType));
		  saveThirdOrder(orderInfo);
		  SwiftpassController.mapUserInfo.put(userId, orderInfo);
		  SwiftpassController.orderResult.put(orderNo, 0);//初始状态
		  mv.addObject("payUrl", payUrl);

			
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		mv.setViewName("wapv2/alipay");
		
		return mv;
	}
	/**
	 * 获取播放信息
	 */
	@RequestMapping(value="/getAliPayLink")
	public ModelAndView getWxPayLink(PrintWriter out){
		logBefore(logger, "getAliPayLink");
		ModelAndView mv = this.getModelAndView();
		Map payMap =new HashMap();
		try{
			PageData pd = new PageData();
			String payUrl=  "";

			pd = this.getPageData();
			SortedMap<String,String> map =new TreeMap<String,String>();
			String vipType =pd.getString("vipType");
			String channelNo = pd.getString("channelNo");
			String userId = pd.getString("uid");
			if(channelNo==null){
				channelNo="";
			}
			String total_fee = null;
			Map payInfo = (HashMap)HomeController.mapHomeData.get("payInfo");
			if(payInfo.get(vipType) !=null && !"".equals(payInfo.get(vipType))){
				total_fee =String.valueOf(Double.parseDouble(payInfo.get(vipType).toString()));
			} else {
				vipType="0";
				total_fee =HeepayConfig.total_fee;
			}
			String orderNo = createOrderNo(channelNo);
		  payUrl= createOrder(orderNo,total_fee,channelNo,AlipayConfig.return_urlv2+"/"+channelNo);
		  OrderInfo orderInfo=new OrderInfo();
		  orderInfo.setOrderNo(orderNo);
		  orderInfo.setUserId(userId);
		  orderInfo.setChannelNo(channelNo);
		  orderInfo.setPayAmt(total_fee);
		  orderInfo.setPlugin_type(pd.getString("plugin_type"));
		  orderInfo.setVipType(Integer.parseInt(vipType));
		  saveThirdOrder(orderInfo);
		  SwiftpassController.mapUserInfo.put(userId, orderInfo);
		  SwiftpassController.orderResult.put(orderNo, 0);//初始状态
		  mv.addObject("payUrl", payUrl);

			
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		mv.setViewName("wapv2/alipay");
		
		return mv;
	}

	private String createOrderNo(String channelNo){
		String orderNo ="";
		orderNo =channelNo + DateUtil.getDays()+CommonUtil.getRandomString(0,9,6);
		return orderNo;
	}
	private String createOrder(String agent_bill_id,String pay_amt,String channelNo,String return_url ) throws Exception{
		String  payUrl = "";
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", AlipayConfig.service);
    sParaTemp.put("partner", AlipayConfig.partner);
    sParaTemp.put("seller_id", AlipayConfig.seller_id);
    sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", AlipayConfig.payment_type);
		sParaTemp.put("notify_url", AlipayConfig.notify_url+"/"+channelNo);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("out_trade_no", agent_bill_id);
		sParaTemp.put("subject", AlipayConfig.subject);
		sParaTemp.put("total_fee", pay_amt);
		sParaTemp.put("show_url", "");
		sParaTemp.put("app_pay","Y");//启用此参数可唤起钱包APP支付。
		sParaTemp.put("body", "");
		//其他业务参数根据在线开发文档，添加参数.文档地址:https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.2Z6TSk&treeId=60&articleId=103693&docType=1
        //如sParaTemp.put("参数名","参数值");

		
		//建立请求
			payUrl = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
       return payUrl;
	}
	/**
	 * 获取支付信息
	 */
	@RequestMapping(value="/returnPayInfo/{CHANNEL_NO}")
	public void returnPayInfo(HttpServletRequest req, HttpServletResponse resp,@PathVariable String CHANNEL_NO){

		  try {
	            req.setCharacterEncoding("utf-8");
	            resp.setCharacterEncoding("utf-8");
	            resp.setHeader("Content-type", "text/html;charset=UTF-8");
	            String respString = "fail";
                String status = req.getParameter("trade_status");
                paylogger.info("通知内容status=：" + status);
                if(status != null && ("TRADE_FINISHED".equals(status)||"TRADE_SUCCESS".equals(status))){
                	Map<String,String> map = new HashMap<String,String>();
                    String result_code = req.getParameter("result");
                    //商户系统内部的定单号 
                    String out_trade_no = req.getParameter("out_trade_no");
                    //汇付宝交易号(订单号) 
                    String transaction_id = req.getParameter("trade_no");
                    
                    String pay_amt = req.getParameter("pay_amt");
                    map.put("out_trade_no", out_trade_no);
                    map.put("total_fee", pay_amt);
                    map.put("pay_result", result_code);
                		map.put("channel_no", CHANNEL_NO);
                		map.put("transaction_id", transaction_id);
                		map.put("status", String.valueOf(1));
                		if(CHANNEL_NO.indexOf(Const.IOS_CHANNEL_HREAD)>=0){
                			thirdOrderService.edit(map);
                		} else {
                			thirdOrderService.editAndroid(map);
                		}
                		SwiftpassController.orderResult.put(out_trade_no, 1);//支付成功
                   
                    paylogger.info(out_trade_no+ "result_code="+result_code);
                } 
                respString = "success";
	            resp.getWriter().write(respString);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
	}
	public  void saveThirdOrder(OrderInfo orderInfo){
		try {
    	Map<String,String> map = new HashMap<String,String>();
        String result_code = "1";
        String out_trade_no = orderInfo.getOrderNo();
        String pay_amt = orderInfo.getPayAmt();
        String orderNo=orderInfo.getOrderNo();
        map.put("out_trade_no", out_trade_no);
        map.put("total_fee", pay_amt);
        map.put("pay_result", result_code);
    		map.put("channel_no", orderInfo.getChannelNo());
    		map.put("status", "0");
    		map.put("vip_type", String.valueOf(orderInfo.getVipType()));
    		if(orderNo.indexOf(Const.IOS_CHANNEL_HREAD)>=0){
					thirdOrderService.saveThirdOrder(map);
    		} else {
    			thirdOrderService.saveAndroidThirdOrder(map);
    		}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 获取支付信息
	 */
	@RequestMapping(value="/callbackPay/{CHANNEL_NO}")
	public ModelAndView callbackPay(HttpServletRequest req,Page page,@PathVariable String CHANNEL_NO){
		paylogger.info("callbackPay");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
    String out_trade_no = req.getParameter("out_trade_no");
		pd.put("CHANNEL_NO", CHANNEL_NO);
		pd.put("out_trade_no", out_trade_no);
		mv.addObject("pd", pd);
		mv.setViewName("wap/payresult");
		return  mv;
	}
	/**
	 * 获取支付信息
	 */
	@RequestMapping(value="/callbackPayV2/{CHANNEL_NO}")
	public ModelAndView callbackPayV2(HttpServletRequest req,Page page,@PathVariable String CHANNEL_NO){
		paylogger.info("callbackPay");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
    String out_trade_no = req.getParameter("out_trade_no");
		pd.put("CHANNEL_NO", CHANNEL_NO);
		pd.put("out_trade_no", out_trade_no);
		mv.addObject("pd", pd);
		mv.setViewName("wapv2/login_result");
		return  mv;
	}
	public static int checkOrderPayed(String orderNo){
		String signString = "version=1&agent_id="+HeepayConfig.agent_id+"&agent_bill_id="+orderNo+"&key="+HeepayConfig.key;
		String param = signString+"&sign="+MD5.md5(signString);
	
		Map<String,String> resultMap = null;
		  try {
			  //result	必填	支付结果 1=成功
				String returnXml= CommonUtil.doGet(Const.HEE_PAY_ORDER_QUERY_URL+"?"+param);
			  if(returnXml!=null && returnXml.indexOf("result=1")>0){
				  SwiftpassController.orderResult.put(orderNo, 1);//支付成功
				  return 1;
			  }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
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
	public static void main(String[] args) throws UnsupportedEncodingException, DocumentException {
/*		getStringOfStr("m_ios290");
		System.out.println(getRandomString(0,9,10));*/
		checkOrderPayed("m_ios500120160811006417");
	}
}
