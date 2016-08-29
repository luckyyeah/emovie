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
import com.fh.entity.ClientComment;
import com.fh.entity.OrderInfo;
import com.fh.entity.Page;
import com.fh.entity.PayData;
import com.fh.service.videocontent.column.ColumnService;
import com.fh.service.videocontent.comment.ClientCommentService;
import com.fh.service.videocontent.plan.PlanService;
import com.fh.service.videocontent.tab.TabService;
import com.fh.service.videocontent.video.PayService;
import com.fh.service.videocontent.video.ThirdOrderService;
import com.fh.service.videocontent.video.VideoService;
import com.fh.util.CommonUtil;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.PageData;
import com.fh.util.Tools;
import com.heepay.HeepayConfig;
/** 
 * 类名称：HomeController
 * 创建人：FH 
 * 创建时间：2015-03-21
 */
@Controller
@RequestMapping(value="/wapv2")
public class WapMovieV2Controller extends BaseController {
	

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
	
	@Resource(name="clientCommentService")
	private ClientCommentService clientCommentService;
	
	public static  Map mapcolumnData =new HashMap();
	
	public static  Map mapVideoData =new HashMap();
	
	private static Log paylogger = LogFactory.getLog("paylogger");
	
	private static String ck_player =null;
	/**
	 * 频道视频列表
	 */
	@RequestMapping(value="/listColumnVideo/{CHANNEL_NO}/{COLUMN_ID}")
	public ModelAndView listColumnVideo(Page page,@PathVariable String CHANNEL_NO,@PathVariable("COLUMN_ID") String COLUMN_ID,@RequestParam(value="PAGE_NO",required=false) String PAGE_NO){
		
		logBefore(logger, "listColumnVideo");
		ModelAndView mv = this.getModelAndView();
		listColumnVideo( mv,CHANNEL_NO, COLUMN_ID, PAGE_NO);
		mv.setViewName("wapv2/column_video_list");
		mv.addObject("COLUMN_NO", 1);
		return mv;
	}	
	/**
	 * 频道视频列表
	 */
	@RequestMapping(value="/listDiamondVideo/{CHANNEL_NO}/{COLUMN_ID}")
	public ModelAndView listDiamondVideo(Page page,@PathVariable String CHANNEL_NO,@PathVariable("COLUMN_ID") String COLUMN_ID,@RequestParam(value="PAGE_NO",required=false) String PAGE_NO){
		
		logBefore(logger, "listColumnVideo");
		ModelAndView mv = this.getModelAndView();
		listColumnVideo( mv,CHANNEL_NO, COLUMN_ID, PAGE_NO);
		mv.addObject("COLUMN_NO", 3);
		mv.setViewName("wapv2/diamond");
		return mv;
	}
	private void listColumnVideo(ModelAndView mv,String CHANNEL_NO,String COLUMN_ID,String PAGE_NO){
		try{
			
			PageData pd = new PageData();


			pd = this.getPageData();


			pd.put("PAGE_SIZE", Const.PAGE_SZIE);
			if(PAGE_NO !=null){
				pd.put("PAGE_FROM", (Integer.parseInt(PAGE_NO)-1)*Const.PAGE_SZIE);
			} else {
				pd.put("PAGE_FROM", 0);
				PAGE_NO = "1";
			}
			pd.put("CHANNEL_NO", CHANNEL_NO);
			//取得视频信息
			pd.put("COLUMN_ID", COLUMN_ID);
			if(mapcolumnData.get(COLUMN_ID)==null || ((HashMap)mapcolumnData.get(COLUMN_ID)).get(PAGE_NO)==null){
				Map mapcolumnPageNoData =new HashMap();
				Map mapcolumnPageNoInfoData =new HashMap();
				if(mapcolumnData.get(COLUMN_ID)!=null){
					mapcolumnPageNoData = (HashMap)mapcolumnData.get(COLUMN_ID);
				}
				PageData columnData = columnService.findById(pd);        
				PageData pageCntObj = videoService.getVideoCnt(pd);
				int pageCnt = 0;
				if(pageCntObj !=null && pageCntObj.get("VIDEO_CNT")!=null){
					double videoCnt=(Long)pageCntObj.get("VIDEO_CNT");
					pageCnt = (int) Math.ceil(videoCnt/Const.PAGE_SZIE);
				}
				List<PageData>  videoDataList = videoService.listVideosByPage(pd);
				List <String> pageNoList = new ArrayList<String>();
				for(int i=1;i<=pageCnt;i++){
					pageNoList.add(String.valueOf(i));
				}
				mapcolumnPageNoInfoData.put("pageNoList", pageNoList);
				mapcolumnPageNoInfoData.put("videoDataList", videoDataList);
				mapcolumnPageNoData.put(PAGE_NO, mapcolumnPageNoInfoData);
				mapcolumnPageNoData.put("columnData", columnData);
				mapcolumnData.put(COLUMN_ID, mapcolumnPageNoData);
			}
			mv.addObject("columnDataList", HomeController.mapHomeData.get("columnDataList"));
			mv.addObject("columnData",((HashMap)WapMovieV2Controller.mapcolumnData.get(COLUMN_ID)).get("columnData"));
			mv.addObject("videoDataList", ((HashMap)((HashMap)WapMovieV2Controller.mapcolumnData.get(COLUMN_ID)).get(PAGE_NO)).get("videoDataList"));
			mv.addObject("pageNoList", ((HashMap)((HashMap)WapMovieV2Controller.mapcolumnData.get(COLUMN_ID)).get(PAGE_NO)).get("pageNoList"));
			mv.addObject("PAGE_NO",Integer.parseInt(PAGE_NO));
			mv.addObject("pd", pd);
			mv.addObject("payInfo", 	HomeController.mapHomeData.get("payInfo"));
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
			//动态支付
			mv.addObject("payType", HomeController.mapPayType);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
	}
	/**
	 * 视频列表
	 */
	@RequestMapping(value="/videoPlay/{CHANNEL_NO}/{COLUMN_ID}/{VIDEO_ID}")
	public ModelAndView videoPlay(Page page,@PathVariable String CHANNEL_NO,@PathVariable("COLUMN_ID") String COLUMN_ID,@PathVariable("VIDEO_ID") String VIDEO_ID){
		logBefore(logger, "startindex");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();

		try{
			
			pd = this.getPageData();
			Map mapcolumnVideoData =new HashMap();
			pd.put("CHANNEL_NO", CHANNEL_NO);
			//取得视频信息
			pd.put("COLUMN_ID", COLUMN_ID);
			List<PageData>  clientCommentList= new ArrayList<PageData>();
			//使用内存缓存，避免多次查询数据库
			if(mapVideoData.get(VIDEO_ID)==null){
				List<PageData>  recommenVideoDataList = videoService.listRecommendVideos(pd);
				page.setPd(pd);
				pd.put("VIDEO_ID", VIDEO_ID);
				PageData videoData = videoService.findById(pd);
				PageData columnData = columnService.findById(pd); 
				int resourceType =0;
				if(videoData.get("FREE_FLAG")!=null){
					if((Integer)videoData.get("FREE_FLAG")==1){
						resourceType =2;
					}
				}
				if(videoData.get("VIP_FLAG")!=null){
					resourceType =(Integer)videoData.get("VIP_FLAG");
				}
		    clientCommentList=clientCommentService.listClientComment(pd);
				mapcolumnVideoData.put("recommenVideoDataList", recommenVideoDataList);
				mapcolumnVideoData.put("videoData", videoData);
				mapcolumnVideoData.put("columnData", columnData);
				mapcolumnVideoData.put("resourceType", resourceType);
				mapcolumnVideoData.put("clientCommentList", clientCommentList);
				mapVideoData.put(VIDEO_ID, mapcolumnVideoData);
			} else {
				mapcolumnVideoData = (HashMap)mapVideoData.get(VIDEO_ID);
				clientCommentList = (ArrayList<PageData>)mapcolumnVideoData.get("clientCommentList");
			}
			List<ClientComment>  clientCommentDataList= new ArrayList<ClientComment>();
	    try { 
	    	pd.put("CLIENT_COMMENT_CNT", Const.CLIENT_COMMENT_CNT);

	    	int commentTime =0;
	    	for(int i=0;i<10;i++){
	    		//随机取评价
	    		int randIndex = CommonUtil.getRandomInt(1, clientCommentList.size()-1);
	    		PageData clientComment =clientCommentList.get(randIndex);
	    		ClientComment clientCommentData =new ClientComment();
	    		clientCommentData.setClientId(clientComment.getString("CLIENT_NAME"));
	    		//产生1-9的随机数时间
	    		commentTime+=CommonUtil.getRandomInt(8, 100);
	    		clientCommentData.setCommentTime(commentTime);
	    		clientCommentData.setClientComment(clientComment.getString("CLIENT_COMMENT"));
	    		clientCommentData.setClientIconUrl(clientComment.getString("CLIENT_ICON_URL"));
	    		clientCommentData.setPreCol1(clientComment.getString("PRE_COL1"));
	    		clientCommentData.setPreCol2(clientComment.getString("PRE_COL2"));
	    		clientCommentData.setPreCol3(clientComment.getString("PRE_COL3"));
	    		clientCommentDataList.add(clientCommentData);
	    	}
	    }
	    catch(Exception ex) {
	    	logger.error(ex);
	    }
			mv.setViewName("wapv2/play");
			mv.addObject("bannerDataList", HomeController.mapHomeData.get("bannerDataList"));
			mv.addObject("columnDataList", HomeController.mapHomeData.get("columnDataList"));
			mv.addObject("columnData", mapcolumnVideoData.get("columnData"));
			mv.addObject("videoData", mapcolumnVideoData.get("videoData"));
			mv.addObject("recommenVideoDataList", mapcolumnVideoData.get("recommenVideoDataList"));
			mv.addObject("payInfo", 	HomeController.mapHomeData.get("payInfo"));
			mv.addObject("clientCommentDataList", 	clientCommentDataList);
		//	mv.addObject("topVideoDataList", topVideoDataList);
			mv.addObject("pd", pd);
			if((Integer)mapcolumnVideoData.get("resourceType")==3){
				mv.addObject("COLUMN_NO", 3);
			} else {
				mv.addObject("COLUMN_NO", 1);
			}
			mv.addObject("resourceType", mapcolumnVideoData.get("resourceType"));
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
			//动态支付
			mv.addObject("payType", HomeController.mapPayType);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}	
	/**
	 * 视频列表
	 */
	@RequestMapping(value="/channel/{CHANNEL_NO}")
	public ModelAndView listChannelDetail(Page page,@PathVariable String CHANNEL_NO){
		logBefore(logger, "startindex");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		Map filmMap =new HashMap();
		Map newFilmMap =new HashMap();
		Map columnNameMap =new HashMap();
		try{
			pd = this.getPageData();
			pd.put("CHANNEL_NO", CHANNEL_NO);
			if(!DateUtil.getDays().equals((String)HomeController.mapHomeData.get("filmUpdateDate"))){
				List<PageData> columnDataList = (List<PageData>)HomeController.mapHomeData.get("columnDataList");
				for(PageData columnData:columnDataList){
					String columnId= columnData.getString("COLUMN_ID");
					if(HomeController.mapHomeData.get("filmMap")!=null){
					   filmMap = (HashMap)HomeController.mapHomeData.get("filmMap");
					}
					if(filmMap.get(columnId)==null){
						filmMap.put(columnId, CommonUtil.getRandomInt(1200, 1500));
						newFilmMap.put(columnId, CommonUtil.getRandomInt(20, 39));
					} else {
						int newFilmCnt =CommonUtil.getRandomInt(20, 39);
						int filmCnt = (Integer)filmMap.get(columnId)+newFilmCnt;
						filmMap.put(columnId, filmCnt);
						newFilmMap.put(columnId, newFilmCnt);
					}
					columnNameMap.put(columnId, columnData.getString("NAME_TWO"));
				}
				HomeController.mapHomeData.put("columnNameMap",columnNameMap);
				HomeController.mapHomeData.put("filmMap",filmMap);
				HomeController.mapHomeData.put("newFilmMap",newFilmMap);
				HomeController.mapHomeData.put("filmUpdateDate",DateUtil.getDays());
			}
			mv.setViewName("wapv2/channel");
			mv.addObject("bannerDataList", HomeController.mapHomeData.get("bannerDataList"));
			mv.addObject("columnDataList", HomeController.mapHomeData.get("columnDataList"));
			mv.addObject("filmMap", HomeController.mapHomeData.get("filmMap"));
			mv.addObject("newFilmMap", HomeController.mapHomeData.get("newFilmMap"));
			mv.addObject("columnNameMap", HomeController.mapHomeData.get("columnNameMap"));
			mv.addObject("payInfo", 	HomeController.mapHomeData.get("payInfo"));
		//	mv.addObject("topVideoDataList", topVideoDataList);
			mv.addObject("pd", pd);
			mv.addObject("COLUMN_NO", 2);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
			//动态支付
			mv.addObject("payType", HomeController.mapPayType);
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
			pd.put("VIDEO_ID", VIDEO_ID);
			PageData videoData = null;
			Map mapcolumnVideoData = (HashMap)mapVideoData.get(VIDEO_ID);
			if(mapcolumnVideoData !=null){
				videoData = (PageData)mapcolumnVideoData.get("videoData");
			} else {
				videoData = videoService.findById(pd);
			}
			String playData =  null;
			//保存在静态变量中避免每次去读取文件
			if(ck_player==null){
				playData = Tools.readTxtFile(Const.CKPLAY);
				ck_player = playData;
			} else {
				playData =ck_player;
			}
			//shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();  
			Session session = currentUser.getSession();
			int vipType= 0;
			if(session.getAttribute("vipType")!=null){
				vipType = (Integer)session.getAttribute("vipType");
			}
			int videoVipType= 0;
			int freeFlag = 0;
			if(videoData !=null){
				if(videoData.get("FREE_FLAG")!=null){
					freeFlag = (Integer)videoData.get("FREE_FLAG");
				}
				if(videoData.get("VIP_FLAG")!=null){
					videoVipType = (Integer)videoData.get("VIP_FLAG");
				}
				if((freeFlag==1&&SHOW_TYPE==1) ||(videoVipType<=vipType)){
					if(SHOW_TYPE==1){
						playData = 	playData.replaceAll("VIDEO_URL", videoData.getString("VIDEO_URL"));
					} else {
						playData = 	playData.replaceAll("VIDEO_URL", videoData.getString("VIDEO_URL_TWO"));
					}
				} else{
					playData="0";
				}
			}
			out.write(playData);
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	/**
	 * 获取播放信息
	 */
	@RequestMapping(value="/checkPay")
	public void checkPay(PrintWriter out){

		PageData pd = new PageData();
		int checkRet = -1;
		try{
			//shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();  
			Session session = currentUser.getSession();
			if(session.getAttribute("orderInfo")==null){
				OrderInfo orderInfo =(OrderInfo)session.getAttribute("orderInfo");
				if(orderInfo !=null &&orderInfo.getOrderNo() !=null && !"".equals(orderInfo.getOrderNo())){
					checkRet=0;
				}
			}
			out.write(checkRet);
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	/**
	 * 获取播放信息
	 */
	@RequestMapping(value="/checkPayed")
	public void checkPayed(PrintWriter out){
		logBefore(logger, "checkPayed");
		PageData pd = new PageData();
		int vipType = 0;
		try{
			pd = this.getPageData();
			String orderNo = pd.getString("out_trade_no");
			PageData orderData = thirdOrderService.findByOrderNo(pd);
			if(orderData!=null){
				
			} else {
				orderData = thirdOrderService.findAndroidByOrderNo(pd);
			}
			if(orderData !=null){
				vipType =(Integer)orderData.get("VIP_TYPE");
			}
			//漏单是付费通服务器上检查
			if(orderData==null || (Integer)orderData.get("STATUS")==0){
				//海豚支付
				//海豚支付
				if(HomeController.mapPayType.get("4")!=null){
					String ret =YLpayController.checkOrderPayed(orderNo);
					if(ret==null){
						vipType =0;
					}
				} else	if(HomeController.mapPayType.get("3")!=null){ 
					String ret = HeepayController.checkOrderPayed(orderNo);
					if(ret==null){
						vipType =0;
					}
				}
			}
			if(vipType !=0){
				//shiro管理的session
				Subject currentUser = SecurityUtils.getSubject();  
				Session session = currentUser.getSession();
				session.setAttribute("vipType", vipType);
			}
			logger.info("out_trade_no="+orderNo +"|vipType="+vipType);
			out.write(String.valueOf(vipType));
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
	 * 自助激活
	 * @throws Exception 
	 */
	@RequestMapping(value="/checkOrderPayed")
	public void checkOrderPayed(HttpServletRequest request,PrintWriter out) throws Exception{
		paylogger.info("checkOrderPayed");
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		String orderNo = pd.getString("orderNo");
		String userId = pd.getString("uid");
		int ret= 0;
		pd.put("transaction_id", orderNo);
		PageData orderData = thirdOrderService.findByWxOrderNo(pd);
		int vipType=0;
		if(orderData!=null){
			
		} else {
			orderData = thirdOrderService.findAndroidByWxOrderNo(pd);
		}
		if(orderData !=null){
			vipType =(Integer)orderData.get("VIP_TYPE");
		}
		//付费订单
		if(vipType !=0 && (Integer)orderData.get("STATUS")==1){
			//shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();  
			Session session = currentUser.getSession();
			session.setAttribute("vipType", vipType);
			ret=1;
		} else {
			vipType =0;
		}
		out.write(String.valueOf(vipType));
		out.close();
	
	}
	/**
	 * 登录
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
		mv.setViewName("wapv2/login");
		mv.addObject("pd", pd);
		return mv;
	}	  
	/**
	 * 登录
	 */
	@RequestMapping(value="/member/{CHANNEL_NO}")
	public ModelAndView member(Page page,@PathVariable String CHANNEL_NO){
		logBefore(logger, "login");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
	
		try {
			pd.put("CHANNEL_NO", CHANNEL_NO);

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.addObject("payInfo", 	HomeController.mapHomeData.get("payInfo"));
		//动态支付
		mv.addObject("payType", HomeController.mapPayType);
		mv.setViewName("wapv2/member");
		mv.addObject("pd", pd);
		mv.addObject("COLUMN_NO", 5);
		return mv;
	}	  
	/**
	 * 视频列表关于
	 */
	@RequestMapping(value="/contract/{CHANNEL_NO}")
	public ModelAndView contract(Page page,@PathVariable String CHANNEL_NO){
		logBefore(logger, "login");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
	
		try {
			pd.put("CHANNEL_NO", CHANNEL_NO);

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mv.setViewName("wapv2/contract");
		mv.addObject("pd", pd);
		return mv;
	}	
    @RequestMapping(value="/clearCache")
    public String clearChache(HttpServletRequest request,PrintWriter out) {
    	paylogger.info("clearChache start");
    	String acceptjson = "";  
    	Map<String, Object> result = new HashMap<String, Object>();
    	PageData pd = new PageData();
    	pd = this.getPageData();
    	result.put("result", "success");
    	HomeController.mapHomeData =new HashMap();
    	WapMovieV2Controller.mapcolumnData =new HashMap();
    	WapMovieV2Controller.mapVideoData =new HashMap();
    	HomeController.mapPayType = new HashMap();
    	HomeTuKuController.mapHomeData = new HashMap();
    	HomeTuKuController.mapTabData =new HashMap();
    	HomeTuKuController.mapImageData =new HashMap();
       String jsonData = JSONArray.toJSONString(result);

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
		mv.setViewName("wapv2/login_result");
		return  mv;
	}
	@RequestMapping(value="/saveOrder")
	public void saveOrder(HttpServletRequest request,PrintWriter out) {
		PageData pd = new PageData();
		String payUrl=  "";

			pd = this.getPageData();
			String vipType =pd.getString("vipType");
			String channelNo = pd.getString("channelNo");
			String orderNo = pd.getString("out_trade_no");	
			String plugin_type = pd.getString("plugin_type");	
			logger.info("saveorder orderNo="+orderNo);
			String total_fee = null;
			Map payInfo = (HashMap)HomeController.mapHomeData.get("payInfo");
			if(payInfo.get(vipType) !=null && !"".equals(payInfo.get(vipType))){
				total_fee =String.valueOf((int)(Double.parseDouble(payInfo.get(vipType).toString())));
			} else {
				vipType="0";
				total_fee =HeepayConfig.total_fee;
			}
		  OrderInfo orderInfo=new OrderInfo();
		  orderInfo.setOrderNo(orderNo);
		  orderInfo.setChannelNo(channelNo);
		  orderInfo.setPayAmt(total_fee);
		  orderInfo.setVipType(Integer.parseInt(vipType));
		  orderInfo.setPlugin_type(plugin_type);
		  saveThirdOrder(orderInfo);
			out.write("OK");
			out.close();
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
    		SwiftpassController.orderResult.put(out_trade_no, 1);//支付成功
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 获取支付信息
	 */
	@RequestMapping(value="/goIdea/{CHANNEL_NO}")
	public ModelAndView goIdea(Page page,@PathVariable String CHANNEL_NO){
		paylogger.info("loadResult");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();

		pd.put("CHANNEL_NO", CHANNEL_NO);
		mv.addObject("pd", pd);
		mv.setViewName("wapv2/idea");
		return  mv;
	}
	/**
	 * 提交建议
	 * @throws Exception 
	 */
	@RequestMapping(value="/idea")
	public void idea(HttpServletRequest request,PrintWriter out) throws Exception{
		paylogger.info("idea");
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		pd.put("FEEDBACK_ID", this.get32UUID());
		clientCommentService.saveIdea(pd);

		out.write("1");
		out.close();
	
	}
	/**
	 * 获取播放信息
	 */
	@RequestMapping(value="/orderSyn")
	public void OrderSyn(PrintWriter out){
		logBefore(logger, "checkPayed");
		PageData pd = new PageData();
		int vipType = 0;
		String transaction_id=null;
		try{
			pd = this.getPageData();
			pd.put("STATUS", 0);
		
			List<PageData> notCompleteOrderList = thirdOrderService.findNotCompleteOrder(pd);
			for(PageData notCompleteOrder:notCompleteOrderList){
				String orderNo = notCompleteOrder.getString("OUT_TRADE_NO");
				if(HomeController.mapPayType.get("4")!=null){
					transaction_id =YLpayController.checkOrderPayed(orderNo);
					if(transaction_id==null){
						vipType =0;
					}
				} else	if(HomeController.mapPayType.get("3")!=null){ 
					transaction_id = HeepayController.checkOrderPayed(orderNo);
					if(transaction_id==null){
						vipType =0;
					}
				}
				if(transaction_id!=null){
					//更新订单信息
					Map<String,String> map = new HashMap<String,String>();
	        map.put("out_trade_no", orderNo);
					map.put("status", String.valueOf(1));
	        map.put("transaction_id", transaction_id);
	        thirdOrderService.edit(map);
	    		logger.info("update out_trade_no="+orderNo );
			}
			out.write(String.valueOf(vipType));
			out.close();
		
			}
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
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
