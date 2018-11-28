package com.jyblife.logic.wl.ops.remote.json;

import com.alibaba.fastjson.serializer.NameFilter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSONNameFilter implements NameFilter {

    private String[] includes = new String[]{"total_pages", "total_rows", "page_size", "search_items", "page_count", "monthly_sum"};
    private Pattern pattern = Pattern.compile("_(\\w)");

    @Override
    public String process(Object o, String s, Object o1) {

        for (String name : includes) {
            if (name.equals(s)) {
                s = this.translate(s);
                return s;
            }
        }
        return s;
    }

    private String translate(String source) {
        StringBuffer sb = new StringBuffer();
        Matcher m = pattern.matcher(source);
        while (m.find()) {
            m.appendReplacement(sb, m.group(1).toUpperCase());
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
