package com.framex.soa.service;


import java.lang.annotation.*;

/**
 * @author lijie
 * @date 2017/11/14 20:59
 * @description
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Service {

    String name();

    String version();

    String group() default "defaultGroup";
}
