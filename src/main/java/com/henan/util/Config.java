package com.henan.util;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Config
{
    private static Properties p = new Properties();
    
    private final static Logger log = Logger.getLogger(Config.class);
    static
    {
        try
        {
            String path = Config.class.getResource("/").toURI().getPath();
            log.info("properties路径：" + path);
            p.load(new FileInputStream(path + "dzzz.properties"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static String getConfig(String key)
    {
        return p.getProperty(key);
    }
    
    public static void setConfig(String key, String value)
    {
        p.setProperty(key, value);
    }
    
    public static Properties getAllSettings()
    {
        return p;
    }
}
