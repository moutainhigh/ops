package com.jyblife.logic.wl.ops.common.enums;

/**
 * @author yangcey
 * @date 2018/9/25
 **/
public enum OrderStatusEnum {
    FAIL(-1,"交易失败"),
    INIT(0,"新建"),
    SUCCESS(10,"交易成功"),;

    private Integer value;
    private String text;

    OrderStatusEnum(Integer value,String text){
        this.value = value;
        this.text = text;
    }

    public static String getText(Integer value) {
        for (OrderStatusEnum c : OrderStatusEnum.values()) {
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
