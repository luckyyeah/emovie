package com.fh.util;

import org.springframework.context.ApplicationContext;
/**
 * 项目名称：
 * @author:fh
 * 
*/
public class Const {
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	public static final String SESSION_USER = "sessionUser";
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String SESSION_menuList = "menuList";			//当前菜单
	public static final String SESSION_allmenuList = "allmenuList";		//全部菜单
	public static final String SESSION_QX = "QX";
	public static final String SESSION_userpds = "userpds";			
	public static final String SESSION_USERROL = "USERROL";				//用户对象
	public static final String SESSION_USERNAME = "USERNAME";			//用户名
	public static final String TRUE = "T";
	public static final String FALSE = "F";
	public static final String LOGIN = "/login_toLogin.do";				//登录地址
	public static final String SYSNAME = "admin/config/SYSNAME.txt";	//系统名称路径
	public static final String PAGE	= "admin/config/PAGE.txt";			//分页条数配置路径
	public static final String EMAIL = "admin/config/EMAIL.txt";		//邮箱服务器配置路径
	public static final String SMS1 = "admin/config/SMS1.txt";			//短信账户配置路径1
	public static final String SMS2 = "admin/config/SMS2.txt";			//短信账户配置路径2
	public static final String FWATERM = "admin/config/FWATERM.txt";	//文字水印配置路径
	public static final String IWATERM = "admin/config/IWATERM.txt";	//图片水印配置路径
	public static final String WEIXIN	= "admin/config/WEIXIN.txt";	//微信配置路径
	public static final String CKPLAY	= "admin/config/CKPLAY.txt";	//CKPlay配置路径	
	public static final String WAPPLAY	= "admin/config/WAPPLAY.txt";	//Wap播放器路径	
	public static final String FILEPATHIMG = "uploadFiles/uploadImgs/";	//图片上传路径
	public static final String FILEPATHFILE = "uploadFiles/file/";		//文件上传路径
	public static final String FILEPATHTWODIMENSIONCODE = "uploadFiles/twoDimensionCode/"; //二维码存放路径
	public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(upload)|(code)|(app)|(weixin)|(static)|(main)|(index)|(movie)|(wapmovie)|(ylpay)|(wapv2)|(wapv3)|(wapv4)|(wapvv)|(tuku)|(thirdpay)|(viapay)|(alipay)|(bbpay)|(dxpay)).*";	//不对匹配该值的访问路径拦截（正则）
	
	
	public static ApplicationContext WEB_APP_CONTEXT = null; //该值会在web容器启动时由WebAppContextListener初始化
	
	/**
	 * APP Constants
	 */
	//app注册接口_请求协议参数)
	public static final String[] APP_REGISTERED_PARAM_ARRAY = new String[]{"countries","uname","passwd","title","full_name","company_name","countries_code","area_code","telephone","mobile"};
	public static final String[] APP_REGISTERED_VALUE_ARRAY = new String[]{"国籍","邮箱帐号","密码","称谓","名称","公司名称","国家编号","区号","电话","手机号"};
	
	//app根据用户名获取会员信息接口_请求协议中的参数
	public static final String[] APP_GETAPPUSER_PARAM_ARRAY = new String[]{"USERNAME"};
	public static final String[] APP_GETAPPUSER_VALUE_ARRAY = new String[]{"用户名"};
	

	public static final  String UPLOAD_PATH = "/data/webapps/me/upload/";
	
	public static final  String ACCESS_PATH = "upload/";
	//展示普通类型
	public static final  String SHOW_TYPE_NOMAL = "1";
	//展示普通类型
	public static final  int TYPE_SECOND_LEVERL = 4;
	
	//是banner
	public static final  String BANNER_FLAG = "1";
	//是HOME
	public static final  String HOME_FLAG = "1";
	public static final  int HOME_VIDEO_CNT = 6;
	
	public static final  String RECOMMEND_FLAG = "1";
	public static final  int RECOMMEND_VIDEO_CNT = 4;
	public static final  String COMPETITIVE_FLAG = "1";
	//是HOME
	public static final  String VIP_FLAG = "1";
	
	//是HOME
	public static final  String TRY_FLAG = "2";
	
	public static final  int PAGE_SZIE = 24;
	
	public static final  int IMG_PAGE_SZIE = 9;
	
	public static final  int IMG_MAX_PAGE_SZIE = 100;
	
	public static final  int APP_PAGE_SZIE = 10;

    public static final String WX_APP_ID = "wx09aae063a26e6dfc";

    public static final String WX_APP_SECRET = "aae9d6f5ef14832170ff7cf33ce035b8";
    public static final String WX_PARTNER_KEY = "00199001021375811644915724170800";
    public static final String WX_MCH_ID = "1301546601";
    public static final String WX_BILL_URL = "https://api.mch.weixin.qq.com/pay/downloadbill";
    
    public static final  String HIDDEN_COLUMN = "HIDDEN_COLUMN";
    
    public static final  int ACCESSS_INTERVAL_TIME = 3600;
    //访问次数
    public static final  int ACCESSS_TIMES =10000;  
    public static final  int REPORT_TIME_CNT =24;  
    //商务角色
    public static final int CHANNEL_OWER_USER_ROLE_FROM =10; 
    public static final int CHANNEL_OWER_USER_ROLE_TO =99; 
    //APP上架
    public static final int APP_STATUS_ON =1; 
    //退出推送标示
    public static final int EXIT_PUSH_FLAG_ON =1; 
    
    //退出推送标示
    public static final int CLIENT_COMMENT_CNT =50; 
    //des加密秘钥
    public static final String DES_ENCRYPT_KEY ="!@%^m#$l"; 
    
    //搜索返回结果条数
    public static final int SEARCH_MAX_NUM =10; 
    
    //是搜索结果
    public static final int SEARCH_RESULT_STATUS =1; 
    
    //搜索TOP结果
    public static final int TOP_MAX_NUM =10; 
    //普通VIP
    public static final int COMMON_VIP =2; 
    
    //IOS渠道
    public static final String IOS_CHANNEL_HREAD ="m_ios"; 
    //惠付通商户可通过此接口查询单据支付状态，数据将以XML形式同步返回。
    public static final String HEE_PAY_ORDER_QUERY_URL ="https://pay.heepay.com/Phone/SDK/PayQuery.aspx";
    //惠付通商户可通过此接口查询单据支付状态，数据将以XML形式同步返回。
    public static final String YL_PAY_ORDER_QUERY_URL ="http://check.ylsdk.com"; 
    //订单同步接口
    public static final String ORDER_SYN_URL ="/wapv2/orderSyn"; 
    
}
