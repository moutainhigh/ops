package com.jyblife.logic.wl.ops.manage.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author yangcey
 * @date 2018/9/25
 **/
public class LogisticsQuotaLimitSaveDto {
	
	@Min(value=0, message = "企业当日可用额度比例不能小于0")
	@Max(value=1, message = "企业当日可用额度比例不能大于100%")
    @NotNull(message = "企业当日可用额度比不能为空")
    private BigDecimal rate;


    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
