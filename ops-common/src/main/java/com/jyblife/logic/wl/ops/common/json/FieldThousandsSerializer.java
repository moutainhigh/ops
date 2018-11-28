package com.jyblife.logic.wl.ops.common.json;

import com.alibaba.fastjson.serializer.BigDecimalCodec;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author yangcey
 * @date 2018/11/2
 **/
public class FieldThousandsSerializer implements ObjectSerializer {
    public static Logger logger =   LoggerFactory.getLogger(FieldThousandsSerializer.class);
    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        if (object == null) {
            serializer.out.writeNull();
            return;
        }
        try {
            DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
            String text = null;
            if (object instanceof String) {
                String obj = (String) object;
                text = decimalFormat.format(new BigDecimal(obj));
            } else if (object instanceof BigDecimal) {
                BigDecimal obj = (BigDecimal) object;
                text = decimalFormat.format(obj);
            } else if (object instanceof Integer) {
                Integer obj = (Integer) object;
                text = decimalFormat.format(obj);
            } else if (object instanceof Double) {
                Double obj = (Double) object;
                text = decimalFormat.format(obj);
            } else if (object instanceof Long) {
                Long obj = (Long) object;
                text = decimalFormat.format(obj);
            } else if (object instanceof Float) {
                Float obj = (Float) object;
                text = decimalFormat.format(obj);
            } else if (object instanceof Short) {
                Short obj = (Short) object;
                text = decimalFormat.format(obj);
            } else if (object instanceof BigInteger) {
                BigInteger obj = (BigInteger) object;
                text = decimalFormat.format(obj);
            } else if (object instanceof Byte) {
                Byte obj = (Byte) object;
                text = decimalFormat.format(obj);
            } else if (object instanceof Number) {
                Number obj = (Number) object;
                text = decimalFormat.format(obj);
            } else if (object instanceof AtomicInteger) {
                AtomicInteger obj = (AtomicInteger) object;
                text = decimalFormat.format(obj);
            } else if (object instanceof AtomicLong) {
                AtomicLong obj = (AtomicLong) object;
                text = decimalFormat.format(obj);
            } else {
                return;
            }
            serializer.write(text);
        }catch (Exception e){
            logger.error("{}",e);
        }
    }
}
