package com.jyblife.logic.wl.ops.common.result;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 统一响应封装
 */
public class RespJson extends HashMap<String, Object> implements Serializable {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * code：返回状态
     */
    public static final String KEY_CODE = "state";

    /**
     * message：返回消息文本
     */
    public static final String KEY_MESSAGE = "msg";

    /**
     * data:返回集合对象
     */
    public static final String DATA = "data";


    public static RespJson success() {
        RespJson respJson = new RespJson();
        respJson.put(KEY_CODE, ResultCodeEnum.SUCCESS.getCode());
        respJson.put(KEY_MESSAGE, ResultCodeEnum.SUCCESS.getMsg());
        respJson.put(DATA, ResultCodeEnum.SUCCESS.getMsg());
        return respJson;
    }

    public static RespJson success(String msg) {
        RespJson respJson = RespJson.success();
        respJson.setMessage(msg);
        respJson.setData(msg);
        return respJson;
    }

    public static RespJson success(Object data) {
        RespJson respJson = new RespJson();
        respJson.put(KEY_CODE, ResultCodeEnum.SUCCESS.getCode());
        respJson.put(KEY_MESSAGE,ResultCodeEnum.SUCCESS.getMsg());
        respJson.put(DATA, data);
        return respJson;
    }

    public static RespJson success(Object data, String msg) {
        RespJson respJson = new RespJson();
        respJson.put(KEY_CODE, ResultCodeEnum.SUCCESS.getCode());
        respJson.put(KEY_MESSAGE, msg);
        respJson.put(DATA, data);
        return respJson;
    }

    public Object getData() {
        return this.get(DATA);
    }


    public static RespJson error() {
        RespJson respJson = new RespJson();
        respJson.put(KEY_CODE, ResultCodeEnum.FAIL.getCode());
        respJson.put(KEY_MESSAGE,ResultCodeEnum.FAIL.getMsg());
        respJson.put(DATA,ResultCodeEnum.FAIL.getMsg());
        return respJson;
    }


    public static RespJson error(String msg) {
        RespJson respJson = RespJson.error();
        respJson.setMessage(msg);
        respJson.setData(msg);
        return respJson;
    }

    public static RespJson error(String code, String msg) {
        RespJson respJson = new RespJson();
        respJson.put(KEY_MESSAGE, msg);
        respJson.put(KEY_CODE, code);
        respJson.put(DATA, msg);
        return respJson;
    }


    public static RespJson error(ResultCodeEnum resultCodeEnum) {
        RespJson respJson = new RespJson();
        respJson.put(KEY_CODE, resultCodeEnum.getCode());
        respJson.put(KEY_MESSAGE, resultCodeEnum.getMsg());
        respJson.put(DATA, resultCodeEnum.getMsg());
        return respJson;
    }

    public static RespJson error(ResultCodeEnum resultCodeEnum, Object data) {
        RespJson respJson = new RespJson();
        respJson.put(KEY_CODE, resultCodeEnum.getCode());
        respJson.put(KEY_MESSAGE, resultCodeEnum.getMsg());
        respJson.put(DATA, data);
        return respJson;
    }
    
    public static RespJson paramError(String msg) {
    	RespJson respJson = new RespJson();
        respJson.put(KEY_CODE, ResultCodeEnum.PARAM_ERROR.getCode());
        respJson.put(KEY_MESSAGE, ResultCodeEnum.PARAM_ERROR.getMsg() + "：" + msg);
        respJson.put(DATA, ResultCodeEnum.PARAM_ERROR.getMsg() + "：" + msg);
        return respJson;
    }

    public void setMessage(String msg) {
        this.put(KEY_MESSAGE, msg);
    }

    public void setData(Object data) {
        this.put(DATA, data);
    }
    
    public void setCode(Object code) {
        this.put(KEY_CODE, code);
    }

    public String getCode() {
        Object obj = this.get(KEY_CODE);
        String status = obj == null ? ResultCodeEnum.FAIL.getCode() : obj.toString();
        return status;
    }

    public String getMessage() {
        return this.get(KEY_MESSAGE).toString();
    }

    public boolean isSuccess() {
        return ResultCodeEnum.SUCCESS.getCode().equals(this.getCode());
    }

}
