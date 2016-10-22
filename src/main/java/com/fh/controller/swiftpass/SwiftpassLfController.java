package com.fh.controller.swiftpass;

import java.io.PrintWriter;
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
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
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
import com.alibaba.fastjson.JSONArray;
import com.fh.controller.base.BaseController;
import com.fh.controller.main.HomeController;
import com.fh.entity.LfPayReturnData;
import com.fh.entity.OrderInfo;
import com.fh.entity.Page;
import com.fh.service.videocontent.video.ThirdOrderService;
import com.fh.util.CommonUtil;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.PageData;
import com.swiftpass.config.SwiftpassConfigLf;
import com.swiftpass.util.MD5;
import com.swiftpass.util.SignUtils;
import com.swiftpass.util.XmlUtils;

/** 
 * 类名称：HomeController
 * 创建人：yrh 
 * 创建时间：2016-06-23
 */
@Controller
@RequestMapping(value="/thirdpaylf")
public class SwiftpassLfController extends BaseController {
	

	@Resource(name="thirdOrderService")
	private ThirdOrderService thirdOrderService;
	
	
	private static Log paylogger = LogFactory.getLog("paylogger");
	
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/goPay")
	public ModelAndView goPay(Page page){
		logBefore(logger, "goPay");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		String payUrl=  "";
		try{
			pd = this.getPageData();
			SortedMap<String,String> map =new TreeMap<String,String>();
			page.setPd(pd);
			String total_fee = pd.getString("total_fee");
			String channelNo = pd.getString("channelNo");
			String userId = pd.getString("uid");
			if(channelNo==null){
				channelNo="";
			}
			if(total_fee !=null){
				total_fee =String.valueOf((int)(Double.parseDouble(total_fee)*100));
			} else {
				total_fee =SwiftpassConfigLf.total_fee;
			}
			String orderNo =createOrderNo(channelNo);
			//付费金额
			map.put("total_fee", total_fee);
			//订单号
			map.put("order_no", orderNo);
			//map.put("mch_create_ip", this.getClientIp());
			if("2".equals(pd.getString("version"))){
				payUrl= createOrder(map,channelNo,SwiftpassConfigLf.callback_urlv2+"/"+channelNo);
			} else if("3".equals(pd.getString("version"))){
				payUrl= createOrder(map,channelNo,SwiftpassConfigLf.callback_urlv3+"/"+channelNo);
			} else{
				payUrl= createOrder(map,channelNo,SwiftpassConfigLf.callback_url+"/"+channelNo);
			}
			//payUrl = SwiftpassUrlUtils.reqPayUrl(payUrl,this.getUserAgent());
		  OrderInfo orderInfo=new OrderInfo();
		  orderInfo.setOrderNo(orderNo);
		  orderInfo.setUserId(userId);
		  orderInfo.setChannelNo(channelNo);
		  orderInfo.setPayAmt(String.valueOf(Double.parseDouble(total_fee)/100));
		  orderInfo.setPlugin_type(pd.getString("plugin_type"));
		  orderInfo.setVipType(2);
		  saveThirdOrder(orderInfo);			
		  SwiftpassController.mapUserInfo.put(userId, orderInfo);
		  SwiftpassController.orderResult.put(orderNo, 0);//初始状态
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return  new ModelAndView("redirect:" +payUrl);
	}
	/**
	 * 获取播放信息
	 */
	@RequestMapping(value="/getWxPayLink")
	public void getWxPayLink(PrintWriter out){
		logBefore(logger, "checkPayed");

		Map payMap =new HashMap();
		try{
			PageData pd = new PageData();
			String payUrl=  "";
			SortedMap<String,String> map =new TreeMap<String,String>();
			pd = this.getPageData();
			String vipType =pd.getString("vipType");
			String channelNo = pd.getString("channelNo");
			String userId = pd.getString("uid");
			if(channelNo==null){
				channelNo="";
			}
			String total_fee = null;
			Map payInfo = (HashMap)HomeController.mapHomeData.get("payInfo");
			if(payInfo.get(vipType) !=null && !"".equals(payInfo.get(vipType))){
				total_fee =String.valueOf((int)(Double.parseDouble(payInfo.get(vipType).toString())*100));
			} else {
				vipType="0";
				total_fee =SwiftpassConfigLf.total_fee;
			}
			String orderNo =createOrderNo(channelNo);
			//付费金额
			map.put("total_fee", total_fee);
			//订单号
			map.put("order_no", orderNo);
		//	map.put("mch_create_ip", this.getClientIp());
			if("2".equals(pd.getString("version"))){
				payUrl= createOrder(map,channelNo,SwiftpassConfigLf.callback_urlv2+"/"+channelNo);
			} else if("3".equals(pd.getString("version"))){
				payUrl= createOrder(map,channelNo,SwiftpassConfigLf.callback_urlv3+"/"+channelNo);
			} else{
				payUrl= createOrder(map,channelNo,SwiftpassConfigLf.callback_url+"/"+channelNo);
			}
			//payUrl = SwiftpassUrlUtils.reqPayUrl(payUrl,this.getUserAgent());
		  OrderInfo orderInfo=new OrderInfo();
		  orderInfo.setOrderNo(orderNo);
		  orderInfo.setUserId(userId);
		  orderInfo.setChannelNo(channelNo);
		  orderInfo.setPayAmt(String.valueOf(Double.parseDouble(total_fee)/100));
		  orderInfo.setPlugin_type(pd.getString("plugin_type"));
		  orderInfo.setVipType(Integer.parseInt(vipType));
		 // saveThirdOrder(orderInfo);			
		  SwiftpassController.mapUserInfo.put(userId, orderInfo);
		  SwiftpassController.orderResult.put(orderNo, 0);//初始状态
		  
		  payMap.put("out_trade_no", orderNo);
		  payMap.put("info", payUrl);
		//  saveThirdOrder(orderInfo);
		  String jsonData = JSONArray.toJSONString(payMap);
			out.write(jsonData);
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	private String createOrderNo(String channelNo){
		String orderNo ="";
		orderNo =channelNo + DateUtil.getDays()+CommonUtil.getRandomString(0,9,6);
		return orderNo;
	}
	private String createOrder(SortedMap<String,String> map,String channelNo,String return_url  ) throws Exception{
		    String  payUrl = "";
		    String signString = SwiftpassConfigLf.mch_id+SwiftpassConfigLf.app_id+(String)map.get("order_no")+(String)map.get("total_fee");
        String sign = MD5.sign(signString, SwiftpassConfigLf.key, "utf-8");		//生成签名
        map.put("sign", sign);
        String strParam = "?para_id="+SwiftpassConfigLf.mch_id;
        strParam += "&app_id="+SwiftpassConfigLf.app_id;
        strParam += "&notify_url="+SwiftpassConfigLf.notify_url+"/"+channelNo;
        strParam += "&body="+java.net.URLEncoder.encode(SwiftpassConfigLf.body);
        strParam += "&order_no="+(String)map.get("order_no");
        strParam += "&total_fee="+(String)map.get("total_fee");
        strParam += "&attach=123";     
        strParam += "&sign="+sign;     
       // strParam=java.net.URLEncoder.encode(strParam);
       // System.out.println("map: " + map);
        String reqUrl = SwiftpassConfigLf.req_url+strParam;
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        try {
            HttpPost httpPost = new HttpPost(reqUrl);
            StringEntity entityParams = new StringEntity(XmlUtils.parseXML(map),"utf-8");
            httpPost.setHeader("Content-Type", "text/xml;charset=utf-8");
            httpPost.setEntity(entityParams);
            client = HttpClients.createDefault();
            response = client.execute(httpPost);
            if (response != null && response.getEntity() != null) {
		    			payUrl = new String(EntityUtils.toByteArray(response.getEntity()), "utf-8");
		    			LfPayReturnData rfPayReturnData=JSON.parseObject(payUrl, LfPayReturnData.class);
		    			payUrl= rfPayReturnData.getPayurl();
		    			//System.out.println(payUrl);
		    		}
           
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(response != null){
                response.close();
            }
            if(client != null){
                client.close();
            }
        }
       return payUrl;
	}
	/**
	 * 获取支付信息
	 */
	@RequestMapping(value="/returnPayInfo/{CHANNEL_NO}")
	public ModelAndView returnPayInfo(HttpServletRequest req, HttpServletResponse resp,@PathVariable String CHANNEL_NO){

		  try {
	            req.setCharacterEncoding("utf-8");
	            resp.setCharacterEncoding("utf-8");
	            resp.setHeader("Content-type", "text/html;charset=UTF-8");
	            String respString = "fail";
	            String ORDER_NO =	req.getParameter("orderno");
              paylogger.info("通知内容orderno=" + ORDER_NO);
              if(ORDER_NO!=null &&!"".equals(ORDER_NO)){
              		Map<String,String> map = new HashMap<String,String>();
              		String pay_amt = req.getParameter("fee");
              		String transaction_id = req.getParameter("orderno");
              		map.put("out_trade_no", ORDER_NO);
                  map.put("total_fee", pay_amt);
                  map.put("pay_result", "1");
              		map.put("channel_no", CHANNEL_NO);
		            		map.put("transaction_id", transaction_id);
		            		map.put("status", String.valueOf(1));
              		if(CHANNEL_NO.indexOf(Const.IOS_CHANNEL_HREAD)>=0){
              			thirdOrderService.edit(map);
              		} else {
              			thirdOrderService.editAndroid(map);
              		}
              		SwiftpassController.orderResult.put(ORDER_NO, 1);//支付成功
                 
                  paylogger.info(ORDER_NO+ "result_code=1");
              } 
              respString = "ok";
	            resp.getWriter().write(respString);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
					ModelAndView mv = this.getModelAndView();
					PageData pd = new PageData();
		
					pd.put("CHANNEL_NO", CHANNEL_NO);
					mv.addObject("pd", pd);
					mv.setViewName("wapv3/payresult");
					return  mv;
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
    		map.put("plugin_type", orderInfo.getPlugin_type());
    		map.put("vip_type", String.valueOf(orderInfo.getVipType()));
    		if(orderNo.indexOf(Const.IOS_CHANNEL_HREAD)>=0){
					thirdOrderService.saveThirdOrder(map);
    		} else {
    			thirdOrderService.saveAndroidThirdOrder(map);
    		}
    		//SwiftpassController.orderResult.put(out_trade_no, 1);//支付成功
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 获取支付信息
	 */
	@RequestMapping(value="/callbackPay/{CHANNEL_NO}")
	public ModelAndView videoDetail(Page page,@PathVariable String CHANNEL_NO){
		logBefore(logger, "callbackPay");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();

		pd.put("CHANNEL_NO", CHANNEL_NO);
		mv.addObject("pd", pd);
		mv.setViewName("wap/payresult");
		return  mv;
	}
	/**
	 * 获取支付信息
	 */
	@RequestMapping(value="/callbackPayV2/{CHANNEL_NO}")
	public ModelAndView callbackPayV2(Page page,@PathVariable String CHANNEL_NO){
		logBefore(logger, "callbackPay");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();

		pd.put("CHANNEL_NO", CHANNEL_NO);
		pd.put("out_trade_no", "");
		mv.addObject("pd", pd);
		mv.setViewName("wapv2/login_result");
		return  mv;
	}
	/**
	 * 获取支付信息
	 */
	@RequestMapping(value="/callbackPayV3/{CHANNEL_NO}")
	public ModelAndView callbackPayV3(Page page,@PathVariable String CHANNEL_NO){
		logBefore(logger, "callbackPay");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();

		pd.put("CHANNEL_NO", CHANNEL_NO);
		mv.addObject("pd", pd);
		mv.setViewName("wapv3/payresult");
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
