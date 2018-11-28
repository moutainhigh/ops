package com.jyblife.logic.wl.ops.external.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jyblife.logic.wl.ops.common.contants.Constants;
import com.jyblife.logic.wl.ops.common.exception.OpsException;
import com.jyblife.logic.wl.ops.common.result.OutRespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.common.utils.DateUtil;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.core.redis.RedisTemplates;
import com.jyblife.logic.wl.ops.entity.Customer;
import com.jyblife.logic.wl.ops.entity.CustomerWxRelation;
import com.jyblife.logic.wl.ops.entity.Driver;
import com.jyblife.logic.wl.ops.entity.LogisticsCompany;
import com.jyblife.logic.wl.ops.external.dto.req.ReqCodeLoginDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqCustomerInfoDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqCustomerLocSwitchDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqTradePasswordDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespCodeLoginDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespCustomerInfoDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespCustomerLocSwtichDto;
import com.jyblife.logic.wl.ops.external.mapper.CustomerMapper;
import com.jyblife.logic.wl.ops.external.mapper.CustomerWxRelationMapper;
import com.jyblife.logic.wl.ops.external.mapper.DriverMapper;
import com.jyblife.logic.wl.ops.external.mapper.LogisticsCompanyMapper;
import com.jyblife.logic.wl.ops.external.service.CustomerService;
import com.jyblife.logic.wl.ops.message.constants.MessageTplEnum;
import com.jyblife.logic.wl.ops.message.dto.MessageCodeDto;
import com.jyblife.logic.wl.ops.message.utils.MessageUtils;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    public static Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerWxRelationMapper customerWxRelationMapper;
    @Autowired
    private DriverMapper driverMapper;
    @Autowired
    private LogisticsCompanyMapper logisticsCompanyMapper;

    @Autowired
    private RedisTemplates redisTemplates;

    @Override
    public Customer getCustomerById(Integer customerId) {
        return customerMapper.selectByPrimaryKey(customerId);
    }

    @Override
    public void getPhoneCode(String phone) throws OpsException {
        String day = DateUtil.format(new Date(), "yyyyMMdd");
        Long num = redisTemplates.incrementAndGet(Constants.OPS_PHONE_LIMIT_CODE_PREFIX + phone + day, 1, 1 * 24 * 60 * 60L);
        if (num > 5) { //每天最多发送5次验证码
            throw new OpsException(ResultCodeEnum.FAIL.getCode(), "当天手机号发送短信过于频繁，请明天再重试");
        } else {
            String key = Constants.OPS_PHONE_CODE_PREFIX + phone;
            String value = redisTemplates.get(key);
            if (StringUtils.isBlank(value)) {
                value = StrUtils.getRandomNumStr(4);
                redisTemplates.set(key, value, 5 * 60L);//设置三分钟过期时间
                logger.info("code == {}", value);
            }

            MessageCodeDto messageCodeDto = new MessageCodeDto();
            messageCodeDto.setCode(value);
            JSONObject jsonObj = (JSONObject) JSONObject.toJSON(messageCodeDto);
            MessageUtils.sendFunctionMessage(phone, MessageTplEnum.VALIEDATE_MESSAGE_TEMPLATE, jsonObj);
        }

    }

    @Override
    public RespCodeLoginDto codeLogin(ReqCodeLoginDto dto) throws OpsException {
        String key = Constants.OPS_PHONE_CODE_PREFIX + dto.getPhone();
        String code = redisTemplates.get(key);
        if (StringUtils.isBlank(code)) {
            throw new OpsException(ResultCodeEnum.FAIL.getCode(), "验证码无效");
        }
        if (StringUtils.equalsIgnoreCase(dto.getCode(), code)) {
            //查询用户id
            Customer customer = customerMapper.selectByPhone(dto.getPhone());
            if (customer == null) {
                return null;
            }
            if(customer!=null) {
                RespCodeLoginDto respCodeLoginDto = new RespCodeLoginDto();
                respCodeLoginDto.setCustomerId(String.valueOf(customer.getId()));
                //更新登录信息
                customerMapper.updateCustomerLoginInfo(customer.getId());

                if (StringUtils.isNotBlank(dto.getOpenId())) {
                    CustomerWxRelation relation = customerWxRelationMapper.selectByCustomerId(customer.getId());
                    if (relation == null) {
                        relation = new CustomerWxRelation();
                        Date d = new Date();
                        relation.setCreateTime(d);
                        relation.setUpdateTime(d);
                        relation.setCustomerId(customer.getId());
                        relation.setOpenId(dto.getOpenId());
                        relation.setWxIdentity("1");
                        customerWxRelationMapper.insertSelective(relation);
                    }
                }

                return respCodeLoginDto;
            }
        }

        throw new OpsException(ResultCodeEnum.FAIL.getCode(), "验证码不正确");
    }

    @Override
    public Integer getCustomerIdByOpenId(String openId) {
        return customerWxRelationMapper.getCustomerIdByOpenId(openId);
    }

    @Override
    public int updateCustomer(Customer customer) {
        return customerMapper.updateByPrimaryKeySelective(customer);
    }

    @Override
    public RespCustomerInfoDto getCustomerInfo(Integer customerId) {
        Driver driver = driverMapper.selectByCustomerId(customerId);
        if (driver == null) {
            return null;
        }

        RespCustomerInfoDto.CustomerItem customerItem = new RespCustomerInfoDto.CustomerItem();
        customerItem.setCustomerId(StrUtils.int2Str(customerId));
        customerItem.setName(driver.getName());
        customerItem.setPhone(driver.getPhone());
        customerItem.setStatus(StrUtils.int2Str(driver.getStatus()));
        customerItem.setOpenLocation((int) driver.getOpenLocation());
        customerItem.setPassword(driver.getPassword());

        LogisticsCompany company = logisticsCompanyMapper.selectByPrimaryKey(driver.getLogisticsId().longValue());
        RespCustomerInfoDto.CompanyItem companyItem = new RespCustomerInfoDto.CompanyItem();
        if (driver != null) {
            companyItem.setLogisticsId(String.valueOf(company.getLogisticsId()));
            companyItem.setName(company.getName());
            companyItem.setStatus((company.getStatus().intValue() == 1 && company.getOutStatus().intValue() == 0) ? "1" : "0");
        }

        RespCustomerInfoDto infoDto = new RespCustomerInfoDto();
        infoDto.setCompany(companyItem);
        infoDto.setCustomer(customerItem);

        return infoDto;
    }

    @Override
    public OutRespJson checkTradePassword(ReqTradePasswordDto dto) {
        Driver driver = driverMapper.selectByCustomerId(dto.getCustomerId());
        if (driver == null) {
            return OutRespJson.paramError("当前客户不存在");
        }
        if (!StrUtils.isNumber(dto.getPassword().trim()) || dto.getPassword().trim().length() != 6) {
            return OutRespJson.paramError("支付密码必须是6位数字");
        }

        if (!dto.getPassword().trim().equals(driver.getPassword())) {
            return OutRespJson.paramError("支付密码不正确");
        }

        return OutRespJson.success();
    }

    @Override
    public int updateDriverByCustomerId(Driver driver) {
        return driverMapper.updateDriverByCustomerId(driver);
    }

    @Override
    public OutRespJson getCustomerLocationSwitch(ReqCustomerLocSwitchDto reqTradePasswordDto) {
        Driver driver = driverMapper.selectByCustomerId(reqTradePasswordDto.getCustomerId());
        if (driver != null) {
            RespCustomerLocSwtichDto dto = new RespCustomerLocSwtichDto();
            dto.setCustomerId(reqTradePasswordDto.getCustomerId());
            dto.setOpenLocation((int) driver.getOpenLocation());
            return OutRespJson.success(dto);
        } else {
            return OutRespJson.error(ResultCodeEnum.OUT_CUSTOMER_NULL);
        }
    }

    @Override
    public OutRespJson unbundlingWx(ReqCustomerInfoDto reqCustomerInfoDto) {
        CustomerWxRelation relation = customerWxRelationMapper.selectByCustomerId(reqCustomerInfoDto.getCustomerId());
        if (relation != null) {
            CustomerWxRelation relation1 = new CustomerWxRelation();
            relation1.setId(relation.getId());
            relation1.setUpdateTime(new Date());
            //解绑微信id
            relation1.setOpenId("");
            customerWxRelationMapper.updateByPrimaryKeySelective(relation1);
            return OutRespJson.success();
        } else {
            //不存在就不需要解绑
            return OutRespJson.success();
        }
    }
}
