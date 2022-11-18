package com.myproject.enums;

import java.lang.annotation.*;


/**
 * @author lkxl
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR,ElementType.METHOD,ElementType.TYPE_PARAMETER,ElementType.TYPE_USE})
@Repeatable(MyAnnotations.class)
@Documented
@Inherited//可继承注解
public @interface MyAnnotation {
    String value() default "test";
}
