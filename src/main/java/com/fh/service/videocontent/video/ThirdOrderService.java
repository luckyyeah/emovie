package com.fh.service.videocontent.video;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupportEx;
import com.fh.dao.DaoSupportEx2;
import com.fh.util.PageData;


@Service("thirdOrderService")
public class ThirdOrderService {

	@Resource(name = "daoSupportEx")
	private DaoSupportEx daoEx;
	
	@Resource(name = "daoSupportEx2")
	private DaoSupportEx2 daoEx2;	
	/*
	* 新增
	*/
	public void saveThirdOrder( Map<String,String> map)throws Exception{
		daoEx.save("ThirdOrderMapper.saveThirdOrder", map);
	}
	/*
	* 新增
	*/
	public void saveAndroidThirdOrder( Map<String,String> map)throws Exception{
		daoEx2.save("ThirdOrderMapper.saveThirdOrder", map);
	}
	
	/*
	* 修改
	*/
	public void edit(Map<String,String> map)throws Exception{
		daoEx.update("ThirdOrderMapper.edit", map);
	}
	/*
	* 修改
	*/
	public void editAndroid(Map<String,String> map)throws Exception{
		daoEx2.update("ThirdOrderMapper.edit", map);
	}
	/*
	* 通过id获取数据
	*/
	public PageData findByOrderNo(PageData pd)throws Exception{
		return (PageData)daoEx.findForObject("ThirdOrderMapper.findByOrderNo", pd);
	}
	/*
	* 通过id获取数据
	*/
	public PageData findAndroidByOrderNo(PageData pd)throws Exception{
		return (PageData)daoEx2.findForObject("ThirdOrderMapper.findByOrderNo", pd);
	}
	/*
	* 通过id获取数据
	*/
	public PageData findByWxOrderNo(PageData pd)throws Exception{
		return (PageData)daoEx.findForObject("ThirdOrderMapper.findByWxOrderNo", pd);
	}
	/*
	* 通过id获取数据
	*/
	public PageData findAndroidByWxOrderNo(PageData pd)throws Exception{
		return (PageData)daoEx2.findForObject("ThirdOrderMapper.findByWxOrderNo", pd);
	}
}

