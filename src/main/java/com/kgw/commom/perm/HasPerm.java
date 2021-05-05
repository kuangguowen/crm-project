package com.kgw.commom.perm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @version 1.8
 * @Author kgw
 * @Date: 2021/5/2 17:29
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HasPerm {

    //是否有什么权限
    String perm() default "";
}
