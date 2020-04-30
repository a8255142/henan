package com.henan.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Encoder;

import com.alibaba.fastjson.JSON;
import com.henan.dao.HnLicenseSyncMapper;
import com.henan.dao.LicenseDataDao;
import com.henan.dao.TemplateFieldDao;
import com.henan.entity.HnLicenseSyncWithBLOBs;
import com.henan.entity.LicenseDetail;
import com.henan.entity.TemplateField;
import com.henan.util.Encodes;
import com.henan.util.GridFsUtil;
import com.henan.util.HCofdUtils;
import com.henan.util.OFDTransfUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.gridfs.GridFSDBFile;

import cpcns.util.FileTool;

@Service(value = "dataService")
public class DataService
{
    
    public static void main(String[] args)
        throws IOException
    {
        FileInputStream is;
        try
        {
            is =
                new FileInputStream(new File(
                    "C:\\Users\\Administrator\\Desktop\\中心城市\\zz\\46.ofd"));
            String s = Base64.encodeBase64String(IOUtils.toByteArray(is));
            System.out.println(s);
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    private final static Logger log = Logger.getLogger(DataService.class);
    
    public static Integer count = 0;
    
    public static String msg = "";
    
    private String qiye = "";
    
    private String xydm = "";
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Autowired
    private HnLicenseSyncMapper hnLicenseSyncMapper;
    
    @Autowired
    private LicenseDataDao licenseDataDao;
    
    @Autowired
    private TemplateFieldDao templateFieldDao;
    
    @Autowired
    private SaveService saveService;
    
    private Runnable run = null;//更新组件的线程 
    
    public List<Map<String, Object>> getLicenseData()
    {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Query query = new Query();
        query.addCriteria(Criteria.where("licenseNo").is("cccc"));
        Document licenseInfoMaps =
            mongoTemplate.findOne(query, Document.class, "20180817ZZBH8164");
        System.out.println("ok----------:" + JSON.toJSONString(licenseInfoMaps));
        return list;
    }
    
    public void saveData(Map<String, String> params, final JLabel lblNewLabel,
        final JLabel infolab)
        throws Exception
    {
        log.info("开始保存数据");
        
        run = new Runnable()
        {//实例化更新组件的线程  
                public void run()
                {
                    lblNewLabel.setText("插入条数：" + count.toString());
                    infolab.setText(msg);
                }
            };
        msg = "开始保存数据";
        SwingUtilities.invokeLater(run);//将对象排到事件派发线程的队列中  
        String code = null;
        String licenseNo = null;
        InputStream is = null;
        String b = null;
        String licenseFileNumber = null;
        String jsonData = null;
        String timestamp = null;
        
        List<HnLicenseSyncWithBLOBs> list =
            hnLicenseSyncMapper.findByLimit(params);
        for (HnLicenseSyncWithBLOBs lic : list)
        {
            String rule = lic.getRule();
            code = lic.getCatalogId();
            if (StringUtils.isEmpty(rule))
            {
                log.info("code:" + code + ";证照签章规则为空");
                continue;
            }
            log.info("开始组装数据");
            
            licenseNo = lic.getLicenseNumber();
            params = new HashMap<String, String>();
            
            params.put("code", code);
            params.put("licenseNo", licenseNo);
            LicenseDetail licData = licenseDataDao.selectByParams(params);
            timestamp = String.format("%010d", licData.getId());
            //证照文件编号规则为：证照颁发机构代码+持证人代码+证照类型代码+流水号+版本号
            licenseFileNumber =
                lic.getIssueUnit() + licData.getHoldByNo()
                    + licData.getLicenceTypeCode() + timestamp + "0001";
            lic.setLicenseFileNumber(licenseFileNumber);
            lic.setCreateDate(lic.getInputDate());
            
            lic.setSyncState("0");
            //jsonData = getTemFiledJson(licData);
            //lic.setNewAddMetadata(jsonData);
            lic.setNewAddMetadata("无");
            lic.setCatalogId("77878095e8484b4db146c7f56c62ab89");
            lic.setLicenceType(lic.getLicenseName());
            lic.setFileType("OFD");
            lic.setLicenseFile("无");
            lic.setLicenseBaseImage("无");
            lic.setState("6");
            /*is = getMongoFile(licenseNo, code, rule);
            if (is != null)
            {
                is.reset();
                b = Base64.encodeBase64String(IOUtils.toByteArray(is));
                lic.setLicenseFile(b);
                String img = HCofdUtils.Convert(licenseNo + ".ofd");
                if (StringUtils.isNotEmpty(img))
                {
                    lic.setLicenseBaseImage(img);
                }
                else
                {
                    lic.setLicenseBaseImage("null");
                }
                
            }
            else
            {
                log.info("证照文件为空");
                lic.setLicenseFile("null");
            }*/
            BasicDBObject basicDBObject = getMongoData(lic.getObjectId(), code);
            if (basicDBObject != null)
            {
                log.info("有MongoDB颁发数据");
                lic.setIssueDate(basicDBObject.getString("CLRQ"));
                lic.setValidTimeBegin(basicDBObject.getString("CLRQ"));
            }
            else
            {
                lic.setValidTimeBegin("-");
                log.info("没有MongoDB颁发数据");
            }
            /*is = getMongoPicFile(licData.getLicenseId());
            if (is != null)
            {
                b = Base64.encodeBase64String(IOUtils.toByteArray(is));
                lic.setLicenseBaseImage(b);
            }
            else
            {
                lic.setLicenseBaseImage("null");
            }*/
            //GoThread goThread = new GoThread(lic);
            //goThread.start();
            //77878095e8484b4db146c7f56c62ab89
            lic.setCatalogId("77878095e8484b4db146c7f56c62ab89");
            //lic.setLicenseNumber("无");
            
            try
            {
                log.info("插入数据id:" + licData.getId());
                saveService.save(lic);
                /*HashMap<String, Object> param = Maps.newHashMap();
                param.put("status", "3");
                param.put("code", code);
                param.put("licenseNo", licenseNo);
                saveService.saveLicense(param);*/
                
            }
            catch (Exception e)
            {
                log.error(e.getMessage(), e);
            }
            
            count++;
            log.info("已插入条数：" + count);
            SwingUtilities.invokeLater(run);//将对象排到事件派发线程的队列中  
            lic = null;
            licData = null;
            b = null;
            System.gc();
        }
        msg = "保存数据结束";
        SwingUtilities.invokeLater(run);//将对象排到事件派发线程的队列中  
        log.info("保存数据结束");
    }
    
    public String getTemFiledJson(LicenseDetail lic)
    {
        Map<String, Object> fieldMap = null;
        Map<String, String> params = new HashMap<String, String>();
        List<Map<String, Object>> fieldMaps = new ArrayList<>();
        params.put("code", lic.getCode());
        params.put("licenseNo", lic.getLicenseNo());
        List<TemplateField> list = templateFieldDao.selectByParams(params);
        int i = 1;
        Map<String, Object> values =
            getMongoValue(lic.getObjectId(), lic.getCode());
        if (values != null)
        {
            xydm = (String)values.get("TYXYDM");
            qiye = (String)values.get("MC");
        }
        for (TemplateField field : list)
        {
            String itemNo = field.getItemNo();
            fieldMap = new HashMap<String, Object>();
            fieldMap.put("_id", field.getId());
            fieldMap.put("t_name", field.getFieldName());
            fieldMap.put("t_key", StringUtils.lowerCase(field.getItemNo()));
            Object v = values.get(itemNo);
            if (v != null)
            {
                fieldMap.put("t_value", "");
            }
            else
            {
                fieldMap.put("t_value", v);
            }
            //数据项类型1:文本2:图片3:签章4:水印5:二维码6日期
            String type = field.getType();
            String t = "string";
            if ("1".equals(type))
            {
                t = "string";
            }
            else if ("2".equals(type))
            {
                t = "image";
            }
            else if ("6".equals(type))
            {
                t = "riqi";
            }
            else
            {
                continue;
            }
            fieldMap.put("type", t);
            fieldMap.put("length", "255");
            fieldMap.put("required", field.isPrimaryKey() ? "1" : "0");
            fieldMap.put("t_display", "1");
            fieldMap.put("t_sort", String.valueOf(i++));
            fieldMaps.add(fieldMap);
            /* "_id": "770b344e59a34ebfa9549201d546aeb1",//主键Id
             "t_name": "法定代表人",//字段名
             "t_key": "fddbr",//字段首写英文名
             "t_value": "林恺俊",//字段值
             "type": "string",//字段类型（string-字符串，riqi-日期，image-图片）
             "length": "255",//字段长度
             "required": "1",//是否必填（1-必填，0-非必填）
             "t_display": "1",//是否显示（1-显示，0-隐藏）
             "t_sort": "2"//排序
            */
        }
        return JSON.toJSONString(fieldMaps);
    }
    
    private Map<String, Object> getMongoValue(String objectId, String code)
    {
        Map<String, Object> licenseInfoMap =
            mongoTemplate.findById(objectId, BasicDBObject.class, code);
        return licenseInfoMap;
    }
    
    /**
     * 证照文件
     * <功能详细描述>
     * @author  Administrator
     * @param licenseNo
     * @param code
     * @return
     * @throws Exception 
     */
    private InputStream getMongoFile(String licenseNo, String code, String rule)
        throws Exception
    {
        log.info("查询mongoFile数据");
        InputStream is = null;
        OFDTransfUtil ofdUtil = new OFDTransfUtil();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("code", code);
        params.put("licenseNo", licenseNo);
        GridFSDBFile gridFsFile = GridFsUtil.findOneFile("license", params);
        if (gridFsFile != null)
        {
            log.info("有mongoFile数据");
            is = gridFsFile.getInputStream();
            //String server_url = "http://220.197.219.236:8090/convert-issuer";
            /*log.info("ofd_server_url:" + server_url);
            if (StringUtils.isNotEmpty(server_url))
            {
                String fileName = (String)gridFsFile.get("filename");
                is = ofdUtil.saveTempFile(is, fileName);
            }*/
            //pdfToImageToPdf();
            byte[] b = IOUtils.toByteArray(is);
            
            String docBase64 = new BASE64Encoder().encode(b);
            String result = HCofdUtils.pdfOfdConvert(docBase64);
            //result = HCofdUtils.ServerSign(result, rule);
            byte[] buffer = Encodes.decodeBase64(result);
            params.put("licenseNo", licenseNo);
            params.put("type", "ofd");
            GridFsUtil.deleteFile("licenseOfd", params);
            params.put("size", buffer.length);
            
            params.put("contentType", "application/ofd");
            params.put("isCopy", false);
            
            params.put("filename", code + licenseNo + ".ofd");
            
            GridFsUtil.saveFile("licenseOfd", buffer, params);
            is = new ByteArrayInputStream(buffer);
            String path = "c:\\pdf\\";
            FileTool.saveTo(is, path + licenseNo + ".ofd");
        }
        else
        {
            log.info("无mongoFile数据");
        }
        return is;
    }
    
    /**
     * 底图图片
     * <功能详细描述>
     * @author  Administrator
     * @param licenseId
     * @return
     */
    private InputStream getMongoPicFile(Integer licenseId)
    {
        log.info("查询mongoPicFile数据");
        InputStream is = null;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("licenseId", licenseId);
        params.put("page", 1);
        params.put("isCopy", false);
        GridFSDBFile picFile = GridFsUtil.findOneFile("templateImg", params);
        if (picFile != null)
        {
            is = picFile.getInputStream();
        }
        return is;
    }
    
    class GoThread extends Thread
    {
        
        GoThread(HnLicenseSyncWithBLOBs lic)
        {
            this.lic = lic;
        }
        
        public HnLicenseSyncWithBLOBs lic;
        
        public void run()
        {
            hnLicenseSyncMapper.insert(lic);
            count++;
            log.info("已插入条数：" + count);
        }
    }
    
    /**
     * 照面数据
     * <功能详细描述>
     * @author  Administrator
     * @param licenseNo
     * @param code
     * @return
     * @throws Exception 
     */
    private BasicDBObject getMongoData(String objectId, String code)
        throws Exception
    {
        log.info("查询mongodata数据：objectId:" + objectId + ";code:" + code);
        ByteArrayOutputStream bfis = null;
        Map<String, Object> params = new HashMap<String, Object>();
        BasicDBObject licenseInfoMap =
            mongoTemplate.findById(objectId, BasicDBObject.class, code);
        return licenseInfoMap;
    }
    
}
