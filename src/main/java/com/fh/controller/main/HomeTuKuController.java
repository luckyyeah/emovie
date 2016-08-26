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
import com.fh.entity.Page;
import com.fh.enums.ColumnDataTypeEnum;
import com.fh.enums.PlanTypeEnum;
import com.fh.service.videocontent.column.ColumnService;
import com.fh.service.videocontent.plan.PlanService;
import com.fh.service.videocontent.tab.TabService;
import com.fh.service.videocontent.video.VideoService;
import com.fh.util.Const;
import com.fh.util.PageData;

/** 
 * 类名称：HomeTuKuController
 * 创建人：FH 
 * 创建时间：2015-03-21
 */
@Controller
@RequestMapping(value="/tuku")
public class HomeTuKuController extends BaseController {
	

	@Resource(name="planService")
	private PlanService planService;
	
	@Resource(name="tabService")
	private TabService tabService;
	
	@Resource(name="columnService")
	private ColumnService columnService;
	
	@Resource(name="videoService")
	private VideoService videoService;
	
	public static Map mapHomeData =new HashMap();
	
	public static  Map mapTabData =new HashMap();
	
	public static  Map mapImageData =new HashMap();
	/**
	 * 列表
	 */
	@RequestMapping(value="/index/{CHANNEL_NO}")
	public ModelAndView listIndex(Page page,@PathVariable String CHANNEL_NO){
		logBefore(logger, "startindex");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		List<PageData>  tabDataList =new ArrayList<PageData>();
		//List columnvideoList =new ArrayList();
		Map mapTabColumnList =new HashMap();
		List<PageData>  bannerDataList =new ArrayList<PageData>();
		try{
			pd = this.getPageData();
			pd.put("OS_TYPE", PlanTypeEnum.Tuku.getKey());
			
			if(mapHomeData.get("bannerDataList")==null || mapHomeData.get("tabDataList")==null){
				List<PageData>  planList = planService.listAll(pd);
	
				String showType="";
				Map payInfo =new HashMap();
				//取得方案数据
				for(PageData plan:planList){
					//取得版块信息
					pd.put("PLAN_ID", plan.getString("PLAN_ID"));
					tabDataList = tabService.listTabs(pd);
					for(PageData tab:tabDataList){
						//取得栏目信息
						pd.put("TAB_ID", tab.getString("TAB_ID"));
						List<PageData>   columnDataList=columnService.listColumns(pd);
						if(ColumnDataTypeEnum.BannerType.getKey()==(Integer)tab.get("DATA_TYPE")){
							bannerDataList= columnDataList;
						}
						if(ColumnDataTypeEnum.Image.getKey()==(Integer)tab.get("DATA_TYPE")){
							mapTabColumnList.put(tab.getString("TAB_ID"), columnDataList);
						}
					}
					//取得版块信息
					pd.put("PLAN_ID", plan.getString("PLAN_ID"));
					List<PageData>  appPriceList =planService.listAppPrice(pd);
					for(PageData appPrice :appPriceList){
						payInfo.put(appPrice.getString("VIP_TYPE"), appPrice.get("PRICE"));
					}
					break;
				}
	
				page.setPd(pd);
				HomeController.mapHomeData.put("payInfo", payInfo);
				mapHomeData.put("bannerDataList", bannerDataList);
				mapHomeData.put("tabDataList", tabDataList);
				mapHomeData.put("mapTabColumnList", mapTabColumnList);
			} 
			pd.put("COLUMN_ID",null);
			pd.put("CHANNEL_NO", CHANNEL_NO);
			//mv.setViewName("home/index");
			mv.setViewName("wapv2/tuku/tuku");
			mv.addObject("bannerDataList", mapHomeData.get("bannerDataList"));
			mv.addObject("tabDataList", mapHomeData.get("tabDataList"));
			mv.addObject("mapTabColumnList", mapHomeData.get("mapTabColumnList"));
			mv.addObject("payInfo", 	HomeController.mapHomeData.get("payInfo"));
			mv.addObject("pd", pd);
			mv.addObject("COLUMN_NO", 4);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
			//动态支付
			mv.addObject("payType", HomeController.mapPayType);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	/**
	 * 频道视频列表
	 */
	@RequestMapping(value="/listTukuCovers/{CHANNEL_NO}/{TAB_ID}")
	public ModelAndView listTukuCovers(Page page,@PathVariable String CHANNEL_NO,@PathVariable("TAB_ID") String TAB_ID,@RequestParam(value="PAGE_NO",required=false) String PAGE_NO){
		
		logBefore(logger, "listTukuCovers");
		ModelAndView mv = this.getModelAndView();
		listTukuCovers( mv,CHANNEL_NO, TAB_ID, PAGE_NO);
		mv.addObject("COLUMN_NO", 4);
		mv.setViewName("wapv2/tuku/coverlist");
		return mv;
	}
	private void listTukuCovers(ModelAndView mv,String CHANNEL_NO,String TAB_ID,String PAGE_NO){
		try{
			
			PageData pd = new PageData();


			pd = this.getPageData();

			Map mapcolumnPageNoInfoData =new HashMap();
			Map mapcolumnPageNoData =new HashMap();
			//取得视频信息
			pd.put("TAB_ID", TAB_ID);
			pd.put("CHANNEL_NO", CHANNEL_NO);
			if(mapTabData.get(TAB_ID)==null || ((HashMap)mapTabData.get(TAB_ID)).get(PAGE_NO)==null){
				if(mapTabData.get(TAB_ID)!=null){
					mapcolumnPageNoData = (HashMap)mapTabData.get(TAB_ID);
				}
				pd.put("PAGE_SIZE", Const.IMG_PAGE_SZIE);
				if(PAGE_NO !=null){
					pd.put("PAGE_FROM", (Integer.parseInt(PAGE_NO)-1)*Const.IMG_PAGE_SZIE);
				} else {
					pd.put("PAGE_FROM", 0);
					PAGE_NO = "1";
				}


				PageData tabData = tabService.findById(pd);        
				PageData pageCntObj = columnService.getCoverCnt(pd);
				int pageCnt = 0;
				if(pageCntObj !=null && pageCntObj.get("COVER_CNT")!=null){
					double coverCnt=(Long)pageCntObj.get("COVER_CNT");
					pageCnt = (int) Math.ceil(coverCnt/Const.IMG_PAGE_SZIE);
				}
				List<PageData>  coverDataList = columnService.listCoversByPage(pd);
				List <String> pageNoList = new ArrayList<String>();
				for(int i=1;i<=pageCnt;i++){
					pageNoList.add(String.valueOf(i));
				}
				
				mapcolumnPageNoInfoData.put("pageNoList", pageNoList);
				mapcolumnPageNoInfoData.put("coverDataList", coverDataList);
				mapcolumnPageNoData.put(PAGE_NO, mapcolumnPageNoInfoData);
				mapcolumnPageNoData.put("tabData", tabData);
				mapTabData.put(TAB_ID, mapcolumnPageNoData);
			} else {
				mapcolumnPageNoData = (HashMap)mapTabData.get(TAB_ID);
				mapcolumnPageNoInfoData = (HashMap)((HashMap)mapTabData.get(TAB_ID)).get(PAGE_NO);
			}

			mv.addObject("tabData", mapcolumnPageNoData.get("tabData"));
			mv.addObject("coverDataList", mapcolumnPageNoInfoData.get("coverDataList"));
			mv.addObject("pageNoList", mapcolumnPageNoInfoData.get("pageNoList"));
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
	 * 频道视频列表
	 */
	@RequestMapping(value="/listImages/{CHANNEL_NO}/{COLUMN_ID}")
	public ModelAndView listImages(Page page,@PathVariable String CHANNEL_NO,@PathVariable("COLUMN_ID") String COLUMN_ID,@RequestParam(value="PAGE_NO",required=false) String PAGE_NO){
		
		logBefore(logger, "listImages");
		ModelAndView mv = this.getModelAndView();
		listColumnVideo( mv,CHANNEL_NO, COLUMN_ID, PAGE_NO);
		mv.addObject("COLUMN_NO", 3);
		mv.setViewName("wapv2/tuku/image");
		return mv;
	}
	private void listColumnVideo(ModelAndView mv,String CHANNEL_NO,String COLUMN_ID,String PAGE_NO){
		try{
			
			PageData pd = new PageData();
			long imageCnt=0;
			Map mapColumnImageList =new HashMap();
			pd = this.getPageData();
			pd.put("CHANNEL_NO", CHANNEL_NO);
			//取得视频信息
			pd.put("COLUMN_ID", COLUMN_ID);
			if(mapImageData.get(COLUMN_ID)==null){
				pd.put("PAGE_SIZE", Const.IMG_MAX_PAGE_SZIE);
				if(PAGE_NO !=null){
					pd.put("PAGE_FROM", (Integer.parseInt(PAGE_NO)-1)*Const.IMG_MAX_PAGE_SZIE);
				} else {
					pd.put("PAGE_FROM", 0);
					PAGE_NO = "1";
				}
				PageData columnData = columnService.findById(pd);     
				if(columnData!=null){
					//取得视频信息
					pd.put("TAB_ID", columnData.getString("TAB_ID"));
				}
				List<PageData>  recommendCoversList = columnService.listRecommendCovers(pd);
				PageData pageCntObj = videoService.getVideoCnt(pd);
				int pageCnt = 0;
				if(pageCntObj !=null && pageCntObj.get("VIDEO_CNT")!=null){
				 imageCnt=(Long)pageCntObj.get("VIDEO_CNT");
					pageCnt = (int) Math.ceil(imageCnt/Const.IMG_MAX_PAGE_SZIE);
					
				}
				List<PageData>  imageDataList = videoService.listVideosByPage(pd);

				mapColumnImageList.put("columnData", columnData);
				mapColumnImageList.put("imageDataList", imageDataList);
				mapColumnImageList.put("recommendCoversList", recommendCoversList);
				mapColumnImageList.put("imageCnt", imageCnt);
				mapImageData.put(COLUMN_ID, mapColumnImageList);
			} else {
				mapColumnImageList = (HashMap)mapImageData.get(COLUMN_ID);
			}
			mv.addObject("columnData", mapColumnImageList.get("columnData"));
			mv.addObject("imageDataList", mapColumnImageList.get("imageDataList"));
			mv.addObject("recommendCoversList", mapColumnImageList.get("recommendCoversList"));
			mv.addObject("imageCnt", mapColumnImageList.get("imageCnt"));
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
	 * 获取播放信息
	 */
	@RequestMapping(value="/getNextImage/{COLUMN_ID}")
	public void getNextImage(PrintWriter out,@PathVariable("COLUMN_ID") String COLUMN_ID){

		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			PageData pdImage = new PageData();
			Map mapColumnVideoList =(HashMap)mapImageData.get(COLUMN_ID);
			
			if(mapColumnVideoList!=null){
				
				List<PageData>  imageDataList = (List<PageData>)mapColumnVideoList.get("imageDataList");
				String jsonData = JSONArray.toJSONString(imageDataList);
				out.write(jsonData);
				//return imageDataList;
			}
			//out.write(imageDataList);
			out.flush();
			out.close();
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
