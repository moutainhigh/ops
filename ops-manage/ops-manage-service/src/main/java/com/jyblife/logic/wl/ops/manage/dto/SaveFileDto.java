package com.jyblife.logic.wl.ops.manage.dto;

import java.util.Arrays;

import javax.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class SaveFileDto {

	private MultipartFile[] files;
	@NotNull(message = "type不能为空")
	private Integer type;
	@NotNull(message = "id不能为空")
	private Integer id;

	public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "SaveFileDto [files=" + Arrays.toString(files) + ", type=" + type + ", id=" + id + "]";
	}
}
