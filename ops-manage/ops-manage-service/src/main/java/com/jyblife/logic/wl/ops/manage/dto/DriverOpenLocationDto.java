package com.jyblife.logic.wl.ops.manage.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author yangcey
 * @date 2018/10/29
 **/
public class DriverOpenLocationDto {
    @NotNull(message = "司机id不能为空")
    private Integer driverId;
    @NotNull(message = "定位状态不能为空")
    private Byte openLocation;

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Byte getOpenLocation() {
        return openLocation;
    }

    public void setOpenLocation(Byte openLocation) {
        this.openLocation = openLocation;
    }
}
