package com.jyblife.logic.wl.ops.entity;

import com.jyblife.logic.wl.ops.common.annotation.Id;
import com.jyblife.logic.wl.ops.common.annotation.Table;

import java.util.Date;
@Table("t_driver_vehicle_relation")
public class DriverVehicleRelation {
    /**
     * 标识id
     */
    @Id
    private Integer id;

    /**
     * 司机id
     */
    private Integer driverId;

    /**
     * 车辆id
     */
    private Integer vehicleId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 标识id
     * @return id 标识id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 标识id
     * @param id 标识id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 司机id
     * @return driver_id 司机id
     */
    public Integer getDriverId() {
        return driverId;
    }

    /**
     * 司机id
     * @param driverId 司机id
     */
    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    /**
     * 车辆id
     * @return vehicle_id 车辆id
     */
    public Integer getVehicleId() {
        return vehicleId;
    }

    /**
     * 车辆id
     * @param vehicleId 车辆id
     */
    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}