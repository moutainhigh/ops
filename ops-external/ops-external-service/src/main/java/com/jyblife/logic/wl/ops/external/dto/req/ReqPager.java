package com.jyblife.logic.wl.ops.external.dto.req;

import com.jyblife.logic.wl.ops.common.contants.Constants;

/**
 * @author yangcey
 * @date 2018/9/28
 **/
public class ReqPager {

    protected Integer page = Constants.page;
    protected Integer pageSize = Constants.pageSize;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
