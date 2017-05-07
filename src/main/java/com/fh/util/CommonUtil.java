package com.fh.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.NumberFormat;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.dom4j.DocumentException;

import com.fh.entity.system.User;



public class CommonUtil {
	public static String round2String(double data,int digits){
		   NumberFormat nf = NumberFormat.getNumberInstance();   
	       nf.setMaximumFractionDigits(digits);   
	      return nf.format(data);  
	}
	public static String round2String(float data,int digits){
		   NumberFormat nf = NumberFormat.getNumberInstance();   
	       nf.setMaximumFractionDigits(digits);   
	      return nf.format(data);  
	}
	public static long getStringNum(String str){
		long lRes = 0;
		try{
				
				String res ="";
				String regex = "\\d*";
	       Pattern p = Pattern.compile(regex);
	       Matcher m = p.matcher(str);
	       while(m.find()) {
	           if(!"".equals(m.group()))
	        	   res+=m.group();
	       }
	       lRes = Long.parseLong(res);
		}catch(Exception ex){
			
		}
	   return  lRes;
	}
	public static String getStringOfStr(String str){
		String res ="";
		try{
				String regex = "\\d*";
	       Pattern p = Pattern.compile(regex);
	       Matcher m = p.matcher(str);
	       while(m.find()) {
	           if(!"".equals(m.group()))
	        	   res+=m.group();
	           
	       }
	       res =  str.replace(res, "");
		}catch(Exception ex){
			
		}
	   return  res;
	}
	//检查是否是商务账号
	public static boolean checkChannelOwerUserRole(User user){
		if (user != null && user.getROLE_ID() != null) {
			int roleId = 0;
			try {
				roleId = Integer.parseInt(user.getROLE_ID());
			} catch (Exception ex) {

			}
			if ((roleId >= Const.CHANNEL_OWER_USER_ROLE_FROM) && (roleId <= Const.CHANNEL_OWER_USER_ROLE_TO)) {
				return true;
			}
		}
		return false;
	}
	//检查是否是商务账号
	public static String getRandomString(int min,int max,int len){
			 String strRet ="";
       Random random = new Random();
       for(int i=0;i<len;i++){
    	   int rand = random.nextInt(max)%(max-min+1) + min;
    	   strRet +=rand;
       }
       if(strRet.length() > len){
    	   strRet = strRet.substring(0,len-1);
       }
       return strRet;
	}
	public static int getRandomInt(int min,int max){
		Random random = new Random();
  return random.nextInt(max)%(max-min+1) + min;
}

public static  String postURL(String strURL,String jsonData){

	    
	    PrintWriter out = null;
        BufferedReader in = null;
	    HttpClient httpClient = new HttpClient();
	    PostMethod method = new PostMethod(strURL);
	    try {
	      if(jsonData != null && !"".equals(jsonData.trim())) {
	        RequestEntity requestEntity = new StringRequestEntity(jsonData,"application/json","UTF-8");
	        method.setRequestEntity(requestEntity);
	      }
	      method.releaseConnection();
	      httpClient.executeMethod(method);
	      String responses= method.getResponseBodyAsString();
	      return responses;

	    } catch (IOException e) {
	      e.printStackTrace();
	    }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return "" ;
     
	}
	public static  String  requestURL(String strURL){
	    String  responseMessage = "";
	     BufferedReader in = null;
	     StringBuffer sb = new StringBuffer();  
	    try{
			URL url = new URL(strURL);
			URLConnection conn = url.openConnection();
			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF-8"));
			String strData = null;
			while ((strData = in.readLine()) != null) {
				sb.append(strData);
			}
		} catch (Exception ex) {
			 ex.printStackTrace();

		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		responseMessage = sb.toString();

	    return responseMessage;
	}

	public static String doGet(String url)  {
		StringBuffer resultBuffer = new StringBuffer();
		try{
		URL localURL = new URL(url);
		URLConnection connection = localURL.openConnection();
		HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

		httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
		httpURLConnection.setRequestProperty("Content-Type", "application/json");

		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		
		String tempLine = null;

/*		if (httpURLConnection.getResponseCode() >= 300) {
			throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
		}
*/
		try {
			inputStream = httpURLConnection.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);

			while ((tempLine = reader.readLine()) != null) {
				resultBuffer.append(tempLine);
			}

		} finally {

			if (reader != null) {
				reader.close();
			}

			if (inputStreamReader != null) {
				inputStreamReader.close();
			}

			if (inputStream != null) {
				inputStream.close();
			}

		}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return resultBuffer.toString();
	

	}
	
    public static String getWebService(String webServiceUrl,String funName,Map<String,String> paramMap ) {  
        URL url;  
        BufferedReader bin = null;  
        StringBuilder result = new StringBuilder();  
        try {  
             webServiceUrl = webServiceUrl+ "/" +funName + "?";  
             boolean isFirst =true;
             if(paramMap !=null){
	     		for (String paramKey : paramMap.keySet()) {
	     			if(!isFirst){
	     				webServiceUrl+="&";
	     			}
	     			webServiceUrl+=paramKey+"=" + paramMap.get(paramKey);
	     			isFirst =false;
	    		}
             }
            url = new URL(webServiceUrl);  
            InputStream in = url.openStream(); // 请求  
            bin = new BufferedReader(new InputStreamReader(in, "UTF-8"));  
            String s = null;  
            while ((s = bin.readLine()) != null) {  
                result.append(s);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (null != bin) {  
                try {  
                    bin.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return result.toString();  
    }
	public static String getBasePath(HttpServletRequest request){
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort();
		basePath =basePath +request.getContextPath();
		return basePath;
	}
	   /** 
     * 执行一个HTTP POST请求，返回请求响应的HTML 
     * 
     * @param url         请求的URL地址 
     * @param params    请求的查询参数,可以为null 
     * @param charset 字符集 
     * @param pretty    是否美化 
     * @return 返回请求响应的HTML 
     */ 
    public static String doPost(String strURL,  Part[] parts) { 
	    PrintWriter out = null;
        BufferedReader in = null;
	    HttpClient httpClient = new HttpClient();
	    PostMethod method = new PostMethod(strURL);
	    try {
	      if(parts != null ) {
	          method.setRequestEntity(new MultipartRequestEntity(parts, method.getParams()));

	      }
	      method.releaseConnection();
	      httpClient.executeMethod(method);
	      String responses= method.getResponseBodyAsString(1024);
	      return responses;

	    } catch (IOException e) {
	      e.printStackTrace();
	    }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return "" ;
    } 
    public static String replaceDomain(String strURL,  String newDomain) { 
    	     int domainStart="http://".length();
    	     int domainEnd=0;
    	     if(strURL !=null){
    	    	 domainEnd= strURL.substring(domainStart).indexOf("/")+domainStart;
    	    	 if(domainEnd>0&& strURL.length()>=domainEnd){
        	    	 String srcDomain= strURL.substring(domainStart, domainEnd);
        	    	 strURL=strURL.replaceAll(srcDomain, newDomain);
    	    	 }

    	     }
    	     return strURL;
    }
		public static boolean isVidateUrl(String url){
			boolean isVidate =true;
			String checkUrl="";
			String strCheckValue="";
			//微信报毒检查
		 checkUrl="http://api.utop.cc/wx/?url=";
		 strCheckValue= doGet(checkUrl+url);
			if(!(strCheckValue !=null && strCheckValue.indexOf("OK")>=0)){
				isVidate =false;
			} 
			//QQ报毒检查
			checkUrl="http://api.utop.cc/jc/?url=";
			strCheckValue= doGet(checkUrl+url);
			if(!(strCheckValue !=null && strCheckValue.indexOf("OK")>=0)){
				isVidate =false;
			}
			return isVidate;
			
		}
	public static void main(String[] args) throws UnsupportedEncodingException, DocumentException {
/*		getStringOfStr("m_ios290");
		System.out.println(getRandomString(0,9,10));*/
		System.out.println(replaceDomain("http://1112.edaijinrong.cn/emovie/thirdpay/callbackPay","www.baidu.com"));
/*        Part[] parts = {  
       		 new StringPart("Sjt_TransID", "m_ios300320160812787117")
          }; 
	  String  acceptjson2=CommonUtil.doPost(Const.YL_PAY_ORDER_QUERY_URL, parts);
		String test =doGet("http://check.ylsdk.com?Sjt_TransID=m_ios300320160812787117");
		for(int i=0;i<50000;i++){
		
			System.out.println("i="+i);
		}*/
	}
}
