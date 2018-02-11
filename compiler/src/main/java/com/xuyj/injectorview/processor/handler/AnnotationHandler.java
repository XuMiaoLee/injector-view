package com.xuyj.injectorview.processor.handler;
/*
 * 包名       com.xuyj.injectorview.processor.handler
 * 文件名:    AnnotationHandler
 * 创建者:    xuyj
 * 创建时间:  2018/2/9 on 17:30
 * 描述:     TODO
 */

import java.util.List;
import java.util.Map;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.VariableElement;

public interface AnnotationHandler
{
    /**
     * 关联ProcessingEnvironment
     *
     * @param environment
     */
    void attachProcessingEnv(ProcessingEnvironment environment);

    /**
     * 用来处理注解字段，保存到Map中
     *
     * @param environment
     * @return
     */
    Map<String, List<VariableElement>> handlerAnnotation(RoundEnvironment environment);
}
