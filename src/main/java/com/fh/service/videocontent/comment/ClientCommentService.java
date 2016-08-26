package com.fh.service.videocontent.comment;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.PayData;
import com.fh.util.PageData;


@Service("clientCommentService")
public class ClientCommentService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	

	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("ClientCommentMapper.save", pd);
	}
	/*
	* 新增
	*/
	public void saveIdea(PageData pd)throws Exception{
		dao.save("ClientCommentMapper.saveIdea", pd);
	}
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("ClientCommentMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("ClientCommentMapper.edit", pd);
	}
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ClientCommentMapper.findById", pd);
	}	
	/*
	* 获取用户留言信息
	*/
	public List<PageData> listClientCommentInfo(PageData pd)throws Exception{
		return (List<PageData> )dao.findForList("ClientCommentMapper.listClientCommentInfo", pd);
	}	
	/*
	* 获取用户留言信息
	*/
	public List<PageData> listClientComment(PageData pd)throws Exception{
		return (List<PageData> )dao.findForList("ClientCommentMapper.listClientComment", pd);
	}	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("ClientCommentMapper.deleteAll", ArrayDATA_IDS);
	}
}

