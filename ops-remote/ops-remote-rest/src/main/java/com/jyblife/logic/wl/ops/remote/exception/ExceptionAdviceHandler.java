package com.jyblife.logic.wl.ops.remote.exception;

import com.jyblife.logic.wl.ops.common.exception.OpsException;
import com.jyblife.logic.wl.ops.common.result.OutRespJson;
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

@RestControllerAdvice
public class ExceptionAdviceHandler {

    private static Logger logger = LoggerFactory.getLogger(ExceptionAdviceHandler.class);

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public OutRespJson MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        logger.error("MethodArgumentNotValidException : {}",ex.getMessage());
        BindingResult bindingResult = ex.getBindingResult();
        List<ObjectError> errorList = bindingResult.getAllErrors();
        if(errorList.size()>0) {
            ObjectError objectError = errorList.get(0);
            return OutRespJson.error(ResultCodeEnum.OUT_PARAM_ERROR.getCode(), objectError.getDefaultMessage());
        }else{
            return OutRespJson.error(ResultCodeEnum.OUT_PARAM_ERROR);
        }
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public OutRespJson MissingServletRequestParameterExceptionHandler(MissingServletRequestParameterException ex) {
        logger.error("MissingServletRequestParameterException : {}",ex.getMessage());
        return OutRespJson.error(ResultCodeEnum.OUT_PARAM_ERROR);
    }

    @ExceptionHandler(value = OpsException.class)
    public OutRespJson OpsExceptionHandler(OpsException ex) {
        logger.error("OpsException : {}",ex.toString());
        return OutRespJson.error(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public OutRespJson ExceptionHandler(Exception ex) {
        logger.error("Exception : {}",ex.getMessage());
        return OutRespJson.error();
    }


    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public OutRespJson HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex) {
        logger.error("HttpMessageNotReadableException : {}",ex.getMessage());
        return OutRespJson.error(ResultCodeEnum.OUT_PARAM_ERROR);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public OutRespJson RuntimeExceptionHandler(RuntimeException ex) {
        logger.error("RuntimeException : {}",ex.getMessage());
        return OutRespJson.error();
    }

    @ExceptionHandler(value = Throwable.class)
    public OutRespJson ThrowableHandler(Exception ex) {
        logger.error("Throwable : {}",ex.getMessage());
        return OutRespJson.error();
    }
}
