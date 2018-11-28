package com.jyblife.logic.wl.ops.external.dto.req;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class ReqVehicleAddDto {

	@Min(value = 0, message = "请填写正确的企业id")
	@NotNull(message = "物流企业id不能为空")
	private Integer logisticsId;

	@NotBlank(message = "车牌号不能为空")
	private String number;

	@NotBlank(message = "车型不能为空")
	private String model;

	@Min(value = 0, message = "Capacity 必须为数字")
	@NotBlank(message = "油箱容量不能为空")
	private String capacity;

	@NotBlank(message = "操作人不能为空")
	private String operator;

	@NotBlank(message = "行驶证开始日期不能为空")
	private String startDate;

	@NotBlank(message = "行驶证结束日期不能为空")
	private String endDate;

	private List<File> files;

	public Integer getLogisticsId() {
		return logisticsId;
	}

	public void setLogisticsId(Integer logisticsId) {
		this.logisticsId = logisticsId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public class File {

		@NotNull(message = "附件id不能为空")
		private String fileId;
		@NotBlank(message = "附件地址不能为空")
		private String fileUrl;

		public String getFileId() {
			return fileId;
		}

		public void setFileId(String fileId) {
			this.fileId = fileId;
		}

		public String getFileUrl() {
			return fileUrl;
		}

		public void setFileUrl(String fileUrl) {
			this.fileUrl = fileUrl;
		}
	}

}
