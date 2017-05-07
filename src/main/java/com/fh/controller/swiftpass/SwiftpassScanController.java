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

import com.alibaba.fastjson.JSONArray;
import com.fh.controller.base.BaseController;
import com.fh.controller.main.HomeController;
import com.fh.entity.OrderInfo;
import com.fh.entity.Page;
import com.fh.service.videocontent.video.ThirdOrderService;
import com.fh.util.CommonUtil;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.PageData;
import com.fh.util.SwiftpassUrlUtils;
import com.fh.util.TwoDimensionCode;
import com.heepay.HeepayConfig;
import com.swiftpass.config.SwiftpassConfig;
import com.swiftpass.util.MD5;
import com.swiftpass.util.SignUtils;
import com.swiftpass.util.XmlUtils;
import com.ylpay.YlpayConfig;

/** 
 * 类名称：HomeController
 * 创建人：yrh 
 * 创建时间：2016-06-23
 */
@Controller
@RequestMapping(value="/thirdpayscan")
public class SwiftpassScanController extends BaseController {
	

	@Resource(name="thirdOrderService")
	private ThirdOrderService thirdOrderService;
	
	
	private static Log paylogger = LogFactory.getLog("paylogger");
	
	public static Map<String,Integer> orderResult =new HashMap<String,Integer>(); //用来存储订单的交易状态(key:订单号，value:状态(0:未支付，1：已支付))  ---- 这里可以根据需要存储在数据库中
	public static Map<String,OrderInfo> mapUserInfo =new HashMap<String,OrderInfo>(); //用来存储订单用户信息
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
				total_fee =SwiftpassConfig.total_fee;
			}
			String orderNo = createOrderNo(channelNo);
			//付费金额
			map.put("total_fee", total_fee);
			//订单号
			map.put("out_trade_no", orderNo);
			map.put("mch_create_ip", this.getClientIp());
			if("2".equals(pd.getString("version"))){
				payUrl= createOrder(map,channelNo,SwiftpassConfig.callback_urlv2+"/"+channelNo);
			} else if("3".equals(pd.getString("version"))){
				payUrl= createOrder(map,channelNo,SwiftpassConfig.callback_urlv3+"/"+channelNo);
			} else{
				payUrl= createOrder(map,channelNo,SwiftpassConfig.callback_url+"/"+channelNo);
			}
			//无法调起支付是返回支付页面
			if("2".equals(pd.getString("version"))){
				if(payUrl==null||"".equals(payUrl)){
					payUrl="/wapv2/checkPay?CHANNEL_NO="+channelNo;
					return  new ModelAndView("redirect:" +payUrl);
				}
			} else if("3".equals(pd.getString("version"))){
				if(payUrl==null||"".equals(payUrl)){
					payUrl="/wapv3/checkPay?CHANNEL_NO="+channelNo;
					return  new ModelAndView("redirect:" +payUrl);
				}
			} else{
				if(payUrl==null||"".equals(payUrl)){
					payUrl="/wapmovie/checkPay?CHANNEL_NO="+channelNo;
					return  new ModelAndView("redirect:" +payUrl);
				}
			}
			if(payUrl!=null && !"".equals(payUrl)){
				payUrl = SwiftpassUrlUtils.reqPayUrl(payUrl,this.getUserAgent());
			}
		  OrderInfo orderInfo=new OrderInfo();
		  orderInfo.setOrderNo(orderNo);
		  orderInfo.setUserId(userId);
		  orderInfo.setChannelNo(channelNo);
		  orderInfo.setPayAmt(String.valueOf(Double.parseDouble(total_fee)/100));
		  orderInfo.setPlugin_type(pd.getString("plugin_type"));
		  orderInfo.setVipType(2);
		  saveThirdOrder(orderInfo);			
		  mapUserInfo.put(userId, orderInfo);
		  orderResult.put(orderNo, 0);//初始状态
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
				total_fee =SwiftpassConfig.total_fee;
			}
			String orderNo = createOrderNo(channelNo);
			//付费金额
			map.put("total_fee", total_fee);
			//订单号
			map.put("out_trade_no", orderNo);
			map.put("mch_create_ip", this.getClientIp());
			if("2".equals(pd.getString("version"))){
				payUrl= createOrder(map,channelNo,SwiftpassConfig.callback_urlv2+"/"+channelNo);
			} else if("3".equals(pd.getString("version"))){
				payUrl= createOrder(map,channelNo,SwiftpassConfig.callback_urlv3+"/"+channelNo);
			} else{
				payUrl= createOrder(map,channelNo,SwiftpassConfig.callback_url+"/"+channelNo);
			}
			if(payUrl!=null && !"".equals(payUrl)){
				payUrl = SwiftpassUrlUtils.reqPayUrl(payUrl,this.getUserAgent());
			}
		  OrderInfo orderInfo=new OrderInfo();
		  orderInfo.setOrderNo(orderNo);
		  orderInfo.setUserId(userId);
		  orderInfo.setChannelNo(channelNo);
		  orderInfo.setPayAmt(String.valueOf(Double.parseDouble(total_fee)/100));
		  orderInfo.setPlugin_type(pd.getString("plugin_type"));
		  orderInfo.setVipType(Integer.parseInt(vipType));
		 // saveThirdOrder(orderInfo);			
		  mapUserInfo.put(userId, orderInfo);
		  orderResult.put(orderNo, 0);//初始状态
		  
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
        map.put("mch_id", SwiftpassConfig.mch_id);
        map.put("notify_url", SwiftpassConfig.notify_url+"/"+channelNo);
        map.put("nonce_str", String.valueOf(new Date().getTime()));
        map.put("callback_url",return_url);
        map.put("body", SwiftpassConfig.body);
        map.put("sign_type", "MD5");
        map.put("charset", "UTF-8");
        map.put("service", "pay.weixin.native");
        map.put("version", "2.0");
        //map.put("mch_create_ip", this.getClientIp());
        map.put("mch_create_ip", "220.184.228.242");
        Map<String,String> params = SignUtils.paraFilter(map);
        StringBuilder buf = new StringBuilder((params.size() +1) * 10);
        SignUtils.buildPayParams(buf,params,false);
        String preStr = buf.toString();
        String sign = MD5.sign(preStr, "&key=" + SwiftpassConfig.key, "utf-8");
        map.put("sign", sign);
        String reqUrl = SwiftpassConfig.req_url;
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        String res = null;
        try {
            HttpPost httpPost = new HttpPost(reqUrl);
            StringEntity entityParams = new StringEntity(XmlUtils.parseXML(map),"utf-8");
            httpPost.setEntity(entityParams);
            client = HttpClients.createDefault();
            response = client.execute(httpPost);
            if(response != null && response.getEntity() != null){
                Map<String,String> resultMap = XmlUtils.toMap(EntityUtils.toByteArray(response.getEntity()), "utf-8");
                res = XmlUtils.toXml(resultMap);
                if(resultMap.containsKey("sign")){
                    if(!SignUtils.checkParam(resultMap, SwiftpassConfig.key)){
                        res = "验证签名不通过";
                    }else{
                        if("0".equals(resultMap.get("status")) && "0".equals(resultMap.get("result_code"))){
                        			payUrl = resultMap.get("pay_info");
                        }
                    }
                } 
            }else{
                res = "操作失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
            res = "系统异常";
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
	public void returnPayInfo(HttpServletRequest req, HttpServletResponse resp,@PathVariable String CHANNEL_NO){

		  try {
	            req.setCharacterEncoding("utf-8");
	            resp.setCharacterEncoding("utf-8");
	            resp.setHeader("Content-type", "text/html;charset=UTF-8");
	            String resString = XmlUtils.parseRequst(req);
	           // System.out.println("通知内容：" + resString);
	           // paylogger.info("通知内容：" + resString);
	            String respString = "fail";
	            if(resString != null && !"".equals(resString)){
	                Map<String,String> map = XmlUtils.toMap(resString.getBytes(), "utf-8");
	                String res = XmlUtils.toXml(map);
	               // System.out.println("通知内容：" + res);
	                if(map.containsKey("sign")){
	                    if(!SignUtils.checkParam(map, SwiftpassConfig.key)){
	                        res = "验证签名不通过";
	                        respString = "fail";
	                    }else{
	                        String status = map.get("status");
	                        if(status != null && "0".equals(status)){
	                            String result_code = map.get("result_code");
	                            String out_trade_no = map.get("out_trade_no");
			                    		map.put("channel_no", CHANNEL_NO);
			                    		map.put("status", String.valueOf(1));
			                    		if(CHANNEL_NO.indexOf(Const.IOS_CHANNEL_HREAD)>=0){
			                    			thirdOrderService.edit(map);
			                    		} else {
			                    			thirdOrderService.editAndroid(map);
			                    		}
	                            if("0".equals(result_code)&& "0".equals(map.get("pay_result"))){
	                            	orderResult.put(out_trade_no, 1);//支付成功
	                            }
	                            paylogger.info(out_trade_no+ "result_code="+result_code);
	                        } 
	                        respString = "success";
	                    }
	                }
	            }
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
	public static void main(String[] args) {
		String imgPath = "C:\\Users\\apple\\Desktop\\sm.jpg";
		/*String encoderContent = "Hello 大大、小小,welcome to QRCode!"
				+ "\nMyblog [ http://sjsky.iteye.com ]"
				+ "\nEMail [ sjsky007@gmail.com ]";*/
		
		String encoderContent = "http://www.baidu.com";
		TwoDimensionCode handler = new TwoDimensionCode();
		handler.encoderQRCode(encoderContent, imgPath, "png");
		// try {
		// OutputStream output = new FileOutputStream(imgPath);
		// handler.encoderQRCode(content, output);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		System.out.println("========encoder success");

		//String decoderContent = handler.decoderQRCode(imgPath);
		System.out.println("解析结果如下：");
		//System.out.println(decoderContent);
		System.out.println("========decoder success!!!");
	}
}
