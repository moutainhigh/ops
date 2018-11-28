package com.jyblife.logic.wl.ops.entity;

import com.jyblife.logic.wl.ops.common.annotation.Id;
import com.jyblife.logic.wl.ops.common.annotation.Table;

@Table("t_area_code")
public class AreaCode {
    /**
     * 
     */
    @Id
    private Integer areaCode;

    /**
     * 
     */
    private String areaName;

    /**
     * 
     */
    private Integer pAreaCode;

    /**
     * 
     */
    private Integer level;

    /**
     * 
     * @return area_code 
     */
    public Integer getAreaCode() {
        return areaCode;
    }

    /**
     * 
     * @param areaCode 
     */
    public void setAreaCode(Integer areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * 
     * @return area_name 
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * 
     * @param areaName 
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    /**
     * 
     * @return p_area_code 
     */
    public Integer getpAreaCode() {
        return pAreaCode;
    }

    /**
     * 
     * @param pAreaCode 
     */
    public void setpAreaCode(Integer pAreaCode) {
        this.pAreaCode = pAreaCode;
    }

    /**
     * 
     * @return level 
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 
     * @param level 
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

	@Override
	public String toString() {
		return "AreaCode [areaCode=" + areaCode + ", areaName=" + areaName + ", pAreaCode=" + pAreaCode + ", level="
				+ level + "]";
	}
    
}