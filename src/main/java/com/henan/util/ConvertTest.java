package com.henan.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Encoder;

import com.alibaba.fastjson.JSONObject;

public class ConvertTest
{
    
    //public static String ServerHost = "localhost:44580";
    
    public static String ServerHost = "47.92.38.124:8003";
    
    public static void main(String[] args)
        throws IOException
    {
        Convert();
        
    }
    
    public static void Convert()
        throws IOException
    {
        String url =
            "http://" + ServerHost + "/api/ConvertApi/ThumbnailConvert";
        //String docBase64= FileEncodeBase64("./1.pdf");
        String docBase64 = FileEncodeBase64("d:/12.ofd");
        //		docBase64 = FileEncodeBase64("/Users/ddw/Downloads/北京CA的签章文件1.pdf");
        String RequestId = String.valueOf(java.util.UUID.randomUUID());
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("AppId", "0950609e-2116-11e8-b2ca-00163e0027a9");
        jsonObj.put("RequestId", RequestId);
        jsonObj.put("DocData", docBase64);
        jsonObj.put("PageNum", "1");
        
        String json = jsonObj.toString();
        long startTime = System.currentTimeMillis();
        String res = PostJson(url, json);
        long endTime = System.currentTimeMillis();
        float seconds = (endTime - startTime) / 1000F;
        System.out.println("服务器转换耗时:" + Float.toString(seconds));
        
        Boolean isReturnError = true;
        try
        {
            int a = Integer.parseInt(res);
        }
        catch (Exception e)
        {
            isReturnError = false;
        }
        if (isReturnError == false)
        {
            FileOutputStream fos = new FileOutputStream("d:/Converted.png");
            fos.write(Base64.decodeBase64(res));
            fos.close();
            System.out.println(res);
            System.out.println("Converted File has been writen Successfully!");
        }
        System.out.println("Program End!!!!!!!!!!!!!!!!!!!!!!!!");
        return;
    }
    
    public static String FileEncodeBase64(String filepath)
    {
        File file = new File(filepath);
        try
        {
            InputStream fr = new FileInputStream(file);
            byte[] b = new byte[(int)file.length()];
            
            fr.read(b);
            fr.close();
            
            String s = new BASE64Encoder().encode(b);
            return s;
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }
    
    public static String PostJson(String urlpath, String json)
    {
        String strUrl = urlpath;
        
        try
        {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);
            conn.setRequestProperty("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1 ");
            conn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
            // conn.setRequestProperty("Content-Type",
            // "application/json;charset=utf-8");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.write(json.getBytes("utf-8"));
            out.flush();
            out.close();
            
            InputStream instream = conn.getInputStream();
            BufferedReader reader =
                new BufferedReader(new InputStreamReader(instream, "utf-8"));
            
            String line = "";
            StringBuffer sb = new StringBuffer();
            while ((line = reader.readLine()) != null)
            {
                line = new String(line.getBytes(), "utf-8");
                sb.append(line);
            }
            
            reader.close();
            conn.disconnect();
            
            return sb.toString();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return "";
        }
    }
    
}
