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
@RequestMapping(value="/wapvv")
public class WapV5HomeController extends BaseController {
	

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
	public static Map mapPayType =new HashMap();
	@RequestMapping(value="/index/{CHANNEL_NO}")
	public ModelAndView listV3Index(Page page,@PathVariable String CHANNEL_NO){
		//logBefore(logger, "startindex");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		List<PageData>  columnDataList =new ArrayList<PageData>();

		String columnId= "";
		try{
			pd = this.getPageData();
			pd.put("OS_TYPE", PlanTypeEnum.WapV5.getKey());
			
			if(WapV5HomeController.mapPayType.isEmpty()){
				List<PageData>   payPluginPDList=payPluginService.listPayPluginPD(pd);
				for(PageData payPluginPD:payPluginPDList){
					WapV5HomeController.mapPayType.put(payPluginPD.getString("PLUGIN_TYPE"), payPluginPD.getString("PLUGIN_TYPE"));
				}
			}		
			String showType="";
			if(mapHomeData.get("columnDataList")==null||mapHomeData.get("columnId")==null){
				List<PageData>  planList = planService.listAll(pd);
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
				columnId = (String)mapHomeData.get("columnId");
				columnDataList =(List<PageData>) mapHomeData.get("columnDataList");
			}

		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
		mapHomeData.put("columnDataList", columnDataList);
		mapHomeData.put("columnId", columnId);
		//return  new ModelAndView("redirect:http://1024.enbozm.com/emovie/wapv3/listRecommendVideo/" +CHANNEL_NO +"/"+columnId );
		return  new ModelAndView("redirect:/wapvv/listRecommendVideo/" +CHANNEL_NO +"/"+columnId );
		
		//return  new ModelAndView("redirect:/wapv2/listColumnVideo/" +CHANNEL_NO +"/"+columnId );
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
