package com.jyblife.logic.wl.ops.manage.dto;

import org.apache.commons.lang3.StringUtils;

import com.jyblife.logic.wl.ops.common.utils.DateUtil;

/**
 * @author yangcey
 * @date 2018/9/25
 **/
public class LogisticsLimitListDto extends RequestPager {
    private Search search;

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    /**
     * 定义搜索条件
     */
    public static class Search implements Searcher {

        private String createTimeStart;
        private String createTimeEnd;
        private String code;

        public String getCreateTimeStart() {
        	if(StringUtils.isNoneBlank(createTimeStart)) {
        		createTimeStart = createTimeStart + DateUtil.START_TIME;
        	}
            return createTimeStart;
        }

        public void setCreateTimeStart(String createTimeStart) {
            this.createTimeStart = createTimeStart;
        }

        public String getCreateTimeEnd() {
        	if(StringUtils.isNoneBlank(createTimeEnd)) {
        		createTimeEnd = createTimeEnd + DateUtil.END_TIME;
        	}
            return createTimeEnd;
        }

        public void setCreateTimeEnd(String createTimeEnd) {
            this.createTimeEnd = createTimeEnd;
        }

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}
        
    }
}