package com.fh.service.videocontent.column;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;


@Service("columnService")
public class ColumnService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("ColumnMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("ColumnMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("ColumnMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ColumnMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ColumnMapper.listAll", pd);
	}
	/*
	*列表(条件部分)
	*/
	public List<PageData> listColumns(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ColumnMapper.listColumns", pd);
	}	
	/*
	*列表(条件部分)
	*/
	public List<PageData> listCoversByPage(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ColumnMapper.listCoversByPage", pd);
	}	
	/*
	*列表(条件部分)
	*/
	public List<PageData> listRecommendCovers(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ColumnMapper.listRecommendCovers", pd);
	}	
	/*
	*列表(条件部分)
	*/
	public PageData getCoverCnt(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ColumnMapper.getCoverCnt", pd);
	}
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ColumnMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("ColumnMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

