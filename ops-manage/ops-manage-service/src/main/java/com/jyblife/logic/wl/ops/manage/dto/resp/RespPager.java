package com.jyblife.logic.wl.ops.manage.dto.resp;

import com.jyblife.logic.wl.ops.manage.dto.Searcher;

import java.util.List;

/**
 * @author yangcey
 * @date 2018/9/19
 **/
public abstract class RespPager<T> {

    protected Integer page;
    protected Integer pageSize;
    protected Integer totalPages;
    protected Integer totalRows;
    protected Searcher searchItems;
    protected List<T> data;

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

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Searcher getSearchItems() {
        return searchItems;
    }

    public void setSearchItems(Searcher searchItems) {
        this.searchItems = searchItems;
    }
}
