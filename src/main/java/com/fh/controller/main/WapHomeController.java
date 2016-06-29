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
@RequestMapping(value="/wap")
public class WapHomeController extends BaseController {
	

	@Resource(name="planService")
	private PlanService planService;
	
	@Resource(name="tabService")
	private TabService tabService;
	
	@Resource(name="columnService")
	private ColumnService columnService;
	
	@Resource(name="videoService")
	private VideoService videoService;
	
	public static Map mapHomeData =new HashMap();
	/**
	 * 列表
	 */
	@RequestMapping(value="/index/{CHANNEL_NO}")
	public ModelAndView listIndex(Page page,@PathVariable String CHANNEL_NO){
		logBefore(logger, "startindex");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		List<PageData>  columnDataList =new ArrayList<PageData>();

		String columnId= "";
		try{
			pd = this.getPageData();
			pd.put("OS_TYPE", PlanTypeEnum.Wap.getKey());
			List<PageData>  planList = planService.listAll(pd);

			String showType="";
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
						break;
						
					}
					break;
				}
				break;
			}

		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		mapHomeData.put("columnDataList", columnDataList);
		return  new ModelAndView("redirect:/wapmovie/listColumnVideo/" +CHANNEL_NO +"/"+columnId );
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