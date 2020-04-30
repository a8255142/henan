package com.henan.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cpcns.http.HttpConnection;
import cpcns.http.HttpMethod;
import cpcns.http.HttpResponse;
import cpcns.util.FileTool;
import cpcns.util.WebMocker;

/*
 * 文件转OFD工具
 * 描    述:  1.通过传送文件到转换服务器转换成OFD
 *           2.转换成功后根据返回编号查询已转换好的文件
 * 修 改 人:  zj
 * 修改时间:  2018\8\22 0022
 */
public class OFDTransfUtil
{
    
    private final static Logger log = Logger.getLogger(OFDTransfUtil.class);
    
    public static void main(String args[])
        throws MalformedURLException, IOException
    {
        OFDTransfUtil con = new OFDTransfUtil();
        ArrayList<String> list = new ArrayList<String>();
        list.add("D:\\ccc.pdf");
        try
        {
            //调用转换方法
            con.convert(list,
                "D:\\ccc.ofd",
                "测试文件",
                "http://220.197.219.236:8090/convert-issuer");
        }
        catch (Exception e)
        {
            System.out.print(e.toString());
        }
        
    }
    
    /**
     *
     * @param list 数据源结合
     * @param outfile 输出路径
     * @throws Exception
     */
    public void convert(List<String> list, String outfile, String fileName,
        String server_url)
        throws Exception
    {
        //EnvelopeOptions el= new EnvelopeOptions();
        //el.setPassword("aaa");
        Map<String, String> meta = new HashMap<String, String>();
        //设置将要嵌入到最终生成OFD中的元数据
        meta.put("DocID", fileName + "22222");
        meta.put("Title", fileName);
        Map<String, String> custems = new HashMap<String, String>();
        //设置将要嵌入到最终生成OFD中的元q数据
        //custems.put("attachID", "0002");
        //将元数据集合及待转文档按照规范打成数据源zip包
        File zip = ZipUtil.zipFile(meta, custems, list, null, "");
        //File zip = ZipUtil2.zipFile(meta,custems, list,null);
        //WebMocker是一个模拟http请求的封装类l
        //http://127.0.0.1:8080/cpcns-convert-server/upload
        System.out.println(zip);
        WebMocker http = new WebMocker(server_url);
        //调用上传接口将数据源包上传并获得任务“票据”
        //(1)==========================================++++++++++
        HttpConnection con = HttpConnection.connect(server_url + "/upload");
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("file", zip);
        //data.put("token", "00000");
        con = con.data(data);
        con = con.method(HttpMethod.UPLOAD);
        HttpResponse res = con.execute();
        String ticket = res.bodyAsText();
        System.out.println(ticket);
        //(1)end=============================================
        //(2)================================================
        //String ticket = http.upload(zip);
        //(2)end=============================================
        //
        String result = null;
        while (true)
        {
            try
            {
                Thread.sleep(5 * 1000);
            }
            catch (InterruptedException e)
            {
                System.out.println(e.getMessage());
            }
            //每500毫秒依据任务“票据”查询转换状态
            result = http.query(ticket);
            if (result == null || result.equals(7000))
            {
                System.out.println("转换因为未知原因失败");
                break;
            }
            if (!result.matches("-?\\d+"))
            {
                break;
            }
        }
        //	转换完成后得到下载链接，将其保存为指定路径
        InputStream is = http.down(result);
        FileTool.saveTo(is, outfile);
    }
    
    /**
     *
     * @param list 数据源结合
     * @param outfile 输出路径
     * @throws Exception
     */
    public void convert1(List<String> list, String outfile, String fileName,
        String server_url)
        throws Exception
    {
        //EnvelopeOptions el= new EnvelopeOptions();
        //el.setPassword("aaa");
        Map<String, String> meta = new HashMap<String, String>();
        //设置将要嵌入到最终生成OFD中的元数据
        meta.put("DocID", fileName + "22222");
        meta.put("Title", fileName);
        Map<String, String> custems = new HashMap<String, String>();
        //设置将要嵌入到最终生成OFD中的元q数据
        //custems.put("attachID", "0002");
        //将元数据集合及待转文档按照规范打成数据源zip包
        File zip = ZipUtil.zipFile(meta, custems, list, null, "");
        //File zip = ZipUtil2.zipFile(meta,custems, list,null);
        //WebMocker是一个模拟http请求的封装类l     
        //http://127.0.0.1:8080/cpcns-convert-server/upload
        System.out.println(zip);
        WebMocker http = new WebMocker(server_url);
        //调用上传接口将数据源包上传并获得任务“票据”
        //(1)==========================================++++++++++
        HttpConnection con = HttpConnection.connect(server_url + "/upload");
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("file", zip);
        //data.put("token", "00000");
        con = con.data(data);
        con = con.method(HttpMethod.UPLOAD);
        HttpResponse res = con.execute();
        String ticket = res.bodyAsText();
        System.out.println(ticket);
        //(1)end=============================================
        //(2)================================================
        //String ticket = http.upload(zip);
        //(2)end=============================================
        //
        String result = null;
        while (true)
        {
            try
            {
                Thread.sleep(5 * 1000);
            }
            catch (InterruptedException e)
            {
                System.out.println(e.getMessage());
            }
            //每500毫秒依据任务“票据”查询转换状态
            result = http.query(ticket);
            if (result == null || result.equals(7000))
            {
                System.out.println("转换因为未知原因失败");
                break;
            }
            if (!result.matches("-?\\d+"))
            {
                break;
            }
        }
        //	转换完成后得到下载链接，将其保存为指定路径
        InputStream is = http.down(result);
        FileTool.saveTo(is, outfile);
    }
    
    /**
     *
     * @param
     * @throws Exception
     */
    public InputStream convert(String filePath, String fileName)
        throws Exception
    {
        
        String server_url = "http://220.197.219.236:8090/convert-issuer";
        Map<String, String> meta = new HashMap<String, String>();
        //设置将要嵌入到最终生成OFD中的元数据
        meta.put("Title", fileName);
        Map<String, String> custems = new HashMap<String, String>();
        List<String> list = new ArrayList<>();
        
        list.add(filePath);
        //将元数据集合及待转文档按照规范打成数据源zip包
        File zip = ZipUtil.zipFile(meta, custems, list, null, "");
        System.out.println(zip);
        WebMocker http = new WebMocker(server_url);
        //调用上传接口将数据源包上传并获得任务“票据”
        //(1)==========================================++++++++++
        HttpConnection con = HttpConnection.connect(server_url + "/upload");
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("file", zip);
        con = con.data(data);
        con = con.method(HttpMethod.UPLOAD);
        HttpResponse res = con.execute();
        String ticket = res.bodyAsText();
        System.out.println(ticket);
        //(1)end=============================================
        //(2)================================================
        //String ticket = http.upload(zip);
        //(2)end=============================================
        //
        String result = null;
        while (true)
        {
            try
            {
                Thread.sleep(5 * 1000);
            }
            catch (InterruptedException e)
            {
                System.out.println(e.getMessage());
            }
            //每500毫秒依据任务“票据”查询转换状态
            result = http.query(ticket);
            if (result == null || result.equals(7000))
            {
                log.error("转换因为未知原因失败");
                break;
            }
            if (!result.matches("-?\\d+"))
            {
                break;
            }
        }
        //	转换完成后得到下载链接，将其保存为指定路径
        InputStream is = http.down(result);
        return is;
    }
    
    /**
     * 保存临时文件
     * @param is
     * @param fileName
     * @throws IOException
     */
    public InputStream saveTempFile(InputStream is, String fileName)
        throws Exception
    {
        String path = this.getClass().getResource("/").getPath();
        if (path.contains("target"))
        {
            path = "src/main/webapp/static/imgtemp/";
        }
        else
        {
            path = path + "/";
        }
        path = "E:\\pdf\\";
        FileTool.saveTo(is, path + fileName);
        InputStream ofdIs = convert(path + fileName, fileName);
        FileTool.saveTo(ofdIs, path + fileName.replaceAll("pdf", "ofd"));
        return ofdIs;
    }
    
}
