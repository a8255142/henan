package com.henan.service;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

import org.apache.commons.io.IOUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class MainTest
{
    
    public void outMsg()
    {
        // TODO Auto-generated method stubq
        System.out.println("hello world");
    }
    
    public static void main(String[] args)
        throws IOException
    {
        toImg();
    }
    
    public static void toTxt()
        throws IOException
    {
        FileInputStream input = new FileInputStream(new File("D:/b/888.ofd"));
        
        byte[] b = IOUtils.toByteArray(input);
        
        String docBase64 = new BASE64Encoder().encode(b);
        
        File file = new File("D:/b/77.txt");
        FileWriter writer = new FileWriter(file);
        BufferedWriter out = new BufferedWriter(writer);
        out.write(docBase64);
        out.flush();
        System.out.println("ok");
    }
    
    public static void toOfd()
        throws IOException
    {
        FileReader reader = new FileReader(new File("D:/b/66.txt"));
        BufferedReader br = new BufferedReader(reader);
        
        String base64Code = "";
        String line;
        //网友推荐更加简洁的写法
        while ((line = br.readLine()) != null)
        {
            // 一次读入一行数据
            base64Code = base64Code + line;
        }
        br.close();
        File file = new File("D:/b/66.png");
        byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
        FileOutputStream out = new FileOutputStream(file);
        out.write(buffer);
        out.close();
        System.out.println("ok");
    }
    
    public static void toImg()
        throws IOException
    {
        FileReader reader = new FileReader(new File("D:/b/66.txt"));
        BufferedReader br = new BufferedReader(reader);
        
        String base64Code = "";
        String line;
        //网友推荐更加简洁的写法
        while ((line = br.readLine()) != null)
        {
            // 一次读入一行数据
            base64Code = base64Code + line;
        }
        br.close();
        File file = new File("D:/b/66.jpg");
        byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
        int len = buffer.length;
        byte n1 = buffer[len - 2];
        byte n2 = buffer[len - 1];
        byte b0 = buffer[0];
        byte b1 = buffer[1];
        byte b2 = buffer[2];
        byte b3 = buffer[3];
        byte b4 = buffer[4];
        byte b5 = buffer[5];
        byte b6 = buffer[6];
        byte b7 = buffer[7];
        byte b8 = buffer[8];
        byte b9 = buffer[9];
        System.out.println((char)b1);
        System.out.println((char)b2);
        System.out.println((char)b3);
        System.out.println((char)b4);
        /* byte[] imgContent = new byte[7];
         imgContent[0] = -119;
         imgContent[1] = (byte)'P';
         imgContent[2] = (byte)'N';
         imgContent[3] = (byte)'G';
         imgContent[4] = 13;
         imgContent[5] = 10;
         imgContent[6] = 26;
         byte[] bt3 = byteMerger(imgContent, buffer);*/
        /*FileOutputStream out = new FileOutputStream(file);
                out.write(buffer);
                out.close();*/
        InputStream input = new FileInputStream(file);
        BufferedImage src = ImageIO.read(input);
        ImageIO.write(src, "png", new File("D:/b/662.png"));

        Iterator<ImageReader> readers =
            ImageIO.getImageReadersByFormatName("png");
        ImageReader reader2 = (ImageReader)readers.next();
        ImageInputStream iis = ImageIO.createImageInputStream(file);
        reader2.setInput(iis, true);
        ImageIO.write(reader2.read(0), "png", file);
        
        System.out.println("width:" + reader2.getWidth(0));
        System.out.println("height:" + reader2.getHeight(0));

        ImageIO.read(input);
        System.out.println("ok");
    }
    
    public static byte[] byteMerger(byte[] bt1, byte[] bt2)
    {
        byte[] bt3 = new byte[bt1.length + bt2.length];
        System.arraycopy(bt1, 0, bt3, 0, bt1.length);
        System.arraycopy(bt2, 0, bt3, bt1.length, bt2.length);
        return bt3;
    }
    
}
