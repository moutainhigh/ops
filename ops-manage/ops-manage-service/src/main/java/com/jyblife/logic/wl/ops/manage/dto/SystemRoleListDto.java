package com.jyblife.logic.wl.ops.manage.dto;

/**
 * @author yangcey
 * @date 2018/9/19
 **/
public class SystemRoleListDto extends RequestPager {

    private Search search;

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    public class Search implements Searcher {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
