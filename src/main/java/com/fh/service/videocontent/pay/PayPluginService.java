package com.fh.service.videocontent.pay;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.util.PageData;


@Service("payPluginService")
public class PayPluginService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	


	/*
	*列表
	*/
	public List<PageData> listPayPluginPD(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("PayPluginMapper.listPayPluginPD", pd);
	}
	/*
	*列表(支付关联渠道)
	*/
	public List<PageData> listPayPluginPDByChannelNo(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("PayPluginMapper.listPayPluginPDByChannelNo", pd);
	}
}

