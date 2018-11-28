package com.jyblife.logic.wl.ops.external.dto.resp;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author yangcey
 * @date 2018/10/31
 **/
public class RespOilPriceAdjustMsgListDto extends RespPager<RespOilPriceAdjustMsgListDto.OilPriceAdjustMsgItem> {

    public static class OilPriceAdjustMsgItem {
        private Integer id;
        private String content;
        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private Date effectTime;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Date getEffectTime() {
            return effectTime;
        }

        public void setEffectTime(Date effectTime) {
            this.effectTime = effectTime;
        }
    }

}
