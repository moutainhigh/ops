package com.jyblife.logic.wl.ops.common.enums;

public enum OilCompanyOwnershipEnum {

	STATEOWNED(1,"国有"),
	PRIVATE(2,"民营");

    private Integer value;
    private String text;

    OilCompanyOwnershipEnum(Integer value,String text){
        this.value = value;
        this.text = text;
    }

    public static String getText(Integer value) {
        for (OilCompanyOwnershipEnum c : OilCompanyOwnershipEnum.values()) {
            if (c.value.intValue() == value) {
                return c.text;
            }
        }
        return "";
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
