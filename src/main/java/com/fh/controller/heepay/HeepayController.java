package com.fh.controller.heepay;

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

import com.fh.controller.base.BaseController;
import com.fh.controller.swiftpass.SwiftpassController;
import com.fh.entity.OrderInfo;
import com.fh.entity.Page;
import com.fh.service.videocontent.video.ThirdOrderService;
import com.fh.util.CommonUtil;
import com.fh.util.Const;
import com.fh.util.DateUtil;
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
@RequestMapping(value="/thirdpay2")
public class HeepayController extends BaseController {
	

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
			String orderNo = createOrderNo(channelNo);
		  payUrl= createOrder(orderNo,total_fee,channelNo);
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
	private String createOrder(String agent_bill_id,String pay_amt,String channelNo ) throws Exception{
		String  payUrl = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String time = df.format(new Date());

		String version = "1";                   //当前接口版本号

    String user_ip = this.getClientIp().replace(".", "_");		//用户所在客户端的真实ip其中的“.”替换为“_” 。如 127_127_12_12。因为近期我司发现用户在提交数据时，user_ip在网络层被篡改，导致签名错误，所以我们规定使用这种格式。
    String agent_bill_time = time;			//提交单据的时间yyyyMMddHHmmss 如：20100225102000该参数共计14位，当时不满14位时，在后面加0补足14位
		 try{
		    
				//将数据初始化WeiXinPayModel
				WeiXinPayModel model=new WeiXinPayModel();
				model.set_agent_bill_id(agent_bill_id);
				model.set_agent_bill_time(agent_bill_time);
				model.set_agent_id(HeepayConfig.agent_id);
				model.set_goods_name(HeepayConfig.goods_name);
				model.set_goods_note(HeepayConfig.goods_note);
				model.set_goods_num(HeepayConfig.goods_num);
				model.set_is_frame(HeepayConfig.is_frame);
				model.set_notify_url(HeepayConfig.notify_url+"/"+channelNo);
				model.set_pay_amt(pay_amt);
				model.set_pay_type(HeepayConfig.pay_type);
				model.set_remark(HeepayConfig.remark);
				model.set_return_url(HeepayConfig.callback_url+"/"+channelNo);
				model.set_user_ip(user_ip);
				model.set_is_phone(HeepayConfig.is_phone);
				model.set_version(version);
				String sign=WeiXinHelper.signMd5(HeepayConfig.key, model);

				//获取提交地址
				payUrl=WeiXinHelper.GatewaySubmitUrl(sign, model);


			   
			    
		 } catch (Exception e) {
		     e.printStackTrace();
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
	            String respString = "fail";
                String status = req.getParameter("result");
                paylogger.info("通知内容status=：" + status);
                if(status != null && "1".equals(status)){
                	Map<String,String> map = new HashMap<String,String>();
                    String result_code = req.getParameter("result");
                    String out_trade_no = req.getParameter("agent_bill_id");
                    String pay_amt = req.getParameter("pay_amt");
                    map.put("out_trade_no", out_trade_no);
                    map.put("total_fee", pay_amt);
                    map.put("pay_result", result_code);
                		map.put("channel_no", CHANNEL_NO);
                		if(CHANNEL_NO.indexOf(Const.IOS_CHANNEL_HREAD)>=0){
                			thirdOrderService.saveThirdOrder(map);
                		} else {
                			thirdOrderService.saveAndroidThirdOrder(map);
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
