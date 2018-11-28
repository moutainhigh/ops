package com.jyblife.logic.wl.ops.external.dto.req;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class ReqDriverAddDto {

	@NotBlank(message = "司机姓名不能为空")
	private String name;
	
	@Min(value = 1, message="请填写正确的企业id")
	@NotNull(message = "物流企业id不能为空")
	private Long logisticsId;
	
	@Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}", message="请输入正确的手机号")
	@NotBlank(message = "手机号不能为空")
	private String phone;

	@Min(value = 0, message="Password交易密码必须是6位数字")	
	@Length(min=6, max=6, message="Password交易密码必须是6位数字") 
	@NotBlank(message = "密码不能为空")
	private String password;
	
	@Min(value = 0)
	@NotNull(message = "司机状态不能为空")
	private Integer status;
	
	private List<File> files;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getLogisticsId() {
		return logisticsId;
	}

	public void setLogisticsId(Long logisticsId) {
		this.logisticsId = logisticsId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
