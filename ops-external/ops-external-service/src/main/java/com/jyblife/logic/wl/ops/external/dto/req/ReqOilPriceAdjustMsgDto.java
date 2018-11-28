package com.jyblife.logic.wl.ops.external.dto.req;

import javax.validation.constraints.NotNull;

/**
 * @author yangcey
 * @date 2018/10/31
 **/
public class ReqOilPriceAdjustMsgDto extends ReqPager {
    @NotNull(message = "物流企业id不能为空")
    private Integer logisticsId;

    public Integer getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(Integer logisticsId) {
        this.logisticsId = logisticsId;
    }
}
