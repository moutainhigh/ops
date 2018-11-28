package com.jyblife.logic.wl.ops.common.enums;

/**
 * @author yangcey
 * @date 2018/9/26
 **/
public enum LogisticsQuotaStatusEnum {
    OVERDUE(-1,"过期"),
    NORMAL(0,"正常"),;

    private Integer value;
    private String text;

    LogisticsQuotaStatusEnum(Integer value,String text){
        this.value = value;
        this.text = text;
    }

    public static String getText(Integer value) {
        for (LogisticsQuotaStatusEnum c : LogisticsQuotaStatusEnum.values()) {
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
