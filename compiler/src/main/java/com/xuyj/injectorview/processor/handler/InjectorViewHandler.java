package com.xuyj.injectorview.processor.handler;
/*
 * 包名       com.xuyj.injectorview.processor.handler
 * 文件名:    InjectorViewHandler
 * 创建者:    xuyj
 * 创建时间:  2018/2/9 on 17:35
 * 描述:     TODO
 */

import com.xuyj.injectorview.annotation.InjectorView;
import com.xuyj.injectorview.processor.util.AnnotationUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.VariableElement;

public class InjectorViewHandler implements AnnotationHandler
{
    private ProcessingEnvironment mProcessingEnv;

    @Override
    public void attachProcessingEnv(ProcessingEnvironment environment)
    {
        mProcessingEnv = environment;
    }

    @Override
    public Map<String, List<VariableElement>> handlerAnnotation(RoundEnvironment environment)
    {
        //返回的数据
        Map<String, List<VariableElement>> map = new HashMap<String, List<VariableElement>>();
        Set<? extends Element> elementsSet = environment.getElementsAnnotatedWith(InjectorView.class);
        for (Element element : elementsSet)
        {
            VariableElement variableElement = (VariableElement) element;
            String className = getParentClass(variableElement);
            //获取该类下注解的所有字段
            List<VariableElement> cacheElements = map.get(className);
            if (cacheElements == null)
            {
                cacheElements = new ArrayList<VariableElement>();
            }
            cacheElements.add(variableElement);
            //key为类名，value为注解字段列表
            map.put(className, cacheElements);
        }
        return map;
    }

    /**
     * 根据注解获取到所在的类
     *
     * @param element
     * @return
     */
    private String getParentClass(VariableElement element)
    {
        //获取元素所在的类的类型
        Element enclosingElement = element.getEnclosingElement();
        String packageName = AnnotationUtils.getPackageName(mProcessingEnv, enclosingElement);
        //包名+类名
        return packageName + "." + enclosingElement.getSimpleName().toString();
    }
}
