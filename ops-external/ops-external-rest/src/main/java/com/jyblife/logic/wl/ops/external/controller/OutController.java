package com.jyblife.logic.wl.ops.external.controller;

import com.alibaba.fastjson.JSON;
import com.jyblife.logic.wl.ops.common.command.core.CommandHolder;
import com.jyblife.logic.wl.ops.common.command.core.CommandInvoker;
import com.jyblife.logic.wl.ops.common.exception.OpsException;
import com.jyblife.logic.wl.ops.common.result.OutRespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.common.utils.ValidatorUtil;
import com.jyblife.logic.wl.ops.external.dto.req.OutReqDto;
import com.jyblife.logic.wl.ops.external.exception.RestException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

@RestController
public class OutController {

    public static Logger logger = LoggerFactory.getLogger(OutController.class);

	@RequestMapping("/out")
    public OutRespJson out(@RequestBody OutReqDto dto) {
        logger.info("外部系统请求参数：" + JSON.toJSONString(dto));

        if (dto.getCmd() == null || dto.getCmd().toString().trim().length() != 8) {
            return OutRespJson.error(ResultCodeEnum.CMD_ERROR);
        }

        try {
            String cmd = dto.getCmd().toString();
            CommandInvoker commandInvoker = CommandHolder.get(cmd.substring(0, 6), cmd.substring(6));
            if (commandInvoker == null) {
                return OutRespJson.error(ResultCodeEnum.CMD_ERROR);
            }

            Method method = commandInvoker.getMethod();
            Class<?>[] types = method.getParameterTypes();

            Object[] params = new Object[types.length];
            int i = 0;
            for (Class<?> clazz : types) {
                if (Byte.class == clazz || Byte.TYPE == clazz) {
                    params[i] = ((Byte) dto.getData());
                } else if (String.class == clazz) {
                    params[i] = ((String) dto.getData());
                } else if (BigDecimal.class == clazz) {
                    params[i] = ((BigDecimal) dto.getData());
                } else if (Date.class == clazz) {
                    params[i] = ((Date) dto.getData());
                } else if ((Integer.TYPE == clazz) || (Integer.class == clazz)) {
                    params[i] = ((Integer) dto.getData());
                } else if ((Long.TYPE == clazz) || (Long.class == clazz)) {
                    params[i] = ((Long) dto.getData());
                } else if ((Float.TYPE == clazz) || (Float.class == clazz)) {
                    params[i] = ((Float) dto.getData());
                } else if ((Short.TYPE == clazz) || (Short.class == clazz)) {
                    params[i] = ((Short) dto.getData());
                } else if ((Double.TYPE == clazz) || (Double.class == clazz)) {
                    params[i] = ((Double) dto.getData());
                } else if (Character.TYPE == clazz || Character.class == clazz) {
                    params[i] = ((Character) dto.getData());
                } else if (clazz.isArray()) {
                    Class<?> c = clazz.getComponentType();
                    params[i] = (this.getDataArray(c, dto));
                } else {
                    Object object = this.getDataObject(clazz, dto);
                    params[i] = (object);
                    Map<String, List<String>> map = ValidatorUtil.validateDetail(object);
                    if(map != null && !map.isEmpty()) {
                    	return OutRespJson.error(ResultCodeEnum.PARAM_ERROR,map);
                    }
                }
                i++;
            }

            OutRespJson outRespJson = (OutRespJson) commandInvoker.invoke(params);
            logger.info("外部系统请求响应：" + JSON.toJSONString(outRespJson));
            return outRespJson;
        } catch (OpsException e) {
            throw e;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new RestException(ResultCodeEnum.FAIL);
        }
    }


    protected <T> T getDataObject(Class<T> clazz, OutReqDto dto) {
        if(dto.getData() == null){
            return null;
        }
        return JSON.parseObject(JSON.toJSONString(dto.getData()), clazz);

    }

    protected <T> List<T> getDataArray(Class<T> clazz, OutReqDto dto) {
        if(dto.getData() == null){
            return null;
        }
        return JSON.parseArray(JSON.toJSONString(dto.getData()), clazz);
    }

}
