package com.jyblife.logic.wl.ops.external.exception;

import com.jyblife.logic.wl.ops.common.exception.OpsException;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;

@SuppressWarnings("serial")
public class ExternalServiceException extends OpsException {
	
    public ExternalServiceException() {
        super();
    }

    public ExternalServiceException(String code, String msg) {
        super(code,msg);
    }

    public ExternalServiceException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ExternalServiceException(ResultCodeEnum resultCode) {
        super(resultCode);
    }

    public ExternalServiceException(ResultCodeEnum resultCode, String extMsg) {
        super(resultCode,extMsg);
    }

    public ExternalServiceException(OpsException opsException){
        super(opsException.getCode(),opsException.getMessage());
    }
}
