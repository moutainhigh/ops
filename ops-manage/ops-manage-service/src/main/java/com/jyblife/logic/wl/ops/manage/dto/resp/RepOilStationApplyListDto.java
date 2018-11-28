package com.jyblife.logic.wl.ops.manage.dto.resp;

public class RepOilStationApplyListDto extends RespPager {

	public static class OilStationApplyItem {
		
		private String applyId;
		private String name;
		private String companyId;
		private String provinceId;
		private String cityId;
		private String address;
		private String longitude;
		private String latitude;
		private String contactPerson;
		private String contactPhone;
		private String status;
		private String companyName;
		private String provinceName;
		private String cityName;
		private String statusName;
		private boolean isCanView;
		private boolean isCanEdit;

		public String getApplyId() {
			return applyId;
		}

		public void setApplyId(String applyId) {
			this.applyId = applyId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCompanyId() {
			return companyId;
		}

		public void setCompanyId(String companyId) {
			this.companyId = companyId;
		}

		public String getProvinceId() {
			return provinceId;
		}

		public void setProvinceId(String provinceId) {
			this.provinceId = provinceId;
		}

		public String getCityId() {
			return cityId;
		}

		public void setCityId(String cityId) {
			this.cityId = cityId;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getLongitude() {
			return longitude;
		}

		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}

		public String getLatitude() {
			return latitude;
		}

		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}

		public String getContactPerson() {
			return contactPerson;
		}

		public void setContactPerson(String contactPerson) {
			this.contactPerson = contactPerson;
		}

		public String getContactPhone() {
			return contactPhone;
		}

		public void setContactPhone(String contactPhone) {
			this.contactPhone = contactPhone;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		public String getProvinceName() {
			return provinceName;
		}

		public void setProvinceName(String provinceName) {
			this.provinceName = provinceName;
		}

		public String getCityName() {
			return cityName;
		}

		public void setCityName(String cityName) {
			this.cityName = cityName;
		}

		public String getStatusName() {
			return statusName;
		}

		public void setStatusName(String statusName) {
			this.statusName = statusName;
		}

		public boolean getIsCanView() {
			return isCanView;
		}

		public void setCanView(boolean isCanView) {
			this.isCanView = isCanView;
		}

		public boolean getIsCanEdit() {
			return isCanEdit;
		}

		public void setCanEdit(boolean isCanEdit) {
			this.isCanEdit = isCanEdit;
		}

	}
}