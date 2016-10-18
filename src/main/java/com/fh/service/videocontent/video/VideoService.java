package com.fh.service.videocontent.video;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;


@Service("videoService")
public class VideoService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("VideoMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("VideoMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("VideoMapper.edit", pd);
	}
	/*
	* 修改
	*/
	public void updateVipFlag(PageData pd)throws Exception{
		dao.update("VideoMapper.updateVipFlag", pd);
	}
	/*
	* 修改
	*/
	public void updateBannerFlag(PageData pd)throws Exception{
		dao.update("VideoMapper.updateBannerFlag", pd);
	}
	/*
	* 修改
	*/
	public void updateHomeFlag(PageData pd)throws Exception{
		dao.update("VideoMapper.updateHomeFlag", pd);
	}
	/*
	* 修改
	*/
	public void updateCompetitiveFlag(PageData pd)throws Exception{
		dao.update("VideoMapper.updateCompetitiveFlag", pd);
	}
	/*
	* 修改
	*/
	public void updateRecommendFlag(PageData pd)throws Exception{
		dao.update("VideoMapper.updateRecommendFlag", pd);
	}
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("VideoMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VideoMapper.listAll", pd);
	}
	/*
	*列表(条件部分)
	*/
	public List<PageData> listVideos(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VideoMapper.listVideos", pd);
	}	
	/*
	*列表(条件部分)
	*/
	public List<PageData> listTryVideos(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VideoMapper.listTryVideos", pd);
	}	
	/*
	*列表(条件部分)
	*/
	public List<PageData> listVideosByPage(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VideoMapper.listVideosByPage", pd);
	}	
	/*
	*列表(条件部分)
	*/
	public PageData getVideoCnt(PageData pd)throws Exception{
		return (PageData)dao.findForObject("VideoMapper.getVideoCnt", pd);
	}
	/*
	*列表(条件部分)
	*/
	public List<PageData> listRecommendVideos(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VideoMapper.listRecommendVideos", pd);
	}	
	/*
	*列表(条件部分)
	*/
	public List<PageData> listRecommendVideosV3(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VideoMapper.listRecommendVideosV3", pd);
	}	
	/*
	*列表(条件部分)
	*/
	public List<PageData> listInvalidVideo(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VideoMapper.listInvalidVideo", pd);
	}	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("VideoMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("VideoMapper.deleteAll", ArrayDATA_IDS);
	}
	
}

