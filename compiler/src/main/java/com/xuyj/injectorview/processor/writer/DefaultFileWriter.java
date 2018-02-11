package com.xuyj.injectorview.processor.writer;
/*
 * 包名       com.xuyj.injectorview.processor.writer
 * 文件名:    DefaultFileWriter
 * 创建者:    xuyj
 * 创建时间:  2018/2/11 on 10:06
 * 描述:     TODO
 */

import com.xuyj.injectorview.annotation.InjectorView;
import com.xuyj.injectorview.processor.bean.ClassInfo;

import java.io.IOException;
import java.io.Writer;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Name;
import javax.lang.model.element.VariableElement;

public class DefaultFileWriter extends AbstractWriter
{
    public DefaultFileWriter(ProcessingEnvironment mProcessingEnv)
    {
        super(mProcessingEnv);
    }

    @Override
    protected void writerEnd(Writer writer) throws IOException
    {
        writer.write("  }");
        writer.write("\n\n");
        writer.write("} ");

    }

    @Override
    protected void writerField(Writer writer, VariableElement element, ClassInfo info) throws IOException
    {
        InjectorView annotation = element.getAnnotation(InjectorView.class);
        Name filedName = element.getSimpleName();
        writer.write("      target." + filedName + "= (" + element.asType() + ")target.findViewById(" + annotation.value() + ");");
        writer.write("\n");
    }

    @Override
    protected void generateImport(Writer writer, ClassInfo info) throws IOException
    {
        writer.write("package " + info.getPackageName() + ";");
        writer.write("\n\n");
        writer.write("import android.widget.TextView;");
        writer.write("\n");
        writer.write("public class " + info.getCreatorClassName() + "{");
        writer.write("\n\n");
        writer.write("  public " + info.getCreatorClassName() + "(" + info.getClassName() + " target){");
        writer.write("\n\n");
    }
}
