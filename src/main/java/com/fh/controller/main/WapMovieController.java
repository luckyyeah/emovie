package com.fh.controller.main;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.fh.controller.base.BaseController;
import com.fh.controller.heepay.HeepayController;
import com.fh.controller.swiftpass.SwiftpassController;
import com.fh.controller.ylpay.YLpayController;
import com.fh.entity.OrderInfo;
import com.fh.entity.Page;
import com.fh.entity.PayData;
import com.fh.enums.ColumnDataTypeEnum;
import com.fh.enums.PlanTypeEnum;
import com.fh.enums.VideoDataTypeEnum;
import com.fh.service.videocontent.column.ColumnService;
import com.fh.service.videocontent.pay.PayPluginService;
import com.fh.service.videocontent.plan.PlanService;
import com.fh.service.videocontent.tab.TabService;
import com.fh.service.videocontent.video.PayService;
import com.fh.service.videocontent.video.ThirdOrderService;
import com.fh.service.videocontent.video.VideoService;
import com.fh.util.Const;
import com.fh.util.PageData;
import com.fh.util.Tools;
/** 
 * 类名称：HomeController
 * 创建人：FH 
 * 创建时间：2015-03-21
 */
@Controller
@RequestMapping(value="/wapmovie")
public class WapMovieController extends BaseController {
	

	@Resource(name="planService")
	private PlanService planService;
	
	@Resource(name="tabService")
	private TabService tabService;
	
	@Resource(name="columnService")
	private ColumnService columnService;
	
	@Resource(name="videoService")
	private VideoService videoService;
	
	@Resource(name="payService")
	private PayService payService;
	
	
	@Resource(name="thirdOrderService")
	private ThirdOrderService thirdOrderService;	 	 

	
	private static Log paylogger = LogFactory.getLog("paylogger");
	
	public static List<PageData> tryVideoDataList =null;
	
	public static Map mapColumnData =new HashMap();
	/**
	 * 视频列表
	 */
	@RequestMapping(value="/listColumnVideo/{CHANNEL_NO}/{COLUMN_ID}")
	public ModelAndView listColumnVideo(Page page,@PathVariable("COLUMN_ID") String COLUMN_ID,@PathVariable String CHANNEL_NO,@RequestParam(value="PAGE_NO",required=false) String PAGE_NO,@RequestParam(required=false) String COLUMN_NO){
		//logBefore(logger, "startindex");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();

		try{
			//shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();  
			Session session = currentUser.getSession();
			session.setAttribute("CHANNEL_NO", CHANNEL_NO);
			List<PageData>  bannerDataList =new ArrayList<PageData>();
			
			List<PageData>  videoDataList =new ArrayList<PageData>();
			pd = this.getPageData();
			pd.put("CHANNEL_NO", CHANNEL_NO);

			pd.put("PAGE_SIZE", Const.PAGE_SZIE);
			if(PAGE_NO !=null){
				pd.put("PAGE_FROM", (Integer.parseInt(PAGE_NO)-1)*Const.PAGE_SZIE);
			} else {
				pd.put("PAGE_FROM", 0);
				PAGE_NO = "1";
			}
			//取得视频信息
			pd.put("COLUMN_ID", COLUMN_ID);
			       
/*			PageData pageCntObj = videoService.getVideoCnt(pd);
			int pageCnt = 0;
			if(pageCntObj !=null && pageCntObj.get("VIDEO_CNT")!=null){
				double videoCnt=(Long)pageCntObj.get("VIDEO_CNT");
				pageCnt = (int) Math.ceil(videoCnt/Const.PAGE_SZIE);
			}*/
			PageData columnData = null; 
			List<PageData>  videoList = null;
			if(mapColumnData.get(COLUMN_ID)==null){
				columnData = columnService.findById(pd);
				 mapColumnData.put(COLUMN_ID+"column",videoList);
			  videoList = videoService.listVideos(pd);
			  mapColumnData.put(COLUMN_ID,videoList);
			  
			} else {
				columnData = (PageData)mapColumnData.get(COLUMN_ID+"column");
				videoList = (List<PageData> )mapColumnData.get(COLUMN_ID);
			}
			for(PageData videoData:videoList){
				
				if(ColumnDataTypeEnum.BannerType.getKey()==(Integer)videoData.get("DATA_TYPE")){
					bannerDataList.add(videoData);
				}
				if(ColumnDataTypeEnum.VideoDataType.getKey()==(Integer)videoData.get("DATA_TYPE")){
					videoDataList.add(videoData);
				}
			}
			page.setPd(pd);
/*			List <String> pageNoList = new ArrayList<String>();
			for(int i=1;i<=pageCnt;i++){
				pageNoList.add(String.valueOf(i));
			}*/
			pd.put("COLUMN_NO", COLUMN_NO);
			mv.setViewName("wap/index");
			mv.addObject("bannerDataList", bannerDataList);
			mv.addObject("columnDataList", WapHomeController.mapHomeData.get("columnDataList"));
			mv.addObject("columnData", columnData);
			mv.addObject("videoDataList", videoDataList);
		//	mv.addObject("pageNoList", pageNoList);
			mv.addObject("PAGE_NO",Integer.parseInt(PAGE_NO));
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}	
	/**
	 * 视频列表
	 */
	@RequestMapping(value="/videoDetail/{COLUMN_ID}/{VIDEO_ID}")
	public ModelAndView videoDetail(Page page,@PathVariable("COLUMN_ID") String COLUMN_ID,@PathVariable("VIDEO_ID") String VIDEO_ID){
		//logBefore(logger, "startindex");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		List<PageData>  columnDataList =new ArrayList<PageData>();
		//List columnvideoList =new ArrayList();
		Map mapColumnvideoList =new HashMap();
		List<PageData>  bannerDataList =new ArrayList<PageData>();
		try{
			pd = this.getPageData();
			String showType="";

			//取得视频信息
			pd.put("COLUMN_ID", COLUMN_ID);
			List<PageData>  recommenVideoDataList = videoService.listRecommendVideos(pd);
			page.setPd(pd);
			pd.put("VIDEO_ID", VIDEO_ID);
			PageData videoData = videoService.findById(pd);
			PageData columnData = columnService.findById(pd); 
			PageData pdTop =new PageData();
			pdTop.put("PAGE_FROM", 0);
			//
			pdTop.put("PAGE_SIZE", Const.TOP_MAX_NUM);
			pdTop.put("RECOMMEND_FLAG", Const.RECOMMEND_FLAG);
			List<PageData>  topVideoDataList = videoService.listVideosByPage(pdTop);
			
			mv.setViewName("home/video_detail");
			mv.addObject("bannerDataList", HomeController.mapHomeData.get("bannerDataList"));
			mv.addObject("columnDataList", HomeController.mapHomeData.get("columnDataList"));
			mv.addObject("columnData", columnData);
			mv.addObject("videoData", videoData);
			mv.addObject("recommenVideoDataList", recommenVideoDataList);
			mv.addObject("topVideoDataList", topVideoDataList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}	
	/**
	 * 视频列表
	 */
	@RequestMapping(value="/videoPlay/{CHANNEL_NO}/{VIDEO_ID}")
	public ModelAndView videoPlay(Page page,@PathVariable String CHANNEL_NO,@PathVariable("VIDEO_ID") String VIDEO_ID){
		//logBefore(logger, "startindex");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();

		try{
			pd = this.getPageData();
			pd.put("VIDEO_ID", VIDEO_ID);
			PageData videoData = videoService.findById(pd);
			String playData =  "";
			int SHOW_TYPE = 1;
			if(videoData !=null){
				if(SHOW_TYPE==1){
					playData = 	 videoData.getString("VIDEO_URL");
				} else {
					playData = 	 videoData.getString("VIDEO_URL_TWO");
				}
			}
			pd.put("CHANNEL_NO", CHANNEL_NO);
			pd.put("playData", playData);
			mv.setViewName("wap/video_play");
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}	
	/**
	 * 获取支付信息
	 */
	@RequestMapping(value="/getPayInfo")
	public void getPayInfo(PrintWriter out){

		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			List<PageData>  planList = planService.listAll(pd);
			Map payInfo =new HashMap();
			//取得方案数据
			for(PageData plan:planList){
				//取得版块信息
				pd.put("PLAN_ID", plan.getString("PLAN_ID"));
				PageData  appInfo =planService.getAppInfo(pd);
				payInfo.put("price", appInfo.get("PRICE"));
				break;
			}
			String jsonData=JSONArray.toJSONString(payInfo);
			out.write(jsonData);
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	/**
	 * 获取播放信息
	 */
	@RequestMapping(value="/getPlayInfo/{VIDEO_ID}/{SHOW_TYPE}")
	public void getPlayInfo(PrintWriter out,@PathVariable("VIDEO_ID") String VIDEO_ID,@PathVariable("SHOW_TYPE") int SHOW_TYPE){

		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			int tryTimes =  0;
			try{
				tryTimes = Integer.parseInt(pd.getString("trymp4times"));
			}catch(Exception ex){
				
			}
			//取得试播数据
			pd.put("DATA_TYPE", VideoDataTypeEnum.TryDataType.getKey());
			if(WapMovieController.tryVideoDataList ==null || WapMovieController.tryVideoDataList.size()==0){
				WapMovieController.tryVideoDataList = videoService.listVideos(pd);
			}
			pd.put("VIDEO_ID", VIDEO_ID);
			PageData videoData = videoService.findById(pd);
			
			String playData =  Tools.readTxtFile(Const.WAPPLAY);
			if(videoData !=null){
				if(SHOW_TYPE==1){
					playData = 	playData.replaceAll("VIDEO_IMG", videoData.getString("IMG_ONE"));
					if(tryTimes <WapMovieController.tryVideoDataList.size()){
						videoData = WapMovieController.tryVideoDataList.get(tryTimes);
					} else	if(0 <WapMovieController.tryVideoDataList.size()){ 
						videoData = WapMovieController.tryVideoDataList.get(0);
					}
					playData = 	playData.replaceAll("VIDEO_URL", videoData.getString("VIDEO_URL"));
					
				} else {
					playData = 	playData.replaceAll("VIDEO_URL", videoData.getString("VIDEO_URL_TWO"));
					playData = 	playData.replaceAll("IMG_TWO", videoData.getString("IMG_TWO"));
				}
			}
			out.write(playData);
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	/**
	 * 视频列表
	 */
	@RequestMapping(value="/checkPay")
	public ModelAndView checkPay(Page pageD){
		logBefore(logger, "checkPay");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();

		try{
			pd = this.getPageData();
			pd.put("OS_TYPE", PlanTypeEnum.Wap.getKey());
			List<PageData>  planList = planService.listAll(pd);
			Map payInfo =new HashMap();
			//取得方案数据
			for(PageData plan:planList){
				//取得版块信息
				pd.put("PLAN_ID", plan.getString("PLAN_ID"));
				PageData  appInfo =planService.getAppInfo(pd);
				payInfo.put("price", appInfo.get("PRICE"));
				break;
			}
			//shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();  
			Session session = currentUser.getSession();
			String channelNo = (String)session.getAttribute("CHANNEL_NO");
			if(pd.getString("CHANNEL_NO") ==null){
				pd.put("CHANNEL_NO", channelNo);
			}
			mv.setViewName("wap/order");
			mv.addObject("pd", pd);
			mv.addObject("payInfo", payInfo);
			mv.addObject("payType",WapHomeController.mapPayType);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}		
	/**
	 * 获取播放信息
	 */
	@RequestMapping(value="/checkPayed")
	public void checkPayed(PrintWriter out){
		logBefore(logger, "checkPayed");
		PageData pd = new PageData();
		String checkRet = "-1";
		try{
			pd = this.getPageData();
			String userId = pd.getString("uid");
			
			String orderNo= null;
			OrderInfo orderInfo = (OrderInfo)SwiftpassController.mapUserInfo.get(userId);
			if(orderInfo !=null){
				orderNo= orderInfo.getOrderNo();
			}
			logger.info("orderNo="+orderNo );
			if(orderInfo !=null && orderNo !=null){
				Integer orderStatus = (Integer)SwiftpassController.orderResult.get(orderNo);
				logger.info("orderStatus="+orderStatus );
				if((orderStatus !=null) && (orderStatus==1)){
					checkRet="0";
				} else {
					
				}
			}
			logger.info("userId="+userId +"|checkRet="+checkRet);
			out.write(checkRet);
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
    @RequestMapping(value="/pay")
    public String pay(HttpServletRequest request,PrintWriter out) {
    	paylogger.info("pay start");
    	String acceptjson = "";  
    	Map<String, Object> result = new HashMap<String, Object>();
    	PageData pd = new PageData();
    	pd = this.getPageData();
    	result.put("result", "error");
        try {  
        	//保存激活数据
        	PayData payData= new  PayData();
        	payData.setSdk_version(getUserOS());
        	payData.setCell_model(this.getUserAgent());
        	payData.setPay_id(this.get32UUID());
        	payData.setOrder_no(pd.getString("order_no"));
           payService.savePay(payData);
           result.put("result", "success");
        }catch(Exception ex) {
        	logger.error(ex);
        }
        String jsonData = JSONArray.toJSONString(result);
			
		out.write(jsonData);
		out.close();
        paylogger.info("pay end");
        return null;
    }
	/**
	 * 视频列表关于
	 */
	@RequestMapping(value="/about/{CHANNEL_NO}")
	public ModelAndView about(Page page,@PathVariable String CHANNEL_NO){
		logBefore(logger, "about");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
	
		try {
			pd.put("CHANNEL_NO", CHANNEL_NO);
			pd.put("DATA_TYPE", ColumnDataTypeEnum.AbaoutType.getKey());
			List<PageData> columnList = columnService.listColumns(pd);
			if(columnList !=null){
				pd.put("contractImg", columnList.get(0).get("IMG_ONE"));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("wap/about");
		mv.addObject("columnDataList", WapHomeController.mapHomeData.get("columnDataList"));
		mv.addObject("pd", pd);
		return mv;
	}	
	/**
	 * 视频列表关于
	 */
	@RequestMapping(value="/login/{CHANNEL_NO}")
	public ModelAndView login(Page page,@PathVariable String CHANNEL_NO){
		logBefore(logger, "login");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
	
		try {
			pd.put("CHANNEL_NO", CHANNEL_NO);

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("wap/login");
		mv.addObject("pd", pd);
		return mv;
	}	  
	/**
	 * 获取支付信息
	 * @throws Exception 
	 */
	@RequestMapping(value="/checkOrderPayed")
	public void checkOrderPayed(HttpServletRequest request,PrintWriter out) throws Exception{
		paylogger.info("checkOrderPayed");
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		String orderNo = pd.getString("orderNo");
		String userId = pd.getString("uid");
		String ret= null;

		pd.put("transaction_id", orderNo);
		PageData orderData = thirdOrderService.findByWxOrderNo(pd);
		int vipType=0;
		if(orderData!=null){
			
		} else {
			orderData = thirdOrderService.findAndroidByWxOrderNo(pd);
		}
		if(orderData !=null){
			vipType =(Integer)orderData.get("VIP_TYPE");
			ret ="1";
			SwiftpassController.orderResult.put(orderNo, 1);//初始状态
		} else{
			//海豚支付
			if(WapV3HomeController.mapPayType.get("4")!=null){
				ret=YLpayController.checkOrderPayed(orderNo);
			} else if(WapV3HomeController.mapPayType.get("3")!=null){
				ret = HeepayController.checkOrderPayed(orderNo);
			}
			
		}
		if(ret!= null){
			OrderInfo orderInfo = (OrderInfo)SwiftpassController.mapUserInfo.get(userId);
			if(orderInfo ==null){
			  orderInfo=new OrderInfo();
			  orderInfo.setOrderNo(orderNo);
			  orderInfo.setUserId(userId);
			  SwiftpassController.mapUserInfo.put(userId, orderInfo);
			} else {
				orderInfo.setOrderNo(orderNo);
				SwiftpassController.mapUserInfo.put(userId, orderInfo);

			}
		}
		out.write(String.valueOf(ret));
		out.close();
	
	}
    @RequestMapping(value="/clearChache")
    public String clearChache(HttpServletRequest request,PrintWriter out) {
    	paylogger.info("clearChache start");
    	String acceptjson = "";  
    	Map<String, Object> result = new HashMap<String, Object>();
    	PageData pd = new PageData();
    	pd = this.getPageData();
    	result.put("result", "success");
    	WapMovieController.mapColumnData =new HashMap();
    	WapMovieController.tryVideoDataList =null;
        String jsonData = JSONArray.toJSONString(result);
        WapHomeController.mapPayType = new HashMap();
		out.write(jsonData);
		out.close();
        paylogger.info("clearChache end");
        return null;
    }
	/**
	 * 获取支付信息
	 */
	@RequestMapping(value="/loadResult/{CHANNEL_NO}")
	public ModelAndView loadResult(Page page,@PathVariable String CHANNEL_NO){
		paylogger.info("loadResult");
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
