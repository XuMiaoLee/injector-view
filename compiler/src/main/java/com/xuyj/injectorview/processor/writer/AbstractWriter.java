package com.xuyj.injectorview.processor.writer;
/*
 * 包名       com.xuyj.injectorview.processor.writer
 * 文件名:    AbstractWriter
 * 创建者:    xuyj
 * 创建时间:  2018/2/11 on 09:30
 * 描述:     TODO
 */

import com.xuyj.injectorview.processor.util.AnnotationUtils;
import com.xuyj.injectorview.processor.bean.ClassInfo;
import com.xuyj.injectorview.processor.util.IOUtils;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.VariableElement;
import javax.tools.JavaFileObject;

public abstract class AbstractWriter
{

    private       ProcessingEnvironment mProcessingEnv;
    private final Filer                 mFiler;

    public AbstractWriter(ProcessingEnvironment mProcessingEnv)
    {
        this.mProcessingEnv = mProcessingEnv;
        mFiler = mProcessingEnv.getFiler();
    }

    public void generate(Map<String, List<VariableElement>> map)
    {
        Iterator<Map.Entry<String, List<VariableElement>>> iterator = map.entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry<String, List<VariableElement>> entry = iterator.next();
            List<VariableElement> variableElements = entry.getValue();
            if (variableElements == null || variableElements.isEmpty())
            {
                continue;
            }
            ClassInfo info = createClassInfo(variableElements.get(0));
            JavaFileObject javaFileObject;
            Writer writer = null;
            try
            {
                javaFileObject = mFiler.createSourceFile(info.getClassFullPath());
                writer = javaFileObject.openWriter();
                generateImport(writer, info);
                for (VariableElement element : variableElements)
                {
                    writerField(writer, element, info);
                }
                writerEnd(writer);
                writer.flush();
            } catch (IOException e)
            {
                e.printStackTrace();
            } finally
            {
                IOUtils.closeQuitly(writer);
            }
        }
    }

    protected abstract void writerEnd(Writer writer) throws IOException;

    protected abstract void writerField(Writer writer, VariableElement element, ClassInfo info) throws IOException;

    /**
     * 写入improt代码，class
     *
     * @param writer
     * @param info
     */
    protected abstract void generateImport(Writer writer, ClassInfo info) throws IOException;

    /**
     * 创建注解类信息，方便写入新的class文件
     *
     * @param element
     * @return
     */
    private ClassInfo createClassInfo(VariableElement element)
    {
        Element enclosingElement = element.getEnclosingElement();
        ClassInfo info = new ClassInfo();
        //获取注解所在的类名
        info.setClassName(enclosingElement.getSimpleName().toString());
        //获取包路径
        info.setPackageName(AnnotationUtils.getPackageName(mProcessingEnv, enclosingElement));
        //要创建的新类名
        info.setCreatorClassName(info.getClassName() + "_ViewBinding");
        return info;
    }


}
