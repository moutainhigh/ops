package com.jyblife.logic.wl.ops.common.result;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 统一响应封装
 */
public class OutRespJson extends HashMap<String, Object> implements Serializable {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * code：返回状态
     */
    public static final String KEY_CODE = "code";

    /**
     * message：返回消息文本
     */
    public static final String KEY_MESSAGE = "msg";

    /**
     * data:返回集合对象
     */
    public static final String DATA = "data";


    public static OutRespJson success() {
        OutRespJson respJson = new OutRespJson();
        respJson.put(KEY_CODE, ResultCodeEnum.SUCCESS.getCode());
        respJson.put(KEY_MESSAGE, ResultCodeEnum.SUCCESS.getMsg());
        respJson.put(DATA, ResultCodeEnum.SUCCESS.getMsg());
        return respJson;
    }

    public static OutRespJson success(String msg) {
        OutRespJson respJson = OutRespJson.success();
        respJson.setMessage(msg);
        respJson.setData(msg);
        return respJson;
    }

    public static OutRespJson success(Object data) {
        OutRespJson respJson = new OutRespJson();
        respJson.put(KEY_CODE, ResultCodeEnum.SUCCESS.getCode());
        respJson.put(KEY_MESSAGE,ResultCodeEnum.SUCCESS.getMsg());
        respJson.put(DATA, data);
        return respJson;
    }

    public static OutRespJson success(Object data, String msg) {
        OutRespJson respJson = new OutRespJson();
        respJson.put(KEY_CODE, ResultCodeEnum.SUCCESS.getCode());
        respJson.put(KEY_MESSAGE, msg);
        respJson.put(DATA, data);
        return respJson;
    }

    public Object getData() {
        return this.get(DATA);
    }


    public static OutRespJson error() {
        OutRespJson respJson = new OutRespJson();
        respJson.put(KEY_CODE, ResultCodeEnum.FAIL.getCode());
        respJson.put(KEY_MESSAGE,ResultCodeEnum.FAIL.getMsg());
        respJson.put(DATA,ResultCodeEnum.FAIL.getMsg());
        return respJson;
    }


    public static OutRespJson error(String msg) {
        OutRespJson respJson = OutRespJson.error();
        respJson.setMessage(msg);
        respJson.setData(msg);
        return respJson;
    }

    public static OutRespJson error(String code, String msg) {
        OutRespJson respJson = new OutRespJson();
        respJson.put(KEY_MESSAGE, msg);
        respJson.put(DATA, msg);
        respJson.put(KEY_CODE, code);
        return respJson;
    }


    public static OutRespJson error(ResultCodeEnum resultCodeEnum) {
        OutRespJson respJson = new OutRespJson();
        respJson.put(KEY_CODE, resultCodeEnum.getCode());
        respJson.put(KEY_MESSAGE, resultCodeEnum.getMsg());
        respJson.put(DATA, resultCodeEnum.getMsg());
        return respJson;
    }
    
    public static OutRespJson paramError(String msg) {
        OutRespJson respJson = new OutRespJson();
        respJson.put(KEY_CODE, ResultCodeEnum.OUT_PARAM_ERROR.getCode());
        respJson.put(KEY_MESSAGE, ResultCodeEnum.OUT_PARAM_ERROR.getMsg() + "：" + msg);
        respJson.put(DATA, ResultCodeEnum.OUT_PARAM_ERROR.getMsg() + "：" + msg);
        return respJson;
    }

    public static OutRespJson error(ResultCodeEnum resultCodeEnum, Object data) {
        OutRespJson respJson = new OutRespJson();
        respJson.put(KEY_CODE, resultCodeEnum.getCode());
        respJson.put(KEY_MESSAGE, resultCodeEnum.getMsg());
        respJson.put(DATA, data);
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
