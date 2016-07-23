package com.viapay;

import java.util.SortedMap;
import java.util.TreeMap;

import com.alibaba.fastjson.JSON;
import com.fh.entity.ViaPayData;
import com.fh.util.CommonUtil;
import com.swiftpass.util.SignUtils;

public class ViaPayUtils {
	 public static 	String getPayUrl(String fee,String orderNo,String imsi,String deviceCode,String channelNo){
				String url=ViaPayConfig.req_url;
				String sign= ViaPayConfig.key + ViaPayConfig.package_id+fee;
				sign=MD5.sign(sign);
				SortedMap<String,String> map =new TreeMap<String,String>();
				map.put("packageId", ViaPayConfig.package_id);
				map.put("fee", fee);
				map.put("channelOrderId", orderNo);
				map.put("returnUrl", ViaPayConfig.notify_url+"/"+channelNo+"/"+orderNo+"/"+fee);
				map.put("payType", ViaPayConfig.pay_type);
				map.put("platform", ViaPayConfig.plat_form);
				map.put("imsi",imsi);
				map.put("isSupportReturnUrl","0");
				map.put("deviceCode",deviceCode);
				map.put("sign",sign);
				url+="?"+SignUtils.payParamsToString(map);
				return url;
			}
		    public static void main(String[] args) {

		      	   String strURL =getPayUrl("5","201601010001","460025017331694","351694060494064","256");
		      	 String acceptjson =CommonUtil.requestURL(strURL);
		      			ViaPayData appUserData=  JSON.parseObject(acceptjson, ViaPayData.class);
		      	   System.out.println(appUserData);
		          
		      }
}
