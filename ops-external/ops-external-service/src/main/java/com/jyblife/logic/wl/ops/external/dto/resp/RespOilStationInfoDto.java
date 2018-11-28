package com.jyblife.logic.wl.ops.external.dto.resp;

import java.util.List;

public class RespOilStationInfoDto {

	private Integer stationId;
	private String name;
	private Integer companyId;
	private String companyName;
	private Integer provinceId;
	private String province;
	private Integer cityId;
	private String city;
	private String address;
	private String longitude;
	private String latitude;
	private String contactPerson;
	private String contactPhone;
	private String remark;
	private Integer closest = 0;
	private Integer mostVisit = 0;
	private Double distance = 0.0d;
	private Integer status;
	private List<Goods> goods;

	public Integer getStationId() {
		return stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Goods> getGoods() {
		return goods;
	}

	public void setGoods(List<Goods> goods) {
		this.goods = goods;
	}

	public static class Goods {
		
		private Integer goodsId;
		private String name;
		private Integer retailPrice;
		private Integer agreedPrice;
		private Integer discountPrice;

		public Integer getGoodsId() {
			return goodsId;
		}

		public void setGoodsId(Integer goodsId) {
			this.goodsId = goodsId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getRetailPrice() {
			return retailPrice;
		}

		public void setRetailPrice(Integer retailPrice) {
			this.retailPrice = retailPrice;
		}

		public Integer getAgreedPrice() {
			return agreedPrice;
		}

		public void setAgreedPrice(Integer agreedPrice) {
			this.agreedPrice = agreedPrice;
		}

		public Integer getDiscountPrice() {
			return discountPrice;
		}

		public void setDiscountPrice(Integer discountPrice) {
			this.discountPrice = discountPrice;
		}

	}

}
