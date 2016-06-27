package com.fh.service.videocontent.video;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.PayData;
import com.fh.util.PageData;


@Service("thirdOrderService")
public class ThirdOrderService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/*
	* 新增
	*/
	public void saveThirdOrder( Map<String,String> map)throws Exception{
		dao.save("ThirdOrderMapper.saveThirdOrder", map);
	}
	/*
	* 通过id获取数据
	*/
	public PageData findByOrderNo(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ThirdOrderMapper.findByOrderNo", pd);
	}
	
}

