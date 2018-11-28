package com.jyblife.logic.wl.ops.common.enums;

/**
 * 订单类型
 * 
 **/
public enum OrderTypeEnum {
	
	NORMAL(1,"正常"),
	REPAIR(3,"补单"),
    BACK(2,"退单"),
    ;

    private Integer value;
    private String text;

    OrderTypeEnum(Integer value,String text){
        this.value = value;
        this.text = text;
    }

    public static String getText(Integer value) {
        for (OrderTypeEnum c : OrderTypeEnum.values()) {
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
