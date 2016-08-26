package com.fh.controller.beibeipay;
 
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
public class BeiBeiPayConfig {
    
    /**
     * 交易密钥
     */
    public static String key ;
    
    /**
     * 商户号
     */
    public static String agent_id;
    
    /**
     * 请求url
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
     * 是否使用手机端微信支付，1=是，微信扫码支付不用传本参数
     */
    public static String is_phone;
    /**
     * 1（默认值）=使用微信公众号支付，0=使用wap微信支付
     */
    public static String is_frame;
    /**
     * 默认金额
     */
    public static String total_fee;
    
    /**
     * 30，（数据类型：int）
     */
    public static String pay_type;
    
    /**
     * 产品数量,长度最长20字符（不参加签名）
     */
    public static String goods_num;
    
    
    /**
     * 商品名称, 长度最长50字符，不能为空（不参加签名）
     */
    public static String goods_name;
 
    /**
     * 商户自定义 原样返回,长度最长50字符，可以为空。（不参加签名）
     */
    public static String remark;
    /**
     * 支付说明, 长度50字符（不参加签名）
     */
    public static String goods_note;
    static{
        Properties prop = new Properties();   
        InputStream in = BeiBeiPayConfig.class.getResourceAsStream("/beibeipayconfig.properties");   
        try {   
            prop.load(in);   
            key = prop.getProperty("key").trim();   
            agent_id = prop.getProperty("agent_id").trim();   
            req_url = prop.getProperty("req_url").trim();   
            notify_url = prop.getProperty("notify_url").trim(); 
            callback_url = prop.getProperty("callback_url").trim();  
            is_phone= prop.getProperty("is_phone").trim();  
            is_frame= prop.getProperty("is_frame").trim();  
            total_fee= prop.getProperty("total_fee").trim(); 
            pay_type= prop.getProperty("pay_type").trim();  
            goods_num= prop.getProperty("goods_num").trim();  
            goods_name= prop.getProperty("goods_name").trim();  
            remark= prop.getProperty("remark").trim();  
            goods_note= prop.getProperty("goods_note").trim();  
        } catch (IOException e) {   
            e.printStackTrace();   
        } 
    }
}
