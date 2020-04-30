package com.henan.util;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImgUtil
{
    public static void main(String[] args)
    {
        System.out.println("Hello World!");
        
        ImgUtil.convert("D:\\ofd\\2.png", "JPG", "D:\\ofd\\2.jpg");//测试OK
    }
    
    public static void convert(String srcImageFile, String formatName,
        String destImageFile)
    {
        try
        {
            File f = new File(srcImageFile);
            f.canRead();
            f.canWrite();
            BufferedImage src = ImageIO.read(f);
            ImageIO.write(src, formatName, new File(destImageFile));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
