package com.henan.util;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

/**
 * mongo文件操作工具类
 * 
 * @author dell
 *
 */
public class GridFsUtil
{
    
    private static final String[] KEY_WORDS = {"$ge", "$get", "$lt", "$lte",
        "$ne", "$in", "$nin", "$all", "$exists", "$or", "$nor", "$where",
        "$type"};
    
    private static MongoTemplate mongoTemplate =
        SpringContextHolder.getBean("mongoTemplate");//getBean(MongoTemplate.class);
    
    /**
     * 插入数据
     * 
     * @param collection
     *            集合(表)名
     * @param in
     *            文件输入流
     * @param params
     *            参数
     */
    public static void saveFile(String collection, InputStream in,
        Map<String, Object> params)
    {
        DB db = mongoTemplate.getDb();
        GridFS fs = new GridFS(db, collection);
        GridFSInputFile fsInput = fs.createFile(in);
        fsInput = buildParams(fsInput, params);
        fsInput.save();
    }
    
    public static void saveFile(String collection, byte[] bytes,
        Map<String, Object> params)
    {
        //		String licenseNo = (String) params.get("licenseNo");
        //		long t1 = System.currentTimeMillis();
        DB db = mongoTemplate.getDb();
        GridFS fs = new GridFS(db, collection);
        GridFSInputFile fsInput = fs.createFile(bytes);
        fsInput = buildParams(fsInput, params);
        fsInput.save();
        //		long t2 = System.currentTimeMillis();
        //		System.out.println("保存用时(ms):"+(t2-t1)+" licenseNo:"+licenseNo);
    }
    
    /**
     * 封装文件参数
     * 
     * @param fsInput
     * @param params
     * @return
     */
    private static GridFSInputFile buildParams(GridFSInputFile fsInput,
        Map<String, Object> params)
    {
        if (params != null)
        {
            for (String key : params.keySet())
            {
                fsInput.put(key, params.get(key));
            }
        }
        return fsInput;
    }
    
    /**
     * deleteFile 删除文件
     * 
     * @param db
     *            数据库实体
     * @param collection
     *            集合名
     * @param params
     *            封装删除的条件
     */
    public static void deleteFile(String collection, Map<String, Object> params)
    {
        //		String licenseNo = (String) params.get("licenseNo");
        //		long t3 = System.currentTimeMillis();
        DB db = mongoTemplate.getDb();
        GridFS fs = new GridFS(db, collection);
        DBObject dbObj = getDBObject(params);
        fs.remove(dbObj);
        //		long t4 = System.currentTimeMillis();
        //		System.out.println("删除用时(ms):"+(t4-t3)+" licenseNo:"+licenseNo);
    }
    
    /**
     * 根据ObjectId删除文件
     * 
     * @param db
     *            数据库实体
     * @param collection
     *            集合名
     * @param id
     *            文件的标识
     */
    public static void deleteFile(String collection, ObjectId id)
    {
        DB db = mongoTemplate.getDb();
        GridFS fs = new GridFS(db, collection);
        fs.remove(id);
    }
    
    /**
     * 更新文件
     * 
     * @param db
     *            数据库实体
     * @param collection
     * @param in
     * @param id
     * @param params
     */
    public static void updateFile(String collection, InputStream in,
        ObjectId id, Map<String, Object> params)
    {
        deleteFile(collection, id);
        saveFile(collection, in, params);
    }
    
    /**
     * 更新文件
     * 
     * @param db
     *            数据库实体
     * @param collection
     * @param in
     * @param params
     */
    public static void updateFile(String collection, InputStream in,
        Map<String, Object> params)
    {
        deleteFile(collection, params);
        saveFile(collection, in, params);
    }
    
    /**
     * 获取文件总数
     * 
     * @param db
     *            数据库实体
     * @param collection
     * @param params
     * @return
     */
    public static int getRecordCount(DB db, String collection,
        Map<String, Object> params)
    {
        
        DBObject dbObj = getDBObject(params);
        GridFS fs = new GridFS(db, collection);
        return fs.getFileList(dbObj).count();
    }
    
    /**
     * 获取文件存储路径
     * 
     * @param db
     *            数据库实体
     * @param collection
     * @param params
     * @return
     */
    public static String getFilePath(String collection,
        Map<String, Object> params)
    {
        DBObject dbObj = getDBObject(params);
        List<GridFSDBFile> dbFile = getFSDBFile(collection, dbObj);
        if (dbFile == null || dbFile.size() > 1)
        {
            return null;
        }
        return dbFile.get(0).getFilename();
    }
    
    /**
     * 获取db对象 本类内部使用
     * 
     * @param db
     *            数据库实体
     * @param map
     * @return
     */
    private static DBObject getDBObject(Map<String, Object> map)
    {
        DBObject dbObj = null;
        if (map != null)
        {
            dbObj = new BasicDBObject();
            Set<String> keys = map.keySet();
            List<String> keyWords = Arrays.asList(KEY_WORDS);
            for (String key : keys)
            {
                if (keyWords.contains(key))
                {
                    
                }
                else
                {
                    Object obj = map.get(key);
                    // 如果不包含关键字，则取它作为key的value
                    if (keyWords.contains(obj))
                    {
                        dbObj.put(key,
                            new BasicDBObject((String)obj, map.get((String)obj)));
                    }
                    else
                    {
                        dbObj.put(key, obj);
                    }
                }
            }
        }
        return dbObj;
    }
    
    /**
     * 获取 GridFSDBFile对象 本类内部使用
     * 
     * @param db
     *            数据库实体
     * @param collection
     * @param dbObj
     * @return
     */
    private static List<GridFSDBFile> getFSDBFile(String collection,
        DBObject dbObj)
    {
        DB db = mongoTemplate.getDb();
        GridFS fs = new GridFS(db, collection);
        List<GridFSDBFile> dbFile = fs.find(dbObj);
        if (dbFile == null || dbFile.size() == 0)
        {
            return null;
        }
        return dbFile;
    }
    
    public static GridFSDBFile findOneFile(String collection,
        Map<String, Object> params)
    {
        DBObject dbObj = getDBObject(params);
        DB db = mongoTemplate.getDb();
        GridFS fs = new GridFS(db, collection);
        GridFSDBFile dbFile = fs.findOne(dbObj);
        return dbFile;
    }
}
