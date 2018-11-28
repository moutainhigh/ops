package com.jyblife.logic.wl.ops.common.exception;

import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;

/**
 * 业务异常类
 */
public class OpsException extends RuntimeException {

    private static final long serialVersionUID = -3473021900158143766L;

    /**
     * @Fields code : 错误编码
     */
    private String code;

    /**
     * @Fields message : 错误消息
     */
    private String msg;

    public OpsException() {
        super();
    }

    public OpsException(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public OpsException(String msg, Throwable cause) {
        super(msg, cause);
        this.msg = msg;
    }

    public OpsException(ResultCodeEnum resultCode) {
        super();
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    public OpsException(ResultCodeEnum resultCode, String extMsg) {
        super();
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg() + extMsg;
    }

    /**
     * <p>Title: 业务异常</p>
     * <p>Description: 带错误消息的业务异常</p>
     *
     * @param msg 错误消息
     */
    public OpsException(String msg) {
        super(msg);
        this.msg = msg;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the msg
     */
    public String getMessage() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMessage(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "[code=" + this.code + "]" + ",[msg=" + this.msg + "]";
    }


}
