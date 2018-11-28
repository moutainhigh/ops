package com.jyblife.logic.wl.ops.common.json;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.jyblife.logic.wl.ops.common.contants.Constants;
import com.jyblife.logic.wl.ops.common.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;

/**
 * @author yangcey
 * @date 2018/11/16
 **/
public class DateAppendEndTimeDeserializer implements ObjectDeserializer {
    @Override
    public  String deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        String date = parser.getLexer().stringVal();
        return StringUtils.isNotBlank(date)?(date+ DateUtil.END_TIME):date;
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}