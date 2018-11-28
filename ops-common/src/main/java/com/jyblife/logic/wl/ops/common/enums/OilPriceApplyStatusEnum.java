package com.jyblife.logic.wl.ops.common.enums;

public enum OilPriceApplyStatusEnum {

	SAVED(0,"已保存"), //新建
	REJECTED(3,"已驳回"),
	SUBMITED(5,"待审核"), //提交后待审核
	APPROVED(7,"审批通过");

    private Integer value;
    private String text;

    OilPriceApplyStatusEnum(Integer value,String text){
        this.value = value;
        this.text = text;
    }

    public static String getText(Integer value) {
        for (OilPriceApplyStatusEnum c : OilPriceApplyStatusEnum.values()) {
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
