package com.jyblife.logic.wl.ops.manage.dto.req;

import org.apache.commons.lang3.StringUtils;

import com.jyblife.logic.wl.ops.common.utils.DateUtil;
import com.jyblife.logic.wl.ops.manage.dto.RequestPager;
import com.jyblife.logic.wl.ops.manage.dto.Searcher;

public class ReqOilPriceListDto extends RequestPager {

	private ReqOilPriceListDto.Search search;

	public ReqOilPriceListDto.Search getSearch() {
		return search;
	}

	public void setSearch(ReqOilPriceListDto.Search search) {
		this.search = search;
	}

	public static class Search implements Searcher {

		private String status; // 价格状态 1-待生效，2-已生效，3已失效
		private String effectStartTime; // 启用时间
		private String effectEndTime; // 启用时间
		private String unEffectStartTime; // 失效时间
		private String unEffectEndTime; // 失效时间
		private String createUserName; //创建人
		private String companyName; //油企名称
		private String stationName; //油站名称

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getEffectStartTime() {
			if(StringUtils.isNoneBlank(effectStartTime)) {
				effectStartTime = effectStartTime + DateUtil.START_TIME;
        	}
			return effectStartTime;
		}

		public void setEffectStartTime(String effectStartTime) {
			this.effectStartTime = effectStartTime;
		}

		public String getEffectEndTime() {
			if(StringUtils.isNoneBlank(effectEndTime)) {
				effectEndTime = effectEndTime + DateUtil.END_TIME;
        	}
			return effectEndTime;
		}

		public void setEffectEndTime(String effectEndTime) {
			this.effectEndTime = effectEndTime;
		}

		public String getUnEffectStartTime() {
			if(StringUtils.isNoneBlank(unEffectStartTime)) {
				unEffectStartTime = unEffectStartTime + DateUtil.START_TIME;
        	}
			return unEffectStartTime;
		}

		public void setUnEffectStartTime(String unEffectStartTime) {
			this.unEffectStartTime = unEffectStartTime;
		}

		public String getUnEffectEndTime() {
			if(StringUtils.isNoneBlank(unEffectEndTime)) {
				unEffectEndTime = unEffectEndTime + DateUtil.END_TIME;
        	}
			return unEffectEndTime;
		}

		public void setUnEffectEndTime(String unEffectEndTime) {
			this.unEffectEndTime = unEffectEndTime;
		}

		public String getCreateUserName() {
			return createUserName;
		}

		public void setCreateUserName(String createUserName) {
			this.createUserName = createUserName;
		}

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		public String getStationName() {
			return stationName;
		}

		public void setStationName(String stationName) {
			this.stationName = stationName;
		}
		
		//下划线get set 用于导出
		public String getEffect_start_time() {
			return effectStartTime;
		}

		public void setEffect_start_time(String effect_start_time) {
			this.effectStartTime = effect_start_time;
		}

		public String getEffect_end_time() {
			return effectEndTime;
		}

		public void setEffect_end_time(String effect_end_time) {
			this.effectEndTime = effect_end_time;
		}

		public String getUn_effect_start_time() {
			return unEffectStartTime;
		}

		public void setUn_effect_start_time(String un_effect_start_time) {
			this.unEffectStartTime = un_effect_start_time;
		}

		public String getUn_effect_end_time() {
			return unEffectEndTime;
		}

		public void setUn_effect_end_time(String un_effect_end_time) {
			this.unEffectEndTime = un_effect_end_time;
		}

		public String getCreate_user_name() {
			return createUserName;
		}

		public void setCreate_user_name(String create_user_name) {
			this.createUserName = create_user_name;
		}

		public String getCompany_name() {
			return companyName;
		}

		public void setCompany_name(String company_name) {
			this.companyName = company_name;
		}

		public String getStation_name() {
			return stationName;
		}

		public void setStation_name(String station_name) {
			this.stationName = station_name;
		}

	}
}
