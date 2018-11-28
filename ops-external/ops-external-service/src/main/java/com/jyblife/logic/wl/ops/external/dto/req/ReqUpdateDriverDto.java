package com.jyblife.logic.wl.ops.external.dto.req;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class ReqUpdateDriverDto {

	@NotNull(message = "customerId不能为空")
	private Integer customerId;

	@Min(value = 0, message="password交易密码必须是6位数字")
	@Length(min = 6, max = 6, message = "password交易密码必须是6位数字")
	@NotBlank(message = "password交易密码不能为空")
	private String password;

	@Min(value = 0)
	@NotNull(message = "司机状态不能为空")
	private Integer status;

	private List<File> files;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public class File {

		private String fileId;
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
