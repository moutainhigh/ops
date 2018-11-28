package com.jyblife.logic.wl.ops.manage.dto.resp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class UserMenuDto implements Serializable {

	private Integer id;
	private String name;
	private String code;
	private String icon;
	private Integer systemId;
	private Integer parentId;
	private String parentIds;
	private String pageUrl;
	private Integer orderIndex;
	private Byte isPublic;
	private Byte isExternal;
	private String remark;
	List<UserMenuDto> children;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSystemId() {
		return systemId;
	}

	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public Integer getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

	public Byte getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Byte isPublic) {
		this.isPublic = isPublic;
	}

	public Byte getIsExternal() {
		return isExternal;
	}

	public void setIsExternal(Byte isExternal) {
		this.isExternal = isExternal;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<UserMenuDto> getChildren() {
		if(children == null) {
			children = new ArrayList<UserMenuDto>();
		}
		return children;
	}

	public void setChildren(List<UserMenuDto> children) {
		this.children = children;
	}

}
