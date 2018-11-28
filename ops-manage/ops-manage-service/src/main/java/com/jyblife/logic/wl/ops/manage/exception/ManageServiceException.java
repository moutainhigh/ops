package com.jyblife.logic.wl.ops.manage.exception;

import com.jyblife.logic.wl.ops.common.exception.OpsException;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;

public class ManageServiceException extends OpsException {
    public ManageServiceException() {
        super();
    }

    public ManageServiceException(String code, String msg) {
        super(code,msg);
    }

    public ManageServiceException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ManageServiceException(ResultCodeEnum resultCode) {
        super(resultCode);
    }

    public ManageServiceException(ResultCodeEnum resultCode, String extMsg) {
        super(resultCode,extMsg);
    }

    public ManageServiceException(OpsException opsException){
        super(opsException.getCode(),opsException.getMessage());
    }
}
