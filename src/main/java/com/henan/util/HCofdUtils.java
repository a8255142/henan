package com.henan.util;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Encoder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;

/**
 * 
 * 华测OFD工具
 * <功能详细描述>
 * 
 * @author  zj
 * @version  [版本号, 2018年12月3日]
 */
public class HCofdUtils
{
    private static Logger logger = LoggerFactory.getLogger(HCofdUtils.class);
    
    //PDF转OFD接口地址
    public final static String PDF_OFD_URL = "/api/ConvertApi/PdfOfdConvert";
    
    //OFD服务端签章
    public final static String SERVER_SIGN_URL =
        "/api/ServerSignApi/ApplyStamp";
    
    /**
     * PDF转成OFD文件
     * <功能详细描述>
     * @author  Administrator
     * @param pdfData PDFbase64string
     * @return
     */
    public static String pdfOfdConvert(String pdfData)
    {
        String result = null;
        Map<String, String> params = Maps.newHashMap();
        String RequestId = String.valueOf(java.util.UUID.randomUUID());
        RequestId = RequestId.substring(0, 8);
        params.put("AppId", Config.getConfig("HC_APP_ID"));
        params.put("RequestId", RequestId);
        params.put("DocData", pdfData);
        String body = JSON.toJSONString(params);
        String baseUrl = "http://" + Config.getConfig("hc_url");
        try
        {
            String re = PostJson(baseUrl + PDF_OFD_URL, body);
            String errorStr = convertError(re);
            logger.info("转换服务返回:" + errorStr);
            if (StringUtils.isEmpty(errorStr))
            {
                result = re;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.error("PDF转OFD异常：", e);
        }
        logger.error("PDF转OFD异常结束");
        return result;
        
    }
    
    public static Map<String, BufferedImage> convertToImages(InputStream input)
    {
        
        final Map<String, BufferedImage> result =
            new HashMap<String, BufferedImage>();
        Document docOut = new Document();
        docOut.setMargins(0, 0, 0, 0);
        try
        {
            docOut.open();
            PDDocument doc = PDDocument.load(input);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for (int i = 0; i < pageCount; i++)
            {
                long t1 = System.currentTimeMillis();
                ByteArrayOutputStream bb = new ByteArrayOutputStream();
                BufferedImage image = renderer.renderImage(i, 1.25f); //第二个参数越大生成图片分辨率越高。
                long t2 = System.currentTimeMillis();
                ImageIO.write(image, "jpg", bb);
                long t3 = System.currentTimeMillis();
                Image jpg = Image.getInstance(bb.toByteArray());
                long t4 = System.currentTimeMillis();
                jpg.scalePercent(80.0f); //此处百分比与前面的分辨率参数相乘结果为1，则可保证等比输出。
                jpg.setAlignment(Image.ALIGN_CENTER);
                docOut.add(jpg);
                long t5 = System.currentTimeMillis();
                System.out.println((t2 - t1) + "***" + (t3 - t2) + "***"
                    + (t4 - t3) + "***" + (t5 - t4));
                
                result.put("img" + i, image);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (DocumentException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public static void main(String[] args)
        throws IOException
    {
        //pdfToImageToPdf();
        /*String docBase64 = FileEncodeBase64("D:\\cc.pdf");
        String result = pdfOfdConvert(docBase64);
        byte[] buffer = Encodes.decodeBase64(result);
        FileOutputStream out = new FileOutputStream("D:\\V1.ofd");
        out.write(buffer);s
        out.close();*/
        //String docBase64 = FileEncodeBase64("d:/23.ofd");
        
        FileOutputStream fos = new FileOutputStream("D:/pdf/33.jpg");
        FileReader reader = new FileReader(new File("D:/pdf/33.txt"));
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
        String res = "";
        String line;
        //网友推荐更加简洁的写法
        while ((line = br.readLine()) != null)
        {
            res = res + line;
        }
        
        fos.write(Base64.decodeBase64(res));
        fos.flush();
        fos.close();
        //HCofdUtils.Convert1("23.ofd");
        /*FileOutputStream fos = new FileOutputStream("D:/pdf/2111.ofd");
        FileReader reader = new FileReader(new File("D:/pdf/12.jpg"));
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
        String res = "";
        String line;
        //网友推荐更加简洁的写法
        while ((line = br.readLine()) != null)
        {
            res = res + line;
        }*/
        /*String docBase64 = FileEncodeBase64("D:/pdf/1558007114996.jpg");
        System.out.println("=========");
        System.out.println(docBase64);
        FileOutputStream fos = new FileOutputStream("D:/pdf/tupian.txt");
        fos.write(Base64.decodeBase64(docBase64));
        fos.flush();
        fos.close();*/
        System.out.println("ok");
    }
    
    /**
     * 错误码转换
     * <功能详细描述>
     * @author  Administrator
     * @param res
     * @return
     */
    private static String convertError(String res)
    {
        String errorMsg = null;
        if ("-1001".equals(res))
        {
            errorMsg = "请求中未找到申请数据";
        }
        else if ("-1051".equals(res))
        {
            errorMsg = "AppId 错误";
        }
        else if ("-1052".equals(res))
        {
            errorMsg = "文件 base64 编码错误";
        }
        else if ("-1056".equals(res))
        {
            errorMsg = "格式转换失败";
        }
        else if ("-9".equals(res))
        {
            errorMsg = "接口异常";
        }
        return errorMsg;
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
    
    public static String ServerSign(String docBase64, String rule)
    {
        String res = null;
        JSONObject jsonObj = new JSONObject();
        String businessNum = String.valueOf(java.util.UUID.randomUUID());
        businessNum = businessNum.substring(0, 8);
        jsonObj.put("businessNum", businessNum); // 流水号，不固定20位以内数字或英文字符，重复的流水号会导致合并签章失败
        
        String baseUrl = "http://" + Config.getConfig("hc_url");
        
        //外网测试
        //String appId = "0950609e-2116-11e8-b2ca-00163e0027a9";
        String appId = Config.getConfig("HC_APP_ID");
        jsonObj.put("appId", appId); // 固定值     
        jsonObj.put("policyType", "1"); // 固定值 
        //      -----------------------------------------
        
        //      -----------------------------------------
        //String sn = "2EABE957DAE3E052B0B13A3F8D59D2C0";
        String sn = Config.getConfig("HC_CHANNEL_ID");
        sn = sn.toUpperCase();
        jsonObj.put("channelId", sn); // 固定值,服务端证书序列号
        //测试
        String userCert = Config.getConfig("HC_USER_CERT");
        jsonObj.put("userCert", userCert); // policyType=1 时，这个值为调用者的证书序列号
        
        JSONObject documentInfo = new JSONObject();
        documentInfo.put("docuName", "sign.ofd"); // 文档名称
        documentInfo.put("fileDesc", ""); // 文档说明
        documentInfo.put("docBase64", docBase64); // 文档base64编码  docBase64
        jsonObj.put("documentInfo", documentInfo);
        
        JSONArray ruleList = new JSONArray(); // 签章规则，应用程序根据需要动态添加内容
        //外网测试
        //ruleList.add("HNCA_10000071");//测试单页1
        rule = StringUtils.trim(rule);
        ruleList.add(rule);//测试单页1
        
        jsonObj.put("ruleList", ruleList);
        
        String strJson = jsonObj.toString();
        logger.info(strJson);
        long startTime = System.currentTimeMillis();
        Map<String, String> headers = Maps.newHashMap();
        try
        {
            headers.put("Content-Type", "application/json;utf-8");
            String re = PostJson(baseUrl + SERVER_SIGN_URL, strJson);
            logger.info("华测签章返回：" + re);
            JSONObject jsonRes = JSONObject.parseObject(re);
            if (jsonRes.getString("ErrorCode").equals("0"))
            { // 获取错误码
                res = jsonRes.getString("SignDoc");
            }
            else
            {
                logger.info("出现错误");
                logger.info(jsonRes.getString("ErrorCode"));
                logger.info(jsonRes.getString("ErrorMsg")); // 打印错误信息
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.error("服务器签章异常：", e);
        }
        
        long endTime = System.currentTimeMillis();
        float seconds = (endTime - startTime) / 1000F;
        
        System.out.println("服务器签章耗时:" + Float.toString(seconds));
        res = res.trim();
        return res;
    }
    
    public static String PostJson(String urlpath, String json)
    {
        String strUrl = urlpath;
        logger.info("请求地址：" + strUrl);
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
            logger.error("请求转换服务异常:", ex);
            System.out.println(ex.getMessage());
            return "";
        }
    }
    
    public static String Convert(String fileName)
        throws IOException
    {
        String ServerHost = Config.getConfig("hc_url");
        String AppId = Config.getConfig("HC_APP_ID");
        String url =
            "http://" + ServerHost + "/api/ConvertApi/ThumbnailConvert";
        logger.info("转换图片地址：" + url);
        logger.info("AppId:" + AppId);
        String docBase64 = FileEncodeBase64("c:/pdf/" + fileName);
        //      docBase64 = FileEncodeBase64("/Users/ddw/Downloads/北京CA的签章文件1.pdf");
        String RequestId = String.valueOf(java.util.UUID.randomUUID());
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("AppId", AppId);
        jsonObj.put("RequestId", RequestId);
        jsonObj.put("DocData", docBase64);
        jsonObj.put("PageNum", "1");
        
        String json = jsonObj.toString();
        
        long startTime = System.currentTimeMillis();
        String res = PostJson(url, json);
        logger.info("转换图片返回：" + res);
        long endTime = System.currentTimeMillis();
        float seconds = (endTime - startTime) / 1000F;
        System.out.println("转换图片耗时:" + Float.toString(seconds));
        
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
            
            Long dt = new Date().getTime();
            
            FileOutputStream fos =
                new FileOutputStream("c:/pdf/" + dt + ".png");
            
            fos.write(Base64.decodeBase64(res));
            fos.close();
            ImgUtil.convert("c:/pdf/" + dt + ".png", "JPG", "c:/pdf/" + dt
                + ".jpg");//测试OK
            InputStream is = new FileInputStream("c:/pdf/" + dt + ".jpg");
            byte[] b = IOUtils.toByteArray(is);
            res = new BASE64Encoder().encode(b);
            res = res.replaceAll("\r|\n", "");
            System.out.println("Converted File has been writen Successfully!");
        }
        System.out.println("Program End!!!!!!!!!!!!!!!!!!!!!!!!");
        return res;
    }
    
    public static String Convert1(String fileName)
        throws IOException
    {
        String ServerHost = "47.92.38.124:8003";
        String url =
            "http://" + ServerHost + "/api/ConvertApi/ThumbnailConvert";
        String docBase64 = FileEncodeBase64("d:/pdf/" + fileName);
        //      docBase64 = FileEncodeBase64("/Users/ddw/Downloads/北京CA的签章文件1.pdf");
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
        System.out.println("转换图片耗时:" + Float.toString(seconds));
        
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
            Long dt = new Date().getTime();
            FileOutputStream fos =
                new FileOutputStream("d:/pdf/" + dt + ".png");
            fos.write(Base64.decodeBase64(res));
            fos.close();
            System.out.println("Converted File has been writen Successfully!");
        }
        System.out.println("Program End!!!!!!!!!!!!!!!!!!!!!!!!");
        return res;
    }
}
