package com.henan.entity;

import java.util.Date;

public class HnCatalogSync
{
    /**
     * 证照目录编号
     */
    private String catalogNo;
    
    /**
     * 证照名称
     */
    private String licenseName;
    
    /**
     * 证照类型
     */
    private String licenseType;
    
    /**
     * 证照类型代码
     */
    private String licenceTypeCode;
    
    /**
     * 证照颁发机构代码
     */
    private String issueUnit;
    
    /**
     * 证照颁发机构名称
     */
    private String issueUnitname;
    
    /**
     * 证照持有主体类型0.自然人1.法人2.自然人或法人
     */
    private Integer whose;
    
    /**
     * 证照目录状态  11.有效-2.失效
     */
    private Integer state;
    
    /**
     * 证照目录类别0.批文1.证照
     */
    private Integer category;
    
    /**
     * 证照颁发机构行政区划代码
     */
    private String areaCode;
    
    /**
     * 创建时间
     */
    private Date createDate;
    
    /**
     * 同步状态 0.未同步 1.已同步
     */
    private Integer syncState;
    
    /**
     * 同步时间
     */
    private Date syncDate;
    
    /**
     * 证照底图
     */
    private byte[] licenseBaseImage;
    
    /**
     * 证照目录编号
     * @return catalog_no 证照目录编号
     */
    public String getCatalogNo()
    {
        return catalogNo;
    }
    
    /**
     * 证照目录编号
     * @param catalogNo 证照目录编号
     */
    public void setCatalogNo(String catalogNo)
    {
        this.catalogNo = catalogNo;
    }
    
    /**
     * 证照名称
     * @return license_name 证照名称
     */
    public String getLicenseName()
    {
        return licenseName;
    }
    
    /**
     * 证照名称
     * @param licenseName 证照名称
     */
    public void setLicenseName(String licenseName)
    {
        this.licenseName = licenseName;
    }
    
    /**
     * 证照类型
     * @return license_type 证照类型
     */
    public String getLicenseType()
    {
        return licenseType;
    }
    
    /**
     * 证照类型
     * @param licenseType 证照类型
     */
    public void setLicenseType(String licenseType)
    {
        this.licenseType = licenseType;
    }
    
    /**
     * 证照类型代码
     * @return licence_type_code 证照类型代码
     */
    public String getLicenceTypeCode()
    {
        return licenceTypeCode;
    }
    
    /**
     * 证照类型代码
     * @param licenceTypeCode 证照类型代码
     */
    public void setLicenceTypeCode(String licenceTypeCode)
    {
        this.licenceTypeCode = licenceTypeCode;
    }
    
    /**
     * 证照颁发机构代码
     * @return issue_unit 证照颁发机构代码
     */
    public String getIssueUnit()
    {
        return issueUnit;
    }
    
    /**
     * 证照颁发机构代码
     * @param issueUnit 证照颁发机构代码
     */
    public void setIssueUnit(String issueUnit)
    {
        this.issueUnit = issueUnit;
    }
    
    /**
     * 证照颁发机构名称
     * @return issue_unitname 证照颁发机构名称
     */
    public String getIssueUnitname()
    {
        return issueUnitname;
    }
    
    /**
     * 证照颁发机构名称
     * @param issueUnitname 证照颁发机构名称
     */
    public void setIssueUnitname(String issueUnitname)
    {
        this.issueUnitname = issueUnitname;
    }
    
    /**
     * 证照持有主体类型0.自然人1.法人2.自然人或法人
     * @return whose 证照持有主体类型0.自然人1.法人2.自然人或法人
     */
    public Integer getWhose()
    {
        return whose;
    }
    
    /**
     * 证照持有主体类型0.自然人1.法人2.自然人或法人
     * @param whose 证照持有主体类型0.自然人1.法人2.自然人或法人
     */
    public void setWhose(Integer whose)
    {
        this.whose = whose;
    }
    
    /**
     * 证照目录状态  11.有效-2.失效
     * @return state 证照目录状态  11.有效-2.失效
     */
    public Integer getState()
    {
        return state;
    }
    
    /**
     * 证照目录状态  11.有效-2.失效
     * @param state 证照目录状态  11.有效-2.失效
     */
    public void setState(Integer state)
    {
        this.state = state;
    }
    
    /**
     * 证照目录类别0.批文1.证照
     * @return category 证照目录类别0.批文1.证照
     */
    public Integer getCategory()
    {
        return category;
    }
    
    /**
     * 证照目录类别0.批文1.证照
     * @param category 证照目录类别0.批文1.证照
     */
    public void setCategory(Integer category)
    {
        this.category = category;
    }
    
    /**
     * 证照颁发机构行政区划代码
     * @return area_code 证照颁发机构行政区划代码
     */
    public String getAreaCode()
    {
        return areaCode;
    }
    
    /**
     * 证照颁发机构行政区划代码
     * @param areaCode 证照颁发机构行政区划代码
     */
    public void setAreaCode(String areaCode)
    {
        this.areaCode = areaCode;
    }
    
    /**
     * 创建时间
     * @return create_date 创建时间
     */
    public Date getCreateDate()
    {
        return createDate;
    }
    
    /**
     * 创建时间
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }
    
    /**
     * 同步状态 0.未同步 1.已同步
     * @return sync_state 同步状态 0.未同步 1.已同步
     */
    public Integer getSyncState()
    {
        return syncState;
    }
    
    /**
     * 同步状态 0.未同步 1.已同步
     * @param syncState 同步状态 0.未同步 1.已同步
     */
    public void setSyncState(Integer syncState)
    {
        this.syncState = syncState;
    }
    
    /**
     * 同步时间
     * @return sync_date 同步时间
     */
    public Date getSyncDate()
    {
        return syncDate;
    }
    
    /**
     * 同步时间
     * @param syncDate 同步时间
     */
    public void setSyncDate(Date syncDate)
    {
        this.syncDate = syncDate;
    }
    
    /**
     * 证照底图
     * @return license_base_image 证照底图
     */
    public byte[] getLicenseBaseImage()
    {
        return licenseBaseImage;
    }
    
    /**
     * 证照底图
     * @param licenseBaseImage 证照底图
     */
    public void setLicenseBaseImage(byte[] licenseBaseImage)
    {
        this.licenseBaseImage = licenseBaseImage;
    }
    
    public static void main(String[] args)
    {
        Date date = new Date();
        long timeStampSec = System.currentTimeMillis() / 1000;
        String timestamp = String.format("%010d", timeStampSec);
        System.out.println(timestamp);
    }
}