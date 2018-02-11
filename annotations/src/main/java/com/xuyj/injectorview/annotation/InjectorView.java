package com.xuyj.injectorview.annotation;
/*
 * 包名       com.xuyj.injectorview.annotation
 * 文件名:    InjectorView
 * 创建者:    xuyj
 * 创建时间:  2018/2/9 on 17:12
 * 描述:     TODO
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.CLASS)
public @interface InjectorView
{
    int value();
}
