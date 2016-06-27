package com.fh.service.videocontent.video;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.PayData;
import com.fh.util.PageData;


@Service("payService")
public class PayService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/*
	* 新增
	*/
	public void savePay(PayData payData)throws Exception{
		dao.save("PayMapper.savePay", payData);
	}
	/*
	* 通过id获取数据
	*/
	public PageData findByOrderNo(PageData pd)throws Exception{
		return (PageData)dao.findForObject("PayMapper.findByOrderNo", pd);
	}
	
}

