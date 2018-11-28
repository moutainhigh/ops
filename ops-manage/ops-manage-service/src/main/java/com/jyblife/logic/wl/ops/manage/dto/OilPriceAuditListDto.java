package com.jyblife.logic.wl.ops.manage.dto;

import org.apache.commons.lang3.StringUtils;

import com.jyblife.logic.wl.ops.common.utils.DateUtil;

public class OilPriceAuditListDto extends RequestPager {

	private OilPriceAuditListDto.Search search;

	public OilPriceAuditListDto.Search getSearch() {
		return search;
	}

	public void setSearch(OilPriceAuditListDto.Search search) {
		this.search = search;
	}

	public class Search implements Searcher {
		
		private String code;
		private Integer status;
		private String createUserName;
		private String createStartTime;
		private String createEndTime;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public String getCreateUserName() {
			return createUserName;
		}

		public void setCreateUserName(String createUserName) {
			this.createUserName = createUserName;
		}

		public String getCreateStartTime() {
			if(StringUtils.isNoneBlank(createStartTime)) {
				createStartTime = createStartTime + DateUtil.START_TIME;
        	}
			return createStartTime;
		}

		public void setCreateStartTime(String createStartTime) {
			this.createStartTime = createStartTime;
		}

		public String getCreateEndTime() {
			if(StringUtils.isNoneBlank(createEndTime)) {
				createEndTime = createEndTime + DateUtil.END_TIME;
        	}
			return createEndTime;
		}

		public void setCreateEndTime(String createEndTime) {
			this.createEndTime = createEndTime;
		}

	}
}
