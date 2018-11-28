package com.jyblife.logic.wl.ops.common.enums;

public enum OilStationApplyStatusEnum {

	SAVED(0,"已保存"),
	REJECTED(3,"已驳回"),
	SUBMITED(5,"已提交"),
	APPROVED(7,"已审核");

    private Integer value;
    private String text;

    OilStationApplyStatusEnum(Integer value,String text){
        this.value = value;
        this.text = text;
    }

    public static String getText(Integer value) {
        for (OilStationApplyStatusEnum c : OilStationApplyStatusEnum.values()) {
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
