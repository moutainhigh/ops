package com.jyblife.logic.wl.ops.common.enums;

/**
 * @author yangcey
 * @date 2018/9/21
 **/
public enum DriverApproveStatusEnum {
    INIT(0,"申请中"),//TODO
    PROCESS(1,"审批中"),//TODO
    PASS(2, "审核通过"),
    ;
    private Integer value;
    private String text;

    DriverApproveStatusEnum(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    public static String getText(Integer value) {
        for (DriverApproveStatusEnum c : DriverApproveStatusEnum.values()) {
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
