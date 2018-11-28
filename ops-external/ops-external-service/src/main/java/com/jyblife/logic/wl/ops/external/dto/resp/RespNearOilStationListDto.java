package com.jyblife.logic.wl.ops.external.dto.resp;

import java.math.BigDecimal;

@SuppressWarnings("rawtypes")
public class RespNearOilStationListDto extends RespPager {

	public static class OilStationItem implements Comparable<OilStationItem> {

		private String stationId;
		private String name;
		private String companyId;
		private String provinceId;
		private String cityId;
		private String address;
		private String longitude;
		private String latitude;
		private String remark;
		private String contactPerson;
		private String contactPhone;
		private String status;
		private String companyName;
		private String province;
		private String city;
		private String distance;
		private Integer closest = 2;
		private Integer mostVisit = 2;

		public String getStationId() {
			return stationId;
		}

		public void setStationId(String stationId) {
			this.stationId = stationId;
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

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
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

		public String getProvince() {
			return province;
		}

		public void setProvince(String province) {
			this.province = province;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getDistance() {
			return distance;
		}

		public void setDistance(String distance) {
			this.distance = distance;
		}

		public Integer getClosest() {
			return closest;
		}

		public void setClosest(Integer closest) {
			this.closest = closest;
		}

		public Integer getMostVisit() {
			return mostVisit;
		}

		public void setMostVisit(Integer mostVisit) {
			this.mostVisit = mostVisit;
		}

		@Override
		public int compareTo(OilStationItem o) {
			return new BigDecimal(this.getDistance()).compareTo(new BigDecimal(o.getDistance()));
		}

	}

}
