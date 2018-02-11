package com.xuyj.injectorview.processor.bean;
/*
 * 包名       com.xuyj.injectorview.processor.bean
 * 文件名:    ClassInfo
 * 创建者:    xuyj
 * 创建时间:  2018/2/11 on 09:48
 * 描述:     TODO
 */

public class ClassInfo
{
    /**
     * 注解字段所在的包名
     */
    private String packageName;
    /**
     * 注解字段所在的类名
     */
    private String className;
    /**
     * 新创建的类型
     */
    private String creatorClassName;

    public String getPackageName()
    {
        return packageName;
    }

    /**
     * 获取要创建的类的全路径
     *
     * @return
     */
    public String getClassFullPath()
    {
        return packageName + "." + creatorClassName;
    }

    public void setPackageName(String packageName)
    {
        this.packageName = packageName;
    }

    public String getClassName()
    {
        return className;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    public String getCreatorClassName()
    {
        return creatorClassName;
    }

    public void setCreatorClassName(String creatorClassName)
    {
        this.creatorClassName = creatorClassName;
    }
}
