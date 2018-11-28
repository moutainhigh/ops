package com.jyblife.logic.wl.ops.manage.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.jyblife.logic.wl.ops.common.json.DateAppendEndTimeDeserializer;
import com.jyblife.logic.wl.ops.common.json.DateAppendStartTimeDeserializer;

/**
 * @author yangcey
 * @date 2018/10/29
 **/
public class OilPriceApplyListDto extends RequestPager {

    private OilPriceApplyListDto.Search search;

    public OilPriceApplyListDto.Search getSearch() {
        return search;
    }

    public void setSearch(OilPriceApplyListDto.Search search) {
        this.search = search;
    }

    /**
     * 定义搜索条件
     */
    public class Search implements Searcher {
        /**
         * 申请编码
         */
        private String code;
        /**
         * 审核状态
         */
        private Integer status;
        //创建人
        private String createUserName;
        //申请时间范围 开始时间
        @JSONField(deserializeUsing = DateAppendStartTimeDeserializer.class)
        private String startCreateTime;
        //申请时间范围 结束时间
        @JSONField(deserializeUsing = DateAppendEndTimeDeserializer.class)
        private String endCreateTime;

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

        public String getStartCreateTime() {
            return startCreateTime;
        }

        public void setStartCreateTime(String startCreateTime) {
            this.startCreateTime = startCreateTime;
        }

        public String getEndCreateTime() {
            return endCreateTime;
        }

        public void setEndCreateTime(String endCreateTime) {
            this.endCreateTime = endCreateTime;
        }
    }
}
