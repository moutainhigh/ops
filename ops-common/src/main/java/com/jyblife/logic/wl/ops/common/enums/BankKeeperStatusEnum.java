package com.jyblife.logic.wl.ops.common.enums;

/**
 * @author yangcey
 * @date 2018/9/21
 * 银行管家状态
 **/
public enum BankKeeperStatusEnum {
    ENABLE(0,"正常"),
    DISABLE(1,"冻结");

    private Integer value;
    private String text;

    BankKeeperStatusEnum(Integer value, String text){
        this.value = value;
        this.text = text;
    }

    public static String getText(Integer value) {
        for (BankKeeperStatusEnum c : BankKeeperStatusEnum.values()) {
            if (value != null && c.value.intValue() == value.intValue()) {
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
