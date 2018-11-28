package com.jyblife.logic.wl.ops.common.enums;

/**
 * @author yangcey
 * @date 2018/9/21
 **/
public enum StatusEnum {

    ENABLE(1,"启用"),
    DISABLE(0,"禁用");

    private Integer value;
    private String text;

    StatusEnum(Integer value,String text){
        this.value = value;
        this.text = text;
    }

    public static String getText(Integer value) {
        for (StatusEnum c : StatusEnum.values()) {
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
