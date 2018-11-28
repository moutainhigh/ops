package com.jyblife.logic.wl.ops.external.handler.impl;

import com.jyblife.logic.wl.ops.common.exception.OpsException;
import com.jyblife.logic.wl.ops.common.result.OutRespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.entity.Customer;
import com.jyblife.logic.wl.ops.entity.Driver;
import com.jyblife.logic.wl.ops.external.dto.req.*;
import com.jyblife.logic.wl.ops.external.dto.resp.RespCodeLoginDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespCustomerIdDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespCustomerInfoDto;
import com.jyblife.logic.wl.ops.external.handler.AbstractHandler;
import com.jyblife.logic.wl.ops.external.handler.CustomerHandler;
import com.jyblife.logic.wl.ops.external.mapper.DriverMapper;
import com.jyblife.logic.wl.ops.external.service.CustomerService;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerHandlerImpl extends AbstractHandler implements CustomerHandler {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DriverMapper driverMapper;

    @Override
    public OutRespJson checkCustomer(ReqCheckCustomerDto checkCustomerDto) {
        try {
            Customer customer = customerService.getCustomerById(checkCustomerDto.getCustomerId());
            if (customer != null) {
                return OutRespJson.success();
            }
            return OutRespJson.error();
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            return OutRespJson.error();
        }
    }

    @Override
    public OutRespJson getPhoneCode(ReqPhoneCodeDto reqPhoneCodeDto) throws OpsException {
        try {
            //判断司机信息是否存在
            Driver driver = driverMapper.selectByPhone(reqPhoneCodeDto.getPhone());
            if(driver == null){
                return OutRespJson.error(ResultCodeEnum.OUT_DRIVER_NOT_EXIST);
            }
            customerService.getPhoneCode(reqPhoneCodeDto.getPhone());
            return OutRespJson.success();
        } catch (OpsException e) {
            throw e;
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            return OutRespJson.error();
        }
    }

    @Override
    public OutRespJson codeLogin(ReqCodeLoginDto reqCodeLoginDto) throws OpsException {
        try {
            RespCodeLoginDto respCodeLoginDto = customerService.codeLogin(reqCodeLoginDto);
            if(respCodeLoginDto == null) {
            	return OutRespJson.error(ResultCodeEnum.OUT_CUSTOMER_NULL);
            }
            return OutRespJson.success(respCodeLoginDto);
        } catch (OpsException e) {
            throw e;
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            return OutRespJson.error();
        }
    }

    @Override
    public OutRespJson getCustomerId(ReqCustomerIdDto reqCustomerIdDto) {
        try {
            Integer customerId = customerService.getCustomerIdByOpenId(reqCustomerIdDto.getOpenId());
            if(customerId == null) {
            	return OutRespJson.paramError("当前客户不存在");
            }
            
            RespCustomerIdDto respCustomerIdDto = new RespCustomerIdDto();
            respCustomerIdDto.setCustomerId(String.valueOf(customerId));
            
            return OutRespJson.success(respCustomerIdDto);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            return OutRespJson.error();
        }
    }

    @Override
    public OutRespJson getCustomerInfo(ReqCustomerInfoDto reqCustomerInfoDto) {
        try {
        	RespCustomerInfoDto customerInfo = customerService.getCustomerInfo(reqCustomerInfoDto.getCustomerId());
        	if(customerInfo == null) {
            	return OutRespJson.paramError("当前客户不存在");
            }
        	
            return OutRespJson.success(customerInfo);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            return OutRespJson.error();
        }
    }

    @Override
    public OutRespJson updateCustomerStatus(ReqCustomerStatusDto reqCustomerStatusDto) {
        try {
        	Date curDate = new Date();
            Customer customer = new Customer();
            customer.setId(reqCustomerStatusDto.getCustomerId());
            customer.setStatus(reqCustomerStatusDto.getStatus());
            customer.setUpdateTime(curDate);
            customer.setStatusTime(curDate);
            int i = customerService.updateCustomer(customer);
            
            if(i == 0) {
            	return OutRespJson.paramError("当前客户不存在");
            }
            
            Driver driver = new Driver();
            driver.setCustomerId(reqCustomerStatusDto.getCustomerId());
            driver.setStatus(reqCustomerStatusDto.getStatus());
            driver.setUpdateTime(curDate);
            driver.setStatusTime(curDate);
            customerService.updateDriverByCustomerId(driver);
            
            return OutRespJson.success();
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            return OutRespJson.error();
        }
    }

    @Override
    public OutRespJson checkTradePassword(ReqTradePasswordDto reqTradePasswordDto) {
        try {
            return customerService.checkTradePassword(reqTradePasswordDto);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            return OutRespJson.error();
        }
    }

    @Override
    public OutRespJson getCustomerLocationSwitch(ReqCustomerLocSwitchDto reqTradePasswordDto){
        try {
            return customerService.getCustomerLocationSwitch(reqTradePasswordDto);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            return OutRespJson.error();
        }
    }

    @Override
    public OutRespJson unbundlingWx(ReqCustomerInfoDto reqCustomerInfoDto) {

        try {
            return customerService.unbundlingWx(reqCustomerInfoDto);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            return OutRespJson.error();
        }
    }
}
