package com.jyblife.logic.wl.ops.common.contants;

/**
 * 常量类
 *
 * @author
 */
public class Constants {

    //系统编码
    public static final String SYSTEM_CODE = "61";
    //管理平台模块编码
    public static final String MANAGE_MODULE_CODE = "02";
    //对外服务接口模块编码
    public static final String EXTERNAL_MODULE_CODE = "01";

    public static final String SYSTEM_MANAGE_MODULE_CODE = SYSTEM_CODE + MANAGE_MODULE_CODE;

    public static final String SYSTEM_EXTERNAL_MODULE_CODE = SYSTEM_CODE + EXTERNAL_MODULE_CODE;

    /**
     * 登录token key定义前缀
     */
    public static String USER_LOGIN_TOKEN_REDIS_KEY_ = "ops:user:login:token:";
    /**
     * 用户token失效时间 一个小时
     */
    public static final Long USER_LOGIN_TOKEN_EXPIRE_TIME = (long) (60 * 60);
    
    //用户信息
    public static String USER_INFO_REDIS_KEY_ = "ops:user:info:id:";

    /**
     * 用户cookie session名称
     */
    public static final String USER_LOGIN_COOKIE_KEY = "PHPSESSID";

    public static final Integer page = 1;

    public static final Integer pageSize = 10;


    public static final String ENABLE_TEXT = "启用";

    public static final String DISABLE_TEXT = "禁用";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String OIL_PRICE_APPLY_ATTACHMENT_PATH = "/oil_price_apply/";
    
    public static final String OIL_STATION_APPLY_ATTACHMENT_PATH = "/oil_station_apply/";
    
    public static final String OIL_COMPANY_APPLY_ATTACHMENT_PATH = "/oil_company/";
    
    //验证码，redis key前缀
    public static final String OPS_PHONE_CODE_PREFIX = "ops:phone:code:";
    
    //手机验证码每天限制5次发送  redis key前缀
    public static final String OPS_PHONE_LIMIT_CODE_PREFIX = "ops:phone:limit:code:";
    
    //订单编码前缀
    public static final String ORDER_CODE_PREFIX = "D";
    
    public static final String OPS_ORDER_CODE_PREFIX = "ops:order:code:"; 
    
    //油价申请编码前缀
    public static final String PRICE_APPLY_CODE_PREFIX = "JG";
    //邮件申请编码生成 redis key前缀
    public static final String OPS_OIL_PRICE_APPLY_CODE_PREFIX = "ops:oil_price_apply:code:"; 
    
    //企业额度限制
    public static final String LOGISTICS_QUOTA_LIMIT_CODE_PREFIX = "WL";
    public static final String OPS_LOGISTICS_QUOTA_LIMIT_CODE_PREFIX = "ops:logistics_quota_limit:code:"; 
    
    /**
     * 企业额度redis锁前缀
     */
    public static String LOGISTICS_COMPANY_QUOTA_LOCK_PREFIX_ = "ops:logistics:company:quota:lock:";
    /**
     * 车辆额度redis锁前缀
     */
    public static String LOGISTICS_VEHICLE_QUOTA_LOCK_PREFIX_ = "ops:logistics:vehicle:quota:lock:";
    /**
     * 等待时长
     */
    public static int REDIS_TRY_LOCK_WAIT_TIME = 3; //单位：秒
    /**
     * 自动释放锁时长
     */
    public static int REDIS_LOCK_LEASE_TIME = 10; //单位：秒

}
