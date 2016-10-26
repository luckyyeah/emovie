package com.fh.controller.main;

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
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.enums.ColumnDataTypeEnum;
import com.fh.enums.PlanTypeEnum;
import com.fh.service.videocontent.column.ColumnService;
import com.fh.service.videocontent.pay.PayPluginService;
import com.fh.service.videocontent.plan.PlanService;
import com.fh.service.videocontent.tab.TabService;
import com.fh.service.videocontent.video.VideoService;
import com.fh.util.Const;
import com.fh.util.PageData;

/** 
 * 类名称：HomeController
 * 创建人：FH 
 * 创建时间：2015-03-21
 */
@Controller
@RequestMapping(value="/wapv3")
public class WapV3HomeController extends BaseController {
	

	@Resource(name="planService")
	private PlanService planService;
	
	@Resource(name="tabService")
	private TabService tabService;
	
	@Resource(name="columnService")
	private ColumnService columnService;
	
	@Resource(name="videoService")
	private VideoService videoService;
	
	@Resource(name="payPluginService")
	private PayPluginService payPluginService;
	
	public static Map mapHomeData =new HashMap();
	public static Map mapChannelPayType =new HashMap();
	public static Map mapDefaultPayType =new HashMap();
	public static Map mapPayInfo =new HashMap();
	
	@RequestMapping(value="/index/{CHANNEL_NO}")
	public ModelAndView listV3Index(Page page,@PathVariable String CHANNEL_NO){
		//logBefore(logger, "startindex");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		List<PageData>  columnDataList =new ArrayList<PageData>();

		String columnId= "";
		try{
			pd = this.getPageData();
			pd.put("OS_TYPE", PlanTypeEnum.WapV3.getKey());
			pd.put("CHANNEL_NO", CHANNEL_NO);
			List<PageData>  planList =new ArrayList<PageData>();
			//方案ID
			String planId= "";
			if(mapHomeData.get("PLAN"+CHANNEL_NO)==null){
				planList= planService.listPlanLinkChannelByChannelId(pd);
				if(planList==null || planList.size()<=0){
					 planList = planService.listAll(pd);
					 if(planList!=null && planList.size()>=0){
						 //保存渠道方案信息
						 mapHomeData.put("PLAN"+CHANNEL_NO, planList);
						 planId = planList.get(0).getString("PLAN_ID");
						 mapHomeData.put("PLANID"+CHANNEL_NO, planId);
					 }
				} else {
					  mapHomeData.put("PLAN"+CHANNEL_NO, planList);
					  planId = planList.get(0).getString("PLAN_ID");
					  mapHomeData.put("PLANID"+CHANNEL_NO, planId);
				}
				 //取得价格
			  	pd.put("PLAN_ID", planId);
					PageData  appInfo =planService.getAppInfo(pd);
					Map payInfo =new HashMap();
					payInfo.put("price", appInfo.get("PRICE"));
					payInfo.put("monthPrice", appInfo.get("MONTH_PRICE"));
					payInfo.put("yearPrice", appInfo.get("YEAR_PRICE"));
					mapPayInfo.put(planId, payInfo);
					//支付插件
					if(WapV3HomeController.mapChannelPayType.get(CHANNEL_NO)==null){
						List<PageData>   payPluginPDList=payPluginService.listPayPluginPDByChannelNo(pd);
						Map mapPayType =new HashMap();
						for(PageData payPluginPD:payPluginPDList){
							mapPayType.put(payPluginPD.getString("PLUGIN_TYPE"), payPluginPD.getString("PLUGIN_TYPE"));
						}
						if(payPluginPDList==null|| payPluginPDList.size()<=0){
							payPluginPDList=payPluginService.listPayPluginPD(pd);
							for(PageData payPluginPD:payPluginPDList){
								mapPayType.put(payPluginPD.getString("PLUGIN_TYPE"), payPluginPD.getString("PLUGIN_TYPE"));
							}
						}
						WapV3HomeController.mapChannelPayType.put(CHANNEL_NO, mapPayType);
						if(mapDefaultPayType.isEmpty()){
							mapDefaultPayType = mapPayType;
						}
					}	
			} else {
				planList = (List<PageData> )mapHomeData.get("PLAN"+CHANNEL_NO);
				planId = planList.get(0).getString("PLAN_ID");
			}
	
			String showType="";
			if(mapHomeData.get("columnDataList"+planId)==null||mapHomeData.get("columnId"+planId)==null){
				//List<PageData>  planList = planService.listAll(pd);
				//取得方案数据
				for(PageData plan:planList){
					//取得版块信息
					pd.put("PLAN_ID", plan.getString("PLAN_ID"));
					List<PageData> tabList = tabService.listTabs(pd);
					for(PageData tab:tabList){
						//取得栏目信息
						pd.put("TAB_ID", tab.getString("TAB_ID"));
						columnDataList=columnService.listColumns(pd);
						for(PageData column:columnDataList){
							//取得视频信息
							pd.put("COLUMN_ID", column.getString("COLUMN_ID"));
							columnId =column.getString("COLUMN_ID");
							//首页推荐类型
							if(ColumnDataTypeEnum.RecommendDataType.getKey()==(Integer)column.get("DATA_TYPE")){
								break;
							}
							
						}
						break;
					}
					break;
				}
			} else {
				columnId = (String)mapHomeData.get("columnId"+planId);
				columnDataList =(List<PageData>) mapHomeData.get("columnDataList"+planId);
			}
			mapHomeData.put("columnDataList"+planId, columnDataList);
			mapHomeData.put("columnId"+planId, columnId);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		

		//return  new ModelAndView("redirect:http://1024.enbozm.com/emovie/wapv3/listRecommendVideo/" +CHANNEL_NO +"/"+columnId );
		return  new ModelAndView("redirect:/wapv3/listRecommendVideo/" +CHANNEL_NO +"/"+columnId );
		//return  new ModelAndView("redirect:/wapv2/listColumnVideo/" +CHANNEL_NO +"/"+columnId );
		//素包切换
		//return  new ModelAndView("redirect:/wapvv/index/" +CHANNEL_NO);
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
