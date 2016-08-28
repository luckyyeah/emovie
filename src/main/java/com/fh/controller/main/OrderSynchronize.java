package com.fh.controller.main;
import java.io.PrintWriter;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.service.videocontent.video.ThirdOrderService;
import com.fh.util.CommonUtil;
import com.fh.util.Const;
import com.fh.util.PageData;

@Controller
@RequestMapping(value="/ordersyn")
public class OrderSynchronize extends BaseController implements Runnable{  

	public  static boolean isRun =false;
	public  static String  basePath="";
	public  void run()  
	{  
		try {
			while(true){
				try{
					CommonUtil.doGet(basePath+Const.ORDER_SYN_URL);
					Thread.sleep(1000*60);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}  

	@RequestMapping(value="/startOrderSyn")
	public String startOrderSyn(){
		if(!isRun){
			this.basePath= CommonUtil.getBasePath(this.getRequest());
			try{
				(new Thread(new OrderSynchronize())).start();  
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
		}
    this.isRun=true;
		return "1";

	}
}
