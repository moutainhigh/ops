package com.jyblife.logic.wl.ops.manage.exception;

import com.jyblife.logic.wl.ops.common.exception.OpsException;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;

/**
 * rest异常
 * @Author yangcey
 */
public class RestException extends OpsException {

    public RestException() {
        super();
    }

    public RestException(String code, String msg) {
        super(code,msg);
    }

    public RestException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public RestException(ResultCodeEnum resultCode) {
        super(resultCode);
    }

    public RestException(ResultCodeEnum resultCode, String extMsg) {
        super(resultCode,extMsg);
    }

    public RestException(OpsException opsException){
        super(opsException.getCode(),opsException.getMessage());
    }
}
