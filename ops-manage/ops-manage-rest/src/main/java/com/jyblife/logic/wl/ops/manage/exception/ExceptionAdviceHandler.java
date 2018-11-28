package com.jyblife.logic.wl.ops.manage.exception;

import com.jyblife.logic.wl.ops.common.exception.OpsException;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @Author: yangcey
 */
@RestControllerAdvice
public class ExceptionAdviceHandler {

    private static Logger logger = LoggerFactory.getLogger(ExceptionAdviceHandler.class);

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public RespJson MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        logger.error("MethodArgumentNotValidException : {}",ex.getMessage());
        BindingResult bindingResult = ex.getBindingResult();
        List<ObjectError> errorList = bindingResult.getAllErrors();
        if(errorList.size()>0) {
            ObjectError objectError = errorList.get(0);
            return RespJson.error(ResultCodeEnum.PARAM_ERROR.getCode(), objectError.getDefaultMessage());
        }else{
            return RespJson.error(ResultCodeEnum.PARAM_ERROR);
        }
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public RespJson MissingServletRequestParameterExceptionHandler(MissingServletRequestParameterException ex) {
        logger.error("MissingServletRequestParameterException : {}",ex.getMessage());
        return RespJson.error(ResultCodeEnum.PARAM_ERROR);
    }



    @ExceptionHandler(value = RestException.class)
    public RespJson RestExceptionHandler(RestException ex) {
        logger.error("RestException : {}",ex.toString());
        return RespJson.error(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(value = ManageServiceException.class)
    public RespJson ManageServiceExceptionHandler(ManageServiceException ex) {
        logger.error("ManageServiceException : {}",ex.toString());
        return RespJson.error(ex.getCode(), ex.getMessage());
    }


    @ExceptionHandler(value = OpsException.class)
    public RespJson OpsExceptionHandler(OpsException ex) {
        logger.error("OpsException : {}",ex.toString());
        return RespJson.error(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public RespJson ExceptionHandler(Exception ex) {
        logger.error("Exception : {}",ex.getMessage());
        return RespJson.error();
    }


    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public RespJson HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex) {
        logger.error("HttpMessageNotReadableException : {}",ex.getMessage());
        return RespJson.error(ResultCodeEnum.PARAM_BODY_ERROR);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public RespJson RuntimeExceptionHandler(RuntimeException ex) {
        logger.error("RuntimeException : {}",ex.getMessage());
        return RespJson.error();
    }


    @ExceptionHandler(value = Throwable.class)
    public RespJson ThrowableHandler(Exception ex) {
        logger.error("Throwable : {}",ex.getMessage());
        return RespJson.error();
    }
}
