package com.jyblife.logic.wl.ops.common.enums;

/**
 * @author yangcey
 * @date 2018/9/26
 **/
public enum LogisticsQuotaLogCategoryEnum {
    ORDER_PAYMENT(10,"订单支付"),
    LOGISTICS_REPAYMENT(20,"物流还款");

    private Integer value;
    private String text;

    LogisticsQuotaLogCategoryEnum(Integer value,String text){
        this.value = value;
        this.text = text;
    }

    public static String getText(Integer value) {
        for (LogisticsQuotaLogCategoryEnum c : LogisticsQuotaLogCategoryEnum.values()) {
            if (c.value.intValue() == value) {
                return c.text;
            }
        }
        return null;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
