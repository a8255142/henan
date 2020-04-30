package com.henan.entity;

public class HnLicenseSync
{
    /**
     * 证照文件编号
     */
    private String licenseFileNumber;
    
    /**
     * 证照目录代码
     */
    private String catalogId;
    
    /**
     * 证照编号
     */
    private String licenseNumber;
    
    /**
     * 证照名称
     */
    private String licenseName;
    
    /**
     * 颁发时间
     */
    private String issueDate;
    
    /**
     * 证照有效期（起始）
     */
    private String validTimeBegin;
    
    /**
     * 证照有效期（截止）
     */
    private String validTimeEnd;
    
    /**
     * 证照颁发机构代码
     */
    private String issueUnit;
    
    /**
     * 证照颁发机构名称
     */
    private String issueUnitname;
    
    /**
     * 证照颁发机构行政区划代码
     */
    private String areaCode;
    
    /**
     * 证照持有主体
     */
    private String holder;
    
    /**
     * 证照持有主体代码
     */
    private String holderCode;
    
    /**
     * 证照目录类别0.批文1.证照
     */
    private String category;
    
    /**
     * 证照持有主体类型0.自然人1.法人2.自然人或法人
     */
    private String whose;
    
    /**
     * 证照状态 11. 有效-10. 暂时失效-4. 已过期-5. 已作废
     */
    private String state;
    
    /**
     * 创建时间
     */
    private String createDate;
    
    /**
     * 同步状态 0.未同步 1.已同步
     */
    private String syncState;
    
    /**
     * 同步时间
     */
    private String syncDate;
    
    /**
     * 同步文件类型(OFD/PDF)
     */
    private String fileType;
    
    /**
     * 证照类型
     */
    private String licenceType;
    
    /**
     * 证照类型代码
     */
    private String licenceTypeCode;
    
    /**
     * 证照类型代码
     */
    private String inputDate;
    
    /**
     * 证照文件编号
     * @return license_file_number 证照文件编号
     */
    public String getLicenseFileNumber()
    {
        return licenseFileNumber;
    }
    
    /**
     * 证照文件编号
     * @param licenseFileNumber 证照文件编号
     */
    public void setLicenseFileNumber(String licenseFileNumber)
    {
        this.licenseFileNumber = licenseFileNumber;
    }
    
    /**
     * 证照目录代码
     * @return catalog_id 证照目录代码
     */
    public String getCatalogId()
    {
        return catalogId;
    }
    
    /**
     * 证照目录代码
     * @param catalogId 证照目录代码
     */
    public void setCatalogId(String catalogId)
    {
        this.catalogId = catalogId;
    }
    
    /**
     * 证照编号
     * @return license_number 证照编号
     */
    public String getLicenseNumber()
    {
        return licenseNumber;
    }
    
    /**
     * 证照编号
     * @param licenseNumber 证照编号
     */
    public void setLicenseNumber(String licenseNumber)
    {
        this.licenseNumber = licenseNumber;
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
     * 证照持有主体
     * @return holder 证照持有主体
     */
    public String getHolder()
    {
        return holder;
    }
    
    /**
     * 证照持有主体
     * @param holder 证照持有主体
     */
    public void setHolder(String holder)
    {
        this.holder = holder;
    }
    
    /**
     * 证照持有主体代码
     * @return holder_code 证照持有主体代码
     */
    public String getHolderCode()
    {
        return holderCode;
    }
    
    /**
     * 证照持有主体代码
     * @param holderCode 证照持有主体代码
     */
    public void setHolderCode(String holderCode)
    {
        this.holderCode = holderCode;
    }
    
    public String getIssueDate()
    {
        return issueDate;
    }
    
    public void setIssueDate(String issueDate)
    {
        this.issueDate = issueDate;
    }
    
    public String getValidTimeBegin()
    {
        return validTimeBegin;
    }
    
    public void setValidTimeBegin(String validTimeBegin)
    {
        this.validTimeBegin = validTimeBegin;
    }
    
    public String getValidTimeEnd()
    {
        return validTimeEnd;
    }
    
    public void setValidTimeEnd(String validTimeEnd)
    {
        this.validTimeEnd = validTimeEnd;
    }
    
    public String getCategory()
    {
        return category;
    }
    
    public void setCategory(String category)
    {
        this.category = category;
    }
    
    public String getWhose()
    {
        return whose;
    }
    
    public void setWhose(String whose)
    {
        this.whose = whose;
    }
    
    public String getState()
    {
        return state;
    }
    
    public void setState(String state)
    {
        this.state = state;
    }
    
    public String getCreateDate()
    {
        return createDate;
    }
    
    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }
    
    public String getSyncState()
    {
        return syncState;
    }
    
    public void setSyncState(String syncState)
    {
        this.syncState = syncState;
    }
    
    public String getSyncDate()
    {
        return syncDate;
    }
    
    public void setSyncDate(String syncDate)
    {
        this.syncDate = syncDate;
    }
    
    public String getFileType()
    {
        return fileType;
    }
    
    public void setFileType(String fileType)
    {
        this.fileType = fileType;
    }
    
    public String getLicenceType()
    {
        return licenceType;
    }
    
    public void setLicenceType(String licenceType)
    {
        this.licenceType = licenceType;
    }
    
    public String getLicenceTypeCode()
    {
        return licenceTypeCode;
    }
    
    public void setLicenceTypeCode(String licenceTypeCode)
    {
        this.licenceTypeCode = licenceTypeCode;
    }
    
    public String getInputDate()
    {
        return inputDate;
    }
    
    public void setInputDate(String inputDate)
    {
        this.inputDate = inputDate;
    }
    
}