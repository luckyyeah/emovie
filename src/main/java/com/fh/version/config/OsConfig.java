package com.fh.version.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.fh.enums.PlanTypeEnum;


/**
 * <一句话功能简述>
 * <功能详细描述>配置信息
 * 
 * @author  Administrator
 * @version  [版本号, 2014-8-29]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OsConfig {
    

    public static int osType =-1;
    /**
     * android os
     */
    private static String OS_ANDROID_DB="rdsg818dzgn21033h322o.mysql.rds.aliyuncs.com";
    /**
     * IOS os
     */
    private static String OS_IOS_DB="rdsk8sjiuu87b5821a35o.mysql.rds.aliyuncs.com";

    private static String OS_WAP_DB="rdsk8sjiuu87b5821a35o.mysql.rds.aliyuncs.com";
    
    /**
     * DB URL
     */
    private static String DB_URl="";
    
    static{
        Properties prop = new Properties();   
        InputStream in = OsConfig.class.getResourceAsStream("/dbconfig.properties");   
        try {   
            prop.load(in);   
            DB_URl = prop.getProperty("urlEx").trim();   
            if(DB_URl.indexOf(OS_IOS_DB)>0){
            	osType=PlanTypeEnum.Ios.getKey();
            }
/*            if(DB_URl.indexOf(OS_ANDROID_DB)>0){
            	osType=PlanTypeEnum.Android.getKey();
            }*/
            if(DB_URl.indexOf(OS_WAP_DB)>0){
            	osType=PlanTypeEnum.Wap.getKey();
            }

        } catch (IOException e) {   
            e.printStackTrace();   
        } 
    }
}
