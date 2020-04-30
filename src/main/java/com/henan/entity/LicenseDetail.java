package com.henan.entity;

import java.io.Serializable;
import java.util.Map;

/**
 * 证照信息实体
 */
public class LicenseDetail implements Serializable
{
    
    private static final long serialVersionUID = 1L;
    
    private Integer id;//证照id
    
    private Integer licenseId;//证照目录id
    
    private String code;//证照编码
    
    private String licenseNo;//证照唯一编号
    
    private String status;//证照状态:新录入(0),合成(2),盖章(3),注销(4)
    
    private String flag;//查询状态条件
    
    private Map<String, Object> dataMap;//照面数据项
    
    private String inputBy;//录入人
    
    private String inputDate;//录入时间
    
    private String holdBy;//持证人
    
    private String expiryDate;//过期时间
    
    private String searchField;//搜索对应字段
    
    private String deptNo;//所属部门
    
    private String licenseName;
    
    private String licenseVersion;//证照版本
    
    private String holdByNo;
    
    private String objectId;
    
    private Boolean canceled = Boolean.FALSE;//是否注销
    
    private String issueUnit;//证照颁发机构代码
    
    private String issueUnitname;//证照颁发机构名称
    
    private String areaCode;//证照颁发机构行政区划代码
    
    private String licenceTypeCode;//证照类型代码
    
    public String getObjectId()
    {
        return objectId;
    }
    
    public void setObjectId(String objectId)
    {
        this.objectId = objectId;
    }
    
    public String getHoldByNo()
    {
        return holdByNo;
    }
    
    public void setHoldByNo(String holdByNo)
    {
        this.holdByNo = holdByNo;
    }
    
    public String getLicenseVersion()
    {
        return licenseVersion;
    }
    
    public void setLicenseVersion(String licenseVersion)
    {
        this.licenseVersion = licenseVersion;
    }
    
    public Integer getLicenseId()
    {
        return licenseId;
    }
    
    public void setLicenseId(Integer licenseId)
    {
        this.licenseId = licenseId;
    }
    
    public String getCode()
    {
        return code;
    }
    
    public void setCode(String code)
    {
        this.code = code;
    }
    
    public String getLicenseNo()
    {
        return licenseNo;
    }
    
    public void setLicenseNo(String licenseNo)
    {
        this.licenseNo = licenseNo;
    }
    
    public String getStatus()
    {
        return status;
    }
    
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    public String getFlag()
    {
        return flag;
    }
    
    public void setFlag(String flag)
    {
        this.flag = flag;
    }
    
    public String getInputBy()
    {
        return inputBy;
    }
    
    public void setInputBy(String inputBy)
    {
        this.inputBy = inputBy;
    }
    
    public String getInputDate()
    {
        return inputDate;
    }
    
    public void setInputDate(String inputDate)
    {
        this.inputDate = inputDate;
    }
    
    public String getHoldBy()
    {
        return holdBy;
    }
    
    public void setHoldBy(String holdBy)
    {
        this.holdBy = holdBy;
    }
    
    public String getExpiryDate()
    {
        return expiryDate;
    }
    
    public void setExpiryDate(String expiryDate)
    {
        this.expiryDate = expiryDate;
    }
    
    public String getSearchField()
    {
        return searchField;
    }
    
    public void setSearchField(String searchField)
    {
        this.searchField = searchField;
    }
    
    public String getLicenseName()
    {
        return licenseName;
    }
    
    public void setLicenseName(String licenseName)
    {
        this.licenseName = licenseName;
    }
    
    public String getDeptNo()
    {
        return deptNo;
    }
    
    public void setDeptNo(String deptNo)
    {
        this.deptNo = deptNo;
    }
    
    public Map<String, Object> getDataMap()
    {
        return dataMap;
    }
    
    public void setDataMap(Map<String, Object> dataMap)
    {
        this.dataMap = dataMap;
    }
    
    public Boolean getCanceled()
    {
        return canceled;
    }
    
    public void setCanceled(Boolean canceled)
    {
        this.canceled = canceled;
    }
    
    public String getIssueUnit()
    {
        return issueUnit;
    }
    
    public void setIssueUnit(String issueUnit)
    {
        this.issueUnit = issueUnit;
    }
    
    public String getIssueUnitname()
    {
        return issueUnitname;
    }
    
    public void setIssueUnitname(String issueUnitname)
    {
        this.issueUnitname = issueUnitname;
    }
    
    public String getAreaCode()
    {
        return areaCode;
    }
    
    public void setAreaCode(String areaCode)
    {
        this.areaCode = areaCode;
    }
    
    public String getLicenceTypeCode()
    {
        return licenceTypeCode;
    }
    
    public void setLicenceTypeCode(String licenceTypeCode)
    {
        this.licenceTypeCode = licenceTypeCode;
    }
    
    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
}
