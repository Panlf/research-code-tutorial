package com.plf.boot.plugin.annotations;

import java.lang.annotation.*;

/**
 * @author panlf
 * @date 2022/7/13
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface EnableTableScan {
    boolean enable() default false;
}
