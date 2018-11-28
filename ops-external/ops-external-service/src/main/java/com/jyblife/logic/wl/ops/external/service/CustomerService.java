package com.jyblife.logic.wl.ops.external.service;

import com.jyblife.logic.wl.ops.common.exception.OpsException;
import com.jyblife.logic.wl.ops.common.result.OutRespJson;
import com.jyblife.logic.wl.ops.entity.Customer;
import com.jyblife.logic.wl.ops.entity.Driver;
import com.jyblife.logic.wl.ops.external.dto.req.ReqCodeLoginDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqCustomerInfoDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqCustomerLocSwitchDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqTradePasswordDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespCodeLoginDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespCustomerInfoDto;

/**
 * @author yangcey
 * @date 2018/9/28
 **/
public interface CustomerService {

    Customer getCustomerById(Integer customerId);

    void getPhoneCode(String phone) throws OpsException;

    RespCodeLoginDto codeLogin(ReqCodeLoginDto dto) throws OpsException;

    Integer getCustomerIdByOpenId(String openId);

    int updateCustomer(Customer customer);
    
    int updateDriverByCustomerId(Driver driver);

    RespCustomerInfoDto getCustomerInfo(Integer customerId);
    
    OutRespJson checkTradePassword(ReqTradePasswordDto dto);

    OutRespJson getCustomerLocationSwitch(ReqCustomerLocSwitchDto reqTradePasswordDto);

    OutRespJson unbundlingWx(ReqCustomerInfoDto reqCustomerInfoDto);
}
