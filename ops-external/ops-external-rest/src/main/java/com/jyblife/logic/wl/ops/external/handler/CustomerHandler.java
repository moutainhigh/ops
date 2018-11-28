package com.jyblife.logic.wl.ops.external.handler;

import com.jyblife.logic.wl.ops.common.command.annotation.CommandMethod;
import com.jyblife.logic.wl.ops.common.command.annotation.CommandService;
import com.jyblife.logic.wl.ops.common.result.OutRespJson;
import com.jyblife.logic.wl.ops.external.dto.req.*;

@CommandService("910400")
public interface CustomerHandler {

    /**
     * 检验客户
     *
     * @param checkCustomerDto
     * @return
     */
    @CommandMethod("01")
    OutRespJson checkCustomer(ReqCheckCustomerDto checkCustomerDto);

    /**
     * 获取短信验证码
     *
     * @param reqPhoneCodeDto
     * @return
     */
    @CommandMethod("02")
    OutRespJson getPhoneCode(ReqPhoneCodeDto reqPhoneCodeDto);

    /**
     * 验证码登陆
     *
     * @param reqCodeLoginDto
     * @return
     */
    @CommandMethod("03")
    OutRespJson codeLogin(ReqCodeLoginDto reqCodeLoginDto);

    /**
     * 获取客户id
     *
     * @param reqCustomerIdDto
     * @return
     */
    @CommandMethod("04")
    OutRespJson getCustomerId(ReqCustomerIdDto reqCustomerIdDto);

    /**
     * 获取客户信息
     *
     * @param reqCustomerInfoDto
     * @return
     */
    @CommandMethod("05")
    OutRespJson getCustomerInfo(ReqCustomerInfoDto reqCustomerInfoDto);

    /**
     * 更新用户状态
     *
     * @param reqCustomerStatusDto
     * @return
     */
    @CommandMethod("06")
    OutRespJson updateCustomerStatus(ReqCustomerStatusDto reqCustomerStatusDto);

    /**
     * 检查用户交易密码
     *
     * @param reqTradePasswordDto
     * @return
     */
    @CommandMethod("07")
    OutRespJson checkTradePassword(ReqTradePasswordDto reqTradePasswordDto);

    /**
     * 获取司机定位开关
     *
     * @param reqTradePasswordDto
     * @return
     */
    @CommandMethod("08")
    OutRespJson getCustomerLocationSwitch(ReqCustomerLocSwitchDto reqTradePasswordDto);


    /**
     * 解绑微信
     *
     * @param reqCustomerInfoDto
     * @return
     */
    @CommandMethod("09")
    OutRespJson unbundlingWx(ReqCustomerInfoDto reqCustomerInfoDto);
}
