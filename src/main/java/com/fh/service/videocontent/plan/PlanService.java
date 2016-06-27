package com.fh.service.videocontent.plan;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;


@Service("planService")
public class PlanService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("PlanMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("PlanMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("PlanMapper.edit", pd);
	}
	/*
	* 复制
	*/
	public void copyPlan(PageData pd)throws Exception{
		dao.delete("PlanMapper.copyPlan", pd);
	}
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("PlanMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("PlanMapper.listAll", pd);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAllPlan()throws Exception{
		return (List<PageData>)dao.findForList("PlanMapper.listAllPlan", null);
	}
	/*
	*列表(渠道关联方案)
	*/
	public List<PageData> listPlanByChannelId(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("PlanMapper.listPlanByChannelId", pd);
	}
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("PlanMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("PlanMapper.deleteAll", ArrayDATA_IDS);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData getAppInfo(PageData pd)throws Exception{
		PageData pdResult = (PageData)dao.findForObject("PlanMapper.getAppInfo", pd);
		if(pdResult ==null){
			pdResult =new PageData();	
			pdResult.put("PRICE", 48);
		}
		return pdResult;
	}

}

