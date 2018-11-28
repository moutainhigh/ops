package com.jyblife.logic.wl.ops.external.dto.resp;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yangcey
 * @date 2018/10/31
 **/
public class RespLogicQuotaUsedDto {
    private Integer logisticsId;

    List<LogicQuotaUseItem> items;

    public Integer getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(Integer logisticsId) {
        this.logisticsId = logisticsId;
    }

    public List<LogicQuotaUseItem> getItems() {
        return items;
    }

    public void setItems(List<LogicQuotaUseItem> items) {
        this.items = items;
    }

    public static class LogicQuotaUseItem {
        private String date;
        private String amount;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }
    }


}
