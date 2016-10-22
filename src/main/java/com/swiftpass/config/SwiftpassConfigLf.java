package com.swiftpass.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * <一句话功能简述>
 * <功能详细描述>配置信息
 * 
 * @author  Administrator
 * @version  [版本号, 2014-8-29]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SwiftpassConfigLf {
    
    /**
     * 威富通交易密钥
     */
    public static String key ;
    
    /**
     * 威富通商户号
     */
    public static String mch_id;
    
    /**
     * 威富通商户号
     */
    public static String app_id;
    
    /**
     * 威富通请求url
     */
    public static String req_url;
    
    /**
     * 通知url
     */
    public static String notify_url;
    
    /**
     * 回调url
     */
    public static String callback_url;
    
    
    /**
     * 回调url
     */
    public static String callback_urlv2;
    
    /**
     * 回调url
     */
    public static String callback_urlv3;

    /**
     * 支付页名称body
     */
    public static String body;
    /**
     * 默认金额
     */
    public static String total_fee;
    static{
        Properties prop = new Properties();   
        InputStream in = SwiftpassConfig.class.getResourceAsStream("/configlf.properties");   
        try {   
            prop.load(in);   
            key = prop.getProperty("key").trim();   
            mch_id = prop.getProperty("mch_id").trim();  
            app_id = prop.getProperty("app_id").trim();   
            req_url = prop.getProperty("req_url").trim();   
            notify_url = prop.getProperty("notify_url").trim(); 
            callback_url = prop.getProperty("callback_url").trim();  
            callback_urlv2 = prop.getProperty("callback_urlv2").trim();  
            callback_urlv3 = prop.getProperty("callback_urlv3").trim();              
            body= prop.getProperty("body").trim();  
            total_fee= prop.getProperty("total_fee").trim();  
        } catch (IOException e) {   
            e.printStackTrace();   
        } 
    }
}
