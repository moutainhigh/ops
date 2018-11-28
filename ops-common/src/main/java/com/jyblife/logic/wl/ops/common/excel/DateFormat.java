package com.jyblife.logic.wl.ops.common.excel;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface DateFormat {
    String format() default "yyyy-MM-dd HH:mm:ss";
}
