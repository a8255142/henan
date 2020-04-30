package com.henan.entity;

public class HnLicenseSyncWithBLOBs extends HnLicenseSync
{
    /**
     * 证照文件
     */
    private String licenseFile;
    
    /**
     * 证照扩展数据
     */
    private String newAddMetadata;
    
    /**
     * 电子证照图片
     */
    private String licenseBaseImage;
    
    /**
     * 版本号
     */
    private String licenseVersion;
    
    /**
     * 签章规则
     */
    private String rule;
    
    /**
     * 照面MongoDBID
     */
    private String objectId;
    
    /**
     * 证照文件
     * @return license_file 证照文件
     */
    public String getLicenseFile()
    {
        return licenseFile;
    }
    
    /**
     * 证照文件
     * @param licenseFile 证照文件
     */
    public void setLicenseFile(String licenseFile)
    {
        this.licenseFile = licenseFile;
    }
    
    /**
     * 证照扩展数据
     * @return new_add_metadata 证照扩展数据
     */
    public String getNewAddMetadata()
    {
        return newAddMetadata;
    }
    
    /**
     * 证照扩展数据
     * @param newAddMetadata 证照扩展数据
     */
    public void setNewAddMetadata(String newAddMetadata)
    {
        this.newAddMetadata = newAddMetadata;
    }
    
    /**
     * 电子证照图片
     * @return license_base_image 电子证照图片
     */
    public String getLicenseBaseImage()
    {
        return licenseBaseImage;
    }
    
    /**
     * 电子证照图片
     * @param licenseBaseImage 电子证照图片
     */
    public void setLicenseBaseImage(String licenseBaseImage)
    {
        this.licenseBaseImage = licenseBaseImage;
    }
    
    public String getLicenseVersion()
    {
        return licenseVersion;
    }
    
    public void setLicenseVersion(String licenseVersion)
    {
        this.licenseVersion = licenseVersion;
    }
    
    public String getRule()
    {
        return rule;
    }
    
    public void setRule(String rule)
    {
        this.rule = rule;
    }
    
    public String getObjectId()
    {
        return objectId;
    }
    
    public void setObjectId(String objectId)
    {
        this.objectId = objectId;
    }
    
}