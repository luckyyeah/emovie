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
import com.fh.entity.OrderInfo;
import com.fh.entity.Page;
import com.fh.entity.PayData;
import com.fh.service.videocontent.column.ColumnService;
import com.fh.service.videocontent.plan.PlanService;
import com.fh.service.videocontent.tab.TabService;
import com.fh.service.videocontent.video.PayService;
import com.fh.service.videocontent.video.VideoService;
import com.fh.util.CommonUtil;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.PageData;
import com.fh.util.Tools;
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
	
	private static Log paylogger = LogFactory.getLog("paylogger");
	/**
	 * 频道视频列表
	 */
	@RequestMapping(value="/listColumnVideo/{CHANNEL_NO}/{COLUMN_ID}")
	public ModelAndView listColumnVideo(Page page,@PathVariable String CHANNEL_NO,@PathVariable("COLUMN_ID") String COLUMN_ID,@RequestParam(value="PAGE_NO",required=false) String PAGE_NO){
		
		logBefore(logger, "listColumnVideo");
		ModelAndView mv = this.getModelAndView();
		listColumnVideo( mv,CHANNEL_NO, COLUMN_ID, PAGE_NO);
		mv.setViewName("wapv2/column_video_list");
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

			mv.addObject("bannerDataList", HomeController.mapHomeData.get("bannerDataList"));
			mv.addObject("columnDataList", HomeController.mapHomeData.get("columnDataList"));
			mv.addObject("columnData", columnData);
			mv.addObject("videoDataList", videoDataList);
			mv.addObject("pageNoList", pageNoList);
			mv.addObject("PAGE_NO",Integer.parseInt(PAGE_NO));
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
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

			pd.put("CHANNEL_NO", CHANNEL_NO);
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
			//List<PageData>  topVideoDataList = videoService.listVideosByPage(pdTop);
			
			mv.setViewName("wapv2/play");
			mv.addObject("bannerDataList", HomeController.mapHomeData.get("bannerDataList"));
			mv.addObject("columnDataList", HomeController.mapHomeData.get("columnDataList"));
			mv.addObject("columnData", columnData);
			mv.addObject("videoData", videoData);
			mv.addObject("recommenVideoDataList", recommenVideoDataList);
		//	mv.addObject("topVideoDataList", topVideoDataList);
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
		//	mv.addObject("topVideoDataList", topVideoDataList);
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
			pd.put("VIDEO_ID", VIDEO_ID);
			PageData videoData = videoService.findById(pd);
			String playData =  Tools.readTxtFile(Const.CKPLAY);
			if(SHOW_TYPE==1){
				playData = 	playData.replaceAll("VIDEO_URL", videoData.getString("VIDEO_URL"));
			} else {
				playData = 	playData.replaceAll("VIDEO_URL", videoData.getString("VIDEO_URL_TWO"));
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
