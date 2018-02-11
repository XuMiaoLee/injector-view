package com.xuyj.injectorview.processor.util;
/*
 * 包名       com.xuyj.injectorview.processor
 * 文件名:    AnnotationUtils
 * 创建者:    xuyj
 * 创建时间:  2018/2/11 on 09:41
 * 描述:     TODO
 */

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;

public class AnnotationUtils
{
    /**
     * 获取包名
     *
     * @param env
     * @param element
     * @return
     */
    public static String getPackageName(ProcessingEnvironment env, Element element)
    {
        return env.getElementUtils().getPackageOf(element).getQualifiedName().toString();
    }
}
