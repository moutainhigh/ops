package com.jyblife.logic.wl.ops.common.enums;

public enum VehicleApproveStatusEnum {
    
    SAVED(0,"已保存"),
	REJECTED(3,"已驳回"),
	SUBMITED(5,"审核中"),
	APPROVED(2,"审核通过"),
	APPROVED2(7,"审核通过"), //后期统一状态
    ;
    
    private Integer value;
    private String text;

    VehicleApproveStatusEnum(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    public static String getText(Integer value) {
        for (VehicleApproveStatusEnum c : VehicleApproveStatusEnum.values()) {
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
