package com.jyblife.logic.wl.ops.entity;

import java.io.Serializable;
import java.util.Date;

import com.jyblife.logic.wl.ops.common.annotation.Column;

/**
 * 实体对象基类
 */
public abstract class BaseEntity implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
	@Column("create_user_id")
	protected int createUserId;
	
    @Column("create_time")
    protected Date createTime;
    
    @Column("update_user_id")
	protected int updateUserId;
    
    @Column("update_time")
    protected  Date updateTime;

	public int getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(int updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
