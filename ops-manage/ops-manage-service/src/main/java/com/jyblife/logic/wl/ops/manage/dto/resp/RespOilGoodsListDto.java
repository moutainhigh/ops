package com.jyblife.logic.wl.ops.manage.dto.resp;

/**
 * @author yangcey
 * @date 2018/9/20
 **/
public class RespOilGoodsListDto extends RespPager {

    public static class GoodsItem {
        private String goodsId;
        private String name;
        private String code;
        private String sort;
        private String remark;
        private String status;
        private String statusName;
        private boolean isCanView;
        private boolean isCanEdit;

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }

        public boolean getIsCanView() {
            return isCanView;
        }

        public void setIsCanView(boolean canView) {
            isCanView = canView;
        }

        public boolean getIsCanEdit() {
            return isCanEdit;
        }

        public void setIsCanEdit(boolean canEdit) {
            isCanEdit = canEdit;
        }
    }
}
