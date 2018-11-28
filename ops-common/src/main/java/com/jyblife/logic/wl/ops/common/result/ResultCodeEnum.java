package com.jyblife.logic.wl.ops.common.result;

import com.jyblife.logic.wl.ops.common.contants.Constants;

/**
 * 错误编码=系统编号(..)+模块编号(..)+错误码类型+错误码编号
 */
public enum ResultCodeEnum {

	SUCCESS("0", "操作成功"),

    FAIL("-1", "操作失败"),

    //管理平台错误码
    PARAM_ERROR(Constants.SYSTEM_MANAGE_MODULE_CODE + "100", "参数错误"),
    PARAM_BODY_ERROR(Constants.SYSTEM_MANAGE_MODULE_CODE+ "101", "输入参数错误"),
    
    USER_NOT_EXIST(Constants.SYSTEM_MANAGE_MODULE_CODE + "102", "用户不存在"),
    REQUEST_USER_CENTER_ERROR(Constants.SYSTEM_MANAGE_MODULE_CODE + "103", "请求用户中心失败"),
    LOGIN_TIME_OUT(Constants.SYSTEM_MANAGE_MODULE_CODE + "104", "您当前未登录，请登录后重试"),
    NEW_CONFIRM_PASSWORD_ERROR(Constants.SYSTEM_MANAGE_MODULE_CODE + "105", "新密码与确认密码不一致!"),
    ORGINAL_PASSWORD_ERROR(Constants.SYSTEM_MANAGE_MODULE_CODE + "106", "原始密码不一致不一致!"),
    MODULE_DEL_HAS_CHILD_TIP(Constants.SYSTEM_MANAGE_MODULE_CODE + "107", "请先移除子模块"),
    ROLE_NAME_EXIST(Constants.SYSTEM_MANAGE_MODULE_CODE + "108", "当前角色已经存在，不能重复添加！"),
    USER_PASSWORD_ERROR(Constants.SYSTEM_MANAGE_MODULE_CODE + "109", "密码错误"),
    MODULE_CODE_EXIST(Constants.SYSTEM_MANAGE_MODULE_CODE + "110", "当前权限码的模块已经存在，请重新填写！！"),
    USER_NAME_EXIST(Constants.SYSTEM_MANAGE_MODULE_CODE + "111", "当前用户名的用户已经存在，请重新填写用户名！"),
    OILGOODS_NAME_EXIST(Constants.SYSTEM_MANAGE_MODULE_CODE + "112", "商品名称已被占用！"),
    VEHICLE_NOT_EXIST(Constants.SYSTEM_MANAGE_MODULE_CODE + "113", "当前车辆不存在！"),
    OILGOODS_NOT_EXIST(Constants.SYSTEM_MANAGE_MODULE_CODE + "114", "当前油品不存在！"),
    LOGISTICSCOMPANY_NOT_EXIST(Constants.SYSTEM_MANAGE_MODULE_CODE + "115", "当前油企不存在！"),
    DRIVER_NOT_EXIST(Constants.SYSTEM_MANAGE_MODULE_CODE + "116", "当前司机不存在！"),
    VEHICLE_QUOTA_LIMIT_NOT_EXIST(Constants.SYSTEM_MANAGE_MODULE_CODE + "117", "车辆每日不存在限额！"),
    LOGISTICS_QUOTA_LIMIT_NOT_EXIST(Constants.SYSTEM_MANAGE_MODULE_CODE + "118", "车企每日不存在限额！"),
    PRICEAPPLY_ATTACHMENT_NOT_EXIST(Constants.SYSTEM_MANAGE_MODULE_CODE + "119", "油品价格文件不存在！"),
    USER_DISABLED(Constants.SYSTEM_MANAGE_MODULE_CODE + "120", "用户已被禁用"),
    PERMISSION_ERROR(Constants.SYSTEM_MANAGE_MODULE_CODE + "121", "你无权操作当前页面及功能！"),
    OILCOMPANY_NAME_ERROR(Constants.SYSTEM_MANAGE_MODULE_CODE + "122", "油企名称已经存在！"),
    OILCOMPANY_SHORTNAME_ERROR(Constants.SYSTEM_MANAGE_MODULE_CODE + "123", "油企短名已经存在！"),
    OILCOMPANY_TAXCODE_ERROR(Constants.SYSTEM_MANAGE_MODULE_CODE + "124", "纳税人识别号已经存在！"),
    OILSTATION_EXIST(Constants.SYSTEM_MANAGE_MODULE_CODE + "125", "当前油站名称已经存在"),
    OILSTATION_COMPANY_EXIST(Constants.SYSTEM_MANAGE_MODULE_CODE + "126", "当前油企名下油站名称已经存在"),
    OILPRICEAPPLY_NOT_EXIST(Constants.SYSTEM_MANAGE_MODULE_CODE + "127", "价格申请记录不存在"),
    OILPRICEAPPLY_EDIT_ERROR(Constants.SYSTEM_MANAGE_MODULE_CODE + "128", "价格申请记录驳回状态才可以修改"),
    OILSTATION_PRINTSN_EXIST(Constants.SYSTEM_MANAGE_MODULE_CODE + "129", "当前打印机SN已经存在"),
    OILATTENDANT_NOT_EXIST(Constants.SYSTEM_MANAGE_MODULE_CODE + "130", "当前加油员不存在！"),
    
    
    //外部接口服务错误码
    CMD_ERROR(Constants.SYSTEM_EXTERNAL_MODULE_CODE + "100", "命令字不存在"),
	OUT_PARAM_ERROR(Constants.SYSTEM_EXTERNAL_MODULE_CODE + "101", "参数错误"),
	OUT_LOGISTICSCOMPANY_NULL(Constants.SYSTEM_EXTERNAL_MODULE_CODE + "102", "当前物流企业不存在"),
	OUT_PHONE_EXIST(Constants.SYSTEM_EXTERNAL_MODULE_CODE + "103", "当前手机号的客户信息已经存在"),
	OUT_VEHICLE_EXIST(Constants.SYSTEM_EXTERNAL_MODULE_CODE + "104", "当前车牌号的车辆信息已经存在"),
	OUT_CUSTOMER_NULL(Constants.SYSTEM_EXTERNAL_MODULE_CODE + "105", "当前客户信息不存在"),
	OUT_LOGISTICSCOMPANY_EXIST(Constants.SYSTEM_EXTERNAL_MODULE_CODE + "106", "当前标识的物流企业已经存在"),
	OUT_VEHICLE_NULL(Constants.SYSTEM_EXTERNAL_MODULE_CODE + "107", "当前车辆信息不存在"),
	OUT_STATION_NULL(Constants.SYSTEM_EXTERNAL_MODULE_CODE + "108", "当前油站信息不存在"),
	NETWORK_ERROR(Constants.SYSTEM_EXTERNAL_MODULE_CODE + "109", "网络错误，请稍后重试"),
    OUT_DRIVER_NULL(Constants.SYSTEM_EXTERNAL_MODULE_CODE + "110", "当前司机信息不存在"),
    OUT_STATION_ERROR(Constants.SYSTEM_EXTERNAL_MODULE_CODE + "111", "加油站不存在或者已经失效"),
    OUT_DRIVER_NOT_EXIST(Constants.SYSTEM_EXTERNAL_MODULE_CODE + "112", "当前手机号的司机信息不存在"),
    OUT_OIL_COMPANY_ERROR(Constants.SYSTEM_EXTERNAL_MODULE_CODE + "113", "油企不存在或者已经失效"),
    OUT_STATION_DISABLED(Constants.SYSTEM_EXTERNAL_MODULE_CODE + "114", "当前油站已禁用"),
    OUT_PRINT_SN_NULL(Constants.SYSTEM_EXTERNAL_MODULE_CODE + "115", "该油企未配置打印机"),
    ;



	private String code;

    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ResultCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
