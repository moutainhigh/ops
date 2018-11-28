package com.jyblife.logic.wl.ops.manage.dto.resp;

public class RepOilCompanyListDto extends RespPager {

	public static class OilCompanyItem {

		private Integer companyId;
		private String name;
		private String shortName;
		private String taxCode;
		private String corporate;
		private String address;
		private String contactPhone;
		private Integer ownership;
		private String buildDate;
		private String remark;
		private String ownershipName;
		private Integer status;
		private String statusName;
		private boolean isCanView = false;
		private boolean isCanEdit = false;

		public Integer getCompanyId() {
			return companyId;
		}

		public void setCompanyId(Integer companyId) {
			this.companyId = companyId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getShortName() {
			return shortName;
		}

		public void setShortName(String shortName) {
			this.shortName = shortName;
		}

		public String getTaxCode() {
			return taxCode;
		}

		public void setTaxCode(String taxCode) {
			this.taxCode = taxCode;
		}

		public String getCorporate() {
			return corporate;
		}

		public void setCorporate(String corporate) {
			this.corporate = corporate;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getContactPhone() {
			return contactPhone;
		}

		public void setContactPhone(String contactPhone) {
			this.contactPhone = contactPhone;
		}

		public Integer getOwnership() {
			return ownership;
		}

		public void setOwnership(Integer ownership) {
			this.ownership = ownership;
		}

		public String getBuildDate() {
			return buildDate;
		}

		public void setBuildDate(String buildDate) {
			this.buildDate = buildDate;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getOwnershipName() {
			return ownershipName;
		}

		public void setOwnershipName(String ownershipName) {
			this.ownershipName = ownershipName;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public String getStatusName() {
			return statusName;
		}

		public void setStatusName(String statusName) {
			this.statusName = statusName;
		}

		public Boolean getIsCanView() {
			return isCanView;
		}

		public void setIsCanView(Boolean isCanView) {
			this.isCanView = isCanView;
		}

		public Boolean getIsCanEdit() {
			return isCanEdit;
		}

		public void setIsCanEdit(Boolean isCanEdit) {
			this.isCanEdit = isCanEdit;
		}

	}
}