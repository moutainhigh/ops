package com.jyblife.logic.wl.ops.common.enums;

/**
 * @author yangcey
 * @date 2018/9/21
 * 图片附件类型枚举
 **/
public enum PhotoAttachTypeEnum {
    DRIVER(1),
    VEHICLE(10),//车辆附件
    ;
    //TODO 还有其他类型需要等待加入
    private Integer value;

    PhotoAttachTypeEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
