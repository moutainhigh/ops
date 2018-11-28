package com.jyblife.logic.wl.ops.common.command.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface CommandMethod {
    String value();
}
