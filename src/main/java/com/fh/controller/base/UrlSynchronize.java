package com.fh.controller.base;

import org.apache.log4j.Logger;

import com.alipay.config.AlipayConfig;
import com.fh.controller.beibeipay.BeiBeiPayConfig;
import com.fh.util.CommonUtil;
import com.fh.util.DateUtil;
import com.fh.util.FileUtil;
import com.heepay.HeepayConfig;
import com.swiftpass.config.SwiftpassConfig;
import com.swiftpass.config.SwiftpassConfigLf;
import com.swiftpass.config.SwiftpassConfigYr;
import com.ylpay.YlpayConfig;

public class UrlSynchronize  implements Runnable{  
	static Logger log = Logger.getRootLogger();  
	public  static boolean isRun =false;
	public  static String  basePath="";
	public  void run()  
	{  
		try {
			while(true){
				try{
					
					if(!CommonUtil.isVidateUrl(BaseController.domain)){
						BaseController.urlList = FileUtil.readFileToStringList("/data/urllist/h5url");
						int index=0;
		        	//休眠1分钟再请求
		        	Thread.sleep(1000*60*2);
						for(String srcUrl:BaseController.urlList ){
							
								String url=srcUrl;
								if(url!=null && "".equals(url.trim())){
									continue;
								}
			        	//去掉www.或者http://开头的字符串
			        	url = url.replace("wwww.", "").replace("http://", "");
			        	url=DateUtil.getDayTime()+"."+url;
			        	if(CommonUtil.isVidateUrl(url)){
			        		BaseController.domain=url;
			        		log.info("domain="+BaseController.domain);
			        	try{
			        		//替换域名
			        		//微付通
			        		SwiftpassConfig.notify_url=CommonUtil.replaceDomain(SwiftpassConfig.notify_url, BaseController.domain);
			        		SwiftpassConfig.callback_url =CommonUtil.replaceDomain(SwiftpassConfig.callback_url, BaseController.domain);
			        		SwiftpassConfig.callback_urlv2 =CommonUtil.replaceDomain(SwiftpassConfig.callback_urlv2, BaseController.domain);
			        		SwiftpassConfig.callback_urlv3 =CommonUtil.replaceDomain(SwiftpassConfig.callback_urlv3, BaseController.domain);
			        		//乐付
			        		SwiftpassConfigLf.notify_url=CommonUtil.replaceDomain(SwiftpassConfigLf.notify_url, BaseController.domain);
			        		SwiftpassConfigLf.callback_url =CommonUtil.replaceDomain(SwiftpassConfigLf.callback_url, BaseController.domain);
			        		SwiftpassConfigLf.callback_urlv2 =CommonUtil.replaceDomain(SwiftpassConfigLf.callback_urlv2, BaseController.domain);
			        		SwiftpassConfigLf.callback_urlv3 =CommonUtil.replaceDomain(SwiftpassConfigLf.callback_urlv3, BaseController.domain);
			        		
			        		//广州悦人微
			        		SwiftpassConfigYr.notify_url=CommonUtil.replaceDomain(SwiftpassConfigYr.notify_url, BaseController.domain);
			        		SwiftpassConfigYr.callback_url =CommonUtil.replaceDomain(SwiftpassConfigYr.callback_url, BaseController.domain);
			        		SwiftpassConfigYr.callback_urlv2 =CommonUtil.replaceDomain(SwiftpassConfigYr.callback_urlv2, BaseController.domain);
			        		SwiftpassConfigYr.callback_urlv3 =CommonUtil.replaceDomain(SwiftpassConfigYr.callback_urlv3, BaseController.domain);
			        		//广州悦人微
			        		YlpayConfig.notify_url=CommonUtil.replaceDomain(YlpayConfig.notify_url, BaseController.domain);
			        		YlpayConfig.callback_url =CommonUtil.replaceDomain(YlpayConfig.callback_url, BaseController.domain);
			        		YlpayConfig.callback_urlv2 =CommonUtil.replaceDomain(YlpayConfig.callback_urlv2, BaseController.domain);
			        		YlpayConfig.callback_urlv3 =CommonUtil.replaceDomain(YlpayConfig.callback_urlv3, BaseController.domain);
			        					        		
			        		//海豚
			        		HeepayConfig.notify_url=CommonUtil.replaceDomain(HeepayConfig.notify_url, BaseController.domain);
			        		HeepayConfig.callback_url =CommonUtil.replaceDomain(HeepayConfig.callback_url, BaseController.domain);
			        		HeepayConfig.callback_urlv2 =CommonUtil.replaceDomain(HeepayConfig.callback_urlv2, BaseController.domain);
			        		HeepayConfig.callback_urlv3 =CommonUtil.replaceDomain(HeepayConfig.callback_urlv3, BaseController.domain);
			        		
			        		
									//贝贝付
			        		BeiBeiPayConfig.notify_url=CommonUtil.replaceDomain(BeiBeiPayConfig.notify_url, BaseController.domain);
			        		BeiBeiPayConfig.callback_url =CommonUtil.replaceDomain(BeiBeiPayConfig.callback_url, BaseController.domain);
			        		BeiBeiPayConfig.callback_urlv2 =CommonUtil.replaceDomain(BeiBeiPayConfig.callback_urlv2, BaseController.domain);
			        		BeiBeiPayConfig.callback_urlv3 =CommonUtil.replaceDomain(BeiBeiPayConfig.callback_urlv3, BaseController.domain);
					        		
									//星罗支付宝
			        		AlipayConfig.return_url =CommonUtil.replaceDomain(AlipayConfig.return_url, BaseController.domain);
			        		AlipayConfig.return_urlv2 =CommonUtil.replaceDomain(AlipayConfig.return_urlv2, BaseController.domain);
			        		AlipayConfig.return_urlv3 =CommonUtil.replaceDomain(AlipayConfig.return_urlv3, BaseController.domain);
		        		}catch(Exception ex){
		        				ex.printStackTrace();
								}
			        		break;
			        	} else {
				        	log.info("error url=" + url);
				        	//BaseController.urlList.remove(srcUrl);
				        }
			        	//休眠1分钟再请求
			        	Thread.sleep(1000*60*2);
			        	index++;
			        }
						//检查主域名
						checkMainDomain();
					}
	        //FileUtil.WriteToFile(BaseController.urlList,"/data/urllist/url");
					//休眠6分钟检查一次
					Thread.sleep(1000*60*10);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}  
  public void checkMainDomain() throws InterruptedException{
	  
			for(int i=BaseController.urlList.size()-1 ;i>=0;i-- ){
				String url=BaseController.urlList.get(i);
				if(url!=null && "".equals(url.trim())){
					continue;
				}
				//去掉www.或者http://开头的字符串
				url = url.replace("wwww.", "").replace("http://", "").trim();
				url= url;
				if(!CommonUtil.isVidateUrl(url)){
			    	log.info("error url=" + url);
			    	BaseController.urlList.remove(i);
			    }
				//休眠1分钟再请求
				Thread.sleep(1000*60*2);
			}
			FileUtil.WriteToFile(BaseController.urlList,"/data/urllist/h5url");
  }
	public String startOrderSyn(){
		if(!isRun){
			try{
				(new Thread(new UrlSynchronize())).start();  
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
		}
    this.isRun=true;
		return "1";

	}
}
