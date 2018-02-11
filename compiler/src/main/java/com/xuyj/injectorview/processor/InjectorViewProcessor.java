package com.xuyj.injectorview.processor;

import com.xuyj.injectorview.processor.handler.AnnotationHandler;
import com.xuyj.injectorview.processor.handler.InjectorViewHandler;
import com.xuyj.injectorview.processor.writer.DefaultFileWriter;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

/*
 * 包名       com.xuyj.injectorview.processor
 * 文件名:    InjectorViewProcessor
 * 创建者:    xuyj
 * 创建时间:  2018/2/9 on 17:16
 * 描述:     TODO
 */

/**
 * 只支持该路径下的注解哦
 */
@SupportedAnnotationTypes("com.xuyj.injectorview.annotation.InjectorView")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class InjectorViewProcessor extends AbstractProcessor
{

    private AnnotationHandler mAnnotationHandler;
    private DefaultFileWriter mWriter;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment)
    {
        super.init(processingEnvironment);
        mAnnotationHandler = new InjectorViewHandler();
        mAnnotationHandler.attachProcessingEnv(processingEnvironment);
        mWriter = new DefaultFileWriter(processingEnvironment);
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment)
    {
        //处理注解
        Map<String, List<VariableElement>> map = mAnnotationHandler.handlerAnnotation(roundEnvironment);
        //生成文件
        mWriter.generate(map);
//        StringBuilder builder = new StringBuilder()
//                .append("package com.xuyj.injectorview.annotation;\n\n")
//                .append("public class GeneratedClass {\n\n") // open class
//                .append("\tpublic String zzzz() {\n") // open method
//                .append("\t\treturn \"");
//
//
//        // for each javax.lang.model.element.Element annotated with the CustomAnnotation
//        for (Element element : roundEnvironment.getElementsAnnotatedWith(InjectorView.class)) {
//            String objectType = element.getSimpleName().toString();
//
//
//            // this is appending to the return statement
//            builder.append(objectType).append(" says hello!\\n");
//        }
//
//
//        builder.append("\";\n") // end return
//                .append("\t}\n") // close method
//                .append("}\n"); // close class
//
//
//        try { // write the file
//            JavaFileObject source = processingEnv.getFiler().createSourceFile("com.xuyj.injectorview.annotation.GeneratedClass");
//
//
//            Writer writer = source.openWriter();
//            writer.write(builder.toString());
//            writer.flush();
//            writer.close();
//        } catch (IOException e) {
//            // Note: calling e.printStackTrace() will print IO errors
//            // that occur from the file already existing after its first run, this is normal
//        }

        return true;
    }
}
