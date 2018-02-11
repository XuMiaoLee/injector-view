package com.xuyj.injectorview.annotation;
/*
 * 包名       com.xuyj.injectorview.annotation
 * 文件名:    InjectView
 * 创建者:    xuyj
 * 创建时间:  2018/2/11 on 10:39
 * 描述:     TODO
 */

import android.app.Activity;

import java.lang.reflect.InvocationTargetException;

public class InjectView
{

    public static final String CLASS_SUFFIX = "_ViewBinding";

    public static void inject(Activity activity)
    {
        String className = activity.getClass().getName() + CLASS_SUFFIX;
        try
        {
            Class.forName(className).getConstructor(activity.getClass()).newInstance(activity);
        } catch (InstantiationException e)
        {
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        } catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
    }
}
