package com.fh.controller.ylpay;

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

import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
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
import com.fh.entity.YlPayReturnData;
import com.fh.service.videocontent.video.ThirdOrderService;
import com.fh.util.CommonUtil;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.MD5;
import com.fh.util.PageData;
import com.heepay.HeepayConfig;
import com.heepay.Md5Tools;
import com.heepay.WeiXinPayModel;
import com.ylpay.YlpayConfig;

/** 
 * 类名称：HomeController
 * 创建人：yrh 
 * 创建时间：2016-06-23
 */
@Controller
@RequestMapping(value="/ylpay")
public class YLpayController extends BaseController {
	

	@Resource(name="thirdOrderService")
	private ThirdOrderService thirdOrderService;
	
	
	private static Log paylogger = LogFactory.getLog("paylogger");
	
	public static Map<String,Integer> orderResult =new HashMap<String,Integer>(); //用来存储订单的交易状态(key:订单号，value:状态(0:未支付，1：已支付))  ---- 这里可以根据需要存储在数据库中
	public static Map<String,OrderInfo> mapUserInfo =new HashMap<String,OrderInfo>(); //用来存储订单用户信息
	public static  String pay_amt = "48";
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
			String total_fee =pd.getString("total_fee");
			String channelNo = pd.getString("channelNo");
			String userId = pd.getString("uid");
			if(channelNo==null){
				channelNo="";
			}
			if(total_fee !=null){
				total_fee =String.valueOf((int)(Double.parseDouble(total_fee)));
			} else {
				total_fee =HeepayConfig.total_fee;
			}
			YLpayController.pay_amt= total_fee;
			String orderNo = createOrderNo(channelNo);
		  payUrl= createOrder(orderNo,total_fee,channelNo);
		  OrderInfo orderInfo=new OrderInfo();
		  orderInfo.setOrderNo(orderNo);
		  orderInfo.setUserId(userId);
		  orderInfo.setChannelNo(channelNo);
		  orderInfo.setPayAmt(total_fee);
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
	private String createOrder(String agent_bill_id,String pay_amt,String channelNo ) throws Exception{
		String  payUrl = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String time = df.format(new Date());

		String version = "1";                   //当前接口版本号

    String user_ip = this.getClientIp().replace(".", "_");		//用户所在客户端的真实ip其中的“.”替换为“_” 。如 127_127_12_12。因为近期我司发现用户在提交数据时，user_ip在网络层被篡改，导致签名错误，所以我们规定使用这种格式。
    String agent_bill_time = time;			//提交单据的时间yyyyMMddHHmmss 如：20100225102000该参数共计14位，当时不满14位时，在后面加0补足14位
		 try{
		    
				//将数据初始化WeiXinPayModel
				StringBuilder _sbString=new StringBuilder();
				_sbString.append("Buy")
				.append(YlpayConfig.agent_id)
				.append(agent_bill_id)
				.append(pay_amt)
				.append("CNY")
				.append("0")
				.append("0")
				.append(YlpayConfig.goods_name)
				.append(YlpayConfig.notify_url+"/"+channelNo+"/"+agent_bill_id)
				.append("0")
				.append("0")
				.append("zsyh")
				.append("1")
				.append(YlpayConfig.key);
        String sign=Md5Tools.MD5(_sbString.toString()).toLowerCase();
        Part[] parts = {  
        		 new StringPart("p0_Cmd", "Buy"),
        		 new StringPart("p1_MerId", YlpayConfig.agent_id),
        		 new StringPart("p2_Order", agent_bill_id),
        		 new StringPart("p3_Amt",pay_amt),
        		 new StringPart("p4_Cur", "CNY"),
        		 new StringPart("p5_Pid", "0"),
        		 new StringPart("p6_Pcat", "0"),
        		 new StringPart("p7_Pdesc", YlpayConfig.goods_name),
        		 new StringPart("p8_Url", YlpayConfig.notify_url+"/"+channelNo+"/"+agent_bill_id),
        		 new StringPart("p9_SAF", "0"),
        		 new StringPart("pa_MP", "0"),
        		 new StringPart("pd_FrpId", "zsyh"),
        		 new StringPart("pr_NeedResponse", "1"),
        		 new StringPart("Sjt_UserName", "1"),
        		 new StringPart("Sjt_Paytype", YlpayConfig.pay_type),
        		 new StringPart("hmac", sign)
           }; 
				

				//获取提交地址
       String  acceptjson=CommonUtil.doPost(YlpayConfig.req_url, parts);
				YlPayReturnData ylPayReturnData=  JSON.parseObject(acceptjson, YlPayReturnData.class);
				payUrl = ylPayReturnData.getMessage();
			   
			    
		 } catch (Exception e) {
		     e.printStackTrace();
		 }
       return payUrl;
	}
	/**
	 * 获取支付信息
	 */
	@RequestMapping(value="/returnPayInfo/{CHANNEL_NO}/{ORDER_NO}")
	public ModelAndView returnPayInfo(HttpServletRequest req, HttpServletResponse resp,@PathVariable String CHANNEL_NO,@PathVariable String ORDER_NO){

		  try {
	            req.setCharacterEncoding("utf-8");
	            resp.setCharacterEncoding("utf-8");
	            resp.setHeader("Content-type", "text/html;charset=UTF-8");
	            String respString = "fail";
	            String pay_result =	req.getParameter("Sjt_Return");
                paylogger.info("通知内容status=：" + pay_result);
                if("1".equals(pay_result)){
                		Map<String,String> map = new HashMap<String,String>();
                		String pay_amt = req.getParameter("Sjt_factMoney");
                    map.put("out_trade_no", ORDER_NO);
                    map.put("total_fee", pay_amt);
                    map.put("pay_result", "1");
                		map.put("channel_no", CHANNEL_NO);
                		if(CHANNEL_NO.indexOf(Const.IOS_CHANNEL_HREAD)>=0){
                			thirdOrderService.saveThirdOrder(map);
                		} else {
                			thirdOrderService.saveAndroidThirdOrder(map);
                		}
                		SwiftpassController.orderResult.put(ORDER_NO, 1);//支付成功
                   
                    paylogger.info(ORDER_NO+ "result_code=1");
                } 
                respString = "OK";
	            resp.getWriter().write(respString);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
					ModelAndView mv = this.getModelAndView();
					PageData pd = new PageData();
		
					pd.put("CHANNEL_NO", CHANNEL_NO);
					mv.addObject("pd", pd);
					mv.setViewName("wap/payresult");
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
    		map.put("channel_no", orderInfo.getOrderNo());
    		if(orderNo.indexOf(Const.IOS_CHANNEL_HREAD)>=0){
					thirdOrderService.saveThirdOrder(map);
    		} else {
    			thirdOrderService.saveAndroidThirdOrder(map);
    		}
    		SwiftpassController.orderResult.put(out_trade_no, 1);//支付成功
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
		paylogger.info("callbackPay");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();

		pd.put("CHANNEL_NO", CHANNEL_NO);
		mv.addObject("pd", pd);
		mv.setViewName("wap/payresult");
		return  mv;
	}

	public static int checkOrderPayed(String orderNo){

		String param = "Sjt_TransID="+orderNo;
	
		Map<String,String> resultMap = null;
		  try {
		        Part[] parts = {  
		        		 new StringPart("Sjt_TransID", orderNo)
		           }; 
			  String  acceptjson=CommonUtil.doPost(Const.YL_PAY_ORDER_QUERY_URL, parts);
			  //result	必填	支付结果 1=成功
				//String acceptjson= CommonUtil.doGet(Const.YL_PAY_ORDER_QUERY_URL+"?"+param);
				YlPayReturnData ylPayReturnData=  JSON.parseObject(acceptjson, YlPayReturnData.class);
			  if(ylPayReturnData.getStatus()!=null && "1".equals(ylPayReturnData.getStatus())){
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
	public static void main(String[] args) throws Exception {
/*		getStringOfStr("m_ios290");
		System.out.println(getRandomString(0,9,10));*/
		YLpayController ylpayController =new YLpayController();
		ylpayController.createOrder("test001","2","m_ios200");
	//	checkOrderPayed("m_ios500120160811006417");
	}
}
