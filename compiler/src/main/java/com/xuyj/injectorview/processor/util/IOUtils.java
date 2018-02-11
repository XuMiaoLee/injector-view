package com.xuyj.injectorview.processor.util;
/*
 * 包名       com.xuyj.injectorview.processor.util
 * 文件名:    IOUtils
 * 创建者:    xuyj
 * 创建时间:  2018/2/11 on 10:04
 * 描述:     TODO
 */

import java.io.Closeable;
import java.io.IOException;

public class IOUtils
{
    public static void closeQuitly(Closeable closeable)
    {
        try
        {
            closeable.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
