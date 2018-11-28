package com.jyblife.logic.wl.ops.common.command.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface CommandService {
    String value();
}
